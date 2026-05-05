/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1799;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventEatFood extends Event {
/*    */   private final class_1799 stack;
/*    */   
/*    */   public EventEatFood(class_1799 stack) {
/* 10 */     this.stack = stack;
/*    */   }
/*    */   
/*    */   public class_1799 getFood() {
/* 14 */     return this.stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventEatFood.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */