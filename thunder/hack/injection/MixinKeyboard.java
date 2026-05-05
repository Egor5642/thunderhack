/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_309;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.EventKeyPress;
/*    */ import thunder.hack.events.impl.EventKeyRelease;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_309.class})
/*    */ public class MixinKeyboard
/*    */ {
/*    */   @Inject(method = {"onKey"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
/*    */     EventKeyRelease eventKeyRelease;
/*    */     EventKeyPress event;
/* 23 */     if (Module.fullNullCheck())
/* 24 */       return;  boolean whitelist = (Module.mc.field_1755 == null || Module.mc.field_1755 instanceof thunder.hack.gui.clickui.ClickGUI || Module.mc.field_1755 instanceof thunder.hack.gui.hud.HudEditorGui);
/* 25 */     if (!whitelist)
/*    */       return; 
/* 27 */     if (action == 0) Managers.MODULE.onKeyReleased(key); 
/* 28 */     if (action == 1) Managers.MODULE.onKeyPressed(key); 
/* 29 */     if (action == 2) action = 1;
/*    */     
/* 31 */     switch (action) {
/*    */       case 0:
/* 33 */         eventKeyRelease = new EventKeyRelease(key, scanCode);
/* 34 */         ThunderHack.EVENT_BUS.post(eventKeyRelease);
/* 35 */         if (eventKeyRelease.isCancelled()) ci.cancel(); 
/*    */         break;
/*    */       case 1:
/* 38 */         event = new EventKeyPress(key, scanCode);
/* 39 */         ThunderHack.EVENT_BUS.post(event);
/* 40 */         if (event.isCancelled()) ci.cancel(); 
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinKeyboard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */