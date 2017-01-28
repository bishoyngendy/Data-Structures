package eg.edu.alexu.csd.datastructure.linkedList.cs25;

/**
 * Assignment 4 : DoubleNode.
 * @author Bishoy Nader
 */
public class DoubleNode {

  /**
   * previous node.
   */
  public DoubleNode prev;
  /**
   * next node.
   */
  public DoubleNode next;
  /**
   * node value.
   */
  public Object value;
  /**
   * DoubleNode default constructor.
   */
  public DoubleNode() {
    this.next = null;
    this.prev = null;
    this.value = null;
  }

  /**
   * DoubleNode Constructor that sets the value of the node.
   * @param valueParam the value of the node
   */
  public DoubleNode(final Object valueParam) {
    this.next = null;
    this.prev = null;
    this.value = valueParam;
  }

  /**
   * DoubleNode Constructor that sets the value,
   * next node and previous node of the node.
   * @param valueParam value of the new double node
   * @param nextParam next node of the new double node
   * @param prevParam previous node of the new double node
   */
  public DoubleNode(final Object valueParam,
                    final DoubleNode nextParam,
                    final DoubleNode prevParam) {
    this.next = nextParam;
    this.prev = prevParam;
    this.value = valueParam;
  }
}
