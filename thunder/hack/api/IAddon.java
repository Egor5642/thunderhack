/*    */ package thunder.hack.api;
/*    */ 
/*    */ import com.mojang.logging.LogUtils;
/*    */ import java.util.List;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public interface IAddon
/*    */ {
/*    */   void onInitialize();
/*    */   
/*    */   List<Module> getModules();
/*    */   
/*    */   List<Command> getCommands();
/*    */   
/*    */   List<HudElement> getHudElements();
/*    */   
/*    */   String getPackage();
/*    */   
/*    */   String getName();
/*    */   
/*    */   String getAuthor();
/*    */   
/*    */   String getRepo();
/*    */   
/*    */   String getVersion();
/*    */   
/*    */   default String getDescription() {
/* 30 */     return "";
/*    */   }
/*    */   
/*    */   default void onShutdown() {
/* 34 */     LogUtils.getLogger().info("Shutting down addon: " + getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\api\IAddon.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */