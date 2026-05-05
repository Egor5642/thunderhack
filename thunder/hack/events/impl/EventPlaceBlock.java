/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_2338;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventPlaceBlock extends Event {
/*    */   private final class_2338 blockPos;
/*    */   private final class_2248 block;
/*    */   
/*    */   public EventPlaceBlock(class_2338 blockPos, class_2248 block) {
/* 12 */     this.blockPos = blockPos;
/* 13 */     this.block = block;
/*    */   }
/*    */   
/*    */   public class_2338 getBlockPos() {
/* 17 */     return this.blockPos;
/*    */   }
/*    */   
/*    */   public class_2248 getBlock() {
/* 21 */     return this.block;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventPlaceBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */