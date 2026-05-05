/*     */ package thunder.hack.utility.world;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_243;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public final class HoleUtility
/*     */ {
/*  18 */   public static final class_2382[] VECTOR_PATTERN = new class_2382[] { new class_2382(0, 0, 1), new class_2382(0, 0, -1), new class_2382(1, 0, 0), new class_2382(-1, 0, 0) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List<class_2338> getHolePoses(@NotNull class_243 from) {
/*  26 */     List<class_2338> positions = new ArrayList<>();
/*     */     
/*  28 */     double decimalX = from.method_10216() - Math.floor(from.method_10216());
/*  29 */     double decimalZ = from.method_10215() - Math.floor(from.method_10215());
/*  30 */     int offX = calcOffset(decimalX);
/*  31 */     int offZ = calcOffset(decimalZ);
/*  32 */     positions.add(getPos(from));
/*  33 */     for (int x = 0; x <= Math.abs(offX); x++) {
/*  34 */       for (int z = 0; z <= Math.abs(offZ); z++) {
/*  35 */         int properX = x * offX;
/*  36 */         int properZ = z * offZ;
/*  37 */         positions.add(((class_2338)Objects.<class_2338>requireNonNull(getPos(from))).method_10069(properX, 0, properZ));
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return positions;
/*     */   }
/*     */   @NotNull
/*     */   public static List<class_2338> getSurroundPoses(@NotNull class_243 from) {
/*  45 */     class_2338 fromPos = class_2338.method_49638((class_2374)from);
/*  46 */     ArrayList<class_2338> tempOffsets = new ArrayList<>();
/*     */     
/*  48 */     double decimalX = Math.abs(from.method_10216()) - Math.floor(Math.abs(from.method_10216()));
/*  49 */     double decimalZ = Math.abs(from.method_10215()) - Math.floor(Math.abs(from.method_10215()));
/*  50 */     int lengthXPos = calcLength(decimalX, false);
/*  51 */     int lengthXNeg = calcLength(decimalX, true);
/*  52 */     int lengthZPos = calcLength(decimalZ, false);
/*  53 */     int lengthZNeg = calcLength(decimalZ, true);
/*     */     int x;
/*  55 */     for (x = 1; x < lengthXPos + 1; x++) {
/*  56 */       tempOffsets.add(addToPlayer(fromPos, x, 0.0D, (1 + lengthZPos)));
/*  57 */       tempOffsets.add(addToPlayer(fromPos, x, 0.0D, -(1 + lengthZNeg)));
/*     */     } 
/*  59 */     for (x = 0; x <= lengthXNeg; x++) {
/*  60 */       tempOffsets.add(addToPlayer(fromPos, -x, 0.0D, (1 + lengthZPos)));
/*  61 */       tempOffsets.add(addToPlayer(fromPos, -x, 0.0D, -(1 + lengthZNeg)));
/*     */     }  int z;
/*  63 */     for (z = 1; z < lengthZPos + 1; z++) {
/*  64 */       tempOffsets.add(addToPlayer(fromPos, (1 + lengthXPos), 0.0D, z));
/*  65 */       tempOffsets.add(addToPlayer(fromPos, -(1 + lengthXNeg), 0.0D, z));
/*     */     } 
/*  67 */     for (z = 0; z <= lengthZNeg; z++) {
/*  68 */       tempOffsets.add(addToPlayer(fromPos, (1 + lengthXPos), 0.0D, -z));
/*  69 */       tempOffsets.add(addToPlayer(fromPos, -(1 + lengthXNeg), 0.0D, -z));
/*     */     } 
/*     */     
/*  72 */     return tempOffsets;
/*     */   }
/*     */   @NotNull
/*     */   private static class_2338 getPos(@NotNull class_243 from) {
/*  76 */     return class_2338.method_49637(from.method_10216(), (from.method_10214() - Math.floor(from.method_10214()) > 0.8D) ? (Math.floor(from.method_10214()) + 1.0D) : Math.floor(from.method_10214()), from.method_10215());
/*     */   }
/*     */   
/*     */   public static int calcOffset(double dec) {
/*  80 */     return (dec >= 0.7D) ? 1 : ((dec <= 0.3D) ? -1 : 0);
/*     */   }
/*     */   
/*     */   public static int calcLength(double decimal, boolean negative) {
/*  84 */     if (negative) return (decimal <= 0.3D) ? 1 : 0; 
/*  85 */     return (decimal >= 0.7D) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public static class_2338 addToPlayer(@NotNull class_2338 playerPos, double x, double y, double z) {
/*  89 */     if (playerPos.method_10263() < 0) x = -x; 
/*  90 */     if (playerPos.method_10264() < 0) y = -y; 
/*  91 */     if (playerPos.method_10260() < 0) z = -z; 
/*  92 */     return playerPos.method_10081((class_2382)class_2338.method_49637(x, y, z));
/*     */   }
/*     */   
/*     */   public static boolean isHole(class_2338 pos) {
/*  96 */     return (isSingleHole(pos) || 
/*  97 */       validTwoBlockIndestructible(pos) || validTwoBlockBedrock(pos) || 
/*  98 */       validQuadIndestructible(pos) || validQuadBedrock(pos));
/*     */   }
/*     */   
/*     */   public static boolean isSingleHole(class_2338 pos) {
/* 102 */     return (validIndestructible(pos) || validBedrock(pos));
/*     */   }
/*     */   
/*     */   public static boolean validIndestructible(@NotNull class_2338 pos) {
/* 106 */     return (!validBedrock(pos) && (
/* 107 */       isIndestructible(pos.method_10069(0, -1, 0)) || isBedrock(pos.method_10069(0, -1, 0))) && (
/* 108 */       isIndestructible(pos.method_10069(1, 0, 0)) || isBedrock(pos.method_10069(1, 0, 0))) && (
/* 109 */       isIndestructible(pos.method_10069(-1, 0, 0)) || isBedrock(pos.method_10069(-1, 0, 0))) && (
/* 110 */       isIndestructible(pos.method_10069(0, 0, 1)) || isBedrock(pos.method_10069(0, 0, 1))) && (
/* 111 */       isIndestructible(pos.method_10069(0, 0, -1)) || isBedrock(pos.method_10069(0, 0, -1))) && 
/* 112 */       isReplaceable(pos) && 
/* 113 */       isReplaceable(pos.method_10069(0, 1, 0)) && 
/* 114 */       isReplaceable(pos.method_10069(0, 2, 0)));
/*     */   }
/*     */   
/*     */   public static boolean validBedrock(@NotNull class_2338 pos) {
/* 118 */     return (isBedrock(pos.method_10069(0, -1, 0)) && 
/* 119 */       isBedrock(pos.method_10069(1, 0, 0)) && 
/* 120 */       isBedrock(pos.method_10069(-1, 0, 0)) && 
/* 121 */       isBedrock(pos.method_10069(0, 0, 1)) && 
/* 122 */       isBedrock(pos.method_10069(0, 0, -1)) && 
/* 123 */       isReplaceable(pos) && 
/* 124 */       isReplaceable(pos.method_10069(0, 1, 0)) && 
/* 125 */       isReplaceable(pos.method_10069(0, 2, 0)));
/*     */   }
/*     */   
/*     */   public static boolean validTwoBlockBedrock(@NotNull class_2338 pos) {
/* 129 */     if (!isReplaceable(pos)) return false; 
/* 130 */     class_2382 addVec = getTwoBlocksDirection(pos);
/*     */ 
/*     */     
/* 133 */     if (addVec == null) {
/* 134 */       return false;
/*     */     }
/* 136 */     class_2338[] checkPoses = { pos, pos.method_10081(addVec) };
/*     */     
/* 138 */     for (class_2338 checkPos : checkPoses) {
/*     */       
/* 140 */       if (!isReplaceable(checkPos.method_10069(0, 1, 0)) || !isReplaceable(checkPos.method_10069(0, 2, 0))) {
/* 141 */         return false;
/*     */       }
/* 143 */       class_2338 downPos = checkPos.method_10074();
/* 144 */       if (!isBedrock(downPos)) {
/* 145 */         return false;
/*     */       }
/* 147 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 148 */         class_2338 reducedPos = checkPos.method_10081(vec);
/* 149 */         if (!isBedrock(reducedPos) && !reducedPos.equals(pos) && !reducedPos.equals(pos.method_10081(addVec))) {
/* 150 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean validTwoBlockIndestructible(@NotNull class_2338 pos) {
/* 158 */     if (!isReplaceable(pos)) return false; 
/* 159 */     class_2382 addVec = getTwoBlocksDirection(pos);
/*     */ 
/*     */     
/* 162 */     if (addVec == null) {
/* 163 */       return false;
/*     */     }
/* 165 */     class_2338[] checkPoses = { pos, pos.method_10081(addVec) };
/*     */     
/* 167 */     boolean wasIndestrictible = false;
/* 168 */     for (class_2338 checkPos : checkPoses) {
/* 169 */       class_2338 downPos = checkPos.method_10074();
/*     */       
/* 171 */       if (isIndestructible(downPos)) {
/* 172 */         wasIndestrictible = true;
/* 173 */       } else if (!isBedrock(downPos)) {
/* 174 */         return false;
/*     */       } 
/* 176 */       if (!isReplaceable(checkPos.method_10069(0, 1, 0)) || !isReplaceable(checkPos.method_10069(0, 2, 0))) {
/* 177 */         return false;
/*     */       }
/* 179 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 180 */         class_2338 reducedPos = checkPos.method_10081(vec);
/*     */         
/* 182 */         if (isIndestructible(reducedPos)) {
/* 183 */           wasIndestrictible = true;
/*     */         
/*     */         }
/* 186 */         else if (!isBedrock(reducedPos) && !reducedPos.equals(pos) && !reducedPos.equals(pos.method_10081(addVec))) {
/* 187 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 191 */     return wasIndestrictible;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static class_2382 getTwoBlocksDirection(class_2338 pos) {
/* 196 */     for (class_2382 vec : VECTOR_PATTERN) {
/* 197 */       if (isReplaceable(pos.method_10081(vec))) {
/* 198 */         return vec;
/*     */       }
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean validQuadIndestructible(@NotNull class_2338 pos) {
/* 205 */     List<class_2338> checkPoses = getQuadDirection(pos);
/*     */     
/* 207 */     if (checkPoses == null) {
/* 208 */       return false;
/*     */     }
/* 210 */     boolean wasIndestrictible = false;
/* 211 */     for (class_2338 checkPos : checkPoses) {
/* 212 */       class_2338 downPos = checkPos.method_10074();
/* 213 */       if (isIndestructible(downPos)) {
/* 214 */         wasIndestrictible = true;
/* 215 */       } else if (!isBedrock(downPos)) {
/* 216 */         return false;
/*     */       } 
/*     */       
/* 219 */       if (!isReplaceable(checkPos.method_10069(0, 1, 0)) || !isReplaceable(checkPos.method_10069(0, 2, 0))) {
/* 220 */         return false;
/*     */       }
/* 222 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 223 */         class_2338 reducedPos = checkPos.method_10081(vec);
/*     */         
/* 225 */         if (isIndestructible(reducedPos)) {
/* 226 */           wasIndestrictible = true;
/*     */         
/*     */         }
/* 229 */         else if (!isBedrock(reducedPos) && !checkPoses.contains(reducedPos)) {
/* 230 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     return wasIndestrictible;
/*     */   }
/*     */   
/*     */   public static boolean validQuadBedrock(@NotNull class_2338 pos) {
/* 239 */     List<class_2338> checkPoses = getQuadDirection(pos);
/*     */     
/* 241 */     if (checkPoses == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     for (class_2338 checkPos : checkPoses) {
/* 245 */       class_2338 downPos = checkPos.method_10074();
/* 246 */       if (!isBedrock(downPos)) {
/* 247 */         return false;
/*     */       }
/* 249 */       if (!isReplaceable(checkPos.method_10069(0, 1, 0)) || !isReplaceable(checkPos.method_10069(0, 2, 0))) {
/* 250 */         return false;
/*     */       }
/* 252 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 253 */         class_2338 reducedPos = checkPos.method_10081(vec);
/* 254 */         if (!isBedrock(reducedPos) && !checkPoses.contains(reducedPos)) {
/* 255 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 260 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static List<class_2338> getQuadDirection(@NotNull class_2338 pos) {
/* 265 */     List<class_2338> dirList = new ArrayList<>();
/* 266 */     dirList.add(pos);
/*     */     
/* 268 */     if (!isReplaceable(pos)) {
/* 269 */       return null;
/*     */     }
/* 271 */     if (isReplaceable(pos.method_10069(1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, 1)) && isReplaceable(pos.method_10069(1, 0, 1))) {
/* 272 */       dirList.add(pos.method_10069(1, 0, 0));
/* 273 */       dirList.add(pos.method_10069(0, 0, 1));
/* 274 */       dirList.add(pos.method_10069(1, 0, 1));
/*     */     } 
/* 276 */     if (isReplaceable(pos.method_10069(-1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, -1)) && isReplaceable(pos.method_10069(-1, 0, -1))) {
/* 277 */       dirList.add(pos.method_10069(-1, 0, 0));
/* 278 */       dirList.add(pos.method_10069(0, 0, -1));
/* 279 */       dirList.add(pos.method_10069(-1, 0, -1));
/*     */     } 
/* 281 */     if (isReplaceable(pos.method_10069(1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, -1)) && isReplaceable(pos.method_10069(1, 0, -1))) {
/* 282 */       dirList.add(pos.method_10069(1, 0, 0));
/* 283 */       dirList.add(pos.method_10069(0, 0, -1));
/* 284 */       dirList.add(pos.method_10069(1, 0, -1));
/*     */     } 
/* 286 */     if (isReplaceable(pos.method_10069(-1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, 1)) && isReplaceable(pos.method_10069(-1, 0, 1))) {
/* 287 */       dirList.add(pos.method_10069(-1, 0, 0));
/* 288 */       dirList.add(pos.method_10069(0, 0, 1));
/* 289 */       dirList.add(pos.method_10069(-1, 0, 1));
/*     */     } 
/*     */     
/* 292 */     if (dirList.size() != 4) {
/* 293 */       return null;
/*     */     }
/* 295 */     return dirList;
/*     */   }
/*     */   
/*     */   private static boolean isIndestructible(class_2338 bp) {
/* 299 */     if (Module.mc.field_1687 == null) return false;
/*     */     
/* 301 */     class_2248 block = Module.mc.field_1687.method_8320(bp).method_26204();
/* 302 */     return (block == class_2246.field_10540 || block == class_2246.field_22108 || block == class_2246.field_22423 || block == class_2246.field_23152);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isBedrock(class_2338 bp) {
/* 307 */     if (Module.mc.field_1687 == null) return false;
/*     */     
/* 309 */     return (Module.mc.field_1687.method_8320(bp).method_26204() == class_2246.field_9987);
/*     */   }
/*     */   
/*     */   private static boolean isReplaceable(class_2338 bp) {
/* 313 */     if (Module.mc.field_1687 == null) return false;
/*     */     
/* 315 */     return Module.mc.field_1687.method_8320(bp).method_45474();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\world\HoleUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */