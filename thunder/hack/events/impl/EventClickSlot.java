/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_1713;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventClickSlot extends Event {
/*    */   private final class_1713 slotActionType;
/*    */   private final int slot;
/*    */   
/*    */   public EventClickSlot(class_1713 slotActionType, int slot, int button, int id) {
/* 11 */     this.slot = slot;
/* 12 */     this.button = button;
/* 13 */     this.id = id;
/* 14 */     this.slotActionType = slotActionType;
/*    */   }
/*    */   private final int button; private final int id;
/*    */   public class_1713 getSlotActionType() {
/* 18 */     return this.slotActionType;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 22 */     return this.slot;
/*    */   }
/*    */   
/*    */   public int getButton() {
/* 26 */     return this.button;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 30 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventClickSlot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */