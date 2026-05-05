/*     */ package thunder.hack.utility.player;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1280;
/*     */ import net.minecraft.class_1293;
/*     */ import net.minecraft.class_1294;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1743;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1829;
/*     */ import net.minecraft.class_1890;
/*     */ import net.minecraft.class_1893;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_7923;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.injection.accesors.IInteractionManager;
/*     */ 
/*     */ public final class InventoryUtility {
/*  26 */   private static int cachedSlot = -1;
/*     */   
/*     */   public static int getItemCount(class_1792 item) {
/*  29 */     if (Module.mc.field_1724 == null) return 0;
/*     */     
/*  31 */     int counter = 0;
/*     */     
/*  33 */     for (int i = 0; i <= 44; i++) {
/*  34 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(i);
/*  35 */       if (itemStack.method_7909() == item) {
/*  36 */         counter += itemStack.method_7947();
/*     */       }
/*     */     } 
/*  39 */     return counter;
/*     */   }
/*     */   
/*     */   public static SearchInvResult getAxe() {
/*  43 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound(); 
/*  44 */     int slot = -1;
/*  45 */     float f = 1.0F;
/*     */     
/*  47 */     for (int b1 = 9; b1 < 45; b1++) {
/*  48 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438((b1 >= 36) ? (b1 - 36) : b1);
/*  49 */       if (itemStack != null) { class_1792 class_1792 = itemStack.method_7909(); if (class_1792 instanceof class_1743) { class_1743 axe = (class_1743)class_1792;
/*  50 */           float f1 = ((Integer)axe.method_57347().method_57829(class_9334.field_50072)).intValue();
/*  51 */           f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9118.method_58273()).method_40264(class_1893.field_9118).get(), itemStack);
/*  52 */           if (f1 > f) {
/*  53 */             f = f1;
/*  54 */             slot = b1;
/*     */           }  }
/*     */          }
/*     */     
/*     */     } 
/*  59 */     if (slot >= 36) slot -= 36;
/*     */     
/*  61 */     if (slot == -1) return SearchInvResult.notFound(); 
/*  62 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getPickAxeHotbar() {
/*  66 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/*  68 */     int slot = -1;
/*  69 */     float f = 1.0F;
/*  70 */     for (int b1 = 0; b1 < 9; b1++) {
/*  71 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/*  72 */       if (itemStack != null && itemStack.method_7909() instanceof net.minecraft.class_1810) {
/*  73 */         float f1 = 0.0F;
/*  74 */         f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9131.method_58273()).method_40264(class_1893.field_9131).get(), itemStack);
/*  75 */         if (f1 > f) {
/*  76 */           f = f1;
/*  77 */           slot = b1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (slot == -1) return SearchInvResult.notFound(); 
/*  83 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getPickAxe() {
/*  87 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/*  89 */     int slot = -1;
/*  90 */     float f = 1.0F;
/*  91 */     for (int b1 = 9; b1 < 45; b1++) {
/*  92 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/*  93 */       if (itemStack != null && itemStack.method_7909() instanceof net.minecraft.class_1810) {
/*  94 */         float f1 = 0.0F;
/*  95 */         f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9131.method_58273()).method_40264(class_1893.field_9131).get(), itemStack);
/*  96 */         if (f1 > f) {
/*  97 */           f = f1;
/*  98 */           slot = b1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 104 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getPickAxeHotBar() {
/* 108 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/* 110 */     int slot = -1;
/* 111 */     float f = 1.0F;
/* 112 */     for (int b1 = 0; b1 < 9; b1++) {
/* 113 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 114 */       if (itemStack != null && itemStack.method_7909() instanceof net.minecraft.class_1810) {
/* 115 */         float f1 = 0.0F;
/* 116 */         f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9131.method_58273()).method_40264(class_1893.field_9131).get(), itemStack);
/* 117 */         if (f1 > f) {
/* 118 */           f = f1;
/* 119 */           slot = b1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 125 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getSkull() {
/* 129 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound(); 
/* 130 */     int slot = -1;
/* 131 */     for (int b1 = 0; b1 < 9; b1++) {
/* 132 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 133 */       if (itemStack != null && (itemStack
/* 134 */         .method_7909().equals(class_1802.field_8398) || itemStack
/* 135 */         .method_7909().equals(class_1802.field_8791) || itemStack
/* 136 */         .method_7909().equals(class_1802.field_8681) || itemStack
/* 137 */         .method_7909().equals(class_1802.field_8575) || itemStack
/* 138 */         .method_7909().equals(class_1802.field_8470))) {
/* 139 */         slot = b1;
/*     */         break;
/*     */       } 
/*     */     } 
/* 143 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 144 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getSword() {
/* 148 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/* 150 */     int slot = -1;
/* 151 */     float f = 1.0F;
/* 152 */     for (int b1 = 9; b1 < 45; b1++) {
/* 153 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 154 */       if (itemStack != null) { class_1792 class_1792 = itemStack.method_7909(); if (class_1792 instanceof class_1829) { class_1829 sword = (class_1829)class_1792;
/* 155 */           float f1 = ((Integer)sword.method_57347().method_57829(class_9334.field_50072)).intValue();
/* 156 */           f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9118.method_58273()).method_40264(class_1893.field_9118).get(), itemStack);
/* 157 */           if (f1 > f) {
/* 158 */             f = f1;
/* 159 */             slot = b1;
/*     */           }  }
/*     */          }
/*     */     
/*     */     } 
/* 164 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 165 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getSwordHotBar() {
/* 169 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/* 171 */     int slot = -1;
/* 172 */     float f = 1.0F;
/* 173 */     for (int b1 = 0; b1 < 9; b1++) {
/* 174 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 175 */       if (itemStack != null) { class_1792 class_1792 = itemStack.method_7909(); if (class_1792 instanceof class_1829) { class_1829 sword = (class_1829)class_1792;
/* 176 */           float f1 = ((Integer)sword.method_57347().method_57829(class_9334.field_50072)).intValue();
/* 177 */           f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9118.method_58273()).method_40264(class_1893.field_9118).get(), itemStack);
/* 178 */           if (f1 > f) {
/* 179 */             f = f1;
/* 180 */             slot = b1;
/*     */           }  }
/*     */          }
/*     */     
/*     */     } 
/* 185 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 186 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public static SearchInvResult getAxeHotBar() {
/* 191 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/* 193 */     int slot = -1;
/* 194 */     float f = 1.0F;
/* 195 */     for (int b1 = 0; b1 < 9; b1++) {
/* 196 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 197 */       if (itemStack != null) { class_1792 class_1792 = itemStack.method_7909(); if (class_1792 instanceof class_1743) { class_1743 axe = (class_1743)class_1792;
/* 198 */           float f1 = ((Integer)axe.method_57347().method_57829(class_9334.field_50072)).intValue();
/* 199 */           f1 += class_1890.method_8225(Module.mc.field_1687.method_30349().method_30530(class_1893.field_9118.method_58273()).method_40264(class_1893.field_9118).get(), itemStack);
/* 200 */           if (f1 > f) {
/* 201 */             f = f1;
/* 202 */             slot = b1;
/*     */           }  }
/*     */          }
/*     */     
/*     */     } 
/* 207 */     if (slot == -1) return SearchInvResult.notFound(); 
/* 208 */     return new SearchInvResult(slot, true, Module.mc.field_1724.method_31548().method_5438(slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getElytra() {
/* 213 */     for (class_1799 stack : (Module.mc.field_1724.method_31548()).field_7548) {
/* 214 */       if (stack.method_7909() == class_1802.field_8833 && stack.method_7919() < 430)
/* 215 */         return -2; 
/*     */     } 
/* 217 */     int slot = -1;
/* 218 */     for (int i = 0; i < 36; i++) {
/* 219 */       class_1799 s = Module.mc.field_1724.method_31548().method_5438(i);
/* 220 */       if (s.method_7909() == class_1802.field_8833 && s.method_7919() < 430) {
/* 221 */         slot = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 226 */     if (slot < 9 && slot != -1) {
/* 227 */       slot += 36;
/*     */     }
/* 229 */     return slot;
/*     */   }
/*     */   
/*     */   public static SearchInvResult findInHotBar(Searcher searcher) {
/* 233 */     if (Module.mc.field_1724 != null) {
/* 234 */       for (int i = 0; i < 9; i++) {
/* 235 */         class_1799 stack = Module.mc.field_1724.method_31548().method_5438(i);
/* 236 */         if (searcher.isValid(stack)) {
/* 237 */           return new SearchInvResult(i, true, stack);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 242 */     return SearchInvResult.notFound();
/*     */   }
/*     */   
/*     */   public static SearchInvResult findItemInHotBar(List<class_1792> items) {
/* 246 */     return findInHotBar(stack -> items.contains(stack.method_7909()));
/*     */   }
/*     */   
/*     */   public static SearchInvResult findItemInHotBar(class_1792... items) {
/* 250 */     return findItemInHotBar(Arrays.asList(items));
/*     */   }
/*     */   
/*     */   public static SearchInvResult findInInventory(Searcher searcher) {
/* 254 */     if (Module.mc.field_1724 != null) {
/* 255 */       for (int i = 36; i >= 0; i--) {
/* 256 */         class_1799 stack = Module.mc.field_1724.method_31548().method_5438(i);
/* 257 */         if (searcher.isValid(stack)) {
/* 258 */           if (i < 9) i += 36; 
/* 259 */           return new SearchInvResult(i, true, stack);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 264 */     return SearchInvResult.notFound();
/*     */   }
/*     */   
/*     */   public static SearchInvResult findItemInInventory(List<class_1792> items) {
/* 268 */     return findInInventory(stack -> items.contains(stack.method_7909()));
/*     */   }
/*     */   
/*     */   public static SearchInvResult findItemInInventory(class_1792... items) {
/* 272 */     return findItemInInventory(Arrays.asList(items));
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBlockInHotBar(@NotNull List<class_2248> blocks) {
/* 276 */     return findItemInHotBar(blocks.stream().map(class_2248::method_8389).toList());
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBlockInHotBar(class_2248... blocks) {
/* 280 */     return findItemInHotBar(Arrays.<class_2248>stream(blocks).map(class_2248::method_8389).toList());
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBlockInInventory(@NotNull List<class_2248> blocks) {
/* 284 */     return findItemInInventory(blocks.stream().map(class_2248::method_8389).toList());
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBlockInInventory(class_2248... blocks) {
/* 288 */     return findItemInInventory(Arrays.<class_2248>stream(blocks).map(class_2248::method_8389).toList());
/*     */   }
/*     */   
/*     */   public static void saveSlot() {
/* 292 */     cachedSlot = (Module.mc.field_1724.method_31548()).field_7545;
/*     */   }
/*     */   
/*     */   public static void returnSlot() {
/* 296 */     if (cachedSlot != -1)
/* 297 */       switchTo(cachedSlot); 
/* 298 */     cachedSlot = -1;
/*     */   }
/*     */   
/*     */   public static void saveAndSwitchTo(int slot) {
/* 302 */     saveSlot();
/* 303 */     if (Module.mc.field_1724 == null || Module.mc.method_1562() == null)
/* 304 */       return;  if ((Module.mc.field_1724.method_31548()).field_7545 == slot && Managers.PLAYER.serverSideSlot == slot)
/*     */       return; 
/* 306 */     (Module.mc.field_1724.method_31548()).field_7545 = slot;
/* 307 */     ((IInteractionManager)Module.mc.field_1761).syncSlot();
/*     */   }
/*     */   
/*     */   public static void switchTo(int slot) {
/* 311 */     if (Module.mc.field_1724 == null || Module.mc.method_1562() == null)
/* 312 */       return;  if ((Module.mc.field_1724.method_31548()).field_7545 == slot && Managers.PLAYER.serverSideSlot == slot)
/*     */       return; 
/* 314 */     (Module.mc.field_1724.method_31548()).field_7545 = slot;
/* 315 */     ((IInteractionManager)Module.mc.field_1761).syncSlot();
/*     */   }
/*     */   
/*     */   public static void switchToSilent(int slot) {
/* 319 */     if (Module.mc.field_1724 == null || Module.mc.method_1562() == null)
/* 320 */       return;  Module.mc.method_1562().method_52787((class_2596)new class_2868(slot));
/*     */   }
/*     */   
/*     */   public static SearchInvResult getAntiWeaknessItem() {
/* 324 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound();
/*     */     
/* 326 */     class_1792 mainHand = Module.mc.field_1724.method_6047().method_7909();
/* 327 */     if (mainHand instanceof class_1829 || mainHand instanceof net.minecraft.class_1810 || mainHand instanceof class_1743 || mainHand instanceof net.minecraft.class_1821)
/*     */     {
/*     */ 
/*     */       
/* 331 */       return new SearchInvResult((Module.mc.field_1724.method_31548()).field_7545, true, Module.mc.field_1724.method_6047());
/*     */     }
/*     */     
/* 334 */     return findInHotBar(itemStack -> 
/* 335 */         (itemStack.method_7909() instanceof class_1829 || itemStack.method_7909() instanceof net.minecraft.class_1810 || itemStack.method_7909() instanceof class_1743 || itemStack.method_7909() instanceof net.minecraft.class_1821));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getHitDamage(@NotNull class_1799 weapon, class_1657 ent) {
/* 343 */     if (Module.mc.field_1724 == null) return 0.0F; 
/* 344 */     float baseDamage = 1.0F;
/*     */     
/* 346 */     class_1792 class_1792 = weapon.method_7909(); if (class_1792 instanceof class_1829) { class_1829 swordItem = (class_1829)class_1792;
/* 347 */       baseDamage = 7.0F; }
/*     */     
/* 349 */     class_1792 = weapon.method_7909(); if (class_1792 instanceof class_1743) { class_1743 axeItem = (class_1743)class_1792;
/* 350 */       baseDamage = 9.0F; }
/*     */     
/* 352 */     if (Module.mc.field_1724.field_6017 > 0.0F || ModuleManager.criticals.isEnabled()) {
/* 353 */       baseDamage += baseDamage / 2.0F;
/*     */     }
/* 355 */     if (Module.mc.field_1724.method_6059(class_1294.field_5910)) {
/* 356 */       int strength = ((class_1293)Objects.<class_1293>requireNonNull(Module.mc.field_1724.method_6112(class_1294.field_5910))).method_5578() + 1;
/* 357 */       baseDamage += (3 * strength);
/*     */     } 
/*     */ 
/*     */     
/* 361 */     baseDamage = class_1280.method_5496((class_1309)ent, baseDamage, Module.mc.field_1687.method_48963().method_48830(), ent.method_6096(), (float)ent.method_5996(class_5134.field_23725).method_6194());
/* 362 */     return baseDamage;
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBedInHotBar() {
/* 366 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound(); 
/* 367 */     for (int b1 = 0; b1 < 9; b1++) {
/* 368 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(b1);
/* 369 */       if (itemStack != null && itemStack.method_7909() instanceof net.minecraft.class_1748)
/* 370 */         return new SearchInvResult(b1, true, Module.mc.field_1724.method_31548().method_5438(b1)); 
/*     */     } 
/* 372 */     return SearchInvResult.notFound();
/*     */   }
/*     */   
/*     */   public static SearchInvResult findBed() {
/* 376 */     if (Module.mc.field_1724 == null) return SearchInvResult.notFound(); 
/* 377 */     for (int b1 = 9; b1 < 45; b1++) {
/* 378 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438((b1 >= 36) ? (b1 - 36) : b1);
/* 379 */       if (itemStack != null && itemStack.method_7909() instanceof net.minecraft.class_1748)
/* 380 */         return new SearchInvResult(b1, true, Module.mc.field_1724.method_31548().method_5438(b1)); 
/*     */     } 
/* 382 */     return SearchInvResult.notFound();
/*     */   }
/*     */   
/*     */   public static class_1792 getItem(String Name) {
/* 386 */     if (Name == null) return class_1802.field_8162; 
/* 387 */     for (class_2248 block : class_7923.field_41175) {
/* 388 */       if (block.method_9539().replace("block.minecraft.", "").equals(Name.toLowerCase()))
/* 389 */         return class_1792.method_7867(block); 
/* 390 */     }  for (class_1792 item : class_7923.field_41178) {
/* 391 */       if (item.method_7876().replace("item.minecraft.", "").equals(Name.toLowerCase()))
/* 392 */         return item; 
/* 393 */     }  return class_1802.field_8831;
/*     */   }
/*     */   
/*     */   public static int getBedsCount() {
/* 397 */     if (Module.mc.field_1724 == null) return 0;
/*     */     
/* 399 */     int counter = 0;
/*     */     
/* 401 */     for (int i = 0; i <= 44; i++) {
/* 402 */       class_1799 itemStack = Module.mc.field_1724.method_31548().method_5438(i);
/* 403 */       if (itemStack.method_7909() instanceof net.minecraft.class_1748) {
/* 404 */         counter += itemStack.method_7947();
/*     */       }
/*     */     } 
/* 407 */     return counter;
/*     */   }
/*     */   
/*     */   public static interface Searcher {
/*     */     boolean isValid(class_1799 param1class_1799);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\player\InventoryUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */