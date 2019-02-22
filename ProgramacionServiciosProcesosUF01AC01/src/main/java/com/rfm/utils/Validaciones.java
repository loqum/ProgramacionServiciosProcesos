package com.rfm.utils;

import org.apache.log4j.Logger;

public class Validaciones {

  private static final Logger LOG = Logger.getLogger(Validaciones.class);

  public static boolean isNumeric(String cadena) {
    boolean resultado;

    try {
      Integer.parseInt(cadena);
      resultado = true;
    } catch (NumberFormatException e) {
      resultado = false;
      LOG.error("No has introducido un numero");
      System.exit(0);
    }

    return resultado;

  }
}
