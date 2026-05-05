/*     */ package thunder.hack.gui.clickui.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.gui.clickui.AbstractElement;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class ColorPickerElement
/*     */   extends AbstractElement
/*     */ {
/*     */   private float hue;
/*     */   private float saturation;
/*     */   private float brightness;
/*     */   private int alpha;
/*     */   private boolean afocused;
/*     */   private boolean hfocused;
/*     */   private boolean sbfocused;
/*     */   private float spos;
/*     */   private float bpos;
/*     */   private float hpos;
/*     */   private float apos;
/*     */   private Color prevColor;
/*     */   private boolean firstInit;
/*     */   private boolean extended;
/*     */   private final Setting colorSetting;
/*     */   
/*     */   public ColorSetting getColorSetting() {
/*  34 */     return (ColorSetting)this.colorSetting.getValue();
/*     */   }
/*     */   
/*     */   public ColorPickerElement(Setting setting) {
/*  38 */     super(setting);
/*  39 */     this.colorSetting = setting;
/*  40 */     this.prevColor = getColorSetting().getColorObject();
/*  41 */     updatePos();
/*  42 */     this.firstInit = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*  48 */     class_4587 matrixStack = context.method_51448();
/*     */     
/*  50 */     boolean colorHovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, (this.y + 5.0F), 90.0D, 7.0D);
/*     */     
/*  52 */     FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), (this.x + 6.0F), (this.y + 8.0F), (new Color(-1)).getRGB());
/*     */     
/*  54 */     Render2DEngine.drawBlurredShadow(matrixStack, this.x + this.width - 22.0F, this.y + 5.0F, 14.0F, 7.0F, colorHovered ? 6 : 10, getColorSetting().getColorObject());
/*  55 */     if (colorHovered) {
/*  56 */       Render2DEngine.drawRound(matrixStack, this.x + this.width - 22.5F, this.y + 4.5F, 15.0F, 8.0F, 1.0F, getColorSetting().getColorObject());
/*     */     } else {
/*  58 */       Render2DEngine.drawRound(matrixStack, this.x + this.width - 22.0F, this.y + 5.0F, 14.0F, 7.0F, 1.0F, getColorSetting().getColorObject());
/*     */     } 
/*  60 */     if (!this.extended) {
/*     */       return;
/*     */     }
/*  63 */     boolean rainbowHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 36.0F), (this.y + 54.0F), 24.0D, 7.0D);
/*  64 */     boolean copyHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 9.0F), (this.y + 54.0F), 24.0D, 7.0D);
/*  65 */     boolean pasteHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 63.0F), (this.y + 54.0F), 24.0D, 7.0D);
/*  66 */     boolean dark = Render2DEngine.isDark(ThunderHack.copy_color);
/*  67 */     boolean dark2 = Render2DEngine.isDark(getColorSetting().getColorObject());
/*     */     
/*  69 */     Render2DEngine.drawRect(matrixStack, this.x + 9.0F, this.y + 54.0F, 24.0F, 7.0F, new Color(4342338));
/*  70 */     FontRenderers.sf_medium_mini.drawString(matrixStack, "Сopy", (this.x + 13.0F), (this.y + 56.5F), copyHovered ? (new Color(-1543503873, true)).getRGB() : Color.WHITE.getRGB());
/*     */     
/*  72 */     Render2DEngine.drawRect(matrixStack, this.x + 36.0F, this.y + 54.0F, 24.0F, 7.0F, getColorSetting().isRainbow() ? getColorSetting().getColorObject() : new Color(4342338));
/*  73 */     FontRenderers.sf_medium_mini.drawString(matrixStack, "RB", (this.x + 44.0F), (this.y + 56.5F), rainbowHovered ? (new Color(-1543503873, true)).getRGB() : (dark2 ? Color.WHITE.getRGB() : Color.BLACK.getRGB()));
/*     */     
/*  75 */     Render2DEngine.drawRect(matrixStack, this.x + 63.0F, this.y + 54.0F, 24.0F, 7.0F, ThunderHack.copy_color);
/*  76 */     FontRenderers.sf_medium_mini.drawString(matrixStack, "Paste", (this.x + 67.0F), (this.y + 56.5F), pasteHovered ? (new Color(-1543503873, true)).getRGB() : (dark ? Color.WHITE.getRGB() : Color.BLACK.getRGB()));
/*     */     
/*  78 */     renderPicker(matrixStack, mouseX, mouseY, getColorSetting().getColorObject());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeight() {
/*  83 */     return this.extended ? 66.0F : 15.0F;
/*     */   }
/*     */   
/*     */   private void renderPicker(class_4587 matrixStack, int mouseX, int mouseY, Color color) {
/*  87 */     double cx = (this.x + 6.0F);
/*  88 */     double cy = (this.y + 16.0F);
/*  89 */     double cw = (this.width - 38.0F);
/*  90 */     double ch = (this.height - 30.0F);
/*     */     
/*  92 */     if (this.prevColor != getColorSetting().getColorObject()) {
/*  93 */       updatePos();
/*  94 */       this.prevColor = getColorSetting().getColorObject();
/*     */     } 
/*     */     
/*  97 */     if (this.firstInit) {
/*  98 */       this.spos = (float)(cx + cw - cw - cw * this.saturation);
/*  99 */       this.bpos = (float)(cy + ch - ch * this.brightness);
/* 100 */       this.hpos = (float)(cy + ch - 3.0D + (ch - 3.0D) * this.hue);
/* 101 */       this.apos = (float)(cy + ch - 3.0D - (ch - 3.0D) * (this.alpha / 255.0F));
/* 102 */       this.firstInit = false;
/*     */     } 
/*     */     
/* 105 */     this.spos = Render2DEngine.scrollAnimate(this.spos, (float)(cx + cw - cw - cw * this.saturation), 0.6F);
/* 106 */     this.bpos = Render2DEngine.scrollAnimate(this.bpos, (float)(cy + ch - ch * this.brightness), 0.6F);
/* 107 */     this.hpos = Render2DEngine.scrollAnimate(this.hpos, (float)(cy + ch - 3.0D + (ch - 3.0D) * this.hue), 0.6F);
/* 108 */     this.apos = Render2DEngine.scrollAnimate(this.apos, (float)(cy + ch - 3.0D - (ch - 3.0D) * (this.alpha / 255.0F)), 0.6F);
/*     */     
/* 110 */     Color colorA = Color.getHSBColor(this.hue, 0.0F, 1.0F), colorB = Color.getHSBColor(this.hue, 1.0F, 1.0F);
/* 111 */     Color colorC = new Color(0, 0, 0, 0), colorD = new Color(0, 0, 0);
/*     */     
/* 113 */     Render2DEngine.horizontalGradient(matrixStack, (float)cx + 2.0F, (float)cy, (float)(cx + cw), (float)(cy + ch), colorA, colorB);
/* 114 */     Render2DEngine.verticalGradient(matrixStack, (float)(cx + 2.0D), (float)cy, (float)(cx + cw), (float)(cy + ch), colorC, colorD);
/*     */     float i;
/* 116 */     for (i = 1.0F; i < ch - 2.0D; i++) {
/* 117 */       float curHue = (float)(1.0D / ch / i);
/* 118 */       Render2DEngine.drawRect(matrixStack, (float)(cx + cw + 4.0D), (float)(cy + i), 8.0F, 1.0F, Color.getHSBColor(curHue, 1.0F, 1.0F));
/*     */     } 
/*     */     
/* 121 */     Render2DEngine.drawRect(matrixStack, (float)(cx + cw + 17.0D), (float)(cy + 1.0D), 8.0F, (float)(ch - 3.0D), new Color(-1));
/*     */     
/* 123 */     Render2DEngine.verticalGradient(matrixStack, (float)(cx + cw + 17.0D), (float)(cy + 0.800000011920929D), (float)(cx + cw + 25.0D), (float)(cy + ch - 2.0D), new Color(color.getRed(), color.getGreen(), color.getBlue(), 255), new Color(0, 0, 0, 0));
/*     */     
/* 125 */     Render2DEngine.drawRect(matrixStack, (float)(cx + cw + 3.0D), this.hpos + 0.5F, 10.0F, 1.0F, Color.WHITE);
/* 126 */     Render2DEngine.drawRect(matrixStack, (float)(cx + cw + 16.0D), this.apos + 0.5F, 10.0F, 1.0F, Color.WHITE);
/* 127 */     Render2DEngine.drawRound(matrixStack, this.spos - 1.5F, this.bpos - 1.5F, 3.0F, 3.0F, 1.5F, new Color(-1));
/*     */     
/* 129 */     Color value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/*     */     
/* 131 */     if (this.sbfocused) {
/* 132 */       this.saturation = (float)(MathUtility.clamp((float)(mouseX - cx), 0.0F, (float)cw) / cw);
/* 133 */       this.brightness = (float)((ch - MathUtility.clamp((float)(mouseY - cy), 0.0F, (float)ch)) / ch);
/* 134 */       value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/* 135 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */     
/* 138 */     if (this.hfocused) {
/* 139 */       this.hue = (float)-((ch - MathUtility.clamp((float)(mouseY - cy), 0.0F, (float)ch)) / ch);
/* 140 */       value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/* 141 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */     
/* 144 */     if (this.afocused) {
/* 145 */       this.alpha = (int)((ch - MathUtility.clamp((float)(mouseY - cy), 0.0F, (float)ch)) / ch * 255.0D);
/* 146 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updatePos() {
/* 151 */     float[] hsb = Color.RGBtoHSB(getColorSetting().getColorObject().getRed(), getColorSetting().getColorObject().getGreen(), getColorSetting().getColorObject().getBlue(), null);
/* 152 */     this.hue = -1.0F + hsb[0];
/* 153 */     this.saturation = hsb[1];
/* 154 */     this.brightness = hsb[2];
/* 155 */     this.alpha = getColorSetting().getAlpha();
/*     */   }
/*     */   
/*     */   private void setColor(Color color) {
/* 159 */     getColorSetting().setColor(color.getRGB());
/* 160 */     this.prevColor = color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 165 */     double cx = (this.x + 4.0F);
/* 166 */     double cy = (this.y + 17.0F);
/* 167 */     double cw = (this.width - 34.0F);
/* 168 */     double ch = (this.height - 30.0F);
/*     */     
/* 170 */     boolean rainbowHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 36.0F), (this.y + 54.0F), 24.0D, 7.0D);
/* 171 */     boolean copyHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 9.0F), (this.y + 54.0F), 24.0D, 7.0D);
/* 172 */     boolean pasteHovered = Render2DEngine.isHovered(mouseX, mouseY, (this.x + 63.0F), (this.y + 54.0F), 24.0D, 7.0D);
/* 173 */     boolean colorHovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, 90.0D, 11.0D);
/*     */     
/* 175 */     if (colorHovered) {
/* 176 */       this.extended = !this.extended;
/*     */     }
/* 178 */     if (!this.extended) {
/*     */       return;
/*     */     }
/* 181 */     if (Render2DEngine.isHovered(mouseX, mouseY, cx + cw + 17.0D, cy, 8.0D, ch) && button == 0) { this.afocused = true; }
/*     */     
/* 183 */     else if (Render2DEngine.isHovered(mouseX, mouseY, cx + cw + 4.0D, cy, 8.0D, ch) && button == 0) { this.hfocused = true; }
/*     */     
/* 185 */     else if (Render2DEngine.isHovered(mouseX, mouseY, cx, cy, cw, ch) && button == 0) { this.sbfocused = true; }
/*     */     
/* 187 */     else if (rainbowHovered && button == 0) { getColorSetting().setRainbow(!getColorSetting().isRainbow()); }
/*     */     
/* 189 */     else if (copyHovered) { ThunderHack.copy_color = getColorSetting().getColorObject(); }
/*     */     
/* 191 */     else if (pasteHovered) { getColorSetting().setColor(ThunderHack.copy_color.getRGB()); }
/*     */     
/* 193 */     super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 198 */     this.hfocused = false;
/* 199 */     this.afocused = false;
/* 200 */     this.sbfocused = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 205 */     this.hfocused = false;
/* 206 */     this.afocused = false;
/* 207 */     this.sbfocused = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\ColorPickerElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */