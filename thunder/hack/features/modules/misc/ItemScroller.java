/*    */ package thunder.hack.features.modules.misc;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1713;
/*    */ import net.minecraft.class_1735;
/*    */ import net.minecraft.class_1792;
/*    */ import thunder.hack.events.impl.EventClickSlot;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class ItemScroller extends Module {
/*    */   public Setting<Integer> delay;
/*    */   
/*    */   public ItemScroller() {
/* 14 */     super("ItemScroller", Module.Category.MISC);
/*    */ 
/*    */     
/* 17 */     this.delay = new Setting("Delay", Integer.valueOf(80), Integer.valueOf(0), Integer.valueOf(500));
/*    */     
/* 19 */     this.pauseListening = false;
/*    */   } private boolean pauseListening;
/*    */   @EventHandler
/*    */   public void onClick(EventClickSlot e) {
/* 23 */     if ((isKeyPressed(340) || isKeyPressed(344)) && (
/* 24 */       isKeyPressed(341) || isKeyPressed(345)) && e
/* 25 */       .getSlotActionType() == class_1713.field_7795 && !this.pauseListening) {
/*    */       
/* 27 */       class_1792 copy = ((class_1735)mc.field_1724.field_7512.field_7761.get(e.getSlot())).method_7677().method_7909();
/* 28 */       this.pauseListening = true;
/* 29 */       for (int i2 = 0; i2 < mc.field_1724.field_7512.field_7761.size(); i2++) {
/* 30 */         if (((class_1735)mc.field_1724.field_7512.field_7761.get(i2)).method_7677().method_7909() == copy)
/* 31 */           mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i2, 1, class_1713.field_7795, (class_1657)mc.field_1724); 
/*    */       } 
/* 33 */       this.pauseListening = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\ItemScroller.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */