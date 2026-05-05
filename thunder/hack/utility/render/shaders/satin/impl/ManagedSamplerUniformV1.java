/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import net.minecraft.class_1044;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_5944;
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
/*    */ public final class ManagedSamplerUniformV1
/*    */   extends ManagedSamplerUniformBase
/*    */ {
/*    */   public ManagedSamplerUniformV1(String name) {
/* 26 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(class_1044 texture) {
/* 31 */     set(texture);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(class_276 textureFbo) {
/* 36 */     set(textureFbo);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(int textureName) {
/* 41 */     set(Integer.valueOf(textureName));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void set(Object value) {
/* 46 */     SamplerAccess[] targets = this.targets;
/* 47 */     if (targets.length > 0 && this.cachedValue != value) {
/* 48 */       for (SamplerAccess target : targets) {
/* 49 */         ((class_5944)target).method_34583(this.name, value);
/*    */       }
/* 51 */       this.cachedValue = value;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ManagedSamplerUniformV1.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */