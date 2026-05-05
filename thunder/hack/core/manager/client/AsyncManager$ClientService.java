/*    */ package thunder.hack.core.manager.client;
/*    */ 
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientService
/*    */   extends Thread
/*    */ {
/*    */   public void run() {
/* 72 */     while (!Thread.currentThread().isInterrupted()) {
/*    */       try {
/* 74 */         Managers.TELEMETRY.onUpdate();
/* 75 */         if (!Module.fullNullCheck()) {
/* 76 */           Managers.MODULE.modules.forEach(m -> {
/*    */                 if (m.isEnabled())
/*    */                   m.onThread(); 
/* 79 */               }); Thread.sleep(100L); continue;
/* 80 */         }  Thread.yield();
/* 81 */       } catch (Exception exception) {
/* 82 */         exception.printStackTrace();
/* 83 */         Command.sendMessage(exception.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\AsyncManager$ClientService.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */