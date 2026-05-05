/*    */ package meteordevelopment.orbit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ICancellable
/*    */ {
/*    */   void setCancelled(boolean paramBoolean);
/*    */   
/*    */   default void cancel() {
/* 16 */     setCancelled(true);
/*    */   }
/*    */   
/*    */   boolean isCancelled();
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\ICancellable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */