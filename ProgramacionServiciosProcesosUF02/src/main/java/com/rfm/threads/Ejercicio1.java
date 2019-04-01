package com.rfm.threads;

public class Ejercicio1 {

  public static void main(String[] args) {
    TareaPar tareaPar = new TareaPar();
    TareaImpar tareaImpar = new TareaImpar();

    Thread hilo1 = new Thread(tareaPar);
    Thread hilo2 = new Thread(tareaImpar);

    try {

      hilo1.start();
      hilo2.start();
      hilo1.interrupt();
      hilo2.join();


    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }

}
