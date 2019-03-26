package com.rfm.application;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.rfm.utils.Constants;
import com.rfm.utils.Factory;
import com.rfm.utils.FactoryMethod;
import com.rfm.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

/**
 * @author Ruben Fernandez Moreno
 */

public class MainController implements Initializable {

  private static final Logger LOG = Logger.getLogger(MainController.class);

  @FXML
  private TextField inputUrl;

  @FXML
  private TextArea inputListaDescargas;

  @FXML
  private Button botonDescargar;

  @FXML
  private Button botonDescargarLista;

  @FXML
  private Button botonCancelar;

  @FXML
  private Button botonBorrar;

  @FXML
  private MenuItem botonCerrarApp;

  @FXML
  private MenuItem botonAbrirArchivo;

  @FXML
  private MenuItem botonRutaGuardado;

  @FXML
  private ProgressBar barraProgreso;

  @FXML
  private ProgressIndicator indicadorProgreso;

  private StringBuilder sb = new StringBuilder();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void descargar(ActionEvent actionEvent) {

    if (!inputUrl.getText().equals("")) {

      barraProgreso.setProgress(0);
      indicadorProgreso.setProgress(0);

      URL url;
      int indiceNombreArchivo = 0;
      Thread threadDescarga = null;

      int indiceUrl = inputUrl.getText().lastIndexOf('/');

      String nombreFichero = Utils.guardarArchivo(inputUrl.getText().substring(indiceUrl + 1));

      try {
        url = new URL(inputUrl.getText());
        TareaDescarga tareaDescarga = new TareaDescarga(url, nombreFichero);

        barraProgreso.progressProperty().unbind();
        barraProgreso.progressProperty().add(tareaDescarga.getProgress());

        indicadorProgreso.progressProperty().unbind();
        indicadorProgreso.progressProperty().add(tareaDescarga.getProgress());

        threadDescarga = new Thread(tareaDescarga);

        threadDescarga.start();

        indiceNombreArchivo = nombreFichero.lastIndexOf('\\');
        sb.append("Archivo ".concat("'").concat(nombreFichero.substring(indiceNombreArchivo + 1))
            .concat("'").concat(" descargado con �xito en ").concat("'").concat(nombreFichero)
            .concat("'")).append("\n");

        inputListaDescargas.setText(sb.toString());

      } catch (MalformedURLException e) {
        LOG.error("Error: " + e.getMessage());

      } finally {

        inputUrl.setText(Constants.BLANK.getValue());
        LOG.info("Archivo ".concat("'").concat(nombreFichero.substring(indiceNombreArchivo + 1))
            .concat("'").concat(" descargado con �xito en ").concat("'").concat(nombreFichero)
            .concat("'"));
      }

    }

  }

  public void borrarListaDescargas() {
    Utils.borrarTextArea(inputListaDescargas, sb);
  }

  public void cerrarAppAction() {
    Utils.ventanaConfirmacion();
  }

  public void abrirArchivoAction() {

    try (Factory factory = FactoryMethod.getInstance()) {

      inputListaDescargas.setText(factory.readFile(Utils.abrirArchivo()));

    } catch (Exception e) {

      LOG.error("Error al abrir o leer el archivo: " + e.getMessage());
    }

  }

  public File seleccionarDirectorio() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Selecciona un directorio...");
    File dir = directoryChooser.showDialog(null);

    try {

      Preferences preferences = Preferences.userNodeForPackage(MainController.class);
      preferences.clear();
      preferences.put("directorio", dir.getAbsolutePath());

    } catch (BackingStoreException e) {
      LOG.error("Error al guardar las preferencias: " + e.getMessage());
    }

    return dir;
  }

  public void descargarLista(ActionEvent actionEvent) {
    String directorio = null;

    Preferences preferences = Preferences.userNodeForPackage(MainController.class);
    directorio = preferences.get("directorio", "C:\\Users\\");

    if (directorio != null) {

      String nombreArchivo = null;
      String nombreArchivoCompleto = null;
      Thread threadDescarga = null;
      List<String> enlacesUrl = new ArrayList<>();

      try {

        enlacesUrl = Utils.agregarEnlacesList(inputListaDescargas.getText());

        for (String string : enlacesUrl) {
          nombreArchivo = string.substring(string.lastIndexOf('/'));
          nombreArchivoCompleto = directorio.concat("\\").concat(nombreArchivo);
          TareaDescarga tareaDescarga = new TareaDescarga(new URL(string), nombreArchivoCompleto);
          threadDescarga = new Thread(tareaDescarga);
          threadDescarga.start();

          sb.append("Archivo ".concat("'").concat(nombreArchivo).concat("'")
              .concat(" descargado con éxito en ").concat("'").concat(directorio).concat("'"))
              .append("\n");
        }

      } catch (MalformedURLException e) {
        LOG.error("Error: " + e.getMessage());

      } finally {
        inputListaDescargas.setText(null);
        inputListaDescargas.setText(sb.toString());
      }

    } else {
      Utils.ventanaAlerta();
    }
  }

  public void cancelarDescarga(ActionEvent actionEvent) {
    
  }

}
