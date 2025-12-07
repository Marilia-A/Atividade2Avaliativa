package model;
public class Diario {
    private int id;
    private boolean status;
    //get e set
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
    //construtores
    public Diario(int id, boolean status) {
        this.id = id;
        this.status = status;
    }
    public Diario() {
    }
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
