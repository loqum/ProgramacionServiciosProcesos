package com.rfm.application;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;


public class TareaDescarga extends Task<State> {

  private URL url;
  private String nombreFichero;

  public TareaDescarga(URL url, String nombreFichero) {
    this.url = url;
    this.nombreFichero = nombreFichero;
  }

  @Override
  protected State call() throws Exception {
    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;
    double contador = 0;

    try {
      
      URLConnection urlConnection = url.openConnection();

      inputStream = urlConnection.getInputStream();

      fileOutputStream = new FileOutputStream(nombreFichero);

      byte[] array = new byte[2048];
      int leido = inputStream.read(array);

      while (leido > 0) {
        updateProgress(++contador, 100);
        fileOutputStream.write(array, 0, leido);
        leido = inputStream.read(array);
      }

    } catch (Exception e) {
      System.err.println(e.getMessage());
      return State.FAILED;

    } finally {
      inputStream.close();
      fileOutputStream.close();
    }

    return State.SUCCEEDED;
  }
}
