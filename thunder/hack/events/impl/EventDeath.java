/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1657;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventDeath extends Event {
/*    */   private final class_1657 player;
/*    */   
/*    */   public EventDeath(class_1657 player) {
/* 10 */     this.player = player;
/*    */   }
/*    */   
/*    */   public class_1657 getPlayer() {
/* 14 */     return this.player;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventDeath.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */