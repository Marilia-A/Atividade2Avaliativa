package view;

import dao.AlunoDAO;
import dao.DiarioDAO;
import dao.DisciplinaDAO;
import dao.PeriodoDAO;
import dao.TurmaDAO;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Aluno;
import model.Diario;
import model.Disciplina;
import model.Periodo;
import model.Turma;

public class FormCadastroDiario extends JFrame {

    private JTextField txtIdDiario;
    private JComboBox<String> cmbAluno;
    private JComboBox<String> cmbDisciplina;
    private JComboBox<String> cmbPeriodo;
    private JComboBox<String> cmbTurma;
    private JCheckBox chkAprovado;

    private JTextField txtNota;             
    private DefaultListModel<String> modeloNotas;
    private JList<String> lstNotas;

    private JLabel lblMedia;
    private JLabel lblSituacao;

    private JButton btnSalvarDiario;
    private JButton btnAlterarDiario;
    private JButton btnExcluirDiario;
    private JButton btnPesquisarDiario;
    private JButton btnLimpar;
    private JButton btnSair;

    private final AlunoDAO alunoDAO     = new AlunoDAO();
    private final DisciplinaDAO discDAO = new DisciplinaDAO();
    private final PeriodoDAO periodoDAO = new PeriodoDAO();
    private final TurmaDAO turmaDAO     = new TurmaDAO();
    private final DiarioDAO diarioDAO   = new DiarioDAO();

