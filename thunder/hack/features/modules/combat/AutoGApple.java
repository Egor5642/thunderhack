/*    */ package thunder.hack.features.modules.combat;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1802;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.PostPlayerUpdateEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IMinecraftClient;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ import thunder.hack.utility.player.InventoryUtility;
/*    */ 
/*    */ public final class AutoGApple
/*    */   extends Module {
/* 17 */   public final Setting<Integer> Delay = new Setting("UseDelay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(2000));
/* 18 */   private final Setting<Float> health = new Setting("health", Float.valueOf(15.0F), Float.valueOf(1.0F), Float.valueOf(36.0F));
/* 19 */   public Setting<Boolean> absorption = new Setting("Absorption", Boolean.valueOf(false));
/* 20 */   public Setting<Boolean> autoTotemIntegration = new Setting("AutoTotemIntegration", Boolean.valueOf(true));
/*    */   
/*    */   private boolean isActive;
/* 23 */   private final Timer useDelay = new Timer();
/*    */   
/*    */   public AutoGApple() {
/* 26 */     super("AutoGApple", Module.Category.COMBAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onUpdate(PostPlayerUpdateEvent e) {
/* 31 */     if (fullNullCheck())
/* 32 */       return;  if (GapInOffHand()) {
/* 33 */       if (mc.field_1724.method_6032() + (((Boolean)this.absorption.getValue()).booleanValue() ? mc.field_1724.method_6067() : 0.0F) <= ((Float)this.health.getValue()).floatValue() && this.useDelay.passedMs(((Integer)this.Delay.getValue()).intValue())) {
/* 34 */         this.isActive = true;
/* 35 */         if (mc.field_1755 != null && !mc.field_1724.method_6115())
/* 36 */         { ((IMinecraftClient)mc).idoItemUse(); }
/*    */         else
/* 38 */         { mc.field_1690.field_1904.method_23481(true); } 
/* 39 */       } else if (this.isActive) {
/* 40 */         this.isActive = false;
/* 41 */         mc.field_1690.field_1904.method_23481(false);
/*    */       } 
/* 43 */     } else if (this.isActive) {
/* 44 */       this.isActive = false;
/* 45 */       mc.field_1690.field_1904.method_23481(false);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean GapInOffHand() {
/* 50 */     if (((Boolean)this.autoTotemIntegration.getValue()).booleanValue() && ModuleManager.autoTotem.isEnabled() && InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8463, class_1802.field_8367 }).found()) {
/* 51 */       if (!ModuleManager.autoTotem.rcGap.is(AutoTotem.RCGap.Off)) {
/* 52 */         return true;
/*    */       }
/* 54 */       sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */     } 
/*    */     
/* 57 */     return (!mc.field_1724.method_6079().method_7960() && (mc.field_1724.method_6079().method_7909() == class_1802.field_8463 || mc.field_1724.method_6079().method_7909() == class_1802.field_8367));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\AutoGApple.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */