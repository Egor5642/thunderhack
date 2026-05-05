/*    */ package thunder.hack.utility.render.shaders;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_1041;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_6367;
/*    */ import org.lwjgl.opengl.GL30;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.render.WindowResizeCallback;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedCoreShader;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ShaderEffectManager;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.SamplerUniform;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*    */ 
/*    */ 
/*    */ public class BlurProgram
/*    */ {
/*    */   private Uniform2f uSize;
/*    */   private Uniform2f uLocation;
/*    */   private Uniform1f radius;
/*    */   private Uniform2f inputResolution;
/*    */   private Uniform1f brightness;
/*    */   private Uniform1f quality;
/*    */   private Uniform4f color1;
/*    */   private SamplerUniform sampler;
/*    */   private class_276 input;
/* 34 */   public static final ManagedCoreShader BLUR = ShaderEffectManager.getInstance()
/* 35 */     .manageCoreShader(class_2960.method_60655("thunderhack", "blur"), class_290.field_1592);
/*    */   
/*    */   public BlurProgram() {
/* 38 */     setup();
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height, float r, Color c1, float blurStrenth, float blurOpacity) {
/* 42 */     if (this.input == null) {
/* 43 */       this.input = (class_276)new class_6367(Module.mc.method_22683().method_4486(), Module.mc.method_22683().method_4502(), false, class_310.field_1703);
/*    */     }
/* 45 */     float i = (float)Module.mc.method_22683().method_4495();
/* 46 */     this.radius.set(r * i);
/* 47 */     this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
/* 48 */     this.uSize.set(width * i, height * i);
/* 49 */     this.brightness.set(blurOpacity);
/* 50 */     this.quality.set(blurStrenth);
/* 51 */     this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 1.0F);
/* 52 */     this.sampler.set(this.input.method_30277());
/*    */   }
/*    */   
/*    */   public void use() {
/* 56 */     class_276 buffer = class_310.method_1551().method_1522();
/* 57 */     this.input.method_1235(false);
/* 58 */     GL30.glBindFramebuffer(36008, buffer.field_1476);
/* 59 */     GL30.glBlitFramebuffer(0, 0, buffer.field_1482, buffer.field_1481, 0, 0, buffer.field_1482, buffer.field_1481, 16384, 9729);
/* 60 */     buffer.method_1235(false);
/*    */     
/* 62 */     if (this.input != null && (this.input.field_1482 != Module.mc.method_22683().method_4489() || this.input.field_1481 != Module.mc.method_22683().method_4506())) {
/* 63 */       this.input.method_1234(Module.mc.method_22683().method_4489(), Module.mc.method_22683().method_4506(), class_310.field_1703);
/*    */     }
/* 65 */     this.inputResolution.set(buffer.field_1482, buffer.field_1481);
/* 66 */     this.sampler.set(this.input.method_30277());
/*    */     
/* 68 */     Objects.requireNonNull(BLUR); RenderSystem.setShader(BLUR::getProgram);
/*    */   }
/*    */   
/*    */   protected void setup() {
/* 72 */     this.inputResolution = BLUR.findUniform2f("InputResolution");
/* 73 */     this.brightness = BLUR.findUniform1f("Brightness");
/* 74 */     this.quality = BLUR.findUniform1f("Quality");
/* 75 */     this.color1 = BLUR.findUniform4f("color1");
/* 76 */     this.uSize = BLUR.findUniform2f("uSize");
/* 77 */     this.uLocation = BLUR.findUniform2f("uLocation");
/* 78 */     this.radius = BLUR.findUniform1f("radius");
/* 79 */     this.sampler = BLUR.findSampler("InputSampler");
/* 80 */     WindowResizeCallback.EVENT.register((client, window) -> {
/*    */           if (this.input != null)
/*    */             this.input.method_1234(window.method_4489(), window.method_4506(), class_310.field_1703); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\BlurProgram.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */