import java.util.*;
import java.lang.*;

public class PrefixEvaluation {
    // static int currentVal = 0;
    // static String currentOperator;
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String expression;
        PrefixEvaluation prefixEvaluator = new PrefixEvaluation();
        
        System.out.println("Prefix Evaluation – Brandon Baker");
        
         do {  
             System.out.print("\nEnter an expression: ");
             expression = scnr.nextLine();
             System.out.println(prefixEvaluator.eval(expression));
         }
         while (expression.equalsIgnoreCase("done") != true);
         scnr.close();
    }

    public int eval(String expr) {
    	int result = 0;
    	
    	Scanner parser = new Scanner(expr);
        String token = parser.next();
        expr = expr.substring(token.length()).trim();
        parser = new Scanner(expr);

        if (isNumeric(token)) {
            result = Integer.parseInt(token);
        }
        else if(token.equals("(")) {
          ArrayList<Integer> operands = new ArrayList<Integer>();
          token = parser.next();
          char operator = token.charAt(0);
          expr = expr.substring(token.length()).trim();
          parser = new Scanner(expr);
          
          // Collect operands recursively
          while(!token.equals(")")) {
        	  int operand = eval(expr);
        	  operands.add(operand);
        	  
        	  expr = expr.substring(String.valueOf(operand).length()).trim();
        	  parser = new Scanner(expr);
        	  token = parser.next();
          }
          
          // Perform operation on operands. Do the first two operands
          result = operands.get(0);
          operands.remove(0);
          
          // Then do the rest of the operands, if any
          for(Integer operandRight: operands) {
        	  result = doMath(operator, result, operandRight);
          }
        }

        
        return result;
    }

    private int doMath(char operator, int left, int right) {
    	int result = 0;
    	switch(operator) {
        	case '+': {
        		result = left + right;
        		break;
        	}
        	case '-': {
        		result = left - right;
        		break;
        	}
        	case '/': {
        		result = left / right;
        		break;
        	}
        	case '*': {
        		result = left * right;
        		break;
        	}
        	default: break;
    	}
    	
    	return result;
    }

    private boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
    
    private boolean isOperator(char val) {
    	return val == '+' || val == '-' || val == '/' || val == '*';
    }
}

// Your program can assume that the input is well-formed. For example, there will be no bad operators, floating point values, unmatched parentheses, or missing arguments. All the tokens in the input will be separated by whitespace (space, tab, or newline) which means that you can use Scanner.getNext() to get each token.
// • Your program is to continue to accept expressions until the user enters “done”. Ignore case on this comparison.
// • Your output must include your name.
// • Your solution must be recursive.
// • Turn in only your source file: PrefixEvaluation.java.
// • Make sure your class is not in a package (that is, it is in the default package).
// • Hint: Consider writing a single recursive method int eval(String e) which evaluates e.
// If e is an integer (the base case), just return its value. If e isn’t an integer, it must be start with a “(“ (recursive case). Read the operator next, then read, evaluate and store (ArrayList?) expressions until you find the matching “)”. Once you have your values (the operands to the operation), perform the required operation and return its result.