package model;
public class Aluno extends Pessoa { 
    private String matricula;
    private String nomePai;
    private String nomeMae;
    //get e set
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNomePai() {
        return nomePai;
    }
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
    public String getNomeMae() {
        return nomeMae;
    }
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    //construtores
   
    public Aluno() {
    }
    public Aluno(int id, String nome, String endereco, String telefone, String email, String matricula, String nomePai,
            String nomeMae) {
        super(id, nome, endereco, telefone, email);
        this.matricula = matricula;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }
    
}