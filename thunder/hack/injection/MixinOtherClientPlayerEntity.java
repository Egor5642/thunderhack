/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_638;
/*    */ import net.minecraft.class_742;
/*    */ import net.minecraft.class_745;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.combat.Aura;
/*    */ import thunder.hack.features.modules.misc.FakePlayer;
/*    */ import thunder.hack.utility.interfaces.IEntityLiving;
/*    */ import thunder.hack.utility.interfaces.IOtherClientPlayerEntity;
/*    */ 
/*    */ @Mixin({class_745.class})
/*    */ public class MixinOtherClientPlayerEntity extends class_742 implements IOtherClientPlayerEntity {
/*    */   @Unique
/*    */   private double backUpX;
/*    */   
/*    */   public MixinOtherClientPlayerEntity(class_638 world, GameProfile profile) {
/* 23 */     super(world, profile);
/*    */   } @Unique
/*    */   private double backUpY; @Unique
/*    */   private double backUpZ; public void resolve(Aura.Resolver mode) {
/* 27 */     if (this == FakePlayer.fakePlayer) {
/* 28 */       this.backUpY = -999.0D;
/*    */       
/*    */       return;
/*    */     } 
/* 32 */     this.backUpX = method_23317();
/* 33 */     this.backUpY = method_23318();
/* 34 */     this.backUpZ = method_23321();
/*    */     
/* 36 */     if (mode == Aura.Resolver.BackTrack) {
/* 37 */       double minDst = 999.0D;
/* 38 */       Aura.Position bestPos = null;
/* 39 */       for (Aura.Position p : ((IEntityLiving)this).getPositionHistory()) {
/* 40 */         double dst = Module.mc.field_1724.method_5649(p.getX(), p.getY(), p.getZ());
/* 41 */         if (dst < minDst) {
/* 42 */           minDst = dst;
/* 43 */           bestPos = p;
/*    */         } 
/*    */       } 
/* 46 */       if (bestPos != null) {
/* 47 */         method_5814(bestPos.getX(), bestPos.getY(), bestPos.getZ());
/* 48 */         if (Aura.target == this) {
/* 49 */           ModuleManager.aura.resolvedBox = method_5829();
/*    */         }
/*    */       } 
/*    */       return;
/*    */     } 
/* 54 */     class_243 from = new class_243(((IEntityLiving)this).getPrevServerX(), ((IEntityLiving)this).getPrevServerY(), ((IEntityLiving)this).getPrevServerZ());
/* 55 */     class_243 to = new class_243(this.field_6224, this.field_6245, this.field_6263);
/*    */     
/* 57 */     if (mode == Aura.Resolver.Advantage)
/* 58 */     { if (Module.mc.field_1724.method_5707(from) > Module.mc.field_1724.method_5707(to)) { method_5814(to.field_1352, to.field_1351, to.field_1350); }
/* 59 */       else { method_5814(from.field_1352, from.field_1351, from.field_1350); }
/*    */        }
/* 61 */     else { method_5814(to.field_1352, to.field_1351, to.field_1350); }
/*    */     
/* 63 */     if (Aura.target == this)
/* 64 */       ModuleManager.aura.resolvedBox = method_5829(); 
/*    */   }
/*    */   
/*    */   public void releaseResolver() {
/* 68 */     if (this.backUpY != -999.0D) {
/* 69 */       method_5814(this.backUpX, this.backUpY, this.backUpZ);
/* 70 */       this.backUpY = -999.0D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinOtherClientPlayerEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */