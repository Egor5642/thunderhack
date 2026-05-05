/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import net.minecraft.class_1799;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.features.modules.combat.AutoTotem;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class ToolSaver
/*    */   extends Module {
/*    */   public ToolSaver() {
/* 12 */     super("ToolSaver", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 15 */     this.savePercent = new Setting("Save %", Integer.valueOf(10), Integer.valueOf(1), Integer.valueOf(50));
/*    */   }
/*    */   private final Setting<Integer> savePercent;
/*    */   public void onUpdate() {
/* 19 */     class_1799 tool = mc.field_1724.method_6047();
/* 20 */     if (!(tool.method_7909() instanceof net.minecraft.class_1766)) {
/*    */       return;
/*    */     }
/* 23 */     float durability = (tool.method_7936() - tool.method_7919());
/* 24 */     int percent = (int)(durability / tool.method_7936() * 100.0F);
/*    */     
/* 26 */     if (percent <= ((Integer)this.savePercent.getValue()).intValue()) {
/* 27 */       (mc.field_1724.method_31548()).field_7545 = AutoTotem.findNearestCurrentItem();
/* 28 */       sendMessage(ClientSettings.isRu() ? "Твой инструмент почти сломался!" : "Your tool is almost broken!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\ToolSaver.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */