package com.rfm.main;

import com.rfm.factory.Encriptador;
import com.rfm.factory.EncriptadorArchivo;
import com.rfm.factory.FactoryEncriptador;
import com.rfm.factory.FactoryEncriptadorArchivo;
import com.rfm.utils.Constantes;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class MainClass {

  private static Scanner scanner = new Scanner(System.in);
  private static final Logger LOG = Logger.getLogger(MainClass.class);

  public static void main(String[] args) throws IOException {

    String frase = null;
    String clave = null;
    String respuestaPregunta = null;
    String nombreArchivo = null;
    String respuestaNombreArchivo = null;

    System.out.println(Constantes.getMensajeAlgoritmo());
    String algoritmo = scanner.nextLine();

    Encriptador encriptador = FactoryEncriptador.getInstance(algoritmo);

    System.out.println(Constantes.getMensajeOperacion());
    String operacion = scanner.nextLine();

    switch (operacion) {

    case "1":

      System.out.println(Constantes.getIntroduceFrase());
      frase = scanner.nextLine();

      System.out.println(Constantes.getIntroduceClave());
      clave = scanner.nextLine();

      System.out.println(Constantes.getPreguntaArchivo());
      respuestaPregunta = scanner.nextLine();

      if (respuestaPregunta.equalsIgnoreCase("si")) {

        System.out.println(Constantes.getRutaArchivo());
        nombreArchivo = scanner.nextLine();

        EncriptadorArchivo archivo = FactoryEncriptadorArchivo.getInstance();
        archivo.escribirArchivo(nombreArchivo.concat(".txt"), encriptador.cifrar(frase, clave));
      }

      LOG.info("Resultado: " + encriptador.cifrar(frase, clave));

      break;

    case "2":

      System.out.println(Constantes.getIntroduceFrase());
      frase = scanner.nextLine();

      System.out.println(Constantes.getIntroduceClave());
      clave = scanner.nextLine();

      System.out.println(Constantes.getPreguntaArchivo());
      respuestaPregunta = scanner.nextLine();

      if (respuestaPregunta.equalsIgnoreCase("si")) {

        System.out.println(Constantes.getRutaArchivo());
        nombreArchivo = scanner.nextLine();

        EncriptadorArchivo archivo = FactoryEncriptadorArchivo.getInstance();
        archivo.escribirArchivo(nombreArchivo.concat(".txt"), encriptador.descifrar(frase, clave));
      }

      LOG.info("Resultado: " + encriptador.descifrar(frase, clave));

      break;

    case "3":

      System.out.println("Especifica la ruta y nombre del archivo: ");
      respuestaNombreArchivo = scanner.nextLine();

      System.out.println(Constantes.getIntroduceClave());
      clave = scanner.nextLine();

      EncriptadorArchivo cifrarArchivo = FactoryEncriptadorArchivo.getInstance();

      encriptador.cifrar(cifrarArchivo.leerArchivo(respuestaNombreArchivo), clave);

      LOG.info("Resultado: "
          + encriptador.cifrar(cifrarArchivo.leerArchivo(respuestaNombreArchivo), clave));

      break;

    case "4":

      System.out.println("Especifica la ruta y nombre del archivo: ");
      respuestaNombreArchivo = scanner.nextLine();

      System.out.println(Constantes.getIntroduceClave());
      clave = scanner.nextLine();

      EncriptadorArchivo descifrarArchivo = FactoryEncriptadorArchivo.getInstance();

      encriptador.descifrar(descifrarArchivo.leerArchivo(respuestaNombreArchivo), clave);

      LOG.info("Resultado: "
          + encriptador.descifrar(descifrarArchivo.leerArchivo(respuestaNombreArchivo), clave));

      break;

    default:

      LOG.error("Introducido parametro incorrecto");

      break;
    }

  }

}
