package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Semaforos extends TypePOI implements Serializable {
    private int id;
    private String nome;
    private String automoveis;
    private String peoes;

    //Para JavaFX
    private Location location;
    private TypePOI typePOI;

    public static ST<Integer, Semaforos> semaforosST = new ST<>();


    public Semaforos( String nome, TypePOI typePOI, Location localizacao, int idCategoria, String categoria, int idSemaforo, String automoveis, String peoes) {
        super( nome, typePOI, localizacao, idCategoria, categoria);
        this.id = idSemaforo;
        this.automoveis = automoveis;
        this.peoes = peoes;
    }

    public Semaforos(String nome, TypePOI typePOI, Location localizacao) {
        super( nome, typePOI, localizacao, typePOI.getId(), typePOI.getCategoria());
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

    public String getAutomoveis() {
        return automoveis;
    }

    public void setAutomoveis(String automoveis) {
        this.automoveis = automoveis;
    }

    public String getPeoes() {
        return peoes;
    }

    public void setPeoes(String peoes) {
        this.peoes = peoes;
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
        return "Semaforos{" +
                "id=" + id +
                ", automoveis='" + automoveis + '\'' +
                ", peoes='" + peoes + '\'' +
                '}';
    }

    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(semaforosST.contains(id)){
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Semaforo com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na semaforosST
     */
    public void inserirSemaforoST() {
        if (semaforosST.contains(id)) {
            System.out.println("Semaforo COM ID:" + id + "JÁ EXISTE!!!");
            return;
        }
        if(id <= 0){
            id = idUnico();
        }
        semaforosST.put(id, this);
    }

    /**
     * Imprime Todos os Semaforos presentes na ST de Semaforos
     */
    public void imprimirSemaforoST() {
        System.out.println("\nSemaforos:");
        for (int i : semaforosST.keys()) {
            System.out.println("ID: " + semaforosST.get(i).id);
            if(semaforosST.get(i).peoes == null){
                System.out.println("Automovel: " + semaforosST.get(i).automoveis);
            }
            else{
                System.out.println("Peoes: " + semaforosST.get(i).peoes);
            }
        }
    }
}