package com.rfm.excepciones;

import java.lang.Thread.UncaughtExceptionHandler;

public class Problemas {
  
  public static void main(String[] args) {
    UncaughtExceptionHandler uncaughtExceptionHandler = new Manejador();
    Problematica problematica = new Problematica(0);
    problematica.setName("Tarea que da problemas");
    problematica.setUncaughtExceptionHandler(uncaughtExceptionHandler);
    problematica.start();
  }

}
