package com.rfm.threads;

public class Programa {

  public static void main(String[] args) {

    try {
      Tarea tarea = new Tarea();
      TareaDos tareaDos = new TareaDos();
      Thread hilo = new Thread(new Runnable() {

        @Override
        public void run() {
          for (int i = 0; i < 10; i++) {
            System.out.println("Soy un hilo en clase anónima");

          }

        }
      });
      
      Thread hilo1 = new Thread(tarea);
      Thread hilo2 = new Thread(tareaDos);
      
      hilo2.start();
      hilo2.join();
      Thread.sleep(2000);
      hilo.start();
      hilo1.start();
      hilo1.join();
      Thread.sleep(2000);
      System.out.println("Soy el hilo principal");
      System.out.println("Fin del hilo principal");

      

      
      
    } catch (InterruptedException e) {
      System.err.println("Error: " + e.getMessage());
    }

  }

}
