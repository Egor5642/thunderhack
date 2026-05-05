/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2639;
/*    */ import net.minecraft.class_2805;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ 
/*    */ public final class FastLatency extends Module {
/* 15 */   private final Setting<Integer> delay = new Setting("Delay", Integer.valueOf(80), Integer.valueOf(0), Integer.valueOf(1000));
/*    */   
/* 17 */   private final Timer timer = new Timer();
/* 18 */   private final Timer limitTimer = new Timer();
/*    */   private long ping;
/*    */   public int resolvedPing;
/*    */   
/*    */   public FastLatency() {
/* 23 */     super("FastLatency", Module.Category.CLIENT);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRender3D(class_4587 stack) {
/* 28 */     if (this.timer.passedMs(5000L) && this.limitTimer.every(((Integer)this.delay.getValue()).intValue())) {
/* 29 */       sendPacket((class_2596)new class_2805(1337, "w "));
/* 30 */       this.ping = System.currentTimeMillis();
/* 31 */       this.timer.reset();
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 37 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2639) { class_2639 c = (class_2639)class_2596; if (c.comp_2262() == 1337) {
/* 38 */         this.resolvedPing = (int)MathUtility.clamp((float)(System.currentTimeMillis() - this.ping), 0.0F, 1000.0F);
/* 39 */         this.timer.setMs(5000L);
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\FastLatency.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */