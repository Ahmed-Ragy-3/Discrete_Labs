import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

public class InferenceEngine implements IInferenceEngine {

   private List<Expression> expressions;
   private List<InferenceRule> rules;

   public InferenceEngine() {
      expressions = new ArrayList<>();
   }

   @Override
   public void addExpression(Expression exp) {
      expressions.add(exp);
   }

   @Override
   public void addRule(InferenceRule rule) {
      rules.add(rule);
   }

   @Override
   public Expression applyRules() {
      return null;
   }
   
   public static void main(String[] args) {
      
   }
}
