/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.Timer;
/*    */ 
/*    */ public class PortalGodMode
/*    */   extends Module {
/* 12 */   private Timer confirmTimer = new Timer();
/*    */   private boolean teleported;
/*    */   
/*    */   public PortalGodMode() {
/* 16 */     super("PortalGodMode", Module.Category.PLAYER);
/* 17 */     this.confirmTimer.setMs(99999L);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send e) {
/* 22 */     if (e.getPacket() instanceof net.minecraft.class_2793 && this.confirmTimer.getPassedTimeMs() < 5000L) {
/* 23 */       this.teleported = true;
/* 24 */       e.cancel();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 30 */     this.teleported = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 35 */     for (int x = (int)(mc.field_1724.method_23317() - 2.0D); x < mc.field_1724.method_23317() + 2.0D; x++) {
/* 36 */       for (int z = (int)(mc.field_1724.method_23321() - 2.0D); z < mc.field_1724.method_23321() + 2.0D; z++) {
/* 37 */         for (int y = (int)(mc.field_1724.method_23318() - 2.0D); y < mc.field_1724.method_23318() + 2.0D; y++) {
/* 38 */           if (mc.field_1687.method_8320(class_2338.method_49637(x, y, z)).method_26204() == class_2246.field_10316)
/* 39 */             this.confirmTimer.reset(); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   } public String getDisplayInfo() {
/* 44 */     return this.teleported ? "God" : ((this.confirmTimer.getPassedTimeMs() < 5000L) ? "Ready" : "Waiting");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\PortalGodMode.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */