/*    */ package thunder.hack.utility.render.shaders;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedCoreShader;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ShaderEffectManager;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*    */ 
/*    */ 
/*    */ public class HudShader
/*    */ {
/*    */   private Uniform2f uSize;
/*    */   private Uniform2f uLocation;
/*    */   private Uniform1f radius;
/*    */   private Uniform1f blend;
/*    */   private Uniform1f alpha;
/*    */   private Uniform1f outline;
/*    */   private Uniform1f glow;
/*    */   private Uniform4f color1;
/*    */   private Uniform4f color2;
/*    */   private Uniform4f color3;
/*    */   private Uniform4f color4;
/* 30 */   public static final ManagedCoreShader HUD_SHADER = ShaderEffectManager.getInstance()
/* 31 */     .manageCoreShader(class_2960.method_60655("thunderhack", "hudshader"), class_290.field_1592);
/*    */   
/*    */   public HudShader() {
/* 34 */     setup();
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height, float r, float externalAlpha, float internalAlpha) {
/* 38 */     float i = (float)Module.mc.method_22683().method_4495();
/* 39 */     this.radius.set(r * i);
/* 40 */     this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
/* 41 */     this.uSize.set(width * i, height * i);
/*    */     
/* 43 */     Color c1 = HudEditor.getColor(270);
/* 44 */     Color c2 = HudEditor.getColor(0);
/* 45 */     Color c3 = HudEditor.getColor(180);
/* 46 */     Color c4 = HudEditor.getColor(90);
/*    */     
/* 48 */     this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, externalAlpha);
/* 49 */     this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, externalAlpha);
/* 50 */     this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, externalAlpha);
/* 51 */     this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, externalAlpha);
/* 52 */     this.blend.set(((Float)HudEditor.blend.getValue()).floatValue());
/* 53 */     this.outline.set(((Float)HudEditor.outline.getValue()).floatValue());
/* 54 */     this.glow.set(((Float)HudEditor.glow1.getValue()).floatValue());
/* 55 */     this.alpha.set(internalAlpha);
/*    */   }
/*    */   
/*    */   public void use() {
/* 59 */     Objects.requireNonNull(HUD_SHADER); RenderSystem.setShader(HUD_SHADER::getProgram);
/*    */   }
/*    */   
/*    */   public void setup() {
/* 63 */     this.uSize = HUD_SHADER.findUniform2f("uSize");
/* 64 */     this.uLocation = HUD_SHADER.findUniform2f("uLocation");
/* 65 */     this.radius = HUD_SHADER.findUniform1f("radius");
/* 66 */     this.blend = HUD_SHADER.findUniform1f("blend");
/* 67 */     this.alpha = HUD_SHADER.findUniform1f("alpha");
/* 68 */     this.color1 = HUD_SHADER.findUniform4f("color1");
/* 69 */     this.color2 = HUD_SHADER.findUniform4f("color2");
/* 70 */     this.color3 = HUD_SHADER.findUniform4f("color3");
/* 71 */     this.color4 = HUD_SHADER.findUniform4f("color4");
/* 72 */     this.outline = HUD_SHADER.findUniform1f("outline");
/* 73 */     this.glow = HUD_SHADER.findUniform1f("glow");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\HudShader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */