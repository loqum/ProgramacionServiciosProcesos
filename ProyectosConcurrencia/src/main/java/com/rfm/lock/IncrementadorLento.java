package com.rfm.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementadorLento extends Thread {

  private final String id;
  private final ContadorCompartido contadorCompartido;

  private static Lock llave = new ReentrantLock();

  public IncrementadorLento(String id, ContadorCompartido contadorCompartido) {
    this.id = id;
    this.contadorCompartido = contadorCompartido;
  }

  @Override
  public void run() {
    llave.lock();
    try {
      int valor = contadorCompartido.getN(id);
      valor++;
      sleep(1000);
      contadorCompartido.setN(id, valor);
    } catch (InterruptedException e) {
      System.err.println(id + ": " + e);
    } finally {
      llave.unlock();
    }

  }
}
