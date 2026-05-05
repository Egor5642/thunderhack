/*    */ package thunder.hack.gui.misc;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1263;
/*    */ import net.minecraft.class_1277;
/*    */ import net.minecraft.class_1661;
/*    */ import net.minecraft.class_1733;
/*    */ import net.minecraft.class_1747;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_495;
/*    */ import net.minecraft.class_9288;
/*    */ import net.minecraft.class_9334;
/*    */ import thunder.hack.features.modules.render.Tooltips;
/*    */ 
/*    */ public class PeekScreen extends class_495 {
/* 20 */   private static final class_1799[] ITEMS = new class_1799[27];
/*    */   
/*    */   public PeekScreen(class_1733 handler, class_1661 inventory, class_2561 title, class_2248 block) {
/* 23 */     super(handler, inventory, title);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean method_25402(double mouseX, double mouseY, int button) {
/* 28 */     if (button == 2 && this.field_2787 != null && !this.field_2787.method_7677().method_7960() && this.field_22787.field_1724.field_7498.method_34255().method_7960()) {
/* 29 */       class_1799 itemStack = this.field_2787.method_7677();
/*    */       
/* 31 */       if (Tooltips.hasItems(itemStack) && ((Boolean)Tooltips.middleClickOpen.getValue()).booleanValue()) {
/*    */         
/* 33 */         Arrays.fill((Object[])ITEMS, class_1799.field_8037);
/* 34 */         class_9288 nbt = (class_9288)itemStack.method_57824(class_9334.field_49622);
/* 35 */         if (nbt != null) {
/* 36 */           List<class_1799> list = nbt.method_57489().toList();
/* 37 */           for (int i = 0; i < list.size(); i++) {
/* 38 */             ITEMS[i] = list.get(i);
/*    */           }
/*    */         } 
/*    */         
/* 42 */         this.field_22787.method_1507((class_437)new PeekScreen(new class_1733(0, this.field_22787.field_1724.method_31548(), (class_1263)new class_1277(ITEMS)), this.field_22787.field_1724.method_31548(), this.field_2787.method_7677().method_7964(), ((class_1747)this.field_2787.method_7677().method_7909()).method_7711()));
/* 43 */         return true;
/*    */       } 
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean method_25406(double mouseX, double mouseY, int button) {
/* 51 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\misc\PeekScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */