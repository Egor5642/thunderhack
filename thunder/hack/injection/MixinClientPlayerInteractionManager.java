/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1269;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_636;
/*     */ import net.minecraft.class_746;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventAttackBlock;
/*     */ import thunder.hack.events.impl.EventBreakBlock;
/*     */ import thunder.hack.events.impl.EventClickSlot;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.player.NoInteract;
/*     */ import thunder.hack.features.modules.player.SpeedMine;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_636.class})
/*     */ public class MixinClientPlayerInteractionManager
/*     */ {
/*     */   @Shadow
/*     */   private int field_3716;
/*     */   
/*     */   @Inject(method = {"interactBlock"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void interactBlock(class_746 player, class_1268 hand, class_3965 hitResult, CallbackInfoReturnable<class_1269> cir) {
/*  41 */     class_2248 bs = Module.mc.field_1687.method_8320(hitResult.method_17777()).method_26204();
/*  42 */     if (ModuleManager.noInteract.isEnabled() && (bs == class_2246.field_10034 || bs == class_2246.field_10380 || bs == class_2246.field_10181 || bs == class_2246.field_10535 || bs == class_2246.field_9980 || bs == class_2246.field_10312 || bs == class_2246.field_10223 || bs == class_2246.field_10179 || bs == class_2246.field_10443 || bs == class_2246.field_10200 || bs == class_2246.field_10228 || bs instanceof net.minecraft.class_2480 || bs instanceof net.minecraft.class_2354 || bs instanceof net.minecraft.class_2349 || bs instanceof net.minecraft.class_2533) && (ModuleManager.aura
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       .isEnabled() || !((Boolean)NoInteract.onlyAura.getValue()).booleanValue())) {
/*  59 */       cir.setReturnValue(class_1269.field_5811);
/*     */     }
/*     */     
/*  62 */     if (Module.mc.field_1724 != null && ModuleManager.antiBallPlace.isEnabled() && ((Module.mc.field_1724
/*  63 */       .method_6079().method_7909() == class_1802.field_8575 && hand == class_1268.field_5810) || (Module.mc.field_1724.method_6047().method_7909() == class_1802.field_8575 && hand == class_1268.field_5808)))
/*  64 */       cir.setReturnValue(class_1269.field_5811); 
/*     */   }
/*     */   
/*     */   @Redirect(method = {"updateBlockBreakingProgress"}, at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I", opcode = 180, ordinal = 0))
/*     */   public int updateBlockBreakingProgressHook(class_636 clientPlayerInteractionManager) {
/*  69 */     return ModuleManager.speedMine.isEnabled() ? 0 : this.field_3716;
/*     */   }
/*     */   
/*     */   @Inject(method = {"updateBlockBreakingProgress"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void updateBlockBreakingProgress(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
/*  74 */     if (ModuleManager.speedMine.isEnabled() && ModuleManager.speedMine.mode.getValue() == SpeedMine.Mode.Packet) {
/*  75 */       cir.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"attackBlock"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void attackBlockHook(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
/*  81 */     if (Module.fullNullCheck())
/*  82 */       return;  EventAttackBlock event = new EventAttackBlock(pos, direction);
/*  83 */     ThunderHack.EVENT_BUS.post(event);
/*  84 */     if (event.isCancelled()) {
/*  85 */       cir.setReturnValue(Boolean.valueOf(false));
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"breakBlock"}, at = {@At("RETURN")}, cancellable = true)
/*     */   public void breakBlockHook(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
/* 106 */     if (Module.fullNullCheck())
/* 107 */       return;  EventBreakBlock event = new EventBreakBlock(pos);
/* 108 */     ThunderHack.EVENT_BUS.post(event);
/* 109 */     if (event.isCancelled())
/* 110 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"clickSlot"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void clickSlotHook(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo ci) {
/* 115 */     if (Module.fullNullCheck())
/* 116 */       return;  EventClickSlot event = new EventClickSlot(actionType, slotId, button, syncId);
/* 117 */     ThunderHack.EVENT_BUS.post(event);
/* 118 */     if (event.isCancelled())
/* 119 */       ci.cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientPlayerInteractionManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */