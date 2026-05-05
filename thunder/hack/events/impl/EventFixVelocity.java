/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_243;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventFixVelocity extends Event {
/*    */   class_243 movementInput;
/*    */   float speed;
/*    */   float yaw;
/*    */   class_243 velocity;
/*    */   
/*    */   public EventFixVelocity(class_243 movementInput, float speed, float yaw, class_243 velocity) {
/* 13 */     this.movementInput = movementInput;
/* 14 */     this.speed = speed;
/* 15 */     this.yaw = yaw;
/* 16 */     this.velocity = velocity;
/*    */   }
/*    */   
/*    */   public class_243 getMovementInput() {
/* 20 */     return this.movementInput;
/*    */   }
/*    */   
/*    */   public float getSpeed() {
/* 24 */     return this.speed;
/*    */   }
/*    */   
/*    */   public class_243 getVelocity() {
/* 28 */     return this.velocity;
/*    */   }
/*    */   
/*    */   public void setVelocity(class_243 velocity) {
/* 32 */     this.velocity = velocity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventFixVelocity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */