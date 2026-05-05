/*    */ package thunder.hack.utility.render.animation;
/*    */ 
/*    */ import net.minecraft.class_3532;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class EaseOutBack {
/*    */   private int prevTick;
/*    */   private int tick;
/*    */   private final int maxTick;
/*    */   
/*    */   public EaseOutBack(int maxTick) {
/* 12 */     this.maxTick = maxTick;
/*    */   }
/*    */   
/*    */   public EaseOutBack() {
/* 16 */     this(10);
/*    */   }
/*    */   
/*    */   public static double dropAnimation(double value) {
/* 20 */     return 1.0D + 2.70158D * Math.pow(value - 1.0D, 3.0D) + 1.70158D * Math.pow(value - 1.0D, 2.0D);
/*    */   }
/*    */   
/*    */   public void update(boolean update) {
/* 24 */     this.prevTick = this.tick;
/* 25 */     this.tick = class_3532.method_15340(this.tick + (update ? 1 : -1), 0, this.maxTick);
/*    */   }
/*    */   
/*    */   public double getAnimationd() {
/* 29 */     return dropAnimation(((this.prevTick + (this.tick - this.prevTick) * Render3DEngine.getTickDelta()) / this.maxTick));
/*    */   }
/*    */   
/*    */   public void reset() {
/* 33 */     this.prevTick = 0;
/* 34 */     this.tick = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\animation\EaseOutBack.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */