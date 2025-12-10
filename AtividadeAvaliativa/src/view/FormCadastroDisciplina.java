package view;

import dao.DisciplinaDAO;
import java.awt.*;
import javax.swing.*;
import model.Disciplina;

public class FormCadastroDisciplina extends JFrame {

    private JTextField txtId;
    private JTextField txtNomeDisciplina;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroDisciplina() {
        setTitle("Cadastro de Disciplina");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblId, gbc);
        txtId = new JTextField();
        gbc.gridx = 1;
        add(txtId, gbc);

        JLabel lblNome = new JLabel("Nome da Disciplina:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNome, gbc);
        txtNomeDisciplina = new JTextField();
        gbc.gridx = 1;
        add(txtNomeDisciplina, gbc);

        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnPesquisar = new JButton("Pesquisar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnPesquisar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 2;
        add(painelBotoes, gbc);

        btnSalvar.addActionListener(e -> salvar());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnSair.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void salvar() {
        String idTexto = txtId.getText().trim();
        String nome    = txtNomeDisciplina.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome da Disciplina!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Disciplina disc = new Disciplina();
        disc.setId(Integer.parseInt(idTexto));
        disc.setNomeDisciplina(nome);

        DisciplinaDAO dao = new DisciplinaDAO();
        boolean ok = dao.salvar(disc);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Disciplina salva com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar disciplina.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da disciplina!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DisciplinaDAO dao = new DisciplinaDAO();
        Disciplina disc = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (disc != null) {
            txtNomeDisciplina.setText(disc.getNomeDisciplina());
        } else {
            JOptionPane.showMessageDialog(this, "Disciplina não encontrada!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        String nome    = txtNomeDisciplina.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome da Disciplina!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Disciplina disc = new Disciplina();
        disc.setId(Integer.parseInt(idTexto));
        disc.setNomeDisciplina(nome);

        DisciplinaDAO dao = new DisciplinaDAO();
        boolean ok = dao.alterar(disc);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Disciplina alterada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar disciplina.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da disciplina!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DisciplinaDAO dao = new DisciplinaDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Disciplina excluída com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "ID não encontrado ou erro ao excluir.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        txtId.setText("");
        txtNomeDisciplina.setText("");
        txtId.requestFocus();
    }
}
