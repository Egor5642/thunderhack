/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1268;
/*    */ import net.minecraft.class_1271;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1835;
/*    */ import net.minecraft.class_1890;
/*    */ import net.minecraft.class_1937;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.UseTridentEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_1835.class})
/*    */ public abstract class MixinTridentItem
/*    */ {
/*    */   @Inject(method = {"onStoppedUsing"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onStoppedUsingHook(class_1799 stack, class_1937 world, class_1309 user, int remainingUseTicks, CallbackInfo ci) {
/* 27 */     if (user == ThunderHack.mc.field_1724 && class_1890.method_60123(stack, (class_1309)ThunderHack.mc.field_1724) > 0.0F) {
/* 28 */       UseTridentEvent e = new UseTridentEvent();
/* 29 */       ThunderHack.EVENT_BUS.post(e);
/* 30 */       if (e.isCancelled())
/* 31 */         ci.cancel(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"use"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void useHook(class_1937 world, class_1657 user, class_1268 hand, CallbackInfoReturnable<class_1271<class_1799>> cir) {
/* 37 */     class_1799 itemStack = user.method_5998(hand);
/* 38 */     if (class_1890.method_60123(itemStack, (class_1309)user) > 0.0F && !user.method_5721() && ModuleManager.tridentBoost.isEnabled() && ((Boolean)ModuleManager.tridentBoost.anyWeather.getValue()).booleanValue()) {
/* 39 */       user.method_6019(hand);
/* 40 */       cir.setReturnValue(class_1271.method_22428(itemStack));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinTridentItem.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */