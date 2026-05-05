/*    */ package thunder.hack.utility;
/*    */ 
/*    */ import thunder.hack.core.manager.IManager;
/*    */ 
/*    */ public class TickTimer {
/*    */   private int time;
/*    */   
/*    */   public TickTimer() {
/*  9 */     reset();
/*    */   }
/*    */   
/*    */   public boolean passedTicks(long t) {
/* 13 */     if (getPassedTicks() < 0)
/* 14 */       reset(); 
/* 15 */     return (getPassedTicks() >= t);
/*    */   }
/*    */   
/*    */   public boolean every(long ms) {
/* 19 */     if (getPassedTicks() < 0)
/* 20 */       reset(); 
/* 21 */     boolean passed = (getPassedTicks() >= ms);
/* 22 */     if (passed)
/* 23 */       reset(); 
/* 24 */     return passed;
/*    */   }
/*    */   
/*    */   public void set(int t) {
/* 28 */     this.time = t;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 32 */     this.time = (IManager.mc.field_1724 == null) ? 0 : IManager.mc.field_1724.field_6012;
/*    */   }
/*    */   
/*    */   private int getPassedTicks() {
/* 36 */     return (IManager.mc.field_1724 == null) ? 0 : (IManager.mc.field_1724.field_6012 - this.time);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\TickTimer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */