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
/*    */ public class RectangleShader
/*    */ {
/*    */   private Uniform2f uSize;
/*    */   private Uniform2f uLocation;
/*    */   private Uniform1f radius;
/*    */   private Uniform4f color1;
/*    */   private Uniform4f color2;
/*    */   private Uniform4f color3;
/*    */   private Uniform4f color4;
/* 26 */   public static final ManagedCoreShader RECTANGLE_SHADER = ShaderEffectManager.getInstance()
/* 27 */     .manageCoreShader(class_2960.method_60655("thunderhack", "rectangle"), class_290.field_1592);
/*    */   
/*    */   public RectangleShader() {
/* 30 */     setup();
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height, float r, float alpha) {
/* 34 */     float i = (float)Module.mc.method_22683().method_4495();
/* 35 */     this.radius.set(r * i);
/* 36 */     this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
/* 37 */     this.uSize.set(width * i, height * i);
/*    */     
/* 39 */     Color c1 = HudEditor.getColor(270);
/* 40 */     Color c2 = HudEditor.getColor(0);
/* 41 */     Color c3 = HudEditor.getColor(180);
/* 42 */     Color c4 = HudEditor.getColor(90);
/*    */     
/* 44 */     this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, alpha);
/* 45 */     this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, alpha);
/* 46 */     this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, alpha);
/* 47 */     this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, alpha);
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height, float r, float alpha, Color c1, Color c2, Color c3, Color c4) {
/* 51 */     int i = ((Integer)Module.mc.field_1690.method_42474().method_41753()).intValue();
/* 52 */     this.radius.set(r * i);
/* 53 */     this.uLocation.set(x * i, -y * i + (Module.mc.method_22683().method_4502() * i) - height * i);
/* 54 */     this.uSize.set(width * i, height * i);
/* 55 */     this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, alpha);
/* 56 */     this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, alpha);
/* 57 */     this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, alpha);
/* 58 */     this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, alpha);
/*    */   }
/*    */   
/*    */   public void use() {
/* 62 */     Objects.requireNonNull(RECTANGLE_SHADER); RenderSystem.setShader(RECTANGLE_SHADER::getProgram);
/*    */   }
/*    */   
/*    */   protected void setup() {
/* 66 */     this.uSize = RECTANGLE_SHADER.findUniform2f("uSize");
/* 67 */     this.uLocation = RECTANGLE_SHADER.findUniform2f("uLocation");
/* 68 */     this.radius = RECTANGLE_SHADER.findUniform1f("radius");
/* 69 */     this.color1 = RECTANGLE_SHADER.findUniform4f("color1");
/* 70 */     this.color2 = RECTANGLE_SHADER.findUniform4f("color2");
/* 71 */     this.color3 = RECTANGLE_SHADER.findUniform4f("color3");
/* 72 */     this.color4 = RECTANGLE_SHADER.findUniform4f("color4");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\RectangleShader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */