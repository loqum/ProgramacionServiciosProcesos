package com.rfm.threads;

public class TareaPar extends CuentaNumeros implements Runnable {

  @Override
  public void run() {
    cuentaNumerosPares();
    
  }

}
