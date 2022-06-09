package edu.ufp.inf.project;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import sun.font.DelegatingShape;

import java.util.ArrayList;

public class Test {

        public ArrayList<Integer> verticesAEvitar = new ArrayList<>();

        //Todos os PoI visitados/usados por um User num dado período Req: 5.a
        public void imprimirPOISvisitadosPorUser(User user, Date d1, Date d2, String subrede){
            ST<Integer, POI> poiUser = user.visitadosporUserPOI(subrede, d1, d2);
            System.out.println("Tamanho: " + poiUser.size());
            for (int date : poiUser.keys()){
                System.out.println("POIs visitados por " + User.usersST.get(1).getNome() + " no periodo: "+ poiUser.get(date).getNome());
            }
        }

        //Todos os PoI nao visitados/usados por um User num dado período Req: 5.b
        public void imprimirPOISNaoVisitadosPorUser(User user, Date d1, Date d2, String subrede){
        ST<Integer, POI> poiNaoVisitadosUser = user.naovisitadosporUserPOI(subrede, d1, d2);
        System.out.println("Tamanho: " + poiNaoVisitadosUser.size());
        for (int date : poiNaoVisitadosUser.keys()){
            System.out.println("POIs nao visitados por " + User.usersST.get(1).getNome() + " no periodo: " + poiNaoVisitadosUser.get(date).getNome());
        }
        }

        //Users que visitaram um POI em especifico Req: 5.c
        public void imprimirUserVisitaramPOI(POI poi, Date d1, Date d2){
            ST<Integer, User> userPOI = poi.usersVisitaram(d1, d2);
            System.out.println("Tamanho: " + userPOI.size());
            for (int userId : userPOI.keys()){
                System.out.println("Users que visitaram o POI " + POI.poiST.get(1).getNome() + ": " + userPOI.get(userId).getNome());
            }
        }
        //Todas os PoI que não foram visitados/usados por Users 5.d
        public void imprimirPOIsnaoVisitadosPorUsers(POI poi, Date d1, Date d2){
            RedBlackBST<Integer, POI> poisNaoVisitadosPorUsers = poi.poisNaoVisitadosPorUsers(d1, d2);
            System.out.println("Tamanho: " + poisNaoVisitadosPorUsers.size());
            for (int poiID : poisNaoVisitadosPorUsers.keys()){
                System.out.println("POIs nao visitados: " + poisNaoVisitadosPorUsers.get(poiID).getNome());
            }
        }

        //Top-5 dos Users que visitaram/usaram o maior número de PoI 5.e
        public void imprimirTop5UsersMaisPOIs(User user) {
            System.out.println("Top-5 dos Users que visitaram/usaram o maior número de PoI:");
            SeparateChainingHashST<Integer, User> userST = user.top5UsersMaisPOIs();
            for (int id : userST.keys()) {
                System.out.println(userST.get(id).getId() + userST.get(id).getNome());
            }
        }

        //Top-5 dos PoIs que foram visitados/usados 5.f
        public void imprimirTop5POIsMaisVisitados(User user){
            System.out.println("Top-5 dos PoIs que foram visitados/usados:");
            ST<Integer, POI> poiST = user.top5POIsMaisVisitados();
            for(int id : poiST.keys()){
                System.out.println(poiST.get(id).getId() + poiST.get(id).getNome());
            }
        }


        //Lista dos vértices e arestas onde uma etiqueta é definida Req: 5.g
        public void imprimirVerticesArestas() {
            RedBlackBST<Integer, POI> listaPOIS = POI.poiST;
            for (int id : listaPOIS.keys()) {
                if (POI.poiST.get(id) instanceof Restaurantes) {
                    System.out.println("ID:" + POI.poiST.get(id).getId() + " com o nome: " + POI.poiST.get(id).getNome());
                }
            }
        }


        public void imprimirLogsPOI(POI poi){
                RedBlackBST<Date, Log> historicoLogs = poi.getLogsHistorico();
                System.out.println("Historico de Logs:");
                for (Date date : historicoLogs.keys()) {
                    System.out.println("Log: " + historicoLogs.get(date).getDate().getDay() + "/"
                                               + historicoLogs.get(date).getDate().getMonth() + "/"
                                               +  historicoLogs.get(date).getDate().getYear()
                                               + " com a mensagem: " + historicoLogs.get(date).getMessage());
            }
        }


        public void now() {
            Date d1 = new Date(2022,1,14);
            Date d8 = new Date(2022,8,13);

            for(int id : POI.poiST.keys()) {
                System.out.println("POI: " + POI.poiST.get(id).getId()  + "\nNome: " + POI.poiST.get(id).getNome() + "\nTipo POI: " + POI.poiST.get(id).getTypePOI().getCategoria() + "\nLocalizacao: " + POI.poiST.get(id).getLocalizacao() + "\nTags: " + POI.poiST.get(id).getTags());
                ST<Integer, User> users= POI.poiST.get(id).usersVisitaram(d1, d8);
                if ( users.size() > 0) {
                    for (int userID : users.keys()) {
                        System.out.println("Nome: " + users.get(userID).getNome() );
                }
                    System.out.println("");
            }
        }

    }




    public void imprimirDijkstra(EdgeWeightedDigraphProject edge, int v1, int v2){
        DijkstraSPProject dijkstra = new DijkstraSPProject(edge, v1);
        for (DirectedEdgeProject dedge : dijkstra.pathTo(v2)) {
            System.out.println(dedge);
        }
        StdOut.printf("From: %d to %d | Distancia Total: %.2f \n", v1, v2, dijkstra.distTo(v2));
        }

    public void imprimirDijkstraTempo(EdgeWeightedDigraphProject edge, int v1, int v2, int tipo){
        DijkstraSPProject dijkstra = new DijkstraSPProject(edge, v1, tipo);
        for (DirectedEdgeProject dedge : dijkstra.pathTo(v2)) {
            System.out.println(dedge);
        }
        StdOut.printf("From: %d to %d | Tempo Total: %.2f do Tipo(%d) \n", v1, v2, dijkstra.distTo(v2), tipo);
    }

    public void imprirDijkstraAEvitar(EdgeWeightedDigraphProject edge, int v1, int v2, ArrayList<Integer> verticesAEvitar){
        DijkstraSPProject dijkstra = new DijkstraSPProject(edge, v1);
        if (dijkstra.hasPathTo(v2)){
            //StdOut.printf("From: %d to %d (%.2f) \n", v1, v2, dijkstra.distTo(v2));
            for (DirectedEdgeProject dedg : dijkstra.pathTo(v2)){
                if (verticesAEvitar.contains(dedg.from()) || verticesAEvitar.contains(dedg.to())){
                    System.out.println(" ");
                }
                System.out.println(dedg + "  ");
            }
        }
        else
            StdOut.printf("%d to %d  sem caminho\n", v1, v2);
    }

    public void imprimirSubGraph(EdgeWeightedDigraphProject edg, POI poi){
            ArrayList<Integer> list = new ArrayList<>();

        if(poi instanceof Restaurantes){
            for(int id : Restaurantes.restaurantesST){
                list.add(id);
            }
        }
        if(poi instanceof ChargerStation){
            for(int id : ChargerStation.chargerStationST){
                list.add(id);
            }
        }
        if(poi instanceof BocaDeIncendio){
            for(int id : BocaDeIncendio.bocaDeIncendioST){
                list.add(id);
            }
        }
        if(poi instanceof Semaforos){
            for(int id : Semaforos.semaforosST){
                list.add(id);
            }
        }
        if(poi instanceof Parque){
            for(int id : Parque.parqueST){
                list.add(id);
            }
        }

        EdgeWeightedDigraphProject edgsubGraph = edg.createSubGraph(edg, list);
        System.out.println(edgsubGraph);
    }







}




