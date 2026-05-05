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
/*    */ public class Speedometer
/*    */   extends HudElement
/*    */ {
/* 20 */   public float speed = 0.0F;
/* 21 */   private final Setting<Boolean> bps = new Setting("BPS", Boolean.valueOf(false));
/* 22 */   private final Setting<Boolean> average = new Setting("Average", Boolean.valueOf(false));
/*    */   
/*    */   public Speedometer() {
/* 25 */     super("Speedometer", 50, 10);
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 29 */     super.onRender2D(context);
/*    */     
/* 31 */     String str = "Speed " + String.valueOf(class_124.field_1068);
/* 32 */     if (!((Boolean)this.bps.getValue()).booleanValue()) {
/* 33 */       str = str + str + " km/h";
/*    */     } else {
/* 35 */       str = str + str + " b/s";
/*    */     } 
/*    */     
/* 38 */     float pX = (getPosX() > mc.method_22683().method_4486() / 2.0F) ? (getPosX() - FontRenderers.getModulesRenderer().getStringWidth(str)) : getPosX();
/*    */     
/* 40 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 41 */       Render2DEngine.drawRoundedBlur(context.method_51448(), pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 42 */       Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
/*    */       
/* 44 */       Render2DEngine.setupRender();
/* 45 */       RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 46 */       RenderSystem.setShaderTexture(0, TextureStorage.speedometerIcon);
/* 47 */       Render2DEngine.renderGradientTexture(context.method_51448(), (pX + 2.0F), (getPosY() + 1.0F), 10.0D, 10.0D, 0.0F, 0.0F, 512.0D, 512.0D, 512.0D, 512.0D, 
/* 48 */           HudEditor.getColor(270), HudEditor.getColor(0), HudEditor.getColor(180), HudEditor.getColor(90));
/* 49 */       Render2DEngine.endRender();
/*    */     } 
/*    */     
/* 52 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), str, (pX + 18.0F), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 53 */     setBounds(pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F);
/*    */   }
/*    */   
/*    */   public float getSpeedKpH() {
/* 57 */     return (((Boolean)this.average.getValue()).booleanValue() ? Managers.PLAYER.averagePlayerSpeed : Managers.PLAYER.currentPlayerSpeed) * 72.0F;
/*    */   }
/*    */   
/*    */   public float getSpeedMpS() {
/* 61 */     return (((Boolean)this.average.getValue()).booleanValue() ? Managers.PLAYER.averagePlayerSpeed : Managers.PLAYER.currentPlayerSpeed) * 20.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\Speedometer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */