package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class BocaDeIncendio extends TypePOI implements Serializable {
    private int id;
    private String nome;
    private String manual;
    private String automatica;

    //Para JavaFX
    private Location location;
    private TypePOI typePOI;

    public static ST<Integer, BocaDeIncendio> bocaDeIncendioST = new ST<>();

    public BocaDeIncendio(String nome, TypePOI typePOI, Location localizacao, int idCategoria, String categoria, int idBoca, String manual, String automatica) {
        super(nome, typePOI, localizacao, idCategoria, categoria);
        this.id = idBoca;
        this.manual = manual;
        this.automatica = automatica;
    }

    public BocaDeIncendio(String nome, TypePOI typePOI, Location localizacao) {
        super(nome, typePOI, localizacao, typePOI.getId(), typePOI.getCategoria());
        this.id = poiST.size() + 1;
        this.location = localizacao;
        this.typePOI = typePOI;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getAutomatica() {
        return automatica;
    }

    public void setAutomatica(String automatica) {
        this.automatica = automatica;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public TypePOI getTypePOI() {
        return typePOI;
    }

    @Override
    public void setTypePOI(TypePOI typePOI) {
        this.typePOI = typePOI;
    }


    @Override
    public String toString() {
        return "BocaDeIncendio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", manual='" + manual + '\'' +
                ", automatica='" + automatica + '\'' +
                '}';
    }

    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(bocaDeIncendioST.contains(id)){
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe uma Boca de Incendio com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na bocaDeIncendioST
     */
    public void inserirBocaDeIncendioST() {
        if (bocaDeIncendioST.contains(id)) {
            System.out.println("Boca de Incendio COM ID:" + id + "JÁ EXISTE!!!");
            return;
        }
        if(id <= 0){
            id = idUnico();
        }
        bocaDeIncendioST.put(id, this);
    }

    /**
     * Imprime Todos as Bocas de Incendio presentes na ST de Bocas de Incendio
     */
    public void imprimirBocasDeIncendioST() {
        System.out.println("\nBocas de Incendio:");
        for (int i : bocaDeIncendioST.keys()) {
            System.out.println("ID: " + bocaDeIncendioST.get(i).id);
            if(bocaDeIncendioST.get(i).automatica == null){
                System.out.println("Manual: " + bocaDeIncendioST.get(i).manual);
            }
            else{
                System.out.println("Automatica: " + bocaDeIncendioST.get(i).automatica);
            }
        }
    }
}