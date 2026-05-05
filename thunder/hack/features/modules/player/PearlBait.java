/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_742;
/*    */ import thunder.hack.events.impl.EventEntitySpawn;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class PearlBait extends Module {
/*    */   public PearlBait() {
/* 13 */     super("PearlBait", Module.Category.PLAYER);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onEntitySpawn(EventEntitySpawn e) {
/* 18 */     if (e.getEntity() instanceof net.minecraft.class_1684)
/* 19 */       mc.field_1687.method_18456().stream()
/* 20 */         .min(Comparator.comparingDouble(p -> p.method_5707(e.getEntity().method_19538())))
/* 21 */         .ifPresent(player -> {
/*    */             if (player.equals(mc.field_1724) && mc.field_1724.method_24828()) {
/*    */               mc.field_1724.method_18800(0.0D, 0.0D, 0.0D);
/*    */               mc.field_1724.field_3913.field_3905 = 0.0F;
/*    */               mc.field_1724.field_3913.field_3907 = 0.0F;
/*    */               mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 1.0D, mc.field_1724.method_23321(), false));
/*    */             } 
/*    */           }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\PearlBait.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */