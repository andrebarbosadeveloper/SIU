package edu.ufp.inf.project;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final double GROUP_MARGIN = 30;
    private static final double radius = 20;
    //Users
    public TableView usersTable;
    public TableColumn userIDCol;
    public TableColumn userNameCol;
    public TableColumn userTypeCol;
    public TextField userIDField;
    public TextField userNameField;
    public TextField userTypeField;
    public Button userCreateButton;
    public TextField userIDEditField;
    public TextField userNameEditField;
    public TextField userTypeEditField;
    public Button userEditButton;
    public Button userRemoverButton;
    public TextField userIDRemoverField;

    //POI
    public TableView systemPOITable;
    public TableColumn sysPOIIDCol;
    public TableColumn sysPOINameCol;
    public TableColumn sysPOITypeCol;
    public TableColumn sysPOILatitudeCol;
    public TableColumn sysPOILongitudeCol;
    public TableColumn sysPOIRedeCol;
    public TableColumn sysPOILocationCol;
    public TextField sysPOIredeField;
    public TextField sysPOILongitudeField;
    public TextField sysPOILatitudeField;
    public TextField sysPOITypeField;
    public TextField sysPOIField;
    public TextField sysPOIIDField;
    public Button createSystemPOIButton;
    public TextField sysPOIredeEditField;
    public TextField sysPOILongitudeEditField;
    public TextField sysPOILatitudeEditField;
    public TextField sysPOITypeEditField;
    public TextField sysPOIEditField;
    public TextField sysPOIIDEditField;
    public Button editSystemPOIButton;
    public Button removeSystemPOIButton;
    public TextField sysPOIIDRemoverField;

    //Logs
    public TableView LogsTable;
    public TableColumn dataLogCol;
    public TableColumn mensagemLogCol;
    public TextField dataLogField;
    public Button addLogButton;
    public TextField mensagemLogField;
    public TextField dataEditLogField;
    public Button editLogButton;
    public TextField mensagemEditLogField;

    //Filtrar
    public Button infoUserFilterButton;
    public TextArea filterResultArea;
    public ComboBox filterUserButton;
    public ComboBox filterTipoPOIButton;
    public MenuButton filterUserPesquisaButton;
    public MenuItem poisVisitadosUser;
    public MenuItem poisNaoVisitadosUser;
    public MenuItem poisVisitadosTop5User;
    public Button infoPOIFilterButton;
    public MenuButton filterPOIPesquisaButton;
    public MenuItem userVisitaramTop5POI;
    public MenuItem usersVisitaramPOI;

    //Edges
    public static EdgeWeightedDigraphProject edgeWeightedDigraph;
    public Button createEdgeButton;
    public Button createEdgeSGButton;
    public TextField edgeInicialCreate;
    public TextField edgeDestinoCreate;
    public TextField edgeDistanciaCreate;
    public TextField edgeTempoCreate;
    public Button editEdgeButton;
    public TextField edgeInicialEdit;
    public TextField edgeDestinoEdit;
    public TextField edgeDistanciaEdit;
    public TextField edgeTempoEdit;
    public TextField edgeInicialRemove;
    public TextField edgeDestinoRemove;
    public Button removeEdgeButton;
    public Pane graphGroupSearchPane;

    //Dijkstra
    public Button filterDijkstraSPButton;
    public Button filterDijkstraSPAvoidingButton;
    public TextField filterAvoidVerticesField;
    public Button filterDFSButton;
    public Pane DijkstraSPPane;


    //Ler e Escrever
    public Button readTextFileButton;
    public Button readBinFileButton;
    public Button writeToTextFileButton;
    public Button writeToBinFileButton;
    public Button exit;
    public Button desenharGraph;
    public Button infoUserTop5FilterButton;
    public Button infoUserVisitaramFilterButton;
    public Button infoUserNaoVisitaramFilterButton;
    public TextField userIDSearch;
    public TextField poiIDSearch;
    public Button infoPOITop5FilterButton;
    public Button infoPOIVisitaramFilterButton;
    public TextField filterPOIinicialDijkstra;
    public TextField filterPOIdestinoDijkstra;



    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<POI> pois = FXCollections.observableArrayList();
    ObservableList<Log> logs = FXCollections.observableArrayList();

    ArrayList<User> userArrayList = new ArrayList<>(users);
    ArrayList<POI> poiArrayList = new ArrayList<>(pois);

    private static final String PATH_BINUtilizador = ".//data//inputUtilizadores.bin";
    private static final String PATH_BINPOIs = ".//data//inputPOI.bin";
    private static final String PATH_BINGrafo = ".//data//grafo.bin";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        systemPOITable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        LogsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Users
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        userIDCol.setCellFactory(TextFieldTableCell.<User, Integer>forTableColumn(new IntegerStringConverter()));

        userNameCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        userNameCol.setCellFactory(TextFieldTableCell.<User>forTableColumn());

        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        //userTypeCol.setCellFactory(TextFieldTableCell.<User>forTableColumn());
        usersTable.setEditable(true);

        //userNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        //POI
        sysPOIIDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        //sysPOIIDCol.setCellFactory(TextFieldTableCell.<POI, Integer>forTableColumn(new IntegerStringConverter()));

        sysPOINameCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        sysPOINameCol.setCellFactory(TextFieldTableCell.<POI>forTableColumn());

        sysPOITypeCol.setCellValueFactory(new PropertyValueFactory<>("TypePOI"));
        //sysPOITypeCol.setCellFactory(TextFieldTableCell.<POI>forTableColumn());



        sysPOILocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        //sysPOILocationCol.setCellFactory(TextFieldTableCell.<POI>forTableColumn());
        //sysPOILatitudeCol.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        //sysPOILongitudeCol.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        //sysPOIRedeCol.setCellValueFactory(new PropertyValueFactory<>("subrede"));
        systemPOITable.setEditable(true);

        //Logs
        dataLogCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        mensagemLogCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        LogsTable.setEditable(true);

    }

    public void handleUserCreate(ActionEvent actionEvent) {
        User user = new User(userNameField.getText(), Type.fromString(userTypeField.getText()));
        user.inserirUserST();
        usersTable.getItems().add(user);
        users.add(user);
        userNameField.setText("");
        userTypeField.setText("");
        user.imprimirUsersST();
    }

    public void handleUserEdit(TableColumn.CellEditEvent<User, String> utilizadorStringCellEdit) {
        User user = (User) usersTable.getSelectionModel().getSelectedItem();

        user.setNome(utilizadorStringCellEdit.getNewValue());

        users.add(user);
        user.editarUser(utilizadorStringCellEdit.getNewValue(), null);
        //fileUtils.getUtilizadores().put(u.getId(), u);
    }

    public void handleUserRemove(ActionEvent actionEvent) {
        User user = User.usersST.get(Integer.parseInt(userIDRemoverField.getText()));
        user.removerUserST();
        usersTable.getItems().remove(user);
        users.remove(user);
        user.imprimirUsersST();

    }

    /**
     * Obtém informação acerca dos utilizadores
     * @return lista de utilizadores
     * @throws IOException
     */
    private void getUsersFromFile() throws IOException {
        for (Integer i : User.usersST.keys()) {
            users.add(User.usersST.get(i));
            System.out.println(User.usersST.get(i));
        }
        usersTable.getItems().addAll(users);
    }

    private void getPOIsFromFile() throws IOException {
        for (Integer i : POI.poiST.keys()) {
            pois.add(POI.poiST.get(i));
            System.out.println(POI.poiST.get(i));
        }
        systemPOITable.getItems().addAll(pois);
    }


    public void handleCreateSystemPOI(ActionEvent actionEvent) {

        TypePOI typePOI;

        Location location = new Location(sysPOIredeField.getText(), Double.parseDouble(sysPOILatitudeField.getText()), Double.parseDouble(sysPOILongitudeField.getText()));

        if(sysPOITypeField.getText().equals("BocaDeIncendio")) {

            typePOI = new TypePOI(1, "BocaDeIncendio");

            BocaDeIncendio bi = new BocaDeIncendio(sysPOIField.getText(), typePOI,location);

            bi.inserirPOISsT();
            bi.inserirBocaDeIncendioST();
            systemPOITable.getItems().add(bi);
            pois.add(bi);
            sysPOIField.setText("");
            sysPOITypeField.setText("");
            sysPOILatitudeField.setText("");
            sysPOILongitudeField.setText("");
            sysPOIredeField.setText("");

        }
        else if(sysPOITypeField.getText().equals("Restaurantes")) {

            typePOI = new TypePOI(2, "Restaurantes");

            Restaurantes res = new Restaurantes(sysPOIField.getText(), typePOI,location);

            res.inserirPOISsT();
            res.inserirRestauranteST();
            systemPOITable.getItems().add(res);
            pois.add(res);
            sysPOIField.setText("");
            sysPOITypeField.setText("");
            sysPOILatitudeField.setText("");
            sysPOILongitudeField.setText("");
            sysPOIredeField.setText("");

        }
        else if(sysPOITypeField.getText().equals("ChargerStation")) {

            typePOI = new TypePOI(3, "ChargerStation");

            ChargerStation cs = new ChargerStation(sysPOIField.getText(), typePOI,location);

            cs.inserirPOISsT();
            cs.inserirChargerStationST();
            systemPOITable.getItems().add(cs);
            pois.add(cs);
            sysPOIField.setText("");
            sysPOITypeField.setText("");
            sysPOILatitudeField.setText("");
            sysPOILongitudeField.setText("");
            sysPOIredeField.setText("");
        }
        else if(sysPOITypeField.getText().equals("Semaforos")) {

            typePOI = new TypePOI(4, "Semaforos");

            Semaforos s = new Semaforos(sysPOIField.getText(), typePOI,location);

            s.inserirPOISsT();
            s.inserirSemaforoST();
            systemPOITable.getItems().add(s);
            pois.add(s);
            sysPOIField.setText("");
            sysPOITypeField.setText("");
            sysPOILatitudeField.setText("");
            sysPOILongitudeField.setText("");
            sysPOIredeField.setText("");
        }
        else if(sysPOITypeField.getText().equals("Parque")) {

            typePOI = new TypePOI(5, "Parque");

            Parque p = new Parque(sysPOIField.getText(), typePOI,location);

            p.inserirPOISsT();
            p.inserirParqueST();
            systemPOITable.getItems().add(p);
            pois.add(p);
            sysPOIField.setText("");
            sysPOITypeField.setText("");
            sysPOILatitudeField.setText("");
            sysPOILongitudeField.setText("");
            sysPOIredeField.setText("");
        }
    }

    public void handleEditSystemPOI(TableColumn.CellEditEvent<POI, String> poiStringCellEdit) {
        POI poi = (POI) systemPOITable.getSelectionModel().getSelectedItem();

        poi.setNome(poiStringCellEdit.getNewValue());

        pois.add(poi);
        poi.editarPOIsSTSemUser(poiStringCellEdit.getNewValue(), null, null);

    }

    public void handleRemoveSystemPOI(ActionEvent actionEvent) {
        POI poi = POI.poiST.get(Integer.parseInt(sysPOIIDRemoverField.getText()));
        poi.removerPOIsST();
        systemPOITable.getItems().remove(poi);
        pois.remove(poi);
    }

    public void handleAddLog(ActionEvent actionEvent) {
        String[] data = dataLogField.getText().split("/");
        Date d = new Date(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        Log log = new Log(d,mensagemLogField.getText());

        LogsTable.getItems().add(log);
        logs.add(log);
        dataLogField.setText("");
        mensagemLogField.setText("");
    }


    public void handleInfoUserTop5Filter(ActionEvent actionEvent) {
        SeparateChainingHashST<Integer, User> usersTop5POIs = User.top5UsersMaisPOIs();
        StringBuilder stringBuilder = new StringBuilder();

        for (int iduser : usersTop5POIs.keys()){
            stringBuilder.append(usersTop5POIs.get(iduser).getNome());
            stringBuilder.append("\t");
        }
        filterResultArea.setText(stringBuilder.toString());
    }

    public void handleInfoUserVisitaramFilter(ActionEvent actionEvent) {

        filterResultArea.clear();
        int id = Integer.parseInt(userIDSearch.getText());
        User user = User.usersST.get(id);
        Date d1 = new Date(2022,1,14);
        Date d8 = new Date(2022,8,13);
        StringBuilder string = new StringBuilder();
        ST<Integer,POI> poisST = user.poisVisitadosPorPeriodo(d1, d8);
        for(int i : poisST.keys()){
            string.append(poisST.get(i).getNome());
            string.append("\t");
        }
        filterResultArea.setText(string.toString());
    }

    public void handleInfoUsernaoVisitaramFilter(ActionEvent actionEvent) {

        filterResultArea.clear();
        int id = Integer.parseInt(userIDSearch.getText());
        User user = User.usersST.get(id);
        Date d1 = new Date(2022,1,14);
        Date d8 = new Date(2022,8,13);
        StringBuilder string = new StringBuilder();
        ST<Integer,POI> poisST = user.naovisitadosporUserPOI("rede",d1, d8);
        for(int i : poisST.keys()){
            string.append(poisST.get(i).getNome());
            string.append("\t");
        }
        filterResultArea.setText(string.toString());
    }

    public void handleInfoPOITop5Filter(ActionEvent actionEvent) {
        ST<Integer, POI> poiTop5Users = User.top5POIsMaisVisitados();
        StringBuilder stringBuilder = new StringBuilder();

        for (int idPoi : poiTop5Users.keys()){
            stringBuilder.append(poiTop5Users.get(idPoi).getNome());
            stringBuilder.append("\t");
        }
        filterResultArea.setText(stringBuilder.toString());
    }

    public void handleInfoPOIVisitaramFilter(ActionEvent actionEvent) {

        filterResultArea.clear();
        int id = Integer.parseInt(poiIDSearch.getText());
        POI poi = POI.poiST.get(id);
        Date d1 = new Date(2022,1,14);
        Date d8 = new Date(2022,8,13);
        StringBuilder string = new StringBuilder();
        ST<Integer,User> userST = poi.usersVisitaram(d1, d8);
        for(int i : userST.keys()){
            string.append(userST.get(i).getNome());
            string.append("\t");
        }
        filterResultArea.setText(string.toString());

    }

    public void handleEdgesCreate(ActionEvent actionEvent) {
        edgeWeightedDigraph =  new EdgeWeightedDigraphProject(POI.poiST.size());
        edgeWeightedDigraph.addEdge(new Ways(Integer.parseInt(edgeInicialCreate.getText()), Integer.parseInt(edgeDestinoCreate.getText()), Integer.parseInt(edgeDistanciaCreate.getText()), Integer.parseInt(edgeTempoCreate.getText())));

        for (DirectedEdgeProject dedg : edgeWeightedDigraph.edges()) {
            int from = dedg.from();
            int to = dedg.to();
            StackPane spFrom = (StackPane) graphGroupSearchPane.getChildren().get(from);
            Circle cFrom = (Circle) spFrom.getChildren().get(0);
            StackPane spTo = (StackPane) graphGroupSearchPane.getChildren().get(to);
            Circle cTo = (Circle) spTo.getChildren().get(0);
            Line line = new Line(cFrom.getCenterX(), cFrom.getCenterY(), cTo.getCenterX(), cTo.getCenterY());
            graphGroupSearchPane.getChildren().add(line);
        }


        edgeInicialCreate.setText("");
        edgeDestinoCreate.setText("");
        edgeDistanciaCreate.setText("");
        edgeTempoCreate.setText("");

    }


    public void handleEdgesEdit(ActionEvent actionEvent) {
        for (DirectedEdgeProject directedEdgeProject : edgeWeightedDigraph.edges()) {
            if (directedEdgeProject.from() == Integer.parseInt(edgeInicialEdit.getText()) && directedEdgeProject.to() == Integer.parseInt(edgeDestinoEdit.getText())) {
                edgeWeightedDigraph.removeEdge(new DirectedEdgeProject(Integer.parseInt(edgeInicialEdit.getText()), Integer.parseInt(edgeDestinoEdit.getText()), 0, 0));
                edgeWeightedDigraph.addEdge(new DirectedEdgeProject(Integer.parseInt(edgeInicialEdit.getText()), Integer.parseInt(edgeDestinoEdit.getText()), Integer.parseInt(edgeDistanciaEdit.getText()), Integer.parseInt(edgeTempoEdit.getText())));
            }
        }
    }

    public void handleEdgesRemove(ActionEvent actionEvent) {
        edgeWeightedDigraph.removeEdge(new DirectedEdgeProject(Integer.parseInt(edgeInicialRemove.getText()), Integer.parseInt(edgeDestinoRemove.getText()), 0, 0));

        handleDesenharGraph(actionEvent);

        edgeInicialEdit.setText("");
        edgeDestinoEdit.setText("");

    }
    public void handleDijkstraSP(ActionEvent actionEvent) {

        int from=Integer.parseInt(filterPOIinicialDijkstra.getText())-1;
        int to = Integer.parseInt(filterPOIdestinoDijkstra.getText())-1;

        DijkstraSPProject dijkstraSPProject = new DijkstraSPProject(this.edgeWeightedDigraph, from);

        if(dijkstraSPProject.pathTo(to)!=null) {
            for (DirectedEdgeProject directedEdgeProject : dijkstraSPProject.pathTo(to)) {
                System.out.println(directedEdgeProject);
                StackPane stackPaneInicial = (StackPane) graphGroupSearchPane.getChildren().get(directedEdgeProject.from());
                Circle circleInicial = (Circle) stackPaneInicial.getChildren().get(0);
                StackPane stackPaneDestino = (StackPane) graphGroupSearchPane.getChildren().get(directedEdgeProject.to());
                Circle circleDestino = (Circle) stackPaneDestino.getChildren().get(0);
                Line line = new Line(circleInicial.getCenterX(), circleInicial.getCenterY(), circleDestino.getCenterX(), circleDestino.getCenterY());
                line.setStroke(Color.LIMEGREEN);
                line.setStrokeWidth(2);
                graphGroupSearchPane.getChildren().add(line);
            }
            System.out.println("Distancia Total: " + dijkstraSPProject.distTo(to));
        }
        else
            System.out.println("Caminho inexistente!");
    }



    public void handleReadTextFile(ActionEvent actionEvent) throws IOException {
        getUsersFromFile();
        getPOIsFromFile();
        this.edgeWeightedDigraph = new EdgeWeightedDigraphProject(POI.poiST.size());    //Para atualizar o tamanho para conter os POIs
    }

    public void handleReadBinFile(ActionEvent actionEvent) {

        usersTable.getItems().clear();
        userArrayList.clear();
        readUsersFromBinFile();

        systemPOITable.getItems().clear();
        poiArrayList.clear();
        readPOIsFromBinFile();

        this.edgeWeightedDigraph = readGrafoFromBinFile();


        usersTable.getItems().addAll(users);
        systemPOITable.getItems().addAll(pois);


        System.out.println("Leu do Ficheiro Binário");
    }

    private void readUsersFromBinFile(){
        File f = new File(PATH_BINUtilizador);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userArrayList = (ArrayList<User>) ois.readObject();
            usersTable.getItems().addAll(userArrayList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readPOIsFromBinFile(){
        File f = new File(PATH_BINPOIs);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            poiArrayList = (ArrayList<POI>) ois.readObject();
            systemPOITable.getItems().addAll(poiArrayList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static EdgeWeightedDigraphProject readGrafoFromBinFile(){
        EdgeWeightedDigraphProject edgeWeightedDigraphProject = null;
        File f = new File(PATH_BINGrafo);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            edgeWeightedDigraphProject = (EdgeWeightedDigraphProject) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return edgeWeightedDigraphProject;
    }



    public void handleWriteToTextFile(ActionEvent actionEvent) {
        Node.escreveNodeFicheiroTxt();
        User.writeUsersFile();
        POI.escreveNodeFicheiroTxt();
    }



    public void handleWriteToBinFile(ActionEvent actionEvent){
        saveUsersToBinFile();
        savePOIsToBinFile();
        saveGrafoBinFile(edgeWeightedDigraph);
    }

    private void saveUsersToBinFile(){
        ArrayList<User> userArrayList = new ArrayList<>(users);

        File f = new File(PATH_BINUtilizador);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePOIsToBinFile(){
        ArrayList<POI> poiArrayList = new ArrayList<>(pois);

        File f = new File(PATH_BINPOIs);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(poiArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveGrafoBinFile(EdgeWeightedDigraphProject edgeWeightedDigraphProject){
        File f = new File(PATH_BINGrafo);

        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(edgeWeightedDigraphProject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleDesenharGraph(ActionEvent actionEvent) {
        graphGroupSearchPane.getChildren().clear();
        for(int id: POI.poiST.keys()){
            System.out.println(POI.poiST.get(id).getClass());

            double x = GROUP_MARGIN + ((POI.poiST.get(id).getLocalizacao().getLatitude()) -(int)(POI.poiST.get(id).getLocalizacao().getLatitude())) * (800 - GROUP_MARGIN * 2);
            double y = GROUP_MARGIN + (((POI.poiST.get(id).getLocalizacao().getLongitude()) -(int)(POI.poiST.get(id).getLocalizacao().getLongitude())) * (400 - GROUP_MARGIN * 2)*(-1));

            System.out.println(x +""+ y);
            Circle circle = new Circle(x,y,radius);


            if (POI.poiST.get(id) instanceof Restaurantes) {
                circle.setFill(Color.YELLOW);
            } else if (POI.poiST.get(id) instanceof BocaDeIncendio) {
                circle.setFill(Color.RED);
            } else if (POI.poiST.get(id) instanceof Semaforos) {
                circle.setFill(Color.GREY);
            } else if (POI.poiST.get(id) instanceof ChargerStation) {
                circle.setFill(Color.BLUE);
            } else if( POI.poiST.get(id) instanceof Parque){
                circle.setFill(Color.GREEN);
            }
            circle.setId(""+id);

            Text text = new Text(""+id);

            StackPane stackPane = new StackPane();

            stackPane.setLayoutX(x-radius);
            stackPane.setLayoutY(y-radius);
            stackPane.getChildren().addAll(circle,text);
            graphGroupSearchPane.getChildren().add(stackPane);

        }

        //Desenhar as Ways(linhas)
        for(int i : Ways.waysST.keys()){
            this.edgeWeightedDigraph.addEdge(Ways.waysST.get(i));
        }

        for (DirectedEdgeProject directedEdgeProject : edgeWeightedDigraph.edges()) {

            int from = directedEdgeProject.from();
            int to = directedEdgeProject.to();

            StackPane spFrom = (StackPane) graphGroupSearchPane.getChildren().get(from);
            Circle cFrom = (Circle) spFrom.getChildren().get(0);
            StackPane spTo = (StackPane) graphGroupSearchPane.getChildren().get(to);
            Circle cTo = (Circle) spTo.getChildren().get(0);
            Line line = new Line(cFrom.getCenterX(), cFrom.getCenterY(), cTo.getCenterX(), cTo.getCenterY());
            graphGroupSearchPane.getChildren().add(line);
        }


    }


}
