/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_3830;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_3830.class})
/*    */ public class MixinSweetBerryBushBlock {
/*    */   @Inject(method = {"onEntityCollision"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onEntityCollisionHook(class_2680 state, class_1937 world, class_2338 pos, class_1297 entity, CallbackInfo ci) {
/* 18 */     if (ModuleManager.noSlow.isEnabled() && ((Boolean)ModuleManager.noSlow.sweetBerryBush.getValue()).booleanValue())
/* 19 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinSweetBerryBushBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */