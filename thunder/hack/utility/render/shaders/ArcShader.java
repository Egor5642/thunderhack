/*    */ package thunder.hack.utility.render.shaders;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedCoreShader;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ShaderEffectManager;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*    */ 
/*    */ 
/*    */ public class ArcShader
/*    */ {
/*    */   private Uniform2f uLocation;
/*    */   private Uniform2f uSize;
/*    */   private Uniform1f radius;
/*    */   private Uniform1f thickness;
/*    */   private Uniform1f time;
/*    */   private Uniform4f color1;
/*    */   private Uniform4f color2;
/*    */   private Uniform1f start;
/*    */   private Uniform1f end;
/* 27 */   public static final ManagedCoreShader ARC = ShaderEffectManager.getInstance()
/* 28 */     .manageCoreShader(class_2960.method_60655("thunderhack", "arc"), class_290.field_1592);
/*    */   
/*    */   public ArcShader() {
/* 31 */     setup();
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height, float r, float thickness, float start, float end, Color c1, Color c2) {
/* 35 */     if (Module.mc.field_1724 == null)
/*    */       return; 
/* 37 */     float i = (float)Module.mc.method_22683().method_4495();
/* 38 */     this.radius.set(r * i);
/* 39 */     this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
/* 40 */     this.uSize.set(width * i, height * i);
/* 41 */     this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 1.0F);
/* 42 */     this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, 1.0F);
/* 43 */     this.time.set(Module.mc.field_1724.field_6012 * 4.0F);
/* 44 */     this.thickness.set(thickness);
/* 45 */     this.start.set(start);
/* 46 */     this.end.set(end);
/*    */   }
/*    */   
/*    */   public void use() {
/* 50 */     Objects.requireNonNull(ARC); RenderSystem.setShader(ARC::getProgram);
/*    */   }
/*    */   
/*    */   protected void setup() {
/* 54 */     this.uSize = ARC.findUniform2f("uSize");
/* 55 */     this.uLocation = ARC.findUniform2f("uLocation");
/* 56 */     this.radius = ARC.findUniform1f("radius");
/* 57 */     this.thickness = ARC.findUniform1f("thickness");
/* 58 */     this.start = ARC.findUniform1f("start");
/* 59 */     this.end = ARC.findUniform1f("end");
/* 60 */     this.time = ARC.findUniform1f("time");
/* 61 */     this.color1 = ARC.findUniform4f("color1");
/* 62 */     this.color2 = ARC.findUniform4f("color2");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\ArcShader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */