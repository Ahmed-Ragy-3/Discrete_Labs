package Discrete_Labs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import Discrete_Labs.Expression;

public class LogicalExpression {
   public static final char NOT = '~';

   private static Map<Character, Boolean> map = new HashMap<>();

   private static boolean isOperand(char c) {
      // a ---> b
      // A ---> B
      c = Character.toLowerCase(c);
      return c >= 'a' && c <= 'z';
   }
   
   private static boolean isOperator(char c) {
      return c == '.' || c == '~' || c == '+' || c == '>';
   }

   private static boolean invalid(char c) {
      return !isOperand(c) && !isOperator(c) && c != '(' && c != ')';
   }

   private static String normalize(String expression) {
      expression = expression.replaceAll(" ", "");
      expression = expression.replaceAll("~~", "");

      expression = expression.replaceAll("\\^",".");
      expression = expression.replaceAll("v", "+");


      return expression;
   }
   
   private static void throwError() {
      throw new IllegalArgumentException();
   }

   private static void validate(String expression) {
      expression = normalize(expression);
      
      char[] expressionChars = expression.toCharArray();
      int n = expression.length();

      for(int i=0; i < n - 1; i++) {
         if(invalid(expressionChars[i])) {
            throwError();

         }else if(isOperator(expressionChars[i]) && isOperator(expressionChars[i + 1]) && expressionChars[i + 1] != NOT) {
            // P ~ + Q
            throwError();
         }else if(isOperand(expressionChars[i]) && isOperand(expressionChars[i + 1])) {
            throwError();
         }
      }

      if(invalid(expressionChars[n - 1]) || isOperator(expressionChars[n - 1]) ||
         (isOperator(expressionChars[0]) && expressionChars[0] != '~')
      ) {
         // this is not handling invalid brackets ( )
         throwError();
      }
   }

   private static int precedence(char c) {
      if(c == '~') {
         return 5;

      }else if(c == '.') {
         return 4;
      
      }else if (c == '+'){
         return 3;
      
      }else {
         return 2;
      }
   }

   private static String toPostfix(String expression) {
      Stack<Character> stack = new Stack<>();

      StringBuilder postfix = new StringBuilder("");

      for(char c : expression.toCharArray()) {

         if(isOperand(c)) {
            postfix.append(c);
            map.put(c, false);

         } else if(isOperator(c)) {
            while(!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
               // هفضي
               postfix.append(stack.pop());
            }
            // h7t el operator el gdeed
            stack.push(c);

         } else if(c == '(') {
            stack.push(c);

         }else if (c == ')') {

            if(stack.isEmpty()) {
               throwError();
            }

            while (stack.peek() != '(') {
               if(stack.isEmpty()) {
                  throwError();
               }
               postfix.append(stack.pop());
            }
            stack.pop();
         }
      }

      while (!stack.isEmpty()) {
         if(stack.peek() == '(') {
            throwError();
         }
         postfix.append(stack.pop());
      }

      return postfix.toString();
   }

   public static boolean evaluateExpression(IExpression expression) {
      Scanner input = new Scanner(System.in);
      Stack<Boolean> stack = new Stack<>();
      
      for(char c : map.keySet()) {
         System.out.print(c + ": ");
         map.put(c, input.nextBoolean());
      }

      for(char c : expression.getRepresentation().toCharArray()) {
         if(isOperand(c)) {
            stack.push(map.get(c));
         
         }else {     // is operator
            boolean operand = stack.pop();
            switch (c) {
               case '~':
                  stack.push(!operand);
                  break;
               case '+':
                  stack.push(operand || stack.pop());
                  break;
               case '.':
                  stack.push(operand && stack.pop());
                  break;
               case '>':
                  // > ===> ~p || q
                  // pq>   == p -> q  == ~p || q
                  stack.push(!stack.pop() || operand);
                  break;
               default:
                  throwError();
                  break;
            }
         }
      }
      input.close();
      return stack.peek();
   }

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      Expression expression = new Expression(input.nextLine());

      try {
         expression.setRepresentation(LogicalExpression.normalize(expression.getRepresentation()));
         LogicalExpression.validate(expression.getRepresentation());

         expression.setRepresentation(LogicalExpression.toPostfix(expression.getRepresentation()));

         boolean b = LogicalExpression.evaluateExpression(expression);
         System.out.println(b);

      } catch (Exception e) {
         System.out.println("Wrong Expression");
      }

      input.close();
   }
   
}
