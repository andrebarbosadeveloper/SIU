package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;


public class ChargerStation extends TypePOI implements Serializable {
    private int id;
    private String nome;
    private String veiculo;

    //Para JavaFX
    private Location location;
    private TypePOI typePOI;

    public static ST<Integer, ChargerStation> chargerStationST = new ST<>();

    public ChargerStation(String nome, TypePOI typePOI, Location localizacao, int idCategoria, String categoria, int idStation, String veiculo) {
        super( nome, typePOI, localizacao, idCategoria, categoria);
        this.id = idStation;
        this.veiculo = veiculo;
    }

    public ChargerStation(String nome, TypePOI typePOI, Location localizacao) {
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

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
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
        return "ChargerStation{" +
                "id=" + id +
                ", veiculo='" + veiculo + '\'' +
                '}';
    }

    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(chargerStationST.contains(id)){
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe uma Estacao de Carregamento com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na chargerStationST
     */
    public void inserirChargerStationST() {
        if (chargerStationST.contains(id)) {
            System.out.println("ESTACAO DE CARREGAMENTO COM ID:" + id + "JÁ EXISTE!!!");
            return;
        }
        if(id <= 0){
            id = idUnico();
        }
        chargerStationST.put(id, this);
    }

    /**
     * Imprime Todos as Estacoes de Carregamento presentes na ST de Estacoes de Carregamento
     */
    public void imprimirChargerStationsST() {
        System.out.println("\nEstacoes de Carregamento:");
        for (int i : chargerStationST.keys()) {
            System.out.println("ID: " + chargerStationST.get(i).id);
            System.out.println("Nome: " + chargerStationST.get(i).getNome());
            System.out.println("Veiculo:" + chargerStationST.get(i).veiculo);
        }
    }


}