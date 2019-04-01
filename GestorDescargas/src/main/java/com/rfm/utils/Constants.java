package com.rfm.utils;

/**
 * @author Ruben Fernandez Moreno
 */

public enum Constants {
  
  BLANK(""),
  
  ENCABEZADO_VENTANA_CONFIRMACION("Atención!"),
  TITULO_VENTANA_CONFIRMACION("Ventana de confirmación"),  
  CONTENIDO_VENTANA_CONFIRMACION("¿Estás seguro de que quieres salir del programa?"),
  
  TITULO_VENTANA_ALERTA("Alerta!"),
  ENCABEZADO_VENTANA_ALERTA("Antes de descargar debes seleccionar un lugar donde guardar los archivos"),
  CONTENIDO_VENTANA_ALERTA("Puedes seleccionar la ruta en Archivo - Ruta de guardado predeterminado"),
  
  TITULO_VENTANA_ALERTA_VACIA("Alerta!"),
  ENCABEZADO_VENTANA_ALERTA_VACIA("Antes debes seleccionar un archivo de texto (*.txt) que incluya los enlaces que deseas descargar"),
  CONTENIDO_VENTANA_ALERTA_VACIA("Puedes seleccionar el archivo en cuesti�n en Archivo - Seleccionar un archivo..."),
  
  TITULO_VENTANA_ALERTA_EXITO("Informaci�n"),
  CONTENIDO_VENTANA_ALERTA_EXITO("Archivo generado correctamente en la ruta seleccionada");
  
  private final String value;
  
  Constants(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
