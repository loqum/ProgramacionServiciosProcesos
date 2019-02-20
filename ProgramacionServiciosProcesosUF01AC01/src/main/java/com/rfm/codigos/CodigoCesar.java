package com.rfm.codigos;

import org.apache.log4j.Logger;

import com.rfm.factory.Encriptador;
import com.rfm.utils.Constantes;

public class CodigoCesar implements Encriptador {

  private static final Logger LOG = Logger.getLogger(CodigoCesar.class);
  private static final int SIZE = Constantes.getAbecedario().length();

  /**
   * Cifra una palabra o frase aplicando el código de cifrado César. El método
   * determina, en primer lugar, la posición que ocupa (si existe) cada letra de
   * la frase introducida en el abecedario. Luego, para poder concatenar cada
   * resultado asigna a un StringBuilder, siempre que la posición sea igual o
   * superior a 0, el caracter del abecedario comprendido en la posición que se
   * determine, así va reemplazando letra por letra. Para evitar que llegue más
   * allá del final del abecedario hacemos el módulo del tamaño del abecedario.
   *
   * @param frase  Frase que se desea cifrar.
   * @param numero Número de saltos.
   * @return frase Frase cifrada.
   */

  @Override
  public String cifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(
                Constantes.getAbecedario().charAt((posicionLetraCifrado + Integer.parseInt(clave)) % SIZE))
            : builder.append(frase.toUpperCase().charAt(i));

      } catch (RuntimeException e) {
        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

  /**
   * Descifra la palabra o frase introducida que ha sido cifrada mediante código
   * Cesar. La operación que realiza este método es parecido a su opuesto. En este
   * caso, el único cambio es restar la posición al número introducido, en lugar
   * de sumarlos, y sumar la cantidad total de letras del abecedario [27], para
   * evitar, como en el cifrado, que el índice se pase, esta vez por delante.
   * 
   * @param frase  Frase que se desea descifrar
   * @param numero Número de saltos.
   * @return frase Frase descifrada.
   */

  @Override
  public String descifrar(String frase, String clave) {
    StringBuilder builder = new StringBuilder();
    int posicionLetraCifrado = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicionLetraCifrado = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      try {

        builder = posicionLetraCifrado >= 0
            ? builder.append(Constantes.getAbecedario()
                .charAt((posicionLetraCifrado - Integer.parseInt(clave) + SIZE) % SIZE))
            : builder.append(frase.toUpperCase().charAt(i));

      } catch (RuntimeException e) {

        LOG.error("Error: " + e.getLocalizedMessage());
        throw e;
      }

    }

    return builder.toString();
  }

}
