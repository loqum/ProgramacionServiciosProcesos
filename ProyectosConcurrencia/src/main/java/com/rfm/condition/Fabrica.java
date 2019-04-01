package com.rfm.condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fabrica extends Thread {

  private final Random random = new Random();
  private final Ficha[] fichas;
  private final Lock proveedores;
  private final Condition[] conditions;

  public Fabrica(Ficha[] fichas) {
    this.fichas = fichas;
    proveedores = new ReentrantLock();
    conditions = new Condition[fichas.length];
    for (int i = 0; i < fichas.length; i++) {
      conditions[i] = proveedores.newCondition();
    }
  }

  public void run() {
    try {
      for (int i = 0; i < 10; i++) {
        foto();
        int caso = random.nextInt(fichas.length);
        get(caso);
        System.out.println();
      }
    } catch (InterruptedException e) {
      // TODO: handle exception
    }
    System.exit(0);
  }

  private void get(int i) throws InterruptedException {
    Ficha ficha = fichas[i];
    String id = ficha.getNombre();
    Condition condition = conditions[i];
    proveedores.lock();

    while (ficha.getExistencias() == 0)
      condition.await();
    ficha.decrementarExistencias();

    if (ficha.getExistencias() < ficha.getMin()) {
      System.out.println("necesito " + id);
      condition.signal();
    }

    proveedores.unlock();

  }

  protected void put(int i) throws InterruptedException {
    Ficha ficha = fichas[i];
    String id = ficha.getNombre();
    Condition condition = conditions[i];
    proveedores.lock();

    while (ficha.getExistencias() >= ficha.getMax())
      condition.await();
    System.out.println("put: " + id);
    ficha.incrementarExistencias();
    condition.signal();
    proveedores.unlock();
  }
  
  private void foto() {
    proveedores.lock();
    for (Ficha ficha : fichas) {
      
      System.out.print(ficha.getNombre());
      System.out.print(": ");
      System.out.printf("{%d, %d} ", ficha.getMin(), ficha.getMax());
      for (int i = 0; i < ficha.getExistencias(); i++)
        System.out.println("[] ");
      System.out.println();
    }
    proveedores.unlock();
  }

}
