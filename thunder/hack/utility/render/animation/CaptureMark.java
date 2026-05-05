/*    */ package thunder.hack.utility.render.animation;
/*    */ import com.mojang.blaze3d.platform.GlStateManager;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_287;
/*    */ import net.minecraft.class_289;
/*    */ import net.minecraft.class_4184;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_757;
/*    */ import net.minecraft.class_7833;
/*    */ import org.joml.Matrix4f;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class CaptureMark {
/* 18 */   private static float espValue = 1.0F; private static float prevEspValue;
/* 19 */   private static float espSpeed = 1.0F;
/*    */   private static boolean flipSpeed;
/*    */   
/*    */   public static void render(class_1297 target) {
/* 23 */     class_4184 camera = Module.mc.field_1773.method_19418();
/*    */     
/* 25 */     double tPosX = Render2DEngine.interpolate(target.field_6014, target.method_23317(), Render3DEngine.getTickDelta()) - (camera.method_19326()).field_1352;
/* 26 */     double tPosY = Render2DEngine.interpolate(target.field_6036, target.method_23318(), Render3DEngine.getTickDelta()) - (camera.method_19326()).field_1351;
/* 27 */     double tPosZ = Render2DEngine.interpolate(target.field_5969, target.method_23321(), Render3DEngine.getTickDelta()) - (camera.method_19326()).field_1350;
/*    */     
/* 29 */     class_4587 matrices = new class_4587();
/* 30 */     RenderSystem.disableDepthTest();
/* 31 */     RenderSystem.disableCull();
/* 32 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 33 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 34 */     matrices.method_22904(tPosX, tPosY + (target.method_18381(target.method_18376()) / 2.0F), tPosZ);
/* 35 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 36 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 37 */     matrices.method_22907(class_7833.field_40718.rotationDegrees(Render2DEngine.interpolateFloat(prevEspValue, espValue, Render3DEngine.getTickDelta())));
/* 38 */     RenderSystem.enableBlend();
/* 39 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 40 */     RenderSystem.setShaderTexture(0, TextureStorage.capture);
/* 41 */     matrices.method_22904(-0.75D, -0.75D, -0.01D);
/* 42 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 43 */     RenderSystem.setShader(class_757::method_34543);
/* 44 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
/* 45 */     buffer.method_22918(matrix, 0.0F, 1.5F, 0.0F).method_22913(0.0F, 1.0F).method_39415(HudEditor.getColor(90).getRGB());
/* 46 */     buffer.method_22918(matrix, 1.5F, 1.5F, 0.0F).method_22913(1.0F, 1.0F).method_39415(HudEditor.getColor(0).getRGB());
/* 47 */     buffer.method_22918(matrix, 1.5F, 0.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(HudEditor.getColor(180).getRGB());
/* 48 */     buffer.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(HudEditor.getColor(270).getRGB());
/* 49 */     class_286.method_43433(buffer.method_60800());
/* 50 */     RenderSystem.enableCull();
/* 51 */     RenderSystem.enableDepthTest();
/* 52 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
/* 53 */     RenderSystem.disableBlend();
/* 54 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public static void tick() {
/* 58 */     prevEspValue = espValue;
/* 59 */     espValue += espSpeed;
/* 60 */     if (espSpeed > 25.0F) flipSpeed = true; 
/* 61 */     if (espSpeed < -25.0F) flipSpeed = false; 
/* 62 */     espSpeed = flipSpeed ? (espSpeed - 0.5F) : (espSpeed + 0.5F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\animation\CaptureMark.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */