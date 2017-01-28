package eg.edu.alexu.csd.datastructure.stack.cs25;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit Test for Stack Implementation.
 * @author Bishoy Nader
 */
public class MyStackTest {

  /**
   * Constant.
   */
  private static final int CONST_5 = 5;
  /**
   * Constant.
   */
  private static final int CONST_4 = 4;
  /**
   * Constant.
   */
  private static final int CONST_3 = 3;

/**
   * Stack Basic Tests.
   */
  @Test
  public void testPush() {
    MyStack tsstStack = new MyStack();
    tsstStack.push(new Object());
    tsstStack.push(new Object());
    tsstStack.push(new Object());
    tsstStack.push(new Object());
    tsstStack.push(new Object());
    assertEquals(CONST_5, tsstStack.size());
  }

  /**
   * Stack Basic Tests.
   */
  @Test
  public void testAdd() {
    MyStack tsstStack = new MyStack();
    tsstStack.add(0, new Object());
    tsstStack.add(1, new Object());
    tsstStack.add(2, new Object());
    tsstStack.add(CONST_3, new Object());
    tsstStack.add(CONST_4, new Object());
    assertEquals(CONST_5, tsstStack.size());
  }

  /**
   * Stack Basic Tests.
   */
  @Test
  public void testPop() {
    MyStack tsstStack = new MyStack();
    tsstStack.add(0, new Object());
    tsstStack.add(1, new Object());
    tsstStack.add(1, new Object());
    tsstStack.add(2, new Object());
    tsstStack.add(0, new Object());
    tsstStack.pop();
    tsstStack.pop();
    tsstStack.pop();
    assertEquals(2, tsstStack.size());
  }

  /**
   * Stack Basic Tests.
   */
  @Test
  public void testPeek() {
    MyStack tsstStack = new MyStack();
    tsstStack.add(0, new Object());
    tsstStack.add(1, new Object());
    tsstStack.add(2, new Object());
    tsstStack.add(CONST_3, new Object());
    tsstStack.add(CONST_4, new Object());
    tsstStack.peek();
    tsstStack.peek();
    tsstStack.peek();
    assertEquals(CONST_5, tsstStack.size());
  }
}
