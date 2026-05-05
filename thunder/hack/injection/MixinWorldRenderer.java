/*    */ package thunder.hack.injection;
/*    */ import net.minecraft.class_279;
/*    */ import net.minecraft.class_761;
/*    */ import net.minecraft.class_765;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.core.manager.client.ShaderManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_761.class})
/*    */ public abstract class MixinWorldRenderer {
/*    */   @ModifyArg(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;setupTerrain(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;ZZ)V"), index = 3)
/*    */   private boolean renderSetupTerrainModifyArg(boolean spectator) {
/* 19 */     return (ModuleManager.freeCam.isEnabled() || spectator);
/*    */   }
/*    */   
/*    */   @Redirect(method = {"render"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/PostEffectProcessor;render(F)V", ordinal = 0))
/*    */   private void replaceShaderHook(class_279 instance, float tickDelta) {
/* 24 */     ShaderManager.Shader shaders = (ShaderManager.Shader)ModuleManager.shaders.mode.getValue();
/* 25 */     if (ModuleManager.shaders.isEnabled() && Module.mc.field_1687 != null) {
/* 26 */       if (Managers.SHADER.fullNullCheck())
/* 27 */         return;  Managers.SHADER.setupShader(shaders, Managers.SHADER.getShaderOutline(shaders));
/*    */     } else {
/* 29 */       instance.method_1258(tickDelta);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderWeather"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderWeatherHook(class_765 manager, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
/* 35 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.noWeather.getValue()).booleanValue())
/* 36 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinWorldRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */