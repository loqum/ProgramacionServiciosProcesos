package com.rfm.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.rfm.application.TareaDescarga;
import com.rfm.utils.Constants;
import com.rfm.utils.Factory;
import com.rfm.utils.FactoryMethod;
import com.rfm.utils.Utils;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

/**
 * @author Ruben Fernandez Moreno
 */

public class GestorDescargaController implements Initializable {

  private static final Logger LOG = Logger.getLogger(GestorDescargaController.class);

  @FXML
  private AnchorPane anchorPaneMain;

  @FXML
  private Button botonDescargar;

  @FXML
  private Button botonDescargarLista;

  @FXML
  private Button botonArchivarEnlaces;

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
    anchorPaneMain.getStyleClass().add("anchorPaneMain");

  }

  public void descargar() {

    if (!inputUrl.getText().equals("")) {

      barraProgreso.setProgress(0);
      indicadorProgreso.setProgress(0);

      URL url;

      int indiceUrl = inputUrl.getText().lastIndexOf('/');

      String nombreFichero = Utils.saveFilesMultiTypes(inputUrl.getText().substring(indiceUrl + 1),
          anchorPaneMain.getScene().getWindow());

      if (nombreFichero != null) {

        try {
          url = new URL(inputUrl.getText());
          tareaDescarga = new TareaDescarga(url, nombreFichero);

          barraProgreso.progressProperty().unbind();
          indicadorProgreso.progressProperty().unbind();

          barraProgreso.progressProperty().bind(tareaDescarga.progressProperty());
          indicadorProgreso.progressProperty().bind(tareaDescarga.progressProperty());

          new Thread(tareaDescarga).start();

          tareaDescarga.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
              barraProgreso.progressProperty().unbind();
              indicadorProgreso.progressProperty().unbind();
              inputUrl.setText(Constants.BLANK.getValue());

              sb.append(Constants.ARCHIVO.getValue().concat("'")
                  .concat(nombreFichero.substring(nombreFichero.lastIndexOf('\\') + 1)).concat("'")
                  .concat(Constants.DESCARGADO_EXITO.getValue()).concat("'").concat(nombreFichero).concat("'"))
                  .append("\n");

              inputListaDescargas.setText(sb.toString());

              LOG.info(Constants.ARCHIVO.getValue().concat("'")
                  .concat(nombreFichero.substring(nombreFichero.lastIndexOf('\\') + 1)).concat("'")
                  .concat(Constants.DESCARGADO_EXITO.getValue()).concat("'").concat(nombreFichero).concat("'"));
            }

          });

          LOG.info("Descarga realizada con �xito");

        } catch (MalformedURLException e) {
          LOG.error("Error: " + e.getMessage());
        }

      }

    }

  }

  public void descargarLista() {
    String directorio = null;

    Preferences preferences = Preferences.userNodeForPackage(GestorDescargaController.class);
    directorio = preferences.get("directorio", null);

    if (directorio != null) {

      if (!inputListaDescargas.getText().equals("")) {

        String nombreArchivo = null;
        String nombreArchivoCompleto = null;
        List<String> enlacesUrl = new ArrayList<>();

        try {

          enlacesUrl = Utils.addUrlList(inputListaDescargas.getText());

          barraProgreso.progressProperty().unbind();
          indicadorProgreso.progressProperty().unbind();

          for (String string : enlacesUrl) {
            nombreArchivo = string.substring(string.lastIndexOf('/'));
            nombreArchivoCompleto = directorio.concat("\\").concat(nombreArchivo);
            tareaDescarga = new TareaDescarga(new URL(string), nombreArchivoCompleto);

            new Thread(tareaDescarga).start();

            barraProgreso.progressProperty().bind(tareaDescarga.progressProperty());
            indicadorProgreso.progressProperty().bind(tareaDescarga.progressProperty());

            tareaDescarga.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {

                  @Override
                  public void handle(WorkerStateEvent t) {
                    barraProgreso.progressProperty().unbind();
                    indicadorProgreso.progressProperty().unbind();
                    inputUrl.setText(Constants.BLANK.getValue());

                  }
                });

            sb.append(Constants.ARCHIVO.getValue().concat("'").concat(nombreArchivo).concat("'")
                .concat(Constants.DESCARGADO_EXITO.getValue()).concat("'").concat(directorio).concat("'")).append("\n");

          }

          LOG.info(Constants.ARCHIVO.getValue().concat("'").concat(nombreArchivo).concat("'")
              .concat(Constants.DESCARGADO_EXITO.getValue()).concat("'").concat(directorio).concat("'").concat("\n"));

        } catch (MalformedURLException e) {
          LOG.error("Error: " + e.getMessage());

        } finally {
          inputListaDescargas.setText(null);
          inputListaDescargas.setText(sb.toString());
        }
      } else {
        Utils.emptyListAlert(anchorPaneMain.getScene().getWindow());
      }

    } else {
      Utils.pathFileAlert(anchorPaneMain.getScene().getWindow());
    }
  }

  public void borrarListaDescargas() {
    Utils.deleteText(inputListaDescargas, sb);
    inputUrl.setText(Constants.BLANK.getValue());
    barraProgreso.setProgress(0);
    indicadorProgreso.setProgress(0);
  }

  public void cerrarAppAction() {
    Utils.confirmationAlert(anchorPaneMain.getScene().getWindow());
  }

  public void abrirArchivoAction() {

    try (Factory factory = FactoryMethod.getInstance()) {

      inputListaDescargas.setText(factory.readFile(Utils.openTxtFile(anchorPaneMain.getScene().getWindow())));

    } catch (Exception e) {

      LOG.error("Error al abrir o leer el archivo: " + e.getMessage());
    }

  }

  public File seleccionarDirectorio() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Selecciona un directorio...");
    File dir = directoryChooser.showDialog(null);

    try {

      Preferences preferences = Preferences.userNodeForPackage(GestorDescargaController.class);
      preferences.clear();
      preferences.put("directorio", dir.getAbsolutePath());

    } catch (BackingStoreException e) {
      LOG.error("Error al guardar las preferencias: " + e.getMessage());
    }

    return dir;
  }

  public void cancelarDescarga() {

    if (tareaDescarga != null) {

      try {
        tareaDescarga.cancel(true);
        barraProgreso.progressProperty().unbind();
        indicadorProgreso.progressProperty().unbind();
        barraProgreso.setProgress(0);
        indicadorProgreso.setProgress(0);
        Utils.deleteText(inputListaDescargas, sb);
        inputUrl.setText("");

      } catch (RuntimeException e) {
        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }
    }

  }

  public void archivarEnlaces() {

    if (!inputListaDescargas.getText().equals("")) {

      try (Factory factory = FactoryMethod.getInstance()) {

        factory.writeFile(Utils.saveTxtFile(anchorPaneMain.getScene().getWindow()), inputListaDescargas.getText());
        Utils.successAlert(anchorPaneMain.getScene().getWindow());

      } catch (Exception e) {

        LOG.error("Error: " + e.getMessage());
      }

    } else {
      Utils.emptyListAlert(anchorPaneMain.getScene().getWindow());
    }

  }

}
