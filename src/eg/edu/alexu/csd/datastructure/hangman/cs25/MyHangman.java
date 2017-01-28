package eg.edu.alexu.csd.datastructure.hangman.cs25;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;

/**
 * Assignment 2 : Hangman.
 * @author Bishoy Nader
 */
public class MyHangman implements IHangman {
  /**
   * Constant.
   */
  public static final int MAGIC_100 = 100;
  /**
   * maximum wrong guesses.
   */
  int maxWrongGuesses = 0;
  /**
   * flag.
   */
  int found;
  /**
   * turns count.
   */
  int counter = 0;
  /**
   * Dictionary.
   */
  String[] locWords = new String[MAGIC_100];
  /**
   * Secret Word.
   */
  String secWord;
  /**
   * Returned Word with dashes.
   */
  String retWord;
  /**
   * Array size.
   */
  int arrSize;
  /**
   * initial array.
   */
  char[] initialArray;
  /**
   * new array returned.
   */
  char[] returnArrayModified;
  /**
   * new array setted.
   */
  char[] settedArrayModified;

  /**
   * Main Function.
   * @param args when running from the terminal
   */
  public static void main(final String[] args) { }

  @Override
  public void setDictionary(final String[] words) {
    for (int i = 0; i < words.length; i++) {
      this.locWords[i] = words[i];
    }
    this.arrSize = words.length;
  }

  @Override
  public String selectRandomSecretWord() {
    if (arrSize == 0) {
      return null;
    }
    int ind = (int) Math.random() * (arrSize - 1);
    secWord = new String(locWords[ind]);
    retWord = new String();
    for (int i = 0; i < secWord.length(); i++) {
      retWord += '-';
    }
    return secWord;
  }

  @Override
  public String guess(final Character guessedChar) {
    if (guessedChar == null) {
      return retWord;
    }
    if (secWord == null) {
      return null;
    }
    if (maxWrongGuesses == 0) {
      return null;
    }
    initialArray = secWord.toCharArray();
    returnArrayModified = new char[initialArray.length];
    if (counter == 0) {
      settedArrayModified = new char[initialArray.length];
    }
    counter++;
    found = 0;
    int iterator = 0;
    for (char ch : initialArray) {
      if (Character.toLowerCase(ch) == Character.toLowerCase(guessedChar)) {
        found = 1;
        returnArrayModified[iterator] = ch;
        settedArrayModified[iterator] = ch;
      } else {
        if (settedArrayModified[iterator] != '\u0000') {
          returnArrayModified[iterator] = settedArrayModified[iterator];
        } else {
          returnArrayModified[iterator] = '-';
          settedArrayModified[iterator] = '-';
        }
      }
      iterator++;
    }
    if (found == 0) {
      maxWrongGuesses--;
      if (maxWrongGuesses <= 0) {
        return null;
      }
    }
    retWord = new String(returnArrayModified);
    return retWord;
  }

  @Override
  public void setMaxWrongGuesses(final Integer max) {
    if (max != null) {
      maxWrongGuesses = max;
    }
  }
}
