/*     */ package thunder.hack.utility.world;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_1267;
/*     */ import net.minecraft.class_1280;
/*     */ import net.minecraft.class_1293;
/*     */ import net.minecraft.class_1294;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1324;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1890;
/*     */ import net.minecraft.class_1893;
/*     */ import net.minecraft.class_1922;
/*     */ import net.minecraft.class_1927;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_259;
/*     */ import net.minecraft.class_265;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_5134;
/*     */ import org.apache.commons.lang3.mutable.MutableInt;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.injection.accesors.IExplosion;
/*     */ import thunder.hack.utility.math.PredictUtility;
/*     */ 
/*     */ 
/*     */ public final class ExplosionUtility
/*     */ {
/*     */   public static boolean terrainIgnore = false;
/*     */   public static class_1927 explosion;
/*     */   
/*     */   public static float getAutoCrystalDamage(class_243 crystalPos, class_1657 target, int predictTicks, boolean optimized) {
/*  45 */     if (predictTicks == 0) return getExplosionDamage(crystalPos, target, optimized);
/*     */     
/*  47 */     return getExplosionDamageWPredict(crystalPos, target, PredictUtility.predictBox(target, predictTicks), optimized);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getSelfExplosionDamage(class_243 explosionPos, int predictTicks, boolean optimized) {
/*  59 */     return getAutoCrystalDamage(explosionPos, (class_1657)Module.mc.field_1724, predictTicks, optimized);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getExplosionDamage(class_243 explosionPos, class_1657 target, boolean optimized) {
/*  71 */     if (Module.mc.field_1687.method_8407() == class_1267.field_5801 || target == null) return 0.0F;
/*     */     
/*  73 */     if (explosion == null) {
/*  74 */       explosion = new class_1927((class_1937)Module.mc.field_1687, (class_1297)Module.mc.field_1724, 1.0D, 33.0D, 7.0D, 6.0F, false, class_1927.class_4179.field_18687);
/*     */     }
/*  76 */     ((IExplosion)explosion).setX(explosionPos.field_1352);
/*  77 */     ((IExplosion)explosion).setY(explosionPos.field_1351);
/*  78 */     ((IExplosion)explosion).setZ(explosionPos.field_1350);
/*     */     
/*  80 */     if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) ((IExplosion)explosion).setWorld((class_1937)Module.mc.field_1687);
/*     */     
/*  82 */     if (!(new class_238(class_3532.method_15357(explosionPos.field_1352 - 11.0D), class_3532.method_15357(explosionPos.field_1351 - 11.0D), class_3532.method_15357(explosionPos.field_1350 - 11.0D), class_3532.method_15357(explosionPos.field_1352 + 13.0D), class_3532.method_15357(explosionPos.field_1351 + 13.0D), class_3532.method_15357(explosionPos.field_1350 + 13.0D))).method_994(target.method_5829())) {
/*  83 */       return 0.0F;
/*     */     }
/*  85 */     if (!target.method_5659(explosion) && !target.method_5655()) {
/*  86 */       double distExposure = (float)target.method_5707(explosionPos) / 144.0D;
/*  87 */       if (distExposure <= 1.0D) {
/*  88 */         terrainIgnore = ((Boolean)ModuleManager.autoCrystal.ignoreTerrain.getValue()).booleanValue();
/*  89 */         double exposure = getExposure(explosionPos, target.method_5829(), optimized);
/*  90 */         terrainIgnore = false;
/*  91 */         double finalExposure = (1.0D - distExposure) * exposure;
/*     */         
/*  93 */         float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0D * 7.0D * 12.0D + 1.0D);
/*     */         
/*  95 */         if (Module.mc.field_1687.method_8407() == class_1267.field_5805) { toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage); }
/*  96 */         else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) { toDamage = toDamage * 3.0F / 2.0F; }
/*     */         
/*  98 */         toDamage = class_1280.method_5496((class_1309)target, toDamage, ((IExplosion)explosion).getDamageSource(), target.method_6096(), (float)target.method_5996(class_5134.field_23725).method_6194());
/*     */         
/* 100 */         if (target.method_6059(class_1294.field_5907)) {
/* 101 */           int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
/* 102 */           float resistance_1 = toDamage * resistance;
/* 103 */           toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
/*     */         } 
/*     */         
/* 106 */         if (toDamage <= 0.0F) { toDamage = 0.0F; }
/*     */         else
/* 108 */         { float protAmount = ((Boolean)ModuleManager.autoCrystal.assumeBestArmor.getValue()).booleanValue() ? 32.0F : getProtectionAmount(target.method_5661());
/*     */           
/* 110 */           if (protAmount > 0.0F)
/* 111 */             toDamage = class_1280.method_5497(toDamage, protAmount);  }
/*     */         
/* 113 */         return toDamage;
/*     */       } 
/*     */     } 
/* 116 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getExplosionDamageWPredict(class_243 explosionPos, class_1657 target, class_238 predict, boolean optimized) {
/* 128 */     if (Module.mc.field_1687.method_8407() == class_1267.field_5801) return 0.0F;
/*     */     
/* 130 */     if (target == null || predict == null) return 0.0F;
/*     */     
/* 132 */     if (explosion == null) {
/* 133 */       explosion = new class_1927((class_1937)Module.mc.field_1687, (class_1297)Module.mc.field_1724, 1.0D, 33.0D, 7.0D, 6.0F, false, class_1927.class_4179.field_18687);
/*     */     }
/* 135 */     ((IExplosion)explosion).setX(explosionPos.field_1352);
/* 136 */     ((IExplosion)explosion).setY(explosionPos.field_1351);
/* 137 */     ((IExplosion)explosion).setZ(explosionPos.field_1350);
/*     */     
/* 139 */     if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) ((IExplosion)explosion).setWorld((class_1937)Module.mc.field_1687);
/*     */     
/* 141 */     if (!(new class_238(class_3532.method_15357(explosionPos.field_1352 - 11.0D), class_3532.method_15357(explosionPos.field_1351 - 11.0D), class_3532.method_15357(explosionPos.field_1350 - 11.0D), class_3532.method_15357(explosionPos.field_1352 + 13.0D), class_3532.method_15357(explosionPos.field_1351 + 13.0D), class_3532.method_15357(explosionPos.field_1350 + 13.0D))).method_994(predict)) {
/* 142 */       return 0.0F;
/*     */     }
/* 144 */     if (!target.method_5659(explosion) && !target.method_5655()) {
/* 145 */       double distExposure = predict.method_1005().method_1031(0.0D, -0.9D, 0.0D).method_1025(explosionPos) / 144.0D;
/* 146 */       if (distExposure <= 1.0D) {
/* 147 */         terrainIgnore = ((Boolean)ModuleManager.autoCrystal.ignoreTerrain.getValue()).booleanValue();
/* 148 */         double exposure = getExposure(explosionPos, predict, optimized);
/* 149 */         terrainIgnore = false;
/* 150 */         double finalExposure = (1.0D - distExposure) * exposure;
/*     */         
/* 152 */         float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0D * 7.0D * 12.0D + 1.0D);
/*     */         
/* 154 */         if (Module.mc.field_1687.method_8407() == class_1267.field_5805) { toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage); }
/* 155 */         else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) { toDamage = toDamage * 3.0F / 2.0F; }
/*     */         
/* 157 */         toDamage = class_1280.method_5496((class_1309)target, toDamage, ((IExplosion)explosion).getDamageSource(), target.method_6096(), (float)((class_1324)Objects.<class_1324>requireNonNull(target.method_5996(class_5134.field_23725))).method_6194());
/*     */         
/* 159 */         if (target.method_6059(class_1294.field_5907)) {
/* 160 */           int resistance = 25 - (((class_1293)Objects.<class_1293>requireNonNull(target.method_6112(class_1294.field_5907))).method_5578() + 1) * 5;
/* 161 */           float resistance_1 = toDamage * resistance;
/* 162 */           toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
/*     */         } 
/*     */         
/* 165 */         if (toDamage <= 0.0F) { toDamage = 0.0F; }
/*     */         else
/* 167 */         { float protAmount = ((Boolean)ModuleManager.autoCrystal.assumeBestArmor.getValue()).booleanValue() ? 32.0F : getProtectionAmount(target.method_5661());
/*     */           
/* 169 */           if (protAmount > 0.0F) toDamage = class_1280.method_5497(toDamage, protAmount);  }
/*     */         
/* 171 */         return toDamage;
/*     */       } 
/*     */     } 
/* 174 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class_3965 rayCastBlock(class_3959 context, class_2338 block) {
/* 185 */     return (class_3965)class_1922.method_17744(context.method_17750(), context.method_17747(), context, (raycastContext, blockPos) -> {
/*     */           class_2680 blockState;
/*     */           if (!blockPos.equals(block)) {
/*     */             blockState = class_2246.field_10124.method_9564();
/*     */           } else {
/*     */             blockState = class_2246.field_10540.method_9564();
/*     */           } 
/*     */           class_243 vec3d = raycastContext.method_17750();
/*     */           class_243 vec3d2 = raycastContext.method_17747();
/*     */           class_265 voxelShape = raycastContext.method_17748(blockState, (class_1922)Module.mc.field_1687, blockPos);
/*     */           class_3965 blockHitResult = Module.mc.field_1687.method_17745(vec3d, vec3d2, blockPos, voxelShape, blockState);
/*     */           class_265 voxelShape2 = class_259.method_1073();
/*     */           class_3965 blockHitResult2 = voxelShape2.method_1092(vec3d, vec3d2, blockPos);
/*     */           double d = (blockHitResult == null) ? Double.MAX_VALUE : raycastContext.method_17750().method_1025(blockHitResult.method_17784());
/*     */           double e = (blockHitResult2 == null) ? Double.MAX_VALUE : raycastContext.method_17750().method_1025(blockHitResult2.method_17784());
/*     */           return (d <= e) ? blockHitResult : blockHitResult2;
/*     */         }raycastContext -> {
/*     */           class_243 vec3d = raycastContext.method_17750().method_1020(raycastContext.method_17747());
/*     */           return class_3965.method_17778(raycastContext.method_17747(), class_2350.method_10142(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350), class_2338.method_49638((class_2374)raycastContext.method_17747()));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getDamageOfGhostBlock(class_243 explosionPos, class_1657 target, class_2338 bp) {
/* 218 */     if (Module.mc.field_1687.method_8407() == class_1267.field_5801) return 0.0F;
/*     */     
/* 220 */     if (explosion == null) {
/* 221 */       explosion = new class_1927((class_1937)Module.mc.field_1687, (class_1297)Module.mc.field_1724, 1.0D, 33.0D, 7.0D, 6.0F, false, class_1927.class_4179.field_18687);
/*     */     }
/* 223 */     ((IExplosion)explosion).setX(explosionPos.field_1352);
/* 224 */     ((IExplosion)explosion).setY(explosionPos.field_1351);
/* 225 */     ((IExplosion)explosion).setZ(explosionPos.field_1350);
/*     */     
/* 227 */     if (((IExplosion)explosion).getWorld() != Module.mc.field_1687) ((IExplosion)explosion).setWorld((class_1937)Module.mc.field_1687);
/*     */     
/* 229 */     double maxDist = 12.0D;
/* 230 */     if (!(new class_238(class_3532.method_15357(explosionPos.field_1352 - maxDist - 1.0D), class_3532.method_15357(explosionPos.field_1351 - maxDist - 1.0D), class_3532.method_15357(explosionPos.field_1350 - maxDist - 1.0D), class_3532.method_15357(explosionPos.field_1352 + maxDist + 1.0D), class_3532.method_15357(explosionPos.field_1351 + maxDist + 1.0D), class_3532.method_15357(explosionPos.field_1350 + maxDist + 1.0D))).method_994(target.method_5829())) {
/* 231 */       return 0.0F;
/*     */     }
/*     */     
/* 234 */     if (!target.method_5659(explosion) && !target.method_5655()) {
/* 235 */       double distExposure = target.method_5707(explosionPos) / 144.0D;
/* 236 */       if (distExposure <= 1.0D) {
/* 237 */         terrainIgnore = ((Boolean)ModuleManager.autoCrystal.ignoreTerrain.getValue()).booleanValue();
/* 238 */         double exposure = getExposureGhost(explosionPos, (class_1297)target, bp);
/* 239 */         terrainIgnore = false;
/* 240 */         double finalExposure = (1.0D - distExposure) * exposure;
/*     */         
/* 242 */         float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0D * 7.0D * maxDist + 1.0D);
/*     */         
/* 244 */         if (Module.mc.field_1687.method_8407() == class_1267.field_5805) {
/* 245 */           toDamage = Math.min(toDamage / 2.0F + 1.0F, toDamage);
/* 246 */         } else if (Module.mc.field_1687.method_8407() == class_1267.field_5807) {
/* 247 */           toDamage = toDamage * 3.0F / 2.0F;
/*     */         } 
/*     */         
/* 250 */         toDamage = class_1280.method_5496((class_1309)target, toDamage, ((IExplosion)explosion).getDamageSource(), target.method_6096(), (float)target.method_5996(class_5134.field_23725).method_6194());
/*     */         
/* 252 */         if (target.method_6059(class_1294.field_5907)) {
/* 253 */           int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
/* 254 */           float resistance_1 = toDamage * resistance;
/* 255 */           toDamage = Math.max(resistance_1 / 25.0F, 0.0F);
/*     */         } 
/*     */         
/* 258 */         if (toDamage <= 0.0F) { toDamage = 0.0F; }
/*     */         else
/* 260 */         { float protAmount = ((Boolean)ModuleManager.autoCrystal.assumeBestArmor.getValue()).booleanValue() ? 32.0F : getProtectionAmount(target.method_5661());
/*     */           
/* 262 */           if (protAmount > 0.0F) toDamage = class_1280.method_5497(toDamage, protAmount);  }
/*     */         
/* 264 */         return toDamage;
/*     */       } 
/*     */     } 
/* 267 */     return 0.0F;
/*     */   }
/*     */   
/*     */   private static float getExposureGhost(class_243 source, class_1297 entity, class_2338 pos) {
/* 271 */     class_238 box = entity.method_5829();
/* 272 */     double d = 1.0D / ((box.field_1320 - box.field_1323) * 2.0D + 1.0D);
/* 273 */     double e = 1.0D / ((box.field_1325 - box.field_1322) * 2.0D + 1.0D);
/* 274 */     double f = 1.0D / ((box.field_1324 - box.field_1321) * 2.0D + 1.0D);
/* 275 */     double g = (1.0D - Math.floor(1.0D / d) * d) / 2.0D;
/* 276 */     double h = (1.0D - Math.floor(1.0D / f) * f) / 2.0D;
/*     */     
/* 278 */     if (d < 0.0D || e < 0.0D || f < 0.0D) {
/* 279 */       return 0.0F;
/*     */     }
/*     */     
/* 282 */     int i = 0;
/* 283 */     int j = 0;
/*     */     double k;
/* 285 */     for (k = 0.0D; k <= 1.0D; k += d) {
/* 286 */       double l; for (l = 0.0D; l <= 1.0D; l += e) {
/* 287 */         double m; for (m = 0.0D; m <= 1.0D; m += f) {
/* 288 */           double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
/* 289 */           double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
/* 290 */           double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
/* 291 */           class_243 vec3d = new class_243(n + g, o, p + h);
/* 292 */           if (raycastGhost(new class_3959(vec3d, source, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, entity), pos).method_17783() == class_239.class_240.field_1333)
/* 293 */             i++; 
/* 294 */           j++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     return i / j;
/*     */   }
/*     */   
/*     */   public static float getExposure(class_243 source, class_238 box, boolean optimized) {
/* 303 */     if (!optimized) return getExposure(source, box);
/*     */     
/* 305 */     int miss = 0;
/* 306 */     int hit = 0;
/*     */     
/* 308 */     for (int k = 0; k <= 1; k++) {
/* 309 */       for (int l = 0; l <= 1; l++) {
/* 310 */         for (int m = 0; m <= 1; m++) {
/* 311 */           double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
/* 312 */           double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
/* 313 */           double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
/* 314 */           class_243 vec3d = new class_243(n, o, p);
/* 315 */           if (raycast(vec3d, source, ((Boolean)ModuleManager.autoCrystal.ignoreTerrain.getValue()).booleanValue()) == class_239.class_240.field_1333)
/* 316 */             miss++; 
/* 317 */           hit++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 321 */     return miss / hit;
/*     */   }
/*     */   
/*     */   public static float getExposure(class_243 source, class_238 box) {
/* 325 */     double d = 0.4545454446934474D;
/* 326 */     double e = 0.21739130885479366D;
/* 327 */     double f = 0.4545454446934474D;
/*     */     
/* 329 */     int i = 0;
/* 330 */     int j = 0;
/*     */     double k;
/* 332 */     for (k = 0.0D; k <= 1.0D; k += d) {
/* 333 */       double l; for (l = 0.0D; l <= 1.0D; l += e) {
/* 334 */         double m; for (m = 0.0D; m <= 1.0D; m += f) {
/* 335 */           double n = class_3532.method_16436(k, box.field_1323, box.field_1320);
/* 336 */           double o = class_3532.method_16436(l, box.field_1322, box.field_1325);
/* 337 */           double p = class_3532.method_16436(m, box.field_1321, box.field_1324);
/* 338 */           class_243 vec3d = new class_243(n + 0.045454555306552624D, o, p + 0.045454555306552624D);
/* 339 */           if (raycast(vec3d, source, ((Boolean)ModuleManager.autoCrystal.ignoreTerrain.getValue()).booleanValue()) == class_239.class_240.field_1333)
/* 340 */             i++; 
/* 341 */           j++;
/*     */         } 
/*     */       } 
/* 344 */     }  return i / j;
/*     */   }
/*     */   
/*     */   private static class_3965 raycastGhost(class_3959 context, class_2338 bPos) {
/* 348 */     return (class_3965)class_1922.method_17744(context.method_17750(), context.method_17747(), context, (innerContext, pos) -> {
/*     */           class_2680 blockState;
/*     */           class_243 vec3d = innerContext.method_17750();
/*     */           class_243 vec3d2 = innerContext.method_17747();
/*     */           if (!pos.equals(bPos)) {
/*     */             blockState = Module.mc.field_1687.method_8320(bPos);
/*     */           } else {
/*     */             blockState = class_2246.field_10540.method_9564();
/*     */           } 
/*     */           class_265 voxelShape = innerContext.method_17748(blockState, (class_1922)Module.mc.field_1687, pos);
/*     */           class_3965 blockHitResult = Module.mc.field_1687.method_17745(vec3d, vec3d2, pos, voxelShape, blockState);
/*     */           class_3965 blockHitResult2 = class_259.method_1073().method_1092(vec3d, vec3d2, pos);
/*     */           double d = (blockHitResult == null) ? Double.MAX_VALUE : innerContext.method_17750().method_1025(blockHitResult.method_17784());
/*     */           double e = (blockHitResult2 == null) ? Double.MAX_VALUE : innerContext.method_17750().method_1025(blockHitResult2.method_17784());
/*     */           return (d <= e) ? blockHitResult : blockHitResult2;
/*     */         }innerContext -> {
/*     */           class_243 vec3d = innerContext.method_17750().method_1020(innerContext.method_17747());
/*     */           return class_3965.method_17778(innerContext.method_17747(), class_2350.method_10142(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350), class_2338.method_49638((class_2374)innerContext.method_17747()));
/*     */         });
/*     */   }
/*     */   
/*     */   public static class_239.class_240 raycast(class_243 start, class_243 end, boolean ignoreTerrain) {
/* 370 */     return (class_239.class_240)class_1922.method_17744(start, end, null, (innerContext, blockPos) -> {
/*     */           class_2680 blockState = Module.mc.field_1687.method_8320(blockPos);
/*     */           if (blockState.method_26204().method_9520() < 600.0F && ignoreTerrain)
/*     */             return null; 
/*     */           class_3965 hitResult = blockState.method_26220((class_1922)Module.mc.field_1687, blockPos).method_1092(start, end, blockPos);
/*     */           return (hitResult == null) ? null : hitResult.method_17783();
/*     */         }innerContext -> class_239.class_240.field_1333);
/*     */   }
/*     */   
/*     */   public static int getProtectionAmount(Iterable<class_1799> equipment) {
/* 380 */     MutableInt mutableInt = new MutableInt();
/* 381 */     equipment.forEach(i -> mutableInt.add(getProtectionAmount(i)));
/* 382 */     return mutableInt.intValue();
/*     */   }
/*     */   
/*     */   public static int getProtectionAmount(class_1799 stack) {
/* 386 */     int modifierBlast = class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9107).get(), stack);
/* 387 */     int modifier = class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9111).get(), stack);
/* 388 */     return modifierBlast * 2 + modifier;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\world\ExplosionUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */