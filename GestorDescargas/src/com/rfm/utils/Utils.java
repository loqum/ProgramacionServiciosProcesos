package com.rfm.utils;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Utils {

  public static String abrirArchivoAction(String inputUrl) {
    FileChooser chooser = new FileChooser();

    chooser.getExtensionFilters().addAll(new ExtensionFilter("TXT (*.txt)", "*.txt"),
        new ExtensionFilter("PDF (*.pdf)", "*.pdf"), new ExtensionFilter("JPEG (*.jpg)", "*.jpeg"),
        new ExtensionFilter("HTML (*.html)", "*.html"), new ExtensionFilter("GIF (*.gif)", "*.gif"));

    chooser.setInitialFileName(inputUrl);
    chooser.setTitle("Guardar como");

    return chooser.showSaveDialog(null).getAbsolutePath();
  }

}
