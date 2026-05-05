/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.clickui.ClickGUI;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class BooleanElement extends AbstractElement {
/*    */   float animation;
/*    */   
/*    */   public BooleanElement(Setting setting) {
/* 20 */     super(setting);
/*    */ 
/*    */     
/* 23 */     this.animation = 0.0F;
/* 24 */     this.animation2 = 0.0F;
/*    */   }
/*    */   float animation2;
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 28 */     super.render(context, mouseX, mouseY, delta);
/*    */     
/* 30 */     Render2DEngine.drawRound(context.method_51448(), this.x + this.width - 21.0F, this.y + this.height / 2.0F - 4.0F, 15.0F, 8.0F, 1.0F, (7.0F * this.animation > 4.0F) ? HudEditor.getColor(0) : new Color(687865855, true));
/*    */     
/* 32 */     this.animation = AnimationUtility.fast(this.animation, ((Boolean)this.setting.getValue()).booleanValue() ? 1.0F : 0.0F, 20.0F);
/* 33 */     this.animation2 = AnimationUtility.fast(this.animation2, ((Boolean)this.setting.getValue()).booleanValue() ? 1.0F : 0.0F, 8.0F);
/* 34 */     Render2DEngine.drawRound(context.method_51448(), this.x + this.width - 20.0F + 7.0F * this.animation, this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 1.0F, new Color(-1));
/* 35 */     Render2DEngine.drawRound(context.method_51448(), this.x + this.width - 20.0F + 7.0F * this.animation2, this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 1.0F, new Color(-1));
/*    */     
/* 37 */     if (7.0F * this.animation > 4.0F) {
/* 38 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), "v", (this.x + this.width - 19.0F), (this.y + this.height / 2.0F - 2.0F), (new Color(-1)).getRGB());
/*    */     } else {
/* 40 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), "x", (this.x + this.width - 12.0F), (this.y + this.height / 2.0F - 2.0F), (new Color(-1)).getRGB());
/*    */     } 
/*    */     
/* 43 */     if (Render2DEngine.isHovered(mouseX, mouseY, (this.x + this.width - 21.0F), (this.y + this.height / 2.0F - 4.0F), 15.0D, 8.0D)) {
/* 44 */       if (GLFW.glfwGetPlatform() != 393219) {
/* 45 */         GLFW.glfwSetCursor(IManager.mc.method_22683().method_4490(), 
/* 46 */             GLFW.glfwCreateStandardCursor(221188));
/*    */       }
/* 48 */       ClickGUI.anyHovered = true;
/*    */     } 
/*    */     
/* 51 */     FontRenderers.sf_medium_mini.drawString(context.method_51448(), this.setting.getName(), (((this.setting.group != null) ? 2.0F : 0.0F) + this.x + 6.0F), (this.y + this.height / 2.0F - 3.0F + 2.0F), (new Color(-1)).getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 56 */     if (this.hovered && button == 0) {
/* 57 */       this.setting.setValue(Boolean.valueOf(!((Boolean)this.setting.getValue()).booleanValue()));
/* 58 */       Managers.SOUND.playBoolean();
/*    */     } 
/*    */     
/* 61 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\BooleanElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */