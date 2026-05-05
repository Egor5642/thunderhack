/*     */ package thunder.hack.gui.thundergui.components;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ 
/*     */ public class SettingElement
/*     */ {
/*     */   protected Setting setting;
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float width;
/*     */   protected float height;
/*     */   protected float offsetY;
/*     */   protected float prev_offsetY;
/*     */   protected float scroll_offsetY;
/*     */   protected float scroll_animation;
/*     */   protected boolean hovered;
/*     */   
/*     */   public SettingElement(Setting setting) {
/*  24 */     this.setting = setting;
/*  25 */     this.scroll_animation = 1.0F;
/*  26 */     this.prev_offsetY = this.y;
/*  27 */     this.scroll_offsetY = this.y;
/*     */   }
/*     */   
/*     */   public void render(class_4587 matrixStack, int mouseX, int mouseY, float delta) {
/*  31 */     this.hovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
/*  32 */     if (this.scroll_offsetY != this.y) {
/*  33 */       this.scroll_animation = AnimationUtility.fast(this.scroll_animation, 1.0F, 5.0F);
/*  34 */       this.y = (int)Render2DEngine.interpolate(this.prev_offsetY, this.scroll_offsetY, this.scroll_animation);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {}
/*     */ 
/*     */   
/*     */   public void onTick() {}
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {}
/*     */ 
/*     */   
/*     */   public void tick() {}
/*     */   
/*     */   public boolean isHovered() {
/*  51 */     return this.hovered;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {}
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {}
/*     */ 
/*     */   
/*     */   public void keyTyped(String chr, int keyCode) {}
/*     */ 
/*     */   
/*     */   public void onClose() {}
/*     */ 
/*     */   
/*     */   public void resetAnimation() {}
/*     */   
/*     */   public Setting getSetting() {
/*  70 */     return this.setting;
/*     */   }
/*     */   
/*     */   public float getX() {
/*  74 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/*  78 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/*  82 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/*  86 */     this.prev_offsetY = this.y;
/*  87 */     this.scroll_offsetY = y + this.offsetY;
/*     */   }
/*     */   
/*     */   public void setPrev_offsetY(float y) {
/*  91 */     this.prev_offsetY = y;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/*  95 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/*  99 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 103 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 107 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void setOffsetY(float offsetY) {
/* 111 */     this.offsetY = offsetY;
/*     */   }
/*     */   
/*     */   public boolean isVisible() {
/* 115 */     return this.setting.isVisible();
/*     */   }
/*     */   
/*     */   public void checkMouseWheel(float dWheel) {
/* 119 */     if (dWheel != 0.0F)
/* 120 */       this.scroll_animation = 0.0F; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\SettingElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */