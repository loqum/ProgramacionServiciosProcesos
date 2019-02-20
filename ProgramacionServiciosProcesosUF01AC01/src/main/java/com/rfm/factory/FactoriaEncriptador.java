package com.rfm.factory;

import com.rfm.codigos.CodigoCesar;
import com.rfm.codigos.CodigoVigenere;

public class FactoriaEncriptador {

  public static Encriptador getInstance(String algoritmo) {

    if (algoritmo.equals("cesar")) {
      return new CodigoCesar();
    } else if (algoritmo.equals("vigenere")) {
      return new CodigoVigenere();
    } else {
      throw new IllegalArgumentException("Introducido parámetro incorrecto.");
    }
  }

}
