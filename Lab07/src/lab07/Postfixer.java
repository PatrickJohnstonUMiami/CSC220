package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {


	/**
	*  Determines if the first operator has same or greater
    *  precedence than the second
	*
	* @param op1 the first operator
	* @param op2 the second operator
	* @return the boolean result
	*/
	private static boolean hasPrecedence(String op1, String op2) {
	    return opToPrcd(op1) >= opToPrcd(op2);
	}


	/**
	* Converts an operator to its precedence priority
	*
	* We expect you to be able to handle +, -, *, /, ^, and (
	* (why don't you need ")" as well? see algorithm in part 4)
	*
	* The order of these is as follows:
	*    ^, * and /, + and -, (
	*
	* @param op a string representing an operator, e.g. "+" or "-"
	* @return an integer value reflecting its precedence
	*/
	private static int opToPrcd(String op) {
		switch (op) {
		//Power has highest precedence
		case "^": return 4; 
		
		//Multiplication/Division has second highest
		case "*": return 3;
		case "/": return 3;
		
		//Subtraction/Addition has 3rd highest
		case "-": return 2;
		case "+": return 2; 
		
		//( has the lowest
		case "(": return 1; 
		
		//Will never reach default
		default: return -1; 
	}
	}

	/**
	* determines if the input token is an operator
	*
	* @param token the string token to check
	* @return a boolean reflecting the result
	*/
	private static boolean isOperator(String token) {
		//Switch Statement 
		switch ( token ) {
		
		case "^":
			return true; 
			
		case "/":
			return true; 
			
		case "-":
			return true; 
			
		case "(":
			return true; 
			
		case ")":
			return true; 
			
		case "*":
			return true; 

		case "+":
			return true; 
			
		default: 
			return false; 
		}
		 // placeholder
	}

	/**
    * Evaluates an expression
    *
    * NOTE Beware the order of pop and evaluation when receiving operand1
    * and operand2 as input.
    *
    * @param operand1 the first operand
    * @param operator the operator to apply
    * @param operand2 the second operand
    * @return a double expressing the result
    * @throws IllegalArgumentException if operator passed is not one of
    *  "+", "-", "*", "/", or "^"
    *
	*/
	private static double evaluate(double operand1, String operator, double operand2){
	

		//Power Case
		switch (operator) {
		case "^":  	
			return Math.pow(operand2, operand1);
		
		//Division Case
		case "/":
			return (operand2/operand1);
			
		//Subtraction Case
		case "-":
			return (operand2-operand1);
			
		//Multiplication Case
		case "*":
			return (operand2*operand1);
		
		//Addition Case
		case "+":
			return (operand2+operand1);
		default: 
			 throw new IllegalArgumentException("evaluate(); Operator is not able to be used");
		}
	 // placeholder
	}


	/**
	* give a description of the purpose of this method
	* @param String line thats being evaluated
	* @return double
	*/
	public static double infixEvaluator(String line){
		
		StringSplitter data = new StringSplitter(line);
		
		Stack<String> operators = new Stack<String>();  
		Stack<Double> operands = new Stack<Double>();	
		
		while ( data.hasNext()) {
			String stringCheck = data.next();
			if ( false == isOperator(stringCheck) ) { 
				operands.push(Double.parseDouble(stringCheck));	
				}
			else if (stringCheck.equals("(") == true) 	{
				
				operators.push(stringCheck); 
				
				}
			else if (stringCheck.equals(")") ==true) { 
				while (false == operators.peek().equals("(")) {
					double rand1 = operands.pop(); 
					double rand2 = operands.pop(); 
					String op1 = operators.pop(); 
					double endVal = evaluate(rand1, op1 ,rand2);
					operands.push(endVal);
				}
				if (operators.peek().equals("(") == true) {
					operators.pop(); 
					}	
			}
			else if (isOperator(stringCheck) == true) {
				while (false == operators.isEmpty() && hasPrecedence(operators.peek(), stringCheck) == true) {
					double rand1 = operands.pop(); 
					double rand2 = operands.pop(); 
					String op1 = operators.pop(); 
					double endVal = evaluate(rand1, op1 ,rand2);
					operands.push(endVal);
				}
				operators.push(stringCheck);
			}
		}
			
		while (false == operators.empty()) {
			double rand1 = operands.pop(); 
			double rand2 = operands.pop(); 
			String op1 = operators.pop(); 
			double endVal = evaluate(rand1, op1 ,rand2);
			operands.push(endVal);
		}
		
			return operands.pop(); 
		
		
	}

	/**
	* give a description of the purpose of this method
	* @param line fill in
	* @return fill in
	*/
	public static String toPostfix(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>(); 
		String holder = ""; 
		while ( data.hasNext()) {
			String tok = data.next();
			
			if (tok.equals("(")) {
				operators.push(tok);
			}
			else if (isOperator(tok)){
				System.out.println("Inf loop");
				while(!operators.empty()) {
				while ( false == operators.peek().equals("(") &&   true == hasPrecedence(operators.peek(), tok)) {
					holder += operators.pop(); 
				}
				}
				System.out.println("Inf loop");

				operators.push(tok);
			}
			else if (tok.equals(")")) {
				while (false == operators.peek().equals("(")) {
					holder = holder + operators.pop();
				}
				operators.pop(); 
			}
			
			else if (false == isOperator(tok)){   
				holder += tok; 
			}
			
			
		}
		
		return holder; 
	}


	public static void main(String[] args){

System.out.println(infixEvaluator("300 / (15/5)"));

        if (infixEvaluator("10 + 2") != 12.0)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");

        if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");

        if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");
        
        if (infixEvaluator("2 ^ 2") != 4)
        	System.err.println("test5 failed --> your answer should have been 100");
        
        if (infixEvaluator("300 / (15/5)") != 100)
            System.err.println("test6 failed --> your answer should have been 100");


        System.out.println("Lab Testing Done!!!");

		if (!toPostfix(new String("(4+5)")).equals("45+"))
		     System.err.println("test1 failed --> should have been 45+");

		if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		   System.err.println("test2 failed --> should have been 45+6*");

	 if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
		    System.err.println("test3 failed --> should have been 456*7/+8-");

		if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		    System.err.println("test4 failed --> should have been 4567-*+8/");

	 if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
	     System.err.println("test5 failed --> should have been 987*654^/3*-2*+");


        // System.out.println("Assignment Testing Done!!!");


	}

}
