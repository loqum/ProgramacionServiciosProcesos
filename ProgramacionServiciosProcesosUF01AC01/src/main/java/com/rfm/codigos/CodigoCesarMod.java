package com.rfm.codigos;

import java.util.stream.IntStream;

import org.apache.log4j.Logger;

import com.rfm.factory.Encriptador;
import com.rfm.utils.Constantes;
import com.rfm.utils.Filters;

public class CodigoCesarMod implements Encriptador {

  private static final Logger LOG = Logger.getLogger(CodigoCesar.class);
  private static final int SIZE = Constantes.getAbecedario().length();

  /**
   * En este metodo hay una pequeña modificacion: a la clave que el usuario
   * introduce se le suma el numero primo mas cercano por debajo de la clave
   * introducida por usuario. Asi, si el usuario introduce la clave [5], esta sera
   * finalmente [8], puesto que 3 es el numero primo mas cercano a 5 por debajo de
   * este numero.
   */

  @Override
  public String cifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;
    int claveSecreta = 0;

    claveSecreta = IntStream.rangeClosed(1, Integer.valueOf(clave)).filter(Filters::isPrime).max().getAsInt();

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario()
                .charAt((posicionLetraCifrado + Integer.parseInt(clave) + claveSecreta) % SIZE))
            : builder.append(frase.toUpperCase().charAt(i));

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
    int claveSecreta = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      claveSecreta = IntStream.rangeClosed(1, Integer.valueOf(clave)).filter(Filters::isPrime).max().getAsInt();

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario()
                .charAt((posicionLetraCifrado - Integer.parseInt(clave) - claveSecreta + SIZE) % SIZE))
            : builder.append(frase.toUpperCase().charAt(i));

      } catch (RuntimeException e) {

        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

}
