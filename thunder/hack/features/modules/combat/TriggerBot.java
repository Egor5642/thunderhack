/*    */ package thunder.hack.features.modules.combat;
/*    */ import java.util.Random;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1294;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2374;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.PlayerUpdateEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ 
/*    */ public final class TriggerBot extends Module {
/* 18 */   public final Setting<Float> attackRange = new Setting("Range", Float.valueOf(3.0F), Float.valueOf(1.0F), Float.valueOf(7.0F));
/* 19 */   public final Setting<BooleanSettingGroup> smartCrit = new Setting("SmartCrit", new BooleanSettingGroup(true));
/* 20 */   public final Setting<Boolean> onlySpace = (new Setting("OnlyCrit", Boolean.valueOf(false))).addToGroup(this.smartCrit);
/* 21 */   public final Setting<Boolean> autoJump = (new Setting("AutoJump", Boolean.valueOf(false))).addToGroup(this.smartCrit);
/* 22 */   public final Setting<Boolean> ignoreWalls = new Setting("IgnoreWalls", Boolean.valueOf(false));
/* 23 */   public final Setting<Boolean> pauseEating = new Setting("PauseWhileEating", Boolean.valueOf(false));
/* 24 */   public final Setting<Integer> minDelay = new Setting("RandomDelayMin", Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(20));
/* 25 */   public final Setting<Integer> maxDelay = new Setting("RandomDelayMax", Integer.valueOf(13), Integer.valueOf(0), Integer.valueOf(20));
/*    */   
/*    */   private int delay;
/* 28 */   private final Random random = new Random();
/*    */   
/*    */   public TriggerBot() {
/* 31 */     super("TriggerBot", Module.Category.COMBAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onAttack(PlayerUpdateEvent e) {
/* 36 */     if (mc.field_1724.method_6115() && ((Boolean)this.pauseEating.getValue()).booleanValue()) {
/*    */       return;
/*    */     }
/* 39 */     if (!mc.field_1690.field_1903.method_1434() && mc.field_1724.method_24828() && ((Boolean)this.autoJump.getValue()).booleanValue()) {
/* 40 */       mc.field_1724.method_6043();
/*    */     }
/*    */     
/* 43 */     if (!autoCrit() && 
/* 44 */       this.delay > 0) {
/* 45 */       this.delay--;
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 50 */     class_1297 ent = Managers.PLAYER.getRtxTarget(mc.field_1724.method_36454(), mc.field_1724.method_36455(), ((Float)this.attackRange.getValue()).floatValue(), ((Boolean)this.ignoreWalls.getValue()).booleanValue());
/* 51 */     if (ent != null && !Managers.FRIEND.isFriend(ent.method_5477().getString())) {
/* 52 */       mc.field_1761.method_2918((class_1657)mc.field_1724, ent);
/* 53 */       mc.field_1724.method_6104(class_1268.field_5808);
/*    */ 
/*    */       
/* 56 */       this.delay = this.random.nextInt(((Integer)this.minDelay.getValue()).intValue(), ((Integer)this.maxDelay.getValue()).intValue() + 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean autoCrit() {
/* 71 */     boolean reasonForSkipCrit = (!((BooleanSettingGroup)this.smartCrit.getValue()).isEnabled() || (mc.field_1724.method_31549()).field_7479 || mc.field_1724.method_6128() || ModuleManager.elytraPlus.isEnabled() || mc.field_1724.method_6059(class_1294.field_5919) || mc.field_1724.method_21754() || mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538())).method_26204() == class_2246.field_10343);
/*    */     
/* 73 */     if (mc.field_1724.field_6017 > 1.0F && mc.field_1724.field_6017 < 1.14D) {
/* 74 */       return false;
/*    */     }
/* 76 */     if (ModuleManager.aura.getAttackCooldown() < (mc.field_1724.method_24828() ? 1.0F : 0.9F)) {
/* 77 */       return false;
/*    */     }
/* 79 */     boolean mergeWithTargetStrafe = (!ModuleManager.targetStrafe.isEnabled() || !((Boolean)ModuleManager.targetStrafe.jump.getValue()).booleanValue());
/* 80 */     boolean mergeWithSpeed = (!ModuleManager.speed.isEnabled() || mc.field_1724.method_24828());
/*    */     
/* 82 */     if (!mc.field_1690.field_1903.method_1434() && mergeWithTargetStrafe && mergeWithSpeed && !((Boolean)this.onlySpace.getValue()).booleanValue() && !((Boolean)this.autoJump.getValue()).booleanValue()) {
/* 83 */       return true;
/*    */     }
/* 85 */     if (mc.field_1724.method_5771()) {
/* 86 */       return true;
/*    */     }
/* 88 */     if (!mc.field_1690.field_1903.method_1434() && ModuleManager.aura.isAboveWater()) {
/* 89 */       return true;
/*    */     }
/* 91 */     if (!reasonForSkipCrit)
/* 92 */       return (!mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.0F); 
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\TriggerBot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */