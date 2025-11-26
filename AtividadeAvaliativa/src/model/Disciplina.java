package model;
public class Disciplina {
    private int id;
    private String nomeDisciplina;
    //get e set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    //construtores 
    public Disciplina(int id, String nomeDisciplina) {
        this.id = id;
        this.nomeDisciplina = nomeDisciplina;
    }
    public Disciplina() {
    }
    
    
}
