/*    */ package thunder.hack.features.modules.combat;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1657;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import thunder.hack.core.manager.player.CombatManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.base.TrapModule;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public final class AutoTrap extends TrapModule {
/* 11 */   private final Setting<CombatManager.TargetBy> targetBy = new Setting("Target By", CombatManager.TargetBy.Distance);
/* 12 */   private final Setting<Boolean> targetMovingPlayers = new Setting("MovingPlayers", Boolean.valueOf(false));
/*    */   
/*    */   public AutoTrap() {
/* 15 */     super("AutoTrap", Module.Category.COMBAT);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean needNewTarget() {
/* 20 */     return (this.target == null || this.target
/* 21 */       .method_5739((class_1297)mc.field_1724) > ((Float)this.range.getValue()).floatValue() || this.target
/* 22 */       .method_6032() + this.target.method_6067() <= 0.0F || this.target
/* 23 */       .method_29504());
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected class_1657 getTarget() {
/* 28 */     return Managers.COMBAT.getTarget(((Float)this.range.getValue()).floatValue(), (CombatManager.TargetBy)this.targetBy.getValue(), p -> (p.method_18798().method_1027() < 0.08D || ((Boolean)this.targetMovingPlayers.getValue()).booleanValue()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\AutoTrap.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */