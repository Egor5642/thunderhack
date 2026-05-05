/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.IntSupplier;
/*    */ import net.minecraft.class_1044;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_280;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.SamplerUniformV2;
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
/*    */ public final class ManagedSamplerUniformV2
/*    */   extends ManagedSamplerUniformBase
/*    */   implements SamplerUniformV2
/*    */ {
/*    */   public ManagedSamplerUniformV2(String name) {
/* 29 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(class_1044 texture) {
/* 34 */     Objects.requireNonNull(texture); set(texture::method_4624);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(class_276 textureFbo) {
/* 39 */     Objects.requireNonNull(textureFbo); set(textureFbo::method_30277);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(int textureName) {
/* 44 */     set(() -> textureName);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void set(Object value) {
/* 49 */     set((IntSupplier)value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(IntSupplier value) {
/* 54 */     SamplerAccess[] targets = this.targets;
/* 55 */     if (targets.length > 0 && this.cachedValue != value) {
/* 56 */       for (SamplerAccess target : targets) {
/* 57 */         ((class_280)target).method_1269(this.name, value);
/*    */       }
/* 59 */       this.cachedValue = value;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ManagedSamplerUniformV2.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */