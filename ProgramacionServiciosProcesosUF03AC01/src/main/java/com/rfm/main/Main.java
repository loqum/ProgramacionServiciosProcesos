package com.rfm.main;

import java.io.IOException;
import java.net.Socket;

import com.rfm.connection.Client;
import com.rfm.connection.ConnectionClient;
import com.rfm.connection.Server;

public class Main {

  public static void main(String[] args) {

    Server server = new Server(5555);

    try {
      server.start();
      while (server.isStarted()) {
        Socket socket = server.acceptConnection();
        Client client = new Client(socket, 12345);
        server.addClient(client);
        ConnectionClient connectionClient = new ConnectionClient(server, client);
        connectionClient.start();
      }
    } catch (IOException e) {
      System.err.println("Error" + e.getMessage());
    }

  }
}
