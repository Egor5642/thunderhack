/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventMove extends Event {
/*    */   public double x;
/*    */   
/*    */   public EventMove(double x, double y, double z) {
/*  9 */     this.x = x;
/* 10 */     this.y = y;
/* 11 */     this.z = z;
/*    */   }
/*    */   public double y; public double z;
/*    */   public void setY(double y) {
/* 15 */     this.y = y;
/*    */   }
/*    */   
/*    */   public void setZ(double z) {
/* 19 */     this.z = z;
/*    */   }
/*    */   
/*    */   public void setX(double x) {
/* 23 */     this.x = x;
/*    */   }
/*    */   
/*    */   public double getX() {
/* 27 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 31 */     return this.y;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 35 */     return this.z;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventMove.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */