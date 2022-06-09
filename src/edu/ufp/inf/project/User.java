package edu.ufp.inf.project;

import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class User implements Serializable {

  private int id;
  private int countVisitados;
  private String nome;
  private Type tipo;

  public RedBlackBST<Date, POI> poisNaoVisitadasHistorico = new RedBlackBST<>();
  public RedBlackBST<Date, POI> poisVisitadasHistorico = new RedBlackBST<>();
  public static SeparateChainingHashST<Integer, User> usersST = new SeparateChainingHashST<>();


  public User(String nome, Type tipo) {
    this.nome = nome;
    this.tipo = tipo;
    this.countVisitados = poisVisitadasHistorico.size();
  }
  public User(int id, String nome, Type tipo) {
    this.id = idUnico();
    this.nome = nome;
    this.tipo = tipo;
    this.countVisitados = poisVisitadasHistorico.size();
  }

  /**
   * POIS visitados por um User num dado período(globalmente a toda a rede ou por tipo de subrede)
   * 5.a
   * @param subrede
   * @param d1
   * @param d2
   * @return poisRetornados
   */
  public ST<Integer,POI> visitadosporUserPOI(String subrede, Date d1, Date d2) {
    ST<Integer, POI> pois = poisVisitadosPorPeriodo(d1,d2);
    ST<Integer, POI> poisRetornados = new ST<>();
    for(int id : pois.keys()){
      if(pois.get(id).getLocalizacao().getSubrede().equals(subrede) || subrede.equals("rede")){
        poisRetornados.put(pois.get(id).getId(), pois.get(id));
      }
    }
    return poisRetornados;
  }

  /**
   * Metodo auxiliar para comparar as datas a serem chamadas na visitadosporUserPOI
   * @param d1
   * @param d2
   * @return pois
   */
  public ST<Integer,POI> poisVisitadosPorPeriodo(Date d1, Date d2){
    ST<Integer, POI> pois = new ST<>();
    for(Date date : poisVisitadasHistorico.keys()){
      if(date.afterDate(d1) && date.beforeDate(d2)){
        pois.put(poisVisitadasHistorico.get(date).getId(),poisVisitadasHistorico.get(date));
      }
    }
    return pois;
  }

  /**
   * POIS não visitados por um User num dado período(globalmente a toda a rede ou por tipo de subrede)
   * 5.b
   * @param subrede
   * @param d1
   * @param d2
   * @return pois
   */
  public ST<Integer,POI> naovisitadosporUserPOI(String subrede, Date d1, Date d2) {
    ST<Integer, POI> pois = new ST<>();
    for(int id : POI.poiST.keys()){
      if(!visitadosporUserPOI(subrede, d1, d2).contains(id)
              && (POI.poiST.get(id).getLocalizacao().getSubrede().equals(subrede) || subrede.equals("rede"))){
        pois.put(id, POI.poiST.get(id));
      }
    }
    return pois;
  }

  /**
   * Adicionar uma POI visitada ao User através do ID
   * @param id - POI
   * @param d1
   */
  public void adicionarPOI(int id, Date d1) {
    if(!POI.poiST.contains(id))
      System.out.println("Nao foi encontrada nenhum POI com esse ID!!");
    else {
      for (Date data : poisVisitadasHistorico.keys()) {
        if (poisVisitadasHistorico.get(data).getId() == id) {
          System.out.println("Este User ja visitou este POI!!");
          return;
        }
      }
      countVisitados ++;
      POI.poiST.get(id).setCountPOI();
      poisVisitadasHistorico.put(d1, POI.poiST.get(id));
    }
  }



  /**
   * Retorna o último ID disponível
   * @return id
   */
  private int idUnico(){
    int id = 1;
    while(usersST.contains(id)){
      id++;
    }
    return id;
  }

  /**
   * Verifica que se já existe um User com o mesmo ID, caso exista retorna, caso não exista chama a idUnico
   * para verificar o ultimo Id disponivel e coloca o ID na usersST
   */
  public void inserirUserST() {
    if (usersST.contains(id)) {
      System.out.println("USER COM ID:" + id + "JÁ EXISTE!!!");
      return;
    }
    if(id <= 0){
      id = idUnico();
    }
    usersST.put(id, this);
  }

  /**
   * Metodo para editar o User
   * @param nome
   * @param tipo
   */
  public void editarUser(String nome, Type tipo) {
    if(tipo != null)
      this.tipo = tipo;
    if(nome != null)
      this.nome = nome;
    usersST.put(id, this);
  }

  /**
   * Metodo para remover o User da ST
   */
  public void removerUserST() {
      if (usersST.contains(this.id))
        usersST.delete(this.id);
  }

  /**
   * Top-5 dos PoIs que foram visitados/usados
   * 5.f
   * @return returnPOIS
   */
  public static ST<Integer, POI> top5POIsMaisVisitados() {
    Integer[] poisArray = new Integer[POI.poiST.size()]; //Array de POIs
    int k = 0;
    for (int id : POI.poiST.keys()) {
      poisArray[k] = POI.poiST.get(id).getCountPOI(); //Tamanho do array de POIs Visitados
      k++;
    }

    Arrays.sort(poisArray, Collections.reverseOrder()); //Ordenar o array

    ST<Integer, POI> poisRetornados = new ST<>();

    int j = 0;
    for (int id : POI.poiST.keys()) {
      if (POI.poiST.get(id).getCountPOI() >= poisArray[4] && j < 5) {
        poisRetornados.put(POI.poiST.get(id).getId(), POI.poiST.get(id)); //Insere na ST de retornados os POIs
        j++;
      }
    }
    return poisRetornados;
  }

  /**
   * Top-5 dos Users que visitaram/usaram o maior número de PoI
   * 5.e
   * @return returnUsers
   */
  public static SeparateChainingHashST<Integer, User> top5UsersMaisPOIs() {
    Integer[] usersArray = new Integer[usersST.size()]; //Array de Users

    int k = 0;
    for (int id : usersST.keys()) {
      usersArray[k] = usersST.get(id).countVisitados; //Tamanho do array de User que visitaram
      k++;
    }

    Arrays.sort(usersArray, Collections.reverseOrder()); //Ordenar o array

    SeparateChainingHashST<Integer, User> usersRetornados = new SeparateChainingHashST<>();

    int j = 0;
    for (int id : usersST.keys()) {
      if (usersST.get(id).countVisitados >= usersArray[4] && j < 5) {
        usersRetornados.put(usersST.get(id).getId(), usersST.get(id)); //Insere na ST de retornados os Users
        j++;
      }
    }
    return usersRetornados;
  }



  /**
   * Imprime Todos os Users presentes na ST de Users
   */
  public void imprimirUsersST() {
    System.out.println("\nUsers:");
    for (int i : usersST.keys()) {
      System.out.println("ID: " + usersST.get(i).id);
      System.out.println("Nome: " + usersST.get(i).nome);
      System.out.println("Tipo: " + usersST.get(i).tipo);
    }
  }

  public static void readUsersFile(){
    String line;

    In in = new In(".//data//inputUsers.txt");

    if(!in.exists()){
      System.out.println("Ficheiro Invalido");
    }else {
        while (in.hasNextLine()){
          line = in.readLine();
          int numeroUsers = Integer.parseInt(line);
          for (int i = 0; i < numeroUsers; i++){
            line = in.readLine();
            String[] userInput = line.split(",", 3);
            User user = new User(Integer.parseInt(userInput[0]),userInput[1],Type.fromString(userInput[2]));
            user.inserirUserST();
          }
        }
    }
  }

  public static void writeUsersFile(){
    Out out = new Out(".//data//outPutUser.txt");
    out.println(User.usersST.size());
    for(int userID : User.usersST.keys()){
      out.println(userID + "," + User.usersST.get(userID).getNome() + "," + User.usersST.get(userID).getTipo().toString());
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Type getTipo() {
    return tipo;
  }

  public void setTipo(Type tipo) {
    this.tipo = tipo;
  }

  public int getCountVisitados() {
    return countVisitados;
  }

  public void setCountVisitados(int countVisitados) {
    this.countVisitados = countVisitados;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", tipo='" + tipo + '\'' +
            '}';
  }
}