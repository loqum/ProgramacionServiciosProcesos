package com.rfm.threads;

import java.util.stream.IntStream;

public class CuentaNumeros {
  
  public void cuentaNumerosPares() {
    IntStream.rangeClosed(0, 100).filter(i -> i % 2 == 0).boxed().map(Object::toString).forEach(i -> System.out.println("Hilo 1: " + i));
  }
  
  public void cuentaNumerosImpares() {
    IntStream.rangeClosed(0, 100).filter(i -> i % 2 != 0).boxed().map(Object::toString).forEach(i -> System.err.println("Hilo 2: " + i));
  }

}
