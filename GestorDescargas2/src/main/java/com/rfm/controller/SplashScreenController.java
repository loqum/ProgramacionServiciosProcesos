package com.rfm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreenController implements Initializable {

  private static final Logger LOG = Logger.getLogger(SplashScreenController.class);

  @FXML
  ImageView fondo;

  @FXML
  ProgressIndicator indicadorProgresoSplash;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    FadeTransition transition = new FadeTransition(Duration.millis(3000), fondo);
    transition.setFromValue(-1);
    transition.setToValue(2);
    transition.play();

    transition.setOnFinished(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {

        try {
          Parent root = null;
          root = FXMLLoader.load(getClass().getResource("/view/GestorDescarga.fxml"));
          Stage ventana = (Stage) fondo.getScene().getWindow();
          ventana.hide();
          Stage ventanaApp = new Stage();
          Scene scene = new Scene(root, 600, 400);
          ventanaApp.setScene(scene);
          scene.getStylesheets().add(getClass().getResource("/view/css/style.css").toExternalForm());
          ventanaApp.setResizable(false);
          ventanaApp.setTitle("RFM-Downloader");
          ventanaApp.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("view/images/icon.png")));

          ventanaApp.show();

        } catch (IOException e) {
          LOG.error("Error: " + e.getMessage());
        }

      }
    });

  }

}
