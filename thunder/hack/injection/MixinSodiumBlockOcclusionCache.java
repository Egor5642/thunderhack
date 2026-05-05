/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2680;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Pseudo;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.XRay;
/*    */ 
/*    */ 
/*    */ @Pseudo
/*    */ @Mixin(targets = {"me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache"}, remap = false)
/*    */ public class MixinSodiumBlockOcclusionCache
/*    */ {
/*    */   @Inject(method = {"shouldDrawSide"}, at = {@At("RETURN")}, cancellable = true)
/*    */   void shouldDrawSideHook(class_2680 state, class_1922 view, class_2338 pos, class_2350 facing, CallbackInfoReturnable<Boolean> cir) {
/* 22 */     if (ModuleManager.xray.isEnabled() && ((Boolean)ModuleManager.xray.wallHack.getValue()).booleanValue())
/* 23 */       cir.setReturnValue(Boolean.valueOf(XRay.isCheckableOre(state.method_26204()))); 
/* 24 */     if (ModuleManager.autoAnchor.isEnabled() && state.method_26204() instanceof net.minecraft.class_2358)
/* 25 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinSodiumBlockOcclusionCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */