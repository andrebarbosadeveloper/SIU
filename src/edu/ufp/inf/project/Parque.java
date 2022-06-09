package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Parque extends TypePOI implements Serializable {

    private int id;
    private String nome;
    public static ST<Integer, Parque> parqueST = new ST<>();

    //Para JavaFX
    private Location location;
    private TypePOI typePOI;

    public Parque(String nome, TypePOI typePOI, Location localizacao, int id, String categoria) {
        super(nome, typePOI, localizacao, id, categoria);
        this.nome = nome;
        this.id = id;
    }

    public Parque(String nome, TypePOI typePOI, Location localizacao) {
        super( nome, typePOI, localizacao, typePOI.getId(), typePOI.getCategoria());
        this.id = poiST.size() + 1;
        this.location = localizacao;
        this.typePOI = typePOI;
    }


    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(parqueST.contains(id)){
            id++;
        }
        return id;
    }



    /**
     * Verifica que se já existe uma Boca de Incendio com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na bocaDeIncendioST
     */
    public void inserirParqueST() {
        if (parqueST.contains(id)) {
            System.out.println("Parque COM ID:" + id + "JÁ EXISTE!!!");
            return;
        }
        if(id <= 0){
            id = idUnico();
        }
        parqueST.put(id, this);
    }

    /**
     * Imprime Todos as Bocas de Incendio presentes na ST de Bocas de Incendio
     */
    public void imprimirParqueST() {
        System.out.println("\nParques:");
        for (int i : parqueST.keys()) {
            System.out.println("ID: " + parqueST.get(i).id);
            System.out.println("Nome: " + parqueST.get(i).nome);
        }
    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
}
