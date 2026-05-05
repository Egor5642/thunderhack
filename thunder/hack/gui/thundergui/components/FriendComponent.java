/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_640;
/*     */ import thunder.hack.core.Core;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.thundergui.ThunderGui;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class FriendComponent {
/*  25 */   float scroll_animation = 0.0F;
/*  26 */   private class_2960 head = null;
/*     */   private final String name;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int progress;
/*     */   private int fade;
/*     */   private final int index;
/*     */   private boolean first_open = true;
/*     */   private float scrollPosY;
/*     */   private float prevPosY;
/*     */   
/*     */   public FriendComponent(String name, int posX, int posY, int index) {
/*  38 */     this.name = name;
/*  39 */     this.posX = posX;
/*  40 */     this.posY = posY;
/*  41 */     this.fade = 0;
/*  42 */     this.index = index * 5;
/*  43 */     loadHead(name);
/*  44 */     this.scrollPosY = posY;
/*  45 */     this.scroll_animation = 0.0F;
/*     */   }
/*     */   
/*     */   public void loadHead(String name) {
/*  49 */     if (Core.HEADS.containsKey(name)) this.head = (class_2960)Core.HEADS.get(name); 
/*  50 */     class_156.method_18349().execute(() -> {
/*     */           try {
/*     */             class_1043 nIBT = getHeadFromURL("https://minotar.net/helm/" + name + "/22.png");
/*     */             this.head = class_310.method_1551().method_1531().method_4617("th-heads-" + name, nIBT);
/*     */             Core.HEADS.put(name, this.head);
/*  55 */           } catch (Exception e) {
/*     */             this.head = null;
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   public static class_1043 getHeadFromURL(String HeadStringURL) {
/*     */     try {
/*  63 */       URL capeURL = new URL(HeadStringURL);
/*  64 */       return getHeadFromStream(capeURL.openStream());
/*  65 */     } catch (IOException e) {
/*  66 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class_1043 getHeadFromStream(InputStream image) {
/*  71 */     class_1011 Head = null;
/*     */     try {
/*  73 */       Head = class_1011.method_4309(image);
/*  74 */     } catch (IOException e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*  77 */     if (Head != null) {
/*  78 */       class_1043 nIBT = new class_1043(parseHead(Head));
/*  79 */       return nIBT;
/*     */     } 
/*  81 */     return null;
/*     */   }
/*     */   
/*     */   public static class_1011 parseHead(class_1011 image) {
/*  85 */     int imageWidth = 22;
/*  86 */     int imageHeight = 22;
/*  87 */     int imageSrcWidth = image.method_4307();
/*  88 */     int srcHeight = image.method_4323();
/*     */     
/*  90 */     for (int imageSrcHeight = image.method_4323(); imageWidth < imageSrcWidth || imageHeight < imageSrcHeight; imageHeight *= 2) {
/*  91 */       imageWidth *= 2;
/*     */     }
/*     */     
/*  94 */     class_1011 imgNew = new class_1011(imageWidth, imageHeight, true);
/*  95 */     for (int x = 0; x < imageSrcWidth; x++) {
/*  96 */       for (int y = 0; y < srcHeight; y++) {
/*  97 */         imgNew.method_4305(x, y, image.method_4315(x, y));
/*     */       }
/*     */     } 
/* 100 */     image.close();
/* 101 */     return imgNew;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_332 context, int MouseX, int MouseY) {
/* 106 */     if (this.scrollPosY != this.posY) {
/* 107 */       this.scroll_animation = AnimationUtility.fast(this.scroll_animation, 1.0F, 15.0F);
/* 108 */       this.posY = (int)Render2DEngine.interpolate(this.prevPosY, this.scrollPosY, this.scroll_animation);
/*     */     } 
/* 110 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 113 */     Render2DEngine.drawRound(context.method_51448(), (this.posX + 5), this.posY, 285.0F, 30.0F, 4.0F, Render2DEngine.applyOpacity(new Color(44, 35, 52, 255), getFadeFactor()));
/*     */     
/* 115 */     if (this.first_open) {
/* 116 */       Render2DEngine.addWindow(context.method_51448(), (this.posX + 5), this.posY, (this.posX + 5 + 285), (this.posY + 30), 1.0D);
/* 117 */       Render2DEngine.drawBlurredShadow(context.method_51448(), (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/* 118 */       Render2DEngine.popWindow();
/* 119 */       this.first_open = false;
/*     */     } 
/*     */     
/* 122 */     if (isHovered(MouseX, MouseY)) {
/* 123 */       Render2DEngine.addWindow(context.method_51448(), (this.posX + 5), this.posY, (this.posX + 5 + 285), (this.posY + 30), 1.0D);
/* 124 */       Render2DEngine.drawBlurredShadow(context.method_51448(), (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, Render2DEngine.applyOpacity(new Color(-1017816450, true), getFadeFactor()));
/* 125 */       Render2DEngine.popWindow();
/*     */     } 
/*     */     
/* 128 */     Render2DEngine.drawRound(context.method_51448(), (this.posX + 266), (this.posY + 8), 14.0F, 14.0F, 2.0F, Render2DEngine.applyOpacity(new Color(25, 20, 30, 255), getFadeFactor()));
/*     */     
/* 130 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 268), (this.posY + 10), 10.0D, 10.0D)) {
/* 131 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 268), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(65, 1, 13, 255), getFadeFactor()));
/*     */     } else {
/* 133 */       Render2DEngine.drawRound(context.method_51448(), (this.posX + 268), (this.posY + 10), 10.0F, 10.0F, 2.0F, Render2DEngine.applyOpacity(new Color(94, 1, 18, 255), getFadeFactor()));
/*     */     } 
/* 135 */     FontRenderers.icons.drawString(context.method_51448(), "w", (this.posX + 268), (this.posY + 13), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*     */ 
/*     */     
/* 138 */     context.method_25290((class_2960)Objects.requireNonNullElse(this.head, TextureStorage.crackedSkin), this.posX + 10, this.posY + 3, 0.0F, 0.0F, 22, 22, 22, 22);
/*     */     
/* 140 */     FontRenderers.modules.drawString(context.method_51448(), this.name, (this.posX + 37), (this.posY + 6), Render2DEngine.applyOpacity(-1, getFadeFactor()));
/*     */     
/* 142 */     boolean online = Module.mc.field_1724.field_3944.method_2880().stream().map(p -> p.method_2966().getName()).toList().contains(this.name);
/*     */     
/* 144 */     FontRenderers.settings.drawString(context.method_51448(), online ? "online" : "offline", (this.posX + 37), (this.posY + 17), online ? Render2DEngine.applyOpacity((new Color(-16025088, true)).getRGB(), getFadeFactor()) : Render2DEngine.applyOpacity((new Color(-4342339, true)).getRGB(), getFadeFactor()));
/*     */   }
/*     */   
/*     */   private float getFadeFactor() {
/* 148 */     return this.fade / (5.0F + this.index);
/*     */   }
/*     */   
/*     */   public void onTick() {
/* 152 */     if (this.progress > 4) {
/* 153 */       this.progress = 0;
/*     */     }
/* 155 */     this.progress++;
/*     */     
/* 157 */     if (this.fade < 10 + this.index) {
/* 158 */       this.fade++;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isHovered(int mouseX, int mouseY) {
/* 163 */     return (mouseX > this.posX && mouseX < this.posX + 295 && mouseY > this.posY && mouseY < this.posY + 30);
/*     */   }
/*     */   
/*     */   public void movePosition(float deltaX, float deltaY) {
/* 167 */     this.posY = (int)(this.posY + deltaY);
/* 168 */     this.posX = (int)(this.posX + deltaX);
/* 169 */     this.scrollPosY = this.posY;
/*     */   }
/*     */   
/*     */   public void mouseClicked(int MouseX, int MouseY, int clickedButton) {
/* 173 */     if (this.posY > (ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790 || this.posY < (ThunderGui.getInstance()).main_posY) {
/*     */       return;
/*     */     }
/* 176 */     if (Render2DEngine.isHovered(MouseX, MouseY, (this.posX + 268), (this.posY + 10), 10.0D, 10.0D)) {
/* 177 */       Managers.FRIEND.removeFriend(this.name);
/* 178 */       ThunderGui.getInstance().loadFriends();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getPosX() {
/* 183 */     return this.posX;
/*     */   }
/*     */   
/*     */   public double getPosY() {
/* 187 */     return this.posY;
/*     */   }
/*     */   
/*     */   public void scrollElement(float deltaY) {
/* 191 */     this.scroll_animation = 0.0F;
/* 192 */     this.prevPosY = this.posY;
/* 193 */     this.scrollPosY += deltaY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\FriendComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */