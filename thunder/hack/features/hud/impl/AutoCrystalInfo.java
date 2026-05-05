/*     */ package thunder.hack.features.hud.impl;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class AutoCrystalInfo extends HudElement {
/*     */   private final ArrayDeque<Integer> speeds;
/*     */   private int max;
/*     */   
/*     */   public AutoCrystalInfo() {
/*  21 */     super("AutoCrystalInfo", 175, 80);
/*     */ 
/*     */     
/*  24 */     this.speeds = new ArrayDeque<>(20);
/*     */   }
/*     */   private int min; private long time;
/*     */   
/*     */   public void onRender2D(class_332 context) {
/*  29 */     super.onRender2D(context);
/*     */     
/*  31 */     Render2DEngine.drawHudBase(context.method_51448(), getPosX(), getPosY(), getWidth(), getHeight(), ((Float)HudEditor.hudRound.getValue()).floatValue());
/*     */     
/*  33 */     Color c1 = HudEditor.getColor(0).darker().darker().darker();
/*  34 */     Color c2 = HudEditor.getColor(0);
/*     */     
/*  36 */     Render2DEngine.drawRect(context.method_51448(), getPosX() + 2.0F, getPosY() + 14.0F, 96.0F, 64.0F, ((Float)HudEditor.hudRound.getValue()).floatValue(), 0.4F, c1, c1, c1, c1);
/*     */ 
/*     */     
/*  39 */     FontRenderers.sf_bold.drawGradientString(context.method_51448(), "AutoCrystal Info", getPosX() + 2.0F, getPosY() + 4.0F, 10);
/*     */     
/*  41 */     RenderSystem.setShader(class_757::method_34540);
/*     */     
/*  43 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/*     */     
/*  45 */     float offset = 0.0F;
/*     */     
/*  47 */     for (Integer speed : this.speeds) {
/*  48 */       bufferBuilder.method_22912(getPosX() + 2.0F + offset, getPosY() + 80.0F - 55.0F * speed.intValue() / this.max, 0.0F).method_39415(c2.getRGB());
/*  49 */       offset += 4.8F;
/*     */     } 
/*     */     
/*  52 */     Render2DEngine.endBuilding(bufferBuilder);
/*     */     
/*  54 */     class_287 bufferBuilder2 = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/*     */     
/*  56 */     offset = 0.0F;
/*     */     
/*  58 */     for (Integer speed : this.speeds) {
/*  59 */       bufferBuilder2.method_22912(getPosX() + 2.0F + offset, getPosY() + 80.0F - 55.0F * speed.intValue() / this.max, 0.0F).method_39415(Render2DEngine.applyOpacity(c2.getRGB(), 0.3F));
/*  60 */       bufferBuilder2.method_22912(getPosX() + 2.0F + offset, getPosY() + 80.0F, 0.0F).method_39415(Render2DEngine.applyOpacity(c2.darker().darker().getRGB(), 0.0F));
/*  61 */       offset += 4.8F;
/*     */     } 
/*     */     
/*  64 */     Render2DEngine.endBuilding(bufferBuilder2);
/*     */     
/*  66 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "" + this.max, (getPosX() + 100.0F), (getPosY() + 16.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*     */     
/*  68 */     if (!this.speeds.isEmpty()) {
/*  69 */       FontRenderers.sf_bold_mini.drawString(context.method_51448(), String.valueOf(this.speeds.getLast()), (getPosX() + 100.0F), (getPosY() + 80.0F - 55.0F * ((Integer)this.speeds.getLast()).intValue() / this.max), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*     */     }
/*  71 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "" + this.min, (getPosX() + 100.0F), (getPosY() + 72.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*     */     
/*  73 */     boolean isNull = (ModuleManager.autoCrystal.getCurrentData() == null);
/*     */     
/*  75 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Target: " + String.valueOf(class_124.field_1080) + ((AutoCrystal.target == null) ? "null" : AutoCrystal.target.method_5477().getString()), (getPosX() + 113.0F), (getPosY() + 16.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*     */     
/*  77 */     int calc = (int)ModuleManager.autoCrystal.getCalcTime();
/*  78 */     float efficiency = isNull ? 0.0F : MathUtility.round2((ModuleManager.autoCrystal.getCurrentData().damage() / ModuleManager.autoCrystal.getCurrentData().selfDamage()));
/*     */     
/*  80 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Calc delay: " + String.valueOf(getCalcColor(calc)) + calc + "ms", (getPosX() + 113.0F), (getPosY() + 24.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  81 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Side: " + String.valueOf(class_124.field_1080) + String.valueOf(isNull ? "null" : ModuleManager.autoCrystal.getCurrentData().bhr().method_17780()), (getPosX() + 113.0F), (getPosY() + 32.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  82 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Damage: " + String.valueOf(class_124.field_1080) + String.valueOf(isNull ? "null" : Float.valueOf(MathUtility.round2(ModuleManager.autoCrystal.getCurrentData().damage()))), (getPosX() + 113.0F), (getPosY() + 40.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  83 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Self: " + String.valueOf(class_124.field_1080) + String.valueOf(isNull ? "null" : Float.valueOf(MathUtility.round2(ModuleManager.autoCrystal.getCurrentData().selfDamage()))), (getPosX() + 113.0F), (getPosY() + 48.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  84 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Overr. dmg: " + String.valueOf(class_124.field_1080) + String.valueOf(isNull ? "null" : Boolean.valueOf(ModuleManager.autoCrystal.getCurrentData().overrideDamage())), (getPosX() + 113.0F), (getPosY() + 56.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  85 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Efficiency: " + String.valueOf(getEfficiencyColor(efficiency)) + efficiency, (getPosX() + 113.0F), (getPosY() + 64.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*  86 */     FontRenderers.sf_bold_mini.drawString(context.method_51448(), "Pause: " + ModuleManager.autoCrystal.getPauseState(), (getPosX() + 113.0F), (getPosY() + 72.0F), ((ColorSetting)HudEditor.textColor.getValue()).getRawColor());
/*     */     
/*  88 */     Render2DEngine.drawRect(context.method_51448(), getPosX() + 110.5F, getPosY() + 12.0F, 0.5F, 65.0F, new Color(1157627903, true));
/*     */     
/*  90 */     setBounds(getPosX(), getPosY(), getWidth(), getHeight());
/*     */   }
/*     */   
/*     */   public class_124 getCalcColor(float val) {
/*  94 */     if (val > 20.0F) return class_124.field_1061; 
/*  95 */     if (val > 10.0F) return class_124.field_1054; 
/*  96 */     return class_124.field_1060;
/*     */   }
/*     */   
/*     */   public class_124 getEfficiencyColor(float val) {
/* 100 */     if (val > 6.0F) return class_124.field_1060; 
/* 101 */     if (val < 1.0F) return class_124.field_1061; 
/* 102 */     return class_124.field_1054;
/*     */   }
/*     */   
/*     */   public void onSpawn() {
/* 106 */     if (this.time != 0L) {
/* 107 */       if (this.speeds.size() > 20) {
/* 108 */         this.speeds.poll();
/*     */       }
/* 110 */       this.speeds.add(Integer.valueOf((int)(1000.0F / (float)(System.currentTimeMillis() - this.time))));
/* 111 */       this.max = ((Integer)Collections.<Integer>max(this.speeds)).intValue();
/* 112 */       this.min = ((Integer)Collections.<Integer>min(this.speeds)).intValue();
/*     */     } 
/* 114 */     this.time = System.currentTimeMillis();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\AutoCrystalInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */