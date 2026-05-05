/*    */ package thunder.hack.features.modules.player;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class ViewLock extends Module {
/*    */   private final Setting<Boolean> lockCurrent;
/*    */   public final Setting<Boolean> pitch;
/*    */   
/*    */   public ViewLock() {
/*  9 */     super("ViewLock", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 12 */     this.lockCurrent = new Setting("LockCurrent", Boolean.valueOf(true));
/* 13 */     this.pitch = new Setting("Pitch", Boolean.valueOf(true));
/* 14 */     this.pitchValue = new Setting("PitchValue", Float.valueOf(0.0F), Float.valueOf(-90.0F), Float.valueOf(90.0F), v -> ((Boolean)this.pitch.getValue()).booleanValue());
/* 15 */     this.yaw = new Setting("Yaw", Boolean.valueOf(true));
/* 16 */     this.yawValue = new Setting("YawValue", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F), v -> ((Boolean)this.yaw.getValue()).booleanValue());
/*    */   }
/*    */   public final Setting<Float> pitchValue; public final Setting<Boolean> yaw; public final Setting<Float> yawValue;
/*    */   public void onEnable() {
/* 20 */     if (((Boolean)this.lockCurrent.getValue()).booleanValue()) {
/* 21 */       this.yawValue.setValue(Float.valueOf(mc.field_1724.method_36454()));
/* 22 */       this.pitchValue.setValue(Float.valueOf(mc.field_1724.method_36455()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onRender3D(class_4587 m) {
/* 27 */     if (((Boolean)this.pitch.getValue()).booleanValue()) mc.field_1724.method_36457(((Float)this.pitchValue.getValue()).floatValue()); 
/* 28 */     if (((Boolean)this.yaw.getValue()).booleanValue()) mc.field_1724.method_36456(((Float)this.yawValue.getValue()).floatValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\ViewLock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */