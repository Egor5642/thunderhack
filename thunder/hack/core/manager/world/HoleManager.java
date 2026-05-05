/*     */ package thunder.hack.core.manager.world;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_243;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ 
/*     */ public class HoleManager implements IManager {
/*  16 */   public static final class_2382[] VECTOR_PATTERN = new class_2382[] { new class_2382(0, 0, 1), new class_2382(0, 0, -1), new class_2382(1, 0, 0), new class_2382(-1, 0, 0) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<class_2338> getHolePoses(@NotNull class_243 from) {
/*  24 */     List<class_2338> positions = new ArrayList<>();
/*     */     
/*  26 */     double decimalX = from.method_10216() - Math.floor(from.method_10216());
/*  27 */     double decimalZ = from.method_10215() - Math.floor(from.method_10215());
/*  28 */     int offX = calcOffset(decimalX);
/*  29 */     int offZ = calcOffset(decimalZ);
/*  30 */     positions.add(getPos(from));
/*  31 */     for (int x = 0; x <= Math.abs(offX); x++) {
/*  32 */       for (int z = 0; z <= Math.abs(offZ); z++) {
/*  33 */         int properX = x * offX;
/*  34 */         int properZ = z * offZ;
/*  35 */         positions.add(((class_2338)Objects.<class_2338>requireNonNull(getPos(from))).method_10069(properX, 0, properZ));
/*     */       } 
/*     */     } 
/*     */     
/*  39 */     return positions;
/*     */   }
/*     */   @NotNull
/*     */   public List<class_2338> getSurroundPoses(@NotNull class_243 from) {
/*  43 */     class_2338 fromPos = class_2338.method_49638((class_2374)from);
/*  44 */     ArrayList<class_2338> tempOffsets = new ArrayList<>();
/*     */     
/*  46 */     double decimalX = Math.abs(from.method_10216()) - Math.floor(Math.abs(from.method_10216()));
/*  47 */     double decimalZ = Math.abs(from.method_10215()) - Math.floor(Math.abs(from.method_10215()));
/*  48 */     int lengthXPos = calcLength(decimalX, false);
/*  49 */     int lengthXNeg = calcLength(decimalX, true);
/*  50 */     int lengthZPos = calcLength(decimalZ, false);
/*  51 */     int lengthZNeg = calcLength(decimalZ, true);
/*     */     int x;
/*  53 */     for (x = 1; x < lengthXPos + 1; x++) {
/*  54 */       tempOffsets.add(addToPlayer(fromPos, x, 0.0D, (1 + lengthZPos)));
/*  55 */       tempOffsets.add(addToPlayer(fromPos, x, 0.0D, -(1 + lengthZNeg)));
/*     */     } 
/*  57 */     for (x = 0; x <= lengthXNeg; x++) {
/*  58 */       tempOffsets.add(addToPlayer(fromPos, -x, 0.0D, (1 + lengthZPos)));
/*  59 */       tempOffsets.add(addToPlayer(fromPos, -x, 0.0D, -(1 + lengthZNeg)));
/*     */     }  int z;
/*  61 */     for (z = 1; z < lengthZPos + 1; z++) {
/*  62 */       tempOffsets.add(addToPlayer(fromPos, (1 + lengthXPos), 0.0D, z));
/*  63 */       tempOffsets.add(addToPlayer(fromPos, -(1 + lengthXNeg), 0.0D, z));
/*     */     } 
/*  65 */     for (z = 0; z <= lengthZNeg; z++) {
/*  66 */       tempOffsets.add(addToPlayer(fromPos, (1 + lengthXPos), 0.0D, -z));
/*  67 */       tempOffsets.add(addToPlayer(fromPos, -(1 + lengthXNeg), 0.0D, -z));
/*     */     } 
/*     */     
/*  70 */     return tempOffsets;
/*     */   }
/*     */   @NotNull
/*     */   private class_2338 getPos(@NotNull class_243 from) {
/*  74 */     return class_2338.method_49637(from.method_10216(), (from.method_10214() - Math.floor(from.method_10214()) > 0.8D) ? (Math.floor(from.method_10214()) + 1.0D) : Math.floor(from.method_10214()), from.method_10215());
/*     */   }
/*     */   
/*     */   public int calcOffset(double dec) {
/*  78 */     return (dec >= 0.7D) ? 1 : ((dec <= 0.3D) ? -1 : 0);
/*     */   }
/*     */   
/*     */   public int calcLength(double decimal, boolean negative) {
/*  82 */     if (negative) return (decimal <= 0.3D) ? 1 : 0; 
/*  83 */     return (decimal >= 0.7D) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public class_2338 addToPlayer(@NotNull class_2338 playerPos, double x, double y, double z) {
/*  87 */     if (playerPos.method_10263() < 0) x = -x; 
/*  88 */     if (playerPos.method_10264() < 0) y = -y; 
/*  89 */     if (playerPos.method_10260() < 0) z = -z; 
/*  90 */     return playerPos.method_10081((class_2382)class_2338.method_49637(x, y, z));
/*     */   }
/*     */   
/*     */   public boolean isHole(class_2338 pos) {
/*  94 */     return (isSingleHole(pos) || 
/*  95 */       validTwoBlockIndestructible(pos) || validTwoBlockBedrock(pos) || 
/*  96 */       validQuadIndestructible(pos) || validQuadBedrock(pos));
/*     */   }
/*     */   
/*     */   public boolean isSingleHole(class_2338 pos) {
/* 100 */     return (validIndestructible(pos) || validBedrock(pos));
/*     */   }
/*     */   
/*     */   public boolean validIndestructible(@NotNull class_2338 pos) {
/* 104 */     return (!validBedrock(pos) && (
/* 105 */       isIndestructible(pos.method_10069(0, -1, 0)) || isBedrock(pos.method_10069(0, -1, 0))) && (
/* 106 */       isIndestructible(pos.method_10069(1, 0, 0)) || isBedrock(pos.method_10069(1, 0, 0))) && (
/* 107 */       isIndestructible(pos.method_10069(-1, 0, 0)) || isBedrock(pos.method_10069(-1, 0, 0))) && (
/* 108 */       isIndestructible(pos.method_10069(0, 0, 1)) || isBedrock(pos.method_10069(0, 0, 1))) && (
/* 109 */       isIndestructible(pos.method_10069(0, 0, -1)) || isBedrock(pos.method_10069(0, 0, -1))) && 
/* 110 */       isReplaceable(pos) && 
/* 111 */       isReplaceable(pos.method_10069(0, 1, 0)) && 
/* 112 */       isReplaceable(pos.method_10069(0, 2, 0)));
/*     */   }
/*     */   
/*     */   public boolean validBedrock(@NotNull class_2338 pos) {
/* 116 */     return (isBedrock(pos.method_10069(0, -1, 0)) && 
/* 117 */       isBedrock(pos.method_10069(1, 0, 0)) && 
/* 118 */       isBedrock(pos.method_10069(-1, 0, 0)) && 
/* 119 */       isBedrock(pos.method_10069(0, 0, 1)) && 
/* 120 */       isBedrock(pos.method_10069(0, 0, -1)) && 
/* 121 */       isReplaceable(pos) && 
/* 122 */       isReplaceable(pos.method_10069(0, 1, 0)) && 
/* 123 */       isReplaceable(pos.method_10069(0, 2, 0)));
/*     */   }
/*     */   
/*     */   public boolean validTwoBlockBedrock(@NotNull class_2338 pos) {
/* 127 */     if (!isReplaceable(pos)) return false; 
/* 128 */     class_2382 addVec = getTwoBlocksDirection(pos);
/*     */ 
/*     */     
/* 131 */     if (addVec == null) {
/* 132 */       return false;
/*     */     }
/* 134 */     class_2338[] checkPoses = { pos, pos.method_10081(addVec) };
/*     */     
/* 136 */     for (class_2338 checkPos : checkPoses) {
/* 137 */       class_2338 downPos = checkPos.method_10074();
/* 138 */       if (!isBedrock(downPos)) {
/* 139 */         return false;
/*     */       }
/* 141 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 142 */         class_2338 reducedPos = checkPos.method_10081(vec);
/* 143 */         if (!isBedrock(reducedPos) && !reducedPos.equals(pos) && !reducedPos.equals(pos.method_10081(addVec))) {
/* 144 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   public boolean validTwoBlockIndestructible(@NotNull class_2338 pos) {
/* 152 */     if (!isReplaceable(pos)) return false; 
/* 153 */     class_2382 addVec = getTwoBlocksDirection(pos);
/*     */ 
/*     */     
/* 156 */     if (addVec == null) {
/* 157 */       return false;
/*     */     }
/* 159 */     class_2338[] checkPoses = { pos, pos.method_10081(addVec) };
/*     */     
/* 161 */     boolean wasIndestrictible = false;
/* 162 */     for (class_2338 checkPos : checkPoses) {
/* 163 */       class_2338 downPos = checkPos.method_10074();
/* 164 */       if (isIndestructible(downPos)) {
/* 165 */         wasIndestrictible = true;
/* 166 */       } else if (!isBedrock(downPos)) {
/* 167 */         return false;
/*     */       } 
/* 169 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 170 */         class_2338 reducedPos = checkPos.method_10081(vec);
/*     */         
/* 172 */         if (isIndestructible(reducedPos)) {
/* 173 */           wasIndestrictible = true;
/*     */         
/*     */         }
/* 176 */         else if (!isBedrock(reducedPos) && !reducedPos.equals(pos) && !reducedPos.equals(pos.method_10081(addVec))) {
/* 177 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 181 */     return wasIndestrictible;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private class_2382 getTwoBlocksDirection(class_2338 pos) {
/* 186 */     for (class_2382 vec : VECTOR_PATTERN) {
/* 187 */       if (isReplaceable(pos.method_10081(vec))) {
/* 188 */         return vec;
/*     */       }
/*     */     } 
/* 191 */     return null;
/*     */   }
/*     */   
/*     */   public boolean validQuadIndestructible(@NotNull class_2338 pos) {
/* 195 */     List<class_2338> checkPoses = getQuadDirection(pos);
/*     */     
/* 197 */     if (checkPoses == null) {
/* 198 */       return false;
/*     */     }
/* 200 */     boolean wasIndestrictible = false;
/* 201 */     for (class_2338 checkPos : checkPoses) {
/* 202 */       class_2338 downPos = checkPos.method_10074();
/* 203 */       if (isIndestructible(downPos)) {
/* 204 */         wasIndestrictible = true;
/* 205 */       } else if (!isBedrock(downPos)) {
/* 206 */         return false;
/*     */       } 
/*     */       
/* 209 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 210 */         class_2338 reducedPos = checkPos.method_10081(vec);
/*     */         
/* 212 */         if (isIndestructible(reducedPos)) {
/* 213 */           wasIndestrictible = true;
/*     */         
/*     */         }
/* 216 */         else if (!isBedrock(reducedPos) && !checkPoses.contains(reducedPos)) {
/* 217 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 222 */     return wasIndestrictible;
/*     */   }
/*     */   
/*     */   public boolean validQuadBedrock(@NotNull class_2338 pos) {
/* 226 */     List<class_2338> checkPoses = getQuadDirection(pos);
/*     */     
/* 228 */     if (checkPoses == null) {
/* 229 */       return false;
/*     */     }
/* 231 */     for (class_2338 checkPos : checkPoses) {
/* 232 */       class_2338 downPos = checkPos.method_10074();
/* 233 */       if (!isBedrock(downPos)) {
/* 234 */         return false;
/*     */       }
/*     */       
/* 237 */       for (class_2382 vec : VECTOR_PATTERN) {
/* 238 */         class_2338 reducedPos = checkPos.method_10081(vec);
/* 239 */         if (!isBedrock(reducedPos) && !checkPoses.contains(reducedPos)) {
/* 240 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private List<class_2338> getQuadDirection(@NotNull class_2338 pos) {
/* 250 */     List<class_2338> dirList = new ArrayList<>();
/* 251 */     dirList.add(pos);
/*     */     
/* 253 */     if (!isReplaceable(pos)) {
/* 254 */       return null;
/*     */     }
/* 256 */     if (isReplaceable(pos.method_10069(1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, 1)) && isReplaceable(pos.method_10069(1, 0, 1))) {
/* 257 */       dirList.add(pos.method_10069(1, 0, 0));
/* 258 */       dirList.add(pos.method_10069(0, 0, 1));
/* 259 */       dirList.add(pos.method_10069(1, 0, 1));
/*     */     } 
/* 261 */     if (isReplaceable(pos.method_10069(-1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, -1)) && isReplaceable(pos.method_10069(-1, 0, -1))) {
/* 262 */       dirList.add(pos.method_10069(-1, 0, 0));
/* 263 */       dirList.add(pos.method_10069(0, 0, -1));
/* 264 */       dirList.add(pos.method_10069(-1, 0, -1));
/*     */     } 
/* 266 */     if (isReplaceable(pos.method_10069(1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, -1)) && isReplaceable(pos.method_10069(1, 0, -1))) {
/* 267 */       dirList.add(pos.method_10069(1, 0, 0));
/* 268 */       dirList.add(pos.method_10069(0, 0, -1));
/* 269 */       dirList.add(pos.method_10069(1, 0, -1));
/*     */     } 
/* 271 */     if (isReplaceable(pos.method_10069(-1, 0, 0)) && isReplaceable(pos.method_10069(0, 0, 1)) && isReplaceable(pos.method_10069(-1, 0, 1))) {
/* 272 */       dirList.add(pos.method_10069(-1, 0, 0));
/* 273 */       dirList.add(pos.method_10069(0, 0, 1));
/* 274 */       dirList.add(pos.method_10069(-1, 0, 1));
/*     */     } 
/*     */     
/* 277 */     if (dirList.size() != 4) {
/* 278 */       return null;
/*     */     }
/* 280 */     return dirList;
/*     */   }
/*     */   
/*     */   private boolean isIndestructible(class_2338 bp) {
/* 284 */     if (mc.field_1687 == null) return false;
/*     */     
/* 286 */     return (mc.field_1687.method_8320(bp).method_26204() == class_2246.field_10540 || mc.field_1687
/* 287 */       .method_8320(bp).method_26204() == class_2246.field_22108 || mc.field_1687
/* 288 */       .method_8320(bp).method_26204() == class_2246.field_22423 || mc.field_1687
/* 289 */       .method_8320(bp).method_26204() == class_2246.field_23152);
/*     */   }
/*     */   
/*     */   private boolean isBedrock(class_2338 bp) {
/* 293 */     if (mc.field_1687 == null) return false;
/*     */     
/* 295 */     return (mc.field_1687.method_8320(bp).method_26204() == class_2246.field_9987);
/*     */   }
/*     */   
/*     */   private boolean isReplaceable(class_2338 bp) {
/* 299 */     if (mc.field_1687 == null) return false;
/*     */     
/* 301 */     return mc.field_1687.method_8320(bp).method_45474();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\world\HoleManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */