package eg.edu.alexu.csd.datastructure.stack.cs25;

import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

/**
 * Assignment 5 : Stacks.
 * @author Bishoy Nader
 */
public class MyEvaluator implements IExpressionEvaluator {
  /**
   * object from the stack.
   */
  MyStack eval = new MyStack();

  /**
   * main function.
   * @param args when running from terminal
   */
  public static void main(final String[] args) {
    MyEvaluator evaluator = new MyEvaluator();
    Scanner in = new Scanner(System.in);
    Boolean status = true;
    while (status) {
      System.out.println("Enter a valid infix expression");
      String infix = in.nextLine();
      String postfix = new String();
      try {
        postfix = evaluator.infixToPostfix(infix);
      } catch (Exception excep) {
        System.out.println("Invalid infix expression");
        continue;
      }
      try {
        System.out.println(evaluator.evaluate(postfix));
      } catch (Exception excep) {
        System.out.println("Can't evaluate such expression");
        continue;
      }
      status = false;
    }
  }

  @Override
  public String infixToPostfix(final String expression) {
    int flag = 0;
    if (expression.length() == 0) {
      throw new RuntimeException();
    }
    int numOfOperators = 0;
    int numOfOperands = 0;
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
      switch (expression.charAt(i)) {
        case '+':
          if (flag != 1) {
            throw new RuntimeException();
          }
          if (eval.isEmpty() || eval.peek().toString().equals("(")) {
            eval.push('+');
            numOfOperators++;
            flag = 0;
          } else {
            ret.append(eval.pop().toString() + ' ');
            i--;
          }
          break;
        case '-':
          if (flag != 1) {
            throw new RuntimeException();
          }
          if (eval.isEmpty() || eval.peek().toString().equals("(")) {
            eval.push('-');
            numOfOperators++;
            flag = 0;

          } else {
            ret.append(eval.pop().toString() + ' ');
            i--;
          }
          break;
        case '*':
          if (flag != 1) {
            throw new RuntimeException();
          }
          if (eval.isEmpty() || eval.peek().toString().equals("(")
              || eval.peek().toString().equals("+")
              || eval.peek().toString().equals("-")) {
            eval.push('*');
            numOfOperators++;
            flag = 0;

          } else {
            ret.append(eval.pop().toString() + ' ');
            i--;
          }
          break;
        case '/':
          if (flag != 1) {
            throw new RuntimeException();
          }
          if (eval.isEmpty() || eval.peek().toString().equals("(")
              || eval.peek().toString().equals("+")
              || eval.peek().toString().equals("-")) {
            eval.push('/');
            numOfOperators++;
            flag = 0;

          } else {
            ret.append(eval.pop().toString() + ' ');
            i--;
          }
          break;
        case ' ':
          break;
        case '(':
          eval.push('(');
          break;
        case ')':
          if (eval.size() == 0) {
            throw new RuntimeException();
          }
          if (eval.peek().toString().equals("(")) {
            eval.pop();
          } else {
            ret.append(eval.pop().toString() + ' ');
            i--;
          }
          break;
        default:
          if (!Character.isLetterOrDigit(expression.charAt(i))) {
            throw new RuntimeException();
          }
          while (Character.isLetterOrDigit((expression.charAt(i)))) {
            ret.append(expression.charAt(i));
            i++;
            if (i == expression.length()) {
              break;
            }
          }
          ret.append(' ');
          numOfOperands++;
          flag++;
          if (i == expression.length()) {
            continue;
          }
          i--;
          break;
      }
    }
    if (numOfOperands - numOfOperators != 1) {
      throw new RuntimeException();
    }
    while (eval.size() != 0) {
      if (eval.peek().toString().equals("(")) {
        throw new RuntimeException();
      }
      ret.append(eval.pop().toString() + ' ');
    }
    return (ret.toString()).trim();
  }

  @Override
  public int evaluate(final String expression) {
    if (expression.length() == 0 || expression.equals(" ")) {
      throw new RuntimeException();
    }
    int numOfOperators = 0;
    int numOfOperands = 0;
    float firstNum = 1;
    float secondNum = 0;
    for (int i = 0; i < expression.length(); i++) {
      switch (expression.charAt(i)) {
        case '+':
          firstNum = (float) (eval.pop());
          secondNum = (float) (eval.pop());
          eval.push(secondNum + firstNum);
          numOfOperators++;
          break;
        case '-':
          firstNum = (float) (eval.pop());
          secondNum = (float) (eval.pop());
          eval.push(secondNum - firstNum);
          numOfOperators++;
          break;
        case '*':
          firstNum = (float) (eval.pop());
          secondNum = (float) (eval.pop());
          eval.push(secondNum * firstNum);
          numOfOperators++;
          break;
        case '/':
          firstNum = (float) (eval.pop());
          secondNum = (float) (eval.pop());
          eval.push(secondNum / firstNum);
          numOfOperators++;
          break;
        case ' ':
          break;
        default:
          StringBuilder retBuilder = new StringBuilder();
          while (Character.isDigit(expression.charAt(i))) {
            retBuilder.append(expression.charAt(i));
            i++;
            if (i == expression.length()) {
              break;
            }
          }
          eval.push(Float.parseFloat(retBuilder.toString()));
          numOfOperands++;
          if (i == expression.length()) {
            break;
          }
          i--;
          break;
      }
    }
    if (eval.size() != 1 || (numOfOperands - numOfOperators != 1)) {
      throw new RuntimeException();
    } else {
      return Math.round((float) eval.pop());
    }
  }
}
