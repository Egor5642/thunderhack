/*    */ package thunder.hack.utility.render.shaders;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.utility.render.animation.AnimationUtility;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedCoreShader;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ShaderEffectManager;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.uniform.Uniform4f;
/*    */ 
/*    */ public class MainMenuProgram {
/*    */   private Uniform1f Time;
/*    */   private Uniform2f uSize;
/*    */   private Uniform4f color;
/* 19 */   public static float time_ = 10000.0F;
/*    */   
/* 21 */   public static final ManagedCoreShader MAIN_MENU = ShaderEffectManager.getInstance()
/* 22 */     .manageCoreShader(class_2960.method_60655("thunderhack", "mainmenu"), class_290.field_1592);
/*    */   
/*    */   public MainMenuProgram() {
/* 25 */     setup();
/*    */   }
/*    */   
/*    */   public void setParameters(float x, float y, float width, float height) {
/* 29 */     float i = (float)Module.mc.method_22683().method_4495();
/* 30 */     this.uSize.set(width * i, height * i);
/* 31 */     time_ += (float)(0.55D * AnimationUtility.deltaTime());
/* 32 */     this.Time.set(time_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use() {
/* 37 */     Objects.requireNonNull(MAIN_MENU); RenderSystem.setShader(MAIN_MENU::getProgram);
/*    */   }
/*    */   
/*    */   protected void setup() {
/* 41 */     this.uSize = MAIN_MENU.findUniform2f("uSize");
/* 42 */     this.Time = MAIN_MENU.findUniform1f("Time");
/* 43 */     this.color = MAIN_MENU.findUniform4f("color");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\MainMenuProgram.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */