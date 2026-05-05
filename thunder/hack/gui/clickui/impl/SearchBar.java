/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_3544;
/*    */ import net.minecraft.class_3675;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.clickui.AbstractButton;
/*    */ import thunder.hack.gui.clickui.ClickGUI;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class SearchBar
/*    */   extends AbstractButton {
/* 16 */   public static String moduleName = "";
/*    */   
/*    */   public static boolean listening;
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 21 */     super.render(context, mouseX, mouseY, delta);
/* 22 */     Render2DEngine.drawGuiBase(context.method_51448(), this.x + 4.0F, this.y + 1.0F, this.width - 8.0F, this.height - 2.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height) ? 0.8F : 0.0F);
/* 23 */     if (!listening) {
/* 24 */       FontRenderers.sf_medium.drawGradientString(context.method_51448(), "Search...", this.x + 7.0F, this.y + this.height / 2.0F - 3.0F, 2);
/*    */     } else {
/* 26 */       FontRenderers.sf_medium.drawGradientString(context.method_51448(), moduleName + moduleName, this.x + 7.0F, this.y + 5.0F, 2);
/*    */     } 
/* 28 */     if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
/* 29 */       if (GLFW.glfwGetPlatform() != 393219) {
/* 30 */         GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), 
/* 31 */             GLFW.glfwCreateStandardCursor(221186));
/*    */       }
/* 33 */       ClickGUI.anyHovered = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 39 */     super.mouseClicked(mouseX, mouseY, button);
/* 40 */     boolean isHovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
/*    */     
/* 42 */     if (isHovered) { listening = true; }
/*    */     else
/* 44 */     { moduleName = "";
/* 45 */       listening = false; }
/*    */ 
/*    */     
/* 48 */     if (listening) ThunderHack.currentKeyListener = ThunderHack.KeyListening.Search;
/*    */   
/*    */   }
/*    */   
/*    */   public void charTyped(char key, int keyCode) {
/* 53 */     if (class_3544.method_57175(key) && listening) {
/* 54 */       moduleName += moduleName;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyTyped(int keyCode) {
/* 60 */     super.keyTyped(keyCode);
/*    */     
/* 62 */     if (keyCode == 70 && (class_3675.method_15987(Module.mc.method_22683().method_4490(), 341) || class_3675.method_15987(Module.mc.method_22683().method_4490(), 345))) {
/* 63 */       listening = !listening;
/* 64 */       ThunderHack.currentKeyListener = ThunderHack.KeyListening.Search;
/*    */       
/*    */       return;
/*    */     } 
/* 68 */     if (ThunderHack.currentKeyListener != ThunderHack.KeyListening.Search) {
/*    */       return;
/*    */     }
/* 71 */     if (listening)
/* 72 */       switch (keyCode) { case 256:
/*    */         case 257:
/* 74 */           listening = false;
/* 75 */           moduleName = ""; break;
/*    */         case 259:
/* 77 */           moduleName = SliderElement.removeLastChar(moduleName); break;
/* 78 */         case 32: moduleName += " ";
/*    */           break; }
/*    */        
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\SearchBar.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */