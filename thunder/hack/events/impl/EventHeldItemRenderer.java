/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventHeldItemRenderer
/*    */   extends Event
/*    */ {
/*    */   private final class_1268 hand;
/*    */   private final class_1799 item;
/*    */   private float ep;
/*    */   private final class_4587 stack;
/*    */   
/*    */   public EventHeldItemRenderer(class_1268 hand, class_1799 item, float equipProgress, class_4587 stack) {
/* 17 */     this.hand = hand;
/* 18 */     this.item = item;
/* 19 */     this.ep = equipProgress;
/* 20 */     this.stack = stack;
/*    */   }
/*    */   
/*    */   public class_1268 getHand() {
/* 24 */     return this.hand;
/*    */   }
/*    */   
/*    */   public class_1799 getItem() {
/* 28 */     return this.item;
/*    */   }
/*    */   
/*    */   public float getEp() {
/* 32 */     return this.ep;
/*    */   }
/*    */   
/*    */   public class_4587 getStack() {
/* 36 */     return this.stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventHeldItemRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */