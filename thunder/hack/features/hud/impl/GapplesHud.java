/*    */ package thunder.hack.features.hud.impl;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_7833;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventEatFood;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class GapplesHud extends HudElement {
/*    */   private float angle;
/*    */   
/*    */   public GapplesHud() {
/* 23 */     super("GapplesHud", 0, 0);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     this.crapple = new Setting("Crapple", Boolean.valueOf(true));
/*    */   } private float prevAngle; private final Setting<Boolean> crapple;
/*    */   public void onRender2D(class_332 context) {
/* 31 */     class_1792 targetItem = ((Boolean)this.crapple.getValue()).booleanValue() ? class_1802.field_8463 : class_1802.field_8367;
/*    */     
/* 33 */     if (getItemCount(targetItem) == 0) {
/*    */       return;
/*    */     }
/* 36 */     float xPos = ModuleManager.crosshair.getAnimatedPosX();
/* 37 */     float yPos = ModuleManager.crosshair.getAnimatedPosY();
/*    */     
/* 39 */     float factor = (this.angle > 0.0F) ? (this.angle / 15.0F) : 0.0F;
/* 40 */     float factor2 = 1.0F - mc.field_1724.method_6048() / 40.0F;
/*    */     
/* 42 */     if (mc.field_1724.method_6030().method_7909() != targetItem) {
/* 43 */       factor2 = 1.0F;
/*    */     }
/* 45 */     factor2 = MathUtility.clamp(factor2, 0.01F, 1.0F);
/*    */     
/* 47 */     context.method_51448().method_22903();
/* 48 */     context.method_51448().method_46416(xPos, yPos, 0.0F);
/* 49 */     context.method_51448().method_22907(class_7833.field_40717.rotation((float)Math.toRadians(-Render2DEngine.interpolateFloat(this.prevAngle, this.angle, Render3DEngine.getTickDelta()))));
/* 50 */     context.method_51448().method_46416(-xPos, -yPos, 0.0F);
/*    */     
/* 52 */     RenderSystem.setShaderColor(0.3F, 0.3F, 0.3F, 1.0F);
/* 53 */     context.method_51448().method_46416(xPos + 20.0F, yPos - 9.0F, 0.0F);
/* 54 */     context.method_51427(targetItem.method_7854(), 0, 0);
/* 55 */     context.method_51448().method_46416(-(xPos + 20.0F), -(yPos - 9.0F), 0.0F);
/* 56 */     RenderSystem.setShaderColor(1.0F, 1.0F - factor, 1.0F - factor, 1.0F);
/*    */     
/* 58 */     context.method_51448().method_46416(xPos + 28.0F, yPos - 1.0F, 0.0F);
/* 59 */     context.method_51448().method_22905(factor2, factor2, 1.0F);
/* 60 */     context.method_51427(targetItem.method_7854(), -8, -8);
/* 61 */     context.method_51448().method_22905((factor2 != 0.0F) ? (1.0F / factor2) : 1.0F, (factor2 != 0.0F) ? (1.0F / factor2) : 1.0F, 1.0F);
/* 62 */     context.method_51448().method_46416(-(xPos + 28.0F), -(yPos - 1.0F), 0.0F);
/*    */     
/* 64 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 66 */     if (factor > 0.0F) {
/* 67 */       Render2DEngine.drawBlurredShadow(context.method_51448(), xPos + 22.0F, yPos - 6.0F, 11.0F, 11.0F, 8, Render2DEngine.injectAlpha(new Color(16717056), (int)(255.0F * factor)));
/*    */     }
/* 69 */     FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), "" + getItemCount(targetItem), (xPos + 28.5F), (yPos + 8.0F), -1);
/* 70 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 71 */     context.method_51448().method_22909();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onEatFood(EventEatFood e) {
/* 76 */     if (e.getFood().method_7909() == class_1802.field_8463 || e.getFood().method_7909() == class_1802.field_8367) {
/* 77 */       this.angle = 15.0F;
/*    */     }
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 82 */     this.prevAngle = this.angle;
/* 83 */     if (this.angle > 0.0F)
/* 84 */       this.angle--; 
/*    */   }
/*    */   
/*    */   public int getItemCount(class_1792 item) {
/* 88 */     if (mc.field_1724 == null) return 0; 
/* 89 */     int n = 0;
/* 90 */     int n2 = 44;
/* 91 */     for (int i = 0; i <= n2; i++) {
/* 92 */       class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
/* 93 */       if (itemStack.method_7909() == item)
/* 94 */         n += itemStack.method_7947(); 
/*    */     } 
/* 96 */     return n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\GapplesHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */