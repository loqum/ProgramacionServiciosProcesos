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

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

  @FXML
  private TextArea inputListaDescargas;

  @FXML
  private TextField inputUrl;

  private StringBuilder sb = new StringBuilder();

  private TareaDescarga tareaDescarga;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void descargar(ActionEvent actionEvent) {

    if (!inputUrl.getText().equals("")) {

      barraProgreso.setProgress(0);
      indicadorProgreso.setProgress(0);

      URL url;

      int indiceUrl = inputUrl.getText().lastIndexOf('/');

      String nombreFichero = Utils.guardarArchivo(inputUrl.getText().substring(indiceUrl + 1));

      try {
        url = new URL(inputUrl.getText());
        tareaDescarga = new TareaDescarga(url, nombreFichero);

        barraProgreso.progressProperty().unbind();
        indicadorProgreso.progressProperty().unbind();

        barraProgreso.progressProperty().bind(tareaDescarga.progressProperty());
        indicadorProgreso.progressProperty().bind(tareaDescarga.progressProperty());

        tareaDescarga.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {

          @Override
          public void handle(WorkerStateEvent t) {
            barraProgreso.progressProperty().unbind();
            indicadorProgreso.progressProperty().unbind();
            inputUrl.setText(Constants.BLANK.getValue());

            sb.append("Archivo ".concat("'").concat(nombreFichero.substring(nombreFichero.lastIndexOf('\\') + 1))
                .concat("'").concat(" descargado con éxito en ").concat("'").concat(nombreFichero).concat("'"))
                .append("\n");

            inputListaDescargas.setText(sb.toString());

            LOG.info("Archivo ".concat("'").concat(nombreFichero.substring(nombreFichero.lastIndexOf('\\') + 1))
                .concat("'").concat(" descargado con éxito en ").concat("'").concat(nombreFichero).concat("'"));
          }
        });

        new Thread(tareaDescarga).start();

      } catch (MalformedURLException e) {
        LOG.error("Error: " + e.getMessage());

      }

    }

  }

  public void borrarListaDescargas() {
    Utils.borrarTextArea(inputListaDescargas, sb);
    inputUrl.setText(Constants.BLANK.getValue());
    barraProgreso.setProgress(0);
    indicadorProgreso.setProgress(0);
  }

  public void cerrarAppAction() {
    Utils.ventanaConfirmacion();
  }

  public void abrirArchivoAction() {

    try (Factory factory = FactoryMethod.getInstance(null)) {

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

      if (!(inputListaDescargas.getText().equals(null) || inputListaDescargas.getText().equals(""))) {

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

          sb.append("Archivo ".concat("'").concat(nombreArchivo).concat("'").concat(" descargado con éxito en ")
              .concat("'").concat(directorio).concat("'")).append("\n");
        }

        } catch (MalformedURLException e) {
          LOG.error("Error: " + e.getMessage());

        } finally {
          inputListaDescargas.setText(null);
          inputListaDescargas.setText(sb.toString());
        }
      } else {
        Utils.ventanaAlertaListaVacia();
      }

    } else {
      Utils.ventanaAlertaRutaArchivo();
    }
  }

  public void cancelarDescarga(ActionEvent actionEvent) {
    tareaDescarga.cancel(true);
    barraProgreso.progressProperty().unbind();
    indicadorProgreso.progressProperty().unbind();
    barraProgreso.setProgress(0);
    indicadorProgreso.setProgress(0);

  }

}
