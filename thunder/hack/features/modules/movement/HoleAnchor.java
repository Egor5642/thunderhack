/*    */ package thunder.hack.features.modules.movement;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import net.minecraft.class_243;
/*    */ import thunder.hack.events.impl.PlayerUpdateEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.world.HoleUtility;
/*    */ 
/*    */ public class HoleAnchor extends Module {
/*    */   private final Setting<Integer> pitch;
/*    */   
/*    */   public HoleAnchor() {
/* 15 */     super("HoleAnchor", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 18 */     this.pitch = new Setting("Pitch", Integer.valueOf(60), Integer.valueOf(0), Integer.valueOf(90));
/* 19 */     this.pull = new Setting("Pull", Boolean.valueOf(true));
/*    */   } private final Setting<Boolean> pull;
/*    */   @EventHandler
/*    */   public void onPlayerUpdate(PlayerUpdateEvent e) {
/* 23 */     if (mc.field_1724.method_36455() > ((Integer)this.pitch.getValue()).intValue() && (
/*    */       
/* 25 */       HoleUtility.validIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(1)) || 
/* 26 */       HoleUtility.validIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(2)) || 
/* 27 */       HoleUtility.validIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(3)) || 
/* 28 */       HoleUtility.validTwoBlockIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(1)) || 
/* 29 */       HoleUtility.validTwoBlockIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(2)) || 
/* 30 */       HoleUtility.validTwoBlockIndestructible(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10087(3))))
/*    */     {
/* 32 */       if (!((Boolean)this.pull.getValue()).booleanValue()) {
/* 33 */         mc.field_1724.method_18800(0.0D, mc.field_1724.method_18798().method_10214(), 0.0D);
/*    */       } else {
/* 35 */         class_243 center = new class_243(Math.floor(mc.field_1724.method_23317()) + 0.5D, Math.floor(mc.field_1724.method_23318()), Math.floor(mc.field_1724.method_23321()) + 0.5D);
/*    */         
/* 37 */         if (Math.abs(center.field_1352 - mc.field_1724.method_23317()) > 0.1D || Math.abs(center.field_1350 - mc.field_1724.method_23321()) > 0.1D) {
/* 38 */           double d3 = center.field_1352 - mc.field_1724.method_23317();
/* 39 */           double d4 = center.field_1350 - mc.field_1724.method_23321();
/* 40 */           mc.field_1724.method_18800(Math.min(d3 / 2.0D, 0.2D), mc.field_1724.method_18798().method_10214(), Math.min(d4 / 2.0D, 0.2D));
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\HoleAnchor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */