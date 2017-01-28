package eg.edu.alexu.csd.datastructure.calculator;

public interface ICalculator {
  /**
  * Adds given two numbers.
  * @param firstNum first number
  * @param secondNum second number
  * @return the sum of the two numbers
  */
  int add(int firstNum, int secondNum);
  
  /**
  * Divides two numbers.
  * @param firstNum first number
  * @param secondNum second number
  * @return the division result
  */
  float divide(int firstNum, int secondNum);
}
