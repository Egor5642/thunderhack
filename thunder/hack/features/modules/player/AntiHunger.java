/*    */ package thunder.hack.features.modules.player;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_2848;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IPlayerMoveC2SPacket;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AntiHunger extends Module {
/*    */   private final Setting<Boolean> ground;
/*    */   
/*    */   public AntiHunger() {
/* 14 */     super("AntiHunger", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 17 */     this.ground = new Setting("CancelGround", Boolean.valueOf(true));
/* 18 */     this.sprint = new Setting("CancelSprint", Boolean.valueOf(true));
/*    */   }
/*    */   private final Setting<Boolean> sprint;
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send e) {
/* 23 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2828) { class_2828 pac = (class_2828)class_2596; if (((Boolean)this.ground.getValue()).booleanValue())
/* 24 */         ((IPlayerMoveC2SPacket)pac).setOnGround(false);  }
/*    */     
/* 26 */     class_2596 = e.getPacket(); if (class_2596 instanceof class_2848) { class_2848 pac = (class_2848)class_2596; if (((Boolean)this.sprint.getValue()).booleanValue() && 
/* 27 */         pac.method_12365() == class_2848.class_2849.field_12981)
/* 28 */         e.cancel();  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AntiHunger.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */