package edu.ufp.inf.project;

import java.io.Serializable;

public enum Type implements Serializable {

  BASIC("basic"), ADMIN("admin");

  public String typeString;

  Type(String type){
    typeString = type;
  }

  @Override
  public String toString() {
    return typeString;
  }

  public static Type fromString(String typeString) {
    for (Type type : Type.values()) {
      if (type.typeString.equalsIgnoreCase(typeString)) {
        return type;
      }
    }
    return null;
  }
}