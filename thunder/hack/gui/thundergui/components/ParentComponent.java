/*    */ package thunder.hack.gui.thundergui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.SettingGroup;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class ParentComponent
/*    */   extends SettingElement {
/*    */   public ParentComponent(Setting setting) {
/* 14 */     super(setting);
/* 15 */     SettingGroup settingGroup = (SettingGroup)setting.getValue();
/* 16 */     settingGroup.setExtended(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(class_4587 stack, int mouseX, int mouseY, float partialTicks) {
/* 21 */     super.render(stack, mouseX, mouseY, partialTicks);
/* 22 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 25 */     FontRenderers.modules.drawCenteredString(stack, getSetting().getName(), (getX() + this.width / 2.0F), (getY() + 2.0F), (new Color(-1325400065, true)).getRGB());
/* 26 */     Render2DEngine.draw2DGradientRect(stack, getX() + 10.0F, getY() + 6.0F, getX() + this.width / 2.0F - 20.0F, getY() + 7.0F, new Color(16777215, true), new Color(16777215, true), new Color(-1325400065, true), new Color(-1325400065, true));
/* 27 */     Render2DEngine.draw2DGradientRect(stack, getX() + this.width / 2.0F + 20.0F, getY() + 6.0F, getX() + this.width - 10.0F, getY() + 7.0F, new Color(-1325400065, true), new Color(-1325400065, true), new Color(16777215, true), new Color(16777215, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 32 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 35 */     if (this.hovered) {
/* 36 */       SettingGroup settingGroup = (SettingGroup)this.setting.getValue();
/* 37 */       settingGroup.setExtended(!settingGroup.isExtended());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\ParentComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */