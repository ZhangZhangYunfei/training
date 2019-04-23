package list;

public class Node {

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  private Node next;

  private int value;

  public int getValue() {
    return value;
  }

  public Node(int value, Node next) {
    this.next = next;
    this.value = value;
  }

}
