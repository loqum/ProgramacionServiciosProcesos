package com.rfm.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Server {

  private static final Logger LOG = Logger.getLogger(Server.class);
  private ServerSocket serverSocket;
  private int port;
  private List<Client> clients;

  public Server() {

  }

  public Server(int port) {
    this.port = port;
    clients = new ArrayList<>();
  }

  public void start() throws IOException {
    serverSocket = new ServerSocket(port);
  }

  public void stop() throws IOException {
    serverSocket.close();
  }

  public boolean isStarted() {
    return !serverSocket.isClosed();
  }

  public Socket acceptConnection() throws IOException {
    return serverSocket.accept();
  }

  public void addClient(Client client) {
    clients.add(client);
  }

  public void sendToAll(String message) {

    for (Client client : clients) {
      client.getOut().println(message);
      LOG.info(message);

    }
  }

}
