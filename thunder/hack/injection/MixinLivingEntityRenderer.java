/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1309;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3887;
/*     */ import net.minecraft.class_4050;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_583;
/*     */ import net.minecraft.class_922;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.injection.accesors.IClientPlayerEntity;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_922.class})
/*     */ public abstract class MixinLivingEntityRenderer<T extends class_1309, M extends class_583<T>>
/*     */ {
/*     */   private class_1309 lastEntity;
/*     */   private float originalHeadYaw;
/*     */   private float originalPrevHeadYaw;
/*     */   private float originalPrevHeadPitch;
/*     */   private float originalHeadPitch;
/*     */   @Shadow
/*     */   protected M field_4737;
/*     */   @Shadow
/*     */   @Final
/*     */   protected List<class_3887<T, M>> field_4738;
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void onRenderPre(T livingEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
/*  55 */     if (Module.fullNullCheck())
/*  56 */       return;  if (Module.mc.field_1724 != null && livingEntity == Module.mc.field_1724 && Module.mc.field_1724.method_49694() == null && ((Boolean)ClientSettings.renderRotations.getValue()).booleanValue() && !ThunderHack.isFuturePresent()) {
/*  57 */       this.originalHeadYaw = ((class_1309)livingEntity).field_6241;
/*  58 */       this.originalPrevHeadYaw = ((class_1309)livingEntity).field_6259;
/*  59 */       this.originalPrevHeadPitch = ((class_1309)livingEntity).field_6004;
/*  60 */       this.originalHeadPitch = livingEntity.method_36455();
/*     */       
/*  62 */       livingEntity.method_36457(((IClientPlayerEntity)(class_310.method_1551()).field_1724).getLastPitch());
/*  63 */       ((class_1309)livingEntity).field_6004 = Managers.PLAYER.lastPitch;
/*  64 */       ((class_1309)livingEntity).field_6241 = ((IClientPlayerEntity)(class_310.method_1551()).field_1724).getLastYaw();
/*  65 */       ((class_1309)livingEntity).field_6283 = Render2DEngine.interpolateFloat(Managers.PLAYER.prevBodyYaw, Managers.PLAYER.bodyYaw, Render3DEngine.getTickDelta());
/*  66 */       ((class_1309)livingEntity).field_6259 = Managers.PLAYER.lastYaw;
/*  67 */       ((class_1309)livingEntity).field_6220 = Render2DEngine.interpolateFloat(Managers.PLAYER.prevBodyYaw, Managers.PLAYER.bodyYaw, Render3DEngine.getTickDelta());
/*     */     } 
/*     */     
/*  70 */     if (livingEntity != Module.mc.field_1724 && ModuleManager.freeCam.isEnabled() && ((Boolean)ModuleManager.freeCam.track.getValue()).booleanValue() && ModuleManager.freeCam.trackEntity != null && ModuleManager.freeCam.trackEntity == livingEntity) {
/*  71 */       ci.cancel();
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     this.lastEntity = (class_1309)livingEntity;
/*     */     
/*  77 */     if (livingEntity instanceof class_1657) { class_1657 pe = (class_1657)livingEntity; if (ModuleManager.chams.isEnabled() && ((Boolean)ModuleManager.chams.players.getValue()).booleanValue()) {
/*  78 */         ModuleManager.chams.renderPlayer(pe, f, g, matrixStack, i, (class_583)this.field_4737, ci, () -> postRender((T)livingEntity));
/*  79 */         if (!pe.method_7325()) {
/*     */ 
/*     */ 
/*     */           
/*  83 */           matrixStack.method_22903();
/*  84 */           float h = class_3532.method_17821(g, pe.field_6220, pe.field_6283);
/*  85 */           float j = class_3532.method_17821(g, pe.field_6259, pe.field_6241);
/*  86 */           float k = j - h; class_1297 entity;
/*  87 */           if (pe.method_5765() && entity = pe.method_5854() instanceof class_1309) {
/*  88 */             class_1309 livingEntity2 = (class_1309)entity;
/*  89 */             h = class_3532.method_17821(g, livingEntity2.field_6220, livingEntity2.field_6283);
/*  90 */             k = j - h;
/*  91 */             float f1 = class_3532.method_15393(k);
/*  92 */             if (f1 < -85.0F) {
/*  93 */               f1 = -85.0F;
/*     */             }
/*  95 */             if (f1 >= 85.0F) {
/*  96 */               f1 = 85.0F;
/*     */             }
/*  98 */             h = j - f1;
/*  99 */             if (f1 * f1 > 2500.0F) {
/* 100 */               h += f1 * 0.2F;
/*     */             }
/* 102 */             k = j - h;
/*     */           } 
/* 104 */           float m = class_3532.method_16439(g, pe.field_6004, pe.method_36455());
/* 105 */           if (class_922.method_38563((class_1309)pe)) {
/* 106 */             m *= -1.0F;
/* 107 */             k *= -1.0F;
/*     */           }  class_2350 direction;
/* 109 */           if (pe.method_41328(class_4050.field_18078) && (direction = pe.method_18401()) != null) {
/* 110 */             float f1 = pe.method_18381(class_4050.field_18076) - 0.1F;
/* 111 */             matrixStack.method_46416(-direction.method_10148() * f1, 0.0F, -direction.method_10165() * f1);
/*     */           } 
/* 113 */           float l = pe.field_6012 + g;
/* 114 */           ModuleManager.chams.setupTransforms1(pe, matrixStack, l, h, g);
/* 115 */           matrixStack.method_22905(-1.0F, -1.0F, 1.0F);
/* 116 */           matrixStack.method_22905(0.9375F, 0.9375F, 0.9375F);
/* 117 */           matrixStack.method_46416(0.0F, -1.501F, 0.0F);
/* 118 */           float n = 0.0F;
/* 119 */           float o = 0.0F;
/* 120 */           if (!pe.method_5765() && pe.method_5805()) {
/* 121 */             n = pe.field_42108.method_48570(g);
/* 122 */             o = pe.field_42108.method_48572(g);
/* 123 */             if (pe.method_6109()) {
/* 124 */               o *= 3.0F;
/*     */             }
/* 126 */             if (n > 1.0F) {
/* 127 */               n = 1.0F;
/*     */             }
/*     */           } 
/* 130 */           for (class_3887<T, M> featureRenderer : this.field_4738) {
/* 131 */             featureRenderer.method_4199(matrixStack, vertexConsumerProvider, i, (class_1297)livingEntity, o, n, g, l, k, m);
/*     */           }
/* 133 */           matrixStack.method_22909();
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   @Unique
/*     */   public void postRender(T livingEntity) {
/* 140 */     if (Module.fullNullCheck())
/* 141 */       return;  if (Module.mc.field_1724 != null && livingEntity == Module.mc.field_1724 && Module.mc.field_1724.method_49694() == null && ((Boolean)ClientSettings.renderRotations.getValue()).booleanValue() && !ThunderHack.isFuturePresent()) {
/* 142 */       ((class_1309)livingEntity).field_6004 = this.originalPrevHeadPitch;
/* 143 */       livingEntity.method_36457(this.originalHeadPitch);
/* 144 */       ((class_1309)livingEntity).field_6241 = this.originalHeadYaw;
/* 145 */       ((class_1309)livingEntity).field_6259 = this.originalPrevHeadYaw;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("TAIL")})
/*     */   public void onRenderPost(T livingEntity, float f, float g, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, CallbackInfo ci) {
/* 151 */     if (Module.fullNullCheck())
/* 152 */       return;  postRender(livingEntity);
/*     */   }
/*     */   
/*     */   @ModifyArgs(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"))
/*     */   private void renderHook(Args args) {
/* 157 */     if (Module.fullNullCheck())
/*     */       return; 
/* 159 */     float alpha = -1.0F;
/*     */     
/* 161 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.antiPlayerCollision.getValue()).booleanValue() && this.lastEntity != Module.mc.field_1724) { class_1309 class_13091 = this.lastEntity; if (class_13091 instanceof class_1657) { class_1657 pl = (class_1657)class_13091; if (!pl.method_5767())
/* 162 */           alpha = MathUtility.clamp((float)(Module.mc.field_1724.method_5707(this.lastEntity.method_19538()) / 3.0D) + 0.2F, 0.0F, 1.0F);  }
/*     */        }
/* 164 */      if (this.lastEntity != Module.mc.field_1724) { class_1309 class_13091 = this.lastEntity; if (class_13091 instanceof class_1657) { class_1657 pl = (class_1657)class_13091; if (pl.method_5767() && ModuleManager.serverHelper.isEnabled() && ((Boolean)ModuleManager.serverHelper.trueSight.getValue()).booleanValue())
/* 165 */           alpha = 0.3F;  }
/*     */        }
/* 167 */      if (alpha != -1.0F)
/* 168 */       args.set(4, Integer.valueOf(Render2DEngine.applyOpacity(654311423, alpha))); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinLivingEntityRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */