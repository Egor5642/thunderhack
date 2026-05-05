/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class SliderComponent
/*     */   extends SettingElement
/*     */ {
/*     */   private final float min;
/*     */   private final float max;
/*     */   public boolean listening;
/*  20 */   public String Stringnumber = "";
/*     */   private float animation;
/*     */   private double stranimation;
/*     */   private boolean dragging;
/*     */   
/*     */   public SliderComponent(Setting setting) {
/*  26 */     super(setting);
/*  27 */     this.min = ((Number)setting.getMin()).floatValue();
/*  28 */     this.max = ((Number)setting.getMax()).floatValue();
/*     */   }
/*     */   
/*     */   public static String removeLastChar(String str) {
/*  32 */     String output = "";
/*  33 */     if (str != null && !str.isEmpty()) {
/*  34 */       output = str.substring(0, str.length() - 1);
/*     */     }
/*  36 */     return output;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_4587 stack, int mouseX, int mouseY, float partialTicks) {
/*  41 */     super.render(stack, mouseX, mouseY, partialTicks);
/*  42 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/*     */     
/*  46 */     FontRenderers.modules.drawString(stack, getSetting().getName(), getX(), (getY() + 5.0F), isHovered() ? -1 : (new Color(-1325400065, true)).getRGB());
/*     */     
/*  48 */     double currentPos = ((((Number)this.setting.getValue()).floatValue() - this.min) / (this.max - this.min));
/*  49 */     this.stranimation += ((((Number)this.setting.getValue()).floatValue() * 100.0F / 100.0F) - this.stranimation) / 2.0D;
/*  50 */     this.animation = Render2DEngine.scrollAnimate(this.animation, (float)currentPos, 0.5F);
/*     */     
/*  52 */     Color color = new Color(-1973791);
/*  53 */     Render2DEngine.drawRound(stack, this.x + 54.0F, this.y + this.height - 8.0F, 90.0F, 1.0F, 0.5F, new Color(-15856114));
/*  54 */     Render2DEngine.drawRound(stack, this.x + 54.0F, this.y + this.height - 8.0F, 90.0F * this.animation, 1.0F, 0.5F, color);
/*  55 */     Render2DEngine.drawRound(stack, this.x + 52.0F + 90.0F * this.animation, this.y + this.height - 9.5F, 4.0F, 4.0F, 1.5F, color);
/*     */     
/*  57 */     if (mouseX > this.x + 154.0F && mouseX < this.x + 176.0F && mouseY > this.y + this.height - 11.0F && mouseY < this.y + this.height - 4.0F) {
/*  58 */       Render2DEngine.drawRound(stack, this.x + 154.0F, this.y + this.height - 11.0F, 22.0F, 7.0F, 0.5F, new Color(82, 57, 100, 178));
/*     */     } else {
/*  60 */       Render2DEngine.drawRound(stack, this.x + 154.0F, this.y + this.height - 11.0F, 22.0F, 7.0F, 0.5F, new Color(50, 35, 60, 178));
/*     */     } 
/*     */     
/*  63 */     if (!this.listening) {
/*  64 */       if (this.setting.getValue() instanceof Float)
/*  65 */         FontRenderers.modules.drawString(stack, String.valueOf(MathUtility.round(((Float)this.setting.getValue()).floatValue(), 2)), (this.x + 156.0F), (this.y + this.height - 9.0F), (new Color(-1157627905, true)).getRGB()); 
/*  66 */       if (this.setting.getValue() instanceof Integer) {
/*  67 */         FontRenderers.modules.drawString(stack, String.valueOf(this.setting.getValue()), (this.x + 156.0F), (this.y + this.height - 9.0F), (new Color(-1157627905, true)).getRGB());
/*     */       }
/*  69 */     } else if (Objects.equals(this.Stringnumber, "")) {
/*  70 */       FontRenderers.modules.drawString(stack, "...", (this.x + 156.0F), (this.y + this.height - 9.0F), (new Color(-1157627905, true)).getRGB());
/*     */     } else {
/*  72 */       FontRenderers.modules.drawString(stack, this.Stringnumber, (this.x + 156.0F), (this.y + this.height - 9.0F), (new Color(-1157627905, true)).getRGB());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     this.animation = MathUtility.clamp(this.animation, 0.0F, 1.0F);
/*     */     
/*  78 */     if (this.dragging) {
/*  79 */       setValue(mouseX, (this.x + 54.0F), 90.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setValue(int mouseX, double x, double width) {
/*  84 */     double diff = (((Number)this.setting.getMax()).floatValue() - ((Number)this.setting.getMin()).floatValue());
/*  85 */     double percentBar = class_3532.method_15350((mouseX - x) / width, 0.0D, 1.0D);
/*  86 */     double value = ((Number)this.setting.getMin()).floatValue() + percentBar * diff;
/*     */     
/*  88 */     if (this.setting.getValue() instanceof Float) {
/*  89 */       this.setting.setValue(Float.valueOf((float)value));
/*  90 */     } else if (this.setting.getValue() instanceof Integer) {
/*  91 */       this.setting.setValue(Integer.valueOf((int)value));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  97 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 100 */     if (mouseX > this.x + 154.0F && mouseX < this.x + 176.0F && mouseY > this.y + this.height - 11.0F && mouseY < this.y + this.height - 4.0F) {
/* 101 */       this.Stringnumber = "";
/* 102 */       this.listening = true;
/*     */     }
/* 104 */     else if (button == 0 && this.hovered) {
/* 105 */       this.dragging = true;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     if (this.listening) {
/* 110 */       ThunderHack.currentKeyListener = ThunderHack.KeyListening.Sliders;
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 115 */     this.dragging = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetAnimation() {
/* 120 */     this.dragging = false;
/* 121 */     this.animation = 0.0F;
/* 122 */     this.stranimation = 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyTyped(String typedChar, int keyCode) {
/* 127 */     if (ThunderHack.currentKeyListener != ThunderHack.KeyListening.Sliders) {
/*     */       return;
/*     */     }
/* 130 */     if (this.listening) {
/* 131 */       switch (keyCode) {
/*     */         case 256:
/* 133 */           this.listening = false;
/* 134 */           this.Stringnumber = "";
/*     */           return;
/*     */         
/*     */         case 257:
/*     */           try {
/* 139 */             searchNumber();
/* 140 */           } catch (Exception e) {
/* 141 */             this.Stringnumber = "";
/* 142 */             this.listening = false;
/*     */           } 
/*     */           return;
/*     */         
/*     */         case 259:
/* 147 */           this.Stringnumber = removeLastChar(this.Stringnumber);
/*     */           return;
/*     */       } 
/*     */       
/* 151 */       this.Stringnumber += this.Stringnumber;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void searchNumber() {
/* 156 */     if (this.setting.getValue() instanceof Float) {
/* 157 */       this.setting.setValue(Float.valueOf(this.Stringnumber));
/* 158 */       this.Stringnumber = "";
/* 159 */       this.listening = false;
/* 160 */     } else if (this.setting.getValue() instanceof Integer) {
/* 161 */       this.setting.setValue(Integer.valueOf(this.Stringnumber));
/* 162 */       this.Stringnumber = "";
/* 163 */       this.listening = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkMouseWheel(float value) {
/* 169 */     super.checkMouseWheel(value);
/* 170 */     if (isHovered()) {
/* 171 */       ThunderGui.scroll_lock = true;
/*     */     } else {
/*     */       return;
/*     */     } 
/* 175 */     if (value < 0.0F) {
/* 176 */       if (this.setting.getValue() instanceof Float) {
/* 177 */         this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() + 0.01F));
/* 178 */       } else if (this.setting.getValue() instanceof Integer) {
/* 179 */         this.setting.setValue(Integer.valueOf(((Integer)this.setting.getValue()).intValue() + 1));
/*     */       } 
/* 181 */     } else if (value > 0.0F) {
/* 182 */       if (this.setting.getValue() instanceof Float) {
/* 183 */         this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() - 0.01F));
/* 184 */       } else if (this.setting.getValue() instanceof Integer) {
/* 185 */         this.setting.setValue(Integer.valueOf(((Integer)this.setting.getValue()).intValue() - 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\SliderComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */