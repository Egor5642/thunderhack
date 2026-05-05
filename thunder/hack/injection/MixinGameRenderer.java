/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_4184;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_5498;
/*     */ import net.minecraft.class_5912;
/*     */ import net.minecraft.class_757;
/*     */ import net.minecraft.class_7833;
/*     */ import net.minecraft.class_9779;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.player.NoEntityTrace;
/*     */ import thunder.hack.utility.math.FrameRateCounter;
/*     */ import thunder.hack.utility.render.BlockAnimationUtility;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.shaders.satin.impl.ReloadableShaderEffectManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_757.class})
/*     */ public abstract class MixinGameRenderer
/*     */ {
/*     */   @Shadow
/*     */   private float field_4005;
/*     */   @Shadow
/*     */   private float field_3988;
/*     */   @Shadow
/*     */   private float field_4004;
/*     */   @Shadow
/*     */   private float field_4025;
/*     */   
/*     */   @Shadow
/*     */   public abstract void method_3182();
/*     */   
/*     */   @Inject(at = {@At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", ordinal = 1, shift = At.Shift.BEFORE)}, method = {"render"})
/*     */   void postHudRenderHook(class_9779 tickCounter, boolean tick, CallbackInfo ci) {
/*  61 */     FrameRateCounter.INSTANCE.recordFrame();
/*     */   }
/*     */   
/*     */   @Inject(at = {@At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = 180, ordinal = 0)}, method = {"renderWorld"})
/*     */   void render3dHook(class_9779 tickCounter, CallbackInfo ci) {
/*  66 */     if (Module.fullNullCheck())
/*     */       return; 
/*  68 */     class_4184 camera = Module.mc.field_1773.method_19418();
/*  69 */     class_4587 matrixStack = new class_4587();
/*  70 */     RenderSystem.getModelViewStack().pushMatrix().mul((Matrix4fc)matrixStack.method_23760().method_23761());
/*  71 */     matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/*  72 */     matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/*  73 */     RenderSystem.applyModelViewMatrix();
/*     */     
/*  75 */     Render3DEngine.lastProjMat.set((Matrix4fc)RenderSystem.getProjectionMatrix());
/*  76 */     Render3DEngine.lastModMat.set((Matrix4fc)RenderSystem.getModelViewMatrix());
/*  77 */     Render3DEngine.lastWorldSpaceMatrix.set((Matrix4fc)matrixStack.method_23760().method_23761());
/*     */     
/*  79 */     Managers.MODULE.onRender3D(matrixStack);
/*  80 */     BlockAnimationUtility.onRender(matrixStack);
/*  81 */     Render3DEngine.onRender3D(matrixStack);
/*     */     
/*  83 */     RenderSystem.getModelViewStack().popMatrix();
/*  84 */     RenderSystem.applyModelViewMatrix();
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderWorld"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V", shift = At.Shift.AFTER)})
/*     */   public void postRender3dHook(class_9779 tickCounter, CallbackInfo ci) {
/*  89 */     if (Module.fullNullCheck())
/*  90 */       return;  Managers.SHADER.renderShaders();
/*     */   }
/*     */   
/*     */   @Redirect(method = {"renderWorld"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"))
/*     */   private float renderWorldHook(float delta, float first, float second) {
/*  95 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.nausea.getValue()).booleanValue()) return 0.0F; 
/*  96 */     return class_3532.method_16439(delta, first, second);
/*     */   }
/*     */   
/*     */   @Inject(method = {"loadPrograms"}, at = {@At("RETURN")})
/*     */   private void loadSatinPrograms(class_5912 factory, CallbackInfo ci) {
/* 101 */     ReloadableShaderEffectManager.INSTANCE.reload(factory);
/*     */   }
/*     */   
/*     */   @Inject(method = {"updateCrosshairTarget"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;findCrosshairTarget(Lnet/minecraft/entity/Entity;DDF)Lnet/minecraft/util/hit/HitResult;")}, cancellable = true)
/*     */   private void onUpdateTargetedEntity(float tickDelta, CallbackInfo info) {
/* 106 */     if (Module.fullNullCheck()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (ModuleManager.freeCam.isEnabled()) {
/* 118 */       Module.mc.method_16011().method_15407();
/* 119 */       info.cancel();
/* 120 */       Module.mc.field_1765 = Managers.PLAYER.getRtxTarget(ModuleManager.freeCam.getFakeYaw(), ModuleManager.freeCam.getFakePitch(), ModuleManager.freeCam.getFakeX(), ModuleManager.freeCam.getFakeY(), ModuleManager.freeCam.getFakeZ());
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"findCrosshairTarget"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void findCrosshairTargetHook(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<class_239> cir) {
/* 126 */     if (ModuleManager.noEntityTrace.isEnabled() && (Module.mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1810 || !((Boolean)NoEntityTrace.ponly.getValue()).booleanValue())) {
/* 127 */       if (Module.mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1829 && ((Boolean)NoEntityTrace.noSword.getValue()).booleanValue())
/* 128 */         return;  double d = Math.max(blockInteractionRange, entityInteractionRange);
/* 129 */       class_243 vec3d = camera.method_5836(tickDelta);
/* 130 */       class_239 hitResult = camera.method_5745(d, tickDelta, false);
/* 131 */       cir.setReturnValue(ensureTargetInRangeCustom(hitResult, vec3d, blockInteractionRange));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"getBasicProjectionMatrix"}, at = {@At("TAIL")}, cancellable = true)
/*     */   public void getBasicProjectionMatrixHook(double fov, CallbackInfoReturnable<Matrix4f> cir) {
/* 137 */     if (ModuleManager.aspectRatio.isEnabled()) {
/* 138 */       class_4587 matrixStack = new class_4587();
/* 139 */       matrixStack.method_23760().method_23761().identity();
/* 140 */       if (this.field_4005 != 1.0F) {
/* 141 */         matrixStack.method_46416(this.field_3988, -this.field_4004, 0.0F);
/* 142 */         matrixStack.method_22905(this.field_4005, this.field_4005, 1.0F);
/*     */       } 
/* 144 */       matrixStack.method_23760().method_23761().mul((Matrix4fc)(new Matrix4f()).setPerspective((float)(fov * 0.01745329238474369D), ((Float)ModuleManager.aspectRatio.ratio.getValue()).floatValue(), 0.05F, this.field_4025 * 4.0F));
/* 145 */       cir.setReturnValue(matrixStack.method_23760().method_23761());
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"getFov(Lnet/minecraft/client/render/Camera;FZ)D"}, at = {@At("TAIL")}, cancellable = true)
/*     */   public void getFov(class_4184 camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cb) {
/* 151 */     if (ModuleManager.fov.isEnabled()) {
/* 152 */       if (((Double)cb.getReturnValue()).doubleValue() == 70.0D && !((Boolean)ModuleManager.fov.itemFov.getValue()).booleanValue() && Module.mc.field_1690.method_31044() != class_5498.field_26664) {
/*     */         return;
/*     */       }
/* 155 */       if (((Boolean)ModuleManager.fov.itemFov.getValue()).booleanValue() && ((Double)cb.getReturnValue()).doubleValue() == 70.0D) {
/* 156 */         cb.setReturnValue(Double.valueOf(((Integer)ModuleManager.fov.itemFovModifier.getValue()).doubleValue()));
/*     */         
/*     */         return;
/*     */       } 
/* 160 */       if (Module.mc.field_1724.method_5869()) {
/*     */         return;
/*     */       }
/* 163 */       cb.setReturnValue(Double.valueOf(((Integer)ModuleManager.fov.fovModifier.getValue()).doubleValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"bobView"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void bobViewHook(class_4587 matrices, float tickDelta, CallbackInfo ci) {
/* 169 */     if (Module.fullNullCheck())
/* 170 */       return;  if (ModuleManager.noBob.isEnabled()) {
/* 171 */       ModuleManager.noBob.bobView(matrices, tickDelta);
/* 172 */       ci.cancel();
/*     */       return;
/*     */     } 
/* 175 */     if (((Boolean)ClientSettings.customBob.getValue()).booleanValue()) {
/* 176 */       ThunderHack.core.bobView(matrices, tickDelta);
/* 177 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Unique
/*     */   private class_239 ensureTargetInRangeCustom(class_239 hitResult, class_243 cameraPos, double interactionRange) {
/* 183 */     class_243 vec3d = hitResult.method_17784();
/* 184 */     if (!vec3d.method_24802((class_2374)cameraPos, interactionRange)) {
/* 185 */       class_243 vec3d2 = hitResult.method_17784();
/* 186 */       class_2350 direction = class_2350.method_10142(vec3d2.field_1352 - cameraPos.field_1352, vec3d2.field_1351 - cameraPos.field_1351, vec3d2.field_1350 - cameraPos.field_1350);
/* 187 */       return (class_239)class_3965.method_17778(vec3d2, direction, class_2338.method_49638((class_2374)vec3d2));
/*     */     } 
/* 189 */     return hitResult;
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"showFloatingItem"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void showFloatingItemHook(class_1799 floatingItem, CallbackInfo info) {
/* 195 */     if (ModuleManager.totemAnimation.isEnabled()) {
/* 196 */       ModuleManager.totemAnimation.showFloatingItem(floatingItem);
/* 197 */       info.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderFloatingItem"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void renderFloatingItemHook(class_332 context, float tickDelta, CallbackInfo ci) {
/* 203 */     if (ModuleManager.totemAnimation.isEnabled()) {
/* 204 */       ModuleManager.totemAnimation.renderFloatingItem(tickDelta);
/* 205 */       ci.cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"tiltViewWhenHurt"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void tiltViewWhenHurtHook(class_4587 matrices, float tickDelta, CallbackInfo ci) {
/* 211 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.hurtCam.getValue()).booleanValue())
/* 212 */       ci.cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinGameRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */