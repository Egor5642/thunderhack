/*     */ package thunder.hack.gui.clickui;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1074;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_7833;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.hud.impl.TargetHud;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClickGui;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.impl.BindElement;
/*     */ import thunder.hack.gui.clickui.impl.BooleanElement;
/*     */ import thunder.hack.gui.clickui.impl.BooleanParentElement;
/*     */ import thunder.hack.gui.clickui.impl.ColorPickerElement;
/*     */ import thunder.hack.gui.clickui.impl.ItemSelectElement;
/*     */ import thunder.hack.gui.clickui.impl.ModeElement;
/*     */ import thunder.hack.gui.clickui.impl.ParentElement;
/*     */ import thunder.hack.gui.clickui.impl.SliderElement;
/*     */ import thunder.hack.gui.clickui.impl.StringElement;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.gui.misc.DialogScreen;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ import thunder.hack.utility.render.animation.GearAnimation;
/*     */ 
/*     */ public class ModuleButton extends AbstractButton {
/*     */   private final List<AbstractElement> elements;
/*  44 */   float category_animation = 0.0F; public final Module module; private boolean open; private boolean hovered; private boolean prevHovered; private float animation;
/*     */   private float animation2;
/*     */   int ticksOpened;
/*  47 */   private final GearAnimation gearAnimation = new GearAnimation();
/*     */   
/*     */   private boolean binding = false;
/*     */   private boolean holdbind = false;
/*     */   
/*     */   public ModuleButton(Module module) {
/*  53 */     this.module = module;
/*  54 */     this.elements = new ArrayList<>();
/*     */     
/*  56 */     for (Setting setting : module.getSettings()) {
/*     */       
/*  58 */       if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled") && !setting.getName().equals("Drawn")) {
/*  59 */         this.elements.add(new BooleanElement(setting)); continue;
/*  60 */       }  if (setting.getValue() instanceof ColorSetting) {
/*  61 */         this.elements.add(new ColorPickerElement(setting)); continue;
/*  62 */       }  if (setting.getValue() instanceof BooleanSettingGroup) {
/*  63 */         this.elements.add(new BooleanParentElement(setting)); continue;
/*  64 */       }  if (setting.isNumberSetting() && setting.hasRestriction()) {
/*  65 */         this.elements.add(new SliderElement(setting)); continue;
/*  66 */       }  if (setting.getValue() instanceof thunder.hack.setting.impl.ItemSelectSetting) {
/*  67 */         this.elements.add(new ItemSelectElement(setting)); continue;
/*  68 */       }  if (setting.getValue() instanceof thunder.hack.setting.impl.SettingGroup) {
/*  69 */         this.elements.add(new ParentElement(setting)); continue;
/*  70 */       }  if (setting.isEnumSetting() && !(setting.getValue() instanceof thunder.hack.setting.impl.PositionSetting)) {
/*  71 */         this.elements.add(new ModeElement(setting)); continue;
/*  72 */       }  if (setting.getValue() instanceof thunder.hack.setting.impl.Bind && !setting.getName().equals("Keybind")) {
/*  73 */         this.elements.add(new BindElement(setting)); continue;
/*  74 */       }  if ((setting.getValue() instanceof String || setting.getValue() instanceof Character) && !setting.getName().equalsIgnoreCase("displayName")) {
/*  75 */         this.elements.add(new StringElement(setting));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init() {
/*  81 */     this.elements.forEach(AbstractElement::init);
/*     */   }
/*     */   
/*     */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/*  85 */     this.hovered = Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
/*  86 */     this.animation = AnimationUtility.fast(this.animation, this.module.isEnabled() ? 1.0F : 0.0F, 8.0F);
/*  87 */     this.animation2 = AnimationUtility.fast(this.animation2, 1.0F, 10.0F);
/*     */     
/*  89 */     if (this.hovered) {
/*  90 */       if (!this.prevHovered)
/*  91 */         Managers.SOUND.playScroll(); 
/*  92 */       ClickGUI.currentDescription = class_1074.method_4662(this.module.getDescription(), new Object[0]);
/*     */     } 
/*     */     
/*  95 */     this.prevHovered = this.hovered;
/*     */     
/*  97 */     float ix = this.x + 5.0F;
/*  98 */     float iy = this.y + this.height / 2.0F - 3.0F;
/*     */     
/* 100 */     this.offsetY = AnimationUtility.fast(this.offsetY, this.target_offset, 20.0F);
/*     */     
/* 102 */     float offsetY = 0.0F;
/*     */     
/* 104 */     if (isOpen()) {
/* 105 */       Render2DEngine.drawGuiBase(context.method_51448(), this.x + 4.0F, this.y + 2.0F, this.width - 8.0F, this.height + (float)getElementsHeight(), 1.0F, 0.0F);
/* 106 */       Render2DEngine.addWindow(context.method_51448(), new Render2DEngine.Rectangle(this.x + 1.0F, this.y + this.height - 2.0F, this.width + this.x - 2.0F, (float)((this.height + this.y + 1.0F) + getElementsHeight())));
/*     */       
/* 108 */       if (Module.mc.field_1724 != null) if (((BooleanSettingGroup)ClickGui.gear.getValue()).isEnabled()) {
/* 109 */           Render2DEngine.addWindow(context.method_51448(), new Render2DEngine.Rectangle(this.x, this.y + this.height + 1.0F, this.width + this.x + 6.0F, (float)((this.height + this.y + 1.0F) + getElementsHeight())));
/* 110 */           float px = this.x + 4.0F + (this.width - 8.0F) / 2.0F;
/* 111 */           float py = this.y + 12.0F + (this.height + (float)getElementsHeight()) / 2.0F;
/* 112 */           int gScale = ((Integer)ModuleManager.clickGui.gearScale.getValue()).intValue();
/* 113 */           context.method_51448().method_22903();
/* 114 */           context.method_51448().method_46416(px, py, 0.0F);
/* 115 */           context.method_51448().method_22907(class_7833.field_40718.rotationDegrees(this.gearAnimation.getValue()));
/* 116 */           context.method_51448().method_46416(-px, -py, 0.0F);
/* 117 */           RenderSystem.setShaderTexture(0, TextureStorage.Gear);
/* 118 */           RenderSystem.enableBlend();
/* 119 */           RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 120 */           Render2DEngine.renderGradientTexture(context.method_51448(), (px - gScale / 2.0F), (py - gScale / 2.0F), gScale, gScale, 0.0F, 0.0F, gScale, gScale, gScale, gScale, 
/* 121 */               Render2DEngine.injectAlpha(HudEditor.getColor(270).darker(), 110), 
/* 122 */               Render2DEngine.injectAlpha(HudEditor.getColor(0).darker(), 110), 
/* 123 */               Render2DEngine.injectAlpha(HudEditor.getColor(180).darker(), 110), 
/* 124 */               Render2DEngine.injectAlpha(HudEditor.getColor(90).darker(), 110));
/* 125 */           RenderSystem.disableBlend();
/* 126 */           context.method_51448().method_46416(px, py, 0.0F);
/* 127 */           context.method_51448().method_22907(class_7833.field_40718.rotationDegrees((float)Render2DEngine.interpolate((Module.mc.field_1724.field_6012 - 1), Module.mc.field_1724.field_6012, Render3DEngine.getTickDelta()) * -4.0F));
/* 128 */           context.method_51448().method_46416(-px, -py, 0.0F);
/* 129 */           context.method_51448().method_22909();
/* 130 */           Render2DEngine.popWindow();
/*     */         } 
/*     */       
/* 133 */       if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + 4.0F), (this.y + this.height - 12.0F), (this.width - 8.0F), (this.height + (float)getElementsHeight()))) {
/* 134 */         Render2DEngine.drawBlurredShadow(context.method_51448(), (mouseX - 10), (mouseY - 10), 20.0F, 20.0F, 40, HudEditor.getColor(270));
/*     */       }
/*     */       
/* 137 */       for (AbstractElement element : this.elements) {
/* 138 */         if (!element.isVisible()) {
/*     */           continue;
/*     */         }
/* 141 */         element.setOffsetY(offsetY);
/* 142 */         element.setX(this.x);
/* 143 */         element.setY(this.y + this.height + 2.0F);
/* 144 */         element.setWidth(this.width);
/* 145 */         element.setHeight(13.0F);
/*     */         
/* 147 */         if (element instanceof ColorPickerElement) { ColorPickerElement picker = (ColorPickerElement)element;
/* 148 */           element.setHeight(picker.getHeight()); }
/*     */         
/* 150 */         else if (element instanceof SliderElement)
/* 151 */         { element.setHeight(18.0F); }
/*     */         
/* 153 */         if (element instanceof ModeElement) { ModeElement combobox = (ModeElement)element;
/* 154 */           combobox.setWHeight(13.0D);
/* 155 */           if (combobox.isOpen())
/* 156 */           { element.setHeight((13 + (combobox.getSetting().getModes()).length * 12)); }
/* 157 */           else { element.setHeight(13.0F); }
/*     */            }
/* 159 */          offsetY += element.getHeight();
/*     */       } 
/*     */       
/* 162 */       context.method_51448().method_22903();
/* 163 */       TargetHud.sizeAnimation(context.method_51448(), (this.x + this.width / 2.0F + 6.0F), (this.y + this.height / 2.0F - 12.0F), (this.ticksOpened < 5) ? Math.clamp(this.category_animation / offsetY, 0.0F, 1.0F) : 1.0D);
/* 164 */       this.elements.forEach(e -> {
/*     */             if (e.isVisible())
/*     */               e.render(context, mouseX, mouseY, delta); 
/*     */           });
/* 168 */       context.method_51448().method_22909();
/*     */       
/* 170 */       Render2DEngine.drawBlurredShadow(context.method_51448(), this.x + 3.0F, this.y + this.height, this.width - 6.0F, 3.0F, 13, HudEditor.getColor(1));
/* 171 */       if (!this.module.isEnabled())
/* 172 */         Render2DEngine.draw2DGradientRect(context.method_51448(), this.x + 4.0F, this.y + this.height - 1.0F, this.x + 3.0F + this.width - 7.0F, 3.0F + this.y + this.height, Render2DEngine.applyOpacity(HudEditor.getColor(0), 0.0F), HudEditor.getColor(0), Render2DEngine.applyOpacity(HudEditor.getColor(90), 0.0F), HudEditor.getColor(90)); 
/* 173 */       Render2DEngine.popWindow();
/*     */     }
/* 175 */     else if (this.hovered) {
/* 176 */       Render2DEngine.addWindow(context.method_51448(), this.x + 1.0F, this.y, this.x + this.width - 2.0F, this.y + this.height, 1.0D);
/* 177 */       Render2DEngine.drawBlurredShadow(context.method_51448(), (mouseX - 10), (mouseY - 10), 20.0F, 20.0F, 35, HudEditor.getColor(270));
/* 178 */       Render2DEngine.popWindow();
/*     */     } 
/*     */ 
/*     */     
/* 182 */     this.category_animation = AnimationUtility.fast(this.category_animation, offsetY, 20.0F);
/*     */     
/* 184 */     if (this.animation < 0.05D) {
/* 185 */       Render2DEngine.drawRect(context.method_51448(), this.x + 4.0F, this.y + 1.0F, this.width - 8.0F, this.height - 2.0F, Render2DEngine.applyOpacity(((ColorSetting)HudEditor.plateColor.getValue()).getColorObject().darker(), 0.15F));
/*     */     } else {
/*     */       
/* 188 */       switch ((ClickGui.Gradient)ModuleManager.clickGui.gradientMode.getValue()) {
/*     */         case both:
/* 190 */           Render2DEngine.draw2DGradientRect(context.method_51448(), this.x + 4.0F, this.y + 1.0F, this.x + 4.0F + this.width - 8.0F, this.y + 1.0F + this.height - 2.0F, 
/* 191 */               Render2DEngine.applyOpacity(HudEditor.getColor(270), this.animation * 2.0F), 
/* 192 */               Render2DEngine.applyOpacity(HudEditor.getColor(0), this.animation * 2.0F), 
/* 193 */               Render2DEngine.applyOpacity(HudEditor.getColor(180), this.animation), 
/* 194 */               Render2DEngine.applyOpacity(HudEditor.getColor(90), this.animation));
/*     */           break;
/*     */         case UpsideDown:
/* 197 */           Render2DEngine.draw2DGradientRect(context.method_51448(), this.x + 4.0F, this.y + 1.0F, this.x + 4.0F + this.width - 8.0F, this.y + 1.0F + this.height - 2.0F, 
/* 198 */               Render2DEngine.applyOpacity(HudEditor.getColor(270), this.animation * 2.0F), 
/* 199 */               Render2DEngine.applyOpacity(HudEditor.getColor(0), this.animation * 2.0F), 
/* 200 */               Render2DEngine.applyOpacity(HudEditor.getColor(270), this.animation), 
/* 201 */               Render2DEngine.applyOpacity(HudEditor.getColor(0), this.animation));
/*     */           break;
/*     */         case LeftToRight:
/* 204 */           Render2DEngine.draw2DGradientRect(context.method_51448(), this.x + 4.0F, this.y + 1.0F, this.x + 4.0F + this.width - 8.0F, this.y + 1.0F + this.height - 2.0F, 
/* 205 */               Render2DEngine.applyOpacity(HudEditor.getColor(270), this.animation * 2.0F), 
/* 206 */               Render2DEngine.applyOpacity(HudEditor.getColor(270), this.animation * 2.0F), 
/* 207 */               Render2DEngine.applyOpacity(HudEditor.getColor(0), this.animation), 
/* 208 */               Render2DEngine.applyOpacity(HudEditor.getColor(0), this.animation));
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 213 */     if (!this.module.getBind().getBind().equalsIgnoreCase("none") && !this.binding) {
/* 214 */       FontRenderers.sf_medium_modules.drawString(context.method_51448(), getSbind(), (this.x + this.width - 11.0F - FontRenderers.sf_medium_modules.getStringWidth(getSbind())), (this.y + 6.0F), this.module.isEnabled() ? ((ColorSetting)HudEditor.textColor2.getValue()).getColor() : ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/*     */     }
/* 216 */     if (this.binding) {
/* 217 */       FontRenderers.sf_medium_modules.drawString(context.method_51448(), this.holdbind ? (String.valueOf(class_124.field_1080) + "Toggle / " + String.valueOf(class_124.field_1080) + "Hold") : (String.valueOf(class_124.field_1070) + "Toggle " + String.valueOf(class_124.field_1070) + "/ Hold"), (this.x + this.width - 11.0F - FontRenderers.sf_medium_modules.getStringWidth("Toggle/Hold")), (iy + 2.0F), Render2DEngine.applyOpacity(Color.WHITE.getRGB(), this.animation2));
/*     */     }
/* 219 */     if (this.hovered && class_3675.method_15987(Module.mc.method_22683().method_4490(), 340)) {
/* 220 */       FontRenderers.sf_medium_modules.drawString(context.method_51448(), "Drawn " + (this.module.isDrawn() ? (String.valueOf(class_124.field_1060) + "TRUE") : (String.valueOf(class_124.field_1061) + "FALSE")), (ix + 1.0F), (iy + 2.0F), this.module.isEnabled() ? ((ColorSetting)HudEditor.textColor2.getValue()).getColor() : ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/*     */     }
/* 222 */     else if (this.binding) {
/* 223 */       FontRenderers.sf_medium_modules.drawString(context.method_51448(), "PressKey", ix, (iy + 2.0F), this.module.isEnabled() ? Render2DEngine.applyOpacity(((ColorSetting)HudEditor.textColor2.getValue()).getColor(), this.animation2) : Render2DEngine.applyOpacity(((ColorSetting)HudEditor.textColor.getValue()).getColor(), this.animation2));
/*     */     }
/* 225 */     else if (ModuleManager.clickGui.textSide.getValue() == ClickGui.TextSide.Left) {
/* 226 */       FontRenderers.sf_medium_modules.drawString(context.method_51448(), this.module.getName(), (ix + 2.0F), (iy + 2.0F), this.module.isEnabled() ? ((ColorSetting)HudEditor.textColor2.getValue()).getColor() : ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/*     */     } else {
/* 228 */       FontRenderers.sf_medium_modules.drawCenteredString(context.method_51448(), this.module.getName(), (ix + getWidth() / 2.0F - 4.0F), (iy + 2.0F), this.module.isEnabled() ? ((ColorSetting)HudEditor.textColor2.getValue()).getColor() : ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private String getSbind() {
/* 235 */     String sbind = this.module.getBind().getBind();
/* 236 */     if (sbind.equals("LEFT_CONTROL")) {
/* 237 */       sbind = "LCtrl";
/*     */     }
/* 239 */     if (sbind.equals("RIGHT_CONTROL")) {
/* 240 */       sbind = "RCtrl";
/*     */     }
/* 242 */     if (sbind.equals("LEFT_SHIFT")) {
/* 243 */       sbind = "LShift";
/*     */     }
/* 245 */     if (sbind.equals("RIGHT_SHIFT")) {
/* 246 */       sbind = "RShift";
/*     */     }
/* 248 */     if (sbind.equals("LEFT_ALT")) {
/* 249 */       sbind = "LAlt";
/*     */     }
/* 251 */     if (sbind.equals("RIGHT_ALT")) {
/* 252 */       sbind = "RAlt";
/*     */     }
/* 254 */     return sbind;
/*     */   }
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 258 */     if (this.binding) {
/* 259 */       if (mouseX > this.x + 56.0F && mouseX < this.x + 67.0F && mouseY > this.y && mouseY < this.y + this.height) {
/* 260 */         this.holdbind = false;
/* 261 */         this.module.getBind().setHold(false);
/*     */         return;
/*     */       } 
/* 264 */       if (mouseX > this.x + 78.0F && mouseX < this.x + 88.0F && mouseY > this.y && mouseY < this.y + this.height) {
/* 265 */         this.holdbind = true;
/* 266 */         this.module.getBind().setHold(true);
/*     */         return;
/*     */       } 
/* 269 */       this.module.setBind(button, true, this.holdbind);
/* 270 */       this.binding = false;
/*     */     } 
/*     */     
/* 273 */     if (this.hovered) {
/* 274 */       if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 340) && button == 0) {
/* 275 */         this.module.setDrawn(!this.module.isDrawn());
/*     */         
/*     */         return;
/*     */       } 
/* 279 */       if (class_3675.method_15987(Module.mc.method_22683().method_4490(), 261) && button == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 285 */         DialogScreen dialogScreen = new DialogScreen(TextureStorage.questionPic, ClientSettings.isRu() ? "Сброс модуля" : "Reset module", ClientSettings.isRu() ? ("Ты действительно хочешь сбросить " + this.module.getName() + "?") : ("Are you sure you want to reset " + this.module.getName() + "?"), ClientSettings.isRu() ? "Да" : "Yes", ClientSettings.isRu() ? "Нет" : "No", () -> {
/*     */               if (this.module.isEnabled())
/*     */                 this.module.disable("reseting");  for (Setting s : this.module.getSettings()) {
/*     */                 Object patt0$temp = s.getValue(); if (patt0$temp instanceof ColorSetting) {
/*     */                   ColorSetting cs = (ColorSetting)patt0$temp;
/*     */                   cs.setDefault();
/*     */                   continue;
/*     */                 } 
/*     */                 s.setValue(s.getDefaultValue());
/*     */               } 
/*     */               Module.mc.method_1507(null);
/*     */             }() -> Module.mc.method_1507(null));
/* 297 */         Module.mc.method_1507((class_437)dialogScreen);
/*     */       } 
/*     */       
/* 300 */       if (button == 0) {
/* 301 */         if (this.module.isToggleable())
/* 302 */           this.module.toggle(); 
/* 303 */       } else if (button == 1 && this.module.getSettings().size() > 3) {
/* 304 */         setOpen(!isOpen());
/*     */         
/* 306 */         if (this.open) { Managers.SOUND.playSwipeIn(); }
/* 307 */         else { Managers.SOUND.playSwipeOut(); }
/*     */         
/* 309 */         this.animation = 0.5F;
/* 310 */       } else if (button == 2) {
/* 311 */         this.animation2 = 0.0F;
/* 312 */         this.binding = !this.binding;
/*     */       } 
/*     */     } 
/*     */     
/* 316 */     if (this.open)
/* 317 */       this.elements.forEach(element -> {
/*     */             if (element.isVisible())
/*     */               element.mouseClicked(mouseX, mouseY, button); 
/*     */           }); 
/*     */   }
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int button) {
/* 324 */     if (isOpen())
/* 325 */       this.elements.forEach(element -> element.mouseReleased(mouseX, mouseY, button)); 
/*     */   }
/*     */   
/*     */   public void charTyped(char key, int keyCode) {
/* 329 */     if (isOpen())
/* 330 */       for (AbstractElement element : this.elements) {
/* 331 */         element.charTyped(key, keyCode);
/*     */       } 
/*     */   }
/*     */   
/*     */   public void keyTyped(int keyCode) {
/* 336 */     if (isOpen()) {
/* 337 */       for (AbstractElement element : this.elements) {
/* 338 */         element.keyTyped(keyCode);
/*     */       }
/*     */     }
/* 341 */     if (this.binding) {
/* 342 */       if (keyCode == 256 || keyCode == 261) {
/* 343 */         this.module.setBind(-1, false, this.holdbind);
/* 344 */         Command.sendMessage((ClientSettings.isRu() ? "Удален бинд с модуля " : "Removed bind from ") + (ClientSettings.isRu() ? "Удален бинд с модуля " : "Removed bind from "));
/*     */       } else {
/* 346 */         this.module.setBind(keyCode, false, this.holdbind);
/* 347 */         Command.sendMessage(this.module.getName() + this.module.getName() + (ClientSettings.isRu() ? " бинд изменен на " : " bind changed to "));
/*     */       } 
/* 349 */       this.binding = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onGuiClosed() {
/* 354 */     this.elements.forEach(AbstractElement::onClose);
/*     */   }
/*     */   
/*     */   public List<AbstractElement> getElements() {
/* 358 */     return this.elements;
/*     */   }
/*     */   
/*     */   public double getElementsHeight() {
/* 362 */     return this.category_animation;
/*     */   }
/*     */   
/*     */   public double interp(double d, double d2, float d3) {
/* 366 */     return d2 + (d - d2) * d3;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 370 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/* 374 */     this.open = open;
/*     */   }
/*     */   
/*     */   public void tick() {
/* 378 */     if (isOpen()) {
/* 379 */       this.gearAnimation.tick();
/* 380 */       this.ticksOpened++;
/*     */     } else {
/* 382 */       this.ticksOpened = 0;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\ModuleButton.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */