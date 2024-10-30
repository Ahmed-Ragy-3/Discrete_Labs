import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InferenceEngine /*implements IInferenceEngine*/ {

   private List<Expression> expressions;
   private List<InferenceRule> rules;

   private InferenceRule targetRule;

   public InferenceEngine() {
      expressions = new ArrayList<>();
      rules = new ArrayList<>();
   }
   
   public void addExpression(Expression exp) {
      expressions.add(exp);
   }

   public void addRule(InferenceRule rule) {
      rules.add(rule);
      // System.out.println("%s %s %s %s"
      // .formatted(rule.getName(), rule.getExp1(), 
      // rule.getExp2(), rule.getInference()));
   }

   public Expression applyRules() {
      for (InferenceRule rule : rules) {
         if (rule.matches(expressions.get(0), expressions.get(1))) {
            targetRule = rule;
            return rule.apply(expressions.get(0), expressions.get(1));
         }
      }
      return null;
   }
   
   public static void main(String[] args) {
      InferenceEngine engine = new InferenceEngine();
      
      Scanner input;
      try {
         input = new Scanner(new File("rules.txt"));

         while (input.hasNextLine()) {
            String line = input.nextLine();
            String line2 = input.nextLine();
            // System.out.println(line + " ----- " + line2);
            engine.addRule(new InferenceRule(line, line2));
        }

      } catch (FileNotFoundException e) {
         System.out.println("File error");
      }

      input = new Scanner(System.in);

      engine.addExpression(new Expression(input.nextLine()));
      engine.addExpression(new Expression(input.nextLine()));
      
      Expression infered = engine.applyRules();
      
      if(infered == null) {
         System.out.println("The input expression cannot be inferred");
      }else {
         System.out.println(infered.getRepresentation());
         System.out.println(engine.targetRule.getName());
      }

      input.close();
   }


}