/*     */ package thunder.hack.gui.windows;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ public class WindowsScreen
/*     */   extends class_437
/*     */ {
/*  24 */   private List<WindowBase> windows = new ArrayList<>();
/*     */   public static WindowBase lastClickedWindow;
/*     */   public static WindowBase draggingWindow;
/*  27 */   private static final class_2960 clickGuiIcon = class_2960.method_60655("thunderhack", "textures/gui/elements/clickgui.png");
/*     */   
/*     */   public WindowsScreen(WindowBase... windows) {
/*  30 */     super(class_2561.method_30163("THWindows"));
/*  31 */     this.windows.clear();
/*  32 */     lastClickedWindow = null;
/*  33 */     this.windows = Arrays.<WindowBase>stream(windows).toList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
/*  40 */     if (Module.fullNullCheck()) {
/*  41 */       method_25420(context, mouseX, mouseY, delta);
/*     */     }
/*  43 */     class_4587 matrices = context.method_51448();
/*  44 */     int i = IManager.mc.method_22683().method_4486() / 2;
/*     */     
/*  46 */     float offset = this.windows.size() * 20.0F / -2.0F - 23.0F;
/*     */     
/*  48 */     Render2DEngine.drawHudBase(matrices, i + offset - 1.5F, (IManager.mc.method_22683().method_4502() - 25), this.windows.size() * 20.0F + 23.0F, 19.0F, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*     */     
/*  50 */     RenderSystem.enableBlend();
/*  51 */     RenderSystem.defaultBlendFunc();
/*  52 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, (i + offset + 1.0F), (IManager.mc.method_22683().method_4502() - 23), 15.0D, 15.0D) ? 0.95F : 0.7F);
/*  53 */     context.method_25293(clickGuiIcon, (int)(i + offset) + 1, IManager.mc.method_22683().method_4502() - 23, 15, 15, 0.0F, 0.0F, 15, 15, 15, 15);
/*  54 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*  55 */     RenderSystem.disableBlend();
/*     */     
/*  57 */     Render2DEngine.drawLine(i + offset + 20.0F, (IManager.mc.method_22683().method_4502() - 23), i + offset + 20.0F, (IManager.mc.method_22683().method_4502() - 9), Color.GRAY.getRGB());
/*     */     
/*  59 */     offset += 23.0F;
/*  60 */     for (WindowBase w : this.windows) {
/*     */       
/*  62 */       Color c = Render2DEngine.isHovered(mouseX, mouseY, (i + offset), (IManager.mc.method_22683().method_4502() - 24), 17.0D, 17.0D) ? new Color(2083467055, true) : (!w.isVisible() ? new Color(2082348574, true) : new Color(2084256571, true));
/*  63 */       Render2DEngine.drawRect(matrices, i + offset, (IManager.mc.method_22683().method_4502() - 24), 17.0F, 17.0F, ((Float)HudEditor.hudRound.getValue()).floatValue(), 0.7F, c, c, c, c);
/*  64 */       RenderSystem.enableBlend();
/*  65 */       RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/*  66 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, Render2DEngine.isHovered(mouseX, mouseY, (i + offset + 1.0F), (IManager.mc.method_22683().method_4502() - 23), 15.0D, 15.0D) ? 0.95F : 0.7F);
/*  67 */       context.method_25293((w.getIcon() != null) ? w.getIcon() : TextureStorage.configIcon, (int)(i + offset) + 3, IManager.mc.method_22683().method_4502() - 21, 11, 11, 0.0F, 0.0F, 11, 11, 11, 11);
/*  68 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*  69 */       RenderSystem.disableBlend();
/*  70 */       offset += 20.0F;
/*     */     } 
/*     */     
/*  73 */     this.windows.stream().filter(WindowBase::isVisible).forEach(w -> {
/*     */           if (w != lastClickedWindow) {
/*     */             w.render(context, mouseX, mouseY);
/*     */           }
/*     */         });
/*  78 */     if (lastClickedWindow != null && lastClickedWindow.isVisible()) {
/*  79 */       lastClickedWindow.render(context, mouseX, mouseY);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean method_25406(double mouseX, double mouseY, int button) {
/*  84 */     this.windows.forEach(w -> w.mouseReleased(mouseX, mouseY, button));
/*  85 */     return super.method_25406(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25402(double mouseX, double mouseY, int button) {
/*  90 */     this.windows.stream().filter(WindowBase::isVisible).forEach(w -> w.mouseClicked(mouseX, mouseY, button));
/*     */     
/*  92 */     int i = IManager.mc.method_22683().method_4486() / 2;
/*  93 */     float offset = this.windows.size() * 20.0F / -2.0F - 23.0F;
/*     */     
/*  95 */     if (Render2DEngine.isHovered(mouseX, mouseY, (i + offset + 1.0F), (IManager.mc.method_22683().method_4502() - 23), 15.0D, 15.0D)) {
/*  96 */       IManager.mc.method_1507((class_437)ClickGUI.getClickGui());
/*     */     }
/*  98 */     offset += 23.0F;
/*  99 */     for (WindowBase w : this.windows) {
/* 100 */       if (Render2DEngine.isHovered(mouseX, mouseY, (i + offset), (IManager.mc.method_22683().method_4502() - 24), 17.0D, 17.0D))
/* 101 */         w.setVisible(!w.isVisible()); 
/* 102 */       offset += 20.0F;
/*     */     } 
/*     */     
/* 105 */     return super.method_25402(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
/* 110 */     this.windows.stream().filter(WindowBase::isVisible).forEach(w -> w.keyPressed(keyCode, scanCode, modifiers));
/* 111 */     return super.method_25404(keyCode, scanCode, modifiers);
/*     */   }
/*     */   
/*     */   public boolean method_25400(char key, int keyCode) {
/* 115 */     this.windows.stream().filter(WindowBase::isVisible).forEach(w -> w.charTyped(key, keyCode));
/* 116 */     return super.method_25400(key, keyCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
/* 121 */     this.windows.stream().filter(WindowBase::isVisible).forEach(w -> w.mouseScrolled((int)(verticalAmount * 5.0D)));
/* 122 */     return super.method_25401(mouseX, mouseY, horizontalAmount, verticalAmount);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\windows\WindowsScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */