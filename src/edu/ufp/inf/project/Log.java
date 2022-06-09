package edu.ufp.inf.project;

import edu.princeton.cs.algs4.In;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.util.ArrayList;

public class Log implements Serializable {

  public Date date;
  public String message;
  public static ArrayList<Log> logsList = new ArrayList<>();

  public Log(Date date, String message) {
    this.date = date;
    this.message = message;
  }

  public Date getDate() {
    return date;
  }

  public String getMessage() {
    return message;
  }


  @Override
  public String toString() {
    return "Log{" +
            "date=" + date +
            ", message='" + message + '\'' +
            '}';
  }
}