package controller;

import dao.ProfessorDAO;
import model.Professor;

public class ProfessorController {

    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    // Valida dados mínimos do professor
    private boolean validarDadosProfessor(Professor professor) {
        if (professor == null) {
            System.out.println("ProfessorController - validarDadosProfessor: professor nulo");
            return false;
        }

        if (professor.getId() <= 0) {
            System.out.println("ProfessorController - validarDadosProfessor: ID inválido");
            return false;
        }

        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            System.out.println("ProfessorController - validarDadosProfessor: nome vazio");
            return false;
        }

        if (professor.getMatricula() == null || professor.getMatricula().trim().isEmpty()) {
            System.out.println("ProfessorController - validarDadosProfessor: matrícula vazia");
            return false;
        }

        return true;
    }

    public boolean salvarProfessor(Professor professor) {
        System.out.println("ProfessorController - salvarProfessor: iniciando");

        if (!validarDadosProfessor(professor)) {
            System.out.println("ProfessorController - salvarProfessor: validação falhou");
            return false;
        }

        boolean resultado = professorDAO.salvar(professor);

        if (resultado) {
            System.out.println("ProfessorController - salvarProfessor: professor salvo com sucesso");
        } else {
            System.out.println("ProfessorController - salvarProfessor: erro ao salvar no banco");
        }

        return resultado;
    }

    public boolean alterarProfessor(Professor professor) {
        System.out.println("ProfessorController - alterarProfessor: iniciando");

        if (!validarDadosProfessor(professor)) {
            System.out.println("ProfessorController - alterarProfessor: validação falhou");
            return false;
        }

        boolean resultado = professorDAO.alterar(professor);

        if (resultado) {
            System.out.println("ProfessorController - alterarProfessor: professor alterado com sucesso");
        } else {
            System.out.println("ProfessorController - alterarProfessor: erro ao alterar no banco");
        }

        return resultado;
    }

    public boolean excluirProfessor(int id) {
        System.out.println("ProfessorController - excluirProfessor: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("ProfessorController - excluirProfessor: id inválido");
            return false;
        }

        boolean resultado = professorDAO.excluir(id);

        if (resultado) {
            System.out.println("ProfessorController - excluirProfessor: professor excluído com sucesso");
        } else {
            System.out.println("ProfessorController - excluirProfessor: erro ao excluir no banco");
        }

        return resultado;
    }

    public Professor pesquisarProfessorPorId(int id) {
        System.out.println("ProfessorController - pesquisarProfessorPorId: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("ProfessorController - pesquisarProfessorPorId: id inválido");
            return null;
        }

        Professor professor = professorDAO.pesquisarPorId(id);

        if (professor != null) {
            System.out.println("ProfessorController - pesquisarProfessorPorId: professor encontrado");
        } else {
            System.out.println("ProfessorController - pesquisarProfessorPorId: professor não encontrado");
        }

        return professor;
    }
}
