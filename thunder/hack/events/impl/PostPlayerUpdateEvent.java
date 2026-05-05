/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class PostPlayerUpdateEvent extends Event {
/*    */   private int iterations;
/*    */   
/*    */   public int getIterations() {
/*  9 */     return this.iterations;
/*    */   }
/*    */   
/*    */   public void setIterations(int in) {
/* 13 */     this.iterations = in;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\PostPlayerUpdateEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */