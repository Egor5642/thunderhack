/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventAttackBlock extends Event {
/*    */   private class_2338 blockPos;
/*    */   private class_2350 enumFacing;
/*    */   
/*    */   public EventAttackBlock(class_2338 blockPos, class_2350 enumFacing) {
/* 12 */     this.blockPos = blockPos;
/* 13 */     this.enumFacing = enumFacing;
/*    */   }
/*    */   
/*    */   public class_2338 getBlockPos() {
/* 17 */     return this.blockPos;
/*    */   }
/*    */   
/*    */   public void setBlockPos(class_2338 blockPos) {
/* 21 */     this.blockPos = blockPos;
/*    */   }
/*    */   
/*    */   public class_2350 getEnumFacing() {
/* 25 */     return this.enumFacing;
/*    */   }
/*    */   
/*    */   public void setEnumFacing(class_2350 enumFacing) {
/* 29 */     this.enumFacing = enumFacing;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventAttackBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */