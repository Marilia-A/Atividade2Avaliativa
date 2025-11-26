package model;
public class Professor extends Pessoa {
    private String matricula;
    //get e set

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    //construtores

    public Professor(int id, String nome, String endereco, String telefone, String email, String matricula) {
        super(id, nome, endereco, telefone, email);
        this.matricula = matricula;
    }

    public Professor(String matricula) {
        this.matricula = matricula;
    }
    public Professor() {
    }
    
}
