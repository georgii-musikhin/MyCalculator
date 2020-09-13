package simpleCalculator;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class ConsoleCalculator {
	private ArrayList<String> list;
	
	public int makeCalculations(String s) {
		
		String[] arrayOfString = separateString(s);
		
		//Transform to postfix notation
		Stack<String> stack = new Stack<String>();
		ArrayList<String> list = new ArrayList<String>();
		
		for(String word : arrayOfString) {
			if(isNumber(word))
				list.add(word);
			else if(word.equals("("))
				stack.push(word);
			else if(word.equals(")")) {
				try {
				while(!stack.peek().equals("("))
					list.add(stack.pop());
				stack.pop();
				} 
				catch (EmptyStackException e) {
					System.out.println("You placed the brackets incorrectly. Please, try again");
					System.exit(0);
				}
			} 
			else if(word.equals("+") || word.equals("-")) {
				while((!stack.empty()) && ((!stack.peek().equals("(")) && (!stack.peek().equals(")")))) {
						list.add(stack.pop());
				}
				stack.push(word);
			}
			else if(word.equals("*") || word.equals("/")) {
				stack.push(word);
			}
			else {
				System.out.println("Invalid input data. Please, try again");
				System.exit(0);
			}
			
		}
		
		
		//Calculations in stack
		while(!stack.isEmpty())
			list.add(stack.pop());
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String word = iterator.next();
			if(isNumber(word))
				stack.push(word);
			else if((word.equals("(")) || (word.equals(")"))) {
				System.out.println("You placed the brackets incorrectly. Please, try again");
				System.exit(0);
			}
			else {
				int b = Integer.parseInt(stack.pop());
				int a = Integer.parseInt(stack.pop());
				if(word.equals("+"))
					stack.push(Integer.toString(a + b));
				else if(word.equals("-"))
					stack.push(Integer.toString(a - b));
				else if(word.equals("*"))
					stack.push(Integer.toString(a * b));
				else if(word.equals("/")) {
					try {
						stack.push(Integer.toString(a / b));
					}
					catch (ArithmeticException e) {
						System.out.println("You are trying to divide by zero. Please, try again");
						System.exit(0);
					}
				}
					
				else {
					System.out.println("Invalid data. Please, try again");
					System.exit(0);
				}
					
			}
		}
		try {
			return Integer.parseInt(stack.pop());
		}
		catch(EmptyStackException e) {
			return 0;
		}
		
	}
	
	
	//Separating strings to String[]
	public String[] separateString(String s) {
		return s.trim().split(" ");
	}
	
	//Checking if input string is number
	public boolean isNumber(String s) {
		if(s.equals(null))
			return false;
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
