package view;

import dao.PeriodoDAO;
import java.awt.*;
import javax.swing.*;
import model.Periodo;

public class FormCadastroPeriodo extends JFrame {

    private JTextField txtId;
    private JTextField txtNomePeriodo;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroPeriodo() {
        setTitle("Cadastro de Período");
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

        JLabel lblNome = new JLabel("Nome do Período:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNome, gbc);
        txtNomePeriodo = new JTextField();
        gbc.gridx = 1;
        add(txtNomePeriodo, gbc);

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
        String nome    = txtNomePeriodo.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome do Período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Periodo periodo = new Periodo();
        periodo.setId(Integer.parseInt(idTexto));
        periodo.setNomePeriodo(nome);

        PeriodoDAO dao = new PeriodoDAO();
        boolean ok = dao.salvar(periodo);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Período salvo com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar período.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        PeriodoDAO dao = new PeriodoDAO();
        Periodo periodo = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (periodo != null) {
            txtNomePeriodo.setText(periodo.getNomePeriodo());
        } else {
            JOptionPane.showMessageDialog(this, "Período não encontrado!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        String nome    = txtNomePeriodo.getText().trim();

        if (idTexto.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID e Nome do Período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Periodo periodo = new Periodo();
        periodo.setId(Integer.parseInt(idTexto));
        periodo.setNomePeriodo(nome);

        PeriodoDAO dao = new PeriodoDAO();
        boolean ok = dao.alterar(periodo);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Período alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar período.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        PeriodoDAO dao = new PeriodoDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Período excluído com sucesso!");
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
        txtNomePeriodo.setText("");
        txtId.requestFocus();
    }
}
