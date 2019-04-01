package com.rfm.threads;

public class TareaImpar extends CuentaNumeros implements Runnable {

  @Override
  public void run() {
    cuentaNumerosImpares();

  }

}
