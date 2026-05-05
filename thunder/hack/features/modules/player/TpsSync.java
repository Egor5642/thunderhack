/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventTick;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class TpsSync
/*    */   extends Module {
/*    */   public TpsSync() {
/* 13 */     super("TpsSync", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = 100)
/*    */   public void onTick(EventTick e) {
/* 18 */     if (ModuleManager.timer.isEnabled())
/* 19 */       return;  if (Managers.SERVER.getTPS() > 1.0F)
/* 20 */     { ThunderHack.TICK_TIMER = Managers.SERVER.getTPS() / 20.0F; }
/* 21 */     else { ThunderHack.TICK_TIMER = 1.0F; }
/*    */   
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 26 */     ThunderHack.TICK_TIMER = 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\TpsSync.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */