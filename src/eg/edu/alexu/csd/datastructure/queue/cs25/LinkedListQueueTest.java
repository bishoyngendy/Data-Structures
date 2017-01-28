package eg.edu.alexu.csd.datastructure.queue.cs25;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Linked List Queue Test.
 * @author Bishoy Nader
 */
public class LinkedListQueueTest {

  /**
   * Constant.
   */
  public static final int CONST_3 = 3;

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test() {
    LinkedListQueue linkQueue = new LinkedListQueue();
    linkQueue.enqueue(new Object());
    linkQueue.enqueue(new Object());
    linkQueue.enqueue(new Object());
    assertEquals(CONST_3, linkQueue.size());
  }

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test2() {
    LinkedListQueue linkQueue = new LinkedListQueue();
    linkQueue.enqueue(new Object());
    linkQueue.enqueue(new Object());
    linkQueue.enqueue(new Object());
    linkQueue.dequeue();
    linkQueue.dequeue();
    linkQueue.dequeue();
  }

  /**
   * Queues Basic Tests.
   */
  @Test
  public void test3() {
    LinkedListQueue linkQueue = new LinkedListQueue();
    linkQueue.enqueue(1);
    linkQueue.enqueue(2);
    assertEquals(2, linkQueue.size());
    assertEquals(1, linkQueue.dequeue());
    assertEquals(2, linkQueue.dequeue());
    assertEquals(0, linkQueue.size());
  }

  /**
   * Queues Basic Tests.
   */
  @Test(expected = RuntimeException.class)
  public void test4() {
    LinkedListQueue linkQueue = new LinkedListQueue();
    linkQueue.enqueue(new Object());
    linkQueue.enqueue(new Object());
    linkQueue.dequeue();
    linkQueue.dequeue();
    linkQueue.dequeue();
  }

  /**
   * Queues Basic Tests.
   */
  @Test(expected = RuntimeException.class)
  public void test5() {
    LinkedListQueue linkQueue = new LinkedListQueue();
    linkQueue.enqueue(new Object());
    linkQueue.dequeue();
    linkQueue.dequeue();
  }
}
