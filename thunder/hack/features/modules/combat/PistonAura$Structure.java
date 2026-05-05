/*     */ package thunder.hack.features.modules.combat;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_2680;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Structure
/*     */ {
/*     */   private final class_2338 pistonPos;
/*     */   private class_2338 crystalPos;
/*     */   private final class_2338 targetPos;
/*     */   private class_2338 redStonePos;
/*     */   private class_2338 firePos;
/*     */   private final class_1657 target;
/*     */   private class_2338 pistonHeadPos;
/*     */   
/*     */   public class_2338 getPistonHeadPos() {
/* 835 */     return this.pistonHeadPos;
/*     */   }
/*     */   
/*     */   public class_2338 getPistonPos() {
/* 839 */     return this.pistonPos;
/*     */   }
/*     */   
/*     */   public class_2338 getCrystalPos() {
/* 843 */     return this.crystalPos;
/*     */   }
/*     */   
/*     */   public class_2338 getRedStonePos() {
/* 847 */     return this.redStonePos;
/*     */   }
/*     */   
/*     */   public class_2338 getFirePos() {
/* 851 */     return this.firePos;
/*     */   }
/*     */   
/*     */   public class_1657 getTarget() {
/* 855 */     return this.target;
/*     */   }
/*     */   
/*     */   public Structure(@NotNull class_1657 target, @NotNull class_2338 crystalPos, @NotNull class_2338 pistonPos, class_2338 pistonHeadPos, class_2338[] redStonePos, class_2338[] firePos) {
/* 859 */     this.target = target;
/* 860 */     this.targetPos = class_2338.method_49638((class_2374)target.method_19538());
/* 861 */     this.pistonPos = canPlace(this.targetPos.method_10069(pistonPos.method_10263(), pistonPos.method_10264() + 1, pistonPos.method_10260())) ? this.targetPos.method_10069(pistonPos.method_10263(), pistonPos.method_10264() + 1, pistonPos.method_10260()) : null;
/* 862 */     this.crystalPos = (PistonAura.this.getPlaceData(this.targetPos.method_10069(crystalPos.method_10263(), crystalPos.method_10264(), crystalPos.method_10260())) != null) ? this.targetPos.method_10069(crystalPos.method_10263(), crystalPos.method_10264(), crystalPos.method_10260()) : null;
/* 863 */     this.pistonHeadPos = Module.mc.field_1687.method_22347(this.targetPos.method_10069(pistonHeadPos.method_10263(), pistonHeadPos.method_10264() + 1, pistonHeadPos.method_10260())) ? this.targetPos.method_10069(pistonHeadPos.method_10263(), pistonHeadPos.method_10264() + 1, pistonHeadPos.method_10260()) : null;
/*     */     
/* 865 */     if (this.pistonHeadPos != null && !Module.mc.field_1687.method_18467(class_1657.class, new class_238(this.pistonHeadPos)).isEmpty()) {
/* 866 */       this.pistonHeadPos = null;
/*     */     }
/*     */     
/* 869 */     if (this.crystalPos != null && !Module.mc.field_1687.method_18467(class_1297.class, new class_238(this.crystalPos)).isEmpty()) {
/* 870 */       this.crystalPos = null;
/*     */     }
/*     */     
/* 873 */     this.redStonePos = null;
/*     */ 
/*     */     
/* 876 */     List<class_2338> tempRed = Arrays.<class_2338>stream(redStonePos).map(blockPos -> this.targetPos.method_10069(blockPos.method_10263(), blockPos.method_10264() + 1, blockPos.method_10260())).toList();
/* 877 */     class_2680 preState = Module.mc.field_1687.method_8320(pistonPos);
/* 878 */     Module.mc.field_1687.method_8501(pistonPos, class_2246.field_10560.method_9564());
/* 879 */     for (class_2338 pos : tempRed) {
/* 880 */       if (canPlace(pos)) {
/* 881 */         this.redStonePos = pos;
/*     */         break;
/*     */       } 
/*     */     } 
/* 885 */     Module.mc.field_1687.method_8501(pistonPos, preState);
/*     */     
/* 887 */     this.firePos = null;
/*     */ 
/*     */     
/* 890 */     List<class_2338> tempFire = Arrays.<class_2338>stream(firePos).map(blockPos -> this.targetPos.method_10069(blockPos.method_10263(), blockPos.method_10264() + 1, blockPos.method_10260())).toList();
/* 891 */     for (class_2338 pos : tempFire) {
/* 892 */       if (canPlace(pos)) {
/* 893 */         this.firePos = pos;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isNormalPa() {
/* 900 */     return (this.pistonPos != null && this.crystalPos != null && this.targetPos != null && this.redStonePos != null && this.pistonHeadPos != null);
/*     */   }
/*     */   
/*     */   public boolean isFirePa() {
/* 904 */     return (this.pistonPos != null && this.crystalPos != null && this.targetPos != null && this.redStonePos != null && this.pistonHeadPos != null && this.firePos != null);
/*     */   }
/*     */   
/*     */   private boolean canPlace(class_2338 pos) {
/* 908 */     if (pos == null) return false;
/*     */     
/* 910 */     class_2680 prevBlockState = null;
/* 911 */     if (((Boolean)PistonAura.this.supportPlace.getValue()).booleanValue()) {
/* 912 */       prevBlockState = Module.mc.field_1687.method_8320(pos.method_10074());
/* 913 */       if (prevBlockState.method_45474())
/* 914 */       { Module.mc.field_1687.method_8501(pos.method_10074(), class_2246.field_10540.method_9564()); }
/* 915 */       else { prevBlockState = null; }
/*     */     
/*     */     } 
/* 918 */     boolean canPlace = InteractionUtility.canPlaceBlock(pos, (InteractionUtility.Interact)PistonAura.this.interact.getValue(), false);
/*     */     
/* 920 */     if (prevBlockState != null) {
/* 921 */       Module.mc.field_1687.method_8501(pos.method_10074(), prevBlockState);
/*     */     }
/*     */     
/* 924 */     return canPlace;
/*     */   }
/*     */   
/*     */   public double getMaxRange() {
/* 928 */     if (this.pistonPos == null || this.crystalPos == null || this.redStonePos == null) return 999.0D; 
/* 929 */     double piston = InteractionUtility.squaredDistanceFromEyes(this.pistonPos.method_46558());
/* 930 */     double crystal = InteractionUtility.squaredDistanceFromEyes(this.crystalPos.method_46558());
/* 931 */     double redStone = InteractionUtility.squaredDistanceFromEyes(this.redStonePos.method_46558());
/*     */     
/* 933 */     class_2338 firePos = (this.firePos != null) ? this.firePos : this.pistonPos;
/* 934 */     double fire = InteractionUtility.squaredDistanceFromEyes(firePos.method_46558());
/* 935 */     return Math.max(Math.max(fire, crystal), Math.max(redStone, piston));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\PistonAura$Structure.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */