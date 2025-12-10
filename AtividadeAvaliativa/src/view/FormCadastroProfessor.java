package view;

import dao.ProfessorDAO;
import model.Professor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroProfessor extends JFrame {

    private JTextField txtId;
    private JTextField txtMatricula;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;

    private JButton btnSalvar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroProfessor() {
        setTitle("Cadastro de Professor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
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

        JPanel panelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnPesquisar = new JButton("Pesquisar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnPesquisar);
        panelBotoes.add(btnAlterar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSair);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(panelBotoes, gbc);
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

        if (idTexto.isEmpty() || matricula.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha ID, Matrícula e Nome!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Professor prof = new Professor();
        prof.setId(Integer.parseInt(idTexto));
        prof.setMatricula(matricula);
        prof.setNome(nome);
        prof.setEndereco(endereco);
        prof.setTelefone(telefone);
        prof.setEmail(email);

        ProfessorDAO dao = new ProfessorDAO();
        boolean ok = dao.salvar(prof);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Professor salvo com sucesso!");
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar professor.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pesquisar() {
        String idTexto = txtId.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do professor!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProfessorDAO dao = new ProfessorDAO();
        Professor prof = dao.pesquisarPorId(Integer.parseInt(idTexto));

        if (prof != null) {
            txtMatricula.setText(prof.getMatricula());
            txtNome.setText(prof.getNome());
            txtEndereco.setText(prof.getEndereco());
            txtTelefone.setText(prof.getTelefone());
            txtEmail.setText(prof.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "Professor não encontrado!");
        }
    }

    private void alterar() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do professor!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Professor prof = new Professor();
        prof.setId(Integer.parseInt(idTexto));
        prof.setMatricula(txtMatricula.getText().trim());
        prof.setNome(txtNome.getText().trim());
        prof.setEndereco(txtEndereco.getText().trim());
        prof.setTelefone(txtTelefone.getText().trim());
        prof.setEmail(txtEmail.getText().trim());

        ProfessorDAO dao = new ProfessorDAO();
        boolean ok = dao.alterar(prof);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Professor alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao alterar professor.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do professor!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProfessorDAO dao = new ProfessorDAO();
        boolean ok = dao.excluir(Integer.parseInt(idTexto));

        if (ok) {
            JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!");
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
        txtId.requestFocus();
    }
}
