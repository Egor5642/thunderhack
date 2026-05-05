/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_7833;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.clickui.ClickGUI;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class BooleanParentElement extends AbstractElement {
/*    */   private final Setting<BooleanSettingGroup> parentSetting;
/*    */   float animation;
/*    */   float arrowAnimation;
/*    */   
/*    */   public BooleanParentElement(Setting<BooleanSettingGroup> setting) {
/* 26 */     super(setting);
/* 27 */     this.parentSetting = setting;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 34 */     super.render(context, mouseX, mouseY, delta);
/*    */     
/* 36 */     class_4587 matrixStack = context.method_51448();
/*    */     
/* 38 */     float tx = this.x + this.width - 11.0F;
/* 39 */     float ty = this.y + 7.5F;
/*    */     
/* 41 */     this.arrowAnimation = AnimationUtility.fast(this.arrowAnimation, ((BooleanSettingGroup)getParentSetting().getValue()).isExtended() ? 0.0F : 1.0F, 15.0F);
/*    */     
/* 43 */     matrixStack.method_22903();
/* 44 */     matrixStack.method_46416(tx, ty, 0.0F);
/* 45 */     matrixStack.method_22907(class_7833.field_40718.rotationDegrees(-180.0F * this.arrowAnimation));
/* 46 */     matrixStack.method_46416(-tx, -ty, 0.0F);
/* 47 */     matrixStack.method_46416(this.x + this.width - 14.0F, this.y + 4.5F, 0.0F);
/* 48 */     context.method_25290(TextureStorage.guiArrow, 0, 0, 0.0F, 0.0F, 6, 6, 6, 6);
/* 49 */     matrixStack.method_46416(-(this.x + this.width - 14.0F), -(this.y + 4.5F), 0.0F);
/* 50 */     matrixStack.method_22909();
/*    */     
/* 52 */     FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), (this.x + 6.0F), (this.y + this.height / 2.0F - 1.0F), (new Color(-1)).getRGB());
/* 53 */     this.animation = AnimationUtility.fast(this.animation, ((BooleanSettingGroup)getParentSetting().getValue()).isEnabled() ? 1.0F : 0.0F, 15.0F);
/* 54 */     float paddingX = 7.0F * this.animation;
/* 55 */     Color color = HudEditor.getColor(0);
/* 56 */     Render2DEngine.drawRound(context.method_51448(), this.x + this.width - 36.0F, this.y + this.height / 2.0F - 4.0F, 15.0F, 8.0F, 1.0F, (paddingX > 4.0F) ? color : new Color(687865855, true));
/* 57 */     Render2DEngine.drawRound(context.method_51448(), this.x + this.width - 35.0F + paddingX, this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 1.0F, new Color(-1));
/*    */     
/* 59 */     if (7.0F * this.animation > 4.0F) {
/* 60 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), "v", (this.x + this.width - 34.0F), (this.y + this.height / 2.0F - 2.0F), (new Color(-1)).getRGB());
/*    */     } else {
/* 62 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), "x", (this.x + this.width - 27.0F), (this.y + this.height / 2.0F - 2.0F), (new Color(-1)).getRGB());
/*    */     } 
/*    */     
/* 65 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width - 36.0F), (this.y + this.height / 2.0F - 4.0F), 15.0D, 8.0D)) {
/* 66 */       if (GLFW.glfwGetPlatform() != 393219) {
/* 67 */         GLFW.glfwSetCursor(IManager.mc.method_22683().method_4490(), 
/* 68 */             GLFW.glfwCreateStandardCursor(221188));
/*    */       }
/* 70 */       ClickGUI.anyHovered = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 76 */     if (this.hovered) {
/* 77 */       if (button == 0) {
/* 78 */         ((BooleanSettingGroup)getParentSetting().getValue()).setEnabled(!((BooleanSettingGroup)getParentSetting().getValue()).isEnabled());
/* 79 */         Managers.SOUND.playBoolean();
/*    */       } else {
/* 81 */         ((BooleanSettingGroup)getParentSetting().getValue()).setExtended(!((BooleanSettingGroup)getParentSetting().getValue()).isExtended());
/* 82 */         if (((BooleanSettingGroup)getParentSetting().getValue()).isExtended()) {
/* 83 */           Managers.SOUND.playSwipeIn();
/*    */         } else {
/* 85 */           Managers.SOUND.playSwipeOut();
/*    */         } 
/*    */       } 
/*    */     }
/* 89 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */   
/*    */   public Setting<BooleanSettingGroup> getParentSetting() {
/* 93 */     return this.parentSetting;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\BooleanParentElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */