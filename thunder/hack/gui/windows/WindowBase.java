/*     */ package thunder.hack.gui.windows;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.PositionSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ 
/*     */ public class WindowBase
/*     */ {
/*     */   public final Setting<PositionSetting> position;
/*     */   private float x;
/*     */   private float y;
/*     */   private float width;
/*     */   private float height;
/*     */   private float dragX;
/*     */   private float dragY;
/*     */   private float scrollOffset;
/*     */   private float prevScrollOffset;
/*     */   private float maxElementsHeight;
/*     */   private final String name;
/*     */   private boolean dragging;
/*     */   private boolean hoveringWindow;
/*     */   private boolean scaling;
/*     */   private boolean scrolling;
/*     */   private boolean visible = true;
/*     */   private final class_2960 icon;
/*     */   
/*     */   protected WindowBase(float x, float y, float width, float height, String name, Setting<PositionSetting> pos, class_2960 icon) {
/*  40 */     setX(x);
/*  41 */     setY(y);
/*  42 */     setWidth(width);
/*  43 */     setHeight(height);
/*  44 */     this.name = name;
/*  45 */     this.position = pos;
/*  46 */     this.icon = icon;
/*     */   }
/*     */   
/*     */   protected void render(class_332 context, int mouseX, int mouseY) {
/*  50 */     this.prevScrollOffset = AnimationUtility.fast(this.prevScrollOffset, this.scrollOffset, 12.0F);
/*  51 */     Color color2 = new Color(-983868581, true);
/*     */     
/*  53 */     RenderSystem.enableBlend();
/*  54 */     RenderSystem.defaultBlendFunc();
/*  55 */     Render2DEngine.drawHudBase(context.method_51448(), this.x, this.y, this.width + 10.0F, this.height, 1.0F, false);
/*  56 */     Render2DEngine.drawRect(context.method_51448(), this.x + 0.5F, this.y, this.width + 9.0F, 16.0F, new Color(1593835520, true));
/*  57 */     Render2DEngine.horizontalGradient(context.method_51448(), this.x + 2.0F, this.y + 16.0F, this.x + 2.0F + this.width / 2.0F - 2.0F, this.y + 16.5F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/*  58 */     Render2DEngine.horizontalGradient(context.method_51448(), this.x + 2.0F + this.width / 2.0F - 2.0F, this.y + 16.0F, this.x + 2.0F + this.width - 4.0F, this.y + 16.5F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*  59 */     FontRenderers.sf_medium.drawString(context.method_51448(), this.name, (this.x + 4.0F), (this.y + 5.5F), -1);
/*  60 */     boolean hover1 = Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width - 4.0F), (this.y + 3.0F), 10.0D, 10.0D);
/*  61 */     Render2DEngine.drawRectWithOutline(context.method_51448(), this.x + this.width - 4.0F, this.y + 3.0F, 10.0F, 10.0F, hover1 ? new Color(-982026377, true) : new Color(-984131753, true), color2);
/*     */     
/*  63 */     float ratio = (getHeight() - 35.0F) / this.maxElementsHeight;
/*     */     
/*  65 */     boolean hover2 = Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width), (this.y + 19.0F), 6.0D, (getHeight() - 34.0F));
/*  66 */     Render2DEngine.drawRectWithOutline(context.method_51448(), this.x + this.width, this.y + 19.0F, 6.0F, getHeight() - 34.0F, hover2 ? new Color(1595085587, true) : new Color(1593835520, true), color2);
/*  67 */     Render2DEngine.drawRect(context.method_51448(), this.x + this.width, Math.max(this.y + 19.0F - this.scrollOffset * ratio, this.y + 19.0F), 6.0F, Math.min((getHeight() - 34.0F) * ratio, getHeight() - 34.0F), new Color(-1590611663, true));
/*     */     
/*  69 */     Render2DEngine.drawLine(this.x + this.width - 2.0F, this.y + 5.0F, this.x + this.width + 4.0F, this.y + 11.0F, -1);
/*  70 */     Render2DEngine.drawLine(this.x + this.width - 2.0F, this.y + 11.0F, this.x + this.width + 4.0F, this.y + 5.0F, -1);
/*  71 */     RenderSystem.disableBlend();
/*     */     
/*  73 */     if (this.dragging) {
/*  74 */       setX(Render2DEngine.scrollAnimate(normaliseX() - this.dragX, getX(), 0.15F));
/*  75 */       setY(Render2DEngine.scrollAnimate(normaliseY() - this.dragY, getY(), 0.15F));
/*  76 */       if (this.position != null) {
/*  77 */         ((PositionSetting)this.position.getValue()).setX(getX() / IManager.mc.method_22683().method_4486());
/*  78 */         ((PositionSetting)this.position.getValue()).setY(getY() / IManager.mc.method_22683().method_4502());
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (this.scaling) {
/*  83 */       setWidth(Math.max(Render2DEngine.scrollAnimate(normaliseX() - this.dragX, getWidth(), 0.15F), getMinWidth()));
/*  84 */       setHeight(Math.max(Render2DEngine.scrollAnimate(normaliseY() - this.dragY, getHeight(), 0.15F), getMinHeight()));
/*     */     } 
/*     */     
/*  87 */     if (this.scrolling) {
/*  88 */       float diff = (mouseY - this.y - 19.0F) / (getHeight() - 34.0F);
/*  89 */       this.scrollOffset = -(diff * this.maxElementsHeight);
/*  90 */       this.scrollOffset = MathUtility.clamp(this.scrollOffset, -this.maxElementsHeight + getHeight() - 40.0F, 0.0F);
/*     */     } 
/*     */     
/*  93 */     this.hoveringWindow = Render2DEngine.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight());
/*     */     
/*  95 */     Render2DEngine.drawLine(getX() + getWidth(), getY() + getHeight() - 3.0F, getX() + getWidth() + 7.0F, getY() + getHeight() - 10.0F, color2.getRGB());
/*  96 */     Render2DEngine.drawLine(getX() + getWidth() + 5.0F, getY() + getHeight() - 3.0F, getX() + getWidth() + 7.0F, getY() + getHeight() - 5.0F, color2.getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(double mouseX, double mouseY, int button) {
/* 101 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width - 4.0F), (this.y + 3.0F), 10.0D, 10.0D)) {
/* 102 */       setVisible(false);
/*     */       
/*     */       return;
/*     */     } 
/* 106 */     if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, 10.0D)) {
/* 107 */       if (WindowsScreen.draggingWindow == null) {
/* 108 */         this.dragging = true;
/*     */       }
/* 110 */       if (WindowsScreen.draggingWindow == null) {
/* 111 */         WindowsScreen.draggingWindow = this;
/*     */       }
/* 113 */       WindowsScreen.lastClickedWindow = this;
/* 114 */       this.dragX = (int)(mouseX - getX());
/* 115 */       this.dragY = (int)(mouseY - getY());
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width), (this.y + this.height - 10.0F), 10.0D, 10.0D)) {
/* 120 */       WindowsScreen.lastClickedWindow = this;
/* 121 */       this.dragX = (int)(mouseX - getWidth());
/* 122 */       this.dragY = (int)(mouseY - getHeight());
/* 123 */       this.scaling = true;
/*     */       
/*     */       return;
/*     */     } 
/* 127 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width), (this.y + 19.0F), 6.0D, (getHeight() - 34.0F))) {
/* 128 */       WindowsScreen.lastClickedWindow = this;
/* 129 */       this.dragX = (int)(mouseX - getWidth());
/* 130 */       this.dragY = (int)(mouseY - getHeight());
/* 131 */       this.scrolling = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyPressed(int keyCode, int scanCode, int modifiers) {}
/*     */ 
/*     */   
/*     */   protected void charTyped(char key, int keyCode) {}
/*     */   
/*     */   protected void mouseScrolled(int i) {
/* 142 */     if (this.hoveringWindow) {
/* 143 */       this.scrollOffset += (i * 2);
/* 144 */       this.scrollOffset = MathUtility.clamp(this.scrollOffset, -this.maxElementsHeight + getHeight() - 40.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseReleased(double mouseX, double mouseY, int button) {
/* 149 */     this.dragging = false;
/* 150 */     this.scaling = false;
/* 151 */     this.scrolling = false;
/* 152 */     WindowsScreen.draggingWindow = null;
/*     */   }
/*     */   
/*     */   protected float getX() {
/* 156 */     return this.x;
/*     */   }
/*     */   
/*     */   protected void setX(float x) {
/* 160 */     this.x = x;
/*     */   }
/*     */   
/*     */   protected float getY() {
/* 164 */     return this.y;
/*     */   }
/*     */   
/*     */   protected void setY(float y) {
/* 168 */     this.y = y;
/*     */   }
/*     */   
/*     */   protected float getWidth() {
/* 172 */     return this.width;
/*     */   }
/*     */   
/*     */   protected void setWidth(float width) {
/* 176 */     this.width = width;
/*     */   }
/*     */   
/*     */   protected float getHeight() {
/* 180 */     return this.height;
/*     */   }
/*     */   
/*     */   protected void setHeight(float height) {
/* 184 */     this.height = height;
/*     */   }
/*     */   
/*     */   protected int normaliseX() {
/* 188 */     return (int)(IManager.mc.field_1729.method_1603() / Render3DEngine.getScaleFactor());
/*     */   }
/*     */   
/*     */   protected int normaliseY() {
/* 192 */     return (int)(IManager.mc.field_1729.method_1604() / Render3DEngine.getScaleFactor());
/*     */   }
/*     */   
/*     */   protected float getScrollOffset() {
/* 196 */     return this.prevScrollOffset;
/*     */   }
/*     */   
/*     */   protected void resetScroll() {
/* 200 */     this.prevScrollOffset = 0.0F;
/* 201 */     this.scrollOffset = 0.0F;
/*     */   }
/*     */   
/*     */   protected int getMinWidth() {
/* 205 */     return 150;
/*     */   }
/*     */   
/*     */   protected int getMinHeight() {
/* 209 */     return 150;
/*     */   }
/*     */   
/*     */   protected void setMaxElementsHeight(float maxElementsHeight) {
/* 213 */     this.maxElementsHeight = maxElementsHeight;
/*     */   }
/*     */   
/*     */   public boolean isVisible() {
/* 217 */     return this.visible;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 221 */     this.visible = visible;
/*     */   }
/*     */   
/*     */   public class_2960 getIcon() {
/* 225 */     return this.icon;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\windows\WindowBase.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */