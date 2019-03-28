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
    double max = 0;

    Thread.sleep(1000);

    URLConnection urlConnection = url.openConnection();

    try (InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(nombreFichero)) {

      byte[] array = new byte[2048];
      int leido = inputStream.read(array);

      while (leido > 0) {
        updateProgress(++workDone, max++);
        fileOutputStream.write(array, 0, leido);
        leido = inputStream.read(array);

        if (Thread.currentThread().isInterrupted()) {
          LOG.info("Salida");
          return null;
        }

      }

    } catch (Exception e) {
      LOG.error(e.getMessage());

    }

    return null;

  }
}
