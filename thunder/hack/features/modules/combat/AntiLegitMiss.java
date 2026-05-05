/*    */ package thunder.hack.features.modules.combat;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import thunder.hack.events.impl.EventAttack;
/*    */ import thunder.hack.events.impl.EventHandleBlockBreaking;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class AntiLegitMiss
/*    */   extends Module {
/*    */   public AntiLegitMiss() {
/* 11 */     super("AntiLegitMiss", Module.Category.COMBAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onAttack(EventAttack e) {
/* 16 */     if (!(mc.field_1765 instanceof net.minecraft.class_3966) && e.isPre())
/* 17 */       e.cancel(); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockBreaking(EventHandleBlockBreaking e) {
/* 22 */     if (!(mc.field_1765 instanceof net.minecraft.class_3966))
/* 23 */       e.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\AntiLegitMiss.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */