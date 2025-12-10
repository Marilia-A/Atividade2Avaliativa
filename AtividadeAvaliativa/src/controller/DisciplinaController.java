package controller;

import dao.DisciplinaDAO;
import model.Disciplina;

public class DisciplinaController {

    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO();
    }

    // Valida dados mínimos da disciplina
    private boolean validarDadosDisciplina(Disciplina disciplina) {
        if (disciplina == null) {
            System.out.println("DisciplinaController - validarDadosDisciplina: disciplina nula");
            return false;
        }

        if (disciplina.getId() <= 0) {
            System.out.println("DisciplinaController - validarDadosDisciplina: ID inválido");
            return false;
        }

        if (disciplina.getNomeDisciplina() == null ||
                disciplina.getNomeDisciplina().trim().isEmpty()) {
            System.out.println("DisciplinaController - validarDadosDisciplina: nome da disciplina vazio");
            return false;
        }

        return true;
    }

    public boolean salvarDisciplina(Disciplina disciplina) {
        System.out.println("DisciplinaController - salvarDisciplina: iniciando");

        if (!validarDadosDisciplina(disciplina)) {
            System.out.println("DisciplinaController - salvarDisciplina: validação falhou");
            return false;
        }

        boolean resultado = disciplinaDAO.salvar(disciplina);

        if (resultado) {
            System.out.println("DisciplinaController - salvarDisciplina: disciplina salva com sucesso");
        } else {
            System.out.println("DisciplinaController - salvarDisciplina: erro ao salvar no banco");
        }

        return resultado;
    }

    public boolean alterarDisciplina(Disciplina disciplina) {
        System.out.println("DisciplinaController - alterarDisciplina: iniciando");

        if (!validarDadosDisciplina(disciplina)) {
            System.out.println("DisciplinaController - alterarDisciplina: validação falhou");
            return false;
        }

        boolean resultado = disciplinaDAO.alterar(disciplina);

        if (resultado) {
            System.out.println("DisciplinaController - alterarDisciplina: disciplina alterada com sucesso");
        } else {
            System.out.println("DisciplinaController - alterarDisciplina: erro ao alterar no banco");
        }

        return resultado;
    }

    public boolean excluirDisciplina(int id) {
        System.out.println("DisciplinaController - excluirDisciplina: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("DisciplinaController - excluirDisciplina: id inválido");
            return false;
        }

        boolean resultado = disciplinaDAO.excluir(id);

        if (resultado) {
            System.out.println("DisciplinaController - excluirDisciplina: disciplina excluída com sucesso");
        } else {
            System.out.println("DisciplinaController - excluirDisciplina: erro ao excluir no banco");
        }

        return resultado;
    }

    public Disciplina pesquisarDisciplinaPorId(int id) {
        System.out.println("DisciplinaController - pesquisarDisciplinaPorId: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("DisciplinaController - pesquisarDisciplinaPorId: id inválido");
            return null;
        }

        Disciplina disciplina = disciplinaDAO.pesquisarPorId(id);

        if (disciplina != null) {
            System.out.println("DisciplinaController - pesquisarDisciplinaPorId: disciplina encontrada");
        } else {
            System.out.println("DisciplinaController - pesquisarDisciplinaPorId: disciplina não encontrada");
        }

        return disciplina;
    }
}
