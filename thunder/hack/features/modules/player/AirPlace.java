/*    */ package thunder.hack.features.modules.player;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_239;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_3965;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.setting.impl.SettingGroup;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class AirPlace extends Module {
/*    */   private final Setting<Float> range;
/*    */   private final Setting<SettingGroup> renderGroup;
/*    */   
/*    */   public AirPlace() {
/* 22 */     super("AirPlace", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 25 */     this.range = new Setting("Range", Float.valueOf(5.0F), Float.valueOf(0.0F), Float.valueOf(6.0F));
/*    */     
/* 27 */     this.renderGroup = new Setting("Render", new SettingGroup(false, 0));
/* 28 */     this.swing = (new Setting("Swing", Boolean.valueOf(true))).addToGroup(this.renderGroup);
/* 29 */     this.fillColor = (new Setting("Fill Color", new ColorSetting(new Color(100, 50, 255, 50)))).addToGroup(this.renderGroup);
/* 30 */     this.lineColor = (new Setting("Line Color", new ColorSetting(new Color(100, 50, 255, 150)))).addToGroup(this.renderGroup);
/* 31 */     this.lineWidth = (new Setting("Line Width", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(5))).addToGroup(this.renderGroup);
/*    */   }
/*    */   private final Setting<Boolean> swing; private final Setting<ColorSetting> fillColor; private final Setting<ColorSetting> lineColor; private final Setting<Integer> lineWidth;
/*    */   private class_3965 hit;
/*    */   private int cooldown;
/*    */   
/*    */   public void onUpdate() {
/* 38 */     if (this.cooldown > 0) {
/* 39 */       this.cooldown--;
/*    */     }
/* 41 */     class_239 hitResult = mc.method_1560().method_5745(((Float)this.range.getValue()).floatValue(), 0.0F, false);
/*    */     
/* 43 */     if (hitResult instanceof class_3965) { class_3965 bhr = (class_3965)hitResult; }
/*    */     else
/*    */     { return; }
/* 46 */      boolean main = mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1747;
/* 47 */     boolean off = mc.field_1724.method_6079().method_7909() instanceof net.minecraft.class_1747;
/* 48 */     if (mc.field_1690.field_1904.method_1434() && (main || off) && this.cooldown <= 0) {
/* 49 */       mc.field_1761.method_2896(mc.field_1724, main ? class_1268.field_5808 : class_1268.field_5810, this.hit);
/* 50 */       if (((Boolean)this.swing.getValue()).booleanValue()) { mc.field_1724.method_6104(main ? class_1268.field_5808 : class_1268.field_5810); }
/* 51 */       else { sendPacket((class_2596)new class_2879(main ? class_1268.field_5808 : class_1268.field_5810)); }
/* 52 */        this.cooldown = (ModuleManager.fastUse.isEnabled() && (((Boolean)ModuleManager.fastUse.blocks.getValue()).booleanValue() || ((Boolean)ModuleManager.fastUse.all.getValue()).booleanValue())) ? 0 : 4;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRender3D(class_4587 stack) {
/* 58 */     if (this.hit == null || !mc.field_1687.method_8320(this.hit.method_17777()).method_26204().equals(class_2246.field_10124) || (!(mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1747) && !(mc.field_1724.method_6079().method_7909() instanceof net.minecraft.class_1747))) {
/*    */       return;
/*    */     }
/* 61 */     Render3DEngine.FILLED_QUEUE.add(new Render3DEngine.FillAction(new class_238(this.hit
/* 62 */             .method_17777()), ((ColorSetting)this.fillColor
/* 63 */           .getValue()).getColorObject()));
/*    */     
/* 65 */     Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(new class_238(this.hit
/* 66 */             .method_17777()), ((ColorSetting)this.lineColor
/* 67 */           .getValue()).getColorObject(), ((Integer)this.lineWidth
/* 68 */           .getValue()).intValue()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AirPlace.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */