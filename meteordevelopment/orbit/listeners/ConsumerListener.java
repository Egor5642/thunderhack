/*    */ package meteordevelopment.orbit.listeners;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumerListener<T>
/*    */   implements IListener
/*    */ {
/*    */   private final Class<?> target;
/*    */   private final int priority;
/*    */   private final Consumer<T> executor;
/*    */   
/*    */   public ConsumerListener(Class<?> target, int priority, Consumer<T> executor) {
/* 16 */     this.target = target;
/* 17 */     this.priority = priority;
/* 18 */     this.executor = executor;
/*    */   }
/*    */   
/*    */   public ConsumerListener(Class<?> target, Consumer<T> executor) {
/* 22 */     this(target, 0, executor);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void call(Object event) {
/* 28 */     this.executor.accept((T)event);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<?> getTarget() {
/* 33 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority() {
/* 38 */     return this.priority;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStatic() {
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\listeners\ConsumerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */