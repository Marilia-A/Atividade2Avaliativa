package view;

import dao.TurmaDAO;
import java.awt.*;
import javax.swing.*;
import model.Turma;

public class FormCadastroTurma extends JFrame {

    private JTextField txtId;
    private JTextField txtNomeTurma;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroTurma() {
        setTitle("Cadastro de Turma");
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

        JLabel lblNome = new JLabel("Nome da Turma:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNome, gbc);
        txtNomeTurma = new JTextField();
        gbc.gridx = 1;
        add(txtNomeTurma, gbc);

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
        String nome    = txtNomeTurma.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome da Turma!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Turma turma = new Turma();
        turma.setId(Integer.parseInt(idTexto));
        turma.setNomeTurma(nome);

        TurmaDAO dao = new TurmaDAO();
        boolean ok = dao.salvar(turma);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Turma salva com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar turma.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da turma!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        TurmaDAO dao = new TurmaDAO();
        Turma turma = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (turma != null) {
            txtNomeTurma.setText(turma.getNomeTurma());
        } else {
            JOptionPane.showMessageDialog(this, "Turma não encontrada!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        String nome    = txtNomeTurma.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome da Turma!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Turma turma = new Turma();
        turma.setId(Integer.parseInt(idTexto));
        turma.setNomeTurma(nome);

        TurmaDAO dao = new TurmaDAO();
        boolean ok = dao.alterar(turma);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Turma alterada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar turma.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da turma!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        TurmaDAO dao = new TurmaDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Turma excluída com sucesso!");
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
        txtNomeTurma.setText("");
        txtId.requestFocus();
    }
}
