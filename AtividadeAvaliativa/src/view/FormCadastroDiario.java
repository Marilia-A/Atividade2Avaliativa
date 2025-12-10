package view;

import dao.DiarioDAO;
import java.awt.*;
import javax.swing.*;
import model.Diario;

public class FormCadastroDiario extends JFrame {

    private JTextField txtId;
    private JCheckBox chkAtivo;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroDiario() {
        setTitle("Cadastro de Diário");
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

        JLabel lblStatus = new JLabel("Ativo:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblStatus, gbc);
        chkAtivo = new JCheckBox();
        gbc.gridx = 1;
        add(chkAtivo, gbc);

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
        boolean status = chkAtivo.isSelected();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o ID do Diário!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Diario diario = new Diario();
        diario.setId(Integer.parseInt(idTexto));
        diario.setStatus(status);

        DiarioDAO dao = new DiarioDAO();
        boolean ok = dao.salvar(diario);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Diário salvo com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar diário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do Diário!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DiarioDAO dao = new DiarioDAO();
        Diario diario = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (diario != null) {
            chkAtivo.setSelected(diario.isStatus());
        } else {
            JOptionPane.showMessageDialog(this, "Diário não encontrado!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        boolean status = chkAtivo.isSelected();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o ID do Diário!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Diario diario = new Diario();
        diario.setId(Integer.parseInt(idTexto));
        diario.setStatus(status);

        DiarioDAO dao = new DiarioDAO();
        boolean ok = dao.alterar(diario);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Diário alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar diário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do Diário!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DiarioDAO dao = new DiarioDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Diário excluído com sucesso!");
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
        chkAtivo.setSelected(false);
        txtId.requestFocus();
    }
}

