/*    */ package thunder.hack.features.modules.misc;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1657;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.TotemPopEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.features.modules.combat.AntiBot;
/*    */ import thunder.hack.gui.notification.Notification;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class TotemPopCounter extends Module {
/*    */   public Setting<Boolean> notification;
/*    */   
/*    */   public TotemPopCounter() {
/* 18 */     super("TotemPopCounter", Module.Category.MISC);
/*    */ 
/*    */     
/* 21 */     this.notification = new Setting("Notification", Boolean.valueOf(true));
/*    */   } @EventHandler
/*    */   public void onTotemPop(@NotNull TotemPopEvent event) {
/*    */     String s;
/* 25 */     if (event.getEntity() == mc.field_1724) {
/*    */       return;
/*    */     }
/* 28 */     if (ClientSettings.isRu()) { s = String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + event.getEntity().method_5477().getString() + " попнул " + String.valueOf(class_124.field_1068) + String.valueOf(class_124.field_1075); }
/* 29 */     else { s = String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + event.getEntity().method_5477().getString() + " popped " + String.valueOf(class_124.field_1068) + String.valueOf(class_124.field_1075); }
/*    */     
/* 31 */     sendMessage(s);
/* 32 */     if (((Boolean)this.notification.getValue()).booleanValue()) {
/* 33 */       Managers.NOTIFICATION.publicity("TotemPopCounter", s, 2, Notification.Type.INFO);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onUpdate() {
/* 38 */     for (class_1657 player : mc.field_1687.method_18456()) {
/* 39 */       String s; if (player == mc.field_1724 || AntiBot.bots.contains(player) || player.method_6032() > 0.0F || !Managers.COMBAT.popList.containsKey(player.method_5477().getString())) {
/*    */         continue;
/*    */       }
/*    */       
/* 43 */       if (ClientSettings.isRu()) { s = String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + player.method_5477().getString() + " попнул " + String.valueOf(class_124.field_1068); }
/* 44 */       else { s = String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + player.method_5477().getString() + " popped " + String.valueOf(class_124.field_1068); }
/*    */       
/* 46 */       sendMessage(s);
/* 47 */       if (((Boolean)this.notification.getValue()).booleanValue())
/* 48 */         Managers.NOTIFICATION.publicity("TotemPopCounter", s, 2, Notification.Type.INFO); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\TotemPopCounter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */