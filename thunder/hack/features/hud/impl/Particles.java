/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class Particles
/*    */ {
/*    */   public double x;
/*    */   public double y;
/*    */   public double deltaX;
/*    */   
/*    */   public static Color mixColors(Color color1, Color color2, double percent) {
/* 17 */     double inverse_percent = 1.0D - percent;
/* 18 */     int redPart = (int)(color1.getRed() * percent + color2.getRed() * inverse_percent);
/* 19 */     int greenPart = (int)(color1.getGreen() * percent + color2.getGreen() * inverse_percent);
/* 20 */     int bluePart = (int)(color1.getBlue() * percent + color2.getBlue() * inverse_percent);
/* 21 */     return new Color(redPart, greenPart, bluePart);
/*    */   }
/*    */   public double deltaY; public double size; public double opacity; public Color color;
/*    */   public void render2D(class_4587 matrixStack) {
/* 25 */     drawStar(matrixStack, (float)this.x, (float)this.y, this.color);
/*    */   }
/*    */   
/*    */   public void drawStar(class_4587 matrices, float x, float y, Color c) {
/* 29 */     RenderSystem.enableBlend();
/* 30 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 31 */     RenderSystem.setShaderTexture(0, TextureStorage.star);
/* 32 */     RenderSystem.setShaderColor(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, (float)(this.opacity / 255.0D));
/* 33 */     Render2DEngine.renderTexture(matrices, x + this.size / 2.0D, y + this.size / 2.0D, this.size, this.size, 0.0F, 0.0F, 256.0D, 256.0D, 256.0D, 256.0D);
/* 34 */     RenderSystem.disableBlend();
/* 35 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public void updatePosition() {
/* 39 */     this.x += this.deltaX;
/* 40 */     this.y += this.deltaY;
/*    */     
/* 42 */     this.deltaY *= 0.95D;
/* 43 */     this.deltaX *= 0.95D;
/*    */     
/* 45 */     this.opacity -= 2.0D;
/* 46 */     this.size /= 1.01D;
/*    */     
/* 48 */     if (this.opacity < 1.0D)
/* 49 */       this.opacity = 1.0D; 
/*    */   }
/*    */   
/*    */   public void init(double x, double y, double deltaX, double deltaY, double size, Color color) {
/* 53 */     this.x = x;
/* 54 */     this.y = y;
/* 55 */     this.deltaX = deltaX;
/* 56 */     this.deltaY = deltaY;
/* 57 */     this.size = size;
/* 58 */     this.opacity = 254.0D;
/* 59 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\Particles.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */