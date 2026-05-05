/*    */ package thunder.hack.gui.thundergui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class BooleanComponent
/*    */   extends SettingElement
/*    */ {
/* 15 */   float animation = 0.0F;
/*    */   
/*    */   public BooleanComponent(Setting setting) {
/* 18 */     super(setting);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(class_4587 stack, int mouseX, int mouseY, float partialTicks) {
/* 23 */     super.render(stack, mouseX, mouseY, partialTicks);
/* 24 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 27 */     FontRenderers.modules.drawString(stack, getSetting().getName(), getX(), (getY() + 5.0F), isHovered() ? -1 : (new Color(-1325400065, true)).getRGB());
/* 28 */     this.animation = AnimationUtility.fast(this.animation, ((Boolean)this.setting.getValue()).booleanValue() ? 1.0F : 0.0F, 15.0F);
/* 29 */     double paddingX = (7.0F * this.animation);
/* 30 */     Color color = HudEditor.getColor(1);
/* 31 */     Render2DEngine.drawRound(stack, this.x + this.width - 18.0F, this.y + this.height / 2.0F - 4.0F, 15.0F, 8.0F, 4.0F, (paddingX > 4.0D) ? color : new Color(-5066319));
/* 32 */     Render2DEngine.drawRound(stack, (float)((this.x + this.width - 17.0F) + paddingX), this.y + this.height / 2.0F - 3.0F, 6.0F, 6.0F, 3.0F, new Color(-1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 37 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 40 */     if (isHovered()) this.setting.setValue(Boolean.valueOf(!((Boolean)this.setting.getValue()).booleanValue())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\BooleanComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */