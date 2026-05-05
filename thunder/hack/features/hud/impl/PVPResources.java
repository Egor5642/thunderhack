/*    */ package thunder.hack.features.hud.impl;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_1935;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class PVPResources extends HudElement {
/*    */   public PVPResources() {
/* 18 */     super("PVPResources", 50, 50);
/*    */   }
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 22 */     super.onRender2D(context);
/* 23 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), 50.0F, 50.0F, ((Float)HudEditor.hudRound.getValue()).floatValue());
/*    */     
/* 25 */     setBounds(getPosX(), getPosY(), 50.0F, 50.0F);
/*    */     
/* 27 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 28 */       Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX(), getPosY() + 24.5F, getPosX() + 50.0F, getPosY() + 25.0F, new Color(1426063359, true));
/* 29 */       Render2DEngine.drawRectDumbWay(context.method_51448(), getPosX() + 24.5F, getPosY() - 1.0F, getPosX() + 25.0F, getPosY() + 49.0F, new Color(1426063359, true));
/*    */     } else {
/* 31 */       Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 2.0F, getPosY() + 24.5F, getPosX() + 26.0F, getPosY() + 25.0F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/* 32 */       Render2DEngine.horizontalGradient(context.method_51448(), getPosX() + 26.0F, getPosY() + 24.5F, getPosX() + 48.0F, getPosY() + 25.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/* 33 */       Render2DEngine.verticalGradient(context.method_51448(), getPosX() + 25.5F, getPosY() + 2.0F, getPosX() + 26.0F, getPosY() + 23.0F, Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0), ((ColorSetting)HudEditor.textColor.getValue()).getColorObject());
/* 34 */       Render2DEngine.verticalGradient(context.method_51448(), getPosX() + 25.5F, getPosY() + 23.0F, getPosX() + 26.0F, getPosY() + 48.0F, ((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), Render2DEngine.injectAlpha(((ColorSetting)HudEditor.textColor.getValue()).getColorObject(), 0));
/*    */     } 
/*    */     
/* 37 */     int totemCount = getItemCount(class_1802.field_8288);
/* 38 */     int xpCount = getItemCount(class_1802.field_8287);
/* 39 */     int crystalCount = getItemCount(class_1802.field_8301);
/* 40 */     int gappleCount = getItemCount(class_1802.field_8367);
/*    */     
/* 42 */     List<class_1799> list = new ArrayList<>();
/*    */     
/* 44 */     if (totemCount > 0) list.add(new class_1799((class_1935)class_1802.field_8288, totemCount)); 
/* 45 */     if (xpCount > 0) list.add(new class_1799((class_1935)class_1802.field_8287, xpCount)); 
/* 46 */     if (crystalCount > 0) list.add(new class_1799((class_1935)class_1802.field_8301, crystalCount)); 
/* 47 */     if (gappleCount > 0) list.add(new class_1799((class_1935)class_1802.field_8367, gappleCount));
/*    */     
/* 49 */     for (int i = 0; i < list.size(); i++) {
/* 50 */       int offsetX = i % 2 * 25;
/* 51 */       int offsetY = i / 2 * 25;
/* 52 */       context.method_51427(list.get(i), (int)(getPosX() + offsetX + 4.0F), (int)(getPosY() + offsetY + 4.0F));
/* 53 */       context.method_51448().method_22903();
/* 54 */       context.method_51448().method_46416(0.0F, 0.0F, 151.0F);
/* 55 */       Render2DEngine.drawBlurredShadow(context.method_51448(), getPosX() + offsetX + 8.0F, getPosY() + offsetY + 8.0F, 9.0F, 9.0F, 12, Color.BLACK);
/* 56 */       FontRenderers.sf_medium.drawCenteredString(context.method_51448(), String.valueOf(((class_1799)list.get(i)).method_7947()), (int)(getPosX() + offsetX + 12.0F), (int)(getPosY() + offsetY + 11.0F), ((ColorSetting)HudEditor.textColor.getValue()).getColor());
/* 57 */       context.method_51448().method_22909();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getItemCount(class_1792 item) {
/* 62 */     if (mc.field_1724 == null) return 0; 
/* 63 */     int n = 0;
/* 64 */     int n2 = 44;
/* 65 */     for (int i = 0; i <= n2; i++) {
/* 66 */       class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
/* 67 */       if (itemStack.method_7909() == item)
/* 68 */         n += itemStack.method_7947(); 
/*    */     } 
/* 70 */     return n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\PVPResources.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */