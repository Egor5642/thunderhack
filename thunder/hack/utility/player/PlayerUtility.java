/*     */ package thunder.hack.utility.player;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_241;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_746;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.world.ExplosionUtility;
/*     */ 
/*     */ 
/*     */ public final class PlayerUtility
/*     */ {
/*     */   public static boolean isInHell() {
/*  18 */     if (Module.mc.field_1687 == null) return false; 
/*  19 */     return Objects.equals(Module.mc.field_1687.method_27983().method_29177().method_12832(), "the_nether");
/*     */   }
/*     */   
/*     */   public static boolean isInEnd() {
/*  23 */     if (Module.mc.field_1687 == null) return false; 
/*  24 */     return Objects.equals(Module.mc.field_1687.method_27983().method_29177().method_12832(), "the_end");
/*     */   }
/*     */   
/*     */   public static boolean isInOver() {
/*  28 */     if (Module.mc.field_1687 == null) return false; 
/*  29 */     return Objects.equals(Module.mc.field_1687.method_27983().method_29177().method_12832(), "overworld");
/*     */   }
/*     */   
/*     */   public static boolean isEating() {
/*  33 */     if (Module.mc.field_1724 == null) return false;
/*     */     
/*  35 */     return ((Module.mc.field_1724.method_6047().method_57353().method_57832(class_9334.field_50075) || Module.mc.field_1724
/*  36 */       .method_6079().method_57353().method_57832(class_9334.field_50075)) && Module.mc.field_1724
/*  37 */       .method_6115());
/*     */   }
/*     */   
/*     */   public static boolean isMining() {
/*  41 */     if (Module.mc.field_1761 == null) return false;
/*     */     
/*  43 */     return Module.mc.field_1761.method_2923();
/*     */   }
/*     */   
/*     */   public static float squaredDistanceFromEyes(@NotNull class_243 targetPos) {
/*  47 */     if (Module.mc.field_1724 == null) return 0.0F;
/*     */     
/*  49 */     double dx = targetPos.field_1352 - Module.mc.field_1724.method_23317();
/*  50 */     double dy = targetPos.field_1351 - Module.mc.field_1724.method_23318() + Module.mc.field_1724.method_18381(Module.mc.field_1724.method_18376());
/*  51 */     double dz = targetPos.field_1350 - Module.mc.field_1724.method_23321();
/*     */     
/*  53 */     return (float)(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float squaredDistance2d(@NotNull class_241 point) {
/*  58 */     if (Module.mc.field_1724 == null) return 0.0F;
/*     */     
/*  60 */     double d = Module.mc.field_1724.method_23317() - point.field_1343;
/*  61 */     double f = Module.mc.field_1724.method_23321() - point.field_1342;
/*  62 */     return (float)(d * d + f * f);
/*     */   }
/*     */   
/*     */   public static class_746 getPlayer() {
/*  66 */     return Module.mc.field_1724;
/*     */   }
/*     */   
/*     */   public static float calculatePercentage(@NotNull class_1799 stack) {
/*  70 */     float durability = (stack.method_7936() - stack.method_7919());
/*  71 */     return durability / stack.method_7936() * 100.0F;
/*     */   }
/*     */   
/*     */   public static float fixAngle(float angle) {
/*  75 */     return Math.round(angle / (float)(getGCD() * 0.15D)) * (float)(getGCD() * 0.15D);
/*     */   }
/*     */   
/*     */   public static float getGCD() {
/*  79 */     double sensitivity = ((Double)Module.mc.field_1690.method_42495().method_41753()).doubleValue();
/*  80 */     double value = sensitivity * 0.6D + 0.2D;
/*  81 */     double result = Math.pow(value, 3.0D) * 8.0D;
/*     */     
/*  83 */     return (float)result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float squaredDistance2d(double x, double z) {
/*  88 */     if (Module.mc.field_1724 == null) return 0.0F;
/*     */     
/*  90 */     double d = Module.mc.field_1724.method_23317() - x;
/*  91 */     double f = Module.mc.field_1724.method_23321() - z;
/*  92 */     return (float)(d * d + f * f);
/*     */   }
/*     */   
/*     */   public static float getSquaredDistance2D(class_243 vec) {
/*  96 */     double d0 = Module.mc.field_1724.method_23317() - vec.method_10216();
/*  97 */     double d2 = Module.mc.field_1724.method_23321() - vec.method_10215();
/*  98 */     return (float)(d0 * d0 + d2 * d2);
/*     */   }
/*     */   
/*     */   public static boolean canSee(class_243 pos) {
/* 102 */     class_243 vec3d = new class_243(Module.mc.field_1724.method_23317(), Module.mc.field_1724.method_23320(), Module.mc.field_1724.method_23321());
/* 103 */     if (pos.method_1022(vec3d) > 128.0D) {
/* 104 */       return false;
/*     */     }
/* 106 */     return (ExplosionUtility.raycast(vec3d, pos, false) == class_239.class_240.field_1333);
/*     */   }
/*     */   
/*     */   public static boolean isFalling() {
/* 110 */     if (Module.mc.field_1724 == null) {
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     return (!Module.mc.field_1724.method_24828() && !Module.mc.field_1724.method_7337() && (Module.mc.field_1724.method_18798()).field_1351 < 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\player\PlayerUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */