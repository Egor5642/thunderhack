/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public final class NoEntityTrace extends Module {
/*    */   public NoEntityTrace() {
/*  8 */     super("NoEntityTrace", Module.Category.PLAYER);
/*    */   }
/*    */   
/* 11 */   public static final Setting<Boolean> ponly = new Setting("Pickaxe Only", Boolean.valueOf(true));
/* 12 */   public static final Setting<Boolean> noSword = new Setting("No Sword", Boolean.valueOf(true));
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\NoEntityTrace.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */