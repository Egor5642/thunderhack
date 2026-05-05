/*    */ package thunder.hack.core.hooks;
/*    */ 
/*    */ import thunder.hack.core.Managers;
/*    */ 
/*    */ public class ManagerShutdownHook
/*    */   extends Thread {
/*    */   public void run() {
/*  8 */     Managers.FRIEND.saveFriends();
/*  9 */     Managers.CONFIG.save(Managers.CONFIG.getCurrentConfig());
/* 10 */     Managers.WAYPOINT.saveWayPoints();
/* 11 */     Managers.MACRO.saveMacro();
/* 12 */     Managers.PROXY.saveProxies();
/* 13 */     Managers.ADDON.shutDown();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\hooks\ManagerShutdownHook.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */