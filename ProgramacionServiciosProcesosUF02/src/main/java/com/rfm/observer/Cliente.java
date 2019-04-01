package com.rfm.observer;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

public class Cliente implements Observer {

  private String codigo;
  private String nombre;
  private String apellidos;
  private String email;
  private LocalDate fechaNacimiento;

  public Cliente() {

  }

  public Cliente(String codigo, String nombre, String apellidos, String email,
      LocalDate fechaNacimiento) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg.equals("stock")) {
      System.out.println("Cliente notificado de un cambio de stock");
    }
    
    if (arg.equals("precio")) {
      System.out.println("Cliente notificado de un cambio de precio");
    }

  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

}
