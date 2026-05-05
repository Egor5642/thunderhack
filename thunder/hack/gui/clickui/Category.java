/*     */ package thunder.hack.gui.clickui;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_286;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_757;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClickGui;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.impl.SearchBar;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ public class Category
/*     */   extends AbstractCategory {
/*     */   private final class_2960 ICON;
/*     */   private boolean scrollHover;
/*     */   
/*     */   public Category(Module.Category category, ArrayList<Module> features, float x, float y, float width, float height) {
/*  32 */     super(category.getName(), x, y, width, height);
/*  33 */     this.buttons = new ArrayList<>();
/*  34 */     this.ICON = class_2960.method_60655("thunderhack", "textures/gui/headers/" + (Module.Category.isCustomCategory(category) ? "stock" : category.getName().toLowerCase()) + ".png");
/*     */     
/*  36 */     if (category.getName().equals("Client")) {
/*  37 */       this.buttons.add(new SearchBar());
/*     */     }
/*  39 */     features.forEach(feature -> {
/*     */           if (!(feature instanceof thunder.hack.features.modules.client.BaritoneSettings) || ThunderHack.baritone)
/*     */             this.buttons.add(new ModuleButton(feature)); 
/*     */         });
/*     */   }
/*     */   private final List<AbstractButton> buttons; public float catHeight;
/*     */   
/*     */   public void init() {
/*  47 */     this.buttons.forEach(AbstractButton::init);
/*     */   }
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*     */     float height1;
/*  52 */     super.render(context, mouseX, mouseY, delta);
/*     */     
/*  54 */     setWidth(((Integer)ModuleManager.clickGui.moduleWidth.getValue()).intValue());
/*     */     
/*  56 */     this.scrollHover = Render2DEngine.isHovered(mouseX, mouseY, getX(), (getY() + this.height), this.width, (this.catHeight + 20.0F));
/*     */     
/*  58 */     context.method_51448().method_22903();
/*     */     
/*  60 */     boolean popStack = false;
/*     */ 
/*     */     
/*  63 */     if (ModuleManager.clickGui.scrollMode.getValue() == ClickGui.scrollModeEn.Old || getButtonsHeight() < ((Integer)ModuleManager.clickGui.catHeight.getValue()).intValue()) {
/*  64 */       height1 = (float)getButtonsHeight();
/*     */     } else {
/*  66 */       height1 = ((Integer)ModuleManager.clickGui.catHeight.getValue()).intValue();
/*     */     } 
/*  68 */     this.catHeight = AnimationUtility.fast(this.catHeight, height1, 30.0F);
/*     */     
/*  70 */     Color m1 = HudEditor.getColor(270);
/*  71 */     Color m2 = HudEditor.getColor(0);
/*  72 */     Color m3 = HudEditor.getColor(180);
/*  73 */     Color m4 = HudEditor.getColor(90);
/*     */     
/*  75 */     if (isOpen()) {
/*  76 */       Render2DEngine.drawHudBase(context.method_51448(), getX() + 3.0F, getY() + this.height - 6.0F, this.width - 6.0F, this.catHeight, 1.0F, false);
/*     */       
/*  78 */       if (ModuleManager.clickGui.scrollMode.getValue() != ClickGui.scrollModeEn.Old && getButtonsHeight() >= ((Integer)ModuleManager.clickGui.catHeight.getValue()).intValue()) {
/*  79 */         Render2DEngine.addWindow(context.method_51448(), getX() + 3.0F, getY() + this.height - 6.0F, getX() + 3.0F + this.width - 6.0F, getY() + this.height - 6.0F + ((Integer)ModuleManager.clickGui.catHeight.getValue()).intValue(), 1.0D);
/*  80 */         popStack = true;
/*     */       } 
/*     */       
/*  83 */       Render2DEngine.drawBlurredShadow(context.method_51448(), ((int)getX() + 4), (int)(getY() + this.height - 6.0F), ((int)this.width - 8), 8.0F, 7, new Color(0, 0, 0, 180));
/*  84 */       for (AbstractButton button : this.buttons) {
/*  85 */         if (button instanceof ModuleButton) { ModuleButton mb = (ModuleButton)button; if (SearchBar.listening && !mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase()))
/*     */             continue;  }
/*     */         
/*  88 */         if (popStack && ((AbstractButton)this.buttons.getFirst()).getY() + this.moduleOffset < getY() + this.height) {
/*  89 */           button.setY(getY() + this.height + this.moduleOffset);
/*     */         } else {
/*  91 */           button.setY(getY() + this.height);
/*  92 */           this.moduleOffset = 0.0F;
/*     */         } 
/*  94 */         button.setX(getX() + 2.0F);
/*  95 */         button.setWidth(this.width - 4.0F);
/*  96 */         button.setHeight(((Integer)ModuleManager.clickGui.moduleHeight.getValue()).intValue());
/*  97 */         button.render(context, mouseX, mouseY, delta);
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     if (popStack) {
/* 102 */       Render2DEngine.popWindow();
/*     */     }
/* 104 */     Render2DEngine.drawHudBase(context.method_51448(), getX() + 2.0F, getY() - 5.0F, this.width - 4.0F, this.height, 1.0F, false);
/*     */ 
/*     */     
/* 107 */     RenderSystem.setShaderTexture(0, this.ICON);
/* 108 */     RenderSystem.enableBlend();
/* 109 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 110 */     Render2DEngine.addWindow(context.method_51448(), getX() + 2.0F, getY() - 4.0F, getX() + 2.0F + this.width - 4.0F, getY() - 5.0F + this.height, 1.0D);
/* 111 */     RenderSystem.setShader(class_757::method_34543);
/* 112 */     class_287 b = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
/* 113 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 85.0F), (getY() + (this.height - 24.0F) / 2.0F), 12.0D, 12.0D, 0.0F, 0.0F, 12.0D, 12.0D, 12.0D, 12.0D, m1.darker(), m2.darker(), m3.darker(), m4.darker());
/* 114 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 75.0F), (getY() + (this.height - 34.0F) / 2.0F), 16.0D, 16.0D, 0.0F, 0.0F, 16.0D, 16.0D, 16.0D, 16.0D, m1, m2, m3, m4);
/* 115 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 65.0F), (getY() + (this.height - 20.0F) / 2.0F), 12.0D, 12.0D, 0.0F, 0.0F, 12.0D, 12.0D, 12.0D, 12.0D, m1.darker().darker(), m2.darker().darker(), m3.darker().darker(), m4.darker().darker());
/* 116 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 55.0F), (getY() + (this.height - 28.0F) / 2.0F), 6.0D, 6.0D, 0.0F, 0.0F, 6.0D, 6.0D, 6.0D, 6.0D, m1, m2, m3, m4);
/* 117 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 45.0F), (getY() + (this.height - 17.0F) / 2.0F), 17.0D, 17.0D, 0.0F, 0.0F, 17.0D, 17.0D, 17.0D, 17.0D, m1, m2, m3, m4);
/* 118 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 35.0F), (getY() + (this.height - 30.0F) / 2.0F), 15.0D, 15.0D, 0.0F, 0.0F, 15.0D, 15.0D, 15.0D, 15.0D, m1.darker().darker().darker(), m2.darker().darker().darker(), m3.darker().darker().darker(), m4.darker().darker().darker());
/* 119 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 25.0F), (getY() + (this.height - 21.0F) / 2.0F), 8.0D, 8.0D, 0.0F, 0.0F, 8.0D, 8.0D, 8.0D, 8.0D, m1, m2, m3, m4);
/* 120 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 15.0F), (getY() + (this.height - 22.0F) / 2.0F), 12.0D, 12.0D, 0.0F, 0.0F, 12.0D, 12.0D, 12.0D, 12.0D, m1.darker().darker().darker(), m2.darker().darker().darker(), m3.darker().darker().darker(), m4.darker().darker().darker());
/* 121 */     Render2DEngine.renderGradientTextureInternal(b, context.method_51448(), (getX() + 5.0F), (getY() + (this.height - 28.0F) / 2.0F), 20.0D, 20.0D, 0.0F, 0.0F, 20.0D, 20.0D, 20.0D, 20.0D, m1, m2, m3, m4);
/* 122 */     class_286.method_43433(b.method_60800());
/* 123 */     RenderSystem.disableBlend();
/* 124 */     Render2DEngine.popWindow();
/*     */ 
/*     */     
/* 127 */     Render2DEngine.drawBlurredShadow(context.method_51448(), 
/* 128 */         (int)getX() + (this.width - 4.0F) / 2.0F - FontRenderers.categories.getStringWidth(getName()) / 2.0F, (int)getY() + (int)this.height / 2.0F - 10.0F, FontRenderers.categories.getStringWidth(getName()) + 6.0F, 13.0F, 20, Render2DEngine.injectAlpha(Color.black, 170));
/*     */     
/* 130 */     FontRenderers.categories.drawCenteredString(context.method_51448(), getName(), (((int)getX() + 2) + (this.width - 4.0F) / 2.0F), ((int)getY() + (int)this.height / 2.0F - 7.0F), (new Color(-1)).getRGB());
/* 131 */     context.method_51448().method_22909();
/* 132 */     updatePosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 138 */     if (button == 1 && this.hovered) {
/* 139 */       setOpen(!isOpen());
/*     */     }
/* 141 */     super.mouseClicked(mouseX, mouseY, button);
/*     */     
/* 143 */     if (isOpen() && this.scrollHover) {
/* 144 */       this.buttons.forEach(b -> b.mouseClicked(mouseX, mouseY, button));
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 149 */     super.mouseReleased(mouseX, mouseY, button);
/* 150 */     if (isOpen()) {
/* 151 */       this.buttons.forEach(b -> b.mouseReleased(mouseX, mouseY, button));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean keyTyped(int keyCode) {
/* 156 */     if (isOpen()) {
/* 157 */       for (AbstractButton button : this.buttons) {
/* 158 */         button.keyTyped(keyCode);
/*     */       }
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 166 */     if (isOpen()) {
/* 167 */       for (AbstractButton button : this.buttons) {
/* 168 */         button.charTyped(key, keyCode);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 175 */     super.onClose();
/* 176 */     this.buttons.forEach(AbstractButton::onGuiClosed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 181 */     this.buttons.forEach(AbstractButton::tick);
/*     */   }
/*     */   
/*     */   private void updatePosition() {
/* 185 */     float offsetY = 0.0F;
/* 186 */     float openY = 0.0F;
/* 187 */     for (AbstractButton button : this.buttons) {
/* 188 */       if (button instanceof ModuleButton) { ModuleButton mb = (ModuleButton)button; if (SearchBar.listening && !mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase()))
/*     */           continue;  }
/*     */       
/* 191 */       button.setTargetOffset(offsetY);
/* 192 */       if (button instanceof ModuleButton) { ModuleButton mbutton = (ModuleButton)button;
/* 193 */         if (mbutton.isOpen()) {
/* 194 */           for (AbstractElement element : mbutton.getElements()) {
/* 195 */             if (element.isVisible())
/* 196 */               offsetY += element.getHeight(); 
/*     */           } 
/* 198 */           offsetY += 2.0F;
/*     */         }  }
/*     */       
/* 201 */       offsetY += button.getHeight() + openY;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void hudClicked(Module module) {
/* 207 */     for (AbstractButton button : this.buttons) {
/* 208 */       if (button instanceof ModuleButton) { ModuleButton mbutton = (ModuleButton)button; if (mbutton.module == module)
/* 209 */           mbutton.setOpen(true);  }
/*     */     
/*     */     } 
/*     */   }
/*     */   public double getButtonsHeight() {
/* 214 */     double height = 8.0D;
/* 215 */     for (AbstractButton button : this.buttons) {
/* 216 */       if (button instanceof ModuleButton) { ModuleButton mb = (ModuleButton)button; if (SearchBar.listening && !mb.module.getName().toLowerCase().contains(SearchBar.moduleName.toLowerCase()))
/*     */           continue;  }
/*     */       
/* 219 */       if (button instanceof ModuleButton) { ModuleButton mbutton = (ModuleButton)button;
/* 220 */         if (mbutton.isOpen())
/* 221 */           height += 2.0D; 
/* 222 */         height += mbutton.getElementsHeight(); }
/*     */ 
/*     */       
/* 225 */       height += button.getHeight();
/*     */     } 
/* 227 */     return height;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\Category.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */