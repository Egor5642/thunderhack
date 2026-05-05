/*    */ package thunder.hack.features.modules.render;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class VoidESP extends Module {
/*    */   public final Setting<ColorSetting> color;
/*    */   
/*    */   public VoidESP() {
/* 18 */     super("VoidESP", Module.Category.RENDER);
/*    */ 
/*    */     
/* 21 */     this.color = new Setting("Color", new ColorSetting(new Color(-671111680, true)));
/* 22 */     this.range = new Setting("Range", Float.valueOf(6.0F), Float.valueOf(3.0F), Float.valueOf(16.0F));
/*    */     
/* 24 */     this.holes = new ArrayList<>();
/*    */   } public Setting<Float> range; private List<class_2338> holes;
/*    */   public void onRender3D(class_4587 stack) {
/* 27 */     this.holes.forEach(h -> Render3DEngine.renderCrosses(new class_238(h), ((ColorSetting)this.color.getValue()).getColorObject(), 2.0F));
/*    */   }
/*    */   
/*    */   public List<class_2338> calcHoles() {
/* 31 */     ArrayList<class_2338> voidHoles = new ArrayList<>();
/* 32 */     for (int x = (int)(mc.field_1724.method_23317() - ((Float)this.range.getValue()).floatValue()); x < mc.field_1724.method_23317() + ((Float)this.range.getValue()).floatValue(); x++) {
/* 33 */       for (int z = (int)(mc.field_1724.method_23321() - ((Float)this.range.getValue()).floatValue()); z < mc.field_1724.method_23321() + ((Float)this.range.getValue()).floatValue(); z++) {
/* 34 */         class_2338 pos = class_2338.method_49637(x, mc.field_1687.method_31607(), z);
/* 35 */         if (mc.field_1687.method_8320(pos).method_26204() != class_2246.field_9987)
/* 36 */           voidHoles.add(pos); 
/*    */       } 
/* 38 */     }  return voidHoles;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onThread() {
/* 43 */     this.holes = calcHoles();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\VoidESP.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */