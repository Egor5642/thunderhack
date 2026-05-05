/*    */ package thunder.hack.features.modules.player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1890;
/*    */ import net.minecraft.class_1893;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2868;
/*    */ import net.minecraft.class_3965;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AutoTool
/*    */   extends Module
/*    */ {
/* 18 */   public static Setting<Boolean> swapBack = new Setting("SwapBack", Boolean.valueOf(true));
/* 19 */   public static Setting<Boolean> saveItem = new Setting("SaveItem", Boolean.valueOf(true));
/* 20 */   public static Setting<Boolean> silent = new Setting("Silent", Boolean.valueOf(false));
/* 21 */   public static Setting<Boolean> echestSilk = new Setting("EchestSilk", Boolean.valueOf(true));
/*    */   public static int itemIndex;
/*    */   private boolean swap;
/*    */   private long swapDelay;
/* 25 */   private final List<Integer> lastItem = new ArrayList<>();
/*    */   
/*    */   public AutoTool() {
/* 28 */     super("AutoTool", Module.Category.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 33 */     if (!(mc.field_1765 instanceof class_3965))
/* 34 */       return;  class_3965 result = (class_3965)mc.field_1765;
/* 35 */     class_2338 pos = result.method_17777();
/* 36 */     if (mc.field_1687.method_8320(pos).method_26215()) {
/*    */       return;
/*    */     }
/* 39 */     if (getTool(pos) != -1 && mc.field_1690.field_1886.method_1434()) {
/* 40 */       this.lastItem.add(Integer.valueOf((mc.field_1724.method_31548()).field_7545));
/*    */       
/* 42 */       if (((Boolean)silent.getValue()).booleanValue()) { mc.field_1724.field_3944.method_52787((class_2596)new class_2868(getTool(pos))); }
/* 43 */       else { (mc.field_1724.method_31548()).field_7545 = getTool(pos); }
/*    */       
/* 45 */       itemIndex = getTool(pos);
/* 46 */       this.swap = true;
/*    */       
/* 48 */       this.swapDelay = System.currentTimeMillis();
/* 49 */     } else if (this.swap && !this.lastItem.isEmpty() && System.currentTimeMillis() >= this.swapDelay + 300L && ((Boolean)swapBack.getValue()).booleanValue()) {
/* 50 */       if (((Boolean)silent.getValue()).booleanValue())
/* 51 */       { mc.field_1724.field_3944.method_52787((class_2596)new class_2868(((Integer)this.lastItem.get(0)).intValue())); }
/* 52 */       else { (mc.field_1724.method_31548()).field_7545 = ((Integer)this.lastItem.get(0)).intValue(); }
/*    */       
/* 54 */       itemIndex = ((Integer)this.lastItem.get(0)).intValue();
/* 55 */       this.lastItem.clear();
/* 56 */       this.swap = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getTool(class_2338 pos) {
/* 61 */     int index = -1;
/* 62 */     float CurrentFastest = 1.0F;
/* 63 */     for (int i = 0; i < 9; i++) {
/* 64 */       class_1799 stack = mc.field_1724.method_31548().method_5438(i);
/* 65 */       if (stack != class_1799.field_8037 && (
/* 66 */         mc.field_1724.method_31548().method_5438(i).method_7936() - mc.field_1724.method_31548().method_5438(i).method_7919() > 10 || !((Boolean)saveItem.getValue()).booleanValue())) {
/*    */ 
/*    */         
/* 69 */         float digSpeed = class_1890.method_8225(mc.field_1687.method_30349().method_30530(class_1893.field_9131.method_58273()).method_40264(class_1893.field_9131).get(), stack);
/* 70 */         float destroySpeed = stack.method_7924(mc.field_1687.method_8320(pos));
/*    */         
/* 72 */         if (mc.field_1687.method_8320(pos).method_26204() instanceof net.minecraft.class_2189) return -1; 
/* 73 */         if (mc.field_1687.method_8320(pos).method_26204() instanceof net.minecraft.class_2336 && ((Boolean)echestSilk.getValue()).booleanValue()) {
/* 74 */           if (class_1890.method_8225(mc.field_1687.method_30349().method_30530(class_1893.field_9099.method_58273()).method_40264(class_1893.field_9099).get(), stack) > 0 && digSpeed + destroySpeed > CurrentFastest) {
/* 75 */             CurrentFastest = digSpeed + destroySpeed;
/* 76 */             index = i;
/*    */           } 
/* 78 */         } else if (digSpeed + destroySpeed > CurrentFastest) {
/* 79 */           CurrentFastest = digSpeed + destroySpeed;
/* 80 */           index = i;
/*    */         } 
/*    */       } 
/*    */     } 
/* 84 */     return index;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AutoTool.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */