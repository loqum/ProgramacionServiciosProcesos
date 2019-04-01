package com.rfm.condition;

public class Ficha {

  private final int indice;
  private final String nombre;
  private final int min;
  private final int max;
  private int existencias;

  public Ficha(int indice, String nombre, int min, int max, int existencias) {
    this.indice = indice;
    this.nombre = nombre;
    this.min = min;
    this.max = max;
    this.existencias = existencias;
  }

  public int getExistencias() {
    return existencias;
  }

  public void incrementarExistencias() {
    this.existencias++;
  }

  public void decrementarExistencias() {
    this.existencias--;
  }

  public int getIndice() {
    return indice;
  }

  public String getNombre() {
    return nombre;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

}
