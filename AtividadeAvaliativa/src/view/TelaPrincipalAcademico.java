package view;
import java.awt.*;
import javax.swing.*;

public class TelaPrincipalAcademico extends JFrame {

    public TelaPrincipalAcademico() {
        setTitle("Sistema Acadêmico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        criarMenu();
        criarConteudoPrincipal();

        setVisible(true);
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastros = new JMenu("Cadastros");

        JMenuItem menuItemAluno = new JMenuItem("Aluno");
        menuItemAluno.addActionListener(e -> abrirFormularioAluno());

        JMenuItem menuItemProfessor = new JMenuItem("Professor");
        menuItemProfessor.addActionListener(e -> abrirFormularioProfessor());

        JMenuItem menuItemDisciplina = new JMenuItem("Disciplina");
        menuItemDisciplina.addActionListener(e -> abrirFormularioDisciplina());

        JMenuItem menuItemTurma = new JMenuItem("Turma");
        menuItemTurma.addActionListener(e -> abrirFormularioTurma());

        JMenuItem menuItemPeriodo = new JMenuItem("Período");
        menuItemPeriodo.addActionListener(e -> abrirFormularioPeriodo());

        JMenuItem menuItemDiario = new JMenuItem("Diário");
        menuItemDiario.addActionListener(e -> abrirFormularioDiario());

        JMenuItem menuItemNota = new JMenuItem("Nota");
        menuItemNota.addActionListener(e -> abrirFormularioNota());

        menuCadastros.add(menuItemAluno);
        menuCadastros.add(menuItemProfessor);
        menuCadastros.add(menuItemDisciplina);
        menuCadastros.add(menuItemTurma);
        menuCadastros.add(menuItemPeriodo);
        menuCadastros.add(menuItemDiario);
        menuCadastros.add(menuItemNota);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem menuItemSobre = new JMenuItem("Sobre");
        menuItemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(menuItemSobre);

        menuBar.add(menuCadastros);
        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);
    }

    private void criarConteudoPrincipal() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        JPanel painelBoasVindas = new JPanel(new GridBagLayout());
        painelBoasVindas.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel lblTitulo = new JLabel("Sistema Acadêmico");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelBoasVindas.add(lblTitulo, gbc);

        JLabel lblSubtitulo = new JLabel("Use o menu ou os botões para acessar os cadastros");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(70, 70, 70));
        gbc.gridy = 1;
        painelBoasVindas.add(lblSubtitulo, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setBackground(new Color(240, 248, 255));

        Color azulClaro = new Color(70, 130, 180);

        JButton btnAluno = criarBotaoAzul("Aluno", azulClaro, e -> abrirFormularioAluno());
        JButton btnProfessor = criarBotaoAzul("Professor", azulClaro, e -> abrirFormularioProfessor());
        JButton btnDisciplina = criarBotaoAzul("Disciplina", azulClaro, e -> abrirFormularioDisciplina());
        JButton btnTurma = criarBotaoAzul("Turma", azulClaro, e -> abrirFormularioTurma());
        JButton btnPeriodo = criarBotaoAzul("Período", azulClaro, e -> abrirFormularioPeriodo());
        JButton btnDiario = criarBotaoAzul("Diário", azulClaro, e -> abrirFormularioDiario());
        JButton btnNota = criarBotaoAzul("Nota", azulClaro, e -> abrirFormularioNota());

        painelBotoes.add(btnAluno);
        painelBotoes.add(btnProfessor);
        painelBotoes.add(btnDisciplina);
        painelBotoes.add(btnTurma);
        painelBotoes.add(btnPeriodo);
        painelBotoes.add(btnDiario);
        painelBotoes.add(btnNota);

        gbc.gridy = 2;
        painelBoasVindas.add(painelBotoes, gbc);

        painelPrincipal.add(painelBoasVindas, BorderLayout.CENTER);

        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelStatus.setBackground(new Color(220, 220, 220));
        painelStatus.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblStatus = new JLabel("Sistema pronto para uso");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
        painelStatus.add(lblStatus);

        painelPrincipal.add(painelStatus, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JButton criarBotaoAzul(String texto, Color cor, java.awt.event.ActionListener acao) {
        JButton btn = new JButton(texto);
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(acao);
        return btn;
    }

    private void abrirFormularioAluno() {
        SwingUtilities.invokeLater(() -> new FormCadastroAluno());
    }

    private void abrirFormularioProfessor() {
        SwingUtilities.invokeLater(() -> new FormCadastroProfessor());
    }

    private void abrirFormularioDisciplina() {
        SwingUtilities.invokeLater(() -> new FormCadastroDisciplina());
    }

    private void abrirFormularioTurma() {
        SwingUtilities.invokeLater(() -> new FormCadastroTurma());
    }

    private void abrirFormularioPeriodo() {
        SwingUtilities.invokeLater(() -> new FormCadastroPeriodo());
    }

    private void abrirFormularioDiario() {
        SwingUtilities.invokeLater(() -> new FormCadastroDiario());
    }

    private void abrirFormularioNota() {
        SwingUtilities.invokeLater(() -> new FormCadastroNota());
    }

    private void mostrarSobre() {
        String mensagem = "Sistema Acadêmico\nVersão 1.0\n\nCadastros de alunos, professores, disciplinas, turmas, períodos, diários e notas.";
        JOptionPane.showMessageDialog(this, mensagem, "Sobre o Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipalAcademico());
    }
}
