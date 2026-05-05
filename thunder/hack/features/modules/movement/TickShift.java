/*     */ package thunder.hack.features.modules.movement;
/*     */ import net.minecraft.class_2828;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.setting.Setting;
/*     */ 
/*     */ public class TickShift extends Module {
/*     */   private final Setting<Float> timer;
/*     */   private final Setting<Integer> packets;
/*     */   private final Setting<Integer> lagTime;
/*     */   private final Setting<Boolean> sneaking;
/*     */   private final Setting<Boolean> cancelGround;
/*     */   private final Setting<Boolean> cancelRotations;
/*     */   
/*     */   public TickShift() {
/*  16 */     super("TickShift", Module.Category.MOVEMENT);
/*     */ 
/*     */     
/*  19 */     this.timer = new Setting("Timer", Float.valueOf(2.0F), Float.valueOf(0.1F), Float.valueOf(100.0F));
/*  20 */     this.packets = new Setting("Packets", Integer.valueOf(20), Integer.valueOf(0), Integer.valueOf(1000));
/*  21 */     this.lagTime = new Setting("LagTime", Integer.valueOf(1000), Integer.valueOf(0), Integer.valueOf(10000));
/*  22 */     this.sneaking = new Setting("Sneaking", Boolean.valueOf(false));
/*  23 */     this.cancelGround = new Setting("CancelGround", Boolean.valueOf(false));
/*  24 */     this.cancelRotations = new Setting("CancelRotation", Boolean.valueOf(false));
/*     */ 
/*     */     
/*  27 */     this.lagTimer = new Timer();
/*     */   }
/*     */   private static double prevPosX; private static double prevPosY; private static double prevPosZ; private Timer lagTimer; private static float yaw; private static float pitch; private int ticks;
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/*  33 */     if (notMoving()) {
/*  34 */       ThunderHack.TICK_TIMER = 1.0F;
/*  35 */       this.ticks = (this.ticks >= ((Integer)this.packets.getValue()).intValue()) ? ((Integer)this.packets.getValue()).intValue() : (this.ticks + 1);
/*     */     } 
/*     */     
/*  38 */     prevPosX = mc.field_1724.method_23317();
/*  39 */     prevPosY = mc.field_1724.method_23318();
/*  40 */     prevPosZ = mc.field_1724.method_23321();
/*  41 */     yaw = mc.field_1724.method_36454();
/*  42 */     pitch = mc.field_1724.method_36455();
/*     */     
/*  44 */     if (mc.field_1724 == null || mc.field_1687 == null || !this.lagTimer.passedMs(((Integer)this.lagTime.getValue()).intValue())) {
/*  45 */       reset();
/*  46 */     } else if (this.ticks <= 0 || !MovementUtility.isMoving() || (!((Boolean)this.sneaking.getValue()).booleanValue() && mc.field_1724.method_5715())) {
/*  47 */       ThunderHack.TICK_TIMER = 1.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive e) {
/*  53 */     if (e.getPacket() instanceof net.minecraft.class_2708)
/*  54 */       this.lagTimer.reset(); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketSend(PacketEvent.Send e) {
/*  59 */     if (e.getPacket() instanceof class_2828.class_2830)
/*  60 */       shift(e, true); 
/*  61 */     if (e.getPacket() instanceof class_2828.class_2829) {
/*  62 */       shift(e, true);
/*     */     }
/*  64 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2828.class_2831) { class_2828.class_2831 pac = (class_2828.class_2831)class_2596;
/*  65 */       if (((Boolean)this.cancelRotations.getValue()).booleanValue() && (((Boolean)this.cancelGround.getValue()).booleanValue() || pac.method_12273() == mc.field_1724.method_24828()))
/*  66 */       { e.cancel(); }
/*  67 */       else { shift(e, false); }
/*     */        }
/*  69 */      if (e.getPacket() instanceof class_2828.class_5911)
/*  70 */       if (((Boolean)this.cancelGround.getValue()).booleanValue()) { e.cancel(); }
/*  71 */       else { shift(e, false); }
/*     */        
/*     */   }
/*     */   
/*     */   public String getDisplayInfo() {
/*  76 */     return "" + this.ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  81 */     reset();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  86 */     reset();
/*     */   }
/*     */   
/*     */   private static boolean notMoving() {
/*  90 */     return (prevPosX == mc.field_1724.method_23317() && prevPosY == mc.field_1724.method_23318() && prevPosZ == mc.field_1724.method_23321() && yaw == mc.field_1724.method_36454() && pitch == mc.field_1724.method_36455());
/*     */   }
/*     */   
/*     */   private void shift(PacketEvent.Send event, boolean moving) {
/*  94 */     if (event.isCancelled())
/*  95 */       return;  if (moving && MovementUtility.isMoving() && this.ticks > 0 && (((Boolean)this.sneaking.getValue()).booleanValue() || !mc.field_1724.method_5715()))
/*  96 */       ThunderHack.TICK_TIMER = ((Float)this.timer.getValue()).floatValue(); 
/*  97 */     this.ticks = (this.ticks <= 0) ? 0 : (this.ticks - 1);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 101 */     ThunderHack.TICK_TIMER = 1.0F;
/* 102 */     this.ticks = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\TickShift.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */