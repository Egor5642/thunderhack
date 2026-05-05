/*    */ package thunder.hack.features.modules.misc;
/*    */ import net.minecraft.class_7439;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.player.MovementUtility;
/*    */ 
/*    */ public class AutoFlyme extends Module {
/*    */   public final Setting<Boolean> instantSpeed;
/*    */   public final Setting<Boolean> hover;
/*    */   public final Setting<Boolean> useTimer;
/*    */   
/*    */   public AutoFlyme() {
/* 15 */     super("AutoFlyme", Module.Category.MISC);
/*    */ 
/*    */     
/* 18 */     this.instantSpeed = new Setting("InstantSpeed", Boolean.valueOf(true));
/* 19 */     this.hover = new Setting("hover", Boolean.valueOf(false));
/* 20 */     this.useTimer = new Setting("UseTimer", Boolean.valueOf(false));
/*    */     
/* 22 */     this.hoverY = new Setting("hoverY", Float.valueOf(0.228F), Float.valueOf(0.0F), Float.valueOf(1.0F), v -> ((Boolean)this.hover.getValue()).booleanValue());
/* 23 */     this.speed = new Setting("speed", Float.valueOf(1.05F), Float.valueOf(0.0F), Float.valueOf(8.0F), v -> ((Boolean)this.hover.getValue()).booleanValue());
/*    */ 
/*    */     
/* 26 */     this.timer = new Timer();
/*    */   }
/*    */   public Setting<Float> hoverY; public Setting<Float> speed; private final Timer timer;
/*    */   public void onEnable() {
/* 30 */     if (!(mc.field_1724.method_31549()).field_7479) {
/* 31 */       mc.field_1724.field_3944.method_45730("flyme");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 37 */     ThunderHack.TICK_TIMER = 1.0F;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 42 */     if (e.getPacket() instanceof class_7439) {
/* 43 */       class_7439 packet = (class_7439)e.getPacket();
/* 44 */       if ((packet.comp_763().getString().contains("Вы атаковали игрока") || packet.comp_763().getString().contains("Возможность летать была удалена")) && this.timer.passedMs(1000L)) {
/* 45 */         mc.field_1724.field_3944.method_45730("flyme");
/* 46 */         mc.field_1724.field_3944.method_45730("flyme");
/* 47 */         this.timer.reset();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 54 */     if (((Boolean)this.useTimer.getValue()).booleanValue()) ThunderHack.TICK_TIMER = 1.088F; 
/* 55 */     if (!(mc.field_1724.method_31549()).field_7479 && this.timer.passedMs(1000L) && !mc.field_1724.method_24828() && mc.field_1724.field_3913.field_3904) {
/* 56 */       mc.field_1724.field_3944.method_45730("flyme");
/* 57 */       this.timer.reset();
/*    */     } 
/* 59 */     if (!mc.field_1690.field_1903.method_1434() && ((Boolean)this.hover.getValue()).booleanValue() && (mc.field_1724.method_31549()).field_7479 && (mc.field_1724.method_31549()).field_7479 && !mc.field_1724.method_24828() && !mc.field_1687.method_20812((class_1297)mc.field_1724, mc.field_1724.method_5829().method_989(0.0D, -((Float)this.hoverY.getValue()).floatValue(), 0.0D)).iterator().hasNext()) {
/* 60 */       mc.field_1724.method_18800((mc.field_1724.method_18798()).field_1352, -0.05D, (mc.field_1724.method_18798()).field_1350);
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onUpdateWalkingPlayer(EventSync event) {
/* 66 */     if (!((Boolean)this.instantSpeed.getValue()).booleanValue() || !(mc.field_1724.method_31549()).field_7479)
/* 67 */       return;  (new double[2])[0] = 0.0D; (new double[2])[1] = 0.0D; double[] dir = MovementUtility.isMoving() ? MovementUtility.forward(((Float)this.speed.getValue()).floatValue()) : new double[2];
/* 68 */     mc.field_1724.method_18800(dir[0], (mc.field_1724.method_18798()).field_1351, dir[1]);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoFlyme.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */