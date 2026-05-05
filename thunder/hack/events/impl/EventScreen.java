/*    */ package thunder.hack.events.impl;
/*    */ 
/*    */ import net.minecraft.class_437;
/*    */ import thunder.hack.events.Event;
/*    */ 
/*    */ public class EventScreen extends Event {
/*    */   private final class_437 screen;
/*    */   
/*    */   public EventScreen(class_437 screen) {
/* 10 */     this.screen = screen;
/*    */   }
/*    */   
/*    */   public class_437 getScreen() {
/* 14 */     return this.screen;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\events\impl\EventScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */