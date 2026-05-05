/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1690;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_1690.class})
/*    */ public class MixinBoatEntity
/*    */ {
/*    */   @Unique
/*    */   private float prevYaw;
/*    */   
/*    */   @Inject(method = {"updatePassengerPosition"}, at = {@At("HEAD")})
/*    */   protected void updatePassengerPositionHookPre(class_1297 passenger, class_1297.class_4738 positionUpdater, CallbackInfo ci) {
/* 20 */     if (ModuleManager.boatFly.isEnabled()) {
/* 21 */       this.prevYaw = passenger.method_36454();
/* 22 */       this.prevHeadYaw = passenger.method_5791();
/*    */     } 
/*    */   } @Unique
/*    */   private float prevHeadYaw;
/*    */   @Inject(method = {"updatePassengerPosition"}, at = {@At("RETURN")})
/*    */   protected void updatePassengerPositionHookPost(class_1297 passenger, class_1297.class_4738 positionUpdater, CallbackInfo ci) {
/* 28 */     if (ModuleManager.boatFly.isEnabled()) {
/* 29 */       passenger.method_36456(this.prevYaw);
/* 30 */       passenger.method_5847(this.prevHeadYaw);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinBoatEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */