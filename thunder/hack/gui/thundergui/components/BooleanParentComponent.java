/*    */ package thunder.hack.gui.thundergui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class BooleanParentComponent
/*    */   extends SettingElement
/*    */ {
/* 16 */   float animation = 0.0F;
/*    */   private final Setting<BooleanSettingGroup> parentSetting;
/*    */   
/*    */   public BooleanParentComponent(Setting<BooleanSettingGroup> setting) {
/* 20 */     super(setting);
/* 21 */     this.parentSetting = setting;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(class_4587 stack, int mouseX, int mouseY, float partialTicks) {
/* 26 */     super.render(stack, mouseX, mouseY, partialTicks);
/* 27 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 30 */     FontRenderers.modules.drawString(stack, getSetting().getName(), getX(), (getY() + 5.0F), isHovered() ? -1 : (new Color(-1325400065, true)).getRGB());
/* 31 */     this.animation = AnimationUtility.fast(this.animation, ((BooleanSettingGroup)getParentSetting().getValue()).isEnabled() ? 1.0F : 0.0F, 15.0F);
/* 32 */     double paddingX = (7.0F * this.animation);
/* 33 */     Color color = HudEditor.getColor(1);
/* 34 */     Render2DEngine.drawRound(stack, this.x + this.width - 18.0F, this.y + this.height / 2.0F - 4.0F, 15.0F, 8.0F, 4.0F, (paddingX > 4.0D) ? color : new Color(-5066319));
/* 35 */     Render2DEngine.drawRound(stack, (float)((this.x + this.width - 17.0F) + paddingX), this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 3.0F, new Color(-1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 40 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 43 */     if (isHovered()) ((BooleanSettingGroup)getParentSetting().getValue()).setEnabled(!((BooleanSettingGroup)getParentSetting().getValue()).isEnabled()); 
/*    */   }
/*    */   
/*    */   public Setting<BooleanSettingGroup> getParentSetting() {
/* 47 */     return this.parentSetting;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\BooleanParentComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */