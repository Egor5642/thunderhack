/*    */ package thunder.hack.gui.clickui;
/*    */ 
/*    */ import net.minecraft.class_332;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractButton
/*    */ {
/*    */   public float x;
/*    */   public float y;
/*    */   public float width;
/*    */   
/*    */   public float getX() {
/* 14 */     return this.x;
/*    */   } public float height; public float target_offset; public float offsetY;
/*    */   public void init() {}
/*    */   public void setX(float x) {
/* 18 */     this.x = x;
/*    */   }
/*    */   
/*    */   public float getY() {
/* 22 */     return this.y;
/*    */   }
/*    */   
/*    */   public void setY(float y) {
/* 26 */     this.y = y + this.offsetY;
/*    */   }
/*    */   
/*    */   public float getWidth() {
/* 30 */     return this.width;
/*    */   }
/*    */   
/*    */   public void setWidth(float width) {
/* 34 */     this.width = width;
/*    */   }
/*    */   
/*    */   public float getHeight() {
/* 38 */     return this.height;
/*    */   }
/*    */   
/*    */   public void setHeight(float height) {
/* 42 */     this.height = height;
/*    */   }
/*    */   
/*    */   public void setTargetOffset(float offsetY) {
/* 46 */     this.target_offset = offsetY;
/*    */   }
/*    */   
/*    */   public void setOffset(float offsetY) {
/* 50 */     this.offsetY = offsetY;
/*    */   }
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {}
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int button) {}
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {}
/*    */   
/*    */   public void keyTyped(int keyCode) {}
/*    */   
/*    */   public void onGuiClosed() {}
/*    */   
/*    */   public void tick() {}
/*    */   
/*    */   public void charTyped(char key, int keyCode) {}
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\AbstractButton.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */