/*     */ package thunder.hack.features.hud;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_332;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.lwjgl.glfw.GLFW;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.events.impl.EventMouse;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.hud.HudEditorGui;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.PositionSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class HudElement
/*     */   extends Module
/*     */ {
/*  21 */   private final Setting<PositionSetting> pos = new Setting("Position", new PositionSetting(0.5F, 0.5F)); private boolean mouseState = false;
/*     */   private boolean mouseButton = false;
/*     */   private float x;
/*     */   private float y;
/*     */   private float dragX;
/*     */   
/*     */   public HudElement(String name, int width, int height) {
/*  28 */     super(name, Module.Category.HUD);
/*  29 */     this.height = height;
/*  30 */     this.width = width;
/*     */   }
/*     */   private float dragY; private float hitX; private float hitY; private float height; private float width; public static boolean anyHovered = false;
/*     */   
/*     */   public void onRender2D(class_332 context) {
/*  35 */     this.y = mc.method_22683().method_4502() * ((PositionSetting)this.pos.getValue()).getY();
/*  36 */     this.x = mc.method_22683().method_4486() * ((PositionSetting)this.pos.getValue()).getX();
/*     */     
/*  38 */     if ((mc.field_1755 instanceof net.minecraft.class_408 || mc.field_1755 instanceof HudEditorGui) && 
/*  39 */       this.mouseButton && this.mouseState) {
/*  40 */       ((PositionSetting)this.pos.getValue()).setX(Math.clamp(Render2DEngine.scrollAnimate((normaliseX() - this.dragX) / mc.method_22683().method_4486(), ((PositionSetting)this.pos.getValue()).getX(), 0.1F), 0.0F, 1.0F));
/*     */       
/*  42 */       ((PositionSetting)this.pos.getValue()).setY(Math.clamp(Render2DEngine.scrollAnimate((normaliseY() - this.dragY) / mc.method_22683().method_4502(), ((PositionSetting)this.pos.getValue()).getY(), 0.1F), 0.0F, 1.0F - this.height / mc
/*  43 */             .method_22683().method_4502()));
/*     */       
/*  45 */       float finalX = 0.0F;
/*  46 */       float finalY = 0.0F;
/*     */       
/*  48 */       if (((Boolean)HudEditor.sticky.getValue()).booleanValue())
/*  49 */         for (Module m : Managers.MODULE.getEnabledModules()) {
/*  50 */           if (m instanceof HudElement) { HudElement hudElement = (HudElement)m; if (hudElement != this && (hudElement.getPosX() != 0.0F || hudElement.getPosY() != 0.0F)) {
/*  51 */               if (getPosX() > mc.method_22683().method_4486() / 2.0F) {
/*  52 */                 if (isNear(hudElement.getHitX() + hudElement.getWidth(), getHitX() + getWidth())) {
/*  53 */                   finalX = hudElement.getHitX() + hudElement.getWidth() - getWidth();
/*     */                 }
/*     */               }
/*  56 */               else if (isNear(hudElement.getHitX(), getHitX())) {
/*  57 */                 finalX = hudElement.getHitX();
/*     */               } 
/*     */               
/*  60 */               if (isNear(hudElement.getHitY(), getHitY()))
/*  61 */                 finalY = hudElement.getHitY(); 
/*     */             }  }
/*     */         
/*  64 */         }   if (finalX != 0.0F || finalY != 0.0F) {
/*  65 */         Render2DEngine.drawRound(context.method_51448(), (finalX == 0.0F) ? getHitX() : finalX, (finalY == 0.0F) ? getHitY() : finalY, this.width, this.height, 3.0F, new Color(2066689839, true));
/*     */       }
/*  67 */       if (finalX != 0.0F) {
/*  68 */         Render2DEngine.drawLine(finalX, 0.0F, finalX, mc.method_22683().method_4502(), -1);
/*     */       }
/*  70 */       if (finalY != 0.0F) {
/*  71 */         Render2DEngine.drawLine(0.0F, finalY, mc.method_22683().method_4486(), finalY, -1);
/*     */       }
/*     */     } 
/*     */     
/*  75 */     if (this.mouseButton) {
/*  76 */       if (!this.mouseState && isHovering()) {
/*  77 */         this.dragX = (int)(normaliseX() - ((PositionSetting)this.pos.getValue()).getX() * mc.method_22683().method_4486());
/*  78 */         this.dragY = (int)(normaliseY() - ((PositionSetting)this.pos.getValue()).getY() * mc.method_22683().method_4502());
/*  79 */         this.mouseState = true;
/*     */       } 
/*     */     } else {
/*  82 */       this.mouseState = false;
/*     */     } 
/*     */     
/*  85 */     if (isHovering() && (mc.field_1755 instanceof net.minecraft.class_408 || mc.field_1755 instanceof HudEditorGui)) {
/*  86 */       if (GLFW.glfwGetPlatform() != 393219) {
/*  87 */         GLFW.glfwSetCursor(mc.method_22683().method_4490(), this.mouseState ? GLFW.glfwCreateStandardCursor(221187) : GLFW.glfwCreateStandardCursor(221188));
/*     */       }
/*  89 */       anyHovered = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onMouse(@NotNull EventMouse event) {
/*  97 */     if (event.getAction() == 0 && event.getButton() == 1 && isHovering() && mc.field_1755 instanceof HudEditorGui) {
/*  98 */       HudEditorGui.getHudGui().hudClicked(this);
/*     */     }
/* 100 */     if (event.getAction() == 0) {
/*     */       
/* 102 */       if (this.mouseButton && this.mouseState && (
/* 103 */         (Boolean)HudEditor.sticky.getValue()).booleanValue())
/* 104 */         for (Module m : Managers.MODULE.getEnabledModules()) {
/* 105 */           if (m instanceof HudElement) { HudElement hudElement = (HudElement)m; if (hudElement != this && (hudElement.getHitX() != 0.0F || hudElement.getHitY() != 0.0F)) {
/*     */               
/* 107 */               float hitDifX = getPosX() - getHitX();
/* 108 */               float hitDifY = getPosY() - getHitY();
/*     */               
/* 110 */               if (getPosX() > mc.method_22683().method_4486() / 2.0F) {
/* 111 */                 if (isNear(hudElement.getHitX() + hudElement.getWidth(), getHitX() + getWidth())) {
/* 112 */                   ((PositionSetting)this.pos.getValue()).setX((hudElement.getHitX() + hitDifX + hudElement.getWidth() - getWidth()) / mc.method_22683().method_4486());
/*     */                 }
/* 114 */               } else if (isNear(hudElement.getHitX(), getHitX())) {
/* 115 */                 ((PositionSetting)this.pos.getValue()).setX((hudElement.getHitX() + hitDifX) / mc.method_22683().method_4486());
/*     */               } 
/* 117 */               if (isNear(hudElement.getHitY(), getHitY()))
/* 118 */                 ((PositionSetting)this.pos.getValue()).setY((hudElement.getHitY() + hitDifY) / mc.method_22683().method_4502()); 
/*     */             }  }
/*     */         
/*     */         }  
/* 122 */       HudEditorGui.currentlyDragging = null;
/* 123 */       this.mouseButton = false;
/*     */     } 
/* 125 */     if (event.getAction() == 1 && isHovering() && HudEditorGui.currentlyDragging == null) {
/* 126 */       HudEditorGui.currentlyDragging = this;
/* 127 */       this.mouseButton = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int normaliseX() {
/* 132 */     return (int)(mc.field_1729.method_1603() / Render3DEngine.getScaleFactor());
/*     */   }
/*     */   
/*     */   public int normaliseY() {
/* 136 */     return (int)(mc.field_1729.method_1604() / Render3DEngine.getScaleFactor());
/*     */   }
/*     */   
/*     */   public boolean isHovering() {
/* 140 */     return (normaliseX() > Math.min(this.hitX, this.hitX + this.width) && normaliseX() < Math.max(this.hitX, this.hitX + this.width) && normaliseY() > Math.min(this.hitY, this.hitY + this.height) && normaliseY() < Math.max(this.hitY, this.hitY + this.height));
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/* 144 */     this.width = width;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 148 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void setHitX(float hitX) {
/* 152 */     this.hitX = hitX;
/*     */   }
/*     */   
/*     */   public void setHitY(float hitY) {
/* 156 */     this.hitY = hitY;
/*     */   }
/*     */   
/*     */   public void setBounds(float x, float y, float w, float h) {
/* 160 */     setHitX(x);
/* 161 */     setHitY(y);
/* 162 */     setWidth(w);
/* 163 */     setHeight(h);
/*     */   }
/*     */   
/*     */   public float getPosX() {
/* 167 */     return this.x;
/*     */   }
/*     */   
/*     */   public float getHitX() {
/* 171 */     return this.hitX;
/*     */   }
/*     */   
/*     */   public float getHitY() {
/* 175 */     return this.hitY;
/*     */   }
/*     */   
/*     */   public float getPosY() {
/* 179 */     return this.y;
/*     */   }
/*     */   
/*     */   public float getX() {
/* 183 */     return ((PositionSetting)this.pos.getValue()).x;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 187 */     return ((PositionSetting)this.pos.getValue()).y;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 191 */     return this.height;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 195 */     return this.width;
/*     */   }
/*     */   
/*     */   private boolean isNear(float n1, float n2) {
/* 199 */     return (Math.abs(n1 - n2) < 10.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\HudElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */