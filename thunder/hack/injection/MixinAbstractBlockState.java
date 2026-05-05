/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_4970;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_4970.class_4971.class})
/*    */ public class MixinAbstractBlockState {
/*    */   @Inject(method = {"getLuminance"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getLuminanceHook(CallbackInfoReturnable<Integer> cir) {
/* 14 */     if (ModuleManager.xray.isEnabled())
/* 15 */       cir.setReturnValue(Integer.valueOf(15)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinAbstractBlockState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */