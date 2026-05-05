/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AspectRatio extends Module {
/*    */   public AspectRatio() {
/*  8 */     super("AspectRatio", Module.Category.RENDER);
/*    */ 
/*    */     
/* 11 */     this.ratio = new Setting("Ratio", Float.valueOf(1.78F), Float.valueOf(0.1F), Float.valueOf(5.0F));
/*    */   }
/*    */   
/*    */   public Setting<Float> ratio;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\AspectRatio.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */