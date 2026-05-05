/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventSprint extends Event {
/*    */   private boolean sprintState;
/*    */   
/*    */   public EventSprint(boolean sprintState) {
/*  9 */     this.sprintState = sprintState;
/*    */   }
/*    */   
/*    */   public boolean getSprintState() {
/* 13 */     return this.sprintState;
/*    */   }
/*    */   
/*    */   public void setSprintState(boolean sprintState) {
/* 17 */     this.sprintState = sprintState;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventSprint.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */