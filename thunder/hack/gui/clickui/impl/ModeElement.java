/*     */ package thunder.hack.gui.clickui.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.AbstractElement;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class ModeElement
/*     */   extends AbstractElement
/*     */ {
/*     */   public Setting setting2;
/*     */   private boolean open;
/*     */   private double wheight;
/*     */   private String prevMode;
/*     */   private float animation;
/*     */   private float animation2;
/*     */   
/*     */   public ModeElement(Setting setting) {
/*  28 */     super(setting);
/*  29 */     this.setting2 = setting;
/*  30 */     this.prevMode = setting.currentEnumName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*  35 */     this.animation = AnimationUtility.fast(this.animation, this.open ? 0.0F : 1.0F, 15.0F);
/*  36 */     this.animation2 = AnimationUtility.fast(this.animation2, 1.0F, 10.0F);
/*     */     
/*  38 */     float tx = this.x + this.width - 11.0F;
/*  39 */     float ty = this.y + 7.5F;
/*     */     
/*  41 */     class_4587 matrixStack = context.method_51448();
/*     */     
/*  43 */     float thetaRotation = -180.0F * this.animation;
/*  44 */     matrixStack.method_22903();
/*     */     
/*  46 */     matrixStack.method_46416(tx, ty, 0.0F);
/*  47 */     matrixStack.method_22907(class_7833.field_40718.rotationDegrees(thetaRotation));
/*  48 */     matrixStack.method_46416(-tx, -ty, 0.0F);
/*     */     
/*  50 */     matrixStack.method_46416(this.x + this.width - 14.0F, this.y + 4.5F, 0.0F);
/*  51 */     context.method_25290(TextureStorage.guiArrow, 0, 0, 0.0F, 0.0F, 6, 6, 6, 6);
/*  52 */     matrixStack.method_46416(-(this.x + this.width - 14.0F), -this.y - 4.5F, 0.0F);
/*     */     
/*  54 */     matrixStack.method_22909();
/*     */     
/*  56 */     if (this.setting.group != null) {
/*  57 */       Render2DEngine.drawRect(context.method_51448(), this.x + 4.0F, this.y, 1.0F, 17.0F, HudEditor.getColor(1));
/*     */     }
/*     */     
/*  60 */     FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting2.getName(), (((this.setting.group != null) ? 2.0F : 0.0F) + this.x + 6.0F), this.y + this.wheight / 2.0D - 3.0D + 3.0D, (new Color(-1)).getRGB());
/*     */     
/*  62 */     if (this.animation2 < 0.99D && !Objects.equals(this.setting2.currentEnumName(), this.prevMode)) {
/*  63 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.prevMode, (int)(this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.prevMode)), 3.0D + this.y + this.wheight / 2.0D - 3.0D - (this.animation2 * 5.0F), Render2DEngine.applyOpacity(new Color(-1), this.animation2));
/*  64 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting2.currentEnumName(), (this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting2.currentEnumName())), 3.0D + this.y + this.wheight / 2.0D - 3.0D - (this.animation2 * 5.0F) + 5.0D, Render2DEngine.applyOpacity(new Color(-1), 1.0F - this.animation2));
/*     */     } else {
/*  66 */       FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting2.currentEnumName(), (this.x + this.width - 18.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting.currentEnumName())), 3.0D + this.y + this.wheight / 2.0D - 3.0D, (new Color(-1)).getRGB());
/*     */     } 
/*  68 */     if (this.open) {
/*  69 */       Color color = HudEditor.getColor(0);
/*  70 */       double offsetY = 0.0D;
/*  71 */       for (int i = 0; i <= (this.setting2.getModes()).length - 1; i++) {
/*  72 */         FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting2.getModes()[i], (this.x + this.width / 2.0F - FontRenderers.sf_medium_mini.getStringWidth(this.setting2.getModes()[i]) / 2.0F), this.y + this.wheight + 2.0D + offsetY, this.setting2.currentEnumName().equalsIgnoreCase(this.setting2.getModes()[i]) ? color.getRGB() : (new Color(-1)).getRGB());
/*  73 */         offsetY += 12.0D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  80 */     if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.wheight)) {
/*  81 */       if (button == 0) {
/*  82 */         this.prevMode = this.setting2.currentEnumName();
/*  83 */         this.animation2 = 0.0F;
/*  84 */         this.setting2.increaseEnum();
/*  85 */         Managers.SOUND.playBoolean();
/*     */       } else {
/*  87 */         this.open = !this.open;
/*  88 */         if (this.open) {
/*  89 */           Managers.SOUND.playSwipeIn();
/*     */         } else {
/*  91 */           Managers.SOUND.playSwipeOut();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  96 */     if (this.open) {
/*  97 */       double offsetY = 0.0D;
/*  98 */       for (int i = 0; i <= (this.setting2.getModes()).length - 1; i++) {
/*  99 */         if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y + this.wheight + offsetY, this.width, 12.0D) && button == 0) {
/* 100 */           this.prevMode = this.setting2.currentEnumName();
/* 101 */           this.animation2 = 0.0F;
/* 102 */           this.setting2.setEnumByNumber(i);
/* 103 */           Managers.SOUND.playBoolean();
/*     */         } 
/* 105 */         offsetY += 12.0D;
/*     */       } 
/*     */     } 
/* 108 */     super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */   
/*     */   public void setWHeight(double height) {
/* 112 */     this.wheight = height;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 116 */     return this.open;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\ModeElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */