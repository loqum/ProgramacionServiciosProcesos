package com.rfm.threads;

public class Tarea implements Runnable {
  
  @Override
  public void run() {

    for (int i = 0; i < 10; i++) {
      System.out.println("Hilo1");
    }

  }

}
