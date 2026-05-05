/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1313;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_243;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventTravel;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.combat.Aura;
/*     */ import thunder.hack.features.modules.movement.WaterSpeed;
/*     */ import thunder.hack.features.modules.render.Animations;
/*     */ import thunder.hack.utility.interfaces.IEntityLiving;
/*     */ 
/*     */ @Mixin({class_1309.class})
/*     */ public class MixinEntityLiving implements IEntityLiving {
/*     */   @Shadow
/*     */   protected double field_6224;
/*     */   @Shadow
/*     */   protected double field_6245;
/*     */   @Shadow
/*     */   protected double field_6263;
/*     */   @Unique
/*     */   double prevServerX;
/*     */   @Unique
/*     */   double prevServerY;
/*     */   @Unique
/*     */   double prevServerZ;
/*     */   @Unique
/*  42 */   public List<Aura.Position> positonHistory = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Aura.Position> getPositionHistory() {
/*  47 */     return this.positonHistory;
/*     */   }
/*     */   
/*     */   @Inject(method = {"getHandSwingDuration"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> info) {
/*  52 */     if (!((Boolean)ModuleManager.noRender.noSwing.getValue()).booleanValue() && ModuleManager.animations.shouldChangeAnimationDuration() && ((Boolean)Animations.slowAnimation.getValue()).booleanValue())
/*  53 */       info.setReturnValue(Animations.slowAnimationVal.getValue()); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"updateTrackedPositionAndAngles"}, at = {@At("HEAD")})
/*     */   private void updateTrackedPositionAndAnglesHook(double x, double y, double z, float yaw, float pitch, int interpolationSteps, CallbackInfo ci) {
/*  58 */     if (Module.fullNullCheck())
/*  59 */       return;  this.prevServerX = this.field_6224;
/*  60 */     this.prevServerY = this.field_6245;
/*  61 */     this.prevServerZ = this.field_6263;
/*  62 */     this.positonHistory.add(new Aura.Position(this.field_6224, this.field_6245, this.field_6263));
/*  63 */     this.positonHistory.removeIf(Aura.Position::shouldRemove);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevServerX() {
/*  68 */     return this.prevServerX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevServerY() {
/*  73 */     return this.prevServerY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPrevServerZ() {
/*  78 */     return this.prevServerZ;
/*     */   }
/*     */   
/*     */   @Unique
/*     */   private boolean prevFlying = false;
/*     */   
/*     */   @Inject(method = {"isFallFlying"}, at = {@At("TAIL")}, cancellable = true)
/*     */   public void isFallFlyingHook(CallbackInfoReturnable<Boolean> cir) {
/*  86 */     if (ModuleManager.elytraRecast.isEnabled()) {
/*  87 */       boolean elytra = ((Boolean)cir.getReturnValue()).booleanValue();
/*  88 */       if (this.prevFlying && !((Boolean)cir.getReturnValue()).booleanValue()) {
/*  89 */         cir.setReturnValue(Boolean.valueOf(ModuleManager.elytraRecast.castElytra()));
/*     */       }
/*  91 */       this.prevFlying = elytra;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void travelHook(class_243 movementInput, CallbackInfo ci) {
/*  97 */     if (Module.fullNullCheck())
/*  98 */       return;  if ((class_1309)this != Module.mc.field_1724)
/*  99 */       return;  EventTravel event = new EventTravel(Module.mc.field_1724.method_18798(), true);
/* 100 */     ThunderHack.EVENT_BUS.post(event);
/* 101 */     if (event.isCancelled()) {
/* 102 */       Module.mc.field_1724.method_5784(class_1313.field_6308, event.getmVec());
/* 103 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"travel"}, at = {@At("RETURN")}, cancellable = true)
/*     */   public void travelPostHook(class_243 movementInput, CallbackInfo ci) {
/* 109 */     if (Module.fullNullCheck())
/* 110 */       return;  if ((class_1309)this != Module.mc.field_1724)
/* 111 */       return;  EventTravel event = new EventTravel(movementInput, false);
/* 112 */     ThunderHack.EVENT_BUS.post(event);
/* 113 */     if (event.isCancelled()) {
/* 114 */       Module.mc.field_1724.method_5784(class_1313.field_6308, Module.mc.field_1724.method_18798());
/* 115 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"setSprinting"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
/*     */   private boolean setSprintingHook(boolean sprinting) {
/* 121 */     if (Module.mc.field_1724 != null && Module.mc.field_1687 != null && ModuleManager.waterSpeed.isEnabled() && ModuleManager.waterSpeed.mode.is(WaterSpeed.Mode.CancelResurface) && (
/* 122 */       Module.mc.field_1724.method_5799() || Module.mc.field_1687.method_8320(class_2338.method_49638((class_2374)Module.mc.field_1724.method_19538().method_1031(0.0D, -0.5D, 0.0D))).method_26204() instanceof net.minecraft.class_2404)) {
/* 123 */       return true;
/*     */     }
/* 125 */     return sprinting;
/*     */   }
/*     */   
/*     */   @Inject(method = {"getHandSwingDuration"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void onGetHandSwingDuration(CallbackInfoReturnable<Integer> cir) {
/* 130 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.noSwing.getValue()).booleanValue()) {
/* 131 */       cir.setReturnValue(Integer.valueOf(0));
/* 132 */       cir.cancel();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinEntityLiving.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */