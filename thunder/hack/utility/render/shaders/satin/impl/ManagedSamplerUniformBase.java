/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import com.mojang.logging.LogUtils;
/*    */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_280;
/*    */ import net.minecraft.class_283;
/*    */ import net.minecraft.class_5944;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.SamplerUniform;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ManagedSamplerUniformBase
/*    */   extends ManagedUniformBase
/*    */   implements SamplerUniform
/*    */ {
/* 35 */   protected SamplerAccess[] targets = new SamplerAccess[0];
/* 36 */   protected int[] locations = new int[0];
/*    */   protected Object cachedValue;
/*    */   
/*    */   public ManagedSamplerUniformBase(String name) {
/* 40 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean findUniformTargets(List<class_283> shaders) {
/* 45 */     List<SamplerAccess> targets = new ArrayList<>(shaders.size());
/* 46 */     IntArrayList intArrayList = new IntArrayList(shaders.size());
/* 47 */     for (class_283 shader : shaders) {
/* 48 */       class_280 program = shader.method_1295();
/* 49 */       SamplerAccess access = (SamplerAccess)program;
/* 50 */       if (access.hasSampler(this.name)) {
/* 51 */         targets.add(access);
/* 52 */         intArrayList.add(getSamplerLoc(access));
/*    */       } 
/*    */     } 
/* 55 */     this.targets = targets.<SamplerAccess>toArray(new SamplerAccess[0]);
/* 56 */     this.locations = intArrayList.toArray(new int[0]);
/* 57 */     syncCurrentValues();
/* 58 */     return (this.targets.length > 0);
/*    */   }
/*    */   
/*    */   private int getSamplerLoc(SamplerAccess access) {
/* 62 */     return ((Integer)access.getSamplerShaderLocs().get(access.getSamplerNames().indexOf(this.name))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean findUniformTarget(class_5944 shader) {
/* 67 */     LogUtils.getLogger().warn(shader.method_35787());
/* 68 */     return findUniformTarget1((SamplerAccess)shader);
/*    */   }
/*    */   
/*    */   private boolean findUniformTarget1(SamplerAccess access) {
/* 72 */     if (access.hasSampler(this.name)) {
/* 73 */       this.targets = new SamplerAccess[] { access };
/* 74 */       this.locations = new int[] { getSamplerLoc(access) };
/* 75 */       syncCurrentValues();
/* 76 */       return true;
/*    */     } 
/* 78 */     return false;
/*    */   }
/*    */   
/*    */   private void syncCurrentValues() {
/* 82 */     Object value = this.cachedValue;
/* 83 */     if (value != null) {
/* 84 */       this.cachedValue = null;
/* 85 */       set(value);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void set(Object paramObject);
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ManagedSamplerUniformBase.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */