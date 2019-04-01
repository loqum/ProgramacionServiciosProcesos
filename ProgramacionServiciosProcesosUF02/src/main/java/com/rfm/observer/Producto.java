package com.rfm.observer;

import java.util.Observable;
import java.util.Observer;

public class Producto extends Observable {

  private String nombre;
  private String descripcion;
  private float precio;
  private int stock;

  private Observer observer;

  public Producto() {

  }

  public Producto(String nombre, String descripcion, float precio, int stock) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public float getPrecio() {
    return precio;
  }

  public void setPrecio(float precio) {
    this.precio = precio;
    notifyObserversPrice();
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
    notifyObserversStock();
  }

  public Observer getObserver() {
    return observer;
  }

  public void setObserver(Observer observer) {
    this.observer = observer;
  }

  @Override
  public void addObserver(Observer observer) {
    this.observer = observer;
  }

  public void notifyObserversStock() {
    if (observer != null) {
      observer.update(this, "stock");
    }
  }
  
  public void notifyObserversPrice() {
    if (observer != null) {
      observer.update(this, "precio");
    }
  }

}
