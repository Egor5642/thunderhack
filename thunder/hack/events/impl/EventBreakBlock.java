/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventBreakBlock extends Event {
/*    */   private class_2338 bp;
/*    */   
/*    */   public EventBreakBlock(class_2338 bp) {
/* 10 */     this.bp = bp;
/*    */   }
/*    */   
/*    */   public class_2338 getPos() {
/* 14 */     return this.bp;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventBreakBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */