/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_5329;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventCollision;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {class_5329.class}, priority = 800)
/*    */ public abstract class MixinBlockCollisionSpliterator
/*    */ {
/*    */   @Redirect(method = {"computeNext"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"))
/*    */   private class_2680 computeNextHook(class_1922 instance, class_2338 blockPos) {
/* 20 */     if (!ModuleManager.antiWeb.isEnabled() && !ModuleManager.phase.isEnabled() && !ModuleManager.jesus.isEnabled())
/* 21 */       return instance.method_8320(blockPos); 
/* 22 */     EventCollision event = new EventCollision(instance.method_8320(blockPos), blockPos);
/* 23 */     ThunderHack.EVENT_BUS.post(event);
/* 24 */     return event.getState();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBlockCollisionSpliterator.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */