package model;
public class Nota {
    private int id;
    private double nota;
    //get e set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    //construtores
    public Nota(int id, double nota) {
        this.id = id;
        this.nota = nota;
    }
    public Nota() {
    }
      public boolean validarNota() {
        return nota >= 0.0 && nota <= 10.0;
    }
}
