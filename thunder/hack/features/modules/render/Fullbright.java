/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class Fullbright extends Module {
/*    */   public Fullbright() {
/*  8 */     super("Fullbright", Module.Category.RENDER);
/*    */   }
/*    */   
/* 11 */   public static Setting<Float> minBright = new Setting("MinBright", Float.valueOf(0.5F), Float.valueOf(0.0F), Float.valueOf(1.0F));
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Fullbright.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */