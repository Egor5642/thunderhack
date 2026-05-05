/*    */ package thunder.hack.gui.clickui.impl;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_3544;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.clickui.ClickGUI;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class StringElement extends AbstractElement {
/*    */   public boolean listening;
/*    */   private String currentString;
/*    */   
/*    */   public StringElement(Setting setting) {
/* 19 */     super(setting);
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.currentString = "";
/*    */   }
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 27 */     super.render(context, mouseX, mouseY, delta);
/*    */     
/* 29 */     Render2DEngine.drawRect(context.method_51448(), getX() + 5.0F, getY() + 2.0F, getWidth() - 11.0F, 10.0F, new Color(-1811939328, true));
/* 30 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.listening ? (this.currentString + this.currentString) : (String)this.setting.getValue(), (this.x + 6.0F), (this.y + this.height / 2.0F), -1);
/*    */     
/* 32 */     if (Render2DEngine.isHovered(mouseX, mouseY, (getX() + 5.0F), (getY() + 2.0F), (getWidth() - 11.0F), 10.0D)) {
/* 33 */       if (GLFW.glfwGetPlatform() != 393219) {
/* 34 */         GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), 
/* 35 */             GLFW.glfwCreateStandardCursor(221186));
/*    */       }
/* 37 */       ClickGUI.anyHovered = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 43 */     if (this.hovered && button == 0)
/* 44 */       this.listening = !this.listening; 
/* 45 */     if (this.listening) {
/* 46 */       ThunderHack.currentKeyListener = ThunderHack.KeyListening.Strings;
/* 47 */       this.currentString = (String)this.setting.getValue();
/*    */     } 
/* 49 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */ 
/*    */   
/*    */   public void charTyped(char key, int keyCode) {
/* 54 */     if (class_3544.method_57175(key)) {
/* 55 */       this.currentString += this.currentString;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyTyped(int keyCode) {
/* 61 */     if (ThunderHack.currentKeyListener != ThunderHack.KeyListening.Strings) {
/*    */       return;
/*    */     }
/* 64 */     if (this.listening)
/* 65 */       switch (keyCode) {
/*    */ 
/*    */         
/*    */         case 257:
/* 69 */           this.setting.setValue((this.currentString == null || this.currentString.isEmpty()) ? this.setting.getDefaultValue() : this.currentString);
/* 70 */           this.currentString = "";
/* 71 */           this.listening = !this.listening; break;
/*    */         case 259:
/* 73 */           this.currentString = SliderElement.removeLastChar(this.currentString); break;
/* 74 */         case 32: this.currentString += " ";
/*    */           break;
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\StringElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */