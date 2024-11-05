
public class ExpressionClass implements Expression {

   private String expression;

   public ExpressionClass(String expression) {
      this.expression = expression;
   }

   @Override
   public String getRepresentation() {
      return expression;
   }

   @Override
   public void setRepresentation(String representation) {
      this.expression = representation;
   }
  
}
