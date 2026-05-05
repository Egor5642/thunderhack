/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_3532;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventFixVelocity;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.combat.HitBox;
/*     */ import thunder.hack.features.modules.render.Shaders;
/*     */ import thunder.hack.features.modules.render.Trails;
/*     */ import thunder.hack.utility.interfaces.IEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_1297.class})
/*     */ public abstract class MixinEntity
/*     */   implements IEntity
/*     */ {
/*     */   @Shadow
/*     */   private class_238 field_6005;
/*     */   
/*     */   @Shadow
/*     */   protected abstract class_2338 method_23314();
/*     */   
/*     */   public List<Trails.Trail> getTrails() {
/*  44 */     return this.trails;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_2338 thunderHack_Recode$getVelocityBP() {
/*  49 */     return method_23314();
/*     */   }
/*     */   @Unique
/*  52 */   public List<Trails.Trail> trails = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ModifyArgs(method = {"pushAwayFrom"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
/*     */   public void pushAwayFromHook(Args args) {
/*  59 */     if (this == Module.mc.field_1724 && ModuleManager.noPush.isEnabled() && ((Boolean)ModuleManager.noPush.players.getValue()).booleanValue()) {
/*  60 */       args.set(0, Double.valueOf(0.0D));
/*  61 */       args.set(1, Double.valueOf(0.0D));
/*  62 */       args.set(2, Double.valueOf(0.0D));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"updateVelocity"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void updateVelocityHook(float speed, class_243 movementInput, CallbackInfo ci) {
/*  68 */     if (Module.fullNullCheck())
/*  69 */       return;  if (this == Module.mc.field_1724) {
/*  70 */       ci.cancel();
/*  71 */       EventFixVelocity event = new EventFixVelocity(movementInput, speed, Module.mc.field_1724.method_36454(), movementInputToVelocityC(movementInput, speed, Module.mc.field_1724.method_36454()));
/*  72 */       ThunderHack.EVENT_BUS.post(event);
/*  73 */       Module.mc.field_1724.method_18799(Module.mc.field_1724.method_18798().method_1019(event.getVelocity()));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Unique
/*     */   private static class_243 movementInputToVelocityC(class_243 movementInput, float speed, float yaw) {
/*  79 */     double d = movementInput.method_1027();
/*  80 */     if (d < 1.0E-7D) {
/*  81 */       return class_243.field_1353;
/*     */     }
/*  83 */     class_243 vec3d = ((d > 1.0D) ? movementInput.method_1029() : movementInput).method_1021(speed);
/*  84 */     float f = class_3532.method_15374(yaw * 0.017453292F);
/*  85 */     float g = class_3532.method_15362(yaw * 0.017453292F);
/*  86 */     return new class_243(vec3d.field_1352 * g - vec3d.field_1350 * f, vec3d.field_1351, vec3d.field_1350 * g + vec3d.field_1352 * f);
/*     */   }
/*     */   
/*     */   @Inject(method = {"getBoundingBox"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public final void getBoundingBox(CallbackInfoReturnable<class_238> cir) {
/*  91 */     if (ModuleManager.hitBox.isEnabled() && Module.mc != null && Module.mc.field_1724 != null && ((class_1297)this).method_5628() != Module.mc.field_1724.method_5628() && (ModuleManager.aura.isDisabled() || ((Boolean)HitBox.affectToAura.getValue()).booleanValue())) {
/*  92 */       cir.setReturnValue(new class_238(this.field_6005.field_1323 - (((Float)HitBox.XZExpand.getValue()).floatValue() / 2.0F), this.field_6005.field_1322 - (((Float)HitBox.YExpand.getValue()).floatValue() / 2.0F), this.field_6005.field_1321 - (((Float)HitBox.XZExpand.getValue()).floatValue() / 2.0F), this.field_6005.field_1320 + (((Float)HitBox.XZExpand.getValue()).floatValue() / 2.0F), this.field_6005.field_1325 + (((Float)HitBox.YExpand.getValue()).floatValue() / 2.0F), this.field_6005.field_1324 + (((Float)HitBox.XZExpand.getValue()).floatValue() / 2.0F)));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"isGlowing"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void isGlowingHook(CallbackInfoReturnable<Boolean> cir) {
/*  98 */     Shaders shaders = ModuleManager.shaders;
/*  99 */     if (shaders.isEnabled()) {
/* 100 */       cir.setReturnValue(Boolean.valueOf(shaders.shouldRender((class_1297)this)));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"isOnFire"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void isOnFireHook(CallbackInfoReturnable<Boolean> cir) {
/* 106 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.fireEntity.getValue()).booleanValue()) {
/* 107 */       cir.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"isInvisibleTo"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void isInvisibleToHook(class_1657 player, CallbackInfoReturnable<Boolean> cir) {
/* 113 */     if (ModuleManager.serverHelper.isEnabled() && ((Boolean)ModuleManager.serverHelper.trueSight.getValue()).booleanValue()) {
/* 114 */       cir.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"isInLava"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void isInLavaHook(CallbackInfoReturnable<Boolean> cir) {
/* 120 */     if ((ModuleManager.jesus.isEnabled() || ModuleManager.noWaterCollision.isEnabled()) && Module.mc.field_1724 != null && ((class_1297)this).method_5628() == Module.mc.field_1724.method_5628())
/* 121 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"isTouchingWater"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void isTouchingWaterHook(CallbackInfoReturnable<Boolean> cir) {
/* 126 */     if ((ModuleManager.jesus.isEnabled() || ModuleManager.noWaterCollision.isEnabled()) && Module.mc.field_1724 != null && ((class_1297)this).method_5628() == Module.mc.field_1724.method_5628())
/* 127 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"setSwimming"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void setSwimmingHook(boolean swimming, CallbackInfo ci) {
/* 132 */     if ((ModuleManager.jesus.isEnabled() || ModuleManager.noWaterCollision.isEnabled()) && swimming && Module.mc.field_1724 != null && ((class_1297)this).method_5628() == Module.mc.field_1724.method_5628())
/* 133 */       ci.cancel(); 
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"changeLookDirection"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
/*     */   private double changeLookDirectionHook0(double value) {
/* 138 */     if (ModuleManager.viewLock.isEnabled() && ((Boolean)ModuleManager.viewLock.yaw.getValue()).booleanValue())
/* 139 */       return 0.0D; 
/* 140 */     return value;
/*     */   }
/*     */   
/*     */   @ModifyVariable(method = {"changeLookDirection"}, at = @At("HEAD"), ordinal = 1, argsOnly = true)
/*     */   private double changeLookDirectionHook1(double value) {
/* 145 */     if (ModuleManager.viewLock.isEnabled() && ((Boolean)ModuleManager.viewLock.pitch.getValue()).booleanValue())
/* 146 */       return 0.0D; 
/* 147 */     return value;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */