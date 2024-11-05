interface IInferenceRule {
   boolean matches(ExpressionClass exp1, ExpressionClass exp2);
   ExpressionClass apply(ExpressionClass exp1, ExpressionClass exp2);
}

interface IInferenceEngine {
   void addRule(InferenceRule rule);
   void addExpression(ExpressionClass exp);
   ExpressionClass applyRules();
}