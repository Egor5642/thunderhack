/*     */ package thunder.hack.utility.render.shaders.satin.impl;
/*     */ 
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_5912;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform3f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform3i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.UniformFinder;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.UniformMat4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ResettableManagedShaderBase<S extends AutoCloseable>
/*     */   implements UniformFinder
/*     */ {
/*     */   private final class_2960 location;
/*  33 */   private final Map<String, ManagedUniform> managedUniforms = new HashMap<>();
/*  34 */   private final List<ManagedUniformBase> allUniforms = new ArrayList<>();
/*     */   private boolean errored;
/*     */   protected S shader;
/*     */   
/*     */   public ResettableManagedShaderBase(class_2960 location) {
/*  39 */     this.location = location;
/*     */   }
/*     */   
/*     */   public void initializeOrLog(class_5912 mgr) {
/*     */     try {
/*  44 */       initialize(mgr);
/*  45 */     } catch (IOException e) {
/*  46 */       this.errored = true;
/*  47 */       logInitError(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(class_5912 resourceManager) throws IOException {
/*  54 */     release();
/*  55 */     class_310 mc = class_310.method_1551();
/*  56 */     this.shader = parseShader(resourceManager, mc, this.location);
/*  57 */     setup(mc.method_22683().method_4489(), mc.method_22683().method_4506());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void release() {
/*  63 */     if (isInitialized()) {
/*     */       try {
/*  65 */         assert this.shader != null;
/*  66 */         this.shader.close();
/*  67 */         this.shader = null;
/*  68 */       } catch (Exception e) {
/*  69 */         throw new RuntimeException("Failed to release shader " + String.valueOf(this.location), e);
/*     */       } 
/*     */     }
/*  72 */     this.errored = false;
/*     */   }
/*     */   
/*     */   protected Collection<ManagedUniformBase> getManagedUniforms() {
/*  76 */     return this.allUniforms;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/*  82 */     return (this.shader != null);
/*     */   }
/*     */   
/*     */   public boolean isErrored() {
/*  86 */     return this.errored;
/*     */   }
/*     */   
/*     */   public class_2960 getLocation() {
/*  90 */     return this.location;
/*     */   }
/*     */   
/*     */   protected <U extends ManagedUniformBase> U manageUniform(Map<String, U> uniformMap, Function<String, U> factory, String uniformName, String uniformKind) {
/*  94 */     ManagedUniformBase managedUniformBase1 = (ManagedUniformBase)uniformMap.get(uniformName);
/*  95 */     if (managedUniformBase1 != null) {
/*  96 */       return (U)managedUniformBase1;
/*     */     }
/*  98 */     ManagedUniformBase managedUniformBase2 = (ManagedUniformBase)factory.apply(uniformName);
/*  99 */     if (this.shader != null) {
/* 100 */       boolean found = setupUniform(managedUniformBase2, this.shader);
/* 101 */       if (!found) {
/* 102 */         LogUtils.getLogger().warn("No {} found with name {} in shader {}", new Object[] { uniformKind, uniformName, this.location });
/*     */       }
/*     */     } 
/* 105 */     uniformMap.put(uniformName, (U)managedUniformBase2);
/* 106 */     this.allUniforms.add(managedUniformBase2);
/* 107 */     return (U)managedUniformBase2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform1i findUniform1i(String uniformName) {
/* 112 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 1), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform2i findUniform2i(String uniformName) {
/* 117 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 2), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform3i findUniform3i(String uniformName) {
/* 122 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 3), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform4i findUniform4i(String uniformName) {
/* 127 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 4), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform1f findUniform1f(String uniformName) {
/* 132 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 1), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform2f findUniform2f(String uniformName) {
/* 137 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 2), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform3f findUniform3f(String uniformName) {
/* 142 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 3), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public Uniform4f findUniform4f(String uniformName) {
/* 147 */     return (Uniform4f)manageUniform(this.managedUniforms, name -> new ManagedUniform(name, 4), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */   
/*     */   public UniformMat4 findUniformMat4(String uniformName) {
/* 152 */     return manageUniform((Map)this.managedUniforms, name -> new ManagedUniform(name, 16), uniformName, "uniform");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     return "%s[%s]".formatted(new Object[] { getClass().getSimpleName(), this.location });
/*     */   }
/*     */   
/*     */   protected abstract void logInitError(IOException paramIOException);
/*     */   
/*     */   protected abstract S parseShader(class_5912 paramclass_5912, class_310 paramclass_310, class_2960 paramclass_2960) throws IOException;
/*     */   
/*     */   protected abstract boolean setupUniform(ManagedUniformBase paramManagedUniformBase, S paramS);
/*     */   
/*     */   public abstract void setup(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ResettableManagedShaderBase.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */