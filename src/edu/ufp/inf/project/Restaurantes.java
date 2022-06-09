package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class Restaurantes extends TypePOI implements Serializable {
    private int id;
    private String nome;
    private String vegan;
    private String stakehouse;
    private String marisqueira;

    //Para JavaFX
    private Location location;
    private TypePOI typePOI;

    public static ST<Integer, Restaurantes> restaurantesST = new ST<>();

    public Restaurantes(String nome, TypePOI typePOI, Location localizacao, int idCategoria, String categoria, int idRestaurante, String vegan, String stakehouse, String marisqueira) {
        super( nome, typePOI, localizacao, idCategoria, categoria);
        this.id = idRestaurante;
        this.vegan = vegan;
        this.stakehouse = stakehouse;
        this.marisqueira = marisqueira;
    }

    public Restaurantes(String nome, TypePOI typePOI, Location localizacao) {
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

    public String getVegan() {
        return vegan;
    }

    public void setVegan(String vegan) {
        this.vegan = vegan;
    }

    public String getStakehouse() {
        return stakehouse;
    }

    public void setStakehouse(String stakehouse) {
        this.stakehouse = stakehouse;
    }

    public String getMarisqueira() {
        return marisqueira;
    }

    public void setMarisqueira(String marisqueira) {
        this.marisqueira = marisqueira;
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
        return "Restaurantes{" +
                "id=" + id +
                ", vegan='" + vegan + '\'' +
                ", stakehouse='" + stakehouse + '\'' +
                ", marisqueira='" + marisqueira + '\'' +
                '}';
    }

    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(restaurantesST.contains(id)){
            id++;
        }
        return id;
    }

    /**
     * Verifica que se já existe um Restaurante com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
     * para verificar o ultimo Id disponivel e coloca o ID na restauranteST
     */
    public void inserirRestauranteST() {
        if (restaurantesST.contains(id)) {
            System.out.println("Restaurante COM ID:" + id + "JÁ EXISTE!!!");
            return;
        }
        if(id <= 0){
            id = idUnico();
        }
        restaurantesST.put(id, this);
    }

    /**
     * Imprime Todos os Restaurantes presentes na ST de Restaurantes
     */
    public void imprimirRestaurantesST() {
        System.out.println("\nRestaurantes:");
        for (int i : restaurantesST.keys()) {
            System.out.println("ID: " + restaurantesST.get(i).id);
            if(restaurantesST.get(i).vegan == null && restaurantesST.get(i).stakehouse == null){
                System.out.println("Marisqueira: " + restaurantesST.get(i).marisqueira);
            }
            else if(restaurantesST.get(i).vegan == null && restaurantesST.get(i).marisqueira == null){
                System.out.println("Stakehouse: " + restaurantesST.get(i).stakehouse);
            }
            else if(restaurantesST.get(i).stakehouse == null && restaurantesST.get(i).marisqueira == null){
                System.out.println("Vegan: " + restaurantesST.get(i).vegan);
            }
        }
    }
}