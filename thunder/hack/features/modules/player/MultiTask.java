/*    */ package thunder.hack.features.modules.player;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_239;
/*    */ import net.minecraft.class_3965;
/*    */ import net.minecraft.class_3966;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class MultiTask extends Module {
/*    */   public MultiTask() {
/* 10 */     super("MultiTask", Module.Category.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 15 */     class_239 class_239 = mc.field_1765; if (class_239 instanceof class_3965) { class_3965 crossHair = (class_3965)class_239; if (crossHair.method_17777() != null && mc.field_1690.field_1886.method_1434() && !mc.field_1687.method_8320(crossHair.method_17777()).method_26215()) {
/* 16 */         mc.field_1761.method_2910(crossHair.method_17777(), crossHair.method_17780());
/* 17 */         mc.field_1724.method_6104(class_1268.field_5808);
/*    */       }  }
/*    */     
/* 20 */     class_239 = mc.field_1765; if (class_239 instanceof class_3966) { class_3966 ehr = (class_3966)class_239; if (ehr.method_17782() != null && mc.field_1690.field_1886.method_1434() && mc.field_1724.method_7261(0.5F) > 0.9F) {
/* 21 */         mc.field_1761.method_2918((class_1657)mc.field_1724, ehr.method_17782());
/* 22 */         mc.field_1724.method_6104(class_1268.field_5808);
/*    */       }  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\MultiTask.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */