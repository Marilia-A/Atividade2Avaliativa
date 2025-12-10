package controller;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoController {

    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    // Regra de negócio 
    private boolean validarDadosAluno(Aluno aluno) {
        if (aluno == null) {
            System.out.println("AlunoController - validarDadosAluno: aluno nulo");
            return false; 
        }

        // ID obrigatório (pelo padrão do sistema)
        if (aluno.getId() <= 0) {
            System.out.println("AlunoController - validarDadosAluno: ID inválido");
            return false;
        }

        // Nome obrigatório
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            System.out.println("AlunoController - validarDadosAluno: nome vazio");
            return false;
        }

        // Matrícula obrigatória + regra dos 10 dígitos numéricos
        if (!aluno.validarMatricula()) { //puxa o treco da regra da model
            System.out.println("AlunoController - validarDadosAluno: matrícula inválida");
            return false;
        }

        return true;
    }

    public boolean salvarAluno(Aluno aluno) {
        System.out.println("AlunoController - salvarAluno: iniciando");

        if (!validarDadosAluno(aluno)) {
            System.out.println("AlunoController - salvarAluno: validação falhou");
            return false;
        }

        boolean resultado = alunoDAO.salvar(aluno);

        if (resultado) {
            System.out.println("AlunoController - salvarAluno: aluno salvo com sucesso");
        } else {
            System.out.println("AlunoController - salvarAluno: erro ao salvar no banco");
        }

        return resultado;
    }

    public boolean alterarAluno(Aluno aluno) {
        System.out.println("AlunoController - alterarAluno: iniciando");

        if (!validarDadosAluno(aluno)) {
            System.out.println("AlunoController - alterarAluno: validação falhou");
            return false;
        }

        boolean resultado = alunoDAO.alterar(aluno);

        if (resultado) {
            System.out.println("AlunoController - alterarAluno: aluno alterado com sucesso");
        } else {
            System.out.println("AlunoController - alterarAluno: erro ao alterar no banco");
        }

        return resultado;
    }

    public boolean excluirAluno(int id) {
        System.out.println("AlunoController - excluirAluno: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("AlunoController - excluirAluno: id inválido");
            return false;
        }

        boolean resultado = alunoDAO.excluir(id);

        if (resultado) {
            System.out.println("AlunoController - excluirAluno: aluno excluído com sucesso");
        } else {
            System.out.println("AlunoController - excluirAluno: erro ao excluir no banco");
        }

        return resultado;
    }

    public Aluno pesquisarAlunoPorId(int id) {
        System.out.println("AlunoController - pesquisarAlunoPorId: iniciando, id=" + id);

        if (id <= 0) {
            System.out.println("AlunoController - pesquisarAlunoPorId: id inválido");
            return null;
        }

        Aluno aluno = alunoDAO.pesquisarPorId(id);

        if (aluno != null) {
            System.out.println("AlunoController - pesquisarAlunoPorId: aluno encontrado");
        } else {
            System.out.println("AlunoController - pesquisarAlunoPorId: aluno não encontrado");
        }

        return aluno;
    }
}

