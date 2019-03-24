package com.rfm.application;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.rfm.utils.Constants;
import com.rfm.utils.Estado;
import com.rfm.utils.Utils;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

  @FXML
  private TextField inputUrl;

  @FXML
  private TextArea inputListaDescargas;

  @FXML
  private Button botonDescargar;

  @FXML
  private Button botonCancelar;

  @FXML
  private Button botonBorrar;

  @FXML
  private MenuItem botonCerrarApp;

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

//      botonDescargar.setDisable(true);
      barraProgreso.setProgress(0);
      indicadorProgreso.setProgress(0);
      botonCancelar.setDisable(false);

      URL url;
      String nombreFichero = Utils.abrirArchivoAction(inputUrl.getText());

      try {
        url = new URL(inputUrl.getText());
        TareaDescarga tareaDescarga = new TareaDescarga(url, nombreFichero);

        barraProgreso.progressProperty().unbind();
        barraProgreso.progressProperty().add(tareaDescarga.getProgress());

        indicadorProgreso.progressProperty().unbind();
        indicadorProgreso.progressProperty().add(tareaDescarga.getProgress());

        // Start the Task.
        new Thread(tareaDescarga).start();

//        tareaDescarga.execute();

        sb.append(url.getFile()).append("\n");

        inputListaDescargas.setText(sb.toString());

      } catch (MalformedURLException e) {

        e.printStackTrace();

      } finally {
        inputUrl.setText(Estado.BLANK.getValue());
      }

    }

  }

  public void borrarListaDescargas() {
    inputListaDescargas.setText(Estado.BLANK.getValue());
    sb.setLength(0);
  }

  public void cerrarApp() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(Constants.getTituloVentanaConfirmacion());
    alert.setHeaderText(Constants.getEncabezadoVentanaConfirmacion());
    alert.setContentText(Constants.getContenidoVentanaConfirmacion());
    alert.initStyle(StageStyle.UTILITY);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
      System.exit(0);
    }

  }

}
