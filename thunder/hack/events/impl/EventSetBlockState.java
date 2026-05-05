/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventSetBlockState extends Event {
/*    */   private final class_2338 pos;
/*    */   private final class_2680 state;
/*    */   private final class_2680 prevState;
/*    */   
/*    */   public EventSetBlockState(class_2338 pos, class_2680 state, class_2680 prevState) {
/* 13 */     this.pos = pos;
/* 14 */     this.state = state;
/* 15 */     this.prevState = prevState;
/*    */   }
/*    */   
/*    */   public class_2338 getPos() {
/* 19 */     return this.pos;
/*    */   }
/*    */   
/*    */   public class_2680 getState() {
/* 23 */     return this.state;
/*    */   }
/*    */   
/*    */   public class_2680 getPrevState() {
/* 27 */     return this.prevState;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventSetBlockState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */