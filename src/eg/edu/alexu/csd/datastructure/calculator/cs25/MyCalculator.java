package eg.edu.alexu.csd.datastructure.calculator.cs25;

import eg.edu.alexu.csd.datastructure.calculator.ICalculator;

/**
 * Assignment 1 : Calculator.
 * @author Bishoy Nader
 */
public class MyCalculator implements ICalculator {

  /**
   * Main Function.
   * @param args when running from the terminal
   */
  public static void main(final String[] args) { }

  @Override
  public int add(final int firstNum, final int secondNum) {
    return firstNum + secondNum;
  }

  @Override
  public float divide(final int firstNum, final int secondNum) {
    return (float) firstNum / secondNum;
  }
}
