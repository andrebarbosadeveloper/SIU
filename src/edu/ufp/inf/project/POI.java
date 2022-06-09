package edu.ufp.inf.project;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import java.io.Serializable;

public class POI extends Node implements Serializable {

  private int id;
  private int countPOI;
  private User user;
  private String nome;
  private TypePOI typePOI;
  private Location location;

  public RedBlackBST<Date, Log> logsHistorico = new RedBlackBST<>();
  public static RedBlackBST<Integer, POI> poiST = new RedBlackBST<>();


  public POI(String nome, TypePOI typePOI, Location localizacao) {
    this.id = idUnico();
    this.nome = nome;
    this.typePOI = typePOI;
    this.location = localizacao;
    if(Location.subredeST.contains(localizacao.getSubrede()))
      Location.subredeST.put(localizacao.getSubrede(), Location.subredeST.get(localizacao.getSubrede())+1);
    else
      Location.subredeST.put(localizacao.getSubrede(), 1);
  }

  public POI() {}

  /**
   * Retorna o último ID disponível
   * @return id
   */
  private int idUnico(){
    int id = 1;
    while(poiST.contains(id)){
      id++;
    }
    return id;
  }

  /**
   * Todos os Logs existentes no POI
   * @return logsHistorico
   */
  public RedBlackBST<Date, Log> getLogsHistorico() {
    return logsHistorico;
  }

  /**
   * Adicionar Log ao POI
   * @param log
   */
  public void inserirLog(Log log){
    this.logsHistorico.put(log.getDate(), log);
    Log.logsList.add(log);
  }


  /**
   * Todos os User que já visitaram/usaram um determinado PoI
   * 5.c
   * @param d1
   * @param d2
   * @return users
   */
  public ST<Integer, User> usersVisitaram(Date d1, Date d2) {
    ST<Integer, User> users = new ST<>();
    for(int id : User.usersST.keys()){
      for(Date data : User.usersST.get(id).poisVisitadasHistorico.keys()){
        if(User.usersST.get(id).poisVisitadasHistorico.get(data).getId() == this.id && data.afterDate(d1) && data.beforeDate(d2))
          users.put(id, User.usersST.get(id));
      }
    }
    return users;
  }

  /**
   * Todos os User que já visitaram/usaram um determinado PoI sem data (Para usarmos no countPOI)
   * @return users
   */
  public ST<Integer, User> usersVisitaramGlobal() {
    ST<Integer, User> users = new ST<>();
    for(int id : User.usersST.keys()){
      for(Date data : User.usersST.get(id).poisVisitadasHistorico.keys()){
        if(User.usersST.get(id).poisVisitadasHistorico.get(data).getId() == this.id)
          users.put(id, User.usersST.get(id));
      }
    }
    return users;
  }


  /**
   * Todas os PoI que não foram visitados/usados por Users
   * 5.d
   * @param d1
   * @param d2
   * @return
   */
  public RedBlackBST<Integer, POI>poisNaoVisitadosPorUsers(Date d1,Date d2) {
    RedBlackBST<Integer, POI> new_poisST = poiST;
    for (int idUser : User.usersST.keys()) {
      for (int idPoi : poiST.keys()) {
        if (!User.usersST.get(idUser).naovisitadosporUserPOI("rede", d1, d2).contains(poiST.get(idPoi).id)) {
          new_poisST.delete(poiST.get(idPoi).getId());
        }
      }
    }
    return new_poisST;
  }


  /**
   * Verifica se o POI já existe, caso não exista insere o POI na poiST
   */
  public void inserirPOISsT() {
    if (poiST.contains(id)) {
      System.out.println("Este POI já existe!!!");
      return;
    }
    else if(id <= 0){
      id = idUnico();
    }
    poiST.put(id, this);
  }

  /**
   * Editar POI
   * @param nome
   * @param typePOI
   * @param location
   * @param user
   */
  public void editarPOIsST(String nome,TypePOI typePOI, Location location, User user) {
    if (user.getTipo().equals(Type.ADMIN)){
      if (nome != null)
        this.nome = nome;
      if (typePOI != null)
        this.typePOI = typePOI;
      if (location != null)
        this.location = location;

      poiST.put(id, this);
      System.out.println("POI alterado com sucesso.");
    } else
      System.out.println("User sem permissao para alterar este POI.");
  }

  //Para JavaFX
  public void editarPOIsSTSemUser(String nome,TypePOI typePOI, Location location) {
      if (nome != null)
        this.nome = nome;
      if (typePOI != null)
        this.typePOI = typePOI;
      if (location != null)
        this.location = location;
      poiST.put(id, this);
      System.out.println("POI alterado com sucesso.");

  }

  /**
   * Remover POIs da ST e consequentemente das estruturas
   */
  public void removerPOIsST() {
    if (poiST.contains(this.id)){
      poiST.delete(this.id);

      for (int userID : User.usersST.keys()) {
        for (Date date : User.usersST.get(userID).poisVisitadasHistorico.keys()) {
          if (User.usersST.get(userID).poisVisitadasHistorico.get(date).getId() == this.id)
            User.usersST.get(userID).poisVisitadasHistorico.delete(date);
        }
        for (Date date : User.usersST.get(userID).poisNaoVisitadasHistorico.keys()) {
          if (User.usersST.get(userID).poisNaoVisitadasHistorico.get(date).getId() == this.id)
            User.usersST.get(userID).poisNaoVisitadasHistorico.delete(date);
        }
      }
      if (this instanceof ChargerStation){
        ChargerStation.chargerStationST.delete(this.id);
        System.out.println("Charger Station removida com sucesso");
      }
      else if (this instanceof Restaurantes){
        Restaurantes.restaurantesST.delete(this.id);
        System.out.println("Restaurante removido com sucesso");
      }
      else if (this instanceof Semaforos){
        Semaforos.semaforosST.delete(this.id);
        System.out.println("Semaforo removido com sucesso");
      }
      else if (this instanceof BocaDeIncendio){
        BocaDeIncendio.bocaDeIncendioST.delete(this.id);
        System.out.println("Boca de Incendio removida com sucesso");
      }
      else if (this instanceof Parque){
        Parque.parqueST.delete(this.id);
        System.out.println("Parque removido com sucesso");
      }

      System.out.println("POI removido com sucesso");
    }
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCountPOI() {
    return countPOI;
  }

  public void setCountPOI() {
    this.countPOI ++ ;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public TypePOI getTypePOI() {
    return typePOI;
  }

  public void setTypePOI(TypePOI typePOI) {
    this.typePOI = typePOI;
  }

  public Location getLocalizacao() {
    return location;
  }

  public void setLocalizacao(Location localizacao) {
    this.location = localizacao;
  }

  @Override
  public String toString() {
    return "POI{" +
            "id=" + id +
            ", user=" + user +
            ", nome='" + nome + '\'' +
            ", typePOI=" + typePOI +
            ", location=" + location +
            '}';
  }
}