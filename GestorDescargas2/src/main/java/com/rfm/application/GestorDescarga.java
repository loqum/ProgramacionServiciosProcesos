package com.rfm.application;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * @author Ruben Fernandez Moreno
 */

public class GestorDescarga extends Application {

  private static final Logger LOG = Logger.getLogger(GestorDescarga.class);

  @Override
  public void start(Stage primaryStage) {
    BasicConfigurator.configure();

    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("/view/SplashScreen.fxml"));
      Scene scene = new Scene(root, 600, 400);
      primaryStage.setResizable(false);
      primaryStage.setScene(scene);
      primaryStage.initStyle(StageStyle.TRANSPARENT);
      primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("view/images/icon.png")));
      primaryStage.show();
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }

  }

  public static void main(String[] args) {
    launch(args);
  }
}
