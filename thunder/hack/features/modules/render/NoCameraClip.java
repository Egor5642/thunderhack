/*    */ package thunder.hack.features.modules.render;
/*    */ import net.minecraft.class_5498;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ 
/*    */ public class NoCameraClip extends Module {
/*    */   public Setting<Boolean> antiFront;
/*    */   
/*    */   public NoCameraClip() {
/* 11 */     super("NoCameraClip", Module.Category.RENDER);
/*    */ 
/*    */     
/* 14 */     this.antiFront = new Setting("AntiFront", Boolean.valueOf(false));
/* 15 */     this.distance = new Setting("Distance", Float.valueOf(3.0F), Float.valueOf(1.0F), Float.valueOf(20.0F));
/*    */   }
/*    */   public Setting<Float> distance; private float animation;
/*    */   public void onRender3D(class_4587 matrix) {
/* 19 */     if (mc.field_1690.method_31044() == class_5498.field_26664) { this.animation = AnimationUtility.fast(this.animation, 0.0F, 10.0F); }
/* 20 */     else { this.animation = AnimationUtility.fast(this.animation, 1.0F, 10.0F); }
/*    */     
/* 22 */     if (mc.field_1690.method_31044() == class_5498.field_26666 && ((Boolean)this.antiFront.getValue()).booleanValue())
/* 23 */       mc.field_1690.method_31043(class_5498.field_26664); 
/*    */   }
/*    */   
/*    */   public float getDistance() {
/* 27 */     return 1.0F + (((Float)this.distance.getValue()).floatValue() - 1.0F) * this.animation;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\NoCameraClip.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */