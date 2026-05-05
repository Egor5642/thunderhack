/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class NoInteract extends Module {
/*    */   public NoInteract() {
/*  8 */     super("NoInteract", Module.Category.PLAYER);
/*    */   }
/*    */   
/* 11 */   public static Setting<Boolean> onlyAura = new Setting("OnlyAura", Boolean.valueOf(false));
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\NoInteract.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */