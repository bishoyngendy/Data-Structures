package eg.edu.alexu.csd.datastructure.queue.cs25;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * Implementing Queue using array.
 * @author Bishoy Nader
 */
public class ArrayQueue implements IQueue, IArrayBased {

  /**
   * Queue Array.
   */
  Object[] queue;
  /**
   * front index.
   */
  int front = 0;
  /**
   * rear index.
   */
  int rear = 0;
  /**
   * count of elements.
   */
  int count = 0;
  /**
   * Capacity of the array.
   */
  int size = 0;

  /**
   * Main Function.
   * @param args String parameter when running from terminal
   */
  public static void main(final String[] args) {

  }

  /**
   * Class Constructor.
   * @param length of the array
   */
  public ArrayQueue(final int length) {
    queue = new Object[length];
    this.size = length;
  }

  @Override
  public void enqueue(final Object item) {
    if (count == size) {
      throw new RuntimeException();
    }
    queue[rear] = item;
    if (rear == size - 1) {
      rear = 0;
    } else {
      rear++;
    }
    count++;
  }

  @Override
  public Object dequeue() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    count--;
    Object ret = queue[front];
    queue[front] = null;
    if (front == size - 1) {
      front = 0;
    } else {
      front++;
    }
    return ret;
  }

  @Override
  public boolean isEmpty() {
    return count == 0;
  }

  @Override
  public int size() {
    return count;
  }
}
