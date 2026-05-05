/*     */ package thunder.hack.utility.render.shaders.satin.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_283;
/*     */ import net.minecraft.class_284;
/*     */ import net.minecraft.class_5944;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Vector2f;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector4f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform3f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform3i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4i;
/*     */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.UniformMat4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ManagedUniform
/*     */   extends ManagedUniformBase
/*     */   implements Uniform1i, Uniform2i, Uniform3i, Uniform4i, Uniform1f, Uniform2f, Uniform3f, Uniform4f, UniformMat4
/*     */ {
/*  45 */   private static final class_284[] NO_TARGETS = new class_284[0];
/*     */   
/*     */   private final int count;
/*     */   
/*  49 */   private class_284[] targets = NO_TARGETS; private int i0;
/*     */   private int i1;
/*     */   private int i2;
/*     */   private int i3;
/*     */   
/*     */   public ManagedUniform(String name, int count) {
/*  55 */     super(name);
/*  56 */     this.count = count;
/*     */   }
/*     */   private float f0; private float f1; private float f2; private float f3; private boolean firstUpload = true;
/*     */   
/*     */   public boolean findUniformTargets(List<class_283> shaders) {
/*  61 */     List<class_284> list = new ArrayList<>();
/*  62 */     for (class_283 shader : shaders) {
/*  63 */       class_284 uniform = shader.method_1295().method_1271(this.name);
/*     */       
/*  65 */       if (uniform != null) {
/*  66 */         if (uniform.method_35661() != this.count) {
/*  67 */           throw new IllegalStateException("Mismatched number of values, expected " + this.count + " but JSON definition declares " + uniform.method_35661());
/*     */         }
/*  69 */         list.add(uniform);
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     if (list.size() > 0) {
/*  74 */       this.targets = list.<class_284>toArray(new class_284[0]);
/*  75 */       syncCurrentValues();
/*  76 */       return true;
/*     */     } 
/*  78 */     this.targets = NO_TARGETS;
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean findUniformTarget(class_5944 shader) {
/*  85 */     class_284 uniform = shader.method_34582(this.name);
/*  86 */     if (uniform != null) {
/*  87 */       this.targets = new class_284[] { uniform };
/*  88 */       syncCurrentValues();
/*  89 */       return true;
/*     */     } 
/*  91 */     this.targets = NO_TARGETS;
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void syncCurrentValues() {
/*  97 */     if (!this.firstUpload) {
/*  98 */       for (class_284 target : this.targets) {
/*  99 */         if (target.method_35663() != null) {
/* 100 */           target.method_1248(this.i0, this.i1, this.i2, this.i3);
/*     */         } else {
/* 102 */           assert target.method_35664() != null;
/* 103 */           target.method_1252(this.f0, this.f1, this.f2, this.f3);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(int value) {
/* 111 */     class_284[] targets = this.targets;
/* 112 */     int nbTargets = targets.length;
/* 113 */     if (nbTargets > 0 && (
/* 114 */       this.firstUpload || this.i0 != value)) {
/* 115 */       for (class_284 target : targets) {
/* 116 */         target.method_35649(value);
/*     */       }
/* 118 */       this.i0 = value;
/* 119 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int value0, int value1) {
/* 126 */     class_284[] targets = this.targets;
/* 127 */     int nbTargets = targets.length;
/* 128 */     if (nbTargets > 0 && (
/* 129 */       this.firstUpload || this.i0 != value0 || this.i1 != value1)) {
/* 130 */       for (class_284 target : targets) {
/* 131 */         target.method_35650(value0, value1);
/*     */       }
/* 133 */       this.i0 = value0;
/* 134 */       this.i1 = value1;
/* 135 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int value0, int value1, int value2) {
/* 142 */     class_284[] targets = this.targets;
/* 143 */     int nbTargets = targets.length;
/* 144 */     if (nbTargets > 0 && (
/* 145 */       this.firstUpload || this.i0 != value0 || this.i1 != value1 || this.i2 != value2)) {
/* 146 */       for (class_284 target : targets) {
/* 147 */         target.method_35651(value0, value1, value2);
/*     */       }
/* 149 */       this.i0 = value0;
/* 150 */       this.i1 = value1;
/* 151 */       this.i2 = value2;
/* 152 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int value0, int value1, int value2, int value3) {
/* 159 */     class_284[] targets = this.targets;
/* 160 */     int nbTargets = targets.length;
/* 161 */     if (nbTargets > 0 && (
/* 162 */       this.firstUpload || this.i0 != value0 || this.i1 != value1 || this.i2 != value2 || this.i3 != value3)) {
/* 163 */       for (class_284 target : targets) {
/* 164 */         target.method_35656(value0, value1, value2, value3);
/*     */       }
/* 166 */       this.i0 = value0;
/* 167 */       this.i1 = value1;
/* 168 */       this.i2 = value2;
/* 169 */       this.i3 = value3;
/* 170 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(float value) {
/* 177 */     class_284[] targets = this.targets;
/* 178 */     int nbTargets = targets.length;
/* 179 */     if (nbTargets > 0 && (
/* 180 */       this.firstUpload || this.f0 != value)) {
/* 181 */       for (class_284 target : targets) {
/* 182 */         target.method_1251(value);
/*     */       }
/* 184 */       this.f0 = value;
/* 185 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(float value0, float value1) {
/* 192 */     class_284[] targets = this.targets;
/* 193 */     int nbTargets = targets.length;
/* 194 */     if (nbTargets > 0 && (
/* 195 */       this.firstUpload || this.f0 != value0 || this.f1 != value1)) {
/* 196 */       for (class_284 target : targets) {
/* 197 */         target.method_1255(value0, value1);
/*     */       }
/* 199 */       this.f0 = value0;
/* 200 */       this.f1 = value1;
/* 201 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Vector2f value) {
/* 208 */     set(value.x(), value.y());
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float value0, float value1, float value2) {
/* 213 */     class_284[] targets = this.targets;
/* 214 */     int nbTargets = targets.length;
/* 215 */     if (nbTargets > 0 && (
/* 216 */       this.firstUpload || this.f0 != value0 || this.f1 != value1 || this.f2 != value2)) {
/* 217 */       for (class_284 target : targets) {
/* 218 */         target.method_1249(value0, value1, value2);
/*     */       }
/* 220 */       this.f0 = value0;
/* 221 */       this.f1 = value1;
/* 222 */       this.f2 = value2;
/* 223 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Vector3f value) {
/* 230 */     set(value.x(), value.y(), value.z());
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float value0, float value1, float value2, float value3) {
/* 235 */     class_284[] targets = this.targets;
/* 236 */     int nbTargets = targets.length;
/* 237 */     if (nbTargets > 0 && (
/* 238 */       this.firstUpload || this.f0 != value0 || this.f1 != value1 || this.f2 != value2 || this.f3 != value3)) {
/* 239 */       for (class_284 target : targets) {
/* 240 */         target.method_35657(value0, value1, value2, value3);
/*     */       }
/* 242 */       this.f0 = value0;
/* 243 */       this.f1 = value1;
/* 244 */       this.f2 = value2;
/* 245 */       this.f3 = value3;
/* 246 */       this.firstUpload = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Vector4f value) {
/* 253 */     set(value.x(), value.y(), value.z(), value.w());
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Matrix4f value) {
/* 258 */     class_284[] targets = this.targets;
/* 259 */     int nbTargets = targets.length;
/* 260 */     if (nbTargets > 0) {
/* 261 */       for (class_284 target : targets) {
/* 262 */         target.method_1250(value);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromArray(float[] values) {
/* 269 */     if (this.count != values.length) {
/* 270 */       throw new IllegalArgumentException("Mismatched values size, expected " + this.count + " but got " + values.length);
/*     */     }
/*     */     
/* 273 */     class_284[] targets = this.targets;
/* 274 */     int nbTargets = targets.length;
/* 275 */     if (nbTargets > 0)
/* 276 */       for (class_284 target : targets)
/* 277 */         target.method_1253(values);  
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ManagedUniform.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */