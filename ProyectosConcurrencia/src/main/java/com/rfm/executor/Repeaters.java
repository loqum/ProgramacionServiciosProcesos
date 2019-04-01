package com.rfm.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repeaters {
  private static final int NUMBER_THREADS = 10;
  private static final int POOL_SIZE = 4;

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
    for (int i = 0; i < NUMBER_THREADS; i++) {
      String id = String.valueOf((char) ('A' + i));
      int count = (int) (Math.random() * 5);
      Runnable repeater = new Repeater(id, count);
      pool.execute(repeater);
    }
    System.out.println("Repeaters...");
  }

}
