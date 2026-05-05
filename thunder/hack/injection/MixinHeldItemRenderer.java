/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1306;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_3532;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_4597;
/*    */ import net.minecraft.class_742;
/*    */ import net.minecraft.class_759;
/*    */ import net.minecraft.class_7833;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArgs;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventHeldItemRenderer;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_759.class})
/*    */ public abstract class MixinHeldItemRenderer
/*    */ {
/*    */   @Inject(method = {"renderFirstPersonItem"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
/*    */   private void onRenderItem(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
/* 35 */     if (Module.fullNullCheck())
/* 36 */       return;  EventHeldItemRenderer event = new EventHeldItemRenderer(hand, item, equipProgress, matrices);
/* 37 */     ThunderHack.EVENT_BUS.post(event);
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderFirstPersonItem"}, at = {@At("RETURN")})
/*    */   private void onRenderItemPost(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
/* 42 */     if (ModuleManager.chams.isEnabled() && ((Boolean)ModuleManager.chams.handItems.getValue()).booleanValue())
/* 43 */       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderFirstPersonItem"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onRenderItemHook(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
/* 48 */     if (Managers.MODULE != null && ModuleManager.animations.shouldAnimate() && !item.method_7960() && !(item.method_7909() instanceof net.minecraft.class_1806)) {
/* 49 */       ci.cancel();
/* 50 */       ModuleManager.animations.renderFirstPersonItemCustom(player, tickDelta, pitch, hand, swingProgress, item, equipProgress, matrices, vertexConsumers, light);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void applyEatOrDrinkTransformationCustom(class_4587 matrices, float tickDelta, class_1306 arm, @NotNull class_1799 stack) {
/* 56 */     float f = Module.mc.field_1724.method_6014() - tickDelta + 1.0F;
/* 57 */     float g = f / stack.method_7935((class_1309)Module.mc.field_1724);
/*    */     
/* 59 */     if (g < 0.8F) {
/* 60 */       float f1 = class_3532.method_15379(class_3532.method_15362(f / 4.0F * 3.1415927F) * 0.005F);
/* 61 */       matrices.method_46416(0.0F, f1, 0.0F);
/*    */     } 
/* 63 */     float h = 1.0F - (float)Math.pow(g, 27.0D);
/* 64 */     int i = (arm == class_1306.field_6183) ? 1 : -1;
/*    */     
/* 66 */     matrices.method_46416(h * 0.6F * i * ((Float)ModuleManager.viewModel.eatX.getValue()).floatValue(), h * -0.5F * ((Float)ModuleManager.viewModel.eatY.getValue()).floatValue(), h * 0.0F);
/* 67 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(i * h * 90.0F));
/* 68 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(h * 10.0F));
/* 69 */     matrices.method_22907(class_7833.field_40718.rotationDegrees(i * h * 30.0F));
/*    */   }
/*    */   
/*    */   @Inject(method = {"applyEatOrDrinkTransformation"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void applyEatOrDrinkTransformationHook(class_4587 matrices, float tickDelta, class_1306 arm, class_1799 stack, class_1657 player, CallbackInfo ci) {
/* 74 */     if (ModuleManager.animations.isEnabled()) {
/* 75 */       applyEatOrDrinkTransformationCustom(matrices, tickDelta, arm, stack);
/* 76 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */   @ModifyArgs(method = {"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
/*    */   private void renderItem(Args args) {
/* 81 */     if (((Boolean)ModuleManager.noRender.noSwing.getValue()).booleanValue())
/* 82 */       args.set(6, Float.valueOf(0.0F)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinHeldItemRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */