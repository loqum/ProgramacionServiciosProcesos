package com.rfm.semaforobinario;

import java.util.concurrent.Semaphore;

public class IncrementadorLento extends Thread {
  private final String id;
  private final ContadorCompartido contadorCompartido;

  private static Semaphore semaforo = new Semaphore(1);

  public IncrementadorLento(String id, ContadorCompartido contadorCompartido) {
    this.id = id;
    this.contadorCompartido = contadorCompartido;
  }

  @Override
  public void run() {
    try {
      semaforo.acquire();
    } catch (InterruptedException e) {
      System.err.println(id + ": " + e);
    }

    try {
      int valor = contadorCompartido.getN(id);
      valor++;
      Thread.sleep(1000);
      contadorCompartido.setN(id, valor);
    } catch (InterruptedException e) {
      System.err.println(id + ": " + e);
    } finally {
      semaforo.release();
    }
  }

}
