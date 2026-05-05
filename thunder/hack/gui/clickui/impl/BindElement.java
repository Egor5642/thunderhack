/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.Bind;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class BindElement extends AbstractElement {
/*    */   public boolean isListening;
/*    */   
/*    */   public BindElement(Setting setting) {
/* 15 */     super(setting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 22 */     super.render(context, mouseX, mouseY, delta);
/* 23 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.setting.getName(), (getX() + 6.0F), (getY() + this.height / 2.0F - 3.0F + 2.0F), (new Color(-1)).getRGB());
/*    */     
/* 25 */     float tWidth = FontRenderers.sf_medium_mini.getStringWidth(this.isListening ? "..." : ((Bind)this.setting.getValue()).getBind());
/*    */     
/* 27 */     Render2DEngine.drawRect(context.method_51448(), getX() + getWidth() - tWidth - 11.0F, getY() + 2.0F, tWidth + 4.0F, 10.0F, new Color(-1811939328, true));
/* 28 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.isListening ? "..." : ((Bind)this.setting.getValue()).getBind(), (getX() + getWidth() - tWidth - 9.0F), (getY() + this.height / 2.0F - 1.0F), (new Color(-1)).getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 33 */     if (this.isListening) {
/* 34 */       Bind b = new Bind(button, true, false);
/* 35 */       this.setting.setValue(b);
/* 36 */       this.isListening = false;
/*    */     } 
/* 38 */     if (this.hovered && button == 0) this.isListening = !this.isListening;
/*    */     
/* 40 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyTyped(int keyCode) {
/* 45 */     if (this.isListening) {
/* 46 */       if (keyCode == 256 || keyCode == 261) {
/* 47 */         Bind b = new Bind(-1, false, false);
/* 48 */         this.setting.setValue(b);
/*    */       } else {
/* 50 */         Bind b = new Bind(keyCode, false, false);
/* 51 */         this.setting.setValue(b);
/*    */       } 
/* 53 */       this.isListening = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\BindElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */