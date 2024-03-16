package br.edu.controledeproducao;

import java.io.Serializable;

public class Obra implements Serializable {

    private String nomeObra;
    private String nomeCliente;

    public Obra(String nomeObra, String nomeCliente) {
        this.nomeObra = nomeObra;
        this.nomeCliente = nomeCliente;
    }

    public Obra() {
        this.nomeObra = nomeObra;
        this.nomeCliente = nomeCliente;
    }
    public String getNomeObra() {
        return nomeObra;
    }

    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString(){
        return getNomeObra() + " (" + getNomeCliente() + ")";
    }
}
