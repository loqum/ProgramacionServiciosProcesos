package com.rfm.cifrado;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgoritmoCesarTest {

  @Test
  public void testCifradoCesar() {

    String test1 = AlgoritmoCesar.cifradoCesar("Hola", 5);
    String test2 = AlgoritmoCesar.cifradoCesar("Lorem ipsum", 22);
    String test3 = AlgoritmoCesar.cifradoCesar("Habia una vez...", 5);

    String expected1 = "MTPF";
    String expected2 = "GKNZH DLÑPH";
    String expected3 = "MFGNF ZRF AJE...";

    assertEquals(expected1, test1);
    assertEquals(expected2, test2);
    assertEquals(expected3, test3);
  }

}
