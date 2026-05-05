/*    */ package thunder.hack.features.modules.player;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1713;
/*    */ import net.minecraft.class_1738;
/*    */ import net.minecraft.class_1792;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.Timer;
/*    */ 
/*    */ public class MouseElytraFix extends Module {
/*    */   public MouseElytraFix() {
/* 11 */     super("MouseElytraFix", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 14 */     this.delay = new Timer();
/*    */   }
/*    */   private final Timer delay;
/*    */   public void onUpdate() {
/* 18 */     class_1792 class_1792 = mc.field_1724.field_7512.method_34255().method_7909(); if (class_1792 instanceof class_1738) { class_1738 armor = (class_1738)class_1792; if (!ElytraSwap.swapping && 
/* 19 */         this.delay.every(300L) && armor.method_48398() == class_1738.class_8051.field_41935 && 
/* 20 */         mc.field_1724.method_31548().method_7372(2).method_7909() == class_1802.field_8833) {
/* 21 */         mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/* 22 */         int empty = findEmptySlot();
/* 23 */         boolean needDrop = (empty == 999);
/* 24 */         if (needDrop)
/* 25 */           empty = 9; 
/* 26 */         mc.field_1761.method_2906(0, empty, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/* 27 */         if (needDrop)
/* 28 */           mc.field_1761.method_2906(0, -999, 1, class_1713.field_7790, (class_1657)mc.field_1724); 
/*    */       }  }
/*    */   
/*    */   }
/*    */   
/*    */   public static int findEmptySlot() {
/* 34 */     for (int i = 0; i < 36; i++) {
/* 35 */       if (mc.field_1724.method_31548().method_5438(i).method_7960()) return (i < 9) ? (i + 36) : i; 
/* 36 */     }  return 999;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\MouseElytraFix.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */