package com.rfm.semaforo_n_permisos;

import java.util.concurrent.Semaphore;

public class Tarea extends Thread {

  private Semaphore contador;

  public Tarea(Semaphore contador) {
    this.contador = contador;
  }

  public void run() {
    contador.release();
  }

}
