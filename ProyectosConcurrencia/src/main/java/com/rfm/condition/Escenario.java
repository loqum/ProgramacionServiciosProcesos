package com.rfm.condition;

public class Escenario {
  public static void main(String[] args) {
    Ficha[] fichas = new Ficha[3];
    fichas[0] = new Ficha(0, "A", 2, 5, 2);
    fichas[1] = new Ficha(1, "B", 1, 2, 1);
    fichas[2] = new Ficha(2, "C", 2, 2, 2);
    
    Fabrica fabrica = new Fabrica(fichas);
    fabrica.start();
    
    for (int i = 0; i < fichas.length; i++) {
      Proveedor proveedor = new Proveedor(i, fabrica);
      proveedor.start();
    }

  }

}
