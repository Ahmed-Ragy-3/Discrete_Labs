import java.util.HashMap;
import java.util.Map;

public class InferenceRule implements IInferenceRule {

   static final char[] defaultSymbols = new char[] {'P', 'Q', 'R', 'S'};
   Map<Character, Character> map = new HashMap<>();

   private String name;

   private String expression1;
   private String expression2;

   private String inference;

   public InferenceRule(String name, String str) {
      this.name = name;
      StringBuilder strb = new StringBuilder();
      str = str.replaceAll(" ", "");

      int i = 0;
      while (str.charAt(i) != ',') {
         strb.append(str.charAt(i));
         i++;
      }
      i++;
      this.expression1 = strb.toString();

      strb = new StringBuilder();

      while (str.charAt(i) != ':') {
         strb.append(str.charAt(i));
         i++;
      }
      i++;
      this.expression2 = strb.toString();

      this.inference = str.substring(i, str.length());

      // System.out.println("%s %s %s %s"
      // .formatted(this.name, this.expression1, 
      // this.expression2, this.inference));
   }

   public InferenceRule(String name, Expression exp1, Expression exp2, Expression inference) {
      this.name = name;
      this.expression1 = exp1.getRepresentation();
      this.expression2 = exp2.getRepresentation();
      this.inference = inference.getRepresentation();
   }

   private static boolean isOperand(char c) {
      // a ---> b
      // A ---> B
      c = Character.toLowerCase(c);
      return c >= 'a' && c <= 'z' && c != 'v';
   }

   private boolean matches(String exp1, String exp2) {
      exp1 = exp1.replaceAll(" ", "");
      exp2 = exp2.replaceAll(" ", "");

      int i = 0;
      for(char c : exp1.toCharArray()) {
         if(isOperand(c)) {
            map.put(defaultSymbols[i], c);
            exp1 = exp1.replaceAll(Character.toString(c), Character.toString(defaultSymbols[i]));
            exp2 = exp2.replaceAll(Character.toString(c), Character.toString(defaultSymbols[i]));
            i++;
         }
      }

      for(char c : exp2.toCharArray()) {
         if(isOperand(c) && !map.containsKey(c)) {
            map.put(defaultSymbols[i], c);
            exp1 = exp1.replaceAll(Character.toString(c), Character.toString(defaultSymbols[i]));
            exp2 = exp2.replaceAll(Character.toString(c), Character.toString(defaultSymbols[i]));
            i++;
         }
      }
      
      
      // System.out.println(this.expression1.replaceAll(" ", "") + " - rule - " +
      // this.expression2.replaceAll(" ", ""));
       
      //  System.out.println(exp1 + " - " + exp2);
      //  System.out.println("------------------------------------------------------------------------");

      return exp1.compareTo(this.expression1.replaceAll(" ", "")) == 0 && 
             exp2.compareTo(this.expression2.replaceAll(" ", "")) == 0;
   }
   
   @Override
   public boolean matches(Expression exp1, Expression exp2) {
      return matches(exp1.getRepresentation(), exp2.getRepresentation()) || 
             matches(exp2.getRepresentation(), exp1.getRepresentation());
   }

   @Override
   public Expression apply(Expression exp1, Expression exp2) {
      String inf = new String(inference);

      for(char c : map.keySet()) {
         inf = inf.replaceAll(Character.toString(c), Character.toString(map.get(c)));
      }

      return (matches(exp1, exp2) ? new Expression(inf) : null);
   }

   public static char[] getDefaultsymbols() {
      return defaultSymbols;
   }

   public Map<Character, Character> getMap() {
      return map;
   }

   public void setMap(Map<Character, Character> map) {
      this.map = map;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getExpression1() {
      return expression1;
   }

   public void setExpression1(String exp1) {
      this.expression1 = exp1;
   }

   public String getExpression2() {
      return expression2;
   }

   public void setExpression2(String exp2) {
      this.expression2 = exp2;
   }

   public String getInference() {
      return inference;
   }

   public void setInference(String inference) {
      this.inference = inference;
   }

}