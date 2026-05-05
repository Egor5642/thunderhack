/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_1511;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.utility.interfaces.ICrystal;
/*    */ 
/*    */ @Mixin({class_1511.class})
/*    */ public class MixinEndCrystal implements ICrystal {
/*    */   @Unique
/*    */   int attacks;
/*    */   @Unique
/*    */   int cooldown;
/*    */   
/*    */   public boolean canAttack() {
/* 19 */     return (this.cooldown == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void attack() {
/* 24 */     if (this.attacks++ >= 5)
/* 25 */       this.cooldown = 20; 
/*    */   }
/*    */   
/*    */   @Inject(method = {"tick"}, at = {@At("HEAD")})
/*    */   public void tickHook(CallbackInfo ci) {
/* 30 */     if (this.cooldown > 0)
/* 31 */       this.cooldown--; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinEndCrystal.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */