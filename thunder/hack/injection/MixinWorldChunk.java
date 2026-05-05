/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_2818;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.EventSetBlockState;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_2818.class})
/*    */ public class MixinWorldChunk
/*    */ {
/*    */   @Inject(method = {"setBlockState"}, at = {@At("RETURN")})
/*    */   private void setBlockStateHook(class_2338 pos, class_2680 state, boolean moved, CallbackInfoReturnable<class_2680> cir) {
/* 23 */     if (this.field_12858.field_9236)
/* 24 */       ThunderHack.EVENT_BUS.post(new EventSetBlockState(pos, (class_2680)cir.getReturnValue(), state)); 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   class_1937 field_12858;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinWorldChunk.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */