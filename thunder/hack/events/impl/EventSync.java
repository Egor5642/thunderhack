/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventSync extends Event {
/*    */   public EventSync(float yaw, float pitch) {
/*  7 */     this.yaw = yaw;
/*  8 */     this.pitch = pitch;
/*    */   }
/*    */   
/*    */   float yaw;
/*    */   float pitch;
/*    */   Runnable postAction;
/*    */   
/*    */   public float getYaw() {
/* 16 */     return this.yaw;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 20 */     return this.pitch;
/*    */   }
/*    */   
/*    */   public void addPostAction(Runnable r) {
/* 24 */     this.postAction = r;
/*    */   }
/*    */   
/*    */   public Runnable getPostAction() {
/* 28 */     return this.postAction;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventSync.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */