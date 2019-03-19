package com.rfm.threads;

public class TareaDos extends Tarea implements Runnable{
  @Override
  public void run() {

    for (int i = 0; i < 20; i++) {
      System.out.println("Hilo2");
    }

  }

}
