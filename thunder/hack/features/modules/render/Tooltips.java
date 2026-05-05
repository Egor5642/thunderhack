/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_9288;
/*    */ import net.minecraft.class_9334;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class Tooltips extends Module {
/*    */   public Tooltips() {
/* 11 */     super("Tooltips", Module.Category.MISC);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 17 */     this.shulkerRegear = new Setting("ShulkerRegear", Boolean.valueOf(true));
/* 18 */     this.shulkerRegearShiftMode = new Setting("RegearShift", Boolean.valueOf(true));
/*    */   }
/*    */   public static boolean hasItems(class_1799 itemStack) {
/* 21 */     class_9288 compoundTag = (class_9288)itemStack.method_57824(class_9334.field_49622);
/* 22 */     return (compoundTag != null && !compoundTag.method_57489().toList().isEmpty());
/*    */   }
/*    */   
/*    */   public static final Setting<Boolean> middleClickOpen = new Setting("MiddleClickOpen", Boolean.valueOf(true));
/*    */   public static final Setting<Boolean> storage = new Setting("Storage", Boolean.valueOf(true));
/*    */   public static final Setting<Boolean> maps = new Setting("Maps", Boolean.valueOf(true));
/*    */   public final Setting<Boolean> shulkerRegear;
/*    */   public final Setting<Boolean> shulkerRegearShiftMode;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Tooltips.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */