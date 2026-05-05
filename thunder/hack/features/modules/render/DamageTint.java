/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class DamageTint
/*    */   extends Module {
/*    */   public DamageTint() {
/* 12 */     super("DamageTint", Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 16 */     float factor = 1.0F - MathUtility.clamp(mc.field_1724.method_6032(), 0.0F, 12.0F) / 12.0F;
/* 17 */     Color red = new Color(16711680, true);
/*    */     
/* 19 */     if (factor < 1.0F)
/* 20 */       Render2DEngine.draw2DGradientRect(context.method_51448(), 0.0F, 0.0F, mc.method_22683().method_4486(), mc.method_22683().method_4502(), 
/* 21 */           Render2DEngine.injectAlpha(red, (int)(factor * 170.0F)), red, 
/* 22 */           Render2DEngine.injectAlpha(red, (int)(factor * 170.0F)), red); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\DamageTint.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */