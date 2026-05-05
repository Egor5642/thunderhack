/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class NameProtect extends Module {
/*    */   public NameProtect() {
/*  9 */     super("NameProtect", Module.Category.MISC);
/*    */   }
/*    */   
/* 12 */   public static Setting<String> newName = new Setting("name", "Hell_Raider");
/* 13 */   public static Setting<Boolean> hideFriends = new Setting("Hide friends", Boolean.valueOf(true));
/*    */   
/*    */   public static String getCustomName() {
/* 16 */     return ModuleManager.nameProtect.isEnabled() ? ((String)newName.getValue()).replaceAll("&", "§") : mc.method_53462().getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\NameProtect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */