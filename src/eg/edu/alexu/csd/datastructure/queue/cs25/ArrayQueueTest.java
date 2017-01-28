package eg.edu.alexu.csd.datastructure.queue.cs25;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Array Queue Test.
 * @author Bishoy Nader
 */
public class ArrayQueueTest {

  /**
   * Constant.
   */
  public static final int CONST_3 = 3;

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test() {
    ArrayQueue arrQueue = new ArrayQueue(CONST_3);
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    assertEquals(CONST_3, arrQueue.size());
  }

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test2() {
    ArrayQueue arrQueue = new ArrayQueue(CONST_3);
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    arrQueue.dequeue();
    arrQueue.dequeue();
    arrQueue.dequeue();
  }

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test3() {
    ArrayQueue arrQueue = new ArrayQueue(2);
    arrQueue.enqueue(1);
    arrQueue.enqueue(2);
    assertEquals(2, arrQueue.size());
    assertEquals(1, arrQueue.dequeue());
    assertEquals(2, arrQueue.dequeue());
    assertEquals(0, arrQueue.size());
  }

  /**
   * Queues Basic Tests.
   */
  @Test(expected = RuntimeException.class)
  public void test4() {
    ArrayQueue arrQueue = new ArrayQueue(2);
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
  }

  /**
   * Queues Basic Tests.
   */
  @Test(expected = RuntimeException.class)
  public void test5() {
    ArrayQueue arrQueue = new ArrayQueue(2);
    arrQueue.enqueue(new Object());
    arrQueue.enqueue(new Object());
    arrQueue.dequeue();
    arrQueue.dequeue();
    arrQueue.dequeue();
  }

  /**
   * Queues Basic Tests.
   */
  @Test(expected = RuntimeException.class)
  public void test6() {
    ArrayQueue arrQueue = new ArrayQueue(2);
    arrQueue.enqueue(new Object());
    arrQueue.dequeue();
    arrQueue.dequeue();
  }
}
