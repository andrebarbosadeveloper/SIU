package edu.ufp.inf.project;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.util.ArrayList;

public class Ways extends DirectedEdgeProject implements Serializable {
    private int id;
    private ArrayList<Tag> tags = new ArrayList<>();

    public static RedBlackBST<Integer, Ways> waysST = new RedBlackBST<>();


    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
     * the given {@code weight}.
     *
     * @param v      the tail vertex
     * @param w      the head vertex
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *                                  is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Ways(int v, int w, int distancia, int tempoMedioPercursoMin) {
        super(v, w, distancia, tempoMedioPercursoMin);
        this.id = idUnico();
    }

    /**
     * Retorna o último ID disponível
     * @return id
     */
    private int idUnico(){
        int id = 1;
        while(waysST.contains(id)){
            id++;
        }
        return id;
    }

    public void inserirWaysST(Ways way) {
        if (waysST.contains(id)) {
            System.out.println("Esta Way já existe!!!");
            return;
        }
        else if(id <= 0){
            id = idUnico();
        }
        waysST.put(id, way);
    }

    public void addTagWay(Tag tag){
        if (tags.contains(tag)){
            System.out.println("Ja existe esta Tag");
            return;
        }
        tags.add(tag);
    }


    public static void lerWaysFromTxt() {
        String line;
        In in = new In(".//data//dataset1_ways_nodepairs.txt");
        if (!in.exists()) {
            System.out.println("Ficheiro Invalido");
        } else {
            while (in.hasNextLine()) {
                line = in.readLine();
                int numberWays = Integer.parseInt(line);
                for (int i = 0; i < numberWays; i++) {
                    line = in.readLine();
                    String[] way = line.split(",", 5);
                    String[] tags = line.split(",");

                    Ways ways = new Ways(Integer.parseInt(way[1]), Integer.parseInt(way[2]), Integer.parseInt(way[3]), 100);
                    if (Integer.parseInt(tags[4]) != 0)
                        for (int j = 5; j < tags.length - 1; j = j + 2) {
                            ways.addTagWay(new Tag(tags[j], tags[j + 1]));
                        }
                    //System.out.println(ways);
                    ways.inserirWaysST(ways);
                }
            }
        }
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
