
interface Expression {
   String getRepresentation();
   void setRepresentation(String representation);
}

interface LogicalExpressionSolver {
   boolean evaluateExpression(Expression expression); 
}
