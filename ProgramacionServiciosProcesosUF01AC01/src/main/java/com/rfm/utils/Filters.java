package com.rfm.utils;

import java.util.function.IntPredicate;
import java.util.stream.LongStream;

public class Filters {

  private Filters() {

  }

  public static boolean isPrime(int numero) {
    IntPredicate predicate = num -> LongStream.rangeClosed(2, numero / 2).noneMatch(i -> numero % i == 0);
    return predicate.test(numero);

  }

}
