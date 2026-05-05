/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class Cooldowns extends HudElement {
/*    */   public Cooldowns() {
/* 14 */     super("Cooldowns", 100, 100);
/*    */   }
/*    */   
/*    */   private float animation1;
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 20 */     super.onRender2D(context);
/* 21 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), 100.0F, 40.0F, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*    */     
/* 23 */     this.animation1 = AnimationUtility.fast(this.animation1, mc.field_1724.method_7261(0.5F), 50.0F);
/* 24 */     this.animation2 = AnimationUtility.fast(this.animation2, 1.0F - mc.field_1724.field_6235 / 10.0F, 50.0F);
/*    */     
/* 26 */     Render2DEngine.drawGradientRound(context.method_51448(), 
/* 27 */         getPosX() + 30.0F, getPosY() + 20.0F, 65.0F, 5.0F, 1.5F, HudEditor.getColor(90).darker().darker().darker(), HudEditor.getColor(180).darker().darker().darker(), HudEditor.getColor(0).darker().darker().darker(), HudEditor.getColor(270).darker().darker().darker());
/* 28 */     Render2DEngine.drawGradientRound(context.method_51448(), 
/* 29 */         getPosX() + 30.0F, getPosY() + 30.0F, 65.0F, 5.0F, 1.5F, HudEditor.getColor(90).darker().darker().darker(), HudEditor.getColor(180).darker().darker().darker(), HudEditor.getColor(0).darker().darker().darker(), HudEditor.getColor(270).darker().darker().darker());
/*    */     
/* 31 */     Render2DEngine.drawRect(context.method_51448(), 
/* 32 */         getPosX() + 30.0F, getPosY() + 20.0F, 65.0F * this.animation1, 5.0F, 1.5F, 1.0F);
/*    */     
/* 34 */     Render2DEngine.drawRect(context.method_51448(), 
/* 35 */         getPosX() + 30.0F, getPosY() + 30.0F, 65.0F * this.animation2, 5.0F, 1.5F, 1.0F);
/*    */     
/* 37 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Glowing)) {
/* 38 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), "Cooldowns", (getPosX() + 50.0F), (getPosY() + 4.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*    */     } else {
/* 40 */       FontRenderers.sf_bold.drawGradientCenteredString(context.method_51448(), "Cooldowns", getPosX() + 50.0F, getPosY() + 4.0F, 10);
/*    */     } 
/*    */     
/* 43 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 44 */       Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX() + 4.0F, getPosY() + 13.0F, getPosX() + getWidth() - 4.0F, getPosY() + 13.5F, new Color(1426063359, true));
/*    */     } else {
/* 46 */       Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + 50.0F - 2.0F, getPosY() + 14.0F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/* 47 */       Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F + 50.0F - 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + 100.0F - 4.0F, getPosY() + 14.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*    */     } 
/*    */     
/* 50 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Attack", (getPosX() + 5.0F), (getPosY() + 20.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 51 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Hurt", (getPosX() + 5.0F), (getPosY() + 30.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/*    */     
/* 53 */     setBounds(getPosX(), getPosY(), 100.0F, 40.0F);
/*    */   }
/*    */   
/*    */   private float animation2;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\Cooldowns.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */