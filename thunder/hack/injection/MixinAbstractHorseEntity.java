/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_1429;
/*    */ import net.minecraft.class_1496;
/*    */ import net.minecraft.class_1937;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ 
/*    */ @Mixin({class_1496.class})
/*    */ public abstract class MixinAbstractHorseEntity extends class_1429 {
/*    */   protected MixinAbstractHorseEntity(class_1299<? extends class_1429> entityType, class_1937 world) {
/* 16 */     super(entityType, world);
/*    */   }
/*    */   
/*    */   @Inject(method = {"isSaddled"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onIsSaddled(CallbackInfoReturnable<Boolean> cir) {
/* 21 */     if (ModuleManager.entityControl.isEnabled())
/* 22 */       cir.setReturnValue(Boolean.valueOf(true)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinAbstractHorseEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */