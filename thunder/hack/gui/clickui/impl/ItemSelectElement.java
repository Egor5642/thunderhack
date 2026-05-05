/*    */ package thunder.hack.gui.clickui.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.gui.clickui.AbstractElement;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.gui.windows.WindowBase;
/*    */ import thunder.hack.gui.windows.WindowsScreen;
/*    */ import thunder.hack.gui.windows.impl.ItemSelectWindow;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ItemSelectSetting;
/*    */ 
/*    */ public class ItemSelectElement extends AbstractElement {
/*    */   private final Setting<ItemSelectSetting> setting;
/*    */   
/*    */   public ItemSelectElement(Setting<ItemSelectSetting> setting) {
/* 21 */     super(setting);
/* 22 */     this.setting = setting;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(class_332 context, int mouseX, int mouseY, float delta) {
/* 27 */     super.render(context, mouseX, mouseY, delta);
/* 28 */     class_4587 matrixStack = context.method_51448();
/* 29 */     FontRenderers.icons.drawString(matrixStack, "H", (this.x + this.width - 14.0F), (this.y + 6.0F), (new Color(-1250068, true)).getRGB());
/* 30 */     FontRenderers.sf_medium_mini.drawString(matrixStack, this.setting.getName(), (this.x + 6.0F), (this.y + this.height / 2.0F - 1.0F), (new Color(-1)).getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int button) {
/* 35 */     if (this.hovered) {
/* 36 */       IManager.mc.method_1507((class_437)new WindowsScreen(new WindowBase[] { (WindowBase)new ItemSelectWindow(getItemSetting()) }));
/* 37 */       Managers.SOUND.playSwipeIn();
/*    */     } 
/* 39 */     super.mouseClicked(mouseX, mouseY, button);
/*    */   }
/*    */   
/*    */   public Setting<ItemSelectSetting> getItemSetting() {
/* 43 */     return this.setting;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\clickui\impl\ItemSelectElement.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */