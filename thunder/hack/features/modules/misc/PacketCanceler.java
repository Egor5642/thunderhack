/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ 
/*    */ public final class PacketCanceler
/*    */   extends Module
/*    */ {
/* 12 */   private final Setting<Boolean> cliclSlot = new Setting("ClickSlotC2SPacket", Boolean.valueOf(false));
/* 13 */   private final Setting<Boolean> playerMovePosAndOnGround = new Setting("PositionAndOnGround", Boolean.valueOf(false));
/* 14 */   private final Setting<Boolean> playerMoveOnGroundOnly = new Setting("OnGroundOnly", Boolean.valueOf(false));
/* 15 */   private final Setting<Boolean> playerMoveLookAndOnGround = new Setting("LookAndOnGround", Boolean.valueOf(false));
/*    */   public PacketCanceler() {
/* 17 */     super("PacketCanceler", Module.Category.MISC);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   private void onPacketSend(PacketEvent.Send e) {
/* 23 */     if (e.getPacket() instanceof net.minecraft.class_2813 && ((Boolean)this.cliclSlot.getValue()).booleanValue()) {
/* 24 */       e.cancel();
/* 25 */     } else if (e.getPacket() instanceof net.minecraft.class_2828.class_2829 && ((Boolean)this.playerMovePosAndOnGround.getValue()).booleanValue()) {
/* 26 */       e.cancel();
/* 27 */     } else if (e.getPacket() instanceof net.minecraft.class_2828.class_5911 && ((Boolean)this.playerMoveOnGroundOnly.getValue()).booleanValue()) {
/* 28 */       e.cancel();
/* 29 */     } else if (e.getPacket() instanceof net.minecraft.class_2828.class_2831 && ((Boolean)this.playerMoveLookAndOnGround.getValue()).booleanValue()) {
/* 30 */       e.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\PacketCanceler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */