/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1657;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class TotemPopEvent extends Event {
/*    */   private final class_1657 entity;
/*    */   private int pops;
/*    */   
/*    */   public TotemPopEvent(class_1657 entity, int pops) {
/* 11 */     this.entity = entity;
/* 12 */     this.pops = pops;
/*    */   }
/*    */   
/*    */   public class_1657 getEntity() {
/* 16 */     return this.entity;
/*    */   }
/*    */   
/*    */   public int getPops() {
/* 20 */     return this.pops;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\TotemPopEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */