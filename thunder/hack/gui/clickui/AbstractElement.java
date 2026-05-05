/*    */ package thunder.hack.gui.clickui;
/*    */ 
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_3675;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public abstract class AbstractElement {
/*    */   protected Setting setting;
/*    */   protected float x;
/*    */   protected float y;
/*    */   protected float width;
/*    */   protected float height;
/*    */   protected float offsetY;
/*    */   protected boolean hovered;
/*    */   
/*    */   public AbstractElement(Setting setting) {
/* 20 */     this.setting = setting;
/*    */   }
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 24 */     this.hovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {}
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 31 */     if (class_3675.method_15987(class_310.method_1551().method_22683().method_4490(), 261) && button == 2 && this.hovered) {
/* 32 */       Object object = this.setting.getValue(); if (object instanceof ColorSetting) { ColorSetting cs = (ColorSetting)object;
/* 33 */         cs.setDefault(); }
/*    */       else
/* 35 */       { this.setting.setValue(this.setting.getDefaultValue()); }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int button) {}
/*    */ 
/*    */   
/*    */   public void keyTyped(int keyCode) {}
/*    */   
/*    */   public void onClose() {}
/*    */   
/*    */   public Setting getSetting() {
/* 49 */     return this.setting;
/*    */   }
/*    */   
/*    */   public float getX() {
/* 53 */     return this.x;
/*    */   }
/*    */   
/*    */   public float getY() {
/* 57 */     return this.y;
/*    */   }
/*    */   
/*    */   public float getWidth() {
/* 61 */     return this.width;
/*    */   }
/*    */   
/*    */   public float getHeight() {
/* 65 */     return this.height;
/*    */   }
/*    */   
/*    */   public void setX(float x) {
/* 69 */     this.x = x;
/*    */   }
/*    */   
/*    */   public void setY(float y) {
/* 73 */     this.y = y + this.offsetY;
/*    */   }
/*    */   
/*    */   public void setWidth(float width) {
/* 77 */     this.width = width;
/*    */   }
/*    */   
/*    */   public void setHeight(float height) {
/* 81 */     this.height = height;
/*    */   }
/*    */   
/*    */   public void setOffsetY(float offsetY) {
/* 85 */     this.offsetY = offsetY;
/*    */   }
/*    */   
/*    */   public boolean isVisible() {
/* 89 */     return this.setting.isVisible();
/*    */   }
/*    */   
/*    */   public void charTyped(char key, int keyCode) {}
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\AbstractElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */