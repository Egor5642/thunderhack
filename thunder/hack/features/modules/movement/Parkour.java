/*    */ package thunder.hack.features.modules.movement;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class Parkour extends Module {
/*    */   private final Setting<Float> jumpFactor;
/*    */   
/*    */   public Parkour() {
/*  9 */     super("Parkour", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 12 */     this.jumpFactor = new Setting("JumpFactor", Float.valueOf(0.01F), Float.valueOf(0.001F), Float.valueOf(0.3F));
/*    */     
/* 14 */     this.delay = new Timer();
/*    */   } private final Timer delay;
/*    */   public void onRender3D(class_4587 stack) {
/* 17 */     if (mc.field_1724.method_24828() && 
/* 18 */       !mc.field_1690.field_1903.method_1434() && 
/* 19 */       !mc.field_1687.method_20812((class_1297)mc.field_1724, mc.field_1724.method_5829().method_1009(-((Float)this.jumpFactor.getValue()).floatValue(), 0.0D, -((Float)this.jumpFactor.getValue()).floatValue()).method_989(0.0D, -0.99D, 0.0D)).iterator().hasNext() && this.delay
/* 20 */       .every(150L))
/* 21 */       mc.field_1724.method_6043(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\Parkour.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */