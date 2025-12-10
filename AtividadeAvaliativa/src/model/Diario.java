package model;

public class Diario {
    private int id;
    private boolean status;

    // novos campos de relacionamento
    private int idAluno;
    private int idDisciplina;
    private int idTurma;
    private int idPeriodo;

    // get e set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdTurma() {
        return idTurma;
    }
    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    // construtores
    public Diario(int id, boolean status, int idAluno,
                  int idDisciplina, int idTurma, int idPeriodo) {
        this.id = id;
        this.status = status;
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.idTurma = idTurma;
        this.idPeriodo = idPeriodo;
    }

    public Diario(int id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public Diario() {
    }

    // regra de média
    public double calcularMedia(double[] notas) {
        if (notas == null || notas.length == 0) {
            return 0.0;
        }
        double soma = 0.0;
        for (double n : notas) {
            soma += n;
        }
        return soma / notas.length;
    }

    // Usa a regra 2: média < 6,0 reprovado; >= 6,0 aprovado
    public boolean alunoAprovado(double[] notas) {
        double media = calcularMedia(notas);
        return media >= 6.0;
    }
}
