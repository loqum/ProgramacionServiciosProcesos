package com.rfm.codigos;

import org.apache.log4j.Logger;

import com.rfm.factory.Encriptador;
import com.rfm.utils.Constantes;

public class CodigoCesarMod implements Encriptador {

  private static final Logger LOG = Logger.getLogger(CodigoCesar.class);
  private static final int SIZE = Constantes.getAbecedario().length();

  /**
   * En este método hay una pequeña modificación: a la clave que el usuario
   * introduce se le suma el índice que ocupa la última letra de la frase
   * introducida. Así, si el usuario introduce la frase [MADRID] y la clave [5],
   * esta será finalmente [8], puesto que [5 + 3 = 8]
   */

  @Override
  public String cifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;
    int claveSecreta = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));
      claveSecreta = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(frase.length() - 1));

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
      claveSecreta = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(frase.length() - 1));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(
                Constantes.getAbecedario().charAt((posicionLetraCifrado - Integer.parseInt(clave) - claveSecreta + SIZE) % SIZE))
            : builder.append(frase.toUpperCase().charAt(i));

      } catch (RuntimeException e) {

        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

}
