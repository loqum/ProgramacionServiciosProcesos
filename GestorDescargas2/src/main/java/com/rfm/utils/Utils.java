package com.rfm.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Ruben Fernandez Moreno
 */

public class Utils {

  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static String saveFilesMultiTypes(String inputUrl, Window window) {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("JPEG (*.jpg)", "*.jpeg"),
        new ExtensionFilter("PNG (*.png)", "*.png"), new ExtensionFilter("GIF (*.gif)", "*.gif"),
        new ExtensionFilter("TXT (*.txt)", "*.txt"), new ExtensionFilter("PDF (*.pdf)", "*.pdf"),
        new ExtensionFilter("ZIP (*.zip)", "*.zip"), new ExtensionFilter("HTML (*.html)", "*.html"));

    chooser.setInitialFileName(inputUrl);
    chooser.setTitle("Guardar como...");

    return chooser.showSaveDialog(window).getAbsolutePath();
  }

  public static String saveTxtFile(Window windows) {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("TXT (*.txt)", "*.txt"));

    chooser.setTitle("Guardar como...");

    return chooser.showSaveDialog(null).getAbsolutePath();
  }

  public static String openTxtFile(Window window) {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("TXT (*.txt)", "*.txt"));

    chooser.setTitle("Abrir archivo...");

    return chooser.showOpenDialog(null).getAbsolutePath();

  }

  public static void confirmationAlert(Window window) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(Constants.TITULO_VENTANA_CONFIRMACION.getValue());
    alert.setHeaderText(Constants.ENCABEZADO_VENTANA_CONFIRMACION.getValue());
    alert.setContentText(Constants.CONTENIDO_VENTANA_CONFIRMACION.getValue());
    alert.initOwner(window);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
      System.exit(0);
    }

  }

  public static void successAlert(Window window) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(Constants.TITULO_VENTANA_ALERTA_EXITO.getValue());
    alert.setContentText(Constants.CONTENIDO_VENTANA_ALERTA_EXITO.getValue());
    alert.initStyle(StageStyle.UTILITY);
    alert.initOwner(window);
    alert.showAndWait();
  }

  public static void pathFileAlert(Window window) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(Constants.TITULO_VENTANA_ALERTA.getValue());
    alert.setHeaderText(Constants.ENCABEZADO_VENTANA_ALERTA.getValue());
    alert.setContentText(Constants.CONTENIDO_VENTANA_ALERTA.getValue());
    alert.initStyle(StageStyle.UTILITY);
    alert.initOwner(window);
    alert.showAndWait();
  }

  public static void emptyListAlert(Window window) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(Constants.TITULO_VENTANA_ALERTA_VACIA.getValue());
    alert.setHeaderText(Constants.ENCABEZADO_VENTANA_ALERTA_VACIA.getValue());
    alert.setContentText(Constants.CONTENIDO_VENTANA_ALERTA_VACIA.getValue());
    alert.initStyle(StageStyle.UTILITY);
    alert.initOwner(window);
    alert.showAndWait();
  }

  public static void deleteText(TextInputControl textArea, StringBuilder stringBuilder) {
    textArea.setText(Constants.BLANK.getValue());
    stringBuilder.setLength(0);
  }

  public static String catchUrl(String enlace) {
    return Stream.of(enlace.split("\n")).skip(2).collect(Collectors.joining());
  }

  public static long countUrl(String enlace) {
    return Stream.of(enlace.split("\n")).count();
  }

  public static List<String> addUrlList(String enlaces) {
    return Stream.of(enlaces.split("\n")).collect(Collectors.toList());
  }

}
