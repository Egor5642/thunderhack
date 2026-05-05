/*     */ package thunder.hack.features.modules.combat;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1294;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1753;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1833;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2815;
/*     */ import net.minecraft.class_2828;
/*     */ import net.minecraft.class_2848;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.SearchInvResult;
/*     */ 
/*     */ public final class Quiver extends Module {
/*  23 */   private final Setting<Boolean> onlyInHole = new Setting("Only In Hole", Boolean.valueOf(false));
/*     */   
/*     */   private int preBowSlot;
/*     */   private int count;
/*     */   
/*     */   public Quiver() {
/*  29 */     super("Quiver", Module.Category.COMBAT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  34 */     this.count = 0;
/*  35 */     this.preBowSlot = (mc.field_1724.method_31548()).field_7545;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  40 */     if (this.preBowSlot != -1) {
/*  41 */       InventoryUtility.switchTo(this.preBowSlot);
/*     */     }
/*  43 */     mc.field_1690.field_1904.method_23481(false);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onSync(EventSync event) {
/*  49 */     if ((!HoleUtility.isHole(mc.field_1724.method_24515()) && ((Boolean)this.onlyInHole.getValue()).booleanValue()) || (mc.field_1724.method_6115() && !mc.field_1724.method_6047().method_7909().equals(class_1802.field_8102))) {
/*     */       return;
/*     */     }
/*  52 */     SearchInvResult strength = getArrow("strength");
/*  53 */     SearchInvResult swiftness = getArrow("swiftness");
/*     */     
/*  55 */     boolean hasStrength = (!strength.found() || (mc.field_1724.method_6059(class_1294.field_5910) && mc.field_1724.method_6112(class_1294.field_5910).method_5584() > 100));
/*  56 */     boolean hasSwiftness = (!swiftness.found() || (mc.field_1724.method_6059(class_1294.field_5904) && mc.field_1724.method_6112(class_1294.field_5904).method_5584() > 100));
/*     */     
/*  58 */     if (!strength.found() && !swiftness.found()) {
/*  59 */       disable(ClientSettings.isRu() ? "В интвенторе отсутствуют нужные стрелы! Отключение..." : "No arrows in hotbar! Disabling...");
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     if (hasSwiftness && hasStrength) {
/*  64 */       disable();
/*     */       
/*     */       return;
/*     */     } 
/*  68 */     SearchInvResult result = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8102 });
/*  69 */     if (!result.found()) {
/*  70 */       disable(ClientSettings.isRu() ? "В хотбаре отсутствует лук! Отключение..." : "No bow in hotbar! Disabling...");
/*     */       return;
/*     */     } 
/*  73 */     result.switchTo();
/*     */     
/*  75 */     if (class_1753.method_7722(mc.field_1724.method_6048()) >= 0.15D) {
/*  76 */       releaseBow();
/*  77 */       switchInvSlot(strength.slot(), swiftness.slot());
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     if (this.count >= ((strength.found() && swiftness.found()) ? 2 : 1)) {
/*  82 */       disable();
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     mc.field_1690.field_1904.method_23481(true);
/*     */   }
/*     */   
/*     */   private void releaseBow() {
/*  90 */     sendPacket((class_2596)new class_2828.class_2831(mc.field_1724.method_36454(), -90.0F, mc.field_1724.method_24828()));
/*  91 */     mc.field_1690.field_1904.method_23481(false);
/*  92 */     mc.field_1761.method_2897((class_1657)mc.field_1724);
/*  93 */     this.count++;
/*     */   }
/*     */   
/*     */   private SearchInvResult getArrow(String name) {
/*  97 */     return InventoryUtility.findInInventory(stack -> {
/*     */           class_1792 patt0$temp = stack.method_7909();
/*     */           if (patt0$temp instanceof class_1833) {
/*     */             class_1833 tai = (class_1833)patt0$temp;
/*     */             String key = tai.method_7866(stack);
/*     */             return key.contains("effect." + name);
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   } private void switchInvSlot(int from, int to) {
/* 107 */     if (from == -1 || to == -1) {
/*     */       return;
/*     */     }
/* 110 */     sendPacket((class_2596)new class_2848((class_1297)Objects.requireNonNull(mc.field_1724), class_2848.class_2849.field_12985));
/* 111 */     clickSlot(from);
/* 112 */     clickSlot(to);
/* 113 */     clickSlot(from);
/* 114 */     sendPacket((class_2596)new class_2815(mc.field_1724.field_7512.field_7763));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\Quiver.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */