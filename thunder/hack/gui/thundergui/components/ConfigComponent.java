/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.modules.client.ThunderHackGui;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class ConfigComponent {
/*  14 */   float scroll_animation = 0.0F;
/*     */   private final String name;
/*     */   private final String date;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int progress;
/*     */   private int fade;
/*     */   private final int index;
/*     */   private boolean first_open = true;
/*     */   private float scrollPosY;
/*     */   private float prevPosY;
/*     */   
/*     */   public ConfigComponent(String name, String date, int posX, int posY, int index) {
/*  27 */     this.name = name;
/*  28 */     this.date = date;
/*  29 */     this.posX = posX;
/*  30 */     this.posY = posY;
/*  31 */     this.fade = 0;
/*  32 */     this.index = index * 5;
/*  33 */     this.scrollPosY = posY;
/*  34 */     this.scroll_animation = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int MouseX, int MouseY) {
/*  39 */     if (this.scrollPosY != this.posY) {
/*  40 */       this.scroll_animation = AnimationUtility.fast(this.scroll_animation, 1.0F, 15.0F);
/*  41 */       this.posY = (int)Render2DEngine.interpolate(this.prevPosY, this.scrollPosY, this.scroll_animation);
/*     */     } 
/*     */     
/*  44 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/*     */     
/*  48 */     if (Managers.CONFIG.currentConfig.getName().equals(this.name + ".th")) {
/*  49 */       Render2DEngine.drawGradientRound(context.method_51448(), (this.posX + 5), this.posY, 285.0F, 30.0F, 4.0F, 
/*  50 */           Render2DEngine.applyOpacity(new Color(55, 44, 66, 255), getFadeFactor()), 
/*  51 */           Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), getFadeFactor()), 
/*  52 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor1.getValue()).getColorObject(), getFadeFactor()), 
/*  53 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor2.getValue()).getColorObject(), getFadeFactor()));
/*     */     } else {
/*  55 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 5), this.posY, 285.0F, 30.0F, 4.0F, Render2DEngine.applyOpacity(new Color(44, 35, 52, 255), getFadeFactor()));
/*     */     } 
/*  57 */     if (this.first_open) {
/*  58 */       Render2DEngine.addWindow(context.method_51448(), (this.posX + 5), this.posY, (this.posX + 5 + 285), (this.posY + 30), 1.0D);
/*  59 */       Render2DEngine.drawBlurredShadow(context.method_51448(), (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/*  60 */       Render2DEngine.popWindow();
/*  61 */       this.first_open = false;
/*     */     } 
/*     */     
/*  64 */     if (isHovered(MouseX, MouseY)) {
/*  65 */       Render2DEngine.addWindow(context.method_51448(), (this.posX + 5), this.posY, (this.posX + 5 + 285), (this.posY + 30), 1.0D);
/*  66 */       Render2DEngine.drawBlurredShadow(context.method_51448(), (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/*  67 */       Render2DEngine.popWindow();
/*     */     } 
/*     */     
/*  70 */     Render2DEngine.drawRound(context.method_51448(), (this.posX + 250), (this.posY + 8), 30.0F, 14.0F, 2.0F, Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), getFadeFactor()));
/*     */     
/*  72 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 252), (this.posY + 10), 10.0D, 10.0D)) {
/*  73 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 252), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(21, 58, 0, 255), getFadeFactor()));
/*     */     } else {
/*  75 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 252), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(32, 89, 0, 255), getFadeFactor()));
/*     */     } 
/*  77 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 268), (this.posY + 10), 10.0D, 10.0D)) {
/*  78 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 268), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(65, 1, 13, 255), getFadeFactor()));
/*     */     } else {
/*  80 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 268), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(94, 1, 18, 255), getFadeFactor()));
/*     */     } 
/*  82 */     FontRenderers.icons.drawString(context.method_51448(), "x", (this.posX + 252), (this.posY + 13), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*  83 */     FontRenderers.icons.drawString(context.method_51448(), "w", (this.posX + 268), (this.posY + 13), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*     */ 
/*     */     
/*  86 */     FontRenderers.mid_icons.drawString(context.method_51448(), "u", (this.posX + 7), (this.posY + 5), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*  87 */     FontRenderers.modules.drawString(context.method_51448(), this.name, (this.posX + 37), (this.posY + 6), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*  88 */     FontRenderers.settings.drawString(context.method_51448(), "updated on: " + this.date, (this.posX + 37), (this.posY + 17), Render2DEngine.applyOpacity((new Color(-4342339, true)).getRGB(), getFadeFactor()));
/*     */   }
/*     */   
/*     */   private float getFadeFactor() {
/*  92 */     return this.fade / (5.0F + this.index);
/*     */   }
/*     */   
/*     */   public void onTick() {
/*  96 */     if (this.progress > 4) this.progress = 0; 
/*  97 */     this.progress++;
/*     */     
/*  99 */     if (this.fade < 10 + this.index) this.fade++; 
/*     */   }
/*     */   
/*     */   private boolean isHovered(int mouseX, int mouseY) {
/* 103 */     return (mouseX > this.posX && mouseX < this.posX + 295 && mouseY > this.posY && mouseY < this.posY + 30);
/*     */   }
/*     */   
/*     */   public void movePosition(float deltaX, float deltaY) {
/* 107 */     this.posY = (int)(this.posY + deltaY);
/* 108 */     this.posX = (int)(this.posX + deltaX);
/* 109 */     this.scrollPosY = this.posY;
/*     */   }
/*     */   
/*     */   public void mouseClicked(int MouseX, int MouseY, int clickedButton) {
/* 113 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 116 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 252), (this.posY + 10), 10.0D, 10.0D)) {
/* 117 */       Managers.CONFIG.load(this.name);
/*     */     }
/* 119 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 268), (this.posY + 10), 10.0D, 10.0D)) {
/* 120 */       Managers.CONFIG.delete(this.name);
/* 121 */       ThunderGui.getInstance().loadConfigs();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getPosX() {
/* 126 */     return this.posX;
/*     */   }
/*     */   
/*     */   public double getPosY() {
/* 130 */     return this.posY;
/*     */   }
/*     */   
/*     */   public void scrollElement(float deltaY) {
/* 134 */     this.scroll_animation = 0.0F;
/* 135 */     this.prevPosY = this.posY;
/* 136 */     this.scrollPosY += deltaY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\ConfigComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */