package com.rfm.utils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.rfm.application.MainController;

public class FactoryTxt implements Factory, Serializable {

  private static final Logger LOG = Logger.getLogger(MainController.class);
  private static final long serialVersionUID = -3232282448273008017L;

  @Override
  public String readFile(String fileName) throws IOException {

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      return stream.collect(Collectors.joining("\n"));

    } catch (IOException e) {

      LOG.error("Error al leer el archivo: " + e.getMessage());
      throw e;
    }

  }

  @Override
  public void close() throws Exception {
    LOG.info("Cerrando...");

  }

  @Override
  public List<?> readFilesList(String fileName) throws IOException {

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      return stream.collect(Collectors.toList());

    } catch (IOException e) {

      LOG.error("Error al leer el archivo: " + e.getMessage());
      throw e;
    }

  }

  @Override
  public void writeFile(String fileName, String content) throws IOException {
    Files.write(Paths.get(fileName), content.getBytes());
  }

}
