/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ public class AntiBadEffects extends Module {
/*    */   private final Setting<Boolean> blindness;
/*    */   private final Setting<Boolean> nausea;
/*    */   private final Setting<Boolean> miningFatigue;
/*    */   
/*    */   public AntiBadEffects() {
/*  9 */     super("AntiBadEffects", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 12 */     this.blindness = new Setting("Blindness", Boolean.valueOf(true));
/* 13 */     this.nausea = new Setting("Nausea", Boolean.valueOf(true));
/* 14 */     this.miningFatigue = new Setting("MiningFatigue", Boolean.valueOf(true));
/* 15 */     this.levitation = new Setting("Levitation", Boolean.valueOf(true));
/* 16 */     this.slowness = new Setting("Slowness", Boolean.valueOf(true));
/* 17 */     this.jumpBoost = new Setting("JumpBoost", Boolean.valueOf(true));
/*    */   }
/*    */   private final Setting<Boolean> levitation; private final Setting<Boolean> slowness; private final Setting<Boolean> jumpBoost;
/*    */   public void onUpdate() {
/* 21 */     if (mc.field_1724.method_6059(class_1294.field_5919) && ((Boolean)this.blindness.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5919); 
/* 22 */     if (mc.field_1724.method_6059(class_1294.field_5916) && ((Boolean)this.nausea.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5916); 
/* 23 */     if (mc.field_1724.method_6059(class_1294.field_5901) && ((Boolean)this.miningFatigue.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5901); 
/* 24 */     if (mc.field_1724.method_6059(class_1294.field_5902) && ((Boolean)this.levitation.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5902); 
/* 25 */     if (mc.field_1724.method_6059(class_1294.field_5909) && ((Boolean)this.slowness.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5909); 
/* 26 */     if (mc.field_1724.method_6059(class_1294.field_5913) && ((Boolean)this.jumpBoost.getValue()).booleanValue()) mc.field_1724.method_6016(class_1294.field_5913); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AntiBadEffects.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */