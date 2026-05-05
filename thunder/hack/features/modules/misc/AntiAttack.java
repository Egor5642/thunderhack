/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2824;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.combat.Criticals;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ 
/*    */ public final class AntiAttack
/*    */   extends Module
/*    */ {
/* 18 */   private final Setting<Boolean> friend = new Setting("Friend", Boolean.valueOf(true));
/* 19 */   private final Setting<Boolean> zoglin = new Setting("Zoglin", Boolean.valueOf(true));
/* 20 */   private final Setting<Boolean> villager = new Setting("Villager", Boolean.valueOf(false));
/* 21 */   private final Setting<Boolean> oneHp = new Setting("OneHp", Boolean.valueOf(false));
/* 22 */   private final Setting<Float> hp = new Setting("Hp", Float.valueOf(1.0F), Float.valueOf(0.0F), Float.valueOf(20.0F), v -> ((Boolean)this.oneHp.getValue()).booleanValue());
/*    */   
/*    */   public AntiAttack() {
/* 25 */     super("AntiAttack", Module.Category.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   private void onPacketSend(PacketEvent.Send e) {
/* 31 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2824) { class_2824 pac = (class_2824)class_2596;
/* 32 */       class_1297 entity = Criticals.getEntity(pac);
/* 33 */       if (entity == null)
/* 34 */         return;  if (Managers.FRIEND.isFriend(entity.method_5477().getString()) && ((Boolean)this.friend.getValue()).booleanValue())
/* 35 */         e.cancel(); 
/* 36 */       if (entity instanceof net.minecraft.class_1590 && ((Boolean)this.zoglin.getValue()).booleanValue())
/* 37 */         e.cancel(); 
/* 38 */       if (entity instanceof net.minecraft.class_1646 && ((Boolean)this.villager.getValue()).booleanValue())
/* 39 */       { e.cancel(); }
/* 40 */       else if (((Boolean)this.oneHp.getValue()).booleanValue() && entity instanceof class_1309) { class_1309 lent = (class_1309)entity;
/* 41 */         if (lent.method_6032() <= ((Float)this.hp.getValue()).floatValue())
/* 42 */           e.cancel();  }
/*    */        }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AntiAttack.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */