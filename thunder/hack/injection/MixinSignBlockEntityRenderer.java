/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_4597;
/*    */ import net.minecraft.class_8242;
/*    */ import net.minecraft.class_837;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_837.class})
/*    */ public class MixinSignBlockEntityRenderer {
/*    */   @Inject(method = {"renderText"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public final void renderTextHook(class_2338 pos, class_8242 signText, class_4587 matrices, class_4597 vertexConsumers, int light, int lineHeight, int lineWidth, boolean front, CallbackInfo ci) {
/* 18 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.signText.getValue()).booleanValue())
/* 19 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinSignBlockEntityRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */