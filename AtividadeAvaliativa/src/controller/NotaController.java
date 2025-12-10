package controller;

import dao.NotaDAO;
import model.Diario;
import model.Nota;

public class NotaController {

    private NotaDAO notaDAO;
    private Diario diario; // usa para calcular média e aprovação

    public NotaController() {
        this.notaDAO = new NotaDAO();
        this.diario = new Diario();
    }

    // 1) Valida regras básicas da nota (0–10 e ID válido)
    private boolean validarDadosNota(Nota nota) {
        if (nota == null) {
            System.out.println("NotaController - validarDadosNota: nota nula");
            return false;
        }

        if (nota.getId() <= 0) {
            System.out.println("NotaController - validarDadosNota: ID inválido");
            return false;
        }

        // Regra 3: nota somente entre 0 e 10 (usa validarNota() da model)
        if (!nota.validarNota()) {
            System.out.println("NotaController - validarDadosNota: valor da nota fora do intervalo 0–10");
            return false;
        }

        return true;
    }

    // 2) Regra 1: garantir que aluno, disciplina, turma e período estejam preenchidos
    private boolean validarRelacoes(int idAluno, int idDisciplina, int idTurma, int idPeriodo) {
        if (idAluno <= 0) {
            System.out.println("NotaController - validarRelacoes: aluno não informado");
            return false;
        }
        if (idDisciplina <= 0) {
            System.out.println("NotaController - validarRelacoes: disciplina não informada");
            return false;
        }
        if (idTurma <= 0) {
            System.out.println("NotaController - validarRelacoes: turma não informada");
            return false;
        }
        if (idPeriodo <= 0) {
            System.out.println("NotaController - validarRelacoes: período não informado");
            return false;
        }
        return true;
    }

    // 3) Salvar nota aplicando regras de negócio
    public boolean salvarNota(Nota nota, int idAluno, int idDisciplina, int idTurma, int idPeriodo) {
        System.out.println("NotaController - salvarNota: iniciando");

        if (!validarDadosNota(nota)) {
            System.out.println("NotaController - salvarNota: validação de dados da nota falhou");
            return false;
        }

        if (!validarRelacoes(idAluno, idDisciplina, idTurma, idPeriodo)) {
            System.out.println("NotaController - salvarNota: validação de aluno/disciplina/turma/período falhou");
            return false;
        }

        boolean resultado = notaDAO.salvar(nota); // aqui seu DAO ainda só recebe Nota

        if (resultado) {
            System.out.println("NotaController - salvarNota: nota salva com sucesso");
        } else {
            System.out.println("NotaController - salvarNota: erro ao salvar no banco");
        }

        return resultado;
    }

    // 4) Alterar nota aplicando as mesmas regras
    public boolean alterarNota(Nota nota, int idAluno, int idDisciplina, int idTurma, int idPeriodo) {
        System.out.println("NotaController - alterarNota: iniciando");

        if (!validarDadosNota(nota)) {
            System.out.println("NotaController - alterarNota: validação de dados da nota falhou");
            return false;
        }

        if (!validarRelacoes(idAluno, idDisciplina, idTurma, idPeriodo)) {
            System.out.println("NotaController - alterarNota: validação de aluno/disciplina/turma/período falhou");
            return false;
        }

        boolean resultado = notaDAO.alterar(nota); // idem: DAO com assinatura simples

        if (resultado) {
            System.out.println("NotaController - alterarNota: nota alterada com sucesso");
        } else {
            System.out.println("NotaController - alterarNota: erro ao alterar no banco");
        }

        return resultado;
    }

    // 5) Excluir nota
    public boolean excluirNota(int id) {
        System.out.println("NotaController - excluirNota: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("NotaController - excluirNota: id inválido");
            return false;
        }

        boolean resultado = notaDAO.excluir(id);

        if (resultado) {
            System.out.println("NotaController - excluirNota: nota excluída com sucesso");
        } else {
            System.out.println("NotaController - excluirNota: erro ao excluir no banco");
        }

        return resultado;
    }

    // 6) Pesquisar nota por ID
    public Nota pesquisarNotaPorId(int id) {
        System.out.println("NotaController - pesquisarNotaPorId: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("NotaController - pesquisarNotaPorId: id inválido");
            return null;
        }

        Nota nota = notaDAO.pesquisarPorId(id);

        if (nota != null) {
            System.out.println("NotaController - pesquisarNotaPorId: nota encontrada");
        } else {
            System.out.println("NotaController - pesquisarNotaPorId: nota não encontrada");
        }

        return nota;
    }

    // 7) Regra 2: calcular média e verificar aprovação usando Diario
    public double calcularMedia(double[] notas) {
        double media = diario.calcularMedia(notas);
        System.out.println("NotaController - calcularMedia: média calculada = " + media);
        return media;
    }

    public boolean alunoAprovado(double[] notas) {
        boolean aprovado = diario.alunoAprovado(notas);
        System.out.println("NotaController - alunoAprovado: aprovado = " + aprovado);
        return aprovado;
    }
}
