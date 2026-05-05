/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1074;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ThunderHackGui;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.setting.impl.Bind;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ 
/*     */ public class ModulePlate
/*     */ {
/*  21 */   float scroll_animation = 0.0F;
/*     */   
/*     */   private final Module module;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private float scrollPosY;
/*     */   private float prevPosY;
/*     */   private int progress;
/*     */   private int fade;
/*     */   private final int index;
/*     */   private boolean first_open = true;
/*     */   private boolean listening_bind = false;
/*     */   private boolean holdbind = false;
/*     */   
/*     */   public ModulePlate(Module module, int posX, int posY, int index) {
/*  36 */     this.module = module;
/*  37 */     this.posX = posX;
/*  38 */     this.posY = posY;
/*  39 */     this.fade = 0;
/*  40 */     this.index = index * 5;
/*  41 */     this.scrollPosY = posY;
/*  42 */     this.scroll_animation = 0.0F;
/*     */   }
/*     */   
/*     */   public void render(class_4587 stack, int MouseX, int MouseY) {
/*  46 */     if (this.scrollPosY != this.posY) {
/*  47 */       this.scroll_animation = AnimationUtility.fast(this.scroll_animation, 1.0F, 15.0F);
/*  48 */       this.posY = (int)Render2DEngine.interpolate(this.prevPosY, this.scrollPosY, this.scroll_animation);
/*     */     } 
/*     */     
/*  51 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/*     */     
/*  55 */     Render2DEngine.addWindow(stack, new Render2DEngine.Rectangle((this.posX + 1), (this.posY + 1), (this.posX + 90), (this.posY + 30)));
/*     */     
/*  57 */     if (this.module.isOn()) {
/*  58 */       Render2DEngine.drawGradientRound(stack, (this.posX + 1), this.posY, 89.0F, 30.0F, 4.0F, 
/*  59 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor1.getValue()).getColorObject(), getFadeFactor()), 
/*  60 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor1.getValue()).getColorObject(), getFadeFactor()), 
/*  61 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor2.getValue()).getColorObject(), getFadeFactor()), 
/*  62 */           Render2DEngine.applyOpacity(((ColorSetting)ThunderHackGui.onColor2.getValue()).getColorObject(), getFadeFactor()));
/*     */     } else {
/*  64 */       Render2DEngine.drawRound(stack, (this.posX + 1), this.posY, 89.0F, 30.0F, 4.0F, Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), getFadeFactor()));
/*     */     } 
/*     */     
/*  67 */     if (this.first_open) {
/*  68 */       Render2DEngine.drawBlurredShadow(stack, (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/*  69 */       this.first_open = false;
/*     */     } 
/*     */     
/*  72 */     if (isHovered(MouseX, MouseY)) {
/*  73 */       Render2DEngine.drawBlurredShadow(stack, (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/*     */     }
/*     */     
/*  76 */     if (ThunderGui.selected_plate != this) {
/*  77 */       FontRenderers.icons.drawString(stack, "H", (int)(this.posX + 80.0F), (int)(this.posY + 22.0F), Render2DEngine.applyOpacity((new Color(-1250068, true)).getRGB(), getFadeFactor()));
/*     */     } else {
/*     */       
/*  80 */       stack.method_22903();
/*  81 */       stack.method_46416(this.posX + 91.0F, this.posY + 15.0F, 0.0F);
/*  82 */       stack.method_22907(class_7833.field_40718.rotationDegrees((Module.mc.field_1724.field_6012 * 4)));
/*  83 */       stack.method_46416(-(this.posX + 91.0F), -(this.posY + 15.0F), 0.0F);
/*  84 */       FontRenderers.big_icons.drawString(stack, "H", (this.posX + 78.0F), (this.posY + 5.0F), Render2DEngine.applyOpacity((new Color(-10197916, true)).getRGB(), getFadeFactor()));
/*  85 */       stack.method_46416(this.posX + 91.0F, this.posY + 15.0F, 0.0F);
/*  86 */       stack.method_22907(class_7833.field_40718.rotationDegrees((-Module.mc.field_1724.field_6012 * 4)));
/*  87 */       stack.method_46416(-(this.posX + 91.0F), -(this.posY + 15.0F), 0.0F);
/*  88 */       stack.method_22909();
/*     */     } 
/*     */     
/*  91 */     if (!this.listening_bind) {
/*  92 */       FontRenderers.sf_medium.drawString(stack, this.module.getName(), (this.posX + 5), (this.posY + 5), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*     */     }
/*     */     
/*  95 */     if (this.listening_bind) {
/*  96 */       FontRenderers.modules.drawString(stack, "PressKey", ((this.posX + 85) - FontRenderers.modules.getStringWidth("PressKey")), (this.posY + 5), Render2DEngine.applyOpacity(new Color(11579568), getFadeFactor()).getRGB());
/*  97 */     } else if (!Objects.equals(this.module.getBind().getBind(), "None")) {
/*  98 */       String sbind = this.module.getBind().getBind();
/*  99 */       if (sbind.equals("LEFT_CONTROL")) {
/* 100 */         sbind = "LCtrl";
/*     */       }
/* 102 */       if (sbind.equals("RIGHT_CONTROL")) {
/* 103 */         sbind = "RCtrl";
/*     */       }
/* 105 */       if (sbind.equals("LEFT_SHIFT")) {
/* 106 */         sbind = "LShift";
/*     */       }
/* 108 */       if (sbind.equals("RIGHT_SHIFT")) {
/* 109 */         sbind = "RShift";
/*     */       }
/* 111 */       if (sbind.equals("LEFT_ALT")) {
/* 112 */         sbind = "LAlt";
/*     */       }
/* 114 */       if (sbind.equals("RIGHT_ALT")) {
/* 115 */         sbind = "RAlt";
/*     */       }
/*     */       
/* 118 */       FontRenderers.modules.drawString(stack, sbind, ((this.posX + 86) - FontRenderers.modules.getStringWidth(sbind)), (this.posY + 6), Render2DEngine.applyOpacity(new Color(11579568), getFadeFactor()).getRGB());
/*     */     } 
/*     */     
/* 121 */     if (!this.listening_bind && this.module.getDescription() != null) {
/* 122 */       int step = 0;
/* 123 */       StringBuilder firstString = new StringBuilder();
/* 124 */       for (String word : class_1074.method_4662(this.module.getDescription(), new Object[0]).split(" ")) {
/* 125 */         firstString.append(word + " ");
/* 126 */         String[] splitString2 = firstString.toString().split("\n");
/* 127 */         if (FontRenderers.sf_medium_mini.getStringWidth(splitString2[step]) > 70.0F) {
/* 128 */           firstString.append("\n");
/* 129 */           step++;
/*     */         } 
/*     */       } 
/* 132 */       FontRenderers.sf_medium_mini.drawString(stack, firstString.toString(), (this.posX + 5), (this.posY + 14), Render2DEngine.applyOpacity((new Color(-4342339, true)).getRGB(), getFadeFactor()));
/*     */     } 
/*     */     
/* 135 */     if (this.listening_bind) {
/* 136 */       Render2DEngine.drawRound(stack, (this.posX + 5), (this.posY + 5), 40.0F, 20.0F, 3.0F, Color.BLACK);
/*     */       
/* 138 */       if (!this.holdbind) {
/* 139 */         Render2DEngine.drawRound(stack, (this.posX + 6), (this.posY + 6), 38.0F, 8.0F, 2.0F, Render2DEngine.injectAlpha(((ColorSetting)ThunderHackGui.onColor1.getValue()).getColorObject(), 170));
/* 140 */         FontRenderers.settings.drawCenteredString(stack, "Toggle", (this.posX + 25), (this.posY + 7), -1);
/* 141 */         FontRenderers.settings.drawCenteredString(stack, "Hold", (this.posX + 25), (this.posY + 17), (new Color(-1459617793, true)).getRGB());
/*     */       } else {
/* 143 */         Render2DEngine.drawRound(stack, (this.posX + 6), (this.posY + 16), 38.0F, 8.0F, 2.0F, Render2DEngine.injectAlpha(((ColorSetting)ThunderHackGui.onColor1.getValue()).getColorObject(), 170));
/* 144 */         FontRenderers.settings.drawCenteredString(stack, "Hold", (this.posX + 25), (this.posY + 17), -1);
/* 145 */         FontRenderers.settings.drawCenteredString(stack, "Toggle", (this.posX + 25), (this.posY + 7), (new Color(-1459617793, true)).getRGB());
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     Render2DEngine.popWindow();
/*     */   }
/*     */   
/*     */   private float getFadeFactor() {
/* 153 */     return this.fade / (5.0F + this.index);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTick() {
/* 158 */     if (this.progress > 4) {
/* 159 */       this.progress = 0;
/*     */     }
/* 161 */     this.progress++;
/*     */     
/* 163 */     if (this.fade < 10 + this.index) {
/* 164 */       this.fade++;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isHovered(int mouseX, int mouseY) {
/* 169 */     return (mouseX > this.posX && mouseX < this.posX + 90 && mouseY > this.posY && mouseY < this.posY + 30);
/*     */   }
/*     */   
/*     */   public void movePosition(float deltaX, float deltaY) {
/* 173 */     this.posY = (int)(this.posY + deltaY);
/* 174 */     this.posX = (int)(this.posX + deltaX);
/* 175 */     this.scrollPosY = this.posY;
/*     */   }
/*     */   
/*     */   public void scrollElement(float deltaY) {
/* 179 */     this.scroll_animation = 0.0F;
/* 180 */     this.prevPosY = this.posY;
/* 181 */     this.scrollPosY += deltaY;
/*     */   }
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
/* 185 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 188 */     if (this.listening_bind) {
/* 189 */       if (mouseX > this.posX + 6 && mouseX < this.posX + 44 && mouseY > this.posY + 6 && mouseY < this.posY + 14) {
/* 190 */         this.holdbind = false;
/* 191 */         this.module.getBind().setHold(false);
/*     */         return;
/*     */       } 
/* 194 */       if (mouseX > this.posX + 6 && mouseX < this.posX + 44 && mouseY > this.posY + 16 && mouseY < this.posY + 24) {
/* 195 */         this.holdbind = true;
/* 196 */         this.module.getBind().setHold(true);
/*     */         return;
/*     */       } 
/* 199 */       this.module.setBind(clickedButton, true, this.holdbind);
/* 200 */       Command.sendMessage(this.module.getName() + " бинд изменен на " + this.module.getName());
/* 201 */       this.listening_bind = false;
/*     */     } 
/*     */     
/* 204 */     if (mouseX > this.posX && mouseX < this.posX + 90 && mouseY > this.posY && mouseY < this.posY + 30) {
/* 205 */       switch (clickedButton) {
/*     */         case 0:
/* 207 */           this.module.toggle();
/*     */           break;
/*     */         case 1:
/* 210 */           ThunderGui.selected_plate = this;
/*     */           break;
/*     */         case 2:
/* 213 */           this.listening_bind = !this.listening_bind;
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void keyTyped(String typedChar, int keyCode) {
/* 220 */     if (this.listening_bind) {
/* 221 */       Bind bind = new Bind(keyCode, false, this.holdbind);
/* 222 */       if (bind.getBind().equalsIgnoreCase("Escape")) {
/*     */         return;
/*     */       }
/* 225 */       if (bind.getBind().equalsIgnoreCase("Delete")) {
/* 226 */         bind = new Bind(-1, false, this.holdbind);
/*     */       }
/* 228 */       this.module.setBind(bind);
/* 229 */       this.listening_bind = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getPosX() {
/* 234 */     return this.posX;
/*     */   }
/*     */   
/*     */   public double getPosY() {
/* 238 */     return this.posY;
/*     */   }
/*     */   
/*     */   public Module getModule() {
/* 242 */     return this.module;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\ModulePlate.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */