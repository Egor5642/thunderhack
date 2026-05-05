/*   */ package meteordevelopment.orbit;
/*   */ 
/*   */ 
/*   */ public class NoLambdaFactoryException
/*   */   extends RuntimeException
/*   */ {
/*   */   public NoLambdaFactoryException(Class<?> klass) {
/* 8 */     super("No registered lambda listener for '" + klass.getName() + "'.");
/*   */   }
/*   */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\NoLambdaFactoryException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */