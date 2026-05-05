/*    */ package thunder.hack.gui.thundergui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ 
/*    */ public class CategoryPlate
/*    */ {
/* 15 */   float category_animation = 0.0F;
/*    */   private final Module.Category cat;
/*    */   private int posX;
/*    */   private int posY;
/*    */   
/*    */   public CategoryPlate(Module.Category cat, int posX, int posY) {
/* 21 */     this.cat = cat;
/* 22 */     this.posX = posX;
/* 23 */     this.posY = posY;
/*    */   }
/*    */   
/*    */   public void render(class_4587 matrixStack, int MouseX, int MouseY) {
/* 27 */     this.category_animation = AnimationUtility.fast(this.category_animation, isHovered(MouseX, MouseY) ? 1.0F : 0.0F, 15.0F);
/* 28 */     Render2DEngine.addWindow(matrixStack, new Render2DEngine.Rectangle(this.posX, this.posY + 0.5F, (this.posX + 84), this.posY + 15.5F));
/* 29 */     if (isHovered(MouseX, MouseY)) {
/* 30 */       Render2DEngine.drawRound(matrixStack, this.posX, this.posY, 84.0F, 15.0F, 2.0F, new Color(25, 20, 30, (int)MathUtility.clamp(65.0F * this.category_animation, 0.0F, 255.0F)));
/* 31 */       Render2DEngine.drawBlurredShadow(matrixStack, (MouseX - 20), (MouseY - 20), 40.0F, 40.0F, 60, new Color(-1017816450, true));
/*    */     } 
/* 33 */     FontRenderers.modules.drawString(matrixStack, this.cat.getName(), (this.posX + 5), (this.posY + 6), -1);
/* 34 */     Render2DEngine.popWindow();
/*    */   }
/*    */   
/*    */   public void movePosition(float deltaX, float deltaY) {
/* 38 */     this.posY = (int)(this.posY + deltaY);
/* 39 */     this.posX = (int)(this.posX + deltaX);
/*    */   }
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
/* 43 */     if (isHovered(mouseX, mouseY)) {
/* 44 */       (ThunderGui.getInstance()).new_category = this.cat;
/* 45 */       if ((ThunderGui.getInstance()).current_category == null) {
/* 46 */         (ThunderGui.getInstance()).current_category = Module.Category.HUD;
/* 47 */         (ThunderGui.getInstance()).new_category = this.cat;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isHovered(int mouseX, int mouseY) {
/* 53 */     return (mouseX > this.posX && mouseX < this.posX + 84 && mouseY > this.posY && mouseY < this.posY + 15);
/*    */   }
/*    */   
/*    */   public Module.Category getCategory() {
/* 57 */     return this.cat;
/*    */   }
/*    */   
/*    */   public int getPosY() {
/* 61 */     return this.posY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\CategoryPlate.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */