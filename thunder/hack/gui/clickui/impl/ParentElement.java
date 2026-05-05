/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_7833;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.SettingGroup;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class ParentElement
/*    */   extends AbstractElement
/*    */ {
/*    */   private final Setting<SettingGroup> parentSetting;
/*    */   private float animation;
/*    */   
/*    */   public ParentElement(Setting<SettingGroup> setting) {
/* 22 */     super(setting);
/* 23 */     this.parentSetting = setting;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 28 */     super.render(context, mouseX, mouseY, delta);
/*    */     
/* 30 */     class_4587 matrixStack = context.method_51448();
/*    */     
/* 32 */     float tx = this.x + this.width - 11.0F;
/* 33 */     float ty = this.y + 7.5F;
/*    */     
/* 35 */     this.animation = AnimationUtility.fast(this.animation, ((SettingGroup)getParentSetting().getValue()).isExtended() ? 0.0F : 1.0F, 15.0F);
/*    */     
/* 37 */     matrixStack.method_22903();
/* 38 */     matrixStack.method_46416(tx, ty, 0.0F);
/* 39 */     matrixStack.method_22907(class_7833.field_40718.rotationDegrees(-180.0F * this.animation));
/* 40 */     matrixStack.method_46416(-tx, -ty, 0.0F);
/* 41 */     matrixStack.method_46416(this.x + this.width - 14.0F, this.y + 4.5F, 0.0F);
/* 42 */     context.method_25290(TextureStorage.guiArrow, 0, 0, 0.0F, 0.0F, 6, 6, 6, 6);
/* 43 */     matrixStack.method_46416(-(this.x + this.width - 14.0F), -(this.y + 4.5F), 0.0F);
/* 44 */     matrixStack.method_22909();
/*    */     
/* 46 */     FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), (this.x + 6.0F + (6 * ((SettingGroup)getParentSetting().getValue()).getHierarchy())), (this.y + this.height / 2.0F - 1.0F), (new Color(-1)).getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 51 */     if (this.hovered) {
/* 52 */       ((SettingGroup)getParentSetting().getValue()).setExtended(!((SettingGroup)getParentSetting().getValue()).isExtended());
/* 53 */       if (((SettingGroup)getParentSetting().getValue()).isExtended()) {
/* 54 */         Managers.SOUND.playSwipeIn();
/*    */       } else {
/* 56 */         Managers.SOUND.playSwipeOut();
/*    */       } 
/*    */     } 
/* 59 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */   
/*    */   public Setting<SettingGroup> getParentSetting() {
/* 63 */     return this.parentSetting;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\ParentElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */