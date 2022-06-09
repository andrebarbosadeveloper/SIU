package edu.ufp.inf.project;


import java.io.Serializable;

public class TypePOI extends POI implements Serializable {
    private int id;
    private String categoria;

    public TypePOI( String nome, TypePOI typePOI, Location localizacao, int idCategoria, String categoria) {
        super(nome, typePOI, localizacao);
        this.id = idCategoria;
        this.categoria = categoria;
    }

    public TypePOI(int id, String categoria) {
        super();
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "TypePOI{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}