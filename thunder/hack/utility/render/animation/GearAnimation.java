/*    */ package thunder.hack.utility.render.animation;
/*    */ 
/*    */ import thunder.hack.features.modules.client.ClickGui;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class GearAnimation {
/*  8 */   private float espValue = 1.0F;
/*  9 */   private float espSpeed = 1.0F; private float prevEspValue;
/*    */   private boolean flipSpeed;
/*    */   
/*    */   public float getValue() {
/* 13 */     return Render2DEngine.interpolateFloat(this.prevEspValue, this.espValue, Render3DEngine.getTickDelta());
/*    */   }
/*    */   
/*    */   public void tick() {
/* 17 */     this.prevEspValue = this.espValue;
/* 18 */     this.espValue += this.espSpeed;
/* 19 */     if (this.espSpeed > ((Integer)ClickGui.gearStop.getValue()).intValue()) this.flipSpeed = true; 
/* 20 */     if (this.espSpeed < -((Integer)ClickGui.gearStop.getValue()).intValue()) this.flipSpeed = false; 
/* 21 */     this.espSpeed = this.flipSpeed ? (this.espSpeed - ((Float)ClickGui.gearDuration.getValue()).floatValue()) : (this.espSpeed + ((Float)ClickGui.gearDuration.getValue()).floatValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\animation\GearAnimation.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */