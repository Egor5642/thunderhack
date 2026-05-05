/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2868;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class NoServerSlot extends Module {
/*    */   public NoServerSlot() {
/* 11 */     super("NoServerSlot", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive event) {
/* 16 */     if (event.getPacket() instanceof net.minecraft.class_2735) {
/* 17 */       event.cancel();
/* 18 */       sendPacket((class_2596)new class_2868((mc.field_1724.method_31548()).field_7545));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\NoServerSlot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */