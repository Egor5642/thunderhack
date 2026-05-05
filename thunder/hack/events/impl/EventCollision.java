/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventCollision extends Event {
/*    */   private class_2680 bs;
/*    */   private class_2338 bp;
/*    */   
/*    */   public EventCollision(class_2680 bs, class_2338 bp) {
/* 12 */     this.bs = bs;
/* 13 */     this.bp = bp;
/*    */   }
/*    */   
/*    */   public class_2680 getState() {
/* 17 */     return this.bs;
/*    */   }
/*    */   
/*    */   public class_2338 getPos() {
/* 21 */     return this.bp;
/*    */   }
/*    */   
/*    */   public void setState(class_2680 bs) {
/* 25 */     this.bs = bs;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventCollision.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */