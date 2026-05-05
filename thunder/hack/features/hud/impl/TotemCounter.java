/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_7833;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.TotemPopEvent;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class TotemCounter
/*    */   extends HudElement {
/*    */   public TotemCounter() {
/* 21 */     super("TotemCounter", 0, 0);
/*    */   }
/*    */   private float angle;
/*    */   private float prevAngle;
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 27 */     if (getItemCount(class_1802.field_8288) == 0) {
/*    */       return;
/*    */     }
/* 30 */     float xPos = ModuleManager.crosshair.getAnimatedPosX();
/* 31 */     float yPos = ModuleManager.crosshair.getAnimatedPosY();
/*    */     
/* 33 */     float factor = Math.abs((this.angle < 0.0F) ? (this.angle / 15.0F) : 0.0F);
/*    */     
/* 35 */     context.method_51448().method_22903();
/* 36 */     context.method_51448().method_46416(xPos, yPos, 0.0F);
/* 37 */     context.method_51448().method_22907(class_7833.field_40717.rotation((float)Math.toRadians(-Render2DEngine.interpolateFloat(this.prevAngle, this.angle, Render3DEngine.getTickDelta()))));
/* 38 */     context.method_51448().method_46416(-xPos, -yPos, 0.0F);
/*    */     
/* 40 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     context.method_51448().method_46416(xPos - 36.0F, yPos - 9.0F, 0.0F);
/* 42 */     context.method_51427(class_1802.field_8288.method_7854(), 0, 0);
/* 43 */     context.method_51448().method_46416(-(xPos - 36.0F), -(yPos - 9.0F), 0.0F);
/* 44 */     RenderSystem.setShaderColor(1.0F, 1.0F - factor, 1.0F - factor, 1.0F);
/*    */     
/* 46 */     if (factor > 0.0F) {
/* 47 */       Render2DEngine.drawBlurredShadow(context.method_51448(), xPos - 34.0F, yPos - 6.0F, 11.0F, 11.0F, 8, Render2DEngine.injectAlpha(new Color(16711680), (int)(255.0F * factor)));
/*    */     }
/* 49 */     FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), "" + getItemCount(class_1802.field_8288), (xPos - 28.0F), (yPos + 8.0F), -1);
/* 50 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 51 */     context.method_51448().method_22909();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onTotemPop(TotemPopEvent e) {
/* 56 */     if (e.getEntity() == mc.field_1724) {
/* 57 */       this.angle = -15.0F;
/*    */     }
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 62 */     this.prevAngle = this.angle;
/* 63 */     if (this.angle < 0.0F)
/* 64 */       this.angle++; 
/*    */   }
/*    */   
/*    */   public int getItemCount(class_1792 item) {
/* 68 */     if (mc.field_1724 == null) return 0; 
/* 69 */     int n = 0;
/* 70 */     int n2 = 44;
/* 71 */     for (int i = 0; i <= n2; i++) {
/* 72 */       class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
/* 73 */       if (itemStack.method_7909() == item)
/* 74 */         n += itemStack.method_7947(); 
/*    */     } 
/* 76 */     return n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\TotemCounter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */