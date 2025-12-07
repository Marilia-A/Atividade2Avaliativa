package view;
import java.awt.*;
import javax.swing.*;

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

        // depois você cria os métodos carregarAlunos(), carregarDisciplinas(), etc.
        // e chama aqui

        btnSalvar.addActionListener(e -> salvar());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnSair.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void salvar() {
        // pega id, nota, aluno, disciplina, turma, período
        // depois chama controller/DAO para:
        //  - validar campos obrigatórios (aluno, disciplina, turma, período)
        //  - validar nota 0–10
        //  - salvar no banco
    }

    private void pesquisar() {
        // pesquisa nota pelo ID e preenche campos/combos
    }

    private void alterar() {
        // altera uma nota existente
    }

    private void excluir() {
        // exclui nota pelo ID
    }

    private void limpar() {
        txtId.setText("");
        txtNota.setText("");
        cmbAluno.setSelectedIndex(-1);
        cmbDisciplina.setSelectedIndex(-1);
        cmbTurma.setSelectedIndex(-1);
        cmbPeriodo.setSelectedIndex(-1);
    }
}
