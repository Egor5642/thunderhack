/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_243;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventTravel extends Event {
/*    */   private class_243 mVec;
/*    */   private boolean pre;
/*    */   
/*    */   public EventTravel(class_243 mVec, boolean pre) {
/* 11 */     this.mVec = mVec;
/* 12 */     this.pre = pre;
/*    */   }
/*    */   
/*    */   public class_243 getmVec() {
/* 16 */     return this.mVec;
/*    */   }
/*    */   
/*    */   public boolean isPre() {
/* 20 */     return this.pre;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventTravel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */