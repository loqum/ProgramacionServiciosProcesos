package com.rfm.main;

import com.rfm.cifrado.AlgoritmoCesar;

public class MainClass {

  public static void main(String[] args) {

//    System.out.println(AlgoritmoCesar.cifradoCesar("Siempre que nos vemos me miras a los ojos y me dices: ojala", 3));
//
//    System.out.println(AlgoritmoCesar.descifradoCesar("VLHOSUH TXH PRV YHORV OH OLUDV D ÑRV RMRV B OH GLFHV: RMDÑD", 3));
    
    System.out.println(AlgoritmoCesar.cifradoCesar("Z", 3));

    System.out.println(AlgoritmoCesar.descifradoCesar("A", 3));

  }

}
