/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class ColorPickerComponent
/*     */   extends SettingElement {
/*     */   private final Setting colorSetting;
/*     */   private boolean open;
/*     */   private float hue;
/*     */   private float saturation;
/*     */   private float brightness;
/*     */   private int alpha;
/*     */   private boolean afocused;
/*     */   private boolean hfocused;
/*     */   private boolean sbfocused;
/*     */   private boolean copy_focused;
/*     */   private boolean paste_focused;
/*     */   private boolean rainbow_focused;
/*     */   private float spos;
/*     */   private float bpos;
/*     */   private float hpos;
/*     */   private float apos;
/*     */   private Color prevColor;
/*     */   private boolean firstInit;
/*     */   
/*     */   public ColorPickerComponent(Setting setting) {
/*  35 */     super(setting);
/*  36 */     this.colorSetting = setting;
/*  37 */     this.firstInit = true;
/*     */   }
/*     */   
/*     */   public ColorSetting getColorSetting() {
/*  41 */     return (ColorSetting)this.colorSetting.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_4587 stack, int mouseX, int mouseY, float delta) {
/*  46 */     super.render(stack, mouseX, mouseY, delta);
/*  47 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/*  50 */     FontRenderers.modules.drawString(stack, getSetting().getName(), getX(), (getY() + 5.0F), isHovered() ? -1 : (new Color(-1325400065, true)).getRGB());
/*  51 */     Render2DEngine.drawBlurredShadow(stack, (int)(this.x + this.width - 20.0F), (int)(this.y + 5.0F), 14.0F, 6.0F, 10, getColorSetting().getColorObject());
/*  52 */     Render2DEngine.drawRound(stack, this.x + this.width - 20.0F, this.y + 5.0F, 14.0F, 6.0F, 1.0F, getColorSetting().getColorObject());
/*  53 */     if (this.open) {
/*  54 */       renderPicker(stack, mouseX, mouseY, getColorSetting().getColorObject());
/*     */     }
/*     */   }
/*     */   
/*     */   public void onTick() {
/*  59 */     super.onTick();
/*     */   }
/*     */   
/*     */   private void renderPicker(class_4587 stack, int mouseX, int mouseY, Color color) {
/*  63 */     double cx = (this.x + 6.0F);
/*  64 */     float cy = this.y + 20.0F;
/*  65 */     double cw = (this.width - 38.0F);
/*  66 */     double ch = (this.height - 20.0F);
/*     */     
/*  68 */     if (this.prevColor != getColorSetting().getColorObject()) {
/*  69 */       updatePos();
/*  70 */       this.prevColor = getColorSetting().getColorObject();
/*     */     } 
/*     */     
/*  73 */     if (this.firstInit) {
/*  74 */       this.spos = (float)(cx + cw - cw - cw * this.saturation);
/*  75 */       this.bpos = (float)(cy + ch - ch * this.brightness);
/*  76 */       this.hpos = (float)(cy + ch - 3.0D + (ch - 3.0D) * this.hue);
/*  77 */       this.apos = (float)(cy + ch - 3.0D - (ch - 3.0D) * (this.alpha / 255.0F));
/*  78 */       this.firstInit = false;
/*     */     } 
/*     */     
/*  81 */     this.spos = Render2DEngine.scrollAnimate(this.spos, (float)(cx + 40.0D + cw - 40.0D - cw - 40.0D - (cw - 40.0D) * this.saturation), 0.6F);
/*  82 */     this.bpos = Render2DEngine.scrollAnimate(this.bpos, (float)(cy + ch - ch * this.brightness), 0.6F);
/*  83 */     this.hpos = Render2DEngine.scrollAnimate(this.hpos, (float)(cy + ch - 3.0D + (ch - 3.0D) * this.hue), 0.6F);
/*  84 */     this.apos = Render2DEngine.scrollAnimate(this.apos, (float)(cy + ch - 3.0D - (ch - 3.0D) * (this.alpha / 255.0F)), 0.6F);
/*     */     
/*  86 */     Color colorA = Color.getHSBColor(this.hue, 0.0F, 1.0F), colorB = Color.getHSBColor(this.hue, 1.0F, 1.0F);
/*  87 */     Color colorC = new Color(0, 0, 0, 0), colorD = new Color(0, 0, 0);
/*     */     
/*  89 */     Render2DEngine.horizontalGradient(stack, (float)(cx + 40.0D), cy, (float)(cx + cw), (float)(cy + ch), colorA, colorB);
/*  90 */     Render2DEngine.verticalGradient(stack, (float)(cx + 40.0D), cy, (float)(cx + cw), (float)(cy + ch), colorC, colorD);
/*     */     float i;
/*  92 */     for (i = 1.0F; i < ch - 2.0D; i++) {
/*  93 */       float curHue = (float)(1.0D / ch / i);
/*  94 */       Render2DEngine.drawRect(stack, (float)(cx + cw + 4.0D), cy + i, 8.0F, 1.0F, Color.getHSBColor(curHue, 1.0F, 1.0F));
/*     */     } 
/*     */     
/*  97 */     Render2DEngine.drawRect(stack, (float)(cx + cw + 17.0D), cy + 1.0F, 8.0F, (float)(ch - 3.0D), new Color(-1));
/*     */     
/*  99 */     Render2DEngine.verticalGradient(stack, (float)(cx + cw + 17.0D), (float)(cy + 0.8D), (float)(cx + cw + 25.0D), (float)(cy + ch - 2.0D), new Color(color.getRed(), color.getGreen(), color.getBlue(), 255), new Color(0, 0, 0, 0));
/*     */     
/* 101 */     Render2DEngine.drawRect(stack, (float)(cx + cw + 3.0D), this.hpos + 0.5F, 10.0F, 1.0F, Color.WHITE);
/* 102 */     Render2DEngine.drawRect(stack, (float)(cx + cw + 16.0D), this.apos + 0.5F, 10.0F, 1.0F, Color.WHITE);
/* 103 */     Render2DEngine.drawRound(stack, this.spos, this.bpos, 3.0F, 3.0F, 1.5F, new Color(-1));
/*     */     
/* 105 */     Color value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/*     */     
/* 107 */     if (this.sbfocused) {
/* 108 */       this.saturation = (float)(MathUtility.clamp(mouseX - cx + 40.0D, 0.0D, cw - 40.0D) / (cw - 40.0D));
/*     */       
/* 110 */       this.brightness = (float)((ch - MathUtility.clamp((mouseY - cy), 0.0D, ch)) / ch);
/* 111 */       value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/* 112 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */     
/* 115 */     if (this.hfocused) {
/* 116 */       this.hue = (float)-((ch - MathUtility.clamp(mouseY - cy, 0.0F, (float)ch)) / ch);
/* 117 */       value = Color.getHSBColor(this.hue, this.saturation, this.brightness);
/* 118 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */     
/* 121 */     if (this.afocused) {
/* 122 */       this.alpha = (int)((ch - MathUtility.clamp(mouseY - cy, 0.0F, (float)ch)) / ch * 255.0D);
/* 123 */       setColor(new Color(value.getRed(), value.getGreen(), value.getBlue(), this.alpha));
/*     */     } 
/*     */     
/* 126 */     this.rainbow_focused = Render2DEngine.isHovered(mouseX, mouseY, getX(), cy, 40.0D, 10.0D);
/* 127 */     this.copy_focused = Render2DEngine.isHovered(mouseX, mouseY, getX(), (cy + 13.0F), 40.0D, 10.0D);
/* 128 */     this.paste_focused = Render2DEngine.isHovered(mouseX, mouseY, getX(), (cy + 26.0F), 40.0D, 10.0D);
/*     */     
/* 130 */     Render2DEngine.drawRound(stack, getX(), cy, 40.0F, 10.0F, 2.0F, getColorSetting().isRainbow() ? new Color(86, 63, 105, 250) : (this.rainbow_focused ? new Color(66, 48, 80, 250) : new Color(50, 35, 60, 250)));
/* 131 */     Render2DEngine.drawRound(stack, getX(), cy + 13.0F, 40.0F, 10.0F, 2.0F, this.copy_focused ? new Color(66, 48, 80, 250) : new Color(50, 35, 60, 250));
/* 132 */     Render2DEngine.drawRound(stack, getX(), cy + 26.0F, 40.0F, 9.5F, 2.0F, this.paste_focused ? new Color(66, 48, 80, 250) : new Color(50, 35, 60, 250));
/*     */     
/* 134 */     FontRenderers.modules.drawCenteredString(stack, "rainbow", (getX() + 20.0F), (cy + 3.0F), this.rainbow_focused ? -1 : (getColorSetting().isRainbow() ? getColorSetting().getColor() : (new Color(-1241513985, true)).getRGB()));
/* 135 */     FontRenderers.modules.drawCenteredString(stack, "copy", (getX() + 20.0F), (cy + 15.5F), this.copy_focused ? -1 : (new Color(-1241513985, true)).getRGB());
/* 136 */     FontRenderers.modules.drawCenteredString(stack, "paste", (getX() + 20.0F), (cy + 28.5F), this.paste_focused ? -1 : (new Color(-1241513985, true)).getRGB());
/*     */   }
/*     */   
/*     */   private void updatePos() {
/* 140 */     float[] hsb = Color.RGBtoHSB(getColorSetting().getColorObject().getRed(), getColorSetting().getColorObject().getGreen(), getColorSetting().getColorObject().getBlue(), null);
/* 141 */     this.hue = -1.0F + hsb[0];
/* 142 */     this.saturation = hsb[1];
/* 143 */     this.brightness = hsb[2];
/* 144 */     this.alpha = getColorSetting().getAlpha();
/*     */   }
/*     */   
/*     */   private void setColor(Color color) {
/* 148 */     getColorSetting().setColor(color.getRGB());
/* 149 */     this.prevColor = color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 154 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 157 */     double cx = (this.x + 4.0F);
/* 158 */     double cy = (this.y + 21.0F);
/* 159 */     double cw = (this.width - 34.0F);
/* 160 */     double ch = (this.height - 20.0F);
/*     */     
/* 162 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width - 20.0F), (this.y + 5.0F), 14.0D, 6.0D)) {
/* 163 */       this.open = !this.open;
/*     */     }
/* 165 */     if (!this.open) {
/*     */       return;
/*     */     }
/* 168 */     if (Render2DEngine.isHovered(mouseX, mouseY, cx + cw + 17.0D, cy, 8.0D, ch) && button == 0) {
/* 169 */       this.afocused = true;
/*     */     }
/* 171 */     else if (Render2DEngine.isHovered(mouseX, mouseY, cx + cw + 4.0D, cy, 8.0D, ch) && button == 0) {
/* 172 */       this.hfocused = true;
/*     */     }
/* 174 */     else if (Render2DEngine.isHovered(mouseX, mouseY, cx + 40.0D, cy, cw - 40.0D, ch) && button == 0) {
/* 175 */       this.sbfocused = true;
/*     */     } 
/*     */     
/* 178 */     if (this.rainbow_focused) getColorSetting().setRainbow(!getColorSetting().isRainbow()); 
/* 179 */     if (this.copy_focused) ThunderHack.copy_color = getColorSetting().getColorObject(); 
/* 180 */     if (this.paste_focused) {
/* 181 */       setColor((ThunderHack.copy_color == null) ? getColorSetting().getColorObject() : ThunderHack.copy_color);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 186 */     this.hfocused = false;
/* 187 */     this.afocused = false;
/* 188 */     this.sbfocused = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 193 */     this.hfocused = false;
/* 194 */     this.afocused = false;
/* 195 */     this.sbfocused = false;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 199 */     return this.open;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\ColorPickerComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */