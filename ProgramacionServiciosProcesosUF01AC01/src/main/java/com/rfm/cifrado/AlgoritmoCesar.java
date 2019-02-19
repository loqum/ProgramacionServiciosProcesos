package com.rfm.cifrado;

import com.rfm.utils.Constantes;

public class AlgoritmoCesar {

  /**
   * Cifra una palabra o frase aplicando el algoritmo de cifrado César. El método
   * determina, en primer lugar, la posición que ocupa (si existe) cada letra de
   * la frase introducida en el abecedario. Luego, para poder concatenar cada
   * resultado asigna a un StringBuilder, siempre que la posición sea igual o
   * superior a 0, el caracter del abecedario comprendido en la posición que se
   * determine, así va reemplazando letra por letra. Para evitar que llegue más
   * allá del final del abecedario hacemos el módulo del tamaño del abecedario.
   *
   * @param frase  Frase que se desea cifrar.
   * @param numero Numero de saltos.
   * @return frase cifrada.
   */

  public static String cifradoCesar(String frase, int numero) {

    StringBuilder builder = new StringBuilder();
    int posicion = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicion = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      builder = posicion >= 0
          ? builder.append(Constantes.getAbecedario()
              .charAt((posicion + numero) % Constantes.getAbecedario().length()))
          : builder.append(frase.toUpperCase().charAt(i));

    }

    return builder.toString();

  }

  public static String descifradoCesar(String frase, int numero) {

    StringBuilder builder = new StringBuilder();
    int posicion = 0;

    for (int i = 0; i < frase.length(); i++) {

      posicion = Constantes.getAbecedario().indexOf(frase.toUpperCase().charAt(i));

      builder = posicion >= 0
          ? builder.append(Constantes.getAbecedario()
              .charAt((posicion - numero) % Constantes.getAbecedario().length()))
          : builder.append(frase.toUpperCase().charAt(i));

    }

    return builder.toString();

  }

}
