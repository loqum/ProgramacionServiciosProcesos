package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

  @FXML
  private TextField inputUrl;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void descargar(ActionEvent actionEvent) {
    try {

      URL url = new URL(inputUrl.getText());
      URLConnection urlConnection = url.openConnection();

      InputStream inputStream = urlConnection.getInputStream();
      
      FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\ruben\\eclipse-workspace\\GestorDescargas\\foto.html");
      
      byte[]array = new byte[1000];
      int leido = inputStream.read(array);
      
      while (leido > 0) {
        fileOutputStream.write(array, 0, leido);
        leido = inputStream.read(array);
      }
      
      inputStream.close();
      fileOutputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  public void abrirArchivoAction(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    
    chooser.getExtensionFilters().addAll(
        new ExtensionFilter("TXT Files", "*.txt"), 
        new ExtensionFilter("PDF Files", "*.pdf"),
        new ExtensionFilter("JPEG Files", "*.jpeg"),
        new ExtensionFilter("HTML Files", "*.html"),
        new ExtensionFilter("GIF Files", "*.gif"));
    
    File selectedFile = chooser.showOpenDialog(null);

  }

}
