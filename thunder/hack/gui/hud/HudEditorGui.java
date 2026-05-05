/*     */ package thunder.hack.gui.hud;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClickGui;
/*     */ import thunder.hack.gui.clickui.AbstractCategory;
/*     */ import thunder.hack.gui.clickui.Category;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HudEditorGui
/*     */   extends class_437
/*     */ {
/*     */   public static HudElement currentlyDragging;
/*     */   private final List<AbstractCategory> windows;
/*  26 */   private static HudEditorGui instance = new HudEditorGui();
/*     */   
/*     */   private boolean firstOpen;
/*     */   private double dWheel;
/*     */   
/*     */   public HudEditorGui() {
/*  32 */     super(class_2561.method_30163("HudEditorGui"));
/*  33 */     this.windows = Lists.newArrayList();
/*  34 */     this.firstOpen = true;
/*     */     
/*  36 */     setInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void method_25426() {
/*  41 */     if (this.firstOpen) {
/*  42 */       Category window = new Category(Module.Category.HUD, Managers.MODULE.getModulesByCategory(Module.Category.HUD), Module.mc.method_22683().method_4486() / 2.0F - 50.0F, 20.0F, 100.0F, 18.0F);
/*  43 */       window.setOpen(true);
/*  44 */       this.windows.add(window);
/*  45 */       this.firstOpen = false;
/*     */     } 
/*  47 */     this.windows.forEach(AbstractCategory::init);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25421() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/*  57 */     ClickGUI.anyHovered = false;
/*     */     
/*  59 */     if (ModuleManager.clickGui.scrollMode.getValue() == ClickGui.scrollModeEn.Old)
/*  60 */     { for (AbstractCategory window : this.windows) {
/*  61 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 264))
/*  62 */           window.setY(window.getY() + 2.0F); 
/*  63 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 265))
/*  64 */           window.setY(window.getY() - 2.0F); 
/*  65 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 262))
/*  66 */           window.setX(window.getX() + 2.0F); 
/*  67 */         if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 263))
/*  68 */           window.setX(window.getX() - 2.0F); 
/*  69 */         if (this.dWheel != 0.0D)
/*  70 */           window.setY((float)(window.getY() + this.dWheel)); 
/*     */       }  }
/*  72 */     else { for (AbstractCategory window : this.windows) {
/*  73 */         if (this.dWheel != 0.0D)
/*  74 */           window.setModuleOffset((float)this.dWheel, mouseX, mouseY); 
/*     */       }  }
/*  76 */      this.dWheel = 0.0D;
/*     */     
/*  78 */     for (AbstractCategory window : this.windows) {
/*  79 */       window.render(context, mouseX, mouseY, delta);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/*  85 */     this.dWheel = (int)(verticalAmount * 5.0D);
/*  86 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/*  91 */     this.windows.forEach(w -> {
/*     */           w.mouseClicked((int)mouseX, (int)mouseY, button);
/*     */ 
/*     */           
/*     */           this.windows.forEach(());
/*     */         });
/*     */ 
/*     */     
/*  99 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 104 */     this.windows.forEach(w -> w.mouseReleased((int)mouseX, (int)mouseY, button));
/* 105 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 111 */     this.windows.forEach(w -> w.keyTyped(keyCode));
/*     */     
/* 113 */     if (keyCode == 256) {
/* 114 */       super.method_25404(keyCode, scanCode, modifiers);
/* 115 */       return true;
/*     */     } 
/*     */     
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25432() {
/* 123 */     ThunderHack.EVENT_BUS.unsubscribe(this);
/*     */   }
/*     */   
/*     */   public void hudClicked(Module module) {
/* 127 */     for (AbstractCategory window : this.windows) {
/* 128 */       window.hudClicked(module);
/*     */     }
/*     */   }
/*     */   
/*     */   public static HudEditorGui getInstance() {
/* 133 */     if (instance == null) {
/* 134 */       instance = new HudEditorGui();
/*     */     }
/* 136 */     return instance;
/*     */   }
/*     */   
/*     */   public static HudEditorGui getHudGui() {
/* 140 */     return getInstance();
/*     */   }
/*     */   
/*     */   private void setInstance() {
/* 144 */     instance = this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\hud\HudEditorGui.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */