/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1747;
/*    */ import net.minecraft.class_1750;
/*    */ import net.minecraft.class_2680;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.EventPlaceBlock;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_1747.class})
/*    */ public class MixinBlockItem {
/*    */   @Inject(method = {"place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z"}, at = {@At("RETURN")})
/*    */   private void onPlace(@NotNull class_1750 context, class_2680 state, CallbackInfoReturnable<Boolean> info) {
/* 19 */     if (Module.fullNullCheck())
/* 20 */       return;  if ((context.method_8045()).field_9236)
/* 21 */       ThunderHack.EVENT_BUS.post(new EventPlaceBlock(context.method_8037(), state.method_26204())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBlockItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */