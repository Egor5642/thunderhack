/*    */ package thunder.hack.features.modules.player;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2708;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IPlayerPositionLookS2CPacket;
/*    */ 
/*    */ public class NoServerRotate extends Module {
/*    */   public NoServerRotate() {
/* 11 */     super("NoServerRotate", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 16 */     if (fullNullCheck())
/* 17 */       return;  class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2708) { class_2708 pac = (class_2708)class_2596;
/* 18 */       ((IPlayerPositionLookS2CPacket)pac).setYaw(mc.field_1724.method_36454());
/* 19 */       ((IPlayerPositionLookS2CPacket)pac).setPitch(mc.field_1724.method_36455()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\NoServerRotate.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */