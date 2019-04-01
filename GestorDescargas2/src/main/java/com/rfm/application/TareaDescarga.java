package com.rfm.application;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import javafx.concurrent.Task;

public class TareaDescarga extends Task<Void> implements Runnable {

  private static final Logger LOG = Logger.getLogger(TareaDescarga.class);

  private URL url;
  private String nombreFichero;

  public TareaDescarga(URL url, String nombreFichero) {
    this.url = url;
    this.nombreFichero = nombreFichero;
  }

  @Override
  protected Void call() throws Exception {
    double workDone = 0;

    Thread.sleep(1000);

    URLConnection urlConnection = url.openConnection();

    try (

        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(nombreFichero)) {

      byte[] array = new byte[1000];
      int leido = inputStream.read(array);
      int max = leido;

      while (leido > 0) {
        fileOutputStream.write(array, 0, max);
        leido = inputStream.read(array);
        this.updateProgress(++workDone, max);

        if (Thread.currentThread().isInterrupted()) {
          LOG.info("Cancelando...");
          return null;
        }

      }

    } catch (Exception e) {
      LOG.error(e.getMessage());

    }

    return null;

  }
}
