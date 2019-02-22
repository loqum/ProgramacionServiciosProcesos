package com.rfm.utils;

public class Constantes {

  private Constantes() {

  }

  private static final String ABECEDARIO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

  private static final String MENSAJE_ALGORITMO = "Introduce el codigo del algoritmo: "
      .concat("\n\n[1. Cesar]")
      .concat("\n[2. CesarMod]")
      .concat("\n[3. Vigenere]")
      .concat("\n[4. VigenereMod]");

  private static final String MENSAJE_OPERACION = "Introduce el codigo de la operacion: "
      .concat("\n\n[1. Cifrar]")
      .concat("\n[2. Descifrar]")
      .concat("\n[3. Cifrar desde archivo]")
      .concat("\n[4. Descifrar desde archivo]");

  private static final String INTRODUCE_FRASE = "Introduce una frase: ";
  
  private static final String INTRODUCE_CLAVE = "Introduce una clave: ";
  
  private static final String PREGUNTA_ARCHIVO = "¿Quieres generar un archivo de texto con el resultado? [SI] o [NO]";
  
  private static final String RUTA_ARCHIVO = "Escoja la ruta y nombre para el archivo: "
      .concat("\n[Por defecto, la ruta sera la misma que la del jar ejecutado]");

  public static String getAbecedario() {
    return ABECEDARIO;
  }

  public static String getMensajeAlgoritmo() {
    return MENSAJE_ALGORITMO;
  }

  public static String getMensajeOperacion() {
    return MENSAJE_OPERACION;
  }

  public static String getIntroduceFrase() {
    return INTRODUCE_FRASE;
  }

  public static String getIntroduceClave() {
    return INTRODUCE_CLAVE;
  }

  public static String getPreguntaArchivo() {
    return PREGUNTA_ARCHIVO;
  }

  public static String getRutaArchivo() {
    return RUTA_ARCHIVO;
  }

}
