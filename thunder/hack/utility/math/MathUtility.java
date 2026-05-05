/*     */ package thunder.hack.utility.math;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.util.Comparator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_3532;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public final class MathUtility {
/*     */   public static double random(double min, double max) {
/*  16 */     return ThreadLocalRandom.current().nextDouble() * (max - min) + min;
/*     */   }
/*     */   
/*     */   public static float random(float min, float max) {
/*  20 */     return (float)(Math.random() * (max - min) + min);
/*     */   }
/*     */   
/*     */   public static double getDistanceSq(double x, double y, double z) {
/*  24 */     double d0 = Module.mc.field_1724.method_23317() - x;
/*  25 */     double d1 = Module.mc.field_1724.method_23318() - y;
/*  26 */     double d2 = Module.mc.field_1724.method_23321() - z;
/*  27 */     return d0 * d0 + d1 * d1 + d2 * d2;
/*     */   }
/*     */   
/*     */   public static double getDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  31 */     double d0 = x1 - x2;
/*  32 */     double d1 = y1 - y2;
/*  33 */     double d2 = z1 - z2;
/*  34 */     return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */   }
/*     */   
/*     */   public static double getSqrDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  38 */     double d0 = x1 - x2;
/*  39 */     double d1 = y1 - y2;
/*  40 */     double d2 = z1 - z2;
/*  41 */     return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */   }
/*     */   
/*     */   public static float round(float value) {
/*  45 */     BigDecimal bd = new BigDecimal(value);
/*  46 */     bd = bd.setScale(2, RoundingMode.HALF_UP);
/*  47 */     return bd.floatValue();
/*     */   }
/*     */   
/*     */   public static double getDistanceSq(class_1297 ent) {
/*  51 */     return getDistanceSq(ent.method_23317(), ent.method_23318(), ent.method_23321());
/*     */   }
/*     */   
/*     */   public static double roundToDecimal(double n, int point) {
/*  55 */     if (point == 0) {
/*  56 */       return Math.floor(n);
/*     */     }
/*  58 */     double factor = Math.pow(10.0D, point);
/*  59 */     return Math.round(n * factor) / factor;
/*     */   }
/*     */   
/*     */   public static double angle(class_243 vec3d, class_243 other) {
/*  63 */     double lengthSq = vec3d.method_1033() * other.method_1033();
/*     */     
/*  65 */     if (lengthSq < 1.0E-4D) {
/*  66 */       return 0.0D;
/*     */     }
/*     */     
/*  69 */     double dot = vec3d.method_1026(other);
/*  70 */     double arg = dot / lengthSq;
/*     */     
/*  72 */     if (arg > 1.0D)
/*  73 */       return 0.0D; 
/*  74 */     if (arg < -1.0D) {
/*  75 */       return 180.0D;
/*     */     }
/*     */     
/*  78 */     return Math.acos(arg) * 180.0D / Math.PI;
/*     */   }
/*     */   
/*     */   public static class_243 fromTo(class_243 from, double x, double y, double z) {
/*  82 */     return fromTo(from.field_1352, from.field_1351, from.field_1350, x, y, z);
/*     */   }
/*     */   
/*     */   public static float lerp(float f, float st, float en) {
/*  86 */     return st + f * (en - st);
/*     */   }
/*     */   
/*     */   public static class_243 fromTo(double x, double y, double z, double x2, double y2, double z2) {
/*  90 */     return new class_243(x2 - x, y2 - y, z2 - z);
/*     */   }
/*     */   
/*     */   public static float rad(float angle) {
/*  94 */     return (float)(angle * Math.PI / 180.0D);
/*     */   }
/*     */   
/*     */   public static int clamp(int num, int min, int max) {
/*  98 */     return (num < min) ? min : Math.min(num, max);
/*     */   }
/*     */   
/*     */   public static float clamp(float num, float min, float max) {
/* 102 */     return (num < min) ? min : Math.min(num, max);
/*     */   }
/*     */   
/*     */   public static double clamp(double num, double min, double max) {
/* 106 */     return (num < min) ? min : Math.min(num, max);
/*     */   }
/*     */   
/*     */   public static float sin(float value) {
/* 110 */     return class_3532.method_15374(value);
/*     */   }
/*     */   
/*     */   public static float cos(float value) {
/* 114 */     return class_3532.method_15362(value);
/*     */   }
/*     */   
/*     */   public static float wrapDegrees(float value) {
/* 118 */     return class_3532.method_15393(value);
/*     */   }
/*     */   
/*     */   public static double wrapDegrees(double value) {
/* 122 */     return class_3532.method_15338(value);
/*     */   }
/*     */   
/*     */   public static double square(double input) {
/* 126 */     return input * input;
/*     */   }
/*     */   
/*     */   public static double round(double value, int places) {
/* 130 */     BigDecimal bd = BigDecimal.valueOf(value);
/* 131 */     bd = bd.setScale(places, RoundingMode.HALF_UP);
/* 132 */     return bd.doubleValue();
/*     */   }
/*     */   
/*     */   public static float wrap(float angle) {
/* 136 */     float wrappedAngle = angle % 360.0F;
/* 137 */     if (wrappedAngle >= 180.0F) {
/* 138 */       wrappedAngle -= 360.0F;
/*     */     }
/* 140 */     if (wrappedAngle < -180.0F) {
/* 141 */       wrappedAngle += 360.0F;
/*     */     }
/* 143 */     return wrappedAngle;
/*     */   }
/*     */   
/*     */   public static class_243 direction(float yaw) {
/* 147 */     return new class_243(Math.cos(degToRad((yaw + 90.0F))), 0.0D, Math.sin(degToRad((yaw + 90.0F))));
/*     */   }
/*     */   
/*     */   public static float round(float value, int places) {
/* 151 */     if (places < 0) {
/* 152 */       throw new IllegalArgumentException();
/*     */     }
/* 154 */     BigDecimal bd = BigDecimal.valueOf(value);
/* 155 */     bd = bd.setScale(places, RoundingMode.FLOOR);
/* 156 */     return bd.floatValue();
/*     */   }
/*     */   
/*     */   public static float round2(double value) {
/* 160 */     BigDecimal bd = new BigDecimal(value);
/* 161 */     bd = bd.setScale(2, RoundingMode.HALF_UP);
/* 162 */     return bd.floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map, boolean descending) {
/* 167 */     List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
/*     */     
/* 169 */     if (descending) {
/* 170 */       list.sort((Comparator)Map.Entry.comparingByValue(Comparator.reverseOrder()));
/*     */     } else {
/* 172 */       list.sort((Comparator)Map.Entry.comparingByValue());
/*     */     } 
/*     */     
/* 175 */     LinkedHashMap<K, V> result = new LinkedHashMap<>();
/* 176 */     for (Map.Entry<K, V> entry : list) {
/* 177 */       result.put(entry.getKey(), entry.getValue());
/*     */     }
/*     */     
/* 180 */     return result;
/*     */   }
/*     */   
/*     */   public static double degToRad(double deg) {
/* 184 */     return deg * 0.01745329238474369D;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\math\MathUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */