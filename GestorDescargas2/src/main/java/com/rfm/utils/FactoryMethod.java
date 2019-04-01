package com.rfm.utils;

public class FactoryMethod {

  private FactoryMethod() {

  }

  public static Factory getInstance() {
    return new FactoryTxt();
  }

}
