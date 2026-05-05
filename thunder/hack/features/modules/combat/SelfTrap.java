/*    */ package thunder.hack.features.modules.combat;
/*    */ import net.minecraft.class_1657;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.base.TrapModule;
/*    */ 
/*    */ public final class SelfTrap extends TrapModule {
/*    */   public SelfTrap() {
/*  9 */     super("SelfTrap", Module.Category.COMBAT);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean needNewTarget() {
/* 14 */     return (this.target == null);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected class_1657 getTarget() {
/* 19 */     return (class_1657)mc.field_1724;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\SelfTrap.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */