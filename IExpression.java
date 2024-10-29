package Discrete_Labs;

interface IExpression {
   String getRepresentation();
   void setRepresentation(String representation);
}

interface LogicalExpressionSolver {
   boolean evaluateExpression(IExpression expression); 
}
