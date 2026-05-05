/*    */ package thunder.hack.features.modules.movement;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1297;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.EventPlayerTravel;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.player.MovementUtility;
/*    */ 
/*    */ public class EntitySpeed extends Module {
/*    */   private final Setting<Boolean> accelerate;
/*    */   private final Setting<Float> accelerateFactor;
/*    */   
/*    */   public EntitySpeed() {
/* 16 */     super("EntitySpeed", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 19 */     this.accelerate = new Setting("Accelerate", Boolean.valueOf(true));
/* 20 */     this.accelerateFactor = new Setting("AccelerateFactor", Float.valueOf(9.0F), Float.valueOf(0.0F), Float.valueOf(20.0F), v -> ((Boolean)this.accelerate.getValue()).booleanValue());
/* 21 */     this.stopunloaded = new Setting("StopUnloaded", Boolean.valueOf(true));
/* 22 */     this.speed = new Setting("Speed", Float.valueOf(0.77F), Float.valueOf(0.1F), Float.valueOf(5.0F));
/* 23 */     this.timer = new Setting("Timer", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(5.0F));
/* 24 */     this.jitter = new Setting("Jitter", Float.valueOf(0.05F), Float.valueOf(0.0F), Float.valueOf(0.5F));
/*    */   }
/*    */   private final Setting<Boolean> stopunloaded; private final Setting<Float> speed; private final Setting<Float> timer; private final Setting<Float> jitter;
/*    */   private int ticks;
/*    */   private float acceleration;
/*    */   
/*    */   public void onEnable() {
/* 31 */     this.ticks = 0;
/* 32 */     this.acceleration = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 37 */     ThunderHack.TICK_TIMER = 1.0F;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerTravel(@NotNull EventPlayerTravel ev) {
/* 42 */     if (!ev.isPre())
/* 43 */       return;  if (fullNullCheck())
/*    */       return; 
/* 45 */     class_1297 entity = mc.field_1724.method_49694();
/*    */     
/* 47 */     if (entity == null)
/* 48 */       return;  if ((!mc.field_1687.method_8393((int)entity.method_19538().method_10216() >> 4, (int)entity.method_19538().method_10215() >> 4) || entity.method_19538().method_10214() < -60.0D) && ((Boolean)this.stopunloaded.getValue()).booleanValue()) {
/*    */       return;
/*    */     }
/* 51 */     if (entity.field_5976 || mc.field_1724.field_5976) {
/* 52 */       this.acceleration = 0.0F;
/*    */     }
/* 54 */     if (((Float)this.timer.getValue()).floatValue() != 1.0F) {
/* 55 */       ThunderHack.TICK_TIMER = ((Float)this.timer.getValue()).floatValue();
/*    */     }
/* 57 */     double[] motion = MovementUtility.forward(getSpeed());
/* 58 */     double predictedX = entity.method_23317() + motion[0];
/* 59 */     double predictedZ = entity.method_23321() + motion[1];
/*    */     
/* 61 */     if ((!mc.field_1687.method_8393((int)predictedX >> 4, (int)predictedZ >> 4) || entity.method_19538().method_10214() < -60.0D) && ((Boolean)this.stopunloaded.getValue()).booleanValue()) {
/*    */       return;
/*    */     }
/* 64 */     if (MovementUtility.isMoving()) { entity.method_18800(motion[0], entity.method_18798().method_10214(), motion[1]); }
/* 65 */     else { entity.method_18800(0.0D, entity.method_18798().method_10214(), 0.0D); }
/*    */     
/* 67 */     if (this.ticks++ > 50) {
/* 68 */       this.ticks = 0;
/*    */     }
/* 70 */     ev.cancel();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 75 */     if (e.getPacket() instanceof net.minecraft.class_2708)
/* 76 */       this.acceleration = 0.0F; 
/*    */   }
/*    */   
/*    */   private float getSpeed() {
/* 80 */     float baseSpeed = Math.min((this.acceleration += (20.0F - ((Float)this.accelerateFactor.getValue()).floatValue()) / ((Float)this.speed.getValue()).floatValue()) / 100.0F, ((Float)this.speed.getValue()).floatValue());
/* 81 */     if (!((Boolean)this.accelerate.getValue()).booleanValue())
/* 82 */       baseSpeed = ((Float)this.speed.getValue()).floatValue(); 
/* 83 */     baseSpeed += (this.ticks > 25) ? ((Float)this.jitter.getValue()).floatValue() : 0.0F;
/* 84 */     return baseSpeed;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\EntitySpeed.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */