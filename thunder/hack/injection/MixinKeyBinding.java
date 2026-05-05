/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_304;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ 
/*    */ @Mixin({class_304.class})
/*    */ public abstract class MixinKeyBinding
/*    */ {
/*    */   @Shadow
/*    */   public abstract boolean method_1435(class_304 paramclass_304);
/*    */   
/*    */   @Inject(method = {"isPressed"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void pressHook(CallbackInfoReturnable<Boolean> cir) {
/* 22 */     if (method_1435(ThunderHack.mc.field_1690.field_1832) && ThunderHack.mc.field_1724 != null && ThunderHack.mc.field_1687 != null && ModuleManager.safeWalk
/*    */ 
/*    */       
/* 25 */       .isEnabled() && ThunderHack.mc.field_1724
/* 26 */       .method_24828() && ThunderHack.mc.field_1687.method_8320(new class_2338((int)Math.floor(ThunderHack.mc.field_1724.method_19538().method_10216()), (int)Math.floor(ThunderHack.mc.field_1724.method_19538().method_10214()) - 1, (int)Math.floor(ThunderHack.mc.field_1724.method_19538().method_10215()))).method_26215() && 
/* 27 */       !ModuleManager.scaffold.isEnabled())
/* 28 */       cir.setReturnValue(Boolean.valueOf(true)); 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   public abstract boolean method_1434();
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinKeyBinding.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */