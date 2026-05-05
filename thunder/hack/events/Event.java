/*    */ package thunder.hack.events;
/*    */ 
/*    */ public class Event {
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public boolean isCancelled() {
/*  7 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void cancel() {
/* 11 */     this.cancelled = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\Event.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */