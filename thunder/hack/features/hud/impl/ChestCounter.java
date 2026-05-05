/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2281;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2595;
/*    */ import net.minecraft.class_2745;
/*    */ import net.minecraft.class_2769;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_3545;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.features.modules.render.StorageEsp;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class ChestCounter extends HudElement {
/*    */   public ChestCounter() {
/* 24 */     super("ChestCounter", 50, 10);
/*    */   }
/*    */   public void onRender2D(class_332 context) {
/* 27 */     super.onRender2D(context);
/* 28 */     class_3545<Integer, Integer> chests = getChestCount();
/* 29 */     String str = "Chests: " + String.valueOf(class_124.field_1068) + "S:" + String.valueOf(chests.method_15442()) + " D:" + String.valueOf(chests.method_15441());
/* 30 */     float pX = (getPosX() > mc.method_22683().method_4486() / 2.0F) ? (getPosX() - FontRenderers.getModulesRenderer().getStringWidth(str)) : getPosX();
/*    */     
/* 32 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 33 */       Render2DEngine.drawRoundedBlur(context.method_51448(), pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 34 */       Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
/*    */       
/* 36 */       Render2DEngine.setupRender();
/* 37 */       RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 38 */       RenderSystem.setShaderTexture(0, TextureStorage.chestIcon);
/* 39 */       Render2DEngine.renderGradientTexture(context.method_51448(), (pX + 2.0F), (getPosY() + 1.0F), 10.0D, 10.0D, 0.0F, 0.0F, 512.0D, 512.0D, 512.0D, 512.0D, 
/* 40 */           HudEditor.getColor(270), HudEditor.getColor(0), HudEditor.getColor(180), HudEditor.getColor(90));
/* 41 */       Render2DEngine.endRender();
/*    */     } 
/*    */     
/* 44 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), str, (pX + 18.0F), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 45 */     setBounds(pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(str) + 21.0F, 13.0F);
/*    */   }
/*    */   
/*    */   public class_3545<Integer, Integer> getChestCount() {
/* 49 */     int singleCount = 0;
/* 50 */     int doubleCount = 0;
/*    */     
/* 52 */     for (class_2586 be : StorageEsp.getBlockEntities()) {
/* 53 */       if (be instanceof class_2595) { class_2595 chest = (class_2595)be;
/* 54 */         class_2745 chestType = (class_2745)mc.field_1687.method_8320(chest.method_11016()).method_11654((class_2769)class_2281.field_10770);
/* 55 */         if (chestType == class_2745.field_12569) {
/* 56 */           singleCount++; continue;
/* 57 */         }  doubleCount++; }
/*    */     
/*    */     } 
/* 60 */     return new class_3545(Integer.valueOf(singleCount), Integer.valueOf(doubleCount / 2));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\ChestCounter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */