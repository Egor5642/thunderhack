/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_2874;
/*    */ import net.minecraft.class_3532;
/*    */ import net.minecraft.class_765;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.Fullbright;
/*    */ 
/*    */ @Mixin({class_765.class})
/*    */ public class MixinLightmapTextureManager
/*    */ {
/*    */   @Inject(method = {"getDarknessFactor(F)F"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void getDarknessFactor(float tickDelta, CallbackInfoReturnable<Float> info) {
/* 18 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.darkness.getValue()).booleanValue()) info.setReturnValue(Float.valueOf(0.0F)); 
/*    */   }
/*    */   @Inject(method = {"getBrightness"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void getBrightnessHook(class_2874 type, int lightLevel, CallbackInfoReturnable<Float> cir) {
/* 22 */     if (ModuleManager.fullbright.isEnabled()) {
/* 23 */       float f = lightLevel / 15.0F;
/* 24 */       float g = f / (4.0F - 3.0F * f);
/* 25 */       cir.setReturnValue(Float.valueOf(Math.max(class_3532.method_16439(type.comp_656(), g, 1.0F), ((Float)Fullbright.minBright.getValue()).floatValue())));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinLightmapTextureManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */