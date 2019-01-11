package here;
/*
 Name:Aidan Weber-Concannon
 Student#:260708481
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression {

	private ArrayList<String> tokenList;

	// Constructor
	/**
	 * The constructor takes in an expression as a string and tokenizes it
	 * (breaks it up into meaningful units) These tokens are then stored in an
	 * array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException {
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		// ADD YOUR CODE BELOW HERE
		//Avoids out of bounds as there are always brackets
		for(int i = 0;i< expressionString.length();i++){
			token.setLength(0);
			char c = expressionString.charAt(i);
			
			//skips white spaces 
			if(c== ' '){
				continue;
			}else if(c>='0'&&c<='9'){
				token.append(c);
				char d = expressionString.charAt(i+1);
				int j=1;
				while(d>='0'&&d<='9'){
					token.append(d);
					j++;
					d = expressionString.charAt(i+j);
				}
				if(token.length()>1){
					i+=j-1;//not sure if this works
				}
				tokenList.add(token.toString());
				
			}else if(c=='+'||c=='-'){
				char next = expressionString.charAt(i+1);
				if(c==next){
					token.append(c);//must change i after adding 
					token.append(next);
					tokenList.add(token.toString());
					i++;
				}else{
					token.append(c);
					tokenList.add(token.toString());
				}
			}else{
				token.append(c);
				tokenList.add(token.toString());
			}
		}
		
		// ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the
	 * expression Evaluation is done using 2 stack ADTs, operatorStack to store
	 * operators and valueStack to store values and intermediate results. - You
	 * must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval() {
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		// ADD YOUR CODE BELOW HERE
		Integer sum=0;
		for(int i=0;i<this.tokenList.size();i++){
			String current = tokenList.get(i);
			 
			if(current.equals("(")||current.equals("[")){
				continue;
			}else if(isInteger(current)){
				Integer temp = Integer.parseInt(current);
				valueStack.push(temp);
			}else if(current.equals("++")||current.equals("--")||current.equals("+")||current.equals("-")||current.equals("/")||current.equals("*")){
				operatorStack.push(current);
			}else if(current.equals(")")){
				String operator= operatorStack.pop();
				if(operator.equals("++")||operator.equals("--")){//single operators 
					Integer single = valueStack.pop();
					if(operator.equals("++")){
						single++;
						valueStack.push(single);
					}else{ //equals -- (check)
						single--;
						valueStack.push(single);
					}
				}else if(operator.equals("+")||operator.equals("-")||operator.equals("/")||operator.equals("*")){//can replace with else if .equals("]") double operators 
					Integer operated = valueStack.pop();
					Integer operatedOn= valueStack.pop();
					Integer putBack;
					if(operator.equals("+")){
						putBack= operatedOn+operated;
					}else if(operator.equals("-")){
						putBack= operatedOn-operated;
					}else if(operator.equals("/")){
						putBack= operatedOn/operated;
					}else{
						putBack= operatedOn*operated; //double check this maybe better to put else if 
					}
					valueStack.push(putBack);
					
				}
			}else if(current.equals("]")){
				Integer absoluteValue = valueStack.pop();
				if(absoluteValue<0){
					absoluteValue = absoluteValue*-1;
				}
				valueStack.push(absoluteValue);
			}
		}
		
		sum = valueStack.pop();
		return sum;
		// ADD YOUR CODE ABOVE HERE

		 
	}

	// Helper methods
	/**
	 * Helper method to test if a string is an integer Returns true for strings
	 * of integers like "456" and false for string of non-integers like "+" - DO
	 * NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element) {
		try {
			Integer.valueOf(element);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList. -
	 * DO NOT EDIT THIS METHOD
	 */

	@Override
	public String toString() {
		String s = new String();
		for (String t : tokenList)
			s = s + "~" + t;
		return s;
	}

}
