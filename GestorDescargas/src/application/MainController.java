package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.rfm.utils.Estado;
import com.rfm.utils.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

  @FXML
  private TextField inputUrl;
  
  @FXML
  private TextArea inputListaDescargas;
  
  @FXML
  private Button botonDescargar;
  
  @FXML
  private Button botonBorrar;
  
  private StringBuilder sb = new StringBuilder();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void descargar(ActionEvent actionEvent) {

    if (!inputUrl.getText().equals("")) {

      URL url;
      String nombreFichero = Utils.abrirArchivoAction(inputUrl.getText());

      try {
        url = new URL(inputUrl.getText());
        TareaDescarga tareaDescarga = new TareaDescarga(url, nombreFichero);

        tareaDescarga.execute();
        
        sb.append(url.getFile()).append("\n");
        
        inputListaDescargas.setText(sb.toString());
        
      } catch (MalformedURLException e) {

        e.printStackTrace();
        
      } finally {
        inputUrl.setText(Estado.BLANK.getValue());
      }
      
    }

  }
  
  public void borrarListaDescargas(ActionEvent actionEvent) {
    inputListaDescargas.setText(Estado.BLANK.getValue());
    sb.setLength(0);
  }

  

}
