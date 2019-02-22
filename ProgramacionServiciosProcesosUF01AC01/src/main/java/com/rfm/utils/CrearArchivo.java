package com.rfm.utils;

import java.io.File;

public class CrearArchivo {

  private CrearArchivo() {
    throw new IllegalStateException("Utility class");
  }

  public static File crear(String nombreArchivo) {
    return new File(nombreArchivo);
  }

}
