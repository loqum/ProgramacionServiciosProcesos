package com.rfm.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Matriz {

  private static final int FILAS = 40;
  private static final int COLUMNAS = 4;

  private static final int POOL_SIZE = 3;

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int[][] matriz = new int[FILAS][COLUMNAS];

    for (int fila = 0; fila < FILAS; fila++) {
      for (int columna = 0; columna < COLUMNAS; columna++) {
        matriz[fila][columna] = (int) (Math.random() * 1000);
      }
    }

    Future<Integer>[] resultados = new Future[FILAS];

    ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    for (int fila = 0; fila < FILAS; fila++) {
      Callable<Integer> filtro = new Filtro(matriz[fila]);
      Future<Integer> future = pool.submit(filtro);
      resultados[fila] = future;
    }
    
    int suma = 0;
    
    for (int fila = 0; fila < FILAS; fila++) {
      Future<Integer> future = resultados[fila];
      Integer parcial = future.get();
      suma += parcial;
    }
    
    System.out.println("Suma total: " + suma);
    pool.shutdown();
  }

}
