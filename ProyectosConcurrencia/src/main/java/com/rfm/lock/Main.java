package com.rfm.lock;

public class Main {

  public static void main(String[] args) {
    
    ContadorCompartido contadorCompartido = new ContadorCompartido();
    IncrementadorLento hilo1 = new IncrementadorLento("01", contadorCompartido);
    IncrementadorLento hilo2 = new IncrementadorLento("02", contadorCompartido);
    
    hilo1.start();
    hilo2.start();
    

  }

}