    public FormCadastroDiario() {
        setTitle("Cadastro de Diário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // janela maior
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // painel de fundo com margem
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(root);

        // Painel central dividido em duas colunas com muita dor de cabeça
        JPanel painelCentro = new JPanel(new GridLayout(1, 2, 15, 0));
        root.add(painelCentro, BorderLayout.CENTER);

        // dados do diario e configuração da parte esuqerda
        JPanel painelDados = new JPanel(new GridBagLayout());
        painelDados.setBorder(BorderFactory.createTitledBorder("Dados do Diário"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int linha = 0;

        JLabel lblId = new JLabel("ID Diário:");
        gbc.gridx = 0; gbc.gridy = linha;
        painelDados.add(lblId, gbc);

        txtIdDiario = new JTextField(12);
        gbc.gridx = 1;
        painelDados.add(txtIdDiario, gbc);

        JLabel lblAluno = new JLabel("Aluno:");
        gbc.gridx = 0; gbc.gridy = ++linha;
        painelDados.add(lblAluno, gbc);

        cmbAluno = new JComboBox<>();
        gbc.gridx = 1;
        painelDados.add(cmbAluno, gbc);

        JLabel lblDisc = new JLabel("Disciplina:");
        gbc.gridx = 0; gbc.gridy = ++linha;
        painelDados.add(lblDisc, gbc);

        cmbDisciplina = new JComboBox<>();
        gbc.gridx = 1;
        painelDados.add(cmbDisciplina, gbc);

        JLabel lblPeriodo = new JLabel("Período:");
        gbc.gridx = 0; gbc.gridy = ++linha;
        painelDados.add(lblPeriodo, gbc);

        cmbPeriodo = new JComboBox<>();
        gbc.gridx = 1;
        painelDados.add(cmbPeriodo, gbc);

        JLabel lblTurma = new JLabel("Turma:");
        gbc.gridx = 0; gbc.gridy = ++linha;
        painelDados.add(lblTurma, gbc);

        cmbTurma = new JComboBox<>();
        gbc.gridx = 1;
        painelDados.add(cmbTurma, gbc);

        JLabel lblAprovado = new JLabel("Situação geral:");
        gbc.gridx = 0; gbc.gridy = ++linha;
        painelDados.add(lblAprovado, gbc);

        chkAprovado = new JCheckBox("Aprovado");
        chkAprovado.setEnabled(false);
        gbc.gridx = 1;
        painelDados.add(chkAprovado, gbc);

        painelCentro.add(painelDados);

        // dados do lado direito e as configurações
        JPanel painelNotas = new JPanel(new BorderLayout(5, 5));
        painelNotas.setBorder(BorderFactory.createTitledBorder("Lançamento de Notas"));

        JPanel painelEntradaNota = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painelEntradaNota.add(new JLabel("Nota (0 a 10): "));
        txtNota = new JTextField(8);
        painelEntradaNota.add(txtNota);

        JButton btnAddNota = new JButton("Adicionar");
        JButton btnEditarNota = new JButton("Editar");
        JButton btnRemoverNota = new JButton("Remover");
        painelEntradaNota.add(btnAddNota);
        painelEntradaNota.add(btnEditarNota);
        painelEntradaNota.add(btnRemoverNota);

        painelNotas.add(painelEntradaNota, BorderLayout.NORTH);

        // o negocio no meio 
        modeloNotas = new DefaultListModel<>();
        lstNotas = new JList<>(modeloNotas);
        JScrollPane scrollNotas = new JScrollPane(lstNotas);
        painelNotas.add(scrollNotas, BorderLayout.CENTER);

        // base e media e etc
        JPanel painelResumo = new JPanel(new GridLayout(2, 1));
        JPanel linhaMedia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblMedia = new JLabel("Média: -");
        lblSituacao = new JLabel(" | Situação: -");
        linhaMedia.add(lblMedia);
        linhaMedia.add(lblSituacao);

        JPanel linhaBotaoStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvarNotasStatus = new JButton("Calcular média e atualizar status");
        linhaBotaoStatus.add(btnSalvarNotasStatus);

        painelResumo.add(linhaMedia);
        painelResumo.add(linhaBotaoStatus);

        painelNotas.add(painelResumo, BorderLayout.SOUTH);

        painelCentro.add(painelNotas);

        // botoes na esquerda
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createTitledBorder("Ações"));

        btnSalvarDiario    = new JButton("Salvar");
        btnAlterarDiario   = new JButton("Alterar");
        btnExcluirDiario   = new JButton("Excluir");
        btnPesquisarDiario = new JButton("Pesquisar");
        btnLimpar          = new JButton("Limpar");
        btnSair            = new JButton("Sair");

        Dimension tamBotao = new Dimension(160, 35);
        for (JButton b : new JButton[]{btnSalvarDiario, btnAlterarDiario, btnExcluirDiario,
                                       btnPesquisarDiario, btnLimpar, btnSair}) {
            b.setMaximumSize(tamBotao);
            painelBotoes.add(b);
            painelBotoes.add(Box.createVerticalStrut(8));
        }

        root.add(painelBotoes, BorderLayout.EAST);

        // carregano os combos 
        carregarAlunos();
        carregarDisciplinas();
        carregarPeriodos();
        carregarTurmas();

        // as coisas da nota
        btnAddNota.addActionListener(e -> adicionarNotaLista());
        btnEditarNota.addActionListener(e -> editarNotaLista());
        btnRemoverNota.addActionListener(e -> removerNotaLista());
        btnSalvarNotasStatus.addActionListener(e -> calcularMediaAtualizarStatus());

        // eventos normais
        btnSalvarDiario.addActionListener(e -> salvarDiario());
        btnAlterarDiario.addActionListener(e -> alterarDiario());
        btnExcluirDiario.addActionListener(e -> excluirDiario());
        btnPesquisarDiario.addActionListener(e -> pesquisarDiario());
        btnLimpar.addActionListener(e -> limpar());
        btnSair.addActionListener(e -> dispose());

        setVisible(true);
    }

    // helpers de carregar os mil combos e pegar os ids 

    private int pegarIdSelecionado(JComboBox<String> combo) {
        Object sel = combo.getSelectedItem();
        if (sel == null) return 0;
        String item = sel.toString();
        String idStr = item.split("-")[0].trim();
        return Integer.parseInt(idStr);
    }

    private void selecionarItemComboPorId(JComboBox<String> combo, int idBuscado) {
        if (idBuscado == 0) return;
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            String idStr = item.split("-")[0].trim();
            int id = Integer.parseInt(idStr);
            if (id == idBuscado) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void carregarAlunos() {
        cmbAluno.removeAllItems();
        for (Aluno a : alunoDAO.listarTodos()) {
            cmbAluno.addItem(a.getId() + " - " + a.getNome());
        }
        cmbAluno.setSelectedIndex(-1);
    }

    private void carregarDisciplinas() {
        cmbDisciplina.removeAllItems();
        for (Disciplina d : discDAO.listarTodos()) {
            cmbDisciplina.addItem(d.getId() + " - " + d.getNomeDisciplina());
        }
        cmbDisciplina.setSelectedIndex(-1);
    }

    private void carregarPeriodos() {
        cmbPeriodo.removeAllItems();
        for (Periodo p : periodoDAO.listarTodos()) {
            cmbPeriodo.addItem(p.getId() + " - " + p.getNomePeriodo());
        }
        cmbPeriodo.setSelectedIndex(-1);
    }

    private void carregarTurmas() {
        cmbTurma.removeAllItems();
        for (Turma t : turmaDAO.listarTodos()) {
            cmbTurma.addItem(t.getId() + " - " + t.getNomeTurma());
        }
        cmbTurma.setSelectedIndex(-1);
    }

    //coisas de nota

    private void adicionarNotaLista() {
        String texto = txtNota.getText().trim().replace(",", ".");
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe uma nota.", "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double valor = Double.parseDouble(texto);
            if (valor < 0 || valor > 10) {
                JOptionPane.showMessageDialog(this,
                        "A nota deve estar entre 0 e 10.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modeloNotas.addElement(String.valueOf(valor));
            txtNota.setText("");
            txtNota.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nota inválida. Informe um número.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editarNotaLista() {
        int idx = lstNotas.getSelectedIndex();
        if (idx == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma nota para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String atual = modeloNotas.get(idx);
        String nova = JOptionPane.showInputDialog(this,
                "Editar nota:", atual);
        if (nova == null) return;
        nova = nova.trim().replace(",", ".");
        try {
            double valor = Double.parseDouble(nova);
            if (valor < 0 || valor > 10) {
                JOptionPane.showMessageDialog(this,
                        "A nota deve estar entre 0 e 10.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modeloNotas.set(idx, String.valueOf(valor));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nota inválida.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerNotaLista() {
        int idx = lstNotas.getSelectedIndex();
        if (idx != -1) {
            modeloNotas.remove(idx);
        }
    }

    private void calcularMediaAtualizarStatus() {
        if (modeloNotas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Adicione ao menos uma nota.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Double> notas = new ArrayList<>();
        for (int i = 0; i < modeloNotas.size(); i++) {
            notas.add(Double.parseDouble(modeloNotas.get(i))); //linha que ta dando problema
        }
        double soma = 0.0;
        for (double n : notas) soma += n;
        double media = soma / notas.size();

        lblMedia.setText(String.format("Média: %.2f", media));
        boolean aprovado = media >= 6.0;
        lblSituacao.setText(aprovado ? " | Situação: Aprovado" : " | Situação: Reprovado");
        chkAprovado.setSelected(aprovado);
    }


    // asa coisas normais do diario 
    private void salvarDiario() {
        String idTexto = txtIdDiario.getText().trim();
        if (idTexto.isEmpty()
                || cmbAluno.getSelectedIndex() == -1
                || cmbDisciplina.getSelectedIndex() == -1
                || cmbTurma.getSelectedIndex() == -1
                || cmbPeriodo.getSelectedIndex() == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha ID, Aluno, Disciplina, Turma e Período!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Diario d = new Diario();
        d.setId(Integer.parseInt(idTexto));
        d.setStatus(chkAprovado.isSelected());
        d.setIdAluno(pegarIdSelecionado(cmbAluno));
        d.setIdDisciplina(pegarIdSelecionado(cmbDisciplina));
        d.setIdTurma(pegarIdSelecionado(cmbTurma));
        d.setIdPeriodo(pegarIdSelecionado(cmbPeriodo));

        boolean ok = diarioDAO.salvar(d);
        JOptionPane.showMessageDialog(this,
                ok ? "Diário salvo com sucesso!" : "Erro ao salvar diário.",
                ok ? "Sucesso" : "Erro",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    private void alterarDiario() {
        String idTexto = txtIdDiario.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o ID do Diário!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Diario d = new Diario();
        d.setId(Integer.parseInt(idTexto));
        d.setStatus(chkAprovado.isSelected());
        d.setIdAluno(pegarIdSelecionado(cmbAluno));
        d.setIdDisciplina(pegarIdSelecionado(cmbDisciplina));
        d.setIdTurma(pegarIdSelecionado(cmbTurma));
        d.setIdPeriodo(pegarIdSelecionado(cmbPeriodo));

        boolean ok = diarioDAO.alterar(d);
        JOptionPane.showMessageDialog(this,
                ok ? "Diário alterado com sucesso!" : "Erro ao alterar diário.",
                ok ? "Sucesso" : "Erro",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    private void excluirDiario() {
        String idTexto = txtIdDiario.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do Diário!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean ok = diarioDAO.excluir(Integer.parseInt(idTexto));
        JOptionPane.showMessageDialog(this,
                ok ? "Diário excluído com sucesso!" : "ID não encontrado ou erro ao excluir.",
                ok ? "Sucesso" : "Erro",
                ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (ok) limpar();
    }

    private void pesquisarDiario() {
        String idTexto = txtIdDiario.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o ID do Diário!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Diario d = diarioDAO.pesquisarPorId(Integer.parseInt(idTexto));
        if (d != null) {
            chkAprovado.setSelected(d.isStatus());
            selecionarItemComboPorId(cmbAluno,      d.getIdAluno());
            selecionarItemComboPorId(cmbDisciplina, d.getIdDisciplina());
            selecionarItemComboPorId(cmbTurma,      d.getIdTurma());
            selecionarItemComboPorId(cmbPeriodo,    d.getIdPeriodo());
        } else {
            JOptionPane.showMessageDialog(this, "Diário não encontrado!");
        }
    }

    private void limpar() {
        txtIdDiario.setText("");
        cmbAluno.setSelectedIndex(-1);
        cmbDisciplina.setSelectedIndex(-1);
        cmbPeriodo.setSelectedIndex(-1);
        cmbTurma.setSelectedIndex(-1);
        chkAprovado.setSelected(false);
        modeloNotas.clear();
        lblMedia.setText("Média: -");
        lblSituacao.setText(" | Situação: -");
        txtNota.setText("");
        txtNota.requestFocus();
    }
}
