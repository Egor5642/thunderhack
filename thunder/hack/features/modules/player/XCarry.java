/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class XCarry
/*    */   extends Module {
/*    */   public XCarry() {
/* 10 */     super("XCarry", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send e) {
/* 15 */     if (e.getPacket() instanceof net.minecraft.class_2815) e.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\XCarry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */