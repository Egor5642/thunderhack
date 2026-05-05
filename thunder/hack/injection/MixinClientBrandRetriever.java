/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.client.ClientBrandRetriever;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({ClientBrandRetriever.class})
/*    */ public class MixinClientBrandRetriever {
/*    */   @Inject(method = {"getClientModName"}, at = {@At("HEAD")}, cancellable = true, remap = false)
/*    */   private static void getClientModNameHook(CallbackInfoReturnable<String> cir) {
/* 14 */     if (ModuleManager.clientSpoof.isEnabled())
/* 15 */       cir.setReturnValue(ModuleManager.clientSpoof.getClientName()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientBrandRetriever.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */