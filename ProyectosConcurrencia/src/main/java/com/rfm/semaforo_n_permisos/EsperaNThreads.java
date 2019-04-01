package com.rfm.semaforo_n_permisos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class EsperaNThreads {
  
  public static void main(String[] args) throws InterruptedException {
    Semaphore contador = new Semaphore(0);
    List<Tarea> tareas = new ArrayList<>();
    tareas.add(new Tarea(contador));
    for (Tarea tarea : tareas) {
      tarea.start();
    }
    
    contador.acquire(tareas.size());
  }

}
