package com.rfm.utils;

public class Constantes {

  private Constantes() {
    
  }
  
  private static final String ABECEDARIO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

  private static final String MENSAJE_ALGORITMO = 
      "Introduce el código del algoritmo: "
      .concat("\n\n[1. Cesar]")
      .concat("\n[2. CesarMod]")
      .concat("\n[3. Vigenere]")
      .concat("\n[3. VigenereMod]");
  
  private static final String MENSAJE_OPERACION =
      "Introduce el código de la operacion: "
      .concat("\n\n[1. Cifrar]")
      .concat("\n[2. Descifrar]");

  public static String getAbecedario() {
    return ABECEDARIO;
  }

  public static String getMensajeAlgoritmo() {
    return MENSAJE_ALGORITMO;
  }

  public static String getMensajeOperacion() {
    return MENSAJE_OPERACION;
  }

}
