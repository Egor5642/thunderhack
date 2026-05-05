/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_839;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_839.class})
/*    */ public class MixinMobSpawnerBlockEntityRenderer
/*    */ {
/*    */   @Inject(method = {"render(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;Lnet/minecraft/client/render/entity/EntityRenderDispatcher;DD)V"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void renderHook(CallbackInfo ci) {
/* 16 */     if (!Module.fullNullCheck() && ModuleManager.noRender.isOn() && ((Boolean)ModuleManager.noRender.spawnerEntity.getValue()).booleanValue())
/* 17 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinMobSpawnerBlockEntityRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */