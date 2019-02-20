package com.rfm.main;

import com.rfm.factory.Encriptador;
import com.rfm.factory.FactoriaEncriptador;

import java.util.Scanner;

public class MainClass {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    String frase = null;
    String clave = null;

    System.out.println("Introduce el algoritmo [cesar] o [vigenere]: ");
    String algoritmo = scanner.nextLine();

    Encriptador encriptador = FactoriaEncriptador.getInstance(algoritmo);

    System.out.println("Selecciona el tipo de operacion [cifrar] o [descifrar]: ");
    String operacion = scanner.nextLine();

    switch (operacion) {

    case "cifrar":

      System.out.println("Introduce una frase: ");
      frase = scanner.nextLine();

      System.out.println("Introduce un número: ");
      clave = scanner.nextLine();

      System.out.println(encriptador.cifrar(frase, clave));

      break;

    case "descifrar":

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
