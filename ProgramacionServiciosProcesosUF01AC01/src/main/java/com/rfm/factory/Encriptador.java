package com.rfm.factory;

public interface Encriptador {

  public String cifrar(String frase, String clave);

  public String descifrar(String frase, String clave);

}
