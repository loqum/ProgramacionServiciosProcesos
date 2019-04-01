package com.rfm.callable;

import java.util.concurrent.Callable;

public class Filtro implements Callable<Integer> {

  private int[] fila;

  public Filtro(int[] fila) {
    this.fila = fila;
  }

  @Override
  public Integer call() throws Exception {
    int total = 0;
    for (int n : fila) {
      total += n;
    }
    return total;
  }

}
