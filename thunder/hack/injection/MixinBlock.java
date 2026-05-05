/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2680;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.XRay;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_2248.class})
/*    */ public abstract class MixinBlock
/*    */ {
/*    */   @Inject(method = {"shouldDrawSide"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void shouldDrawSideHook(class_2680 state, class_1922 world, class_2338 pos, class_2350 side, class_2338 blockPos, CallbackInfoReturnable<Boolean> cir) {
/* 24 */     if (ModuleManager.xray.isEnabled() && ((Boolean)ModuleManager.xray.wallHack.getValue()).booleanValue())
/* 25 */       cir.setReturnValue(Boolean.valueOf(XRay.isCheckableOre(state.method_26204()))); 
/* 26 */     if (ModuleManager.autoAnchor.isEnabled() && state.method_26204() instanceof net.minecraft.class_2358) {
/* 27 */       cir.setReturnValue(Boolean.valueOf(false));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"getVelocityMultiplier"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getVelocityMultiplierHook(CallbackInfoReturnable<Float> cir) {
/* 41 */     if (ModuleManager.noSlow.isEnabled()) {
/* 42 */       if (((Boolean)ModuleManager.noSlow.soulSand.getValue()).booleanValue() && this == class_2246.field_10114)
/* 43 */         cir.setReturnValue(Float.valueOf(class_2246.field_10566.method_23349())); 
/* 44 */       if (((Boolean)ModuleManager.noSlow.honey.getValue()).booleanValue() && this == class_2246.field_21211)
/* 45 */         cir.setReturnValue(Float.valueOf(class_2246.field_10566.method_23349())); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"getSlipperiness"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getSlipperinessHook(CallbackInfoReturnable<Float> cir) {
/* 51 */     if (ModuleManager.noSlow.isEnabled()) {
/* 52 */       if (((Boolean)ModuleManager.noSlow.slime.getValue()).booleanValue() && this == class_2246.field_10030)
/* 53 */         cir.setReturnValue(Float.valueOf(class_2246.field_10566.method_9499())); 
/* 54 */       if (((Boolean)ModuleManager.noSlow.ice.getValue()).booleanValue() && (this == class_2246.field_10295 || this == class_2246.field_10225 || this == class_2246.field_10384 || this == class_2246.field_10110) && !IManager.mc.field_1690.field_1903.method_1434())
/* 55 */         cir.setReturnValue(Float.valueOf(class_2246.field_10566.method_9499())); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */