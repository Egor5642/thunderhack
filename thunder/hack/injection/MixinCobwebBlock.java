/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2560;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_2846;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.movement.AntiWeb;
/*    */ import thunder.hack.utility.player.InteractionUtility;
/*    */ 
/*    */ @Mixin({class_2560.class})
/*    */ public class MixinCobwebBlock {
/*    */   @Inject(method = {"onEntityCollision"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onEntityCollisionHook(class_2680 state, class_1937 world, class_2338 pos, class_1297 entity, CallbackInfo ci) {
/* 24 */     if (ModuleManager.antiWeb.isEnabled() && AntiWeb.mode.getValue() == AntiWeb.Mode.Ignore && entity == IManager.mc.field_1724) {
/* 25 */       ci.cancel();
/* 26 */       if (((Boolean)AntiWeb.grim.getValue()).booleanValue())
/* 27 */         InteractionUtility.sendSequencedPacket(id -> new class_2846(class_2846.class_2847.field_12973, pos, class_2350.field_11036, id)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinCobwebBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */