/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class TPSCounter extends HudElement {
/*    */   public TPSCounter() {
/* 19 */     super("TPS", 50, 10);
/*    */ 
/*    */     
/* 22 */     this.extraTps = new Setting("ExtraTPS", Boolean.valueOf(true));
/*    */   } private final Setting<Boolean> extraTps;
/*    */   public void onRender2D(class_332 context) {
/* 25 */     super.onRender2D(context);
/* 26 */     String str = "TPS " + String.valueOf(class_124.field_1068) + Managers.SERVER.getTPS() + (((Boolean)this.extraTps.getValue()).booleanValue() ? (" [" + Managers.SERVER.getTPS2() + "]") : "");
/*    */     
/* 28 */     float pX = (getPosX() > mc.method_22683().method_4486() / 2.0F) ? (getPosX() - FontRenderers.getModulesRenderer().getStringWidth(str)) : getPosX();
/*    */     
/* 30 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 31 */       Render2DEngine.drawRoundedBlur(context.method_51448(), pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 32 */       Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
/*    */       
/* 34 */       Render2DEngine.setupRender();
/* 35 */       RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 36 */       RenderSystem.setShaderTexture(0, TextureStorage.tpsIcon);
/* 37 */       Render2DEngine.renderGradientTexture(context.method_51448(), (pX + 2.0F), (getPosY() + 1.0F), 10.0D, 10.0D, 0.0F, 0.0F, 512.0D, 512.0D, 512.0D, 512.0D, 
/* 38 */           HudEditor.getColor(270), HudEditor.getColor(0), HudEditor.getColor(180), HudEditor.getColor(90));
/* 39 */       Render2DEngine.endRender();
/*    */     } 
/*    */     
/* 42 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), str, (pX + 18.0F), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 43 */     setBounds(pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\TPSCounter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */