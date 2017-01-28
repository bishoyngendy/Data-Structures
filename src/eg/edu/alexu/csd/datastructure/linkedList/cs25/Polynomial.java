package eg.edu.alexu.csd.datastructure.linkedList.cs25;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

/**
 * Assignment 4 : PolynomialSolver.
 * @author Bishoy Nader
 */
public class Polynomial implements IPolynomialSolver {

  /**
   * Constant.
   */
  public static final int MAGIC_100000000 = 100000000;
  /**
   * Constant.
   */
  public static final int MAGIC_100 = 100;
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
   * Polynomial A.
   */
  DoubleLinkedList firstLinkedList = new DoubleLinkedList();
  /**
   * Polynomial B.
   */
  DoubleLinkedList secondLinkedList = new DoubleLinkedList();
  /**
   * Polynomial C.
   */
  DoubleLinkedList thirdLinkedList = new DoubleLinkedList();
  /**
   * Polynomial R.
   */
  DoubleLinkedList resultLinkedList = new DoubleLinkedList();

  /**
   * main function of polynomial.
   * @param args string parameter when running from terminal
   */
  public static void main(final String[] args) {
    int choice;
    float value;
    char poly;
    char poly2;
    Polynomial myPolynomial = new Polynomial();
    while (true) {
      printOptions();
      @SuppressWarnings("resource")
      Scanner in = new Scanner(System.in);
      choice = in.nextInt();
      switch (choice) {
        case 1: // Set Polynomial
          System.out.println("Insert the variable name : A, B or C");
          poly = in.next().charAt(0);
          while (poly != 'A' && poly != 'B' && poly != 'C') {
            System.out.println("Polynomial (" + poly + ") is not found");
            poly = in.next().charAt(0);
          }
          System.out.println("Insert the polynomial terms in the form : \n "
                      + "(coeff1 , exponent1 ), (coeff2 , exponent2 ), ..");
          String inputString = in.next();
          int[][] arr = new int[MAGIC_100][2];
          int coff = 0;
          int exp = 0;
          Scanner scan = new Scanner(inputString);
          while (inputString.charAt(inputString.length() - 1) == ',') {
            inputString += in.next();
          }
          int ln = inputString.length();
          int index = 0;
          for (int i = 0; i < ln; i++) {
            Boolean found = false;
            if (inputString.charAt(i) != '(' || inputString.charAt(i) != ')'
                || inputString.charAt(i) != ' '
                || inputString.charAt(i) != ',') {
              if (inputString.charAt(i) == '-') {
                String tmp = new String("");
                tmp += inputString.charAt(++i);
                coff = -1 * Integer.parseInt(tmp);
                found = true;
              } else {
                String tmp = new String("");
                tmp += inputString.charAt(i);
                coff = Integer.parseInt(tmp);
                found = true;
              }
            }
            if (found) {
              String tmp = new String("");
              tmp += inputString.charAt(i + 2);
              i += MAGIC_3;
              exp = Integer.parseInt(tmp);
              arr[index][0] = coff;
              arr[index][1] = exp;
              index++;
            }
          }
          int[][] arrToSet = new int[index][2];
          for (int i = 0; i < index; i++) {
            arrToSet[i][0] = arr[i][0];
            arrToSet[i][1] = arr[i][1];
          }
          myPolynomial.setPolynomial(poly, arrToSet);
          scan.close();
          System.out.println(poly + " is set");
          break;
        case 2: // Print Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          poly = myPolynomial.simplify(poly, myPolynomial, in);
          System.out.println("Polynomial " + poly + " is : "
                             + myPolynomial.print(poly));
          break;
        case MAGIC_3: // Add Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          poly = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.validateRes(myPolynomial);
          poly2 = in.next().charAt(0);
          poly2 = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.setPolynomial('R', myPolynomial.add(poly, poly2));
          System.out.println("The result in R is : " + myPolynomial.print('R'));
          break;
        case MAGIC_4: // Subtract Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          poly = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.validateRes(myPolynomial);
          poly2 = in.next().charAt(0);
          poly2 = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.setPolynomial('R', myPolynomial.subtract(poly, poly2));
          System.out.println("The result in R is : " + myPolynomial.print('R'));
          break;
        case MAGIC_5: // Multiply Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          poly = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.validateRes(myPolynomial);
          poly2 = in.next().charAt(0);
          poly2 = myPolynomial.simplify(poly, myPolynomial, in);
          myPolynomial.setPolynomial('R', myPolynomial.multiply(poly, poly2));
          System.out.println("The result in R is : " + myPolynomial.print('R'));
          break;
        case MAGIC_6: // Evaluate Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          poly = myPolynomial.simplify(poly, myPolynomial, in);
          System.out.println("Insert the value of x");
          value = in.nextFloat();
          localEval(myPolynomial, poly, value);
          break;
        case MAGIC_7: // Clear Polynomial
          myPolynomial.validateRes(myPolynomial);
          poly = in.next().charAt(0);
          localClear(myPolynomial, poly);
          break;
        default:
          break;
      }
    }
  }

  /**
   * Clear the Polynomial.
   * @param polyParam reference to polynomial
   * @param poly character representing the polynomial
   */
  private static void localClear(final Polynomial polyParam, final char poly) {
    if (poly == 'A' || poly == 'B' || poly == 'C' || poly == 'R') {
      if (polyParam.getList(poly).size == 0) {
        System.out.println("Polynomial " + poly + "is already not set");
        System.out.println("=============================================");
      } else {
        polyParam.clearPolynomial(poly);
        System.out.println("Polynomial " + poly + "is Cleared");
        System.out.println("=============================================");
      }
    } else {
      System.out.println("Polynomial (" + poly + ") is not found");
    }
  }

  /**
   * Evaluate the polynomial.
   * @param polyParam reference to polynomial
   * @param poly character representing the polynomial
   * @param value value of x to evaluate.
   */
  private static void localEval(final Polynomial polyParam,
                                final char poly, final float value) {
    float ret = polyParam.evaluatePolynomial(poly, value);
    System.out.println(ret);
    System.out.println("=============================================");
  }

  /**
   * Printing the options each turn.
   */
  private static void printOptions() {
    System.out.println("Please choose an action");
    System.out.println("---------------------------------");
    System.out.println("1- Set a polynomial variable");
    System.out.println("2- Print the value of a polynomial variable");
    System.out.println("3- Add two polynomials");
    System.out.println("4- Subtract two polynomials");
    System.out.println("5- Multiply two polynomials");
    System.out.println("6- Evaluate a polynomial at some point");
    System.out.println("7- Clear a polynomial variable");
    System.out.println("=============================================");
  }

  @Override
  public void setPolynomial(final char poly, final int[][] terms) {
    setLocalPol(getList(poly), terms);
  }

  /**
   * Set Local Polynomial with value.
   * @param ll Polynomial list
   * @param terms Data to be set
   */
  private void setLocalPol(final DoubleLinkedList ll, final int[][] terms) {
    int prevExponent = MAGIC_100000000;
    ll.clear();
    for (int i = 0; i < terms.length; i++) {
      if (terms[i][1] < 0 || prevExponent < terms[i][1]) {
        throw new RuntimeException();
      }
      prevExponent = terms[i][1];
      Point tmp = new Point(terms[i][0], terms[i][1]);
      ll.add(tmp);
    }
  }

  @Override
  public String print(final char poly) {
    DoubleLinkedList list;
    StringBuilder pr = new StringBuilder();
    list = getList(poly);
    if (list.size == 0) {
      return null;
    }
    for (int i = 0; i < list.size; i++) {
      Point newPoint = (Point) list.get(i);
      if (newPoint.x == 0) {
        continue;
      }
      if (i != 0 && newPoint.x <= 0) {
        if (i == 0 && newPoint.x < 0) {
          pr.append("-");
        } else if (newPoint.x < 0) {
          pr.append("- ");
        } else {
          pr.append("+ ");
        }
      }
      if (newPoint.x != 1) {
        pr.append(Integer.toString(Math.abs((newPoint.x))));
      }
      if (newPoint.y != 0) {
        pr.append("X^" + Integer.toString(newPoint.y) + " ");
      }
    }
    return pr.toString();
  }

  @Override
  public void clearPolynomial(final char poly) {
    DoubleLinkedList list;
    list = getList(poly);
    list.clear();
  }

  @Override
  public float evaluatePolynomial(final char poly, final float value) {
    float rst = 0;
    DoubleLinkedList list = getList(poly);
    if (list.size == 0) {
      throw new RuntimeException();
    }
    for (int i = 0; i < list.size; i++) {
      Point newPoint = (Point) list.get(i);
      rst += newPoint.x * Math.pow(value, newPoint.y);
    }
    return rst;
  }

  @Override
  public int[][] add(final char poly1, final char poly2) {
    DoubleLinkedList result = new DoubleLinkedList();
    DoubleLinkedList list1 = getList(poly1);
    DoubleLinkedList list2 = getList(poly2);
    if (list1.size == 0 || list2.size == 0) {
      throw new RuntimeException();
    }
    for (int i = 0, k = 0; i < list1.size || k < list2.size;) {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(0, 0);
      if (i < list1.size) {
        p1 = (Point) list1.get(i);
      }
      if (k < list2.size) {
        p2 = (Point) list2.get(k);
      }
      if (p1.y == p2.y) {
        result.add(new Point(p1.x + p2.x, p1.y));
        i++;
        k++;
      } else if (p1.y > p2.y) {
        result.add(new Point(p1.x, p1.y));
        i++;
      } else if (p1.y < p2.y) {
        result.add(new Point(p2.x, p2.y));
        k++;
      }
    }
    int[][] returnArr = new int[result.size][2];
    resultLinkedList = result;
    for (int i = 0; i < result.size; i++) {
      Point newPoint = (Point) result.get(i);
      returnArr[i][0] = newPoint.x;
      returnArr[i][1] = newPoint.y;
    }
    return returnArr;
  }

  @Override
  public int[][] subtract(final char poly1, final char poly2) {
    DoubleLinkedList result = new DoubleLinkedList();
    DoubleLinkedList list1 = getList(poly1);
    DoubleLinkedList list2 = getList(poly2);
    if (list1.size == 0 || list2.size == 0) {
      throw new RuntimeException();
    } else if (list1 == list2) {
      return new int[][] {{0, 0}};
    }
    for (int i = 0, k = 0; i < list1.size || k < list2.size;) {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(0, 0);
      if (i < list1.size) {
        p1 = (Point) list1.get(i);
      }
      if (k < list2.size) {
        p2 = (Point) list2.get(k);
      }
      if ((p1.y == p2.y) && (p1.x != p2.x)) {
        result.add(new Point(p1.x - p2.x, p1.y));
        i++;
        k++;
      } else if (p1.y > p2.y) {
        result.add(new Point(p1.x, p1.y));
        i++;
      } else if (p1.y < p2.y) {
        result.add(new Point(0 - p2.x, p2.y));
        k++;
      } else {
        i++;
        k++;
      } // skipping their output is zero
    }
    int[][] returnArr = new int[result.size][2];
    resultLinkedList = result;
    for (int i = 0; i < result.size; i++) {
      Point newPoint = (Point) result.get(i);
      returnArr[i][0] = newPoint.x;
      returnArr[i][1] = newPoint.y;
    }
    return returnArr;
  }

  @Override
  public int[][] multiply(final char poly1, final char poly2) {
    DoubleLinkedList result = new DoubleLinkedList();
    DoubleLinkedList list1 = getList(poly1);
    DoubleLinkedList list2 = getList(poly2);
    if (list1.size == 0 || list2.size == 0) {
      throw new RuntimeException();
    }
    for (int i = 0; i < list1.size; i++) {
      Point p1 = (Point) list1.get(i);
      for (int k = 0; k < list2.size; k++) {
        Point p2 = (Point) list2.get(k);
        Point rst = new Point(p1.x * p2.x, p1.y + p2.y);
        Boolean found = false;
        for (int s = 0; s < result.size; s++) {
          Point tmp = (Point) result.get(s);
          if (rst.y == tmp.y) {
            result.set(s, new Point(tmp.x + rst.x, rst.y));
            found = true;
            break;
          }
        }
        if (!found) {
          result.add(rst);
        }
      }
    }
    int[][] returnArr = new int[result.size][2];
    resultLinkedList = result;
    for (int i = 0; i < result.size; i++) {
      Point newPoint = (Point) result.get(i);
      returnArr[i][0] = newPoint.x;
      returnArr[i][1] = newPoint.y;
    }
    Comparator<Object> comp = new Comparator<Object>() {
      @Override
      public int compare(final Object arg0, final Object arg1) {
        int[] c1 = (int[]) arg0;
        int[] c2 = (int[]) arg1;
        if (c1[1] < c2[1]) {
          return 1;
        } else {
          return -1;
        }
      }
    };
    Arrays.sort(returnArr, comp);
    return returnArr;
  }

  /**
   * Gets the required Polynomial.
   * @param poly The Polynomial name
   * @return the required polynomial
   */
  private DoubleLinkedList getList(final char poly) {
    DoubleLinkedList list;
    switch (poly) {
      case 'A':
        list = firstLinkedList;
        break;
      case 'B':
        list = secondLinkedList;
        break;
      case 'C':
        list = thirdLinkedList;
        break;
      case 'R':
        list = resultLinkedList;
        break;
      default:
        throw new RuntimeException();
    }
    return list;
  }
  /**
   * Simplification.
   * @param myPolynomial instance of class
   */
  private void validateRes(final Polynomial myPolynomial) {
    if (myPolynomial.getList('R').size == 0) {
      System.out.println("Insert the variable name : A, B or C");
    } else {
      System.out.println("Insert the variable name : A, B, C or R");
    }
  }

  /**
   * Simplify the main function.
   * @param poly polynomial name
   * @param myPolynomial instance
   * @param in Scanner object
   * @return the char again
   */
  private char simplify(final char poly, final Polynomial myPolynomial,
                        final Scanner in) {
    char ret = poly;
    while (ret != 'A' && ret != 'B' && ret != 'C' && ret != 'R') {
        System.out.println("Polynomial (" + ret + ") is not found");
        myPolynomial.validateRes(myPolynomial);
        ret = in.next().charAt(0);
    }
    while (myPolynomial.getList(ret).size == 0) {
      System.out.println("Polynomial " + ret + "is already not set");
      System.out.println("=============================================");
      myPolynomial.validateRes(myPolynomial);
      ret = in.next().charAt(0);
    }
    return ret;
  }
}
