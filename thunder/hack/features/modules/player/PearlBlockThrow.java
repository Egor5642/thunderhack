/*    */ package thunder.hack.features.modules.player;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2885;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IPlayerInteractBlockC2SPacket;
/*    */ 
/*    */ public class PearlBlockThrow extends Module {
/*    */   public PearlBlockThrow() {
/* 13 */     super("PearlBlockThrow", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPackerSend(PacketEvent.Send event) {
/* 18 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2885) { class_2885 p = (class_2885)class_2596; if (mc.field_1724.method_6047().method_7909() == class_1802.field_8634)
/* 19 */         ((IPlayerInteractBlockC2SPacket)p).setHand(class_1268.field_5810);  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\PearlBlockThrow.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */