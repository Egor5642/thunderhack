/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_266;
/*    */ import net.minecraft.class_329;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_9779;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.hud.impl.Hotbar;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_329.class})
/*    */ public abstract class MixinInGameHud
/*    */ {
/*    */   @Inject(at = {@At("HEAD")}, method = {"render"})
/*    */   public void renderHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
/* 25 */     if (Module.fullNullCheck())
/* 26 */       return;  Managers.MODULE.onRender2D(context);
/* 27 */     Managers.NOTIFICATION.onRender2D(context);
/*    */   }
/*    */   
/*    */   @Inject(at = {@At("HEAD")}, method = {"renderStatusBars"}, cancellable = true)
/*    */   private void renderStatusBarsHook(class_332 context, CallbackInfo ci) {
/* 32 */     if (IManager.mc != null && IManager.mc.field_1755 instanceof thunder.hack.gui.windows.WindowsScreen) {
/* 33 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(at = {@At("HEAD")}, method = {"renderHotbar"}, cancellable = true)
/*    */   public void renderHotbarCustom(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
/* 39 */     if (IManager.mc != null && IManager.mc.field_1755 instanceof thunder.hack.gui.windows.WindowsScreen) {
/* 40 */       ci.cancel();
/*    */     }
/* 42 */     if (ModuleManager.hotbar.isEnabled()) {
/* 43 */       ci.cancel();
/* 44 */       Hotbar.renderHotBarItems(tickCounter.method_60637(true), context);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(at = {@At("HEAD")}, method = {"renderHeldItemTooltip"}, cancellable = true)
/*    */   public void renderHeldItemTooltipHook(class_332 context, CallbackInfo ci) {
/* 51 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.hotbarItemName.getValue()).booleanValue())
/* 52 */       ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(at = {@At("HEAD")}, method = {"renderStatusEffectOverlay"}, cancellable = true)
/*    */   public void renderStatusEffectOverlayHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
/* 57 */     if (ModuleManager.potionHud.isEnabled() || (ModuleManager.legacyHud.isEnabled() && ((Boolean)ModuleManager.legacyHud.potions.getValue()).booleanValue())) {
/* 58 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderExperienceBar"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void renderXpBarCustom(class_332 context, int x, CallbackInfo ci) {
/* 64 */     if (IManager.mc != null && IManager.mc.field_1755 instanceof thunder.hack.gui.windows.WindowsScreen) {
/* 65 */       ci.cancel();
/*    */     }
/* 67 */     if (ModuleManager.hotbar.isEnabled()) {
/* 68 */       ci.cancel();
/* 69 */       Hotbar.renderXpBar(x, context.method_51448());
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderScoreboardSidebarHook(class_332 context, class_266 objective, CallbackInfo ci) {
/* 75 */     if (((Boolean)ModuleManager.noRender.noScoreBoard.getValue()).booleanValue() && ModuleManager.noRender.isEnabled()) {
/* 76 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderVignetteOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderVignetteOverlayHook(class_332 context, class_1297 entity, CallbackInfo ci) {
/* 82 */     if (((Boolean)ModuleManager.noRender.vignette.getValue()).booleanValue())
/* 83 */       ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderPortalOverlay"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void renderPortalOverlayHook(class_332 context, float nauseaStrength, CallbackInfo ci) {
/* 88 */     if (((Boolean)ModuleManager.noRender.portal.getValue()).booleanValue())
/* 89 */       ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderCrosshair"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void renderCrosshair(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
/* 94 */     if (ModuleManager.crosshair.isEnabled())
/* 95 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinInGameHud.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */