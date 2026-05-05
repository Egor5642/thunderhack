/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_9779;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ 
/*    */ 
/*    */ @Mixin({class_9779.class_9781.class})
/*    */ public class MixinDynamic
/*    */ {
/*    */   @Shadow
/*    */   private float field_51958;
/*    */   @Shadow
/*    */   private float field_51959;
/*    */   
/*    */   @Inject(method = {"Lnet/minecraft/client/render/RenderTickCounter$Dynamic;beginRenderTick(J)I"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void beginRenderTickHook(long timeMillis, CallbackInfoReturnable<Integer> cir) {
/* 23 */     if (ThunderHack.TICK_TIMER == 1.0F) {
/*    */       return;
/*    */     }
/* 26 */     this.field_51958 = (float)(timeMillis - this.field_51962) / this.field_51964 * ThunderHack.TICK_TIMER;
/* 27 */     this.field_51962 = timeMillis;
/* 28 */     this.field_51959 += this.field_51958;
/* 29 */     int i = (int)this.field_51959;
/* 30 */     this.field_51959 -= i;
/* 31 */     cir.setReturnValue(Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   private long field_51962;
/*    */   @Final
/*    */   @Shadow
/*    */   private float field_51964;
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinDynamic.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */