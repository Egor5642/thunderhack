/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_8685;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.client.Media;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ @Mixin({class_8685.class})
/*    */ public class MixinSkinTextures {
/*    */   @Inject(method = {"texture"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getSkinTextureHook(CallbackInfoReturnable<class_2960> cir) {
/* 17 */     if (ModuleManager.media.isEnabled() && ((Boolean)Media.skinProtect.getValue()).booleanValue())
/* 18 */       cir.setReturnValue(TextureStorage.sunRiseSkin); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinSkinTextures.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */