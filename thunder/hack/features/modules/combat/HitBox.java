/*    */ package thunder.hack.features.modules.combat;
/*    */ 
/*    */ import thunder.hack.core.manager.client.ServerManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public final class HitBox
/*    */   extends Module {
/*    */   public HitBox() {
/* 10 */     super("HitBoxes", Module.Category.COMBAT);
/*    */   }
/*    */   
/* 13 */   public static final Setting<Float> XZExpand = new Setting("XZExpand", Float.valueOf(1.0F), Float.valueOf(0.0F), Float.valueOf(5.0F));
/* 14 */   public static final Setting<Float> YExpand = new Setting("YExpand", Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(5.0F));
/* 15 */   public static final Setting<Boolean> affectToAura = new Setting("AffectToAura", Boolean.valueOf(false));
/*    */ 
/*    */   
/*    */   public String getDisplayInfo() {
/* 19 */     return "H: " + ServerManager.round2(((Float)XZExpand.getValue()).floatValue()) + " V: " + ServerManager.round2(((Float)YExpand.getValue()).floatValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\HitBox.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */