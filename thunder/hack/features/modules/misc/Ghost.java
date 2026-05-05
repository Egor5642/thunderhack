/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class Ghost extends Module {
/*    */   private boolean bypass;
/*    */   
/*    */   public Ghost() {
/* 12 */     super("Ghost", Module.Category.MISC);
/*    */ 
/*    */     
/* 15 */     this.bypass = false;
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 19 */     this.bypass = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 24 */     if (mc.field_1724 != null) mc.field_1724.method_7331(); 
/* 25 */     this.bypass = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 30 */     if (mc.field_1724 == null || mc.field_1687 == null)
/* 31 */       return;  if (mc.field_1724.method_6032() == 0.0F) {
/* 32 */       mc.field_1724.method_6033(20.0F);
/* 33 */       this.bypass = true;
/* 34 */       mc.method_1507(null);
/* 35 */       mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321());
/* 36 */       sendMessage(ClientSettings.isRu() ? "Для возрождения выключи модуль!" : "To revive, turn off the module!");
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send event) {
/* 42 */     if (this.bypass && event.getPacket() instanceof net.minecraft.class_2828) event.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\Ghost.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */