/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import net.minecraft.class_1304;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2815;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.gui.notification.Notification;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.player.InventoryUtility;
/*    */ import thunder.hack.utility.player.SearchInvResult;
/*    */ 
/*    */ public class ElytraReplace
/*    */   extends Module {
/*    */   public ElytraReplace() {
/* 19 */     super("ElytraReplace", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 22 */     this.durability = new Setting("Durability", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(100));
/*    */   }
/*    */   private final Setting<Integer> durability;
/*    */   public void onUpdate() {
/* 26 */     class_1799 is = mc.field_1724.method_6118(class_1304.field_6174);
/* 27 */     if (is.method_31574(class_1802.field_8833) && 100.0F - is.method_7919() / is.method_7936() * 100.0F <= ((Integer)this.durability.getValue()).intValue()) {
/*    */       
/* 29 */       SearchInvResult result = InventoryUtility.findInInventory(stack -> (stack.method_7909() instanceof net.minecraft.class_1770) ? ((100.0F - stack.method_7919() / stack.method_7936() * 100.0F > ((Integer)this.durability.getValue()).intValue())) : false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 35 */       if (result.found()) {
/* 36 */         clickSlot(result.slot());
/* 37 */         clickSlot(6);
/* 38 */         clickSlot(result.slot());
/* 39 */         sendPacket((class_2596)new class_2815(mc.field_1724.field_7512.field_7763));
/* 40 */         Managers.NOTIFICATION.publicity("ElytraReplace", ClientSettings.isRu() ? "Меняем элитру на новую!" : "Swapping the old elytra for a new one!", 2, Notification.Type.SUCCESS);
/* 41 */         sendMessage(ClientSettings.isRu() ? "Меняем элитру на новую!" : "Swapping the old elytra for a new one!");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\ElytraReplace.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */