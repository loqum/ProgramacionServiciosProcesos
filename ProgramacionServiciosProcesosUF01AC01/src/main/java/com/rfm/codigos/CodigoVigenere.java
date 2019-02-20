package com.rfm.codigos;

import org.apache.log4j.Logger;

import com.rfm.factory.Encriptador;
import com.rfm.utils.Constantes;

public class CodigoVigenere implements Encriptador {

  private static final Logger LOG = Logger.getLogger(CodigoVigenere.class);
  private static final int SIZE = Constantes.getAbecedario().length();

  @Override
  public String cifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;
    int posicionLetraClave = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));
      posicionLetraClave = Constantes.getAbecedario().indexOf(clave.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario()
                .charAt((posicionLetraCifrado + posicionLetraClave) % SIZE))
            : builder.append(clave.toUpperCase().charAt(i));

      } catch (RuntimeException e) {
        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

  @Override
  public String descifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;
    int posicionLetraClave = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));
      posicionLetraClave = Constantes.getAbecedario().indexOf(clave.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario()
                .charAt((posicionLetraCifrado - posicionLetraClave + SIZE) % SIZE))
            : builder.append(clave.toUpperCase().charAt(i));

      } catch (RuntimeException e) {
        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

}
