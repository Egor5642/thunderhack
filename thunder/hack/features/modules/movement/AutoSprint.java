/*    */ package thunder.hack.features.modules.movement;
/*    */ 
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.combat.Aura;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AutoSprint extends Module {
/*    */   public AutoSprint() {
/* 10 */     super("AutoSprint", Module.Category.MOVEMENT);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 15 */     this.stopWhileUsing = new Setting("StopWhileUsing", Boolean.valueOf(false));
/* 16 */     this.pauseWhileAura = new Setting("PauseWhileAura", Boolean.valueOf(false));
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 20 */     mc.field_1724.method_5728((mc.field_1724
/* 21 */         .method_7344().method_7586() > 6 && !mc.field_1724.field_5976 && mc.field_1724.field_3913.field_3905 > 0.0F && (
/*    */ 
/*    */         
/* 24 */         !mc.field_1724.method_5715() || (ModuleManager.noSlow.isEnabled() && ((Boolean)ModuleManager.noSlow.sneak.getValue()).booleanValue())) && (
/* 25 */         !mc.field_1724.method_6115() || !((Boolean)this.stopWhileUsing.getValue()).booleanValue()) && (
/* 26 */         !ModuleManager.aura.isEnabled() || Aura.target == null || !((Boolean)this.pauseWhileAura.getValue()).booleanValue())));
/*    */   }
/*    */   
/*    */   public static final Setting<Boolean> sprint = new Setting("KeepSprint", Boolean.valueOf(true));
/*    */   public static final Setting<Float> motion = new Setting("Motion", Float.valueOf(1.0F), Float.valueOf(0.0F), Float.valueOf(1.0F), v -> ((Boolean)sprint.getValue()).booleanValue());
/*    */   private final Setting<Boolean> stopWhileUsing;
/*    */   private final Setting<Boolean> pauseWhileAura;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\AutoSprint.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */