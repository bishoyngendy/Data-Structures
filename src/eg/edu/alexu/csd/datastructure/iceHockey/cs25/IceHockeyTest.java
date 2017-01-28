package eg.edu.alexu.csd.datastructure.iceHockey.cs25;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;

import java.awt.Point;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

/**
 * Assignment 3 : IceHockeyTest Class.
 * @author Bishoy Nader
 */
public class IceHockeyTest  {

  /**
   * Constant.
   */
  private static final int CONST_12 = 12;
  /**
   * Constant.
   */
  private static final int CONST_16 = 16;
  /**
   * Constant.
   */
  private static final int CONST_9 = 9;
  /**
   * Constant.
   */
  private static final int CONST_8 = 8;
  /**
   * Constant.
   */
  private static final int CONST_7 = 7;
  /**
   * Constant.
   */
  private static final int CONST_5 = 5;
  /**
   * Constant.
   */
  private static final int CONST_3 = 3;
  /**
   * IceHockey Basic Tests.
   */
  @Test
  public void test1() {
    IPlayersFinder playersFinder = new IceHockey();
    String[] image = {"", "", "", "", "", ""};
    Point[] answer = new Point[] {};
    Integer team = 1;
    Integer threashold = 1;
    Point[] result = playersFinder.findPlayers(image, team, threashold);
    assertArrayEquals(answer, result);
  }

  /**
   * IceHockey Basic Tests.
   */
  @Test
  public void test2() {
    IPlayersFinder playersFinder = new IceHockey();
    String[] image = {"AIRHGTMYJR", "ORJFVKTUXF", "ADFIRJOKTM",
                      "EYRUTOFKGN", "POINGHFBCX"};
    Point[] answer = new Point[] {};
    Integer team = 1;
    Integer threashold = 1;
    Point[] result = playersFinder.findPlayers(image, team, threashold);
    assertArrayEquals(answer, result);
  }

  /**
   * IceHockey Basic Tests.
   */
  @Test
  public void test3() {
    IPlayersFinder playersFinder = new IceHockey();
    String[] image = {"33AIRJ3", "3KGPR33", "E333URT",
                      "3SFRH33", "33SFDS3", "FHRNMFK"};
    Point[] answer = new Point[] {};
    Integer team = CONST_3;
    Integer threashold = CONST_16;
    Point[] result = playersFinder.findPlayers(image, team, threashold);
    assertArrayEquals(answer, result);
  }

  /**
   * IceHockey Basic Tests.
   */
  @Test
  public void test4() {
    IPlayersFinder playersFinder = new IceHockey();
    String[] image = {"33AIRJ3", "3KGPR33", "E333URT",
                      "3SFRH33", "33SFDS3", "FHRNMFK"};
    Point[] answer = new Point[] {new Point(2, 2), new Point(2, CONST_8),
                                  new Point(CONST_5, CONST_5),
                                  new Point(CONST_12, 2),
                                  new Point(CONST_12, CONST_8)};
    Integer team = CONST_3;
    Integer threashold = 1;
    Point[] result = playersFinder.findPlayers(image, team, threashold);
    assertArrayEquals(answer, result);
  }

  /**
   * IceHockey Basic Tests.
   */
  @Test
  public void test5() {
    IPlayersFinder playersFinder = new IceHockey();
    String[] image = {"1AAA1", "A1A1A", "AA1AA", "A1A1A", "1AAA1"};
    Point[] answer = new Point[] {new Point(1, 1), new Point(1, CONST_9),
                                  new Point(CONST_3, CONST_3),
                                  new Point(CONST_3, CONST_7),
                                  new Point(CONST_5, CONST_5),
                                  new Point(CONST_7, CONST_3),
                                  new Point(CONST_7, CONST_7),
                                  new Point(CONST_9, 1),
                                  new Point(CONST_9, CONST_9)};
    Integer team = 1;
    Integer threashold = 1;
    Point[] result = playersFinder.findPlayers(image, team, threashold);
    assertArrayEquals(answer, result);
  }
}
