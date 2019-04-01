package com.rfm.utils;

public class FactoryMethod {

  public static Factory getInstance(String instanceName) {
    return new FactoryTxt();
  }

}
