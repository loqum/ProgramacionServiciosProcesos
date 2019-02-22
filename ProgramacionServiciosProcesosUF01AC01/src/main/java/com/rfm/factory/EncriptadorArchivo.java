package com.rfm.factory;

import java.io.IOException;

public interface EncriptadorArchivo {

  public void escribirArchivo(String nombreArchivo, String contenido) throws IOException;

  public String leerArchivo(String nombreArchivo) throws IOException;

}
