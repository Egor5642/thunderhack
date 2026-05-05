/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1313;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2848;
/*     */ import net.minecraft.class_638;
/*     */ import net.minecraft.class_742;
/*     */ import net.minecraft.class_746;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Core;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventAfterRotate;
/*     */ import thunder.hack.events.impl.EventMove;
/*     */ import thunder.hack.events.impl.EventPostSync;
/*     */ import thunder.hack.events.impl.EventSprint;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.PlayerUpdateEvent;
/*     */ import thunder.hack.events.impl.PostPlayerUpdateEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ @Mixin(value = {class_746.class}, priority = 800)
/*     */ public abstract class MixinClientPlayerEntity
/*     */   extends class_742
/*     */ {
/*     */   @Unique
/*     */   boolean pre_sprint_state = false;
/*     */   @Unique
/*     */   private boolean updateLock = false;
/*     */   
/*     */   public MixinClientPlayerEntity(class_638 world, GameProfile profile) {
/*  42 */     super(world, profile); } @Unique
/*     */   private Runnable postAction; @Shadow
/*     */   public abstract float method_5695(float paramFloat); @Shadow
/*     */   protected abstract void method_3136(); @Inject(method = {"tick"}, at = {@At("HEAD")})
/*     */   public void tickHook(CallbackInfo info) {
/*  47 */     if (Module.fullNullCheck())
/*  48 */       return;  ThunderHack.EVENT_BUS.post(new PlayerUpdateEvent());
/*     */   }
/*     */   
/*     */   @Redirect(method = {"tickMovement"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require = 0)
/*     */   private boolean tickMovementHook(class_746 player) {
/*  53 */     if (ModuleManager.noSlow.isEnabled() && ModuleManager.noSlow.canNoSlow())
/*  54 */       return false; 
/*  55 */     return player.method_6115();
/*     */   }
/*     */   
/*     */   @Inject(method = {"shouldSlowDown"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void shouldSlowDownHook(CallbackInfoReturnable<Boolean> cir) {
/*  60 */     if (ModuleManager.noSlow.isEnabled()) {
/*  61 */       if (method_20448()) {
/*  62 */         if (((Boolean)ModuleManager.noSlow.crawl.getValue()).booleanValue()) {
/*  63 */           cir.setReturnValue(Boolean.valueOf(false));
/*     */         }
/*  65 */       } else if (((Boolean)ModuleManager.noSlow.sneak.getValue()).booleanValue()) {
/*  66 */         cir.setReturnValue(Boolean.valueOf(false));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"move"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")}, cancellable = true)
/*     */   public void onMoveHook(class_1313 movementType, class_243 movement, CallbackInfo ci) {
/*  73 */     if (Module.fullNullCheck())
/*  74 */       return;  EventMove event = new EventMove(movement.field_1352, movement.field_1351, movement.field_1350);
/*  75 */     ThunderHack.EVENT_BUS.post(event);
/*  76 */     if (event.isCancelled()) {
/*  77 */       method_5784(movementType, new class_243(event.getX(), event.getY(), event.getZ()));
/*  78 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"sendMovementPackets"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void sendMovementPacketsHook(CallbackInfo info) {
/*  84 */     if (Module.fullNullCheck())
/*  85 */       return;  EventSync event = new EventSync(method_36454(), method_36455());
/*  86 */     ThunderHack.EVENT_BUS.post(event);
/*  87 */     this.postAction = event.getPostAction();
/*  88 */     EventSprint e = new EventSprint(method_5624());
/*  89 */     ThunderHack.EVENT_BUS.post(e);
/*  90 */     ThunderHack.EVENT_BUS.post(new EventAfterRotate());
/*  91 */     if (e.getSprintState() != Module.mc.field_1724.field_3919) {
/*  92 */       if (e.getSprintState()) {
/*  93 */         Module.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)this, class_2848.class_2849.field_12981));
/*     */       } else {
/*  95 */         Module.mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)this, class_2848.class_2849.field_12985));
/*     */       } 
/*  97 */       Module.mc.field_1724.field_3919 = e.getSprintState();
/*     */     } 
/*  99 */     this.pre_sprint_state = Module.mc.field_1724.field_3919;
/* 100 */     Core.lockSprint = true;
/*     */     
/* 102 */     if (event.isCancelled()) info.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"sendMovementPackets"}, at = {@At("RETURN")}, cancellable = true)
/*     */   private void sendMovementPacketsPostHook(CallbackInfo info) {
/* 107 */     if (Module.fullNullCheck())
/* 108 */       return;  Module.mc.field_1724.field_3919 = this.pre_sprint_state;
/* 109 */     Core.lockSprint = false;
/* 110 */     EventPostSync event = new EventPostSync();
/* 111 */     ThunderHack.EVENT_BUS.post(event);
/* 112 */     if (this.postAction != null) {
/* 113 */       this.postAction.run();
/* 114 */       this.postAction = null;
/*     */     } 
/* 116 */     if (event.isCancelled())
/* 117 */       info.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"tick"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendMovementPackets()V", ordinal = 0, shift = At.Shift.AFTER)}, cancellable = true)
/*     */   private void PostUpdateHook(CallbackInfo info) {
/* 122 */     if (Module.fullNullCheck())
/* 123 */       return;  if (this.updateLock) {
/*     */       return;
/*     */     }
/* 126 */     PostPlayerUpdateEvent playerUpdateEvent = new PostPlayerUpdateEvent();
/* 127 */     ThunderHack.EVENT_BUS.post(playerUpdateEvent);
/* 128 */     if (playerUpdateEvent.isCancelled()) {
/* 129 */       info.cancel();
/* 130 */       if (playerUpdateEvent.getIterations() > 0) {
/* 131 */         for (int i = 0; i < playerUpdateEvent.getIterations(); i++) {
/* 132 */           this.updateLock = true;
/* 133 */           method_5773();
/* 134 */           this.updateLock = false;
/* 135 */           method_3136();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"pushOutOfBlocks"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void onPushOutOfBlocksHook(double x, double d, CallbackInfo info) {
/* 143 */     if (ModuleManager.noPush.isEnabled() && ((Boolean)ModuleManager.noPush.blocks.getValue()).booleanValue()) {
/* 144 */       info.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"tickNausea"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void updateNauseaHook(CallbackInfo ci) {
/* 150 */     if (ModuleManager.portalInventory.isEnabled())
/* 151 */       ci.cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientPlayerEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */