/*    */ package thunder.hack.gui.mainmenu;
/*    */ 
/*    */ import net.minecraft.class_332;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class MainMenuButton
/*    */ {
/*    */   private final float posX;
/*    */   private final float posY;
/*    */   private final float width;
/*    */   
/*    */   public MainMenuButton(float posX, float posY, @NotNull String name, Runnable action, boolean isExit) {
/* 16 */     this.name = name;
/* 17 */     this.posX = posX;
/* 18 */     this.posY = posY;
/*    */     
/* 20 */     this.action = action;
/*    */     
/* 22 */     this.width = isExit ? 222.0F : 107.0F;
/* 23 */     this.height = 38.0F;
/*    */   }
/*    */   private final float height; private final String name; private final Runnable action;
/*    */   public MainMenuButton(float posX, float posY, @NotNull String name, Runnable action) {
/* 27 */     this(posX, posY, name, action, false);
/*    */   }
/*    */   
/*    */   public void onRender(class_332 context, float mouseX, float mouseY) {
/* 31 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/* 32 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/* 33 */     Render2DEngine.drawHudBase(context.method_51448(), halfOfWidth + this.posX, halfOfHeight + this.posY, this.width, this.height, 10.0F);
/* 34 */     boolean hovered = Render2DEngine.isHovered(mouseX, mouseY, (halfOfWidth + this.posX), (halfOfHeight + this.posY), this.width, this.height);
/* 35 */     FontRenderers.monsterrat.drawCenteredString(context.method_51448(), this.name, (halfOfWidth + this.posX + this.width / 2.0F), (halfOfHeight + this.posY + this.height / 2.0F - 3.0F), hovered ? -1 : Render2DEngine.applyOpacity(-1, 0.7F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(int mouseX, int mouseY) {
/* 40 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/* 41 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/* 42 */     boolean hovered = Render2DEngine.isHovered(mouseX, mouseY, (halfOfWidth + this.posX), (halfOfHeight + this.posY), this.width, this.height);
/* 43 */     if (hovered) this.action.run(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\mainmenu\MainMenuButton.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */