/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.ILivingEntity;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class NoJumpDelay extends Module {
/*    */   public NoJumpDelay() {
/*  9 */     super("NoJumpDelay", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 12 */     this.delay = new Setting("Delay", Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(4));
/*    */   }
/*    */   private final Setting<Integer> delay;
/*    */   public void onUpdate() {
/* 16 */     if (((ILivingEntity)mc.field_1724).getLastJumpCooldown() > ((Integer)this.delay.getValue()).intValue())
/* 17 */       ((ILivingEntity)mc.field_1724).setLastJumpCooldown(((Integer)this.delay.getValue()).intValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\NoJumpDelay.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */