/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_312;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.EventMouse;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_312.class})
/*    */ public class MixinMouse
/*    */ {
/*    */   @Inject(method = {"onMouseButton"}, at = {@At("HEAD")})
/*    */   public void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo ci) {
/* 18 */     if (window == Module.mc.method_22683().method_4490()) {
/* 19 */       if (action == 0) Managers.MODULE.onMoseKeyReleased(button); 
/* 20 */       if (action == 1) Managers.MODULE.onMoseKeyPressed(button);
/*    */       
/* 22 */       ThunderHack.EVENT_BUS.post(new EventMouse(button, action));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"onMouseScroll"}, at = {@At("HEAD")})
/*    */   private void onMouseScrollHook(long window, double horizontal, double vertical, CallbackInfo ci) {
/* 28 */     if (window == Module.mc.method_22683().method_4490())
/* 29 */       ThunderHack.EVENT_BUS.post(new EventMouse((int)vertical, 2)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinMouse.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */