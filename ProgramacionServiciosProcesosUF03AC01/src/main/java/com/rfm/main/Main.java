package com.rfm.main;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.rfm.connection.Client;
import com.rfm.connection.ConnectionClient;
import com.rfm.connection.Server;

public class Main {

  private static final Logger LOG = Logger.getLogger(Main.class);

  public static void main(String[] args) {

    Server server = new Server(5555);
    long id = 0;

    try {
      server.start();
      while (server.isStarted()) {
        Socket socket = server.acceptConnection();
        Client client = new Client(socket, id++);
        server.addClient(client);
        ConnectionClient connectionClient = new ConnectionClient(server, client);
        connectionClient.start();
      }
    } catch (IOException e) {
      LOG.error("Error" + e.getMessage());
    }

  }
}
