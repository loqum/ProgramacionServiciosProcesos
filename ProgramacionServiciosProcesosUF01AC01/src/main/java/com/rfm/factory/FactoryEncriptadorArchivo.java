package com.rfm.factory;

import com.rfm.codigos.CifradoTxt;

public class FactoryEncriptadorArchivo {

  public static EncriptadorArchivo getInstance() {
    return new CifradoTxt();
  }

}
