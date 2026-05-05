/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1058;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_4603;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_4603.class})
/*    */ public class MixinInGameOverlayRenderer {
/*    */   @Inject(method = {"renderFireOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderFireOverlayHook(class_310 minecraftClient, class_4587 matrixStack, CallbackInfo ci) {
/* 17 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.fireOverlay.getValue()).booleanValue())
/* 18 */       ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderUnderwaterOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderUnderwaterOverlayHook(class_310 minecraftClient, class_4587 matrixStack, CallbackInfo ci) {
/* 23 */     if ((ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.waterOverlay.getValue()).booleanValue()) || ModuleManager.shaders.isEnabled())
/* 24 */       ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderInWallOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderInWallOverlayHook(class_1058 sprite, class_4587 matrices, CallbackInfo ci) {
/* 29 */     if ((ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.blockOverlay.getValue()).booleanValue()) || ModuleManager.shaders.isEnabled())
/* 30 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinInGameOverlayRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */