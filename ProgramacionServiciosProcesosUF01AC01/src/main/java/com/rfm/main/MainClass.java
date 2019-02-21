package com.rfm.main;

import com.rfm.factory.Encriptador;
import com.rfm.factory.FactoriaEncriptador;
import com.rfm.utils.Constantes;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class MainClass {

  private static Scanner scanner = new Scanner(System.in);
  private static final Logger LOG = Logger.getLogger(MainClass.class);

  public static void main(String[] args) {

    String frase = null;
    String clave = null;

    LOG.info(Constantes.getMensajeAlgoritmo());
    String algoritmo = scanner.nextLine();

    Encriptador encriptador = FactoriaEncriptador.getInstance(algoritmo);

    LOG.info(Constantes.getMensajeOperacion());
    String operacion = scanner.nextLine();

    switch (operacion) {

    case "1":

      LOG.info("Introduce una frase: ");
      frase = scanner.nextLine();

      LOG.info("Introduce una clave: ");
      clave = scanner.nextLine();

      LOG.info("Resultado: " + encriptador.cifrar(frase, clave));

      break;

    case "2":

      LOG.info("Introduce una frase: ");
      frase = scanner.nextLine();

      LOG.info("Introduce una clave: ");
      clave = scanner.nextLine();

      LOG.info("Resultado: " + encriptador.descifrar(frase, clave));

      break;

    default:

      LOG.error("Introducido parametro incorrecto");

      break;
    }

  }

}
