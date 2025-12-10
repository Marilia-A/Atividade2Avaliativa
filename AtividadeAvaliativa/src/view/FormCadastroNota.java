package view;

import dao.NotaDAO;
import dao.AlunoDAO;
import dao.DisciplinaDAO;
import dao.TurmaDAO;
import dao.PeriodoDAO;

import model.Nota;
import model.Aluno;
import model.Disciplina;
import model.Turma;
import model.Periodo;

import java.awt.*;
import javax.swing.*;
import java.util.List;

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

        // eventos
        btnSalvar.addActionListener(e -> salvar());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        btnSair.addActionListener(e -> dispose());

        // carregar combos
        carregarAlunos();
        carregarDisciplinas();
        carregarTurmas();
        carregarPeriodos();

        setVisible(true);
    }

    private void carregarAlunos() {
        cmbAluno.removeAllItems();
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> lista = dao.listarTodos(); // implemente no DAO
        for (Aluno a : lista) {
            cmbAluno.addItem(a.getId() + " - " + a.getNome());
        }
        cmbAluno.setSelectedIndex(-1);
    }

    private void carregarDisciplinas() {
        cmbDisciplina.removeAllItems();
        DisciplinaDAO dao = new DisciplinaDAO();
        List<Disciplina> lista = dao.listarTodos();
        for (Disciplina d : lista) {
            cmbDisciplina.addItem(d.getId() + " - " + d.getNomeDisciplina());
        }
        cmbDisciplina.setSelectedIndex(-1);
    }

    private void carregarTurmas() {
        cmbTurma.removeAllItems();
        TurmaDAO dao = new TurmaDAO();
        List<Turma> lista = dao.listarTodos();
        for (Turma t : lista) {
            cmbTurma.addItem(t.getId() + " - " + t.getNomeTurma());
        }
        cmbTurma.setSelectedIndex(-1);
    }

    private void carregarPeriodos() {
        cmbPeriodo.removeAllItems();
        PeriodoDAO dao = new PeriodoDAO();
        List<Periodo> lista = dao.listarTodos();
        for (Periodo p : lista) {
            cmbPeriodo.addItem(p.getId() + " - " + p.getNomePeriodo());
        }
        cmbPeriodo.setSelectedIndex(-1);
    }

    private void salvar() {
        String idTexto   = txtId.getText().trim();
        String notaTexto = txtNota.getText().trim();

        if (idTexto.isEmpty() || notaTexto.isEmpty()
                || cmbAluno.getSelectedIndex()      == -1
                || cmbDisciplina.getSelectedIndex() == -1
                || cmbTurma.getSelectedIndex()      == -1
                || cmbPeriodo.getSelectedIndex()    == -1) {

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
        boolean ok = dao.salvar(nota);

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
            // aqui poderia, no futuro, posicionar as combos conforme os IDs relacionados
        } else {
            JOptionPane.showMessageDialog(this, "Nota não encontrada!");
        }
    }

    private void alterar() {
        String idTexto   = txtId.getText().trim();
        String notaTexto = txtNota.getText().trim();

        if (idTexto.isEmpty() || notaTexto.isEmpty()
                || cmbAluno.getSelectedIndex()      == -1
                || cmbDisciplina.getSelectedIndex() == -1
                || cmbTurma.getSelectedIndex()      == -1
                || cmbPeriodo.getSelectedIndex()    == -1) {

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
        boolean ok = dao.alterar(nota);

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
