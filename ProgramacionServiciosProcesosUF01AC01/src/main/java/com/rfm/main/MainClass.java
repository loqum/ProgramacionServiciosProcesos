package com.rfm.main;

import com.rfm.factory.Encriptador;
import com.rfm.factory.FactoriaEncriptador;
import com.rfm.utils.Constantes;

import java.util.Scanner;

public class MainClass {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    String frase = null;
    String clave = null;

    System.out.println(Constantes.getMensajeAlgoritmo());
    String algoritmo = scanner.nextLine();

    Encriptador encriptador = FactoriaEncriptador.getInstance(algoritmo);

    System.out.println(Constantes.getMensajeOperacion());
    String operacion = scanner.nextLine();

    switch (operacion) {

    case "1":

      System.out.println("Introduce una frase: ");
      frase = scanner.nextLine();

      System.out.println("Introduce un número: ");
      clave = scanner.nextLine();

      System.out.println(encriptador.cifrar(frase, clave));

      break;

    case "2":

      System.out.println("Introduce una frase: ");
      frase = scanner.nextLine();

      System.out.println("Introduce un número: ");
      clave = scanner.nextLine();

      System.out.println(encriptador.descifrar(frase, clave));

      break;

    default:

      System.out.println("Introducido parámetro incorrecto");

      break;
    }

  }

}
