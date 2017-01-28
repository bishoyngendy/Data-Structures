package eg.edu.alexu.csd.datastructure.stack.cs25;

import eg.edu.alexu.csd.datastructure.linkedList.cs25.SingleLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IStack;

import java.util.Scanner;

/**
 * Assignment 5 : Stacks.
 * @author Bishoy Nader
 */
public class MyStack implements IStack {
  /**
   * implementation using single list.
   */
  SingleLinkedList stack = new SingleLinkedList();

  /**
   * Constant.
   */
  private static final int CONST_6 = 6;
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
   * Simple implementation of the stack.
   * @param args when running from the terminal
   */
  public static void main(final String[] args) {
    MyStack myStack = new MyStack();
    Boolean status = true;
    Scanner in = new Scanner(System.in);
    while (status) {
      System.out.println("PLease choose an option :");
      System.out.println("1- Push");
      System.out.println("2- Pop");
      System.out.println("3- Peek");
      System.out.println("4- Get Size");
      System.out.println("5- Check if empty");
      System.out.println("6- Close the program");
      int choice = in.nextInt();
      switch (choice) {
        case 1:
          System.out.println("Enter value to be pushed");
          String obj = in.next();
          myStack.push(obj);
          break;
        case 2:
          try {
            System.out.println(myStack.pop().toString());
          } catch (Exception excep) {
            System.out.println("Invalid Pop operation !!!");
          }
          break;
        case CONST_3:
          try {
            System.out.println(myStack.peek().toString());
          } catch (Exception excep) {
            System.out.println("Invalid Peek operation !!!");
          }
          break;
        case CONST_4:
          System.out.println("The size is " + myStack.size());
          break;
        case CONST_5:
          if (myStack.isEmpty()) {
            System.out.println("The Stack is empty");
          } else {
            System.out.println("The Stack is not empty");
          }
          break;
        case CONST_6:
          status = false;
          break;
        default:
          System.out.println("Invalid Choice");
          break;
      }
    }
    in.close();
  }

  @Override
  public void add(final int index, final Object element) {
    stack.add(stack.size() - index, element);
  }

  @Override
  public Object pop() {
    Object ret = stack.get(0);
    stack.remove(0);
    return ret;
  }

  @Override
  public Object peek() {
    Object ret = stack.get(0);
    return ret;
  }

  @Override
  public void push(final Object element) {
    stack.add(0, element);
  }

  @Override
  public boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public int size() {
    return stack.size();
  }
}
