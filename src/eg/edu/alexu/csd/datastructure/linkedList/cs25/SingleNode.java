package eg.edu.alexu.csd.datastructure.linkedList.cs25;

/**
 * Assignment 4 : SingleNode.
 * @author Bishoy Nader
 */
public class SingleNode {

  /**
   * Next Node.
   */
  public SingleNode next;
  /**
   * Node Value.
   */
  public Object value;

  /**
   * constructor.
   */
  SingleNode() {
    this.next = null;
    this.value = null;
  }

  /**
   * constructor.
   * @param val value of node
   */
  public SingleNode(final Object val) {
    this.value = val;
    this.next = null;
  }

  /**
   * constructor.
   * @param val value of node
   * @param nextParam next node
   */
  SingleNode(final Object val, final SingleNode nextParam) {
    this.value = val;
    this.next = nextParam;
  }
}
