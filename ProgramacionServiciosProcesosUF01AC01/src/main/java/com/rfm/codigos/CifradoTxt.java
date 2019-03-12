package com.rfm.codigos;

import com.rfm.factory.EncriptadorArchivo;
import com.rfm.utils.CrearArchivo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

public class CifradoTxt implements EncriptadorArchivo {

  private static final Logger LOG = Logger.getLogger(CifradoTxt.class);

  @Override
  public void escribirArchivo(String nombreArchivo, String contenido) throws IOException {

    File archivo = CrearArchivo.crear(nombreArchivo);

    Files.write(Paths.get(archivo.getAbsolutePath()), contenido.getBytes());

  }

  @Override
  public String leerArchivo(String nombreArchivo) throws IOException {

    String resultado = null;

    try (Stream<String> stream = Files.lines(Paths.get(nombreArchivo))) {

      resultado = stream.collect(Collectors.joining(" "));

    } catch (IOException e) {

      LOG.error("Error: " + e.getMessage());

    }

    return resultado;

  }

}
