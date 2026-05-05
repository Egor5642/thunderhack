/*    */ package thunder.hack.utility.math;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.class_1293;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_243;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ public class PredictUtility {
/*    */   public static class_1657 movePlayer(class_1657 entity, class_243 newPos) {
/* 17 */     if (entity == null || newPos == null)
/* 18 */       return null; 
/* 19 */     return equipAndReturn(entity, newPos);
/*    */   }
/*    */   
/*    */   public static class_1657 predictPlayer(class_1657 entity, int ticks) {
/* 23 */     class_243 posVec = predictPosition(entity, ticks);
/* 24 */     if (posVec == null)
/* 25 */       return null; 
/* 26 */     return equipAndReturn(entity, posVec);
/*    */   }
/*    */   
/*    */   public static class_243 predictPosition(class_1657 entity, int ticks) {
/* 30 */     if (entity == null) {
/* 31 */       return null;
/*    */     }
/* 33 */     class_243 posVec = new class_243(entity.method_23317(), entity.method_23318(), entity.method_23321());
/*    */     
/* 35 */     double motionX = entity.method_18798().method_10216();
/* 36 */     double motionZ = entity.method_18798().method_10215();
/*    */     
/* 38 */     for (int i = 0; i < ticks; i++) {
/* 39 */       float hbDeltaX = (motionX > 0.0D) ? 0.3F : -0.3F;
/* 40 */       float hbDeltaZ = (motionZ > 0.0D) ? 0.3F : -0.3F;
/*    */       
/* 42 */       if (!Module.mc.field_1687.method_22347(class_2338.method_49638((class_2374)posVec.method_1031(motionX + hbDeltaX, 0.1D, motionZ + hbDeltaZ))) || !Module.mc.field_1687.method_22347(class_2338.method_49638((class_2374)posVec.method_1031(motionX + hbDeltaX, 1.0D, motionZ + hbDeltaZ)))) {
/* 43 */         motionX = 0.0D;
/* 44 */         motionZ = 0.0D;
/*    */       } 
/* 46 */       posVec = posVec.method_1031(motionX, 0.0D, motionZ);
/*    */     } 
/*    */     
/* 49 */     return posVec;
/*    */   }
/*    */   
/*    */   public static class_238 predictBox(class_1657 entity, int ticks) {
/* 53 */     class_243 posVec = predictPosition(entity, ticks);
/* 54 */     if (posVec == null)
/* 55 */       return null; 
/* 56 */     return createBox(posVec, (class_1297)entity);
/*    */   }
/*    */   
/*    */   public static class_1657 equipAndReturn(class_1657 original, class_243 posVec) {
/* 60 */     class_1657 copyEntity = new class_1657((class_1937)Module.mc.field_1687, original.method_24515(), original.method_36454(), new GameProfile(UUID.fromString("66123666-1234-5432-6666-667563866600"), "PredictEntity339"))
/*    */       {
/*    */         public boolean method_7325() {
/* 63 */           return false;
/*    */         }
/*    */ 
/*    */         
/*    */         public boolean method_7337() {
/* 68 */           return false;
/*    */         }
/*    */       };
/*    */     
/* 72 */     copyEntity.method_33574(posVec);
/* 73 */     copyEntity.method_6033(original.method_6032());
/* 74 */     copyEntity.field_6014 = original.field_6014;
/* 75 */     copyEntity.field_5969 = original.field_5969;
/* 76 */     copyEntity.field_6036 = original.field_6036;
/* 77 */     copyEntity.method_31548().method_7377(original.method_31548());
/* 78 */     for (class_1293 se : original.method_6026()) {
/* 79 */       copyEntity.method_6092(se);
/*    */     }
/*    */     
/* 82 */     return copyEntity;
/*    */   }
/*    */   
/*    */   public static class_238 createBox(class_243 vec, class_1297 entity) {
/* 86 */     return entity.method_5829().method_997(entity.method_19538().method_1035(vec));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\math\PredictUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */