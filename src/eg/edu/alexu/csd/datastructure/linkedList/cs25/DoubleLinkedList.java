package eg.edu.alexu.csd.datastructure.linkedList.cs25;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Assignment 4 : DoubleLinkedList.
 * @author Bishoy Nader
 */
public class DoubleLinkedList implements ILinkedList {
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
  int size;

  /**
   * DoubleLinkedList default constructor.
   */
  public DoubleLinkedList() {
    this.head = new DoubleNode();
    this.tail = new DoubleNode();
    head.next = tail;
    tail.prev = head;
    this.size = 0;
  }

  /**
   * Main Function.
   * @param args when running from the terminal
   */
  public static void main(final String[] args) { }

  @Override
  public void add(final int index, final Object element) {
    if (index > size || index < 0) {
      throw new RuntimeException();
    }
    DoubleNode newNode;
    if (index == 0 && size == 0) {
      newNode = new DoubleNode(element, tail, head);
      tail.prev = newNode;
      head.next = newNode;
    } else if (index == 0) {
      newNode = new DoubleNode(element, head.next, head);
      newNode.next.prev = newNode;
      head.next = newNode;
    } else if (index == size) {
      newNode = new DoubleNode(element, tail, tail.prev);
      tail.prev.next = newNode;
      tail.prev = newNode;
    } else {
      DoubleNode tmp = head.next;
      for (int i = 0; i < index && tmp != tail; i++) {
        tmp = tmp.next;
      }
      newNode = new DoubleNode(element, tmp, tmp.prev);
      tmp.prev.next = newNode;
      tmp.prev = newNode;
    }
    size++;
  }

  @Override
  public void add(final Object element) {
    DoubleNode newNode;
    if (isEmpty()) {
      newNode = new DoubleNode(element, tail, head);
      tail.prev = newNode;
      head.next = newNode;
    } else {
      newNode = new DoubleNode(element, tail, tail.prev);
      tail.prev.next = newNode;
      tail.prev = newNode;
    }
    size++;
  }

  @Override
  public Object get(final int index) {
    if (index >= size || index < 0) {
      throw new RuntimeException();
    }
    DoubleNode tmp = head.next;
    for (int i = 0; i < index && tmp != tail; i++) {
      tmp = tmp.next;
    }
    return tmp.value;
  }

  @Override
  public void set(final int index, final Object element) {
    if (index >= size || index < 0) {
      throw new RuntimeException();
    }
    DoubleNode settedNode = new DoubleNode(element);
    DoubleNode tmp = head.next;
    for (int i = 0; i < index && tmp != tail; i++) {
      tmp = tmp.next;
    }
    tmp.prev.next = settedNode;
    if (tmp != tail) {
      tmp.next.prev = settedNode;
    }
    settedNode.next = tmp.next;
    settedNode.prev = tmp.prev;
  }

  @Override
  public void clear() {
    this.head = new DoubleNode();
    this.tail = new DoubleNode();
    head.next = tail;
    tail.prev = head;
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void remove(final int index) {
    if (index >= size || index < 0) {
      throw new RuntimeException();
    }
    DoubleNode tmp = head.next;
    for (int i = 0; i < index && tmp != tail; i++) {
      tmp = tmp.next;
    }
    tmp.prev.next = tmp.next;
    if (tmp != tail) {
      tmp.next.prev = tmp.prev;
    }
    size--;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public ILinkedList sublist(final int fromIndex, final int toIndex) {
    if (fromIndex >= size || fromIndex < 0) {
      throw new RuntimeException();
    }
    if (toIndex >= size || toIndex < 0) {
      throw new RuntimeException();
    }
    if (toIndex < fromIndex) {
      throw new RuntimeException();
    }
    ILinkedList linkedList = new DoubleLinkedList();
    DoubleNode tmp = head.next;
    for (int i = 0; i < fromIndex; i++) {
      tmp = tmp.next;
    }
    for (int i = 0; i < toIndex - fromIndex + 1 && tmp != tail; i++) {
      linkedList.add(i, tmp.value);
      tmp = tmp.next;
    }
    return linkedList;
  }

  @Override
  public boolean contains(final Object element) {
    for (int i = 0; i < size && head.next != tail; i++) {
      if (get(i).equals(element)) {
        return true;
      }
    }
    return false;
  }
}
