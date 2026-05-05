/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_3414;
/*    */ import net.minecraft.class_3419;
/*    */ import net.minecraft.class_638;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventEntityRemoved;
/*    */ import thunder.hack.events.impl.EventEntitySpawn;
/*    */ import thunder.hack.events.impl.EventEntitySpawnPost;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.render.WorldTweaks;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ 
/*    */ @Mixin({class_638.class})
/*    */ public class MixinClientWorld
/*    */ {
/*    */   @Inject(method = {"addEntity"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void addEntityHook(class_1297 entity, CallbackInfo ci) {
/* 28 */     if (Module.fullNullCheck())
/* 29 */       return;  EventEntitySpawn ees = new EventEntitySpawn(entity);
/* 30 */     ThunderHack.EVENT_BUS.post(ees);
/* 31 */     if (ees.isCancelled()) {
/* 32 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"addEntity"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void addEntityHookPost(class_1297 entity, CallbackInfo ci) {
/* 38 */     if (Module.fullNullCheck())
/* 39 */       return;  EventEntitySpawnPost ees = new EventEntitySpawnPost(entity);
/* 40 */     ThunderHack.EVENT_BUS.post(ees);
/* 41 */     if (ees.isCancelled()) {
/* 42 */       ci.cancel();
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"removeEntity"}, at = {@At("HEAD")})
/*    */   public void removeEntityHook(int entityId, class_1297.class_5529 removalReason, CallbackInfo ci) {
/* 48 */     if (Module.fullNullCheck())
/* 49 */       return;  EventEntityRemoved eer = new EventEntityRemoved(Module.mc.field_1687.method_8469(entityId));
/* 50 */     ThunderHack.EVENT_BUS.post(eer);
/*    */   }
/*    */   
/*    */   @Inject(method = {"getSkyColor"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void getSkyColorHook(class_243 cameraPos, float tickDelta, CallbackInfoReturnable<class_243> cir) {
/* 55 */     if (ModuleManager.worldTweaks.isEnabled() && ((BooleanSettingGroup)WorldTweaks.fogModify.getValue()).isEnabled()) {
/* 56 */       ColorSetting c = (ColorSetting)WorldTweaks.fogColor.getValue();
/* 57 */       cir.setReturnValue(new class_243(c.getGlRed(), c.getGlGreen(), c.getGlBlue()));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Inject(method = {"playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V"}, at = {@At("HEAD")})
/*    */   private void playSoundHoof(double x, double y, double z, class_3414 event, class_3419 category, float volume, float pitch, boolean useDistance, long seed, CallbackInfo ci) {
/* 63 */     if (ModuleManager.soundESP.isEnabled())
/* 64 */       ModuleManager.soundESP.add(x, y, z, event.method_14833().method_42094()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientWorld.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */