package com.rfm.utils;

public class Constants {

  private Constants() {

  }

  private static final String TITULO_VENTANA_CONFIRMACION = "Ventana de confirmaci�n";
  private static final String ENCABEZADO_VENTANA_CONFIRMACION = "Atenci�n!";
  private static final String CONTENIDO_VENTANA_CONFIRMACION = "�Est�s seguro de que quieres salir del programa?";

  public static String getTituloVentanaConfirmacion() {
    return TITULO_VENTANA_CONFIRMACION;
  }

  public static String getEncabezadoVentanaConfirmacion() {
    return ENCABEZADO_VENTANA_CONFIRMACION;
  }

  public static String getContenidoVentanaConfirmacion() {
    return CONTENIDO_VENTANA_CONFIRMACION;
  }

}
