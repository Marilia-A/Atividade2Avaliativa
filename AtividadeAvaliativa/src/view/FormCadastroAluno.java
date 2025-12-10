package view;

import dao.AlunoDAO;
import model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroAluno() {
        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        inicializarComponentes();
        adicionarEventos();

        setVisible(true);
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblId, gbc);

        txtId = new JTextField();
        gbc.gridx = 1;
        add(txtId, gbc);

        JLabel lblMatricula = new JLabel("Matrícula:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblMatricula, gbc);

        txtMatricula = new JTextField();
        gbc.gridx = 1;
        add(txtMatricula, gbc);

        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblNome, gbc);

        txtNome = new JTextField();
        gbc.gridx = 1;
        add(txtNome, gbc);

        JLabel lblEndereco = new JLabel("Endereço:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblEndereco, gbc);

        txtEndereco = new JTextField();
        gbc.gridx = 1;
        add(txtEndereco, gbc);

        JLabel lblTelefone = new JLabel("Telefone:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblTelefone, gbc);

        txtTelefone = new JTextField();
        gbc.gridx = 1;
        add(txtTelefone, gbc);

        JLabel lblEmail = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblEmail, gbc);

        txtEmail = new JTextField();
        gbc.gridx = 1;
        add(txtEmail, gbc);

        JLabel lblNomePai = new JLabel("Nome do Pai:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblNomePai, gbc);

        txtNomePai = new JTextField();
        gbc.gridx = 1;
        add(txtNomePai, gbc);

        JLabel lblNomeMae = new JLabel("Nome da Mãe:");
        gbc.gridx = 0;
        gbc.gridy = 7;
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
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(painelBotoes, gbc);
    }

    private void adicionarEventos() {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        btnSair.addActionListener(e -> dispose());
    }

    private void salvar() {
        String idTexto   = txtId.getText().trim();
        String matricula = txtMatricula.getText().trim();
        String nome      = txtNome.getText().trim();
        String endereco  = txtEndereco.getText().trim();
        String telefone  = txtTelefone.getText().trim();
        String email     = txtEmail.getText().trim();
        String nomePai   = txtNomePai.getText().trim();
        String nomeMae   = txtNomeMae.getText().trim();

        if (idTexto.isEmpty() || matricula.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID, Matrícula e Nome!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Aluno aluno = new Aluno();
        aluno.setId(Integer.parseInt(idTexto));
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setEndereco(endereco);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setNomePai(nomePai);
        aluno.setNomeMae(nomeMae);

        if (!aluno.validarMatricula()) {
            JOptionPane.showMessageDialog(this,
                    "Matrícula deve ter 10 dígitos numéricos.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        AlunoDAO dao = new AlunoDAO();
        boolean ok = dao.salvar(aluno);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar aluno.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do aluno!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (aluno != null) {
            txtMatricula.setText(aluno.getMatricula());
            txtNome.setText(aluno.getNome());
            txtEndereco.setText(aluno.getEndereco());
            txtTelefone.setText(aluno.getTelefone());
            txtEmail.setText(aluno.getEmail());
            txtNomePai.setText(aluno.getNomePai());
            txtNomeMae.setText(aluno.getNomeMae());
        } else {
            JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do aluno!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Aluno aluno = new Aluno();
        aluno.setId(Integer.parseInt(idTexto));
        aluno.setMatricula(txtMatricula.getText().trim());
        aluno.setNome(txtNome.getText().trim());
        aluno.setEndereco(txtEndereco.getText().trim());
        aluno.setTelefone(txtTelefone.getText().trim());
        aluno.setEmail(txtEmail.getText().trim());
        aluno.setNomePai(txtNomePai.getText().trim());
        aluno.setNomeMae(txtNomeMae.getText().trim());

        AlunoDAO dao = new AlunoDAO();
        boolean ok = dao.alterar(aluno);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar aluno.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do aluno!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        AlunoDAO dao = new AlunoDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
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
        txtMatricula.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtNomePai.setText("");
        txtNomeMae.setText("");
        txtId.requestFocus();
    }
}
