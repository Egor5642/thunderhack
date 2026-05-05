/*     */ package thunder.hack.gui.clickui;
/*     */ 
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractCategory
/*     */ {
/*     */   private String name;
/*     */   public float animationY;
/*     */   protected float prevTargetX;
/*     */   protected float x;
/*     */   protected float y;
/*     */   protected float width;
/*     */   protected float height;
/*     */   
/*     */   public AbstractCategory(String name, float x, float y, float width, float height) {
/*  21 */     this.name = name;
/*  22 */     this.x = x;
/*  23 */     this.y = y;
/*  24 */     this.width = width;
/*  25 */     this.height = height;
/*  26 */     this.open = false;
/*     */   }
/*     */   protected float sx; protected float sy; private float prevX; private float prevY; protected boolean hovered; public boolean dragging; public float moduleOffset; private boolean open;
/*     */   
/*     */   public void init() {}
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*  33 */     this.hovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
/*  34 */     this.animationY = (float)interpolate(this.y, this.animationY, 0.05D);
/*  35 */     if (this.dragging)
/*  36 */     { this.prevTargetX = this.x;
/*  37 */       this.x = this.prevX + mouseX;
/*  38 */       this.y = this.prevY + mouseY; }
/*  39 */     else { this.prevTargetX = this.x; }
/*     */   
/*     */   }
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/*  43 */     if (this.hovered && button == 0) {
/*  44 */       this.dragging = true;
/*  45 */       this.prevX = this.x - mouseX;
/*  46 */       this.prevY = this.y - mouseY;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double interpolate(double current, double old, double scale) {
/*  51 */     return old + (current - old) * scale;
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/*  55 */     if (button == 0)
/*  56 */       this.dragging = false; 
/*     */   }
/*     */   
/*     */   public boolean keyTyped(int keyCode) {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void charTyped(char key, int modifier) {}
/*     */ 
/*     */   
/*     */   public void onClose() {}
/*     */   
/*     */   public void setOpen(boolean open) {
/*  70 */     this.open = open;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  74 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  78 */     return this.open;
/*     */   }
/*     */   
/*     */   public float getX() {
/*  82 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/*  86 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/*  90 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/*  94 */     this.y = y;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/*  98 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/* 102 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 106 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 110 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void setModuleOffset(float v, float mx, float my) {
/* 114 */     if (Render2DEngine.isHovered(mx, my, this.x, this.y, this.width, (this.height + 1000.0F))) {
/* 115 */       this.moduleOffset += v;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {}
/*     */ 
/*     */   
/*     */   public void hudClicked(Module module) {}
/*     */   
/*     */   public void savePos() {
/* 126 */     this.sx = this.x;
/* 127 */     this.sy = this.y;
/*     */   }
/*     */   
/*     */   public void restorePos() {
/* 131 */     this.x = this.sx;
/* 132 */     this.y = this.sy;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\AbstractCategory.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */