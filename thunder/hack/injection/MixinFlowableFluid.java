/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2382;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2769;
/*    */ import net.minecraft.class_3609;
/*    */ import net.minecraft.class_3610;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_3609.class})
/*    */ public abstract class MixinFlowableFluid
/*    */ {
/*    */   @Shadow
/*    */   protected abstract boolean method_15749(class_1922 paramclass_1922, class_2338 paramclass_2338, class_2350 paramclass_2350);
/*    */   
/*    */   @Inject(method = {"getVelocity"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void getVelocityHook(class_1922 world, class_2338 pos, class_3610 state, CallbackInfoReturnable<class_243> cir) {
/* 26 */     if (ModuleManager.noPush.isEnabled() && ((Boolean)ModuleManager.noPush.water.getValue()).booleanValue()) {
/* 27 */       double d = 0.0D;
/* 28 */       double e = 0.0D;
/* 29 */       class_2338.class_2339 mutable = new class_2338.class_2339();
/* 30 */       class_243 vec3d = new class_243(d, 0.0D, e);
/* 31 */       if (((Boolean)state.method_11654((class_2769)class_3609.field_15902)).booleanValue()) {
/* 32 */         for (class_2350 direction2 : class_2350.class_2353.field_11062) {
/* 33 */           mutable.method_25505((class_2382)pos, direction2);
/* 34 */           if (!method_15749(world, (class_2338)mutable, direction2) && !method_15749(world, mutable.method_10084(), direction2))
/*    */             continue; 
/* 36 */           vec3d = vec3d.method_1029().method_1031(0.0D, -6.0D, 0.0D);
/*    */         } 
/*    */       }
/*    */       
/* 40 */       cir.setReturnValue(vec3d.method_1029());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinFlowableFluid.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */