package com.rfm.application;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

  private static final Logger LOG = Logger.getLogger(Main.class);

  @Override
  public void start(Stage primaryStage) {
    try {
      BasicConfigurator.configure();
      Parent root = FXMLLoader.load(getClass().getResource("/com/rfm/application/Main.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.setTitle("R-Downloader");
      primaryStage.getIcons()
          .add(new Image(ClassLoader.getSystemResourceAsStream("resources/icon.png")));
      primaryStage.show();
    } catch (Exception e) {
      LOG.error("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
