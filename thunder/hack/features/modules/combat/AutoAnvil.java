/*     */ package thunder.hack.features.modules.combat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1747;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_2382;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.player.CombatManager;
/*     */ import thunder.hack.events.impl.EventTick;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.SearchInvResult;
/*     */ import thunder.hack.utility.world.HoleUtility;
/*     */ 
/*     */ public class AutoAnvil extends Module {
/*  29 */   private final Setting<Float> range = new Setting("Range", Float.valueOf(5.0F), Float.valueOf(1.0F), Float.valueOf(7.0F));
/*  30 */   private final Setting<Boolean> once = new Setting("Once", Boolean.valueOf(false));
/*  31 */   private final Setting<Boolean> placePlates = new Setting("PlacePlates", Boolean.valueOf(false));
/*  32 */   private final Setting<InteractionUtility.PlaceMode> placeMode = new Setting("Place Mode", InteractionUtility.PlaceMode.Normal);
/*  33 */   private final Setting<InteractionUtility.Interact> interact = new Setting("Interact Mode", InteractionUtility.Interact.Vanilla);
/*  34 */   private final Setting<InteractionUtility.Rotate> rotate = new Setting("Rotate", InteractionUtility.Rotate.None);
/*  35 */   private final Setting<CombatManager.TargetBy> targetBy = new Setting("TargetBy", CombatManager.TargetBy.Distance);
/*  36 */   private final Setting<Boolean> sand = new Setting("Sand", Boolean.valueOf(false));
/*  37 */   private final Setting<Boolean> gravel = new Setting("Gravel", Boolean.valueOf(false));
/*  38 */   private final Setting<Boolean> concrete = new Setting("Сoncrete", Boolean.valueOf(false));
/*  39 */   private final Setting<Boolean> anvils = new Setting("Anvils", Boolean.valueOf(true));
/*     */   
/*     */   private class_1657 target;
/*     */   
/*     */   public AutoAnvil() {
/*  44 */     super("AutoAnvil", Module.Category.COMBAT);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onTick(EventTick event) {
/*  49 */     if (mc.field_1724 == null)
/*  50 */       return;  if (this.target == null || this.target.method_29504()) {
/*  51 */       this.target = Managers.COMBAT.getTarget(((Float)this.range.getValue()).floatValue(), (CombatManager.TargetBy)this.targetBy.getValue());
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     SearchInvResult result = getBlockResult();
/*  56 */     SearchInvResult plateResult = InventoryUtility.findItemInHotBar(new class_1792[] { class_1802.field_8667, class_1802.field_8779, class_1802.field_8592, class_1802.field_8721, class_1802.field_8391 });
/*     */     
/*  58 */     class_2338 anvilPos = class_2338.method_49638((class_2374)this.target.method_19538()).method_10086(2);
/*     */     
/*  60 */     if (!result.found() || (!plateResult.found() && ((Boolean)this.placePlates.getValue()).booleanValue())) {
/*     */       return;
/*     */     }
/*  63 */     class_2248 targetBlock = mc.field_1687.method_8320(class_2338.method_49638((class_2374)this.target.method_19538())).method_26204();
/*     */     
/*  65 */     if (!(targetBlock instanceof net.minecraft.class_2440) && targetBlock != class_2246.field_10582 && targetBlock != class_2246.field_10224 && ((Boolean)this.placePlates.getValue()).booleanValue()) {
/*  66 */       InteractionUtility.placeBlock(class_2338.method_49638((class_2374)this.target.method_19538()), (InteractionUtility.Rotate)this.rotate.getValue(), (InteractionUtility.Interact)this.interact.getValue(), (InteractionUtility.PlaceMode)this.placeMode.getValue(), plateResult, true, true);
/*     */       
/*     */       return;
/*     */     } 
/*  70 */     if (!InteractionUtility.canPlaceBlock(anvilPos, (InteractionUtility.Interact)this.interact.getValue(), false)) {
/*  71 */       if (needObsidian(anvilPos)) {
/*     */         
/*  73 */         Objects.requireNonNull(anvilPos);
/*     */ 
/*     */ 
/*     */         
/*  77 */         class_2338 obsidianPos = Arrays.<class_2382>stream(HoleUtility.VECTOR_PATTERN).parallel().map(anvilPos::method_10081).filter(pos -> InteractionUtility.canPlaceBlock(pos, (InteractionUtility.Interact)this.interact.getValue(), false)).filter(pos -> (pos.method_19770((class_2374)mc.field_1724.method_19538()) <= this.range.getPow2Value())).findFirst().orElse(null);
/*  78 */         SearchInvResult obbyResult = InventoryUtility.findBlockInHotBar(new class_2248[] { class_2246.field_10540 });
/*     */         
/*  80 */         if (obsidianPos != null && obbyResult.found()) {
/*  81 */           InteractionUtility.placeBlock(obsidianPos, (InteractionUtility.Rotate)this.rotate.getValue(), (InteractionUtility.Interact)this.interact.getValue(), (InteractionUtility.PlaceMode)this.placeMode.getValue(), obbyResult, true, false);
/*  82 */           if (((Boolean)this.once.getValue()).booleanValue()) {
/*  83 */             disable(ClientSettings.isRu() ? "Блок размещен" : "Done");
/*     */           }
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/*  89 */     InteractionUtility.placeBlock(anvilPos, (InteractionUtility.Rotate)this.rotate.getValue(), (InteractionUtility.Interact)this.interact.getValue(), (InteractionUtility.PlaceMode)this.placeMode.getValue(), result, true, false);
/*     */   }
/*     */   
/*     */   private boolean needObsidian(class_2338 anvilPos) {
/*  93 */     if (mc.field_1687 == null) return false;
/*     */ 
/*     */     
/*  96 */     Objects.requireNonNull(anvilPos); return Arrays.<class_2382>stream(HoleUtility.VECTOR_PATTERN).map(anvilPos::method_10081)
/*  97 */       .filter(pos -> !mc.field_1687.method_8320(pos).method_45474())
/*  98 */       .toList()
/*  99 */       .isEmpty();
/*     */   }
/*     */   
/*     */   protected SearchInvResult getBlockResult() {
/* 103 */     List<class_2248> canUseBlocks = new ArrayList<>();
/*     */     
/* 105 */     if (mc.field_1724 == null) return SearchInvResult.notFound(); 
/* 106 */     if (((Boolean)this.anvils.getValue()).booleanValue()) canUseBlocks.add(class_2246.field_10535); 
/* 107 */     if (((Boolean)this.sand.getValue()).booleanValue()) canUseBlocks.add(class_2246.field_10102); 
/* 108 */     if (((Boolean)this.gravel.getValue()).booleanValue()) canUseBlocks.add(class_2246.field_10255);
/*     */ 
/*     */     
/* 111 */     SearchInvResult defaultResult = InventoryUtility.findBlockInHotBar(canUseBlocks);
/* 112 */     SearchInvResult concreteResult = InventoryUtility.findInHotBar(i -> { class_1792 patt0$temp = i.method_7909(); if (patt0$temp instanceof class_1747) { class_1747 bi = (class_1747)patt0$temp; if (bi.method_7711() instanceof net.minecraft.class_2292); }
/*     */            return false;
/* 114 */         }); return (((Boolean)this.concrete.getValue()).booleanValue() && concreteResult.found()) ? concreteResult : defaultResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\AutoAnvil.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */