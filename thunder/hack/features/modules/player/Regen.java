/*    */ package thunder.hack.features.modules.player;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2828;
/*    */ import thunder.hack.events.impl.EventSync;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class Regen extends Module {
/*    */   private final Setting<Integer> health;
/*    */   
/*    */   public Regen() {
/* 12 */     super("Regen", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 15 */     this.health = new Setting("Health", Integer.valueOf(10), Integer.valueOf(0), Integer.valueOf(20));
/* 16 */     this.packetsPerTick = new Setting("Packets/Tick", Integer.valueOf(20), Integer.valueOf(2), Integer.valueOf(120));
/*    */   } private final Setting<Integer> packetsPerTick;
/*    */   @EventHandler
/*    */   public void onSync(EventSync e) {
/* 20 */     if (mc.field_1724.method_6032() + mc.field_1724.method_6067() <= ((Integer)this.health.getValue()).intValue())
/* 21 */       for (int i = 0; i < ((Integer)this.packetsPerTick.getValue()).intValue(); i++)
/* 22 */         sendPacket((class_2596)new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), mc.field_1724.method_24828()));  
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\Regen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */