package eg.edu.alexu.csd.datastructure.linkedList.cs25;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Assignment 4 : SingleLinkedList.
 * @author Bishoy Nader
 */
public class SingleLinkedList implements ILinkedList {
  /**
   * size of the list.
   */
  private int size;
  /**
   * Dummy for the head.
   */
  public SingleNode head = null;
  /**
   * Dummy for the tail.
   */
  public SingleNode tail = null;

  /**
   * constructor.
   */
  public SingleLinkedList() {
    tail = new SingleNode();
    head = new SingleNode();
    size = 0;
  }

  @Override
  public void add(final int index, final Object element) {
    if (index > size || index < 0) {
      throw new RuntimeException("You can't add element here");
    }

    SingleNode tmp = head;
    for (int i = 0; i < index; i++) {
      tmp = tmp.next;
    }

    SingleNode itemToAdd = new SingleNode(element, tmp.next);
    tmp.next = itemToAdd;
    size++;
  }

  @Override
  public void add(final Object element) {
    add(size, element);
  }

  @Override
  public Object get(final int index) {
    if (index > size || index < 0 || size == 0) {
      throw new RuntimeException();
    }
    SingleNode node = head.next;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  @Override
  public void set(final int index, final Object element) {
    if (index > size || index < 0 || size == 0) {
      throw new RuntimeException();
    }
    SingleNode node = head.next;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    node.value = element;
  }

  @Override
  public void clear() {
    head = new SingleNode();
    tail = new SingleNode();
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void remove(final int index) {
    if (index >= size || index < 0 || size == 0) {
      throw new RuntimeException();
    }
    if (size == 1) {
      clear();
      return;
    }
    size--;
    if (index == 0) {
      head.next = head.next.next;
      return;
    }
    SingleNode node = head.next;
    for (int i = 0; i < index - 1; i++) {
      node = node.next;
    }
    node.next = node.next.next;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public ILinkedList sublist(final int fromIndex, final int toIndex) {
    if (toIndex < fromIndex || fromIndex < 0
        || toIndex < 0 || size == 0 || fromIndex >= size || toIndex >= size) {
      throw new RuntimeException();
    }
    SingleLinkedList list = new SingleLinkedList();
    for (int k = fromIndex; k <= toIndex; k++) {
      list.add(this.get(k));
    }
    return list;
  }

  @Override
  public boolean contains(final Object element) {
    SingleNode node = head;
    for (int i = 0; i < size; i++) {
      node = node.next;
      if (node.value.equals(element)) {
        return true;
      }
    }
    return false;
  }
}
