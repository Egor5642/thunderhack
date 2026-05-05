/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_337;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_337.class})
/*    */ public class MixinBossBarHud {
/*    */   @Inject(method = {"render"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void render(CallbackInfo ci) {
/* 14 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.bossbar.getValue()).booleanValue())
/* 15 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBossBarHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */