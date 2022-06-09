package edu.ufp.inf.project;


import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdOut;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Projeto LP2 & AED2");
        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        Test test = new Test();
        //Ficheiros Nodes
        Node.lerNodeFicheiroTxt();
        Node.escreveNodeFicheiroTxt();
        //test.now();


        //Ficheiros Users
        User.readUsersFile();
        User.writeUsersFile();
        //user1.imprimirUsersST();
        //launch(args);

        //Ficheiro Ler Ways
        Ways.lerWaysFromTxt();

        launch(args);



        //Criar Datas
        Date d1 = new Date(2022,1,14);
        Date d2 = new Date(2022,2,18);
        Date d3 = new Date(2022,3,12);
        Date d4 = new Date(2022,4,6);
        Date d5 = new Date(2022,5,24);
        Date d6 = new Date(2022,6,10);
        Date d7 = new Date(2022,7,8);
        Date d8 = new Date(2022,8,13);
        Date d9 = new Date(2022,9,18);

/*
        //Criar Utilizadores
        User user1 = new User("Joao", Type.ADMIN);
        user1.inserirUserST();
        User user2 = new User("Carlos", Type.BASIC);
        user2.inserirUserST();
        User user3 = new User("Tobias", Type.BASIC);
        user3.inserirUserST();
        User user4 = new User("Tiago", Type.BASIC);
        user4.inserirUserST();
        User user5 = new User("Rui", Type.BASIC);
        user5.inserirUserST();
        User user6 = new User("Andre", Type.ADMIN);
        user6.inserirUserST();
        User user7 = new User("Ruben", Type.ADMIN);
        user7.inserirUserST();
        //user1.imprimirUsersST();

*/
        //Criar tipos de POIs
        TypePOI typePOI1 = new TypePOI(1, "Charger Station");
        TypePOI typePOI2 = new TypePOI(2, "Restaurante");
        TypePOI typePOI3 = new TypePOI(3, "Semaforo");
        TypePOI typePOI4 = new TypePOI(4, "Boca de Incendio");
        TypePOI typePOI5 = new TypePOI(5, "Parque");

        //Criar Localizacoes
        Location localizacao1 = new Location("subrede", 12.0, 13.4);
        Location localizacao2 = new Location("Porto", 115.37, 521.91);
        Location localizacao3 = new Location("subrede", 157.12, 189.78);
        Location localizacao4 = new Location("rede", 564.21, 193.32);
        Location localizacao5 = new Location("subrede", 142.23, 156.34);


        //Criar Logs
        Log log1 = new Log(d1, "Log Teste");
        Log log2 = new Log(d2, "Log Teste 2");

        Tag tag1 = new Tag("addr:city", "Vila do Conde");
        Tag tag2 = new Tag("addr:postcode", "4480");
        Tag tag3 = new Tag("addr:street", "Rua de cima");
        Tag tag4 = new Tag("semaforo", "sim");
        Tag tag5 = new Tag("restaurante:tipo", "vegan");
        Tag tag6 = new Tag("chargerstation:tipo", "veiculo");

/*
        //Criar Charger Stations
        ChargerStation chargerStation1 = new ChargerStation( "Charger Station Fatima", typePOI1, localizacao2, 1, "Charger Station", 1, "Automovel");
        chargerStation1.inserirChargerStationST();
        chargerStation1.inserirPOISsT();
        chargerStation1.addTagNode(tag1);
        chargerStation1.addTagNode(tag2);
        //chargerStation1.addTagNode(tag3);
        //chargerStation1.addTagNode(tag6);
        ChargerStation chargerStation2 = new ChargerStation( "Charger Station Alcacer do Sal", typePOI1, localizacao1, 1, "Charger Station", 2, "Trotinete");
        chargerStation2.inserirChargerStationST();
        chargerStation2.inserirPOISsT();
        //chargerStation1.imprimirChargerStationsST();
        //chargerStation2.imprimirTags();

        //Criar Restaurantes
        Restaurantes restaurante1 = new Restaurantes( "Salpicos Verdes", typePOI2, localizacao2, 2, "Restaurante", 3, "Salpicos Verdes", null, null);
        restaurante1.inserirRestauranteST();
        restaurante1.inserirPOISsT();
        Restaurantes restaurante2 = new Restaurantes( "MUU Steakhouse", typePOI2, localizacao1, 2, "Restaurante", 4, null, "MUU Steakhouse", null);
        restaurante2.inserirRestauranteST();
        restaurante2.inserirPOISsT();
        Restaurantes restaurante3 = new Restaurantes( "Alto-Mar", typePOI2, localizacao1, 2, "Restaurante", 5, null, null, "Alto-Mar");
        restaurante3.inserirRestauranteST();
        restaurante3.inserirPOISsT();
        //restaurante1.imprimirRestaurantesST();


        //Criar Semaforos
        Semaforos semaforo1 = new Semaforos( "Semaforo da Marginal", typePOI3, localizacao2, 3, "Semaforo", 6, "Ligado", null);
        semaforo1.inserirSemaforoST();
        semaforo1.inserirPOISsT();
        Semaforos semaforo2 = new Semaforos( "Semaforo da Rotunda", typePOI3, localizacao1, 3, "Semaforo", 7, null, "Ligado");
        semaforo2.inserirSemaforoST();
        semaforo2.inserirPOISsT();
        //semaforo1.imprimirSemaforoST();

        //Criar Bocas de Incendio
        BocaDeIncendio bocaDeIncendio1 = new BocaDeIncendio( "Boca de Incendio da Marginal", typePOI4, localizacao1, 4, "Boca de Incendio", 8, "Ativado", null);
        bocaDeIncendio1.inserirBocaDeIncendioST();
        bocaDeIncendio1.inserirPOISsT();
        BocaDeIncendio bocaDeIncendio2 = new BocaDeIncendio( "Boca de Incendio da Rotunda", typePOI4, localizacao1, 4, "Boca de Incendio", 9, null, "Ativado");
        bocaDeIncendio2.inserirBocaDeIncendioST();
        bocaDeIncendio2.inserirPOISsT();
        //bocaDeIncendio1.imprimirBocasDeIncendioST();

        Parque parque1 = new Parque("Parque Arca D Agua", typePOI5, localizacao1, 10, "Parque");
        parque1.inserirParqueST();
        parque1.inserirPOISsT();
        Parque parque2 = new Parque( "Parque XPTO", typePOI5, localizacao2, 11, "Parque");
        parque2.inserirParqueST();
        parque2.inserirPOISsT();


        //Adicionar POI visitado ao User
        user1.adicionarPOI(1, d3);
        //user1.adicionarPOI(2, d4);
        user1.adicionarPOI(3, d5);
        //user1.adicionarPOI(9, d6);
        //user1.adicionarPOI(10, d2);

        //user2.adicionarPOI(2, d3);
        user2.adicionarPOI(8, d2);
        user2.adicionarPOI(3, d6);
        user2.adicionarPOI(10, d3);

        user3.adicionarPOI(3, d7);
        user3.adicionarPOI(1, d2);

        user4.adicionarPOI(7, d4);
        //user4.adicionarPOI(3, d2);

        //user5.adicionarPOI(6, d6);
        user5.adicionarPOI(1, d2);
        user5.adicionarPOI(3, d4);
        //user5.adicionarPOI(2, d7);
        user5.adicionarPOI(5, d3);

        user6.adicionarPOI(8, d6);

        user7.adicionarPOI(3, d2);
        user7.adicionarPOI(8, d3);




        //Adicionar Log ao POI
        chargerStation1.inserirLog(log1);
        //restaurante1.inserirLog(log2);


*/
        //Imprmir Requisitos 5 e outros
        //Test test = new Test();
        //test.imprimirPOISNaoVisitadosPorUser(user2, d1, d8, "rede");
        //test.imprimirPOISvisitadosPorUser(user2, d1, d8, "rede");
        //test.imprimirUserVisitaramPOI(chargerStation1, d1, d8);
        //test.imprimirPOIsnaoVisitadosPorUsers(semaforo1, d2, d8);
        //test.imprimirVerticesArestas();
        //test.imprimirLogsPOI(restaurante1);
        //test.imprimirTop5POIsMaisVisitados(user1);
        //test.imprimirTop5UsersMaisPOIs(user1);




        //Testar Remover um POI
        /*
        chargerStation1.removerPOIsST();
        for(Date date : user1.poisVisitadasHistorico.keys()){
            System.out.println(user1.poisVisitadasHistorico.get(date).getNome());
        }
        chargerStation2.imprimirChargerStationsST();
        */

        //Editar um POI
        //chargerStation1.editarPOIsST("Charger Station Algarve", null, localizacao1, user1 );
        //chargerStation1.imprimirChargerStationsST();

        //Editar um User
        //user1.editarUser("Joaquim", Type.BASIC);
        //user1.imprimirUsersST();

        //Remover um User
        //user1.imprimirUsersST();
        //user3.removerUserST();
        //user1.imprimirUsersST();


/*
        //Teste dos IDs da PoisST:
        RedBlackBST<Integer, POI> aux = new RedBlackBST<>();
        aux = POI.poiST;
        for(int id : aux.keys()){
            System.out.println("ID:"+POI.poiST.get(id).getId()+"->"+POI.poiST.get(id).getNome());
        }*/


/*
        Ways ways1 = new Ways(0, 1,   100, 15);
        Ways ways2 = new Ways(1, 2,   100, 20);
        //Ways ways11 = new Ways(1, 3,   100, 20);
        Ways ways3 = new Ways(2,4,   200, 25);
        Ways ways4 = new Ways(3, 4,   100, 30);
        Ways ways5 = new Ways(4, 5,   100, 115);
        Ways ways6 = new Ways(5, 6,   100, 115);
        Ways ways7 = new Ways(6, 7,   100, 115);
        Ways ways8 = new Ways(7, 8,   100, 115);
        Ways ways9 = new Ways(8, 9,   100, 115);
        Ways ways10 = new Ways(9, 10,   100, 115);



        EdgeWeightedDigraphProject digraph = new EdgeWeightedDigraphProject(POI.poiST.size());

        digraph.addEdge(ways1);
        digraph.addEdge(ways2);

        digraph.addEdge(ways4);
        digraph.addEdge(ways5);
        digraph.addEdge(ways3);
        digraph.addEdge(ways6);
        digraph.addEdge(ways7);
        digraph.addEdge(ways8);
        digraph.addEdge(ways9);
        digraph.addEdge(ways10);
        //digraph.addEdge(ways11);
        System.out.println(digraph);
*/
        //Testar Dijkstra
        //test.imprimirDijkstra(digraph, 1, 4);
        //test.imprimirDijkstraTempo(digraph, 1, 4, 3);
        //test.imprirDijkstraAEvitar(digraph, 1,4,new ArrayList<>(Arrays.asList(3)));

        //Imprimir um subGraph
        //test.imprimirSubGraph(digraph, restaurante1);

        //Verificar se o Grafo e conexo
        //digraph.isConnected();


        //launch(args);



    }
}