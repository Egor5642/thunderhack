/*    */ package thunder.hack.features.modules.movement;
/*    */ import net.minecraft.class_1536;
/*    */ import net.minecraft.class_2663;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class NoPush extends Module {
/*    */   public Setting<Boolean> blocks;
/*    */   public Setting<Boolean> players;
/*    */   
/*    */   public NoPush() {
/* 12 */     super("NoPush", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 15 */     this.blocks = new Setting("Blocks", Boolean.valueOf(true));
/* 16 */     this.players = new Setting("Players", Boolean.valueOf(true));
/* 17 */     this.water = new Setting("Liquids", Boolean.valueOf(true));
/* 18 */     this.fishingHook = new Setting("FishingHook", Boolean.valueOf(true));
/*    */   } public Setting<Boolean> water; public Setting<Boolean> fishingHook;
/*    */   @EventHandler
/*    */   public void onPacketReceive(PacketEvent.Receive e) {
/* 22 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2663) { class_2663 pac = (class_2663)class_2596; if (pac.method_11470() == 31) { class_1297 class_1297 = pac.method_11469((class_1937)mc.field_1687); if (class_1297 instanceof class_1536) { class_1536 hook = (class_1536)class_1297; if (((Boolean)this.fishingHook.getValue()).booleanValue() && 
/* 23 */             hook.method_26957() == mc.field_1724) e.cancel();  }
/*    */          }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\NoPush.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */