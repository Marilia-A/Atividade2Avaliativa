package model;
public class Turma {
    private int id;
    private String nomeTurma;
    //get e set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeTurma() {
        return nomeTurma;
    }
    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
    //construtores
    public Turma(int id, String nomeTurma) {
        this.id = id;
        this.nomeTurma = nomeTurma;
    }
    public Turma() {
    }
    
    
}
