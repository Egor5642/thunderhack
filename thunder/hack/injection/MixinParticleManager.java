/*    */ package thunder.hack.injection;
/*    */ import net.minecraft.class_702;
/*    */ import net.minecraft.class_703;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.NoRender;
/*    */ 
/*    */ @Mixin({class_702.class})
/*    */ public class MixinParticleManager {
/*    */   @Inject(at = {@At("HEAD")}, method = {"addParticle(Lnet/minecraft/client/particle/Particle;)V"}, cancellable = true)
/*    */   public void addParticleHook(class_703 p, CallbackInfo e) {
/* 15 */     NoRender nR = ModuleManager.noRender;
/*    */     
/* 17 */     if (!nR.isEnabled()) {
/*    */       return;
/*    */     }
/* 20 */     if (((Boolean)nR.elderGuardian.getValue()).booleanValue() && p instanceof net.minecraft.class_700) {
/* 21 */       e.cancel();
/*    */     }
/* 23 */     if (((Boolean)nR.explosions.getValue()).booleanValue() && p instanceof net.minecraft.class_691) {
/* 24 */       e.cancel();
/*    */     }
/* 26 */     if (((Boolean)nR.campFire.getValue()).booleanValue() && p instanceof net.minecraft.class_3937) {
/* 27 */       e.cancel();
/*    */     }
/* 29 */     if (((Boolean)nR.breakParticles.getValue()).booleanValue() && p instanceof net.minecraft.class_727) {
/* 30 */       e.cancel();
/*    */     }
/* 32 */     if (((Boolean)nR.fireworks.getValue()).booleanValue() && (p instanceof net.minecraft.class_677.class_681 || p instanceof net.minecraft.class_677.class_678))
/* 33 */       e.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinParticleManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */