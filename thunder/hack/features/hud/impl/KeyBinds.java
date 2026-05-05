/*     */ package thunder.hack.features.hud.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_332;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class KeyBinds
/*     */   extends HudElement {
/*  20 */   public final Setting<ColorSetting> oncolor = new Setting("OnColor", new ColorSetting(-1));
/*  21 */   public final Setting<ColorSetting> offcolor = new Setting("OffColor", new ColorSetting(1));
/*  22 */   public final Setting<Boolean> onlyEnabled = new Setting("OnlyEnabled", Boolean.valueOf(false)); private float vAnimation; private float hAnimation;
/*     */   
/*     */   public KeyBinds() {
/*  25 */     super("KeyBinds", 100, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRender2D(class_332 context) {
/*  31 */     super.onRender2D(context);
/*     */     
/*  33 */     int y_offset1 = 0;
/*  34 */     float max_width = 50.0F;
/*  35 */     float maxBindWidth = 0.0F;
/*     */     
/*  37 */     float pointerX = 0.0F;
/*  38 */     for (Module feature : Managers.MODULE.modules) {
/*  39 */       if ((!feature.isDisabled() || !((Boolean)this.onlyEnabled.getValue()).booleanValue()) && 
/*  40 */         !Objects.equals(feature.getBind().getBind(), "None") && feature != ModuleManager.clickGui && feature != ModuleManager.thunderHackGui) {
/*  41 */         if (y_offset1 == 0) {
/*  42 */           y_offset1 += 4;
/*     */         }
/*  44 */         y_offset1 += 9;
/*     */         
/*  46 */         float nameWidth = FontRenderers.sf_bold_mini.getStringWidth(feature.getName());
/*  47 */         float bindWidth = FontRenderers.sf_bold_mini.getStringWidth(getShortKeyName(feature));
/*     */         
/*  49 */         if (bindWidth > maxBindWidth) {
/*  50 */           maxBindWidth = bindWidth;
/*     */         }
/*  52 */         if (nameWidth > pointerX) {
/*  53 */           pointerX = nameWidth;
/*     */         }
/*     */       } 
/*     */     } 
/*  57 */     float px = getPosX() + 10.0F + pointerX;
/*  58 */     max_width = Math.max(20.0F + pointerX + maxBindWidth, 50.0F);
/*     */     
/*  60 */     this.vAnimation = AnimationUtility.fast(this.vAnimation, (14 + y_offset1), 15.0F);
/*  61 */     this.hAnimation = AnimationUtility.fast(this.hAnimation, max_width, 15.0F);
/*     */     
/*  63 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), this.hAnimation, this.vAnimation, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*     */     
/*  65 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Glowing)) {
/*  66 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), "KeyBinds", (getPosX() + this.hAnimation / 2.0F), (getPosY() + 4.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*     */     } else {
/*  68 */       FontRenderers.sf_bold.drawGradientCenteredString(context.method_51448(), "KeyBinds", getPosX() + this.hAnimation / 2.0F, getPosY() + 4.0F, 10);
/*     */     } 
/*     */     
/*  71 */     if (y_offset1 > 0) {
/*  72 */       if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/*  73 */         Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX() + 4.0F, getPosY() + 13.0F, getPosX() + getWidth() - 4.0F, getPosY() + 13.5F, new Color(1426063359, true));
/*     */       } else {
/*  75 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 14.0F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*  76 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation - 4.0F, getPosY() + 14.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  81 */     Render2DEngine.addWindow(context.method_51448(), getPosX(), getPosY(), getPosX() + this.hAnimation, getPosY() + this.vAnimation, 1.0D);
/*  82 */     int y_offset = 0;
/*  83 */     for (Module feature : Managers.MODULE.modules) {
/*  84 */       if (feature.isDisabled() && ((Boolean)this.onlyEnabled.getValue()).booleanValue())
/*     */         continue; 
/*  86 */       if (!Objects.equals(feature.getBind().getBind(), "None") && feature != ModuleManager.clickGui && feature != ModuleManager.thunderHackGui) {
/*  87 */         FontRenderers.sf_bold_mini.drawString(context.method_51448(), feature.getName(), (getPosX() + 5.0F), (getPosY() + 19.0F + y_offset), feature.isOn() ? ((ColorSetting)this.oncolor.getValue()).getColor() : ((ColorSetting)this.offcolor.getValue()).getColor());
/*  88 */         FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), getShortKeyName(feature), (px + (
/*     */             
/*  90 */             getPosX() + max_width - px) / 2.0F), (
/*     */             
/*  92 */             getPosY() + 19.0F + y_offset), feature.isOn() ? ((ColorSetting)this.oncolor.getValue()).getColor() : ((ColorSetting)this.offcolor.getValue()).getColor());
/*  93 */         Render2DEngine.drawRect(context.method_51448(), px, getPosY() + 17.0F + y_offset, 0.5F, 8.0F, new Color(1157627903, true));
/*     */         
/*  95 */         y_offset += 9;
/*     */       } 
/*     */     } 
/*  98 */     Render2DEngine.popWindow();
/*  99 */     setBounds(getPosX(), getPosY(), this.hAnimation, this.vAnimation);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getShortKeyName(Module feature) {
/* 104 */     String sbind = feature.getBind().getBind();
/* 105 */     switch (feature.getBind().getBind()) { case "LEFT_CONTROL": case "RIGHT_CONTROL": case "LEFT_SHIFT": case "RIGHT_SHIFT": case "LEFT_ALT": case "RIGHT_ALT":  }  return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       sbind.toUpperCase();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\KeyBinds.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */