package com.rfm.utils;

public enum Estado {
  
  BLANK("");
  
  private final String value;
  
  Estado(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
