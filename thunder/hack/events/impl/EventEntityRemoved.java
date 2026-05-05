/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventEntityRemoved extends Event {
/*    */   public class_1297 entity;
/*    */   
/*    */   public EventEntityRemoved(class_1297 entity) {
/* 10 */     this.entity = entity;
/*    */   }
/*    */   
/*    */   public class_1297 getEntity() {
/* 14 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventEntityRemoved.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */