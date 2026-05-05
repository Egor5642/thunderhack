/*    */ package thunder.hack.injection;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_4184;
/*    */ import net.minecraft.class_758;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.WorldTweaks;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ 
/*    */ @Mixin({class_758.class})
/*    */ public class MixinBackgroundRenderer {
/*    */   @Inject(method = {"applyFog"}, at = {@At("TAIL")})
/*    */   private static void onApplyFog(class_4184 camera, class_758.class_4596 fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo info) {
/* 19 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.fog.getValue()).booleanValue() && 
/* 20 */       fogType == class_758.class_4596.field_20946) {
/* 21 */       RenderSystem.setShaderFogStart(viewDistance * 4.0F);
/* 22 */       RenderSystem.setShaderFogEnd(viewDistance * 4.25F);
/*    */     } 
/*    */ 
/*    */     
/* 26 */     if (ModuleManager.worldTweaks.isEnabled() && ((BooleanSettingGroup)WorldTweaks.fogModify.getValue()).isEnabled()) {
/* 27 */       RenderSystem.setShaderFogStart(((Integer)WorldTweaks.fogStart.getValue()).intValue());
/* 28 */       RenderSystem.setShaderFogEnd(((Integer)WorldTweaks.fogEnd.getValue()).intValue());
/* 29 */       RenderSystem.setShaderFogColor(((ColorSetting)WorldTweaks.fogColor.getValue()).getGlRed(), ((ColorSetting)WorldTweaks.fogColor.getValue()).getGlGreen(), ((ColorSetting)WorldTweaks.fogColor.getValue()).getGlBlue());
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void onGetFogModifier(class_1297 entity, float tickDelta, CallbackInfoReturnable<Object> info) {
/* 35 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.blindness.getValue()).booleanValue()) info.setReturnValue(null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBackgroundRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */