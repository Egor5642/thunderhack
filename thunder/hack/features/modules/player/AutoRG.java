/*     */ package thunder.hack.features.modules.player;
/*     */ 
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_3965;
/*     */ import thunder.hack.events.impl.EventTick;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.SearchInvResult;
/*     */ 
/*     */ public final class AutoRG extends Module {
/*  19 */   private int delayTick = 0;
/*  20 */   private final class_2338.class_2339 mut = new class_2338.class_2339();
/*  21 */   private final class_2338.class_2339 neighborMut = new class_2338.class_2339();
/*     */   
/*  23 */   private static final class_2350[] FAST_DIRS = new class_2350[] { class_2350.field_11036, class_2350.field_11043, class_2350.field_11035, class_2350.field_11034, class_2350.field_11039, class_2350.field_11033 };
/*     */   
/*     */   public AutoRG() {
/*  26 */     super("AutoRG", Module.Category.PLAYER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  31 */     this.delayTick = 0;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onTick(EventTick event) {
/*  36 */     if (mc.field_1724 == null || mc.field_1687 == null || mc.field_1761 == null)
/*     */       return; 
/*  38 */     if (this.delayTick > 0) {
/*  39 */       this.delayTick--;
/*     */       
/*     */       return;
/*     */     } 
/*  43 */     SearchInvResult res = InventoryUtility.findBlockInHotBar(new class_2248[] { class_2246.field_10201, class_2246.field_10381 });
/*  44 */     if (!res.found())
/*     */       return; 
/*  46 */     int pX = mc.field_1724.method_31477();
/*  47 */     int pY = mc.field_1724.method_31478();
/*  48 */     int pZ = mc.field_1724.method_31479();
/*     */ 
/*     */     
/*  51 */     int minY = pY, maxY = pY, minX = pX, maxX = pX, minZ = pZ, maxZ = pZ; boolean fMinY;
/*     */     int y;
/*  53 */     for (fMinY = false, y = pY; y >= pY - 4; ) { if (isTrapka(pX, y, pZ)) { minY = y; fMinY = true; break; }  y--; }
/*  54 */      if (!fMinY)
/*     */       return;  boolean fMaxY; int i;
/*  56 */     for (fMaxY = false, i = pY; i <= pY + 4; ) { if (isTrapka(pX, i, pZ)) { maxY = i; fMaxY = true; break; }  i++; }
/*  57 */      if (!fMaxY)
/*     */       return;  boolean fMinX; int x;
/*  59 */     for (fMinX = false, x = pX; x >= pX - 4; ) { if (isTrapka(x, pY, pZ)) { minX = x; fMinX = true; break; }  x--; }
/*  60 */      if (!fMinX)
/*     */       return;  boolean fMaxX; int j;
/*  62 */     for (fMaxX = false, j = pX; j <= pX + 4; ) { if (isTrapka(j, pY, pZ)) { maxX = j; fMaxX = true; break; }  j++; }
/*  63 */      if (!fMaxX)
/*     */       return;  boolean fMinZ; int z;
/*  65 */     for (fMinZ = false, z = pZ; z >= pZ - 4; ) { if (isTrapka(pX, pY, z)) { minZ = z; fMinZ = true; break; }  z--; }
/*  66 */      if (!fMinZ)
/*     */       return;  boolean fMaxZ; int k;
/*  68 */     for (fMaxZ = false, k = pZ; k <= pZ + 4; ) { if (isTrapka(pX, pY, k)) { maxZ = k; fMaxZ = true; break; }  k++; }
/*  69 */      if (!fMaxZ)
/*     */       return; 
/*  71 */     if (maxX - minX < 2 || maxZ - minZ < 2 || maxY - minY < 2)
/*  72 */       return;  if (!isTrapka(minX, pY, pZ) || !isTrapka(maxX, pY, pZ) || !isTrapka(pX, pY, minZ) || !isTrapka(pX, pY, maxZ)) {
/*     */       return;
/*     */     }
/*  75 */     class_238 pBox = mc.field_1724.method_5829();
/*  76 */     double pMinX = pBox.field_1323, pMinY = pBox.field_1322, pMinZ = pBox.field_1321;
/*  77 */     double pMaxX = pBox.field_1320, pMaxY = pBox.field_1325, pMaxZ = pBox.field_1324;
/*     */     
/*  79 */     double pDX = mc.field_1724.method_23317(), pDY = mc.field_1724.method_23318(), pDZ = mc.field_1724.method_23321();
/*     */     
/*  81 */     int bestX = 0, bestY = 0, bestZ = 0;
/*  82 */     double minScore = Double.MAX_VALUE;
/*     */     
/*  84 */     for (int m = minX + 1; m < maxX; m++) {
/*  85 */       for (int n = minY + 1; n < maxY; n++) {
/*  86 */         for (int i1 = minZ + 1; i1 < maxZ; i1++) {
/*  87 */           class_2248 b = mc.field_1687.method_8320((class_2338)this.mut.method_10103(m, n, i1)).method_26204();
/*     */           
/*  89 */           if (b == class_2246.field_10201 || b == class_2246.field_10381)
/*     */             return; 
/*  91 */           if (b.method_9564().method_45474())
/*     */           {
/*     */             
/*  94 */             if (pMinX >= m + 1.0D || pMaxX <= m || pMinY >= n + 1.0D || pMaxY <= n || pMinZ >= i1 + 1.0D || pMaxZ <= i1) {
/*     */ 
/*     */ 
/*     */               
/*  98 */               double dX = m + 0.5D - pDX;
/*  99 */               double dY = n + 0.5D - pDY;
/* 100 */               double dZ = i1 + 0.5D - pDZ;
/* 101 */               double currentScore = dX * dX + dY * dY + dZ * dZ;
/*     */               
/* 103 */               boolean isCornerX = (m == minX + 1 || m == maxX - 1);
/* 104 */               boolean isCornerZ = (i1 == minZ + 1 || i1 == maxZ - 1);
/* 105 */               boolean isCorner = (isCornerX && isCornerZ);
/*     */               
/* 107 */               if (isCorner && n == maxY - 1) {
/* 108 */                 currentScore -= 20000.0D;
/* 109 */               } else if (isCorner && n == minY + 1) {
/* 110 */                 currentScore -= 10000.0D;
/* 111 */               } else if (n == maxY - 1) {
/* 112 */                 currentScore -= 5000.0D;
/* 113 */               } else if (n == minY + 1) {
/* 114 */                 currentScore -= 2000.0D;
/* 115 */               } else if (m == pX && i1 == pZ) {
/* 116 */                 currentScore += 10000.0D;
/*     */               } 
/*     */               
/* 119 */               if (currentScore < minScore) {
/* 120 */                 minScore = currentScore;
/* 121 */                 bestX = m; bestY = n; bestZ = i1;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (minScore != Double.MAX_VALUE) {
/* 130 */       InventoryUtility.saveAndSwitchTo(res.slot());
/*     */       
/* 132 */       class_3965 hitResult = null;
/*     */       
/* 134 */       for (class_2350 dir : FAST_DIRS) {
/* 135 */         this.neighborMut.method_10103(bestX + dir.method_10148(), bestY + dir.method_10164(), bestZ + dir.method_10165());
/* 136 */         if (!mc.field_1687.method_8320((class_2338)this.neighborMut).method_45474()) {
/* 137 */           hitResult = new class_3965(class_243.method_24953((class_2382)this.neighborMut), dir.method_10153(), (class_2338)this.neighborMut, false);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 142 */       if (hitResult != null) {
/* 143 */         mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, hitResult);
/* 144 */         mc.field_1724.method_6104(class_1268.field_5808);
/*     */       } 
/*     */       
/* 147 */       InventoryUtility.returnSlot();
/* 148 */       this.delayTick = 3;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isTrapka(int x, int y, int z) {
/* 153 */     class_2248 b = mc.field_1687.method_8320((class_2338)this.mut.method_10103(x, y, z)).method_26204();
/* 154 */     return (b == class_2246.field_10540 || b == class_2246.field_22423 || b == class_2246.field_9987);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AutoRG.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */