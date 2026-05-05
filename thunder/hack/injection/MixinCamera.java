/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_4184;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_4184.class})
/*    */ public abstract class MixinCamera {
/*    */   @Shadow
/*    */   private boolean field_18719;
/*    */   
/*    */   @Shadow
/*    */   protected abstract float method_19318(float paramFloat);
/*    */   
/*    */   @ModifyArgs(method = {"update"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;moveBy(FFF)V", ordinal = 0))
/*    */   private void modifyCameraDistance(Args args) {
/* 26 */     if (ModuleManager.noCameraClip.isEnabled()) {
/* 27 */       args.set(0, Float.valueOf(-method_19318(ModuleManager.noCameraClip.getDistance())));
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"clipToSpace"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onClipToSpace(float f, CallbackInfoReturnable<Float> cir) {
/* 33 */     if (ModuleManager.noCameraClip.isEnabled()) {
/* 34 */       cir.setReturnValue(Float.valueOf(ModuleManager.noCameraClip.getDistance()));
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"update"}, at = {@At("TAIL")})
/*    */   private void updateHook(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
/* 40 */     if (ModuleManager.freeCam.isEnabled()) {
/* 41 */       this.field_18719 = true;
/*    */     }
/*    */   }
/*    */   
/*    */   @ModifyArgs(method = {"update"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
/*    */   private void setRotationHook(Args args) {
/* 47 */     if (ModuleManager.freeCam.isEnabled())
/* 48 */       args.setAll(new Object[] { Float.valueOf(ModuleManager.freeCam.getFakeYaw()), Float.valueOf(ModuleManager.freeCam.getFakePitch()) }); 
/*    */   }
/*    */   
/*    */   @ModifyArgs(method = {"update"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V"))
/*    */   private void setPosHook(Args args) {
/* 53 */     if (ModuleManager.freeCam.isEnabled())
/* 54 */       args.setAll(new Object[] { Double.valueOf(ModuleManager.freeCam.getFakeX()), Double.valueOf(ModuleManager.freeCam.getFakeY()), Double.valueOf(ModuleManager.freeCam.getFakeZ()) }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinCamera.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */