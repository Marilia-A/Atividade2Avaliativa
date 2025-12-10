package view;

import dao.NotaDAO;
import java.awt.*;
import javax.swing.*;
import model.Nota;

public class FormCadastroNota extends JFrame {

    private JTextField txtId;
    private JTextField txtNota;
    private JComboBox<String> cmbAluno;
    private JComboBox<String> cmbDisciplina;
    private JComboBox<String> cmbTurma;
    private JComboBox<String> cmbPeriodo;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroNota() {
        setTitle("Cadastro de Nota");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
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

        JLabel lblAluno = new JLabel("Aluno:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblAluno, gbc);
        cmbAluno = new JComboBox<>();
        gbc.gridx = 1;
        add(cmbAluno, gbc);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblDisciplina, gbc);
        cmbDisciplina = new JComboBox<>();
        gbc.gridx = 1;
        add(cmbDisciplina, gbc);

        JLabel lblTurma = new JLabel("Turma:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblTurma, gbc);
        cmbTurma = new JComboBox<>();
        gbc.gridx = 1;
        add(cmbTurma, gbc);

        JLabel lblPeriodo = new JLabel("Período:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(lblPeriodo, gbc);
        cmbPeriodo = new JComboBox<>();
        gbc.gridx = 1;
        add(cmbPeriodo, gbc);

        JLabel lblNota = new JLabel("Nota:");
        gbc.gridx = 0; gbc.gridy = 5;
        add(lblNota, gbc);
        txtNota = new JTextField();
        gbc.gridx = 1;
        add(txtNota, gbc);

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
        gbc.gridx = 0; gbc.gridy = 6;
        add(painelBotoes, gbc);

        // depois você chama carregarAlunos(), carregarDisciplinas(), etc.

        btnSalvar.addActionListener(e -> salvar());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnSair.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void salvar() {
        String idTexto   = txtId.getText().trim();
        String notaTexto = txtNota.getText().trim();

        if (idTexto.isEmpty() || notaTexto.isEmpty()
                || cmbAluno.getSelectedIndex()     == -1
                || cmbDisciplina.getSelectedIndex() == -1
                || cmbTurma.getSelectedIndex()     == -1
                || cmbPeriodo.getSelectedIndex()   == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha ID, Nota, Aluno, Disciplina, Turma e Período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double valorNota;
        try {
            valorNota = Double.parseDouble(notaTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nota inválida. Informe um número.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Nota nota = new Nota();
        nota.setId(Integer.parseInt(idTexto));
        nota.setNota(valorNota);

        // regra: 0–10 pela model
        if (!nota.validarNota()) {
            JOptionPane.showMessageDialog(this,
                    "A nota deve estar entre 0 e 10.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        NotaDAO dao = new NotaDAO();
        boolean ok = dao.salvar(nota); // assinatura do seu DAO

        if (ok) {
            JOptionPane.showMessageDialog(this, "Nota salva com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar nota.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da nota!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        NotaDAO dao = new NotaDAO();
        Nota nota = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (nota != null) {
            txtNota.setText(String.valueOf(nota.getNota()));
            // combos podem ser ajustados depois quando o DAO/controlador devolver IDs
        } else {
            JOptionPane.showMessageDialog(this, "Nota não encontrada!");
        }
    }

    private void alterar() {
        String idTexto   = txtId.getText().trim();
        String notaTexto = txtNota.getText().trim();

        if (idTexto.isEmpty() || notaTexto.isEmpty()
                || cmbAluno.getSelectedIndex()     == -1
                || cmbDisciplina.getSelectedIndex() == -1
                || cmbTurma.getSelectedIndex()     == -1
                || cmbPeriodo.getSelectedIndex()   == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha ID, Nota, Aluno, Disciplina, Turma e Período!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double valorNota;
        try {
            valorNota = Double.parseDouble(notaTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nota inválida. Informe um número.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Nota nota = new Nota();
        nota.setId(Integer.parseInt(idTexto));
        nota.setNota(valorNota);

        if (!nota.validarNota()) {
            JOptionPane.showMessageDialog(this,
                    "A nota deve estar entre 0 e 10.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        NotaDAO dao = new NotaDAO();
        boolean ok = dao.alterar(nota); // assinatura do seu DAO

        if (ok) {
            JOptionPane.showMessageDialog(this, "Nota alterada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar nota.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID da nota!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        NotaDAO dao = new NotaDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Nota excluída com sucesso!");
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
        txtNota.setText("");
        cmbAluno.setSelectedIndex(-1);
        cmbDisciplina.setSelectedIndex(-1);
        cmbTurma.setSelectedIndex(-1);
        cmbPeriodo.setSelectedIndex(-1);
        txtId.requestFocus();
    }
}
