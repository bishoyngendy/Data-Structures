package test;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;
import eg.edu.alexu.csd.datastructure.hangman.cs25.MyHangman;

import java.util.Scanner;

/**
 * Hangman User Interface for the application.
 * @author Bishoy Nader
 */
public class Hangman {

  /**
   * Constant.
   */
  private static final int CONST_5 = 5;
  /**
   * Testing Word.
   */
  static String[] directionary = new String[] {"Kirolos"};

  /**
   * This is the main function of the class.
   * @param args string parameter when running the program
   */
  public static void main(final String[] args) {
    // Here you will create an object of your class
    IHangman hangman = new MyHangman();
    hangman.setDictionary(directionary);
    hangman.setMaxWrongGuesses(CONST_5);
    String secret = hangman.selectRandomSecretWord();
    Scanner input = new Scanner(System.in);  // Get user input
    Character guess = '\u0000';
    do {
      String result = hangman.guess(guess);
      if (result == null) {
        System.out.println("Fail! correct answer = '" + secret + "'"); // fail
        return;
      }
      System.out.println(result);
      if (!result.contains("-")) {
        System.out.println("Well Done!");  // win
        return;
      }
      guess = input.next().toUpperCase().charAt(0);
    } while (true);
  }
}
