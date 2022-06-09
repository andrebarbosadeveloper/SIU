package edu.ufp.inf.project;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Node implements Serializable {

    private int id;
    private Point point;
    private ArrayList<Tag> tags = new ArrayList<>();



    public void addTagNode(Tag tag){
        if (tags.contains(tag)){
            System.out.println("Ja existe esta Tag");
        }
        else{
            tags.add(tag);
            //System.out.println("Tag criada com sucesso!");
        }
    }

    public void imprimirTags(){
        for (Tag tag : this.tags){
            //System.out.println(tag);
            tag.toStringFiles();
        }
    }


    public static void lerNodeFicheiroTxt() {
        String line;
        String type;
        //In in = new In(".//data//dataset1_nodes.txt");
        In in = new In(".//data//nodeOutputRandom.txt");
        if (!in.exists()) {
            System.out.println("Leitura Indisponivel");
        }
        while (in.hasNextLine()) {
            line = in.readLine();
            int numeroNodes = Integer.parseInt(line);

            for (int i = 0; i < numeroNodes; i++) {
                line = in.readLine();
                String[] poi = line.split(",", 4);
                String[] numeroTags = line.split(",");

                Location location = new Location("rede", Double.parseDouble(poi[1]), Double.parseDouble(poi[2]));

                type = poi[3].substring(0, poi[3].indexOf(","));        //poi[3].indexOf(",") -> Posicao da virgula

                switch (type) {
                    case "Restaurante": {
                        TypePOI typePOI = new TypePOI(1, "Restaurante");
                        Restaurantes restaurantes = new Restaurantes("Restaurante", typePOI, location);
                        restaurantes.inserirPOISsT();
                        restaurantes.inserirRestauranteST();
                        if (Integer.parseInt(numeroTags[4]) != 0) {
                            for (int j = 5; j < numeroTags.length - 1; j = j + 2) {
                                restaurantes.addTagNode(new Tag(numeroTags[j], numeroTags[j + 1]));
                            }
                        }
                        break;
                    }
                    case "ChargerStation": {
                        TypePOI typePOI = new TypePOI(2, "ChargerStation");
                        ChargerStation chargerStation = new ChargerStation("ChargerStation", typePOI, location);
                        chargerStation.inserirPOISsT();
                        chargerStation.inserirChargerStationST();
                        if (Integer.parseInt(numeroTags[4]) != 0) {
                            for (int j = 5; j < numeroTags.length - 1; j = j + 2) {
                                chargerStation.addTagNode(new Tag(numeroTags[j], numeroTags[j + 1]));
                            }
                        }
                        break;
                    }
                    case "Parque": {
                        TypePOI typePOI = new TypePOI(3, "Parque");
                        Parque parque = new Parque("Parque", typePOI, location);
                        parque.inserirPOISsT();
                        parque.inserirParqueST();
                        if (Integer.parseInt(numeroTags[4]) != 0) {
                            for (int j = 5; j < numeroTags.length - 1; j = j + 2) {
                                parque.addTagNode(new Tag(numeroTags[j], numeroTags[j + 1]));
                            }
                        }
                        break;
                    }
                    case "Semaforos": {
                        TypePOI typePOI = new TypePOI(4, "Semaforos");
                        Semaforos semaforos = new Semaforos("Semaforo", typePOI, location);
                        semaforos.inserirPOISsT();
                        semaforos.inserirSemaforoST();
                        if (Integer.parseInt(numeroTags[4]) != 0) {
                            for (int j = 5; j < numeroTags.length - 1; j = j + 2) {
                                semaforos.addTagNode(new Tag(numeroTags[j], numeroTags[j + 1]));
                            }
                        }
                        break;
                    }
                    case "BocaDeIncendio": {
                        TypePOI typePOI = new TypePOI(5, "BocaDeIncendio");
                        BocaDeIncendio bocaDeIncendio = new BocaDeIncendio("BocaDeIncendio", typePOI, location);
                        bocaDeIncendio.inserirPOISsT();
                        bocaDeIncendio.inserirBocaDeIncendioST();
                        if (Integer.parseInt(numeroTags[4]) != 0) {
                            for (int j = 5; j < numeroTags.length - 1; j = j + 2) {
                                bocaDeIncendio.addTagNode(new Tag(numeroTags[j], numeroTags[j + 1]));
                            }
                        }
                        break;
                    }
                }
            }
        }
    }


    public static void escreveNodeFicheiroTxt(){
        Out out = new Out(".//data//nodeOutput.txt");

        out.println(POI.poiST.size());

        for (int idPoi : POI.poiST.keys()){
            out.print(idPoi + "," +
                    POI.poiST.get(idPoi).getLocalizacao().getLatitude() + "," +
                    POI.poiST.get(idPoi).getLocalizacao().getLongitude() + "," +
                    POI.poiST.get(idPoi).getTypePOI().getCategoria() + "," +
                    POI.poiST.get(idPoi).getTags().size() + ",");
            for (Tag tag : POI.poiST.get(idPoi).getTags()){
                out.print(tag.getKey() + "," + tag.getValue());
            }
            out.println();
        }
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
