/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_2818;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.world.ExplosionUtility;
/*    */ 
/*    */ @Mixin({class_1937.class})
/*    */ public abstract class MixinWorld
/*    */ {
/*    */   @Inject(method = {"getBlockState"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void blockStateHook(class_2338 pos, CallbackInfoReturnable<class_2680> cir) {
/* 20 */     if (ExplosionUtility.terrainIgnore && Module.mc.field_1687 != null && !Module.mc.field_1687.method_24794(pos)) {
/* 21 */       class_2818 worldChunk = Module.mc.field_1687.method_8497(pos.method_10263() >> 4, pos.method_10260() >> 4);
/*    */       
/* 23 */       class_2680 tempState = worldChunk.method_8320(pos);
/*    */       
/* 25 */       if (tempState.method_26204() == class_2246.field_10540 || tempState
/* 26 */         .method_26204() == class_2246.field_9987 || tempState
/* 27 */         .method_26204() == class_2246.field_10443 || tempState
/* 28 */         .method_26204() == class_2246.field_23152)
/*    */         return; 
/* 30 */       cir.setReturnValue(class_2246.field_10124.method_9564());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinWorld.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */