/*     */ package thunder.hack.gui.clickui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import org.lwjgl.glfw.GLFW;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClickGui;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ import thunder.hack.utility.render.animation.EaseOutBack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClickGUI
/*     */   extends class_437
/*     */ {
/*     */   public static List<AbstractCategory> windows;
/*     */   public static boolean anyHovered;
/*     */   private boolean firstOpen;
/*     */   private float scrollY;
/*     */   private float closeAnimation;
/*  35 */   public static String currentDescription = ""; private float prevYaw; private float prevPitch; private float closeDirectionX; private float closeDirectionY; public static boolean close = false; public static boolean imageDirection;
/*  36 */   public EaseOutBack imageAnimation = new EaseOutBack(6);
/*     */   
/*     */   public ClickGUI() {
/*  39 */     super(class_2561.method_30163("NewClickGUI"));
/*  40 */     windows = Lists.newArrayList();
/*  41 */     this.firstOpen = true;
/*  42 */     setInstance();
/*     */   }
/*     */   
/*  45 */   private static ClickGUI INSTANCE = new ClickGUI();
/*     */   
/*     */   public static ClickGUI getInstance() {
/*  48 */     if (INSTANCE == null) {
/*  49 */       INSTANCE = new ClickGUI();
/*     */     }
/*     */     
/*  52 */     imageDirection = true;
/*     */     
/*  54 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static ClickGUI getClickGui() {
/*  58 */     windows.forEach(AbstractCategory::init);
/*  59 */     return getInstance();
/*     */   }
/*     */   
/*     */   private void setInstance() {
/*  63 */     INSTANCE = this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  68 */     if (this.firstOpen) {
/*  69 */       float offset = 0.0F;
/*  70 */       int windowHeight = 18;
/*     */       
/*  72 */       int halfWidth = Module.mc.method_22683().method_4486() / 2;
/*  73 */       int halfWidthCats = (int)((Module.Category.values().size() - 1.0F) / 2.0F * (((Integer)ModuleManager.clickGui.moduleWidth.getValue()).intValue() + 4.0F));
/*     */       
/*  75 */       for (Module.Category category : Managers.MODULE.getCategories()) {
/*  76 */         if (category == Module.Category.HUD)
/*  77 */           continue;  Category window = new Category(category, Managers.MODULE.getModulesByCategory(category), (halfWidth - halfWidthCats) + offset, 20.0F, 100.0F, windowHeight);
/*  78 */         window.setOpen(true);
/*  79 */         windows.add(window);
/*  80 */         offset += (((Integer)ModuleManager.clickGui.moduleWidth.getValue()).intValue() + 2);
/*  81 */         if (offset > Module.mc.method_22683().method_4486())
/*  82 */           offset = 0.0F; 
/*     */       } 
/*  84 */       this.firstOpen = false;
/*     */     }
/*  86 */     else if (((AbstractCategory)windows.getFirst()).getX() < 0.0F || ((AbstractCategory)windows.getFirst()).getY() < 0.0F) {
/*  87 */       float offset = 0.0F;
/*     */       
/*  89 */       int halfWidth = Module.mc.method_22683().method_4486() / 2;
/*  90 */       int halfWidthCats = (int)(3.0F * (((Integer)ModuleManager.clickGui.moduleWidth.getValue()).intValue() + 4.0F));
/*     */       
/*  92 */       for (AbstractCategory w : windows) {
/*  93 */         w.setX((halfWidth - halfWidthCats) + offset);
/*  94 */         w.setY(20.0F);
/*  95 */         offset += (((Integer)ModuleManager.clickGui.moduleWidth.getValue()).intValue() + 2);
/*  96 */         if (offset > Module.mc.method_22683().method_4486()) {
/*  97 */           offset = 0.0F;
/*     */         }
/*     */       } 
/*     */     } 
/* 101 */     windows.forEach(AbstractCategory::init);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25393() {
/* 111 */     windows.forEach(AbstractCategory::tick);
/* 112 */     this.imageAnimation.update(imageDirection);
/*     */     
/* 114 */     if (close) {
/* 115 */       if (Module.mc.field_1724 != null) {
/* 116 */         if (Module.mc.field_1724.method_36455() > this.prevPitch) {
/* 117 */           this.closeDirectionY = (this.prevPitch - Module.mc.field_1724.method_36455()) * 300.0F;
/*     */         }
/* 119 */         if (Module.mc.field_1724.method_36455() < this.prevPitch) {
/* 120 */           this.closeDirectionY = (this.prevPitch - Module.mc.field_1724.method_36455()) * 300.0F;
/*     */         }
/* 122 */         if (Module.mc.field_1724.method_36454() > this.prevYaw) {
/* 123 */           this.closeDirectionX = (this.prevYaw - Module.mc.field_1724.method_36454()) * 300.0F;
/*     */         }
/* 125 */         if (Module.mc.field_1724.method_36454() < this.prevYaw) {
/* 126 */           this.closeDirectionX = (this.prevYaw - Module.mc.field_1724.method_36454()) * 300.0F;
/*     */         }
/*     */       } 
/* 129 */       if (this.closeDirectionX < 1.0F && this.closeDirectionY < 1.0F && this.closeAnimation > 2.0F) {
/* 130 */         this.closeDirectionY = -3000.0F;
/*     */       }
/* 132 */       this.closeAnimation++;
/* 133 */       if (this.closeAnimation > 6.0F) {
/* 134 */         close = false;
/* 135 */         windows.forEach(AbstractCategory::restorePos);
/* 136 */         method_25419();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/* 143 */     if (((Boolean)ModuleManager.clickGui.blur.getValue()).booleanValue()) {
/* 144 */       method_57734(delta);
/*     */     }
/* 146 */     anyHovered = false;
/*     */     
/* 148 */     ClickGui.Image image = (ClickGui.Image)ModuleManager.clickGui.image.getValue();
/*     */     
/* 150 */     if (image != ClickGui.Image.None) {
/* 151 */       RenderSystem.setShaderTexture(0, image.file);
/*     */       
/* 153 */       Render2DEngine.renderTexture(context.method_51448(), Module.mc
/*     */           
/* 155 */           .method_22683().method_4486() - image.fileWidth * this.imageAnimation.getAnimationd(), (Module.mc
/* 156 */           .method_22683().method_4502() - image.fileHeight), image.fileWidth, image.fileHeight, 0.0F, 0.0F, image.fileWidth, image.fileHeight, image.fileWidth, image.fileHeight);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (this.closeAnimation <= 6.0F) {
/* 167 */       windows.forEach(w -> {
/*     */             w.setX(w.getX() + this.closeDirectionX * AnimationUtility.deltaTime());
/*     */             
/*     */             w.setY(w.getY() + this.closeDirectionY * AnimationUtility.deltaTime());
/*     */           });
/*     */     }
/*     */     
/* 174 */     if (Module.fullNullCheck()) {
/* 175 */       method_25420(context, mouseX, mouseY, delta);
/*     */     }
/*     */     
/* 178 */     if (ModuleManager.clickGui.scrollMode.getValue() == ClickGui.scrollModeEn.Old)
/* 179 */     { for (AbstractCategory window : windows) {
/* 180 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 264))
/* 181 */           window.setY(window.getY() + 2.0F); 
/* 182 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 265))
/* 183 */           window.setY(window.getY() - 2.0F); 
/* 184 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 262))
/* 185 */           window.setX(window.getX() + 2.0F); 
/* 186 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 263))
/* 187 */           window.setX(window.getX() - 2.0F); 
/* 188 */         if (this.scrollY != 0.0F)
/* 189 */           window.setY(window.getY() + this.scrollY); 
/*     */       }  }
/* 191 */     else { for (AbstractCategory window : windows) {
/* 192 */         if (this.scrollY != 0.0F)
/* 193 */           window.setModuleOffset(this.scrollY, mouseX, mouseY); 
/*     */       }  }
/* 195 */      this.scrollY = 0.0F;
/* 196 */     windows.forEach(w -> w.render(context, mouseX, mouseY, delta));
/*     */     
/* 198 */     if (!Objects.equals(currentDescription, "") && ((Boolean)ModuleManager.clickGui.descriptions.getValue()).booleanValue()) {
/* 199 */       Render2DEngine.drawHudBase(context.method_51448(), (mouseX + 7), (mouseY + 5), FontRenderers.sf_medium.getStringWidth(currentDescription) + 6.0F, 11.0F, 1.0F, false);
/* 200 */       FontRenderers.sf_medium.drawString(context.method_51448(), currentDescription, (mouseX + 10), (mouseY + 8), HudEditor.getColor(0).getRGB());
/* 201 */       currentDescription = "";
/*     */     } 
/*     */     
/* 204 */     if (((Boolean)ModuleManager.clickGui.tips.getValue()).booleanValue() && !close) {
/* 205 */       FontRenderers.sf_medium.drawString(context.method_51448(), 
/* 206 */           ClientSettings.isRu() ? "Щелкните левой кнопкой мыши, чтобы включить модуль.\nЩелкните правой кнопкой мыши, чтобы открыть настройки модуля.\nЩелкните колёсиком мыши, чтобы привязать модуль\nCtrl + F, чтобы начать поиск\nПерекиньте конфиг в окошко майна, чтобы загрузить его\nShift + Left Mouse Click, чтобы изменить отображение модуля в Array list\nЩелкните колёсиком мыши по слайдеру, чтобы ввести значение с клавиатуры.\nDelete + Left Mouse Click по модулю, чтобы сбросить его настройки" : 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 215 */           "Left Mouse Click to enable module\nRight Mouse Click to open module settings\nMiddle Mouse Click to bind module\nCtrl + F to start searching\nDrag n Drop config there to load\nShift + Left Mouse Click to change module visibility in Array list\nMiddle Mouse Click on slider to enter value from keyboard\nDelete + Left Mouse Click on module to reset", 5.0D, (Module.mc
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 223 */           .method_22683().method_4502() - 80), HudEditor.getColor(0).getRGB());
/*     */     }
/* 225 */     if (!HudElement.anyHovered && !anyHovered && 
/* 226 */       GLFW.glfwGetPlatform() != 393219) {
/* 227 */       GLFW.glfwSetCursor(Module.mc.method_22683().method_4490(), GLFW.glfwCreateStandardCursor(221185));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 234 */     this.scrollY += (int)(verticalAmount * 5.0D);
/* 235 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 240 */     windows.forEach(w -> {
/*     */           w.mouseClicked((int)mouseX, (int)mouseY, button);
/*     */           
/*     */           windows.forEach(());
/*     */         });
/*     */     
/* 246 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 252 */     windows.forEach(w -> w.mouseReleased((int)mouseX, (int)mouseY, button));
/* 253 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25400(char key, int modifier) {
/* 258 */     windows.forEach(w -> w.charTyped(key, modifier));
/* 259 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 264 */     windows.forEach(w -> w.keyTyped(keyCode));
/*     */     
/* 266 */     if (keyCode == 256) {
/* 267 */       if (Module.mc.field_1724 == null || !((Boolean)ModuleManager.clickGui.closeAnimation.getValue()).booleanValue()) {
/* 268 */         imageDirection = false;
/* 269 */         this.imageAnimation.reset();
/* 270 */         super.method_25404(keyCode, scanCode, modifiers);
/* 271 */         return true;
/*     */       } 
/*     */       
/* 274 */       if (close) {
/* 275 */         return true;
/*     */       }
/* 277 */       imageDirection = false;
/*     */       
/* 279 */       windows.forEach(AbstractCategory::savePos);
/*     */       
/* 281 */       this.closeDirectionX = 0.0F;
/* 282 */       this.closeDirectionY = 0.0F;
/*     */       
/* 284 */       close = true;
/* 285 */       Module.mc.field_1729.method_1612();
/*     */       
/* 287 */       this.closeAnimation = 0.0F;
/* 288 */       if (Module.mc.field_1724 != null) {
/* 289 */         this.prevYaw = Module.mc.field_1724.method_36454();
/* 290 */         this.prevPitch = Module.mc.field_1724.method_36455();
/*     */       } 
/* 292 */       return true;
/*     */     } 
/*     */     
/* 295 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\ClickGUI.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */