package com.rfm.utils;

public enum Constants {
  
  BLANK(""),
  TITULO_VENTANA_CONFIRMACION("Ventana de confirmación"),
  ENCABEZADO_VENTANA_CONFIRMACION("Atención!"),
  CONTENIDO_VENTANA_CONFIRMACION("¿Estás seguro de que quieres salir del programa?");

  private final String value;
  
  Constants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
