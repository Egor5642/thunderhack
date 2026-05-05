/*    */ package thunder.hack.features.hud.impl;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2663;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.features.modules.combat.Aura;
/*    */ import thunder.hack.features.modules.combat.AutoCrystal;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ public class KillStats extends HudElement {
/* 22 */   int death = 0, killstreak = 0, kills = 0;
/*    */   public KillStats() {
/* 24 */     super("KillStats", 100, 35);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 29 */     this.death = 0;
/* 30 */     this.kills = 0;
/* 31 */     this.killstreak = 0;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void death(PacketEvent.Receive event) {
/* 36 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2663) { class_2663 pac = (class_2663)class_2596; if (pac.method_11470() == 3) {
/* 37 */         if (!(pac.method_11469((class_1937)mc.field_1687) instanceof net.minecraft.class_1657))
/* 38 */           return;  if (pac.method_11469((class_1937)mc.field_1687) == mc.field_1724) {
/* 39 */           this.death++;
/* 40 */           this.killstreak = 0;
/*    */         }
/* 42 */         else if (Aura.target == pac.method_11469((class_1937)mc.field_1687) || AutoCrystal.target == pac.method_11469((class_1937)mc.field_1687)) {
/* 43 */           this.killstreak++;
/* 44 */           this.kills++;
/*    */         } 
/*    */       }  }
/*    */   
/*    */   }
/*    */   public void onRender2D(class_332 context) {
/* 50 */     super.onRender2D(context);
/*    */     
/* 52 */     String streak = "KillStreak: " + String.valueOf(class_124.field_1068) + this.killstreak;
/* 53 */     String kd = " KD: " + String.valueOf(class_124.field_1068) + MathUtility.round(this.kills / ((this.death > 0) ? this.death : true));
/* 54 */     float pX = (getPosX() > mc.method_22683().method_4486() / 2.0F) ? (getPosX() - FontRenderers.getModulesRenderer().getStringWidth(streak) - FontRenderers.getModulesRenderer().getStringWidth(kd)) : getPosX();
/*    */     
/* 56 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 57 */       Render2DEngine.drawRoundedBlur(context.method_51448(), pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(streak) + FontRenderers.getModulesRenderer().getStringWidth(kd) + 21.0F, 13.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 58 */       Render2DEngine.drawRect(context.method_51448(), pX + 14.0F, getPosY() + 2.0F, 0.5F, 8.0F, new Color(1157627903, true));
/* 59 */       Render2DEngine.setupRender();
/* 60 */       RenderSystem.setShaderTexture(0, TextureStorage.swordIcon);
/* 61 */       Render2DEngine.renderGradientTexture(context.method_51448(), (pX + 2.0F), (getPosY() + 1.0F), 10.0D, 10.0D, 0.0F, 0.0F, 16.0D, 16.0D, 16.0D, 16.0D, 
/* 62 */           HudEditor.getColor(270), HudEditor.getColor(0), HudEditor.getColor(180), HudEditor.getColor(90));
/* 63 */       Render2DEngine.endRender();
/*    */     } 
/*    */     
/* 66 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), streak, (pX + 18.0F), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 67 */     FontRenderers.getModulesRenderer().drawString(context.method_51448(), kd, (pX + 18.0F + FontRenderers.getModulesRenderer().getStringWidth(streak)), (getPosY() + 5.0F), HudEditor.getColor(1).getRGB());
/* 68 */     setBounds(pX, getPosY(), FontRenderers.getModulesRenderer().getStringWidth(streak) + FontRenderers.getModulesRenderer().getStringWidth(kd) + 21.0F, 13.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\KillStats.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */