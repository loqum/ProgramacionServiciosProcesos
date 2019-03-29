package com.rfm.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Ruben Fernandez Moreno
 */

public class Utils {

	public static String guardarArchivo(String inputUrl) {
		FileChooser chooser = new FileChooser();

		chooser.getExtensionFilters().addAll(new ExtensionFilter("JPEG (*.jpg)", "*.jpeg"),
				new ExtensionFilter("PNG (*.png)", "*.png"), new ExtensionFilter("GIF (*.gif)", "*.gif"),
				new ExtensionFilter("TXT (*.txt)", "*.txt"), new ExtensionFilter("PDF (*.pdf)", "*.pdf"),
				new ExtensionFilter("ZIP (*.zip)", "*.zip"), new ExtensionFilter("HTML (*.html)", "*.html"));

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

		if (result.isPresent() && result.get() == ButtonType.OK) {
			System.exit(0);
		}

	}

	public static void ventanaAlertaRutaArchivo() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(Constants.TITULO_VENTANA_ALERTA.getValue());
		alert.setHeaderText(Constants.ENCABEZADO_VENTANA_ALERTA.getValue());
		alert.setContentText(Constants.CONTENIDO_VENTANA_ALERTA.getValue());
		alert.initStyle(StageStyle.UTILITY);
		alert.showAndWait();
	}

	public static void ventanaAlertaListaVacia() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(Constants.TITULO_VENTANA_ALERTA_VACIA.getValue());
		alert.setHeaderText(Constants.ENCABEZADO_VENTANA_ALERTA_VACIA.getValue());
		alert.setContentText(Constants.CONTENIDO_VENTANA_ALERTA_VACIA.getValue());
		alert.initStyle(StageStyle.UTILITY);
		alert.showAndWait();
	}

	public static void borrarTextArea(TextArea textArea, StringBuilder stringBuilder) {
		textArea.setText(Constants.BLANK.getValue());
		stringBuilder.setLength(0);
	}

	public static String capturarEnlace(String enlace) {
		return Stream.of(enlace.split("\n")).skip(2).collect(Collectors.joining());
	}

	public static long contarEnlaces(String enlace) {
		return Stream.of(enlace.split("\n")).count();
	}

	public static List<String> agregarEnlacesList(String enlaces) {
		return Stream.of(enlaces.split("\n")).collect(Collectors.toList());
	}

}
