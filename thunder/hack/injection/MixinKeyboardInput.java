/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_743;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.events.impl.EventKeyboardInput;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_743.class})
/*    */ public class MixinKeyboardInput {
/*    */   @Inject(method = {"tick"}, at = {@At(value = "FIELD", target = "Lnet/minecraft/client/input/KeyboardInput;sneaking:Z", shift = At.Shift.BEFORE)}, cancellable = true)
/*    */   private void onSneak(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
/* 16 */     if (Module.fullNullCheck())
/* 17 */       return;  EventKeyboardInput event = new EventKeyboardInput();
/* 18 */     ThunderHack.EVENT_BUS.post(event);
/* 19 */     if (event.isCancelled()) ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinKeyboardInput.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */