package com.rfm.condition;

public class Proveedor extends Thread {
  
  private final int id;
  private final Fabrica fabrica;
  
  public Proveedor(int id, Fabrica fabrica) {
    this.id = id;
    this.fabrica = fabrica;
  }
  
  public void run() {
    while (true) {
      try {
        int t = (int) (Math.random() * 3);
        Thread.sleep(t * 1000);
        fabrica.put(id);
      } catch (InterruptedException e) {
      }
    }
  }

}
