package com.rfm.observer;

import java.time.LocalDate;

public class MainClass {

  public static void main(String[] args) {
    Producto producto = new Producto("Martillo", "Sirve para clavar clavos", 12.95F, 0);
        
    Cliente cliente = new Cliente("012", "Gustavo", "Diaz Carrillo", "gusta@gmail.com", LocalDate.of(1992, 2, 15));
    
    producto.addObserver(cliente);
    
    producto.setStock(1);
    producto.setPrecio(10.95F);
    

  }

}
