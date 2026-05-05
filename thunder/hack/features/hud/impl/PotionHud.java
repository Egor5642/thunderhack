/*     */ package thunder.hack.features.hud.impl;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1291;
/*     */ import net.minecraft.class_1293;
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class PotionHud extends HudElement {
/*     */   private float vAnimation;
/*     */   
/*     */   public PotionHud() {
/*  18 */     super("Potions", 100, 100);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  23 */     this.colored = new Setting("Colored", Boolean.valueOf(false));
/*     */   } private float hAnimation; private final Setting<Boolean> colored;
/*     */   public static String getDuration(class_1293 pe) {
/*  26 */     if (pe.method_48559()) {
/*  27 */       return "*:*";
/*     */     }
/*  29 */     int var1 = pe.method_5584();
/*  30 */     int mins = var1 / 1200;
/*  31 */     String sec = String.format("%02d", new Object[] { Integer.valueOf(var1 % 1200 / 20) });
/*  32 */     return "" + mins + ":" + mins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onRender2D(class_332 context) {
/*  58 */     super.onRender2D(context);
/*     */     
/*  60 */     int y_offset1 = 0;
/*  61 */     float max_width = 50.0F;
/*     */     
/*  63 */     float pointerX = 0.0F;
/*  64 */     for (class_1293 potionEffect : mc.field_1724.method_6026()) {
/*  65 */       class_1291 potion = (class_1291)potionEffect.method_5579().comp_349();
/*     */       
/*  67 */       if (y_offset1 == 0) {
/*  68 */         y_offset1 += 4;
/*     */       }
/*  70 */       y_offset1 += 9;
/*     */       
/*  72 */       float nameWidth = FontRenderers.sf_bold_mini.getStringWidth(potion.method_5560().getString() + " " + potion.method_5560().getString());
/*  73 */       float timeWidth = FontRenderers.sf_bold_mini.getStringWidth(getDuration(potionEffect));
/*  74 */       float width = (nameWidth + timeWidth) * 1.4F;
/*     */       
/*  76 */       if (width > max_width) {
/*  77 */         max_width = width;
/*     */       }
/*  79 */       if (timeWidth > pointerX) {
/*  80 */         pointerX = timeWidth;
/*     */       }
/*     */     } 
/*  83 */     this.vAnimation = AnimationUtility.fast(this.vAnimation, (14 + y_offset1), 15.0F);
/*  84 */     this.hAnimation = AnimationUtility.fast(this.hAnimation, max_width, 15.0F);
/*     */     
/*  86 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), this.hAnimation, this.vAnimation, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*     */     
/*  88 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Glowing)) {
/*  89 */       FontRenderers.sf_bold.drawCenteredString(context.method_51448(), "Potions", (getPosX() + this.hAnimation / 2.0F), (getPosY() + 4.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*     */     } else {
/*  91 */       FontRenderers.sf_bold.drawGradientCenteredString(context.method_51448(), "Potions", getPosX() + this.hAnimation / 2.0F, getPosY() + 4.0F, 10);
/*     */     } 
/*     */     
/*  94 */     if (y_offset1 > 0) {
/*  95 */       if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/*  96 */         Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX() + 4.0F, getPosY() + 13.0F, getPosX() + getWidth() - 4.0F, getPosY() + 13.5F, new Color(1426063359, true));
/*     */       } else {
/*  98 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 14.0F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*  99 */         Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F + this.hAnimation / 2.0F - 2.0F, getPosY() + 13.7F, getPosX() + 2.0F + this.hAnimation - 4.0F, getPosY() + 14.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*     */       } 
/*     */     }
/*     */     
/* 103 */     Render2DEngine.addWindow(context.method_51448(), getPosX(), getPosY(), getPosX() + this.hAnimation, getPosY() + this.vAnimation, 1.0D);
/* 104 */     int y_offset = 0;
/* 105 */     for (class_1293 potionEffect : mc.field_1724.method_6026()) {
/* 106 */       class_1291 potion = (class_1291)potionEffect.method_5579().comp_349();
/*     */       
/* 108 */       float px = getPosX() + max_width - pointerX - 10.0F;
/*     */       
/* 110 */       context.method_51448().method_22903();
/* 111 */       context.method_51448().method_46416(getPosX() + 2.0F, getPosY() + 16.0F + y_offset, 0.0F);
/* 112 */       context.method_25298(0, 0, 0, 8, 8, mc.method_18505().method_18663(potionEffect.method_5579()));
/* 113 */       context.method_51448().method_22909();
/*     */       
/* 115 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), potion.method_5560().getString() + " " + potion.method_5560().getString() + String.valueOf(class_124.field_1061), (getPosX() + 12.0F), (getPosY() + 19.0F + y_offset), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 116 */       FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), getDuration(potionEffect), (px + (getPosX() + max_width - px) / 2.0F), (getPosY() + 19.0F + y_offset), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 117 */       Render2DEngine.drawRect(context.method_51448(), px, getPosY() + 17.0F + y_offset, 0.5F, 8.0F, new Color(1157627903, true));
/* 118 */       y_offset += 9;
/*     */     } 
/* 120 */     Render2DEngine.popWindow();
/* 121 */     setBounds(getPosX(), getPosY(), this.hAnimation, this.vAnimation);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\PotionHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */