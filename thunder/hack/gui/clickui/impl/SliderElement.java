/*     */ package thunder.hack.gui.clickui.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3544;
/*     */ import net.minecraft.class_4587;
/*     */ import org.lwjgl.glfw.GLFW;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.AbstractElement;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class SliderElement
/*     */   extends AbstractElement
/*     */ {
/*     */   private final float min;
/*     */   private final float max;
/*     */   private float animation;
/*  27 */   public String Stringnumber = ""; private float prevValue; private boolean dragging; private boolean listening;
/*     */   
/*     */   public SliderElement(Setting setting) {
/*  30 */     super(setting);
/*  31 */     this.min = ((Number)setting.getMin()).floatValue();
/*  32 */     this.max = ((Number)setting.getMax()).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*  37 */     super.render(context, mouseX, mouseY, delta);
/*  38 */     this.animation = Render2DEngine.scrollAnimate(this.animation, (((Number)this.setting.getValue()).floatValue() - this.min) / (this.max - this.min), 0.4F);
/*     */     
/*  40 */     class_4587 matrixStack = context.method_51448();
/*     */     
/*  42 */     if (this.setting.group != null) {
/*  43 */       Render2DEngine.drawRect(context.method_51448(), this.x + 4.0F, this.y, 1.0F, 18.0F, HudEditor.getColor(1));
/*     */     }
/*  45 */     if (!this.dragging) {
/*  46 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), (((this.setting.group != null) ? 2.0F : 0.0F) + this.x + 6.0F), (this.y + 4.0F), (new Color(-1)).getRGB());
/*  47 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.listening ? (Objects.equals(this.Stringnumber, "") ? "..." : this.Stringnumber) : String.valueOf(this.setting.getValue()), 
/*  48 */           (int)(this.x + this.width - 6.0F - FontRenderers.sf_medium_mini.getStringWidth(this.listening ? (Objects.equals(this.Stringnumber, "") ? "..." : this.Stringnumber) : String.valueOf(this.setting.getValue()))), (this.y + 5.0F), (new Color(-1)).getRGB());
/*     */     } else {
/*  50 */       if (this.animation > 0.2F)
/*  51 */         FontRenderers.sf_medium_mini.drawString(matrixStack, String.valueOf(this.setting.getMin()), (this.x + 6.0F), (this.y + 4.0F), (new Color(-1)).getRGB()); 
/*  52 */       if (this.animation < 0.8F) {
/*  53 */         FontRenderers.sf_medium_mini.drawString(matrixStack, String.valueOf(this.setting.getMax()), (this.x + this.width - FontRenderers.sf_medium_mini.getStringWidth(String.valueOf(this.setting.getMax())) - 6.0F), (this.y + 4.0F), (new Color(-1)).getRGB());
/*     */       }
/*  55 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.listening ? (Objects.equals(this.Stringnumber, "") ? "..." : this.Stringnumber) : String.valueOf(this.setting.getValue()), (this.animation > 0.2F) ? ((this.animation < 0.8F) ? (this.x + 6.0F + (this.width - 14.0F) * this.animation - FontRenderers.sf_medium_mini.getStringWidth(String.valueOf(this.setting.getValue())) / 2.0F) : (this.x + this.width - FontRenderers.sf_medium_mini.getStringWidth(String.valueOf(this.setting.getMax())) - 6.0F)) : (this.x + 6.0F), (this.y + 4.0F), (new Color(-1)).getRGB());
/*     */     } 
/*     */     
/*  58 */     Render2DEngine.drawRect(matrixStack, this.x + 6.0F, this.y + this.height - 6.0F, this.width - 12.0F, 2.0F, new Color(687865855, true));
/*  59 */     Render2DEngine.draw2DGradientRect(matrixStack, this.x + 6.0F, this.y + this.height - 6.0F, this.x + 6.0F + (this.width - 12.0F) * this.animation, this.y + this.height - 4.0F, HudEditor.getColor(180), HudEditor.getColor(180), HudEditor.getColor(0), HudEditor.getColor(0));
/*  60 */     Render2DEngine.drawRect(matrixStack, this.x + 6.0F + (this.width - 14.0F) * this.animation, this.y + this.height - 7.5F, 2.0F, 5.0F, new Color(-1973791));
/*     */     
/*  62 */     this.animation = MathUtility.clamp(this.animation, 0.0F, 1.0F);
/*     */     
/*  64 */     if (this.dragging) {
/*  65 */       setValue(mouseX, (this.x + 7.0F), (this.width - 14.0F));
/*     */     }
/*  67 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + 6.0F), (this.y + this.height - 7.0F), (this.width - 12.0F), 3.0D)) {
/*  68 */       if (GLFW.glfwGetPlatform() != 393219) {
/*  69 */         GLFW.glfwSetCursor(IManager.mc.method_22683().method_4490(), 
/*  70 */             GLFW.glfwCreateStandardCursor(221189));
/*     */       }
/*  72 */       ClickGUI.anyHovered = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setValue(int mouseX, double x, double width) {
/*  77 */     float value = Render2DEngine.interpolateFloat(((Number)this.setting.getMin()).floatValue(), ((Number)this.setting.getMax()).floatValue(), class_3532.method_15350((mouseX - x) / width, 0.0D, 1.0D));
/*  78 */     if (this.setting.getValue() instanceof Float) {
/*  79 */       this.setting.setValue(Float.valueOf(MathUtility.round2(value)));
/*  80 */     } else if (this.setting.getValue() instanceof Integer) {
/*  81 */       this.setting.setValue(Integer.valueOf((int)value));
/*     */     } 
/*     */     
/*  84 */     if (value != this.prevValue) {
/*  85 */       Managers.SOUND.playSlider();
/*     */     }
/*  87 */     this.prevValue = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  92 */     if (button == 0 && this.hovered) {
/*  93 */       this.dragging = true;
/*  94 */     } else if (this.hovered) {
/*  95 */       this.Stringnumber = "";
/*  96 */       this.listening = true;
/*     */     } 
/*  98 */     if (this.listening)
/*  99 */       ThunderHack.currentKeyListener = ThunderHack.KeyListening.Sliders; 
/* 100 */     super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 105 */     this.dragging = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyTyped(int keyCode) {
/* 110 */     if (ThunderHack.currentKeyListener != ThunderHack.KeyListening.Sliders) {
/*     */       return;
/*     */     }
/* 113 */     if (this.listening) {
/* 114 */       switch (keyCode) {
/*     */         case 256:
/* 116 */           this.listening = false;
/* 117 */           this.Stringnumber = "";
/*     */           return;
/*     */         
/*     */         case 257:
/*     */           try {
/* 122 */             searchNumber();
/* 123 */           } catch (Exception e) {
/* 124 */             this.Stringnumber = "";
/* 125 */             this.listening = false;
/*     */           } 
/*     */           return;
/*     */         
/*     */         case 259:
/* 130 */           this.Stringnumber = removeLastChar(this.Stringnumber);
/*     */           return;
/*     */ 
/*     */         
/*     */         case 261:
/* 135 */           this.Stringnumber = "";
/* 136 */           this.listening = false;
/*     */           return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 145 */     if (class_3544.method_57175(key)) {
/* 146 */       String k = (key == '-') ? "-" : ".";
/*     */       try {
/* 148 */         k = String.valueOf(Integer.parseInt(String.valueOf(key)));
/* 149 */       } catch (Exception exception) {}
/*     */       
/* 151 */       this.Stringnumber += this.Stringnumber;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String removeLastChar(String str) {
/* 156 */     String output = "";
/* 157 */     if (str != null && !str.isEmpty()) {
/* 158 */       output = str.substring(0, str.length() - 1);
/*     */     }
/* 160 */     return output;
/*     */   }
/*     */   
/*     */   private void searchNumber() {
/* 164 */     if (this.setting.getValue() instanceof Float) {
/* 165 */       this.setting.setValue(Float.valueOf(this.Stringnumber));
/* 166 */       this.Stringnumber = "";
/* 167 */       this.listening = false;
/* 168 */     } else if (this.setting.getValue() instanceof Integer) {
/* 169 */       this.setting.setValue(Integer.valueOf(this.Stringnumber));
/* 170 */       this.Stringnumber = "";
/* 171 */       this.listening = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\SliderElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */