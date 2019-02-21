package com.rfm.codigos;

import org.apache.log4j.Logger;

import com.rfm.factory.Encriptador;
import com.rfm.utils.Constantes;

public class CodigoVigenereMod implements Encriptador {

  private static final Logger LOG = Logger.getLogger(CodigoVigenereMod.class);
  private static final int SIZE = Constantes.getAbecedario().length();

  /**
   * El cambio aqui aplicado se basa en la alteracion de la clave que introduce el
   * usuario: la clave se invierte y se lee de detrás hacia adelante. Es decir, si
   * el usuario introduce [COCO] la clave será finalmente [OCOC].
   */

  @Override
  public String cifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;
    int posicionLetraClave = 0;

    String claveMod = new StringBuilder(clave).reverse().toString();

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));
      posicionLetraClave = Constantes.getAbecedario().indexOf(claveMod.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario().charAt((posicionLetraCifrado + posicionLetraClave) % SIZE))
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

    String claveMod = new StringBuilder(clave).reverse().toString();

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));
      posicionLetraClave = Constantes.getAbecedario().indexOf(claveMod.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder
                .append(Constantes.getAbecedario().charAt((posicionLetraCifrado - posicionLetraClave + SIZE) % SIZE))
            : builder.append(clave.toUpperCase().charAt(i));

      } catch (RuntimeException e) {
        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

}
