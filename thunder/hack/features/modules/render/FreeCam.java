/*     */ package thunder.hack.features.modules.render;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1309;
/*     */ import thunder.hack.events.impl.EventMouse;
/*     */ import thunder.hack.events.impl.EventMove;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class FreeCam extends Module {
/*     */   private final Setting<Float> speed;
/*     */   private final Setting<Float> hspeed;
/*     */   private final Setting<Boolean> freeze;
/*     */   public final Setting<Boolean> track;
/*     */   
/*     */   public FreeCam() {
/*  17 */     super("FreeCam", Module.Category.RENDER);
/*     */ 
/*     */     
/*  20 */     this.speed = new Setting("HSpeed", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(3.0F));
/*  21 */     this.hspeed = new Setting("VSpeed", Float.valueOf(0.42F), Float.valueOf(0.1F), Float.valueOf(3.0F));
/*  22 */     this.freeze = new Setting("Freeze", Boolean.valueOf(false));
/*  23 */     this.track = new Setting("Track", Boolean.valueOf(false));
/*     */   }
/*     */   private float fakeYaw; private float fakePitch; private float prevFakeYaw; private float prevFakePitch; private float prevScroll; private double fakeX; private double fakeY; private double fakeZ; private double prevFakeX;
/*     */   private double prevFakeY;
/*     */   private double prevFakeZ;
/*     */   public class_1309 trackEntity;
/*     */   
/*     */   public void onEnable() {
/*  31 */     mc.field_1730 = false;
/*  32 */     this.trackEntity = null;
/*     */     
/*  34 */     this.fakePitch = mc.field_1724.method_36455();
/*  35 */     this.fakeYaw = mc.field_1724.method_36454();
/*     */     
/*  37 */     this.prevFakePitch = this.fakePitch;
/*  38 */     this.prevFakeYaw = this.fakeYaw;
/*     */     
/*  40 */     this.fakeX = mc.field_1724.method_23317();
/*  41 */     this.fakeY = mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376());
/*  42 */     this.fakeZ = mc.field_1724.method_23321();
/*     */     
/*  44 */     this.prevFakeX = mc.field_1724.method_23317();
/*  45 */     this.prevFakeY = mc.field_1724.method_23318();
/*  46 */     this.prevFakeZ = mc.field_1724.method_23321();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAttack(EventAttack e) {
/*  51 */     if (!e.isPre()) { class_1297 class_1297 = e.getEntity(); if (class_1297 instanceof class_1309) { class_1309 entity = (class_1309)class_1297; if (((Boolean)this.track.getValue()).booleanValue())
/*  52 */           this.trackEntity = entity;  }
/*     */        }
/*     */   
/*     */   }
/*     */   public void onDisable() {
/*  57 */     if (fullNullCheck())
/*  58 */       return;  mc.field_1730 = true;
/*     */   }
/*     */   
/*     */   @EventHandler(priority = 100)
/*     */   public void onSync(EventSync e) {
/*  63 */     this.prevFakeYaw = this.fakeYaw;
/*  64 */     this.prevFakePitch = this.fakePitch;
/*     */     
/*  66 */     if (isKeyPressed(256) || isKeyPressed(340) || isKeyPressed(344)) {
/*  67 */       this.trackEntity = null;
/*     */     }
/*  69 */     if (this.trackEntity != null) {
/*  70 */       this.fakeYaw = this.trackEntity.method_36454();
/*  71 */       this.fakePitch = this.trackEntity.method_36455();
/*     */       
/*  73 */       this.prevFakeX = this.fakeX;
/*  74 */       this.prevFakeY = this.fakeY;
/*  75 */       this.prevFakeZ = this.fakeZ;
/*     */       
/*  77 */       this.fakeX = this.trackEntity.method_23317();
/*  78 */       this.fakeY = this.trackEntity.method_23318() + this.trackEntity.method_18381(this.trackEntity.method_18376());
/*  79 */       this.fakeZ = this.trackEntity.method_23321();
/*     */     } else {
/*  81 */       this.fakeYaw = mc.field_1724.method_36454();
/*  82 */       this.fakePitch = mc.field_1724.method_36455();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onKeyboardInput(EventKeyboardInput e) {
/*  89 */     if (mc.field_1724 == null)
/*     */       return; 
/*  91 */     if (this.trackEntity == null) {
/*  92 */       double[] motion = MovementUtility.forward(((Float)this.speed.getValue()).floatValue());
/*     */       
/*  94 */       this.prevFakeX = this.fakeX;
/*  95 */       this.prevFakeY = this.fakeY;
/*  96 */       this.prevFakeZ = this.fakeZ;
/*     */       
/*  98 */       this.fakeX += motion[0];
/*  99 */       this.fakeZ += motion[1];
/*     */       
/* 101 */       if (mc.field_1690.field_1903.method_1434()) {
/* 102 */         this.fakeY += ((Float)this.hspeed.getValue()).floatValue();
/*     */       }
/* 104 */       if (mc.field_1690.field_1832.method_1434()) {
/* 105 */         this.fakeY -= ((Float)this.hspeed.getValue()).floatValue();
/*     */       }
/*     */     } 
/* 108 */     mc.field_1724.field_3913.field_3905 = 0.0F;
/* 109 */     mc.field_1724.field_3913.field_3907 = 0.0F;
/* 110 */     mc.field_1724.field_3913.field_3904 = false;
/* 111 */     mc.field_1724.field_3913.field_3903 = false;
/*     */   }
/*     */   
/*     */   @EventHandler(priority = -100)
/*     */   public void onMove(EventMove e) {
/* 116 */     if (((Boolean)this.freeze.getValue()).booleanValue()) {
/* 117 */       e.setX(0.0D);
/* 118 */       e.setY(0.0D);
/* 119 */       e.setZ(0.0D);
/* 120 */       e.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketSend(PacketEvent.Send e) {
/* 126 */     if (((Boolean)this.freeze.getValue()).booleanValue() && e.getPacket() instanceof net.minecraft.class_2828)
/* 127 */       e.cancel(); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onScroll(EventMouse e) {
/* 132 */     if (e.getAction() == 2) {
/* 133 */       if (e.getButton() > 0) { this.speed.setValue(Float.valueOf(((Float)this.speed.getValue()).floatValue() + 0.05F)); }
/* 134 */       else { this.speed.setValue(Float.valueOf(((Float)this.speed.getValue()).floatValue() - 0.05F)); }
/* 135 */        this.prevScroll = e.getButton();
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getFakeYaw() {
/* 140 */     return (float)Render2DEngine.interpolate(this.prevFakeYaw, this.fakeYaw, Render3DEngine.getTickDelta());
/*     */   }
/*     */   
/*     */   public float getFakePitch() {
/* 144 */     return (float)Render2DEngine.interpolate(this.prevFakePitch, this.fakePitch, Render3DEngine.getTickDelta());
/*     */   }
/*     */   
/*     */   public double getFakeX() {
/* 148 */     return Render2DEngine.interpolate(this.prevFakeX, this.fakeX, Render3DEngine.getTickDelta());
/*     */   }
/*     */   
/*     */   public double getFakeY() {
/* 152 */     return Render2DEngine.interpolate(this.prevFakeY, this.fakeY, Render3DEngine.getTickDelta());
/*     */   }
/*     */   
/*     */   public double getFakeZ() {
/* 156 */     return Render2DEngine.interpolate(this.prevFakeZ, this.fakeZ, Render3DEngine.getTickDelta());
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\FreeCam.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */