package com.rfm.excepciones;

import java.lang.Thread.UncaughtExceptionHandler;

public class Manejador implements UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.err.println(t.getName() + ": " + e);

  }

}
