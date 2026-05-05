/*    */ package thunder.hack.features.modules.client;
/*    */ import baritone.api.BaritoneAPI;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.EventSetting;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public final class BaritoneSettings extends Module {
/*    */   public final Setting<Boolean> allowBreakBlock;
/*    */   public final Setting<Boolean> allowPlace;
/*    */   public final Setting<Boolean> allowSprint;
/*    */   
/*    */   public BaritoneSettings() {
/* 14 */     super("BaritoneSettings", Module.Category.CLIENT);
/*    */ 
/*    */     
/* 17 */     this.allowBreakBlock = new Setting("AllowBreakBlock", Boolean.valueOf(true));
/* 18 */     this.allowPlace = new Setting("AllowPlace", Boolean.valueOf(true));
/* 19 */     this.allowSprint = new Setting("AllowSprint", Boolean.valueOf(true));
/* 20 */     this.debug = new Setting("Debug", Boolean.valueOf(false));
/* 21 */     this.enterPortal = new Setting("EnterPortal", Boolean.valueOf(false));
/* 22 */     this.desktopNotifications = new Setting("DesktopNotifications", Boolean.valueOf(false));
/*    */   } public final Setting<Boolean> debug; public final Setting<Boolean> enterPortal; public final Setting<Boolean> desktopNotifications;
/*    */   @EventHandler
/*    */   public void onSettingChange(EventSetting e) {
/* 26 */     if (!ThunderHack.baritone) {
/* 27 */       sendMessage(ClientSettings.isRu() ? "Баритон не найден (можешь скачать на https://meteorclient.com)" : "Baritone not found (you can download it at https://meteorclient.com)");
/*    */       return;
/*    */     } 
/* 30 */     (BaritoneAPI.getSettings()).allowBreak.value = this.allowBreakBlock.getValue();
/* 31 */     (BaritoneAPI.getSettings()).allowPlace.value = this.allowPlace.getValue();
/* 32 */     (BaritoneAPI.getSettings()).allowSprint.value = this.allowSprint.getValue();
/* 33 */     (BaritoneAPI.getSettings()).chatDebug.value = this.debug.getValue();
/* 34 */     (BaritoneAPI.getSettings()).enterPortal.value = this.enterPortal.getValue();
/* 35 */     (BaritoneAPI.getSettings()).desktopNotifications.value = this.desktopNotifications.getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isToggleable() {
/* 40 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\BaritoneSettings.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */