package eg.edu.alexu.csd.datastructure.queue.cs25;

import eg.edu.alexu.csd.datastructure.linkedList.cs25.DoubleNode;
import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/**
 * Implementing Queue using Linked List.
 * @author Bishoy Nader
 */
public class LinkedListQueue implements IQueue, ILinkedBased {

  /**
   * Dummy for head.
   */
  public DoubleNode head;
  /**
   * Dummy for tail.
   */
  public DoubleNode tail;
  /**
   * list size.
   */
  int size = 0;

  /**
   * Constructor initializes a new LinkedBased Queue.
   */
  public LinkedListQueue() {
    this.head = new DoubleNode();
    this.tail = new DoubleNode();
    this.head.next = tail;
    this.tail.prev = head;
    this.size = 0;
  }

  /**
   * Main Function.
   * @param args String parameter when running from terminal
   */
  public static void main(final String[] args) {

  }

  @Override
  public void enqueue(final Object item) {
    if (isEmpty()) {
      DoubleNode tmp = new DoubleNode(item, tail, head);
      head.next = tmp;
      tail.prev = tmp;
    } else {
      DoubleNode tmp = new DoubleNode(item, head.next, head);
      head.next = tmp;
      tmp.next.prev = tmp;
    }
    size++;
  }

  @Override
  public Object dequeue() {
    if (isEmpty()) {
      throw new RuntimeException();
    }
    tail.prev.prev.next = tail;
    DoubleNode tmp = tail.prev;
    tail.prev = tail.prev.prev;
    tmp.next = null;
    tmp.prev = null;
    Object ret = tmp.value;
    size--;
    return ret;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
}
