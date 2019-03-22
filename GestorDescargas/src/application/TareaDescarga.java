package application;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.SwingWorker;

public class TareaDescarga extends SwingWorker<Void, Integer> {

  private URL url;
  private String nombreFichero;

  public TareaDescarga(URL url, String nombreFichero) {
    this.url = url;
    this.nombreFichero = nombreFichero;
  }

  @Override
  protected Void doInBackground() throws Exception {

    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;

    try {

      URLConnection urlConnection = url.openConnection();

      inputStream = urlConnection.getInputStream();

      fileOutputStream = new FileOutputStream(nombreFichero);

      byte[] array = new byte[1000];
      int leido = inputStream.read(array);

      while (leido > 0) {
        fileOutputStream.write(array, 0, leido);
        leido = inputStream.read(array);
      }

    } catch (Exception e) {
      System.err.println(e.getMessage());

    } finally {
      inputStream.close();
      fileOutputStream.close();
    }

    return null;
  }

}
