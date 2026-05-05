/*     */ package thunder.hack.features.modules.movement;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.events.impl.EventMove;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.player.MovementUtility;
/*     */ 
/*     */ public class LongJump extends Module {
/*     */   private final Setting<Boolean> useTimer;
/*     */   private final Setting<Boolean> jumpDisable;
/*     */   private final Setting<Float> timerValue;
/*     */   private final Setting<Float> speed;
/*     */   
/*     */   public LongJump() {
/*  19 */     super("LongJump", Module.Category.MOVEMENT);
/*     */ 
/*     */     
/*  22 */     this.useTimer = new Setting("Timer", Boolean.valueOf(false));
/*  23 */     this.jumpDisable = new Setting("JumpDisable", Boolean.valueOf(true));
/*  24 */     this.timerValue = new Setting("TimerSpeed", Float.valueOf(1.0F), Float.valueOf(0.5F), Float.valueOf(3.0F), v -> ((Boolean)this.useTimer.getValue()).booleanValue());
/*  25 */     this.speed = new Setting("Speed", Float.valueOf(1.35F), Float.valueOf(0.1F), Float.valueOf(10.0F));
/*  26 */     this.maxDistance = new Setting("MaxDistance", Float.valueOf(10.0F), Float.valueOf(5.0F), Float.valueOf(40.0F));
/*     */ 
/*     */     
/*  29 */     this.stage = 0;
/*     */   }
/*     */   private final Setting<Float> maxDistance; private float plannedSpeed; private float realSpeed; private int stage; private class_243 prevPosition;
/*     */   @EventHandler
/*     */   public void onMove(EventMove e) {
/*  34 */     if (this.prevPosition != null && mc.field_1724.method_19538().method_1025(this.prevPosition) > this.maxDistance.getPow2Value()) {
/*  35 */       disable(ClientSettings.isRu() ? "Прыжок выполнен! Отключаю.." : "Jump complete! Disabling..");
/*     */     }
/*  37 */     if (MovementUtility.isMoving()) {
/*  38 */       double d; if (((Boolean)this.useTimer.getValue()).booleanValue()) {
/*  39 */         ThunderHack.TICK_TIMER = ((Float)this.timerValue.getValue()).floatValue();
/*     */       }
/*  41 */       switch (this.stage) {
/*     */         case 0:
/*  43 */           this.plannedSpeed = (float)(((Float)this.speed.getValue()).floatValue() * MovementUtility.getBaseMoveSpeed());
/*  44 */           this.realSpeed = 0.0F;
/*  45 */           this.stage++;
/*     */           break;
/*     */         case 1:
/*  48 */           mc.field_1724.method_18800(mc.field_1724.method_18798().method_10216(), 0.42D + isJumpBoost(), mc.field_1724.method_18798().method_10215());
/*  49 */           e.setY(0.42D + isJumpBoost());
/*  50 */           this.plannedSpeed *= 2.149F;
/*  51 */           this.stage++;
/*     */           break;
/*     */         case 2:
/*  54 */           d = 0.6600000262260437D * (this.realSpeed - MovementUtility.getBaseMoveSpeed());
/*  55 */           this.plannedSpeed = (float)(this.realSpeed - d);
/*  56 */           this.stage++;
/*     */           break;
/*     */         case 3:
/*  59 */           if (mc.field_1724.field_5992 || mc.field_1687.method_20812((class_1297)mc.field_1724, mc.field_1724.method_5829().method_1009(-0.2D, 0.0D, -0.2D).method_989(0.0D, mc.field_1724.method_18798().method_10214(), 0.0D)).iterator().hasNext()) {
/*  60 */             if (((Boolean)this.jumpDisable.getValue()).booleanValue())
/*  61 */               disable(ClientSettings.isRu() ? "Прыжок выполнен! Отключаю.." : "Jump complete! Disabling.."); 
/*  62 */             this.stage = 0;
/*  63 */             this.realSpeed = 0.0F;
/*     */           } 
/*  65 */           this.plannedSpeed = this.realSpeed - this.realSpeed / 159.0F;
/*     */           break;
/*     */       } 
/*     */     } 
/*  69 */     this.plannedSpeed = (float)Math.max(MovementUtility.getBaseMoveSpeed(), this.plannedSpeed);
/*     */     
/*  71 */     MovementUtility.modifyEventSpeed(e, this.plannedSpeed);
/*  72 */     e.cancel();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive e) {
/*  77 */     if (e.getPacket() instanceof net.minecraft.class_2708) {
/*  78 */       disable(ClientSettings.isRu() ? "Тебя флагнуло! Отключаю.." : "You've been flagged! Disabling..");
/*     */     }
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  83 */     resetValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  88 */     resetValues();
/*     */   }
/*     */   
/*     */   public void resetValues() {
/*  92 */     this.prevPosition = mc.field_1724.method_19538();
/*  93 */     ThunderHack.TICK_TIMER = 1.0F;
/*  94 */     this.plannedSpeed = 0.0F;
/*  95 */     this.realSpeed = 0.0F;
/*  96 */     this.stage = 0;
/*     */   }
/*     */   
/*     */   public float isJumpBoost() {
/* 100 */     if (mc.field_1724.method_6059(class_1294.field_5913)) return 0.2F; 
/* 101 */     return 0.0F;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntitySync(EventSync eventSync) {
/* 106 */     if (MovementUtility.isMoving())
/* 107 */     { this.realSpeed = (float)Math.hypot(mc.field_1724.method_23317() - mc.field_1724.field_6014, mc.field_1724.method_23321() - mc.field_1724.field_5969); }
/* 108 */     else { resetValues(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\LongJump.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */