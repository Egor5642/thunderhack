/*    */ package thunder.hack.utility.render.animation;
/*    */ 
/*    */ import thunder.hack.utility.math.FrameRateCounter;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ 
/*    */ public class AnimationUtility {
/*    */   public static float deltaTime() {
/*  8 */     return (FrameRateCounter.INSTANCE.getFps() > 5) ? (1.0F / FrameRateCounter.INSTANCE.getFps()) : 0.016F;
/*    */   }
/*    */   
/*    */   public static float fast(float end, float start, float multiple) {
/* 12 */     float clampedDelta = MathUtility.clamp(deltaTime() * multiple, 0.0F, 1.0F);
/* 13 */     return (1.0F - clampedDelta) * end + clampedDelta * start;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\animation\AnimationUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */