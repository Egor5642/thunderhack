/*     */ package thunder.hack.features.modules.render;public class ViewModel extends Module { public final Setting<Boolean> syncHands; public final Setting<SettingGroup> mainHand; public final Setting<Float> positionMainX; public final Setting<Float> positionMainY; public final Setting<Float> positionMainZ; public final Setting<Float> scaleMain; public final Setting<SettingGroup> rotationMain;
/*     */   public final Setting<Float> rotationMainX;
/*     */   public final Setting<Float> rotationMainY;
/*     */   public final Setting<Float> rotationMainZ;
/*     */   public final Setting<SettingGroup> animateMain;
/*     */   public final Setting<Boolean> animateMainX;
/*     */   public final Setting<Boolean> animateMainY;
/*     */   public final Setting<Boolean> animateMainZ;
/*     */   public final Setting<Float> speedAnimateMain;
/*     */   public final Setting<SettingGroup> offHand;
/*     */   public final Setting<Float> positionOffX;
/*     */   public final Setting<Float> positionOffY;
/*     */   public final Setting<Float> positionOffZ;
/*     */   
/*     */   public ViewModel() {
/*  16 */     super("ViewModel", Module.Category.RENDER);
/*     */ 
/*     */     
/*  19 */     this.syncHands = new Setting("SyncHands", Boolean.valueOf(true));
/*     */     
/*  21 */     this.mainHand = new Setting("MainHand", new SettingGroup(false, 0));
/*  22 */     this.positionMainX = (new Setting("positionMainX", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.mainHand);
/*  23 */     this.positionMainY = (new Setting("positionMainY", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.mainHand);
/*  24 */     this.positionMainZ = (new Setting("positionMainZ", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.mainHand);
/*  25 */     this.scaleMain = (new Setting("ScaleMain", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(1.5F))).addToGroup(this.mainHand);
/*     */     
/*  27 */     this.rotationMain = (new Setting("Rotation", new SettingGroup(false, 1))).addToGroup(this.mainHand);
/*  28 */     this.rotationMainX = (new Setting("rotationMainX", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationMain);
/*  29 */     this.rotationMainY = (new Setting("rotationMainY", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationMain);
/*  30 */     this.rotationMainZ = (new Setting("rotationMainZ", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationMain);
/*  31 */     this.animateMain = (new Setting("Animate", new SettingGroup(false, 1))).addToGroup(this.mainHand);
/*  32 */     this.animateMainX = (new Setting("animateMainX", Boolean.valueOf(false))).addToGroup(this.animateMain);
/*  33 */     this.animateMainY = (new Setting("animateMainY", Boolean.valueOf(false))).addToGroup(this.animateMain);
/*  34 */     this.animateMainZ = (new Setting("animateMainZ", Boolean.valueOf(false))).addToGroup(this.animateMain);
/*  35 */     this.speedAnimateMain = (new Setting("speedAnimateMain", Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(5.0F))).addToGroup(this.rotationMain);
/*     */     
/*  37 */     this.offHand = new Setting("OffHand", new SettingGroup(false, 0));
/*  38 */     this.positionOffX = (new Setting("positionOffX", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.offHand);
/*  39 */     this.positionOffY = (new Setting("positionOffY", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.offHand);
/*  40 */     this.positionOffZ = (new Setting("positionOffZ", Float.valueOf(0.0F), Float.valueOf(-3.0F), Float.valueOf(3.0F))).addToGroup(this.offHand);
/*  41 */     this.scaleOff = (new Setting("ScaleOff", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(1.5F))).addToGroup(this.offHand);
/*     */     
/*  43 */     this.rotationOff = (new Setting("RotationOff", new SettingGroup(false, 1))).addToGroup(this.offHand);
/*  44 */     this.rotationOffX = (new Setting("rotationOffX", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationOff);
/*  45 */     this.rotationOffY = (new Setting("rotationOffY", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationOff);
/*  46 */     this.rotationOffZ = (new Setting("rotationOffZ", Float.valueOf(0.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F))).addToGroup(this.rotationOff);
/*  47 */     this.animateOff = (new Setting("AnimateOff", new SettingGroup(false, 1))).addToGroup(this.offHand);
/*  48 */     this.animateOffX = (new Setting("animateOffX", Boolean.valueOf(false))).addToGroup(this.animateOff);
/*  49 */     this.animateOffY = (new Setting("animateOffY", Boolean.valueOf(false))).addToGroup(this.animateOff);
/*  50 */     this.animateOffZ = (new Setting("animateOffZ", Boolean.valueOf(false))).addToGroup(this.animateOff);
/*  51 */     this.speedAnimateOff = (new Setting("speedAnimateOff", Float.valueOf(1.0F), Float.valueOf(1.0F), Float.valueOf(5.0F))).addToGroup(this.rotationOff);
/*  52 */     this.eatMod = new Setting("Eat", new SettingGroup(false, 0));
/*  53 */     this.eatX = (new Setting("EatX", Float.valueOf(1.0F), Float.valueOf(-1.0F), Float.valueOf(2.0F))).addToGroup(this.eatMod);
/*  54 */     this.eatY = (new Setting("EatY", Float.valueOf(1.0F), Float.valueOf(-1.0F), Float.valueOf(2.0F))).addToGroup(this.eatMod);
/*     */   }
/*     */   public final Setting<Float> scaleOff; public final Setting<SettingGroup> rotationOff; public final Setting<Float> rotationOffX; public final Setting<Float> rotationOffY; public final Setting<Float> rotationOffZ; public final Setting<SettingGroup> animateOff; public final Setting<Boolean> animateOffX; public final Setting<Boolean> animateOffY; public final Setting<Boolean> animateOffZ; public final Setting<Float> speedAnimateOff; public final Setting<SettingGroup> eatMod; public final Setting<Float> eatX; public final Setting<Float> eatY; private float prevMainX; private float prevMainY; private float prevMainZ; private float prevOffX; private float prevOffY; private float prevOffZ;
/*     */   
/*     */   private float rotate(float value, float speed) {
/*  59 */     return (value - speed <= 180.0F && value - speed > -180.0F) ? (value - speed) : 180.0F;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSettingChange(EventSetting e) {
/*  64 */     if (!((Boolean)this.syncHands.getValue()).booleanValue()) {
/*     */       return;
/*     */     }
/*  67 */     if (e.getSetting() == this.positionMainX) {
/*  68 */       this.positionOffX.setValueSilent(this.positionMainX.getValue());
/*     */     }
/*  70 */     if (e.getSetting() == this.positionMainY) {
/*  71 */       this.positionOffY.setValueSilent(this.positionMainY.getValue());
/*     */     }
/*  73 */     if (e.getSetting() == this.positionMainZ) {
/*  74 */       this.positionOffZ.setValueSilent(this.positionMainZ.getValue());
/*     */     }
/*  76 */     if (e.getSetting() == this.positionOffX) {
/*  77 */       this.positionMainX.setValueSilent(this.positionOffX.getValue());
/*     */     }
/*  79 */     if (e.getSetting() == this.positionOffY) {
/*  80 */       this.positionMainY.setValueSilent(this.positionOffY.getValue());
/*     */     }
/*  82 */     if (e.getSetting() == this.positionOffZ) {
/*  83 */       this.positionMainZ.setValueSilent(this.positionOffZ.getValue());
/*     */     }
/*  85 */     if (e.getSetting() == this.scaleMain) {
/*  86 */       this.scaleOff.setValueSilent(this.scaleMain.getValue());
/*     */     }
/*  88 */     if (e.getSetting() == this.scaleOff) {
/*  89 */       this.scaleMain.setValueSilent(this.scaleOff.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  94 */     this.prevMainX = ((Float)this.rotationMainX.getValue()).floatValue();
/*  95 */     this.prevMainY = ((Float)this.rotationMainY.getValue()).floatValue();
/*  96 */     this.prevMainZ = ((Float)this.rotationMainZ.getValue()).floatValue();
/*  97 */     this.prevOffX = ((Float)this.rotationOffX.getValue()).floatValue();
/*  98 */     this.prevOffY = ((Float)this.rotationOffY.getValue()).floatValue();
/*  99 */     this.prevOffZ = ((Float)this.rotationOffZ.getValue()).floatValue();
/*     */     
/* 101 */     if (((Boolean)this.animateMainX.getValue()).booleanValue()) {
/* 102 */       this.rotationMainX.setValue(Float.valueOf(rotate(((Float)this.rotationMainX.getValue()).floatValue(), ((Float)this.speedAnimateMain.getValue()).floatValue())));
/*     */     }
/* 104 */     if (((Boolean)this.animateMainY.getValue()).booleanValue()) {
/* 105 */       this.rotationMainY.setValue(Float.valueOf(rotate(((Float)this.rotationMainY.getValue()).floatValue(), ((Float)this.speedAnimateMain.getValue()).floatValue())));
/*     */     }
/* 107 */     if (((Boolean)this.animateMainZ.getValue()).booleanValue()) {
/* 108 */       this.rotationMainZ.setValue(Float.valueOf(rotate(((Float)this.rotationMainZ.getValue()).floatValue(), ((Float)this.speedAnimateMain.getValue()).floatValue())));
/*     */     }
/* 110 */     if (((Boolean)this.animateOffX.getValue()).booleanValue()) {
/* 111 */       this.rotationOffX.setValue(Float.valueOf(rotate(((Float)this.rotationOffX.getValue()).floatValue(), ((Float)this.speedAnimateOff.getValue()).floatValue())));
/*     */     }
/* 113 */     if (((Boolean)this.animateOffY.getValue()).booleanValue()) {
/* 114 */       this.rotationOffY.setValue(Float.valueOf(rotate(((Float)this.rotationOffY.getValue()).floatValue(), ((Float)this.speedAnimateOff.getValue()).floatValue())));
/*     */     }
/* 116 */     if (((Boolean)this.animateOffZ.getValue()).booleanValue())
/* 117 */       this.rotationOffZ.setValue(Float.valueOf(rotate(((Float)this.rotationOffZ.getValue()).floatValue(), ((Float)this.speedAnimateOff.getValue()).floatValue()))); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onHeldItemRender(EventHeldItemRenderer event) {
/* 122 */     if (event.getHand() == class_1268.field_5808) {
/* 123 */       event.getStack().method_46416(((Float)this.positionMainX.getValue()).floatValue(), ((Float)this.positionMainY.getValue()).floatValue(), ((Float)this.positionMainZ.getValue()).floatValue());
/* 124 */       event.getStack().method_22905(((Float)this.scaleMain.getValue()).floatValue(), ((Float)this.scaleMain.getValue()).floatValue(), ((Float)this.scaleMain.getValue()).floatValue());
/* 125 */       event.getStack().method_22907(class_7833.field_40714.rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainX, ((Float)this.rotationMainX.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/* 126 */       event.getStack().method_22907(class_7833.field_40716.rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainY, ((Float)this.rotationMainY.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/* 127 */       event.getStack().method_22907(class_7833.field_40718.rotationDegrees(Render2DEngine.interpolateFloat(this.prevMainZ, ((Float)this.rotationMainZ.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/*     */     } else {
/* 129 */       event.getStack().method_46416(-((Float)this.positionOffX.getValue()).floatValue(), ((Float)this.positionOffY.getValue()).floatValue(), ((Float)this.positionOffZ.getValue()).floatValue());
/* 130 */       event.getStack().method_22905(((Float)this.scaleOff.getValue()).floatValue(), ((Float)this.scaleOff.getValue()).floatValue(), ((Float)this.scaleOff.getValue()).floatValue());
/* 131 */       event.getStack().method_22907(class_7833.field_40714.rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffX, ((Float)this.rotationOffX.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/* 132 */       event.getStack().method_22907(class_7833.field_40716.rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffY, ((Float)this.rotationOffY.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/* 133 */       event.getStack().method_22907(class_7833.field_40718.rotationDegrees(Render2DEngine.interpolateFloat(this.prevOffZ, ((Float)this.rotationOffZ.getValue()).floatValue(), Render3DEngine.getTickDelta())));
/*     */     } 
/*     */   } }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\ViewModel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */