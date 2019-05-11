package com.rfm.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private long idClient;

  public Client(Socket socket, long idClient) throws IOException {
    this.socket = socket;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    this.idClient = idClient;
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public BufferedReader getIn() {
    return in;
  }

  public void setIn(BufferedReader in) {
    this.in = in;
  }

  public PrintWriter getOut() {
    return out;
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public boolean isConnected() {
    return !socket.isClosed();
  }
  
  public void close() throws IOException {
    socket.close();
  }

  public long getIdClient() {
    return idClient;
  }

  public void setIdClient(long idClient) {
    this.idClient = idClient;
  }
}
