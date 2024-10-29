package Discrete_Labs;

public class Expression implements IExpression {

   private String expression;

   public Expression(String expression) {
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
