/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventAttack extends Event {
/*    */   private class_1297 entity;
/*    */   boolean pre;
/*    */   
/*    */   public EventAttack(class_1297 entity, boolean pre) {
/* 11 */     this.entity = entity;
/* 12 */     this.pre = pre;
/*    */   }
/*    */   
/*    */   public class_1297 getEntity() {
/* 16 */     return this.entity;
/*    */   }
/*    */   
/*    */   public boolean isPre() {
/* 20 */     return this.pre;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventAttack.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */