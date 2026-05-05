/*   */ package thunder.hack.core.hooks;
/*   */ 
/*   */ import thunder.hack.core.manager.client.ModuleManager;
/*   */ 
/*   */ public class ModuleShutdownHook
/*   */   extends Thread {
/*   */   public void run() {
/* 8 */     if (ModuleManager.unHook.isEnabled())
/* 9 */       ModuleManager.unHook.disable(); 
/*   */   }
/*   */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\hooks\ModuleShutdownHook.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */