/*    */ package thunder.hack.setting.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public final class ColorSetting
/*    */ {
/*    */   private int color;
/*    */   private final int defaultColor;
/*    */   private boolean rainbow;
/*    */   
/*    */   public ColorSetting(@NotNull Color color) {
/* 15 */     this(color.getRGB());
/*    */   }
/*    */   
/*    */   public ColorSetting(int color) {
/* 19 */     this.color = color;
/* 20 */     this.defaultColor = color;
/*    */   }
/*    */   @NotNull
/*    */   public ColorSetting withAlpha(int alpha) {
/* 24 */     int red = getColor() >> 16 & 0xFF;
/* 25 */     int green = getColor() >> 8 & 0xFF;
/* 26 */     int blue = getColor() & 0xFF;
/* 27 */     return new ColorSetting((alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColor() {
/* 34 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F).getRGB() : this.color;
/*    */   }
/*    */   
/*    */   public void setColor(int color) {
/* 38 */     this.color = color;
/*    */   }
/*    */   
/*    */   public int getRed() {
/* 42 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F).getRed() : (this.color >> 16 & 0xFF);
/*    */   }
/*    */   
/*    */   public int getGreen() {
/* 46 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F).getGreen() : (this.color >> 8 & 0xFF);
/*    */   }
/*    */   
/*    */   public int getBlue() {
/* 50 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F).getBlue() : (this.color & 0xFF);
/*    */   }
/*    */   
/*    */   public float getGlRed() {
/* 54 */     return getRed() / 255.0F;
/*    */   }
/*    */   
/*    */   public float getGlBlue() {
/* 58 */     return getBlue() / 255.0F;
/*    */   }
/*    */   
/*    */   public float getGlGreen() {
/* 62 */     return getGreen() / 255.0F;
/*    */   }
/*    */   
/*    */   public float getGlAlpha() {
/* 66 */     return getAlpha() / 255.0F;
/*    */   }
/*    */   
/*    */   public int getAlpha() {
/* 70 */     return this.color >> 24 & 0xFF;
/*    */   }
/*    */   @NotNull
/*    */   public Color getColorObject() {
/* 74 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F) : new Color(this.color, true);
/*    */   }
/*    */   
/*    */   public int getRawColor() {
/* 78 */     return this.rainbow ? Render2DEngine.rainbow(((Integer)HudEditor.colorSpeed.getValue()).intValue(), 1, 1.0F, 1.0F, 1.0F).getRGB() : this.color;
/*    */   }
/*    */   
/*    */   public boolean isRainbow() {
/* 82 */     return this.rainbow;
/*    */   }
/*    */   
/*    */   public void setRainbow(boolean rainbow) {
/* 86 */     this.rainbow = rainbow;
/*    */   }
/*    */   
/*    */   public void setDefault() {
/* 90 */     setColor(this.defaultColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\impl\ColorSetting.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */