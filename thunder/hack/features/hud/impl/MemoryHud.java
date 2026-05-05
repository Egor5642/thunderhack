/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class MemoryHud extends HudElement {
/*    */   public MemoryHud() {
/* 17 */     super("MemoryHud", 100, 10);
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 21 */     super.onRender2D(context);
/* 22 */     long m = Runtime.getRuntime().maxMemory();
/* 23 */     long t = Runtime.getRuntime().totalMemory();
/* 24 */     long f = Runtime.getRuntime().freeMemory();
/* 25 */     long o = t - f;
/* 26 */     String str = "Mem: " + String.valueOf(class_124.field_1068) + toMiB(o) + "/" + toMiB(m) + "MB [" + o * 100L / m + "%]";
/*    */     
/* 28 */     float pX = (getPosX() > mc.method_22683().method_4486() / 2.0F) ? (getPosX() - FontRenderers.getModulesRenderer().getStringWidth(str)) : getPosX();
/*    */     
/* 30 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 31 */       Render2DEngine.drawRoundedBlur(context.method_51448(), pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 32 */       Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
/*    */       
/* 34 */       Render2DEngine.setupRender();
/* 35 */       RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 36 */       RenderSystem.setShaderTexture(0, TextureStorage.memoryIcon);
/* 37 */       Render2DEngine.renderGradientTexture(context.method_51448(), (pX + 2.0F), (getPosY() + 1.0F), 10.0D, 10.0D, 0.0F, 0.0F, 512.0D, 512.0D, 512.0D, 512.0D, 
/* 38 */           HudEditor.getColor(270), HudEditor.getColor(0), HudEditor.getColor(180), HudEditor.getColor(90));
/* 39 */       Render2DEngine.endRender();
/*    */     } 
/*    */     
/* 42 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), str, (pX + 18.0F), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 43 */     setBounds(pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F);
/*    */   }
/*    */   
/*    */   private long toMiB(long bytes) {
/* 47 */     return bytes / 1024L / 1024L;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\MemoryHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */