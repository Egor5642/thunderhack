/*    */ package thunder.hack.utility.render.animation;
/*    */ 
/*    */ import net.minecraft.class_3532;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class EaseOutCirc
/*    */ {
/*    */   private final int maxTicks;
/*    */   private double value;
/*    */   
/*    */   public EaseOutCirc(int maxTicks) {
/* 12 */     this.maxTicks = maxTicks;
/*    */   }
/*    */   private double dstValue; private int prevStep; private int step;
/*    */   public EaseOutCirc() {
/* 16 */     this(5);
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     this.prevStep = this.step;
/* 21 */     this.step = class_3532.method_15340(this.step + 1, 0, this.maxTicks);
/*    */   }
/*    */   
/*    */   public static double createAnimation(double value) {
/* 25 */     return Math.sqrt(1.0D - Math.pow(value - 1.0D, 2.0D));
/*    */   }
/*    */   
/*    */   public void setValue(double value) {
/* 29 */     if (value != this.dstValue) {
/* 30 */       this.prevStep = 0;
/* 31 */       this.step = 0;
/* 32 */       this.value = this.dstValue;
/* 33 */       this.dstValue = value;
/*    */     } 
/*    */   }
/*    */   
/*    */   public double getAnimationD() {
/* 38 */     double delta = this.dstValue - this.value;
/* 39 */     double animation = createAnimation((this.prevStep + (this.step - this.prevStep) * Render3DEngine.getTickDelta()) / this.maxTicks);
/* 40 */     return this.value + delta * animation;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\animation\EaseOutCirc.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */