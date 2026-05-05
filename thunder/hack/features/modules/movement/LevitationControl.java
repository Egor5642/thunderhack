/*    */ package thunder.hack.features.modules.movement;
/*    */ import net.minecraft.class_1294;
/*    */ import thunder.hack.events.impl.EventMove;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class LevitationControl extends Module {
/*    */   private final Setting<Integer> upAmplifier;
/*    */   
/*    */   public LevitationControl() {
/* 11 */     super("LevitCtrl", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 14 */     this.upAmplifier = new Setting("Up Speed", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(5));
/* 15 */     this.downAmplifier = new Setting("Down Speed", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(5));
/*    */   } private final Setting<Integer> downAmplifier;
/*    */   @EventHandler
/*    */   public void onMove(EventMove e) {
/* 19 */     if (mc.field_1724.method_6059(class_1294.field_5902)) {
/* 20 */       int amplifier = mc.field_1724.method_6112(class_1294.field_5902).method_5578();
/* 21 */       if (mc.field_1690.field_1903.method_1434()) { e.setY((0.05D * (amplifier + 1) - e.getY()) * 0.2D * ((Integer)this.upAmplifier.getValue()).intValue() * 100.0D); }
/* 22 */       else if (mc.field_1690.field_1832.method_1434()) { e.setY(-((0.05D * (amplifier + 1) - e.getY()) * 0.2D * ((Integer)this.downAmplifier.getValue()).intValue() * 100.0D)); }
/* 23 */       else { e.setY(0.0D); }
/* 24 */        e.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\LevitationControl.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */