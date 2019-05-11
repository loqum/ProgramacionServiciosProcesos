package com.rfm.connection;

import java.io.IOException;

import org.apache.log4j.Logger;

public class ConnectionClient extends Thread {

  private static final Logger LOG = Logger.getLogger(ConnectionClient.class);
  private Client client;
  private Server server;

  public ConnectionClient(Server server, Client client) {
    this.client = client;
    this.server = server;
  }

  @Override
  public void run() {
    client.getOut().println("Bienvenido, Cliente " + client.getIdClient() + "\n" + "Para finalizar la sesion escribir '/out/'.");
    LOG.info("Bienvenido, Cliente " + client.getIdClient() + "\n" + "Para finalizar la sesion escribir '/out/'.");

    try {
      while (client.isConnected()) {
        String message = client.getIn().readLine();
        if (message != null) {
          server.sendToAll("Client " + client.getIdClient() + ": " + message);
          
          if (message.equals("/out/")) {
            client.close();
            server.sendToAll("Client " + client.getIdClient() + ": " + "finalizo la conexion.");
            LOG.info("El cliente finalizo la conexion");
          }
          
        }
        
      }
    } catch (IOException e) {
      LOG.error("Error: " + e.getMessage());
    }
  }
}
