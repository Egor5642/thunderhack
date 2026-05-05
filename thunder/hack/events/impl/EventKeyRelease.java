/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventKeyRelease extends Event {
/*    */   private final int key;
/*    */   private final int scanCode;
/*    */   
/*    */   public EventKeyRelease(int key, int scanCode) {
/* 10 */     this.key = key;
/* 11 */     this.scanCode = scanCode;
/*    */   }
/*    */   
/*    */   public int getKey() {
/* 15 */     return this.key;
/*    */   }
/*    */   
/*    */   public int getScanCode() {
/* 19 */     return this.scanCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventKeyRelease.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */