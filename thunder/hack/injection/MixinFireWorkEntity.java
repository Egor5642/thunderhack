/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_1671;
/*    */ import net.minecraft.class_243;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.combat.Aura;
/*    */ 
/*    */ 
/*    */ @Mixin({class_1671.class})
/*    */ public class MixinFireWorkEntity
/*    */ {
/*    */   @Shadow
/*    */   private class_1309 field_7616;
/*    */   
/*    */   @Redirect(method = {"tick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"))
/*    */   private class_243 tickHook(class_1309 instance) {
/* 24 */     if (ModuleManager.aura.isEnabled() && ModuleManager.aura.rotationMode.not(Aura.Mode.None)) if (Aura.target != null && this.field_7616 == IManager.mc.field_1724 && ((Boolean)ModuleManager.aura.elytraTarget
/* 25 */         .getValue()).booleanValue())
/*    */       {
/*    */         
/* 28 */         return Managers.PLAYER.getRotationVector(ModuleManager.aura.rotationPitch, ModuleManager.aura.rotationYaw);
/*    */       } 
/* 30 */     return this.field_7616.method_5720();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinFireWorkEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */