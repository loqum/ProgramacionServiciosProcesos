package com.rfm.factory;

import com.rfm.codigos.CodigoCesar;
import com.rfm.codigos.CodigoCesarMod;
import com.rfm.codigos.CodigoVigenere;
import com.rfm.codigos.CodigoVigenereMod;

public class FactoryEncriptador {

  private FactoryEncriptador() {

  }

  public static Encriptador getInstance(String algoritmo) {

    switch (algoritmo) {
    case "1":
      return new CodigoCesar();

    case "2":
      return new CodigoCesarMod();

    case "3":
      return new CodigoVigenere();

    case "4":
      return new CodigoVigenereMod();

    default:
      throw new IllegalArgumentException("Introducido parámetro incorrecto.");
    }

  }

}
