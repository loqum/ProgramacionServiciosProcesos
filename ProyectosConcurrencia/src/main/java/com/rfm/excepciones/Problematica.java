package com.rfm.excepciones;

public class Problematica extends Thread {
  private final int n;

  public Problematica(int n) {
    this.n = n;
  }

  public void run() {
    int resultado = 100 / n;
  }

}
