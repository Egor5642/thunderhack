/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2664;
/*    */ import net.minecraft.class_2675;
/*    */ import net.minecraft.class_2708;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ 
/*    */ public class AntiCrash extends Module {
/* 14 */   public final Setting<Boolean> debug = new Setting("Debug", Boolean.valueOf(false));
/*    */   
/* 16 */   private Timer debugTimer = new Timer();
/*    */   
/*    */   public AntiCrash() {
/* 19 */     super("AntiCrash", Module.Category.CLIENT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive receive) {
/* 24 */     class_2596 class_2596 = receive.getPacket(); if (class_2596 instanceof class_2664) { class_2664 exp = (class_2664)class_2596; if (exp.method_11475() > 1.0E9D || exp.method_11477() > 1.0E9D || exp.method_11478() > 1.0E9D || exp.method_11476() > 1.0E9D)
/* 25 */       { if (((Boolean)this.debug.getValue()).booleanValue() && this.debugTimer.passedMs(1000L)) {
/* 26 */           sendMessage("ExplosionS2CPacket canceled");
/* 27 */           this.debugTimer.reset();
/*    */         } 
/* 29 */         receive.cancel(); return; }  }
/* 30 */      class_2596 = receive.getPacket(); if (class_2596 instanceof class_2675) { class_2675 p = (class_2675)class_2596; if (p.method_11544() > 1.0E9D || p.method_11547() > 1.0E9D || p.method_11546() > 1.0E9D || p.method_11543() > 1.0E9D || p.method_11548() > 1.0E9D || p.method_11549() > 1.0E9D || p.method_11550() > 1.0E9D)
/* 31 */       { if (((Boolean)this.debug.getValue()).booleanValue() && this.debugTimer.passedMs(1000L)) {
/* 32 */           sendMessage("ParticleS2CPacket canceled");
/* 33 */           this.debugTimer.reset();
/*    */         } 
/* 35 */         receive.cancel(); return; }  }
/* 36 */      class_2596 = receive.getPacket(); if (class_2596 instanceof class_2708) { class_2708 pos = (class_2708)class_2596; if (pos.method_11734() > 1.0E9D || pos.method_11735() > 1.0E9D || pos.method_11738() > 1.0E9D || pos.method_11736() > 1.0E9D || pos.method_11739() > 1.0E9D) {
/* 37 */         if (((Boolean)this.debug.getValue()).booleanValue() && this.debugTimer.passedMs(1000L)) {
/* 38 */           sendMessage("PlayerPositionLookS2CPacket canceled");
/* 39 */           this.debugTimer.reset();
/*    */         } 
/* 41 */         receive.cancel();
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\AntiCrash.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */