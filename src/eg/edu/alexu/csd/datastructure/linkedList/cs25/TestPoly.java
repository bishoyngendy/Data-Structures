package eg.edu.alexu.csd.datastructure.linkedList.cs25;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Assignment 4 : TestPolynomial.
 * @author Bishoy Nader
 */
public class TestPoly {

  /**
   * Constant.
   */
  public static final int MAGICV_5 = -5;
  /**
   * Constant.
   */
  public static final int MAGIC_10 = 10;
  /**
   * Constant.
   */
  public static final int MAGIC_61 = 61;
  /**
   * Constant.
   */
  public static final int MAGIC_7 = 7;
  /**
   * Constant.
   */
  public static final int MAGIC_6 = 6;
  /**
   * Constant.
   */
  public static final int MAGIC_5 = 5;
  /**
   * Constant.
   */
  public static final int MAGIC_4 = 4;
  /**
   * Constant.
   */
  public static final int MAGIC_3 = 3;
  /**
   * Polynomial Basic Tests.
   */
  @Test
  public void testSetPrintPoly() {
    int[][] list = {{MAGIC_5, MAGIC_3}, {MAGIC_3, 2},
                    {MAGIC_7, 1}, {MAGICV_5, 0}};
    Polynomial poly = new Polynomial();
    poly.setPolynomial('A', list);
    assertEquals("Set & Print Poly", "5X^3 + 3X^2 + 7X^1 - 5", poly.print('A'));
  }

  /**
   * Polynomial Basic Tests.
   */
  @Test
  public void testEvaluate() {
    int[][] list = {{MAGIC_5, MAGIC_3}, {MAGIC_3, 2},
                    {MAGIC_7, 1}, {MAGICV_5, 0}};
    Polynomial poly = new Polynomial();
    poly.setPolynomial('A', list);
    float result = poly.evaluatePolynomial('A', 0);
    assertEquals("Poly Evaluate {0}", MAGICV_5, result, 0);
    result = poly.evaluatePolynomial('A', 1);
    assertEquals("Poly Evaluate {0}", MAGIC_10, result, 0);
    result = poly.evaluatePolynomial('A', 2);
    assertEquals("Poly Evaluate {0}", MAGIC_61, result, 0);
  }
}
