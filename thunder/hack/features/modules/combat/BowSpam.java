/*    */ package thunder.hack.features.modules.combat;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2846;
/*    */ import net.minecraft.class_2886;
/*    */ import thunder.hack.events.impl.EventSync;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class BowSpam
/*    */   extends Module {
/* 16 */   private final Setting<Integer> ticks = new Setting("Delay", Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(20));
/*    */   
/*    */   public BowSpam() {
/* 19 */     super("BowSpam", Module.Category.COMBAT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onSync(EventSync event) {
/* 24 */     if ((mc.field_1724.method_6079().method_7909() == class_1802.field_8102 || mc.field_1724.method_6047().method_7909() == class_1802.field_8102) && mc.field_1724.method_6115() && 
/* 25 */       mc.field_1724.method_6048() >= ((Integer)this.ticks.getValue()).intValue()) {
/* 26 */       sendPacket((class_2596)new class_2846(class_2846.class_2847.field_12974, class_2338.field_10980, mc.field_1724.method_5735()));
/* 27 */       sendSequencedPacket(id -> new class_2886((mc.field_1724.method_6079().method_7909() == class_1802.field_8102) ? class_1268.field_5810 : class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
/* 28 */       mc.field_1724.method_6075();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\BowSpam.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */