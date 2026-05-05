/*    */ package thunder.hack.features.modules.misc;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1802;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2886;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.player.InventoryUtility;
/*    */ import thunder.hack.utility.player.SearchInvResult;
/*    */ 
/*    */ public class AutoSoup extends Module {
/*    */   public AutoSoup() {
/* 14 */     super("AutoSoup", Module.Category.MISC);
/*    */ 
/*    */     
/* 17 */     this.health = new Setting("TriggerHealth", Float.valueOf(7.0F), Float.valueOf(1.0F), Float.valueOf(20.0F));
/*    */   }
/*    */   private final Setting<Float> health;
/*    */   public void onUpdate() {
/* 21 */     if (mc.field_1724.method_6032() <= ((Float)this.health.getValue()).floatValue()) {
/* 22 */       SearchInvResult result = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8208 });
/* 23 */       int prevSlot = (mc.field_1724.method_31548()).field_7545;
/* 24 */       if (result.found()) {
/* 25 */         result.switchTo();
/* 26 */         sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
/* 27 */         InventoryUtility.switchTo(prevSlot);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AutoSoup.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */