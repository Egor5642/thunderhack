/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventMouse extends Event {
/*    */   int button;
/*    */   
/*    */   public int getButton() {
/*  9 */     return this.button;
/*    */   }
/*    */   int action;
/*    */   public int getAction() {
/* 13 */     return this.action;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EventMouse(int b, int action) {
/* 19 */     this.button = b;
/* 20 */     this.action = action;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventMouse.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */