interface IInferenceRule {
   boolean matches(Expression exp1, Expression exp2);
   Expression apply(Expression exp1, Expression exp2);
}

interface IInferenceEngine {
   void addRule(InferenceRule rule);
   void addExpression(Expression exp);
   java.beans.Expression applyRules();
}