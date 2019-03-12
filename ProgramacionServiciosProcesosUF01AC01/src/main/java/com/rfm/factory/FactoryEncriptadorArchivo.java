package com.rfm.factory;

import com.rfm.codigos.CifradoTxt;

public class FactoryEncriptadorArchivo {

  private FactoryEncriptadorArchivo() {
    throw new IllegalStateException("Utility class");
  }

  public static EncriptadorArchivo getInstance() {
    return new CifradoTxt();
  }

}
