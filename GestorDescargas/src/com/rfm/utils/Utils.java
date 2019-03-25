package com.rfm.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

public class Utils {

  public static String guardarArchivo(String inputUrl) {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("JPEG (*.jpg)", "*.jpeg"),
        new ExtensionFilter("PNG (*.png)", "*.png"), new ExtensionFilter("GIF (*.gif)", "*.gif"),
        new ExtensionFilter("TXT (*.txt)", "*.txt"), new ExtensionFilter("PDF (*.pdf)", "*.pdf"),
        new ExtensionFilter("ZIP (*.zip)", "*.zip"),
        new ExtensionFilter("HTML (*.html)", "*.html"));

    chooser.setInitialFileName(inputUrl);
    chooser.setTitle("Guardar como");

    return chooser.showSaveDialog(null).getAbsolutePath();
  }

  public static String abrirArchivo() {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("TXT (*.txt)", "*.txt"));

    chooser.setTitle("Abrir archivo...");

    return chooser.showOpenDialog(null).getAbsolutePath();

  }

  public static void ventanaConfirmacion() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(Constants.TITULO_VENTANA_CONFIRMACION.getValue());
    alert.setHeaderText(Constants.ENCABEZADO_VENTANA_CONFIRMACION.getValue());
    alert.setContentText(Constants.CONTENIDO_VENTANA_CONFIRMACION.getValue());
    alert.initStyle(StageStyle.UTILITY);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
      System.exit(0);
    }

  }

  public static void borrarTextArea(TextArea textArea, StringBuilder stringBuilder) {
    textArea.setText(Constants.BLANK.getValue());
    stringBuilder.setLength(0);
  }
  
  

}
