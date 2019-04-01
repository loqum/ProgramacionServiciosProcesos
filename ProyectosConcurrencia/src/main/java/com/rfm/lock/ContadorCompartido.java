package com.rfm.lock;

public class ContadorCompartido {

  private int n = 0;

  public int getN(String id) {
    return n;
  }

  public void setN(String id, int n) {
    this.n = n;
    System.err.println(id + ": " + n);
  }

}
