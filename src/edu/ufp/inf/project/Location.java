package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.security.SecureRandom;

public class Location implements Serializable {

  private String subrede;
  private double latitude;
  private double longitude;

  public static ST<String, Integer> subredeST = new ST<>();

  public Location(String subrede, double latitude, double longitude) {
    this.subrede = subrede;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getSubrede() {
    return subrede;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    return "Location{" +
            "subrede='" + subrede + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
  }
}