/*    */ package thunder.hack.utility;
/*    */ 
/*    */ public class Timer {
/*    */   private long time;
/*    */   
/*    */   public Timer() {
/*  7 */     reset();
/*    */   }
/*    */   
/*    */   public boolean passedS(double s) {
/* 11 */     return (getMs(System.nanoTime() - this.time) >= (long)(s * 1000.0D));
/*    */   }
/*    */   
/*    */   public boolean passedMs(long ms) {
/* 15 */     return (getMs(System.nanoTime() - this.time) >= ms);
/*    */   }
/*    */   
/*    */   public boolean every(long ms) {
/* 19 */     boolean passed = (getMs(System.nanoTime() - this.time) >= ms);
/* 20 */     if (passed)
/* 21 */       reset(); 
/* 22 */     return passed;
/*    */   }
/*    */   
/*    */   public void setMs(long ms) {
/* 26 */     this.time = System.nanoTime() - ms * 1000000L;
/*    */   }
/*    */   
/*    */   public long getPassedTimeMs() {
/* 30 */     return getMs(System.nanoTime() - this.time);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 34 */     this.time = System.nanoTime();
/*    */   }
/*    */   
/*    */   public long getMs(long time) {
/* 38 */     return time / 1000000L;
/*    */   }
/*    */   
/*    */   public long getTimeMs() {
/* 42 */     return getMs(System.nanoTime() - this.time);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\Timer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */