/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1313;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_4174;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventAttack;
/*     */ import thunder.hack.events.impl.EventEatFood;
/*     */ import thunder.hack.events.impl.EventPlayerJump;
/*     */ import thunder.hack.events.impl.EventPlayerTravel;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.Media;
/*     */ import thunder.hack.features.modules.combat.Aura;
/*     */ import thunder.hack.features.modules.movement.AutoSprint;
/*     */ import thunder.hack.features.modules.movement.Speed;
/*     */ 
/*     */ @Mixin(value = {class_1657.class}, priority = 800)
/*     */ public class MixinPlayerEntity
/*     */ {
/*     */   @Inject(method = {"getAttackCooldownProgressPerTick"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getAttackCooldownProgressPerTickHook(CallbackInfoReturnable<Float> cir) {
/*  33 */     if (ModuleManager.aura.isEnabled() && ModuleManager.aura.switchMode.getValue() == Aura.Switch.Silent) {
/*  34 */       cir.setReturnValue(Float.valueOf(12.5F));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"getDisplayName"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getDisplayNameHook(CallbackInfoReturnable<class_2561> cir) {
/*  40 */     if (ModuleManager.media.isEnabled() && ((Boolean)Media.nickProtect.getValue()).booleanValue()) {
/*  41 */       cir.setReturnValue(class_2561.method_30163("Protected"));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"attack"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setSprinting(Z)V", shift = At.Shift.AFTER)})
/*     */   public void attackAHook(CallbackInfo callbackInfo) {
/*  47 */     if (ModuleManager.autoSprint.isEnabled() && ((Boolean)AutoSprint.sprint.getValue()).booleanValue()) {
/*  48 */       float multiplier = 0.6F + 0.4F * ((Float)AutoSprint.motion.getValue()).floatValue();
/*  49 */       Module.mc.field_1724.method_18800((Module.mc.field_1724.method_18798()).field_1352 / 0.6D * multiplier, (Module.mc.field_1724.method_18798()).field_1351, (Module.mc.field_1724.method_18798()).field_1350 / 0.6D * multiplier);
/*  50 */       Module.mc.field_1724.method_5728(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"getMovementSpeed"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getMovementSpeedHook(CallbackInfoReturnable<Float> cir) {
/*  56 */     if (ModuleManager.speed.isEnabled() && ModuleManager.speed.mode.is(Speed.Mode.Vanilla)) {
/*  57 */       cir.setReturnValue(ModuleManager.speed.boostFactor.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"attack"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void attackAHook2(class_1297 target, CallbackInfo ci) {
/*  63 */     EventAttack event = new EventAttack(target, false);
/*  64 */     ThunderHack.EVENT_BUS.post(event);
/*  65 */     if (event.isCancelled()) {
/*  66 */       ci.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void onTravelhookPre(class_243 movementInput, CallbackInfo ci) {
/*  72 */     if (Module.mc.field_1724 == null) {
/*     */       return;
/*     */     }
/*  75 */     EventPlayerTravel event = new EventPlayerTravel(movementInput, true);
/*  76 */     ThunderHack.EVENT_BUS.post(event);
/*  77 */     if (event.isCancelled()) {
/*  78 */       Module.mc.field_1724.method_5784(class_1313.field_6308, Module.mc.field_1724.method_18798());
/*  79 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"travel"}, at = {@At("RETURN")}, cancellable = true)
/*     */   private void onTravelhookPost(class_243 movementInput, CallbackInfo ci) {
/*  86 */     if (Module.mc.field_1724 == null)
/*     */       return; 
/*  88 */     EventPlayerTravel event = new EventPlayerTravel(movementInput, false);
/*  89 */     ThunderHack.EVENT_BUS.post(event);
/*  90 */     if (event.isCancelled()) {
/*  91 */       Module.mc.field_1724.method_5784(class_1313.field_6308, Module.mc.field_1724.method_18798());
/*  92 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"jump"}, at = {@At("HEAD")})
/*     */   private void onJumpPre(CallbackInfo ci) {
/*  98 */     ThunderHack.EVENT_BUS.post(new EventPlayerJump(true));
/*     */   }
/*     */   
/*     */   @Inject(method = {"jump"}, at = {@At("RETURN")})
/*     */   private void onJumpPost(CallbackInfo ci) {
/* 103 */     ThunderHack.EVENT_BUS.post(new EventPlayerJump(false));
/*     */   }
/*     */   
/*     */   @Inject(method = {"eatFood"}, at = {@At("RETURN")})
/*     */   public void eatFoodHook(class_1937 world, class_1799 stack, class_4174 foodComponent, CallbackInfoReturnable<class_1799> cir) {
/* 108 */     ThunderHack.EVENT_BUS.post(new EventEatFood((class_1799)cir.getReturnValue()));
/*     */   }
/*     */   
/*     */   @Inject(method = {"shouldDismount"}, at = {@At("HEAD")}, cancellable = true)
/*     */   protected void shouldDismountHook(CallbackInfoReturnable<Boolean> cir) {
/* 113 */     if (ModuleManager.boatFly.isEnabled() && ((Boolean)ModuleManager.boatFly.allowShift.getValue()).booleanValue())
/* 114 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"getBlockInteractionRange"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getBlockInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
/* 119 */     if (ModuleManager.reach.isEnabled()) {
/* 120 */       if (((Boolean)ModuleManager.reach.Creative.getValue()).booleanValue() && Module.mc.field_1724.method_7337()) {
/* 121 */         cir.setReturnValue(Double.valueOf(((Float)ModuleManager.reach.creativeBlocksRange.getValue()).floatValue()));
/*     */       } else {
/*     */         
/* 124 */         cir.setReturnValue(Double.valueOf(((Float)ModuleManager.reach.blocksRange.getValue()).floatValue()));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"getEntityInteractionRange"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void getEntityInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
/* 131 */     if (ModuleManager.reach.isEnabled())
/* 132 */       if (((Boolean)ModuleManager.reach.Creative.getValue()).booleanValue() && Module.mc.field_1724.method_7337()) {
/* 133 */         cir.setReturnValue(Double.valueOf(((Float)ModuleManager.reach.creativeEntityRange.getValue()).floatValue()));
/*     */       } else {
/*     */         
/* 136 */         cir.setReturnValue(Double.valueOf(((Float)ModuleManager.reach.entityRange.getValue()).floatValue()));
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinPlayerEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */