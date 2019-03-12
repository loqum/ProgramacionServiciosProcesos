package com.rfm.threads;

public class Programa {

  public static void main(String[] args) {
    Tarea tarea = new Tarea();
    tarea.start();
    System.out.println("Soy el hilo principal");
    System.out.println("Fin del hilo principal");
  }

}
