package com.rfm.connection;

import java.io.IOException;

public class ConnectionClient extends Thread {

  private Client client;
  private Server server;

  public ConnectionClient(Server server, Client client) {
    this.client = client;
    this.server = server;
  }

  @Override
  public void run() {
    client.getOut().println("Bienvenido");

    try {
      while (client.isConnected()) {
        String message = client.getIn().readLine();
        server.sendToAll(message);
      }
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
