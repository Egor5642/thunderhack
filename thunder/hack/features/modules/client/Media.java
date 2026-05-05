/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_640;
/*    */ import net.minecraft.class_7439;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IGameMessageS2CPacket;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public final class Media extends Module {
/* 14 */   public static final Setting<Boolean> skinProtect = new Setting("Skin Protect", Boolean.valueOf(true));
/* 15 */   public static final Setting<Boolean> nickProtect = new Setting("Nick Protect", Boolean.valueOf(true));
/*    */   
/*    */   public Media() {
/* 18 */     super("Media", Module.Category.CLIENT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 23 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_7439) { class_7439 pac = (class_7439)class_2596; if (((Boolean)nickProtect.getValue()).booleanValue())
/* 24 */         for (class_640 ple : mc.field_1724.field_3944.method_2880()) {
/* 25 */           if (pac.comp_763().getString().contains(ple.method_2966().getName())) {
/* 26 */             IGameMessageS2CPacket packet = (IGameMessageS2CPacket)e.getPacket();
/* 27 */             packet.setContent(class_2561.method_30163(pac.comp_763().getString().replace(ple.method_2966().getName(), "Protected")));
/*    */           } 
/*    */         }   }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\Media.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */