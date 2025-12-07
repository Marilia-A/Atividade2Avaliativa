package view;
import java.awt.*;
import javax.swing.*;

public class FormCadastroAluno extends JFrame {

    private JTextField txtId;
    private JTextField txtMatricula;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtNomePai;
    private JTextField txtNomeMae;

    private JButton btnSalvar;
    private JButton btnPesquisar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroAluno() {
        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
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

        JLabel lblMatricula = new JLabel("Matrícula:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblMatricula, gbc);
        txtMatricula = new JTextField();
        gbc.gridx = 1;
        add(txtMatricula, gbc);

        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblNome, gbc);
        txtNome = new JTextField();
        gbc.gridx = 1;
        add(txtNome, gbc);

        JLabel lblEndereco = new JLabel("Endereço:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblEndereco, gbc);
        txtEndereco = new JTextField();
        gbc.gridx = 1;
        add(txtEndereco, gbc);

        JLabel lblTelefone = new JLabel("Telefone:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(lblTelefone, gbc);
        txtTelefone = new JTextField();
        gbc.gridx = 1;
        add(txtTelefone, gbc);

        JLabel lblEmail = new JLabel("Email:");
        gbc.gridx = 0; gbc.gridy = 5;
        add(lblEmail, gbc);
        txtEmail = new JTextField();
        gbc.gridx = 1;
        add(txtEmail, gbc);

        JLabel lblNomePai = new JLabel("Nome do Pai:");
        gbc.gridx = 0; gbc.gridy = 6;
        add(lblNomePai, gbc);
        txtNomePai = new JTextField();
        gbc.gridx = 1;
        add(txtNomePai, gbc);

        JLabel lblNomeMae = new JLabel("Nome da Mãe:");
        gbc.gridx = 0; gbc.gridy = 7;
        add(lblNomeMae, gbc);
        txtNomeMae = new JTextField();
        gbc.gridx = 1;
        add(txtNomeMae, gbc);

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
        gbc.gridx = 0; gbc.gridy = 8;
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
        // depois liga com controller/DAO: cria Aluno, valida matrícula, persiste
    }

    private void pesquisar() {
        // busca no banco e preenche campos
    }

    private void alterar() {
        // atualiza dados do aluno
    }

    private void excluir() {
        // exclui aluno
    }

    private void limpar() {
        txtId.setText("");
        txtMatricula.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtNomePai.setText("");
        txtNomeMae.setText("");
    }
}
