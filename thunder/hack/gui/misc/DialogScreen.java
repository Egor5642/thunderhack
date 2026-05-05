/*    */ package thunder.hack.gui.misc;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_437;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class DialogScreen
/*    */   extends class_437
/*    */ {
/*    */   private final class_2960 pic;
/*    */   private final String header;
/*    */   private final String description;
/*    */   private final String yesText;
/*    */   private final String noText;
/*    */   private final Runnable yesAction;
/*    */   private final Runnable noAction;
/*    */   
/*    */   public DialogScreen(class_2960 pic, String header, String description, String yesText, String noText, Runnable yesAction, Runnable noAction) {
/* 25 */     super(class_2561.method_30163("ThDialogScreen"));
/* 26 */     this.pic = pic;
/* 27 */     this.header = header;
/* 28 */     this.description = description;
/* 29 */     this.yesText = yesText;
/* 30 */     this.noText = noText;
/* 31 */     this.yesAction = yesAction;
/* 32 */     this.noAction = noAction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void method_25394(@NotNull class_332 context, int mouseX, int mouseY, float delta) {
/* 37 */     float halfOfWidth = Module.mc.method_22683().method_4486() / 2.0F;
/* 38 */     float halfOfHeight = Module.mc.method_22683().method_4502() / 2.0F;
/*    */     
/* 40 */     float mainX = halfOfWidth - 120.0F;
/* 41 */     float mainY = halfOfHeight - 80.0F;
/* 42 */     float mainWidth = 240.0F;
/* 43 */     float mainHeight = 140.0F;
/*    */     
/* 45 */     Render2DEngine.drawHudBase(context.method_51448(), mainX, mainY, mainWidth, mainHeight, 20.0F, false);
/*    */     
/* 47 */     FontRenderers.sf_medium.drawCenteredString(context.method_51448(), this.header, (mainX + mainWidth / 2.0F), (mainY + 5.0F), -1);
/* 48 */     FontRenderers.sf_medium.drawCenteredString(context.method_51448(), this.description, (mainX + mainWidth / 2.0F), (mainY + 12.0F), (new Color(-1409286145, true)).getRGB());
/*    */     
/* 50 */     Render2DEngine.drawHudBase(context.method_51448(), mainX + 5.0F, mainY + 95.0F, 110.0F, 40.0F, 15.0F, false);
/* 51 */     FontRenderers.sf_medium.drawCenteredString(context.method_51448(), this.yesText, (mainX + 60.0F), (mainY + 112.0F), yesHovered(mouseX, mouseY) ? -1 : (new Color(-1409286145, true)).getRGB());
/*    */     
/* 53 */     Render2DEngine.drawHudBase(context.method_51448(), mainX + 125.0F, mainY + 95.0F, 110.0F, 40.0F, 15.0F, false);
/* 54 */     FontRenderers.sf_medium.drawCenteredString(context.method_51448(), this.noText, (mainX + 180.0F), (mainY + 112.0F), noHovered(mouseX, mouseY) ? -1 : (new Color(-1409286145, true)).getRGB());
/*    */     
/* 56 */     context.method_25290(this.pic, (int)(mainX + mainWidth / 2.0F - 35.0F), (int)mainY + 25, 0.0F, 0.0F, 70, 65, 70, 65);
/*    */   }
/*    */   
/*    */   private boolean isHovered(int mouseX, int mouseY, int x, int y, int width, int height) {
/* 60 */     return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height);
/*    */   }
/*    */   
/*    */   private boolean yesHovered(int mX, int mY) {
/* 64 */     float mainX = Module.mc.method_22683().method_4486() / 2.0F - 120.0F;
/* 65 */     float mainY = Module.mc.method_22683().method_4502() / 2.0F - 80.0F;
/* 66 */     return isHovered(mX, mY, (int)mainX + 5, (int)mainY + 95, 110, 40);
/*    */   }
/*    */   
/*    */   private boolean noHovered(int mX, int mY) {
/* 70 */     float mainX = Module.mc.method_22683().method_4486() / 2.0F - 120.0F;
/* 71 */     float mainY = Module.mc.method_22683().method_4502() / 2.0F - 80.0F;
/* 72 */     return isHovered(mX, mY, (int)mainX + 125, (int)mainY + 95, 110, 40);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 77 */     if (yesHovered((int)mouseX, (int)mouseY)) {
/* 78 */       this.yesAction.run();
/*    */     }
/* 80 */     else if (noHovered((int)mouseX, (int)mouseY)) {
/* 81 */       this.noAction.run();
/*    */     } 
/* 83 */     return super.method_25402(mouseX, mouseY, button);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\misc\DialogScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */