/*    */ package thunder.hack.core.manager.client;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ import java.util.ArrayDeque;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_640;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ 
/*    */ 
/*    */ public class ServerManager
/*    */   implements IManager
/*    */ {
/* 16 */   private final ArrayDeque<Float> tpsResult = new ArrayDeque<>(20);
/*    */   private long time;
/*    */   private long tickTime;
/*    */   private float tps;
/*    */   
/*    */   public float getTPS() {
/* 22 */     return round2(this.tps);
/*    */   }
/*    */   
/*    */   public float getTPS2() {
/* 26 */     return round2((20.0F * (float)this.tickTime / 1000.0F));
/*    */   }
/*    */   
/*    */   public float getTPSFactor() {
/* 30 */     return (float)this.tickTime / 1000.0F;
/*    */   }
/*    */   
/*    */   public static float round2(double value) {
/* 34 */     BigDecimal bd = new BigDecimal(value);
/* 35 */     bd = bd.setScale(2, RoundingMode.HALF_UP);
/* 36 */     return bd.floatValue();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive event) {
/* 41 */     if (event.getPacket() instanceof net.minecraft.class_2761) {
/* 42 */       if (this.time != 0L) {
/* 43 */         this.tickTime = System.currentTimeMillis() - this.time;
/*    */         
/* 45 */         if (this.tpsResult.size() > 20) {
/* 46 */           this.tpsResult.poll();
/*    */         }
/* 48 */         this.tpsResult.add(Float.valueOf(20.0F * 1000.0F / (float)this.tickTime));
/*    */         
/* 50 */         float average = 0.0F;
/*    */         
/* 52 */         for (Float value : this.tpsResult) average += MathUtility.clamp(value.floatValue(), 0.0F, 20.0F);
/*    */         
/* 54 */         this.tps = average / this.tpsResult.size();
/*    */       } 
/* 56 */       this.time = System.currentTimeMillis();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getPing() {
/* 61 */     if (mc.method_1562() == null || mc.field_1724 == null) return 0;
/*    */     
/* 63 */     if (ModuleManager.fastLatency.isEnabled()) {
/* 64 */       return ModuleManager.fastLatency.resolvedPing;
/*    */     }
/* 66 */     class_640 playerListEntry = mc.method_1562().method_2871(mc.field_1724.method_5667());
/* 67 */     if (playerListEntry == null) return 0; 
/* 68 */     return playerListEntry.method_2959();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\ServerManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */