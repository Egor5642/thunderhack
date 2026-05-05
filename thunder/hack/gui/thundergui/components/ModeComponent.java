/*    */ package thunder.hack.gui.thundergui.components;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class ModeComponent
/*    */   extends SettingElement {
/* 13 */   int progress = 0;
/*    */   private double wheight;
/*    */   private boolean open;
/*    */   
/*    */   public ModeComponent(Setting setting) {
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
/*    */     
/* 29 */     if (this.open) {
/* 30 */       double offsetY2 = 0.0D;
/* 31 */       for (int i = 0; i <= (this.setting.getModes()).length - 1; i++) {
/* 32 */         offsetY2 += 12.0D;
/*    */       }
/* 34 */       Render2DEngine.drawRound(stack, this.x + 114.0F, this.y + 2.0F, 62.0F, (float)(11.0D + offsetY2), 0.5F, new Color(50, 35, 60, 121));
/*    */     } 
/*    */     
/* 37 */     if (mouseX > this.x + 114.0F && mouseX < this.x + 176.0F && mouseY > this.y + 2.0F && mouseY < this.y + 15.0F) {
/* 38 */       Render2DEngine.drawRound(stack, this.x + 114.0F, this.y + 2.0F, 62.0F, 11.0F, 0.5F, new Color(82, 57, 100, 178));
/*    */     } else {
/* 40 */       Render2DEngine.drawRound(stack, this.x + 114.0F, this.y + 2.0F, 62.0F, 11.0F, 0.5F, new Color(50, 35, 60, 178));
/*    */     } 
/*    */     
/* 43 */     FontRenderers.modules.drawString(stack, this.setting.currentEnumName(), (this.x + 116.0F), (this.y + 6.0F), (new Color(-1325400065, true)).getRGB());
/*    */     
/* 45 */     switch (this.progress) { case 1: 
/*    */       case 2: 
/*    */       case 3: 
/*    */       case 4: 
/*    */       default:
/* 50 */         break; }  String arrow = "n";
/*    */     
/* 52 */     FontRenderers.icons.drawString(stack, arrow, (int)(this.x + 166.0F), (int)(this.y + 7.0F), -1);
/*    */     
/* 54 */     double offsetY = 13.0D;
/* 55 */     if (this.open) {
/* 56 */       Color color = HudEditor.getColor(1);
/* 57 */       for (int i = 0; i <= (this.setting.getModes()).length - 1; i++) {
/* 58 */         FontRenderers.settings.drawString(stack, this.setting.getModes()[i], (this.x + 116.0F), (float)((this.y + 5.0F) + offsetY), this.setting.currentEnumName().equalsIgnoreCase(this.setting.getModes()[i]) ? color.getRGB() : -1);
/* 59 */         offsetY += 12.0D;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTick() {
/* 66 */     if (this.open && this.progress > 0) {
/* 67 */       this.progress--;
/*    */     }
/* 69 */     if (!this.open && this.progress < 4) {
/* 70 */       this.progress++;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 76 */     if (getY() > ((ThunderGui.getInstance()).main_posY + (ThunderGui.getInstance()).field_22790) || getY() < (ThunderGui.getInstance()).main_posY) {
/*    */       return;
/*    */     }
/* 79 */     if (mouseX > this.x + 114.0F && mouseX < this.x + 176.0F && mouseY > this.y + 2.0F && mouseY < this.y + 15.0F) {
/* 80 */       this.open = !this.open;
/*    */     }
/* 82 */     if (this.open) {
/* 83 */       double offsetY = 0.0D;
/* 84 */       for (int i = 0; i <= (this.setting.getModes()).length - 1; i++) {
/* 85 */         if (Render2DEngine.isHovered(mouseX, mouseY, this.x, this.y + this.wheight + offsetY, this.width, 12.0D) && button == 0)
/* 86 */           this.setting.setEnumByNumber(i); 
/* 87 */         offsetY += 12.0D;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setWHeight(double height) {
/* 93 */     this.wheight = height;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 97 */     return this.open;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\thundergui\components\ModeComponent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */