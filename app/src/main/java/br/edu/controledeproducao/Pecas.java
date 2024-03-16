package br.edu.controledeproducao;

public class Pecas {

    public Pecas(String nome, int id, String tipo, Obra obra) {
        this.obra = obra;
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
    }

    public Pecas() {
    }


    private int id;
    private Obra obra;
    private String tipo;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
