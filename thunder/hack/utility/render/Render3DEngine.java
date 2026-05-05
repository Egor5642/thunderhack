/*     */ package thunder.hack.utility.render;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_286;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_4184;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4588;
/*     */ import net.minecraft.class_757;
/*     */ import net.minecraft.class_7833;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Vector3f;
/*     */ import org.joml.Vector4f;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ 
/*     */ public class Render3DEngine {
/*  28 */   public static List<FillAction> FILLED_QUEUE = new ArrayList<>();
/*  29 */   public static List<OutlineAction> OUTLINE_QUEUE = new ArrayList<>();
/*  30 */   public static List<FadeAction> FADE_QUEUE = new ArrayList<>();
/*  31 */   public static List<FillSideAction> FILLED_SIDE_QUEUE = new ArrayList<>();
/*  32 */   public static List<OutlineSideAction> OUTLINE_SIDE_QUEUE = new ArrayList<>();
/*  33 */   public static List<DebugLineAction> DEBUG_LINE_QUEUE = new ArrayList<>();
/*  34 */   public static List<LineAction> LINE_QUEUE = new ArrayList<>();
/*     */   
/*  36 */   public static final Matrix4f lastProjMat = new Matrix4f();
/*  37 */   public static final Matrix4f lastModMat = new Matrix4f();
/*  38 */   public static final Matrix4f lastWorldSpaceMatrix = new Matrix4f();
/*     */   
/*     */   private static float prevCircleStep;
/*     */   
/*     */   private static float circleStep;
/*     */ 
/*     */   
/*     */   public static void onRender3D(class_4587 stack) {
/*  46 */     if (!FILLED_QUEUE.isEmpty() || !FADE_QUEUE.isEmpty() || !FILLED_SIDE_QUEUE.isEmpty()) {
/*  47 */       class_289 tessellator = class_289.method_1348();
/*  48 */       class_287 bufferBuilder = tessellator.method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/*  49 */       RenderSystem.disableDepthTest();
/*  50 */       setupRender();
/*  51 */       RenderSystem.setShader(class_757::method_34540);
/*     */       
/*  53 */       FILLED_QUEUE.forEach(action -> setFilledBoxVertexes(bufferBuilder, stack.method_23760().method_23761(), action.box(), action.color()));
/*     */       
/*  55 */       FADE_QUEUE.forEach(action -> setFilledFadePoints(action.box(), bufferBuilder, stack.method_23760().method_23761(), action.color(), action.color2()));
/*     */       
/*  57 */       FILLED_SIDE_QUEUE.forEach(action -> setFilledSidePoints(bufferBuilder, stack.method_23760().method_23761(), action.box, action.color(), action.side()));
/*  58 */       Render2DEngine.endBuilding(bufferBuilder);
/*     */       
/*  60 */       endRender();
/*  61 */       RenderSystem.enableDepthTest();
/*     */       
/*  63 */       FADE_QUEUE.clear();
/*  64 */       FILLED_SIDE_QUEUE.clear();
/*  65 */       FILLED_QUEUE.clear();
/*     */     } 
/*     */     
/*  68 */     if (!OUTLINE_QUEUE.isEmpty() || !OUTLINE_SIDE_QUEUE.isEmpty()) {
/*  69 */       setupRender();
/*  70 */       class_289 tessellator = class_289.method_1348();
/*  71 */       class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27377, class_290.field_29337);
/*  72 */       RenderSystem.disableCull();
/*  73 */       RenderSystem.disableDepthTest();
/*  74 */       RenderSystem.setShader(class_757::method_34535);
/*     */       
/*  76 */       RenderSystem.lineWidth(2.0F);
/*     */       
/*  78 */       OUTLINE_QUEUE.forEach(action -> setOutlinePoints(action.box(), matrixFrom((action.box()).field_1323, (action.box()).field_1322, (action.box()).field_1321), buffer, action.color()));
/*     */ 
/*     */ 
/*     */       
/*  82 */       OUTLINE_SIDE_QUEUE.forEach(action -> setSideOutlinePoints(action.box, matrixFrom((action.box()).field_1323, (action.box()).field_1322, (action.box()).field_1321), buffer, action.color(), action.side()));
/*     */ 
/*     */ 
/*     */       
/*  86 */       Render2DEngine.endBuilding(buffer);
/*     */       
/*  88 */       RenderSystem.enableCull();
/*  89 */       RenderSystem.enableDepthTest();
/*  90 */       endRender();
/*  91 */       OUTLINE_QUEUE.clear();
/*  92 */       OUTLINE_SIDE_QUEUE.clear();
/*     */     } 
/*     */     
/*  95 */     if (!DEBUG_LINE_QUEUE.isEmpty()) {
/*  96 */       setupRender();
/*  97 */       RenderSystem.disableDepthTest();
/*  98 */       class_289 tessellator = class_289.method_1348();
/*  99 */       class_287 buffer = tessellator.method_60827(class_293.class_5596.field_29344, class_290.field_29337);
/*     */       
/* 101 */       RenderSystem.disableCull();
/* 102 */       RenderSystem.setShader(class_757::method_34535);
/* 103 */       DEBUG_LINE_QUEUE.forEach(action -> {
/*     */             class_4587 matrices = matrixFrom(action.start.method_10216(), action.start.method_10214(), action.start.method_10215());
/*     */             vertexLine(matrices, (class_4588)buffer, 0.0F, 0.0F, 0.0F, (float)(action.end.method_10216() - action.start.method_10216()), (float)(action.end.method_10214() - action.start.method_10214()), (float)(action.end.method_10215() - action.start.method_10215()), action.color);
/*     */           });
/* 107 */       Render2DEngine.endBuilding(buffer);
/* 108 */       RenderSystem.enableCull();
/* 109 */       RenderSystem.enableDepthTest();
/* 110 */       endRender();
/* 111 */       DEBUG_LINE_QUEUE.clear();
/*     */     } 
/*     */     
/* 114 */     if (!LINE_QUEUE.isEmpty()) {
/* 115 */       setupRender();
/* 116 */       class_289 tessellator = class_289.method_1348();
/* 117 */       RenderSystem.disableCull();
/* 118 */       RenderSystem.setShader(class_757::method_34535);
/* 119 */       RenderSystem.lineWidth(2.0F);
/* 120 */       RenderSystem.disableDepthTest();
/* 121 */       class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27377, class_290.field_29337);
/* 122 */       LINE_QUEUE.forEach(action -> {
/*     */             class_4587 matrices = matrixFrom(action.start.method_10216(), action.start.method_10214(), action.start.method_10215());
/*     */             vertexLine(matrices, (class_4588)buffer, 0.0F, 0.0F, 0.0F, (float)(action.end.method_10216() - action.start.method_10216()), (float)(action.end.method_10214() - action.start.method_10214()), (float)(action.end.method_10215() - action.start.method_10215()), action.color);
/*     */           });
/* 126 */       Render2DEngine.endBuilding(buffer);
/* 127 */       RenderSystem.enableCull();
/* 128 */       RenderSystem.lineWidth(1.0F);
/* 129 */       RenderSystem.enableDepthTest();
/* 130 */       endRender();
/* 131 */       LINE_QUEUE.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void drawFilledBox(class_4587 stack, class_238 box, Color c) {
/* 138 */     FILLED_QUEUE.add(new FillAction(box, c));
/*     */   }
/*     */   
/*     */   public static void setFilledBoxVertexes(@NotNull class_287 bufferBuilder, Matrix4f m, @NotNull class_238 box, @NotNull Color c) {
/* 142 */     float minX = (float)(box.field_1323 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 143 */     float minY = (float)(box.field_1322 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 144 */     float minZ = (float)(box.field_1321 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/* 145 */     float maxX = (float)(box.field_1320 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 146 */     float maxY = (float)(box.field_1325 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 147 */     float maxZ = (float)(box.field_1324 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/*     */     
/* 149 */     bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
/* 150 */     bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
/* 151 */     bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
/* 152 */     bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
/*     */     
/* 154 */     bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
/* 155 */     bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
/* 156 */     bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
/* 157 */     bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
/*     */     
/* 159 */     bufferBuilder.method_22918(m, maxX, minY, minZ).method_39415(c.getRGB());
/* 160 */     bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
/* 161 */     bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 162 */     bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
/*     */     
/* 164 */     bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
/* 165 */     bufferBuilder.method_22918(m, maxX, minY, maxZ).method_39415(c.getRGB());
/* 166 */     bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 167 */     bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
/*     */     
/* 169 */     bufferBuilder.method_22918(m, minX, minY, minZ).method_39415(c.getRGB());
/* 170 */     bufferBuilder.method_22918(m, minX, minY, maxZ).method_39415(c.getRGB());
/* 171 */     bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
/* 172 */     bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
/*     */     
/* 174 */     bufferBuilder.method_22918(m, minX, maxY, minZ).method_39415(c.getRGB());
/* 175 */     bufferBuilder.method_22918(m, minX, maxY, maxZ).method_39415(c.getRGB());
/* 176 */     bufferBuilder.method_22918(m, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 177 */     bufferBuilder.method_22918(m, maxX, maxY, minZ).method_39415(c.getRGB());
/*     */   }
/*     */   @NotNull
/*     */   public static class_238 interpolateBox(@NotNull class_238 from, @NotNull class_238 to, float delta) {
/* 181 */     double X = Render2DEngine.interpolate(from.field_1320, to.field_1320, delta);
/* 182 */     double Y = Render2DEngine.interpolate(from.field_1325, to.field_1325, delta);
/* 183 */     double Z = Render2DEngine.interpolate(from.field_1324, to.field_1324, delta);
/* 184 */     double X1 = Render2DEngine.interpolate(from.field_1323, to.field_1323, delta);
/* 185 */     double Y1 = Render2DEngine.interpolate(from.field_1322, to.field_1322, delta);
/* 186 */     double Z1 = Render2DEngine.interpolate(from.field_1321, to.field_1321, delta);
/* 187 */     return new class_238(X1, Y1, Z1, X, Y, Z);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void drawFilledSide(class_4587 stack, @NotNull class_238 box, Color c, class_2350 dir) {
/* 192 */     FILLED_SIDE_QUEUE.add(new FillSideAction(box, c, dir));
/*     */   }
/*     */   
/*     */   public static void setFilledSidePoints(class_287 buffer, Matrix4f matrix, class_238 box, Color c, class_2350 dir) {
/* 196 */     float minX = (float)(box.field_1323 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 197 */     float minY = (float)(box.field_1322 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 198 */     float minZ = (float)(box.field_1321 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/* 199 */     float maxX = (float)(box.field_1320 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 200 */     float maxY = (float)(box.field_1325 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 201 */     float maxZ = (float)(box.field_1324 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/*     */     
/* 203 */     if (dir == class_2350.field_11033) {
/* 204 */       buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
/* 205 */       buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
/* 206 */       buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
/* 207 */       buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
/*     */     } 
/*     */     
/* 210 */     if (dir == class_2350.field_11043) {
/* 211 */       buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
/* 212 */       buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
/* 213 */       buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
/* 214 */       buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
/*     */     } 
/*     */     
/* 217 */     if (dir == class_2350.field_11034) {
/* 218 */       buffer.method_22918(matrix, maxX, minY, minZ).method_39415(c.getRGB());
/* 219 */       buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
/* 220 */       buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 221 */       buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
/*     */     } 
/* 223 */     if (dir == class_2350.field_11035) {
/* 224 */       buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
/* 225 */       buffer.method_22918(matrix, maxX, minY, maxZ).method_39415(c.getRGB());
/* 226 */       buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 227 */       buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
/*     */     } 
/*     */     
/* 230 */     if (dir == class_2350.field_11039) {
/* 231 */       buffer.method_22918(matrix, minX, minY, minZ).method_39415(c.getRGB());
/* 232 */       buffer.method_22918(matrix, minX, minY, maxZ).method_39415(c.getRGB());
/* 233 */       buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
/* 234 */       buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
/*     */     } 
/*     */     
/* 237 */     if (dir == class_2350.field_11036) {
/* 238 */       buffer.method_22918(matrix, minX, maxY, minZ).method_39415(c.getRGB());
/* 239 */       buffer.method_22918(matrix, minX, maxY, maxZ).method_39415(c.getRGB());
/* 240 */       buffer.method_22918(matrix, maxX, maxY, maxZ).method_39415(c.getRGB());
/* 241 */       buffer.method_22918(matrix, maxX, maxY, minZ).method_39415(c.getRGB());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawTextIn3D(String text, @NotNull class_243 pos, double offX, double offY, double textOffset, @NotNull Color color) {
/* 246 */     class_4587 matrices = new class_4587();
/* 247 */     class_4184 camera = Module.mc.field_1773.method_19418();
/* 248 */     RenderSystem.disableDepthTest();
/* 249 */     RenderSystem.disableCull();
/* 250 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 251 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 252 */     matrices.method_22904(pos.method_10216() - (camera.method_19326()).field_1352, pos.method_10214() - (camera.method_19326()).field_1351, pos.method_10215() - (camera.method_19326()).field_1350);
/* 253 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 254 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 255 */     setupRender();
/* 256 */     matrices.method_22904(offX, offY - 0.1D, -0.01D);
/* 257 */     matrices.method_22905(-0.025F, -0.025F, 0.0F);
/* 258 */     FontRenderers.sf_medium.drawCenteredString(matrices, text, textOffset, 0.0D, color.getRGB());
/* 259 */     RenderSystem.enableCull();
/* 260 */     RenderSystem.enableDepthTest();
/* 261 */     endRender();
/*     */   }
/*     */   @NotNull
/*     */   public static class_243 worldSpaceToScreenSpace(@NotNull class_243 pos) {
/* 265 */     class_4184 camera = (Module.mc.method_1561()).field_4686;
/* 266 */     int displayHeight = Module.mc.method_22683().method_4507();
/* 267 */     int[] viewport = new int[4];
/* 268 */     GL11.glGetIntegerv(2978, viewport);
/* 269 */     Vector3f target = new Vector3f();
/*     */     
/* 271 */     double deltaX = pos.field_1352 - (camera.method_19326()).field_1352;
/* 272 */     double deltaY = pos.field_1351 - (camera.method_19326()).field_1351;
/* 273 */     double deltaZ = pos.field_1350 - (camera.method_19326()).field_1350;
/*     */     
/* 275 */     Vector4f transformedCoordinates = (new Vector4f((float)deltaX, (float)deltaY, (float)deltaZ, 1.0F)).mul((Matrix4fc)lastWorldSpaceMatrix);
/* 276 */     Matrix4f matrixProj = new Matrix4f((Matrix4fc)lastProjMat);
/* 277 */     Matrix4f matrixModel = new Matrix4f((Matrix4fc)lastModMat);
/* 278 */     matrixProj.mul((Matrix4fc)matrixModel).project(transformedCoordinates.x(), transformedCoordinates.y(), transformedCoordinates.z(), viewport, target);
/*     */     
/* 280 */     return new class_243(target.x / getScaleFactor(), (displayHeight - target.y) / getScaleFactor(), target.z);
/*     */   }
/*     */   
/*     */   public static double getScaleFactor() {
/* 284 */     return ((Boolean)ClientSettings.scaleFactorFix.getValue()).booleanValue() ? ((Float)ClientSettings.scaleFactorFixValue.getValue()).floatValue() : Module.mc.method_22683().method_4495();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void drawFilledFadeBox(@NotNull class_4587 stack, @NotNull class_238 box, @NotNull Color c, @NotNull Color c1) {
/* 290 */     FADE_QUEUE.add(new FadeAction(box, c, c1));
/*     */   }
/*     */   
/*     */   public static void setFilledFadePoints(class_238 box, class_287 buffer, Matrix4f posMatrix, Color c, Color c1) {
/* 294 */     float minX = (float)(box.field_1323 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 295 */     float minY = (float)(box.field_1322 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 296 */     float minZ = (float)(box.field_1321 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/* 297 */     float maxX = (float)(box.field_1320 - (Module.mc.method_1561()).field_4686.method_19326().method_10216());
/* 298 */     float maxY = (float)(box.field_1325 - (Module.mc.method_1561()).field_4686.method_19326().method_10214());
/* 299 */     float maxZ = (float)(box.field_1324 - (Module.mc.method_1561()).field_4686.method_19326().method_10215());
/*     */     
/* 301 */     if (((Boolean)ModuleManager.holeESP.culling.getValue()).booleanValue()) {
/* 302 */       RenderSystem.enableCull();
/*     */     }
/* 304 */     buffer.method_22918(posMatrix, minX, minY, minZ).method_39415(c.getRGB());
/* 305 */     buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
/* 306 */     buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
/* 307 */     buffer.method_22918(posMatrix, maxX, minY, minZ).method_39415(c.getRGB());
/*     */     
/* 309 */     buffer.method_22918(posMatrix, maxX, minY, minZ).method_39415(c.getRGB());
/* 310 */     buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
/* 311 */     buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
/* 312 */     buffer.method_22918(posMatrix, maxX, minY, maxZ).method_39415(c.getRGB());
/*     */     
/* 314 */     buffer.method_22918(posMatrix, minX, minY, maxZ).method_39415(c.getRGB());
/* 315 */     buffer.method_22918(posMatrix, maxX, minY, maxZ).method_39415(c.getRGB());
/* 316 */     buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
/* 317 */     buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
/*     */     
/* 319 */     buffer.method_22918(posMatrix, minX, minY, minZ).method_39415(c.getRGB());
/* 320 */     buffer.method_22918(posMatrix, minX, minY, maxZ).method_39415(c.getRGB());
/* 321 */     buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
/* 322 */     buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
/*     */     
/* 324 */     buffer.method_22918(posMatrix, minX, maxY, minZ).method_39415(c1.getRGB());
/* 325 */     buffer.method_22918(posMatrix, minX, maxY, maxZ).method_39415(c1.getRGB());
/* 326 */     buffer.method_22918(posMatrix, maxX, maxY, maxZ).method_39415(c1.getRGB());
/* 327 */     buffer.method_22918(posMatrix, maxX, maxY, minZ).method_39415(c1.getRGB());
/*     */     
/* 329 */     if (((Boolean)ModuleManager.holeESP.culling.getValue()).booleanValue())
/* 330 */       RenderSystem.disableCull(); 
/*     */   }
/*     */   
/*     */   public static void drawLine(@NotNull class_243 start, @NotNull class_243 end, @NotNull Color color) {
/* 334 */     LINE_QUEUE.add(new LineAction(start, end, color));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void drawBoxOutline(@NotNull class_238 box, Color color, float lineWidth) {
/* 339 */     OUTLINE_QUEUE.add(new OutlineAction(box, color, lineWidth));
/*     */   }
/*     */   
/*     */   public static void setOutlinePoints(class_238 box, class_4587 matrices, class_287 buffer, Color color) {
/* 343 */     box = box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882());
/*     */     
/* 345 */     float x1 = (float)box.field_1323;
/* 346 */     float y1 = (float)box.field_1322;
/* 347 */     float z1 = (float)box.field_1321;
/* 348 */     float x2 = (float)box.field_1320;
/* 349 */     float y2 = (float)box.field_1325;
/* 350 */     float z2 = (float)box.field_1324;
/*     */     
/* 352 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x2, y1, z1, color);
/* 353 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y1, z2, color);
/* 354 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x1, y1, z2, color);
/* 355 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y1, z1, color);
/* 356 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y2, z2, color);
/* 357 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x1, y2, z1, color);
/* 358 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x2, y2, z2, color);
/* 359 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y2, z1, color);
/* 360 */     vertexLine(matrices, (class_4588)buffer, x1, y2, z1, x2, y2, z1, color);
/* 361 */     vertexLine(matrices, (class_4588)buffer, x2, y2, z1, x2, y2, z2, color);
/* 362 */     vertexLine(matrices, (class_4588)buffer, x2, y2, z2, x1, y2, z2, color);
/* 363 */     vertexLine(matrices, (class_4588)buffer, x1, y2, z2, x1, y2, z1, color);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void drawSideOutline(@NotNull class_238 box, Color color, float lineWidth, class_2350 dir) {
/* 368 */     OUTLINE_SIDE_QUEUE.add(new OutlineSideAction(box, color, lineWidth, dir));
/*     */   }
/*     */   
/*     */   public static void setSideOutlinePoints(class_238 box, class_4587 matrices, class_287 buffer, Color color, class_2350 dir) {
/* 372 */     box = box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882());
/*     */     
/* 374 */     float x1 = (float)box.field_1323;
/* 375 */     float y1 = (float)box.field_1322;
/* 376 */     float z1 = (float)box.field_1321;
/* 377 */     float x2 = (float)box.field_1320;
/* 378 */     float y2 = (float)box.field_1325;
/* 379 */     float z2 = (float)box.field_1324;
/*     */     
/* 381 */     switch (dir) {
/*     */       case field_11036:
/* 383 */         vertexLine(matrices, (class_4588)buffer, x1, y2, z1, x2, y2, z1, color);
/* 384 */         vertexLine(matrices, (class_4588)buffer, x2, y2, z1, x2, y2, z2, color);
/* 385 */         vertexLine(matrices, (class_4588)buffer, x2, y2, z2, x1, y2, z2, color);
/* 386 */         vertexLine(matrices, (class_4588)buffer, x1, y2, z2, x1, y2, z1, color);
/*     */         break;
/*     */       case field_11033:
/* 389 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x2, y1, z1, color);
/* 390 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y1, z2, color);
/* 391 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x1, y1, z2, color);
/* 392 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y1, z1, color);
/*     */         break;
/*     */       case field_11034:
/* 395 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y2, z1, color);
/* 396 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x2, y2, z2, color);
/* 397 */         vertexLine(matrices, (class_4588)buffer, x2, y2, z2, x2, y2, z1, color);
/* 398 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x2, y1, z1, color);
/*     */         break;
/*     */       case field_11039:
/* 401 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x1, y2, z1, color);
/* 402 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y2, z2, color);
/* 403 */         vertexLine(matrices, (class_4588)buffer, x1, y2, z2, x1, y2, z1, color);
/* 404 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y1, z1, color);
/*     */         break;
/*     */       case field_11043:
/* 407 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y2, z1, color);
/* 408 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x1, y2, z1, color);
/* 409 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x1, y1, z1, color);
/* 410 */         vertexLine(matrices, (class_4588)buffer, x2, y2, z1, x1, y2, z1, color);
/*     */         break;
/*     */       case field_11035:
/* 413 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y2, z2, color);
/* 414 */         vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x2, y2, z2, color);
/* 415 */         vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x2, y1, z2, color);
/* 416 */         vertexLine(matrices, (class_4588)buffer, x1, y2, z2, x2, y2, z2, color);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawHoleOutline(@NotNull class_238 box, Color color, float lineWidth) {
/* 422 */     setupRender();
/* 423 */     class_4587 matrices = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
/* 424 */     class_289 tessellator = class_289.method_1348();
/* 425 */     class_287 buffer = tessellator.method_60827(class_293.class_5596.field_27377, class_290.field_29337);
/*     */     
/* 427 */     RenderSystem.disableCull();
/* 428 */     RenderSystem.setShader(class_757::method_34535);
/* 429 */     RenderSystem.lineWidth(lineWidth);
/*     */     
/* 431 */     box = box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882());
/*     */     
/* 433 */     float x1 = (float)box.field_1323;
/* 434 */     float y1 = (float)box.field_1322;
/* 435 */     float y2 = (float)box.field_1325;
/* 436 */     float z1 = (float)box.field_1321;
/* 437 */     float x2 = (float)box.field_1320;
/* 438 */     float z2 = (float)box.field_1324;
/*     */     
/* 440 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x2, y1, z1, color);
/* 441 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y1, z2, color);
/* 442 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x1, y1, z2, color);
/* 443 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y1, z1, color);
/*     */     
/* 445 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z1, x1, y2, z1, color);
/* 446 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z2, x2, y2, z2, color);
/* 447 */     vertexLine(matrices, (class_4588)buffer, x1, y1, z2, x1, y2, z2, color);
/* 448 */     vertexLine(matrices, (class_4588)buffer, x2, y1, z1, x2, y2, z1, color);
/*     */     
/* 450 */     Render2DEngine.endBuilding(buffer);
/* 451 */     RenderSystem.enableCull();
/* 452 */     endRender();
/*     */   }
/*     */   
/*     */   public static void vertexLine(@NotNull class_4587 matrices, @NotNull class_4588 buffer, float x1, float y1, float z1, float x2, float y2, float z2, @NotNull Color lineColor) {
/* 456 */     Matrix4f model = matrices.method_23760().method_23761();
/* 457 */     class_4587.class_4665 entry = matrices.method_23760();
/* 458 */     Vector3f normalVec = getNormal(x1, y1, z1, x2, y2, z2);
/* 459 */     buffer.method_22918(model, x1, y1, z1).method_1336(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha()).method_60831(entry, normalVec.x(), normalVec.y(), normalVec.z());
/* 460 */     buffer.method_22918(model, x2, y2, z2).method_1336(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), lineColor.getAlpha()).method_60831(entry, normalVec.x(), normalVec.y(), normalVec.z());
/*     */   }
/*     */   @NotNull
/*     */   public static Vector3f getNormal(float x1, float y1, float z1, float x2, float y2, float z2) {
/* 464 */     float xNormal = x2 - x1;
/* 465 */     float yNormal = y2 - y1;
/* 466 */     float zNormal = z2 - z1;
/* 467 */     float normalSqrt = class_3532.method_15355(xNormal * xNormal + yNormal * yNormal + zNormal * zNormal);
/*     */     
/* 469 */     return new Vector3f(xNormal / normalSqrt, yNormal / normalSqrt, zNormal / normalSqrt);
/*     */   }
/*     */   @NotNull
/*     */   public static class_4587 matrixFrom(double x, double y, double z) {
/* 473 */     class_4587 matrices = new class_4587();
/*     */     
/* 475 */     class_4184 camera = (class_310.method_1551()).field_1773.method_19418();
/* 476 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 477 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/*     */     
/* 479 */     matrices.method_22904(x - (camera.method_19326()).field_1352, y - (camera.method_19326()).field_1351, z - (camera.method_19326()).field_1350);
/*     */     
/* 481 */     return matrices;
/*     */   }
/*     */   
/*     */   public static void setupRender() {
/* 485 */     RenderSystem.enableBlend();
/* 486 */     RenderSystem.defaultBlendFunc();
/*     */   }
/*     */   
/*     */   public static void endRender() {
/* 490 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   public static void drawTargetEsp(class_4587 stack, @NotNull class_1297 target) {
/* 494 */     ArrayList<class_243> vecs = new ArrayList<>();
/* 495 */     ArrayList<class_243> vecs1 = new ArrayList<>();
/* 496 */     ArrayList<class_243> vecs2 = new ArrayList<>();
/*     */ 
/*     */     
/* 499 */     double x = target.field_6014 + (target.method_23317() - target.field_6014) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/*     */     
/* 501 */     double y = target.field_6036 + (target.method_23318() - target.field_6036) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10214();
/*     */     
/* 503 */     double z = target.field_5969 + (target.method_23321() - target.field_5969) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/*     */ 
/*     */     
/* 506 */     double height = target.method_17682();
/*     */     
/* 508 */     for (int i = 0; i <= 361; i++) {
/* 509 */       double v = Math.sin(Math.toRadians(i));
/* 510 */       double u = Math.cos(Math.toRadians(i));
/* 511 */       class_243 vec = new class_243((float)(u * 0.5D), height, (float)(v * 0.5D));
/* 512 */       vecs.add(vec);
/*     */       
/* 514 */       double v1 = Math.sin(Math.toRadians(((i + 120) % 360)));
/* 515 */       double u1 = Math.cos(Math.toRadians((i + 120)) % 360.0D);
/* 516 */       class_243 vec1 = new class_243((float)(u1 * 0.5D), height, (float)(v1 * 0.5D));
/* 517 */       vecs1.add(vec1);
/*     */       
/* 519 */       double v2 = Math.sin(Math.toRadians(((i + 240) % 360)));
/* 520 */       double u2 = Math.cos(Math.toRadians(((i + 240) % 360)));
/* 521 */       class_243 vec2 = new class_243((float)(u2 * 0.5D), height, (float)(v2 * 0.5D));
/* 522 */       vecs2.add(vec2);
/* 523 */       height -= 0.004000000189989805D;
/*     */     } 
/*     */ 
/*     */     
/* 527 */     stack.method_22903();
/* 528 */     stack.method_22904(x, y, z);
/*     */     
/* 530 */     setupRender();
/* 531 */     RenderSystem.disableCull();
/* 532 */     RenderSystem.disableDepthTest();
/*     */     
/* 534 */     RenderSystem.setShader(class_757::method_34540);
/* 535 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/*     */     
/* 537 */     Matrix4f matrix = stack.method_23760().method_23761();
/*     */     int j;
/* 539 */     for (j = 0; j < vecs.size() - 1; j++) {
/* 540 */       float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - ThunderHack.initTime) / 5.0F) % 360.0F / 60.0F;
/* 541 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs.get(j)).field_1352, (float)((class_243)vecs.get(j)).field_1351, (float)((class_243)vecs.get(j)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/* 542 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs.get(j + 1)).field_1352, (float)((class_243)vecs.get(j + 1)).field_1351 + 0.1F, (float)((class_243)vecs.get(j + 1)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/*     */     } 
/* 544 */     Render2DEngine.endBuilding(bufferBuilder);
/*     */     
/* 546 */     RenderSystem.setShader(class_757::method_34540);
/* 547 */     bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/* 548 */     for (j = 0; j < vecs1.size() - 1; j++) {
/* 549 */       float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - ThunderHack.initTime) / 5.0F) % 360.0F / 60.0F;
/* 550 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs1.get(j)).field_1352, (float)((class_243)vecs1.get(j)).field_1351, (float)((class_243)vecs1.get(j)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/* 551 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs1.get(j + 1)).field_1352, (float)((class_243)vecs1.get(j + 1)).field_1351 + 0.1F, (float)((class_243)vecs1.get(j + 1)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/*     */     } 
/* 553 */     Render2DEngine.endBuilding(bufferBuilder);
/*     */     
/* 555 */     RenderSystem.setShader(class_757::method_34540);
/* 556 */     bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/* 557 */     for (j = 0; j < vecs2.size() - 1; j++) {
/* 558 */       float alpha = 1.0F - (j + (float)(System.currentTimeMillis() - ThunderHack.initTime) / 5.0F) % 360.0F / 60.0F;
/* 559 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs2.get(j)).field_1352, (float)((class_243)vecs2.get(j)).field_1351, (float)((class_243)vecs2.get(j)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/* 560 */       bufferBuilder.method_22918(matrix, (float)((class_243)vecs2.get(j + 1)).field_1352, (float)((class_243)vecs2.get(j + 1)).field_1351 + 0.1F, (float)((class_243)vecs2.get(j + 1)).field_1350).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor((int)(j / 20.0F)), (int)(alpha * 255.0F)).getRGB());
/*     */     } 
/* 562 */     Render2DEngine.endBuilding(bufferBuilder);
/*     */     
/* 564 */     RenderSystem.enableCull();
/* 565 */     stack.method_22904(-x, -y, -z);
/* 566 */     endRender();
/* 567 */     RenderSystem.enableDepthTest();
/* 568 */     stack.method_22909();
/*     */   }
/*     */   
/*     */   public static void renderCrosses(@NotNull class_238 box, Color color, float lineWidth) {
/* 572 */     setupRender();
/* 573 */     class_4587 matrices = matrixFrom(box.field_1323, box.field_1322, box.field_1321);
/* 574 */     RenderSystem.disableCull();
/* 575 */     RenderSystem.setShader(class_757::method_34535);
/* 576 */     RenderSystem.lineWidth(lineWidth);
/* 577 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27377, class_290.field_29337);
/*     */     
/* 579 */     box = box.method_997((new class_243(box.field_1323, box.field_1322, box.field_1321)).method_22882());
/*     */     
/* 581 */     vertexLine(matrices, (class_4588)buffer, (float)box.field_1320, (float)box.field_1322, (float)box.field_1321, (float)box.field_1323, (float)box.field_1322, (float)box.field_1324, color);
/* 582 */     vertexLine(matrices, (class_4588)buffer, (float)box.field_1323, (float)box.field_1322, (float)box.field_1321, (float)box.field_1320, (float)box.field_1322, (float)box.field_1324, color);
/*     */     
/* 584 */     Render2DEngine.endBuilding(buffer);
/* 585 */     RenderSystem.enableCull();
/* 586 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawSphere(class_4587 matrix, float radius, int slices, int stacks, int color) {
/* 590 */     float drho = 3.1415927F / stacks;
/* 591 */     float dtheta = 6.2831855F / (slices - 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 599 */     setupRender(); int i;
/* 600 */     for (i = 1; i < stacks; i++) {
/* 601 */       float rho = i * drho;
/*     */       
/* 603 */       class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 604 */       RenderSystem.setShader(class_757::method_34540);
/*     */       
/* 606 */       for (int k = 0; k < slices; k++) {
/* 607 */         float theta = k * dtheta;
/* 608 */         float x = (float)(Math.cos(theta) * Math.sin(rho));
/* 609 */         float y = (float)(Math.sin(theta) * Math.sin(rho));
/* 610 */         float z = (float)Math.cos(rho);
/* 611 */         buffer.method_22918(matrix.method_23760().method_23761(), x * radius, y * radius, z * radius).method_39415(color);
/*     */       } 
/* 613 */       Render2DEngine.endBuilding(buffer);
/*     */     } 
/*     */     
/* 616 */     for (int j = 0; j < slices; j++) {
/* 617 */       float theta = j * dtheta;
/*     */       
/* 619 */       class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/*     */       
/* 621 */       RenderSystem.setShader(class_757::method_34540);
/*     */       
/* 623 */       for (i = 0; i <= stacks; i++) {
/* 624 */         float rho = i * drho;
/* 625 */         float x = (float)(Math.cos(theta) * Math.sin(rho));
/* 626 */         float y = (float)(Math.sin(theta) * Math.sin(rho));
/* 627 */         float z = (float)Math.cos(rho);
/* 628 */         buffer.method_22918(matrix.method_23760().method_23761(), x * radius, y * radius, z * radius).method_39415(color);
/*     */       } 
/* 630 */       class_286.method_43433(buffer.method_60800());
/*     */     } 
/* 632 */     endRender();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawCylinder(class_4587 stack, float radius, float height, int slices, int stacks, int color) {
/* 637 */     float da = (float)(6.283185307179586D / slices);
/* 638 */     float dz = height / stacks;
/*     */     
/* 640 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 641 */     RenderSystem.setShader(class_757::method_34540);
/*     */     
/* 643 */     float y = 0.0F;
/*     */     
/* 645 */     for (int j = 0; j <= stacks; j++) {
/* 646 */       for (int k = 0; k <= slices; k++) {
/* 647 */         float x = (float)Math.cos((k * da));
/* 648 */         float z = (float)Math.sin((k * da));
/* 649 */         buffer.method_22918(stack.method_23760().method_23761(), x * radius, y, z * radius).method_39415(color);
/*     */       } 
/* 651 */       y += dz;
/*     */     } 
/*     */     
/* 654 */     class_286.method_43433(buffer.method_60800());
/*     */     
/* 656 */     buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 657 */     RenderSystem.setShader(class_757::method_34540);
/*     */     
/* 659 */     for (int i = 0; i <= slices; i++) {
/* 660 */       float x = (float)Math.cos((i * da));
/* 661 */       float z = (float)Math.sin((i * da));
/*     */       
/* 663 */       buffer.method_22918(stack.method_23760().method_23761(), x * radius, 0.0F, z * radius).method_39415(color);
/* 664 */       buffer.method_22918(stack.method_23760().method_23761(), x * radius, height, z * radius).method_39415(color);
/*     */     } 
/*     */     
/* 667 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawCircle3D(class_4587 stack, class_1297 ent, float radius, int color, int points, boolean hudColor, int colorOffset) {
/* 672 */     setupRender();
/* 673 */     RenderSystem.setShader(class_757::method_34540);
/* 674 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 675 */     double x = ent.field_6014 + (ent.method_23317() - ent.field_6014) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/* 676 */     double y = ent.field_6036 + (ent.method_23318() - ent.field_6036) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10214();
/* 677 */     double z = ent.field_5969 + (ent.method_23321() - ent.field_5969) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/* 678 */     stack.method_22903();
/* 679 */     stack.method_22904(x, y, z);
/*     */     
/* 681 */     Matrix4f matrix = stack.method_23760().method_23761();
/* 682 */     for (int i = 0; i <= points; i++) {
/* 683 */       if (hudColor) {
/* 684 */         color = HudEditor.getColor(i * colorOffset).getRGB();
/*     */       }
/* 686 */       bufferBuilder.method_22918(matrix, (float)(radius * Math.cos(i * 6.28D / points)), 0.0F, (float)(radius * Math.sin(i * 6.28D / points))).method_39415(color);
/*     */     } 
/*     */     
/* 689 */     class_286.method_43433(bufferBuilder.method_60800());
/* 690 */     endRender();
/* 691 */     stack.method_22904(-x, -y, -z);
/* 692 */     stack.method_22909();
/*     */   }
/*     */   
/*     */   public static void drawOldTargetEsp(class_4587 stack, class_1297 target) {
/* 696 */     double cs = (prevCircleStep + (circleStep - prevCircleStep) * getTickDelta());
/* 697 */     double prevSinAnim = absSinAnimation(cs - 0.44999998807907104D);
/* 698 */     double sinAnim = absSinAnimation(cs);
/* 699 */     double x = target.field_6014 + (target.method_23317() - target.field_6014) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/* 700 */     double y = target.field_6036 + (target.method_23318() - target.field_6036) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10214() + prevSinAnim * target.method_17682();
/* 701 */     double z = target.field_5969 + (target.method_23321() - target.field_5969) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/* 702 */     double nextY = target.field_6036 + (target.method_23318() - target.field_6036) * getTickDelta() - (Module.mc.method_1561()).field_4686.method_19326().method_10214() + sinAnim * target.method_17682();
/* 703 */     stack.method_22903();
/* 704 */     setupRender();
/* 705 */     RenderSystem.disableCull();
/* 706 */     RenderSystem.disableDepthTest();
/* 707 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27380, class_290.field_1576);
/*     */     
/* 709 */     RenderSystem.setShader(class_757::method_34540);
/*     */ 
/*     */     
/* 712 */     for (int i = 0; i <= 30; i++) {
/* 713 */       float cos = (float)(x + Math.cos(i * 6.28D / 30.0D) * target.method_17681() * 0.8D);
/* 714 */       float sin = (float)(z + Math.sin(i * 6.28D / 30.0D) * target.method_17681() * 0.8D);
/* 715 */       bufferBuilder.method_22918(stack.method_23760().method_23761(), cos, (float)nextY, sin).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor(i), 170).getRGB());
/* 716 */       bufferBuilder.method_22918(stack.method_23760().method_23761(), cos, (float)y, sin).method_39415(Render2DEngine.injectAlpha(HudEditor.getColor(i), 0).getRGB());
/*     */     } 
/* 718 */     Render2DEngine.endBuilding(bufferBuilder);
/* 719 */     RenderSystem.enableCull();
/* 720 */     endRender();
/* 721 */     RenderSystem.enableDepthTest();
/* 722 */     stack.method_22909();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderGhosts(int espLength, int factor, float shaking, float amplitude, class_1297 target) {
/* 728 */     class_4184 camera = Module.mc.field_1773.method_19418();
/*     */     
/* 730 */     double tPosX = Render2DEngine.interpolate(target.field_6014, target.method_23317(), getTickDelta()) - (camera.method_19326()).field_1352;
/* 731 */     double tPosY = Render2DEngine.interpolate(target.field_6036, target.method_23318(), getTickDelta()) - (camera.method_19326()).field_1351;
/* 732 */     double tPosZ = Render2DEngine.interpolate(target.field_5969, target.method_23321(), getTickDelta()) - (camera.method_19326()).field_1350;
/* 733 */     float iAge = (float)Render2DEngine.interpolate((target.field_6012 - 1), target.field_6012, getTickDelta());
/*     */     
/* 735 */     RenderSystem.enableBlend();
/* 736 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 737 */     RenderSystem.setShaderTexture(0, TextureStorage.firefly);
/* 738 */     RenderSystem.setShader(class_757::method_34543);
/* 739 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
/*     */     
/* 741 */     boolean canSee = Module.mc.field_1724.method_6057(target);
/*     */     
/* 743 */     if (canSee)
/* 744 */     { RenderSystem.enableDepthTest();
/* 745 */       RenderSystem.depthMask(false); }
/* 746 */     else { RenderSystem.disableDepthTest(); }
/*     */     
/* 748 */     for (int j = 0; j < 3; j++) {
/* 749 */       for (int i = 0; i <= espLength; i++) {
/* 750 */         double radians = Math.toRadians((((i / 1.5F + iAge) * factor + (j * 120)) % (factor * 360)));
/* 751 */         double sinQuad = Math.sin(Math.toRadians((iAge * 2.5F + (i * (j + 1)))) * amplitude) / shaking;
/*     */         
/* 753 */         float offset = i / espLength;
/* 754 */         class_4587 matrices = new class_4587();
/* 755 */         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 756 */         matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 757 */         matrices.method_22904(tPosX + Math.cos(radians) * target.method_17681(), tPosY + 1.0D + sinQuad, tPosZ + Math.sin(radians) * target.method_17681());
/* 758 */         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 759 */         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 760 */         Matrix4f matrix = matrices.method_23760().method_23761();
/* 761 */         int color = Render2DEngine.applyOpacity(HudEditor.getColor((int)(180.0F * offset)), offset).getRGB();
/* 762 */         float scale = Math.max(0.24F * offset, 0.2F);
/* 763 */         buffer.method_22918(matrix, -scale, scale, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
/* 764 */         buffer.method_22918(matrix, scale, scale, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
/* 765 */         buffer.method_22918(matrix, scale, -scale, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
/* 766 */         buffer.method_22918(matrix, -scale, -scale, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
/*     */       } 
/*     */     } 
/*     */     
/* 770 */     class_286.method_43433(buffer.method_60800());
/*     */     
/* 772 */     if (canSee)
/* 773 */     { RenderSystem.depthMask(true);
/* 774 */       RenderSystem.disableDepthTest(); }
/* 775 */     else { RenderSystem.enableDepthTest(); }
/*     */     
/* 777 */     RenderSystem.disableBlend();
/*     */   }
/*     */   
/*     */   public static void updateTargetESP() {
/* 781 */     prevCircleStep = circleStep;
/* 782 */     circleStep += 0.15F;
/*     */   }
/*     */   
/*     */   public static double absSinAnimation(double input) {
/* 786 */     return Math.abs(1.0D + Math.sin(input)) / 2.0D;
/*     */   }
/*     */   
/*     */   public static class_243 interpolatePos(float prevposX, float prevposY, float prevposZ, float posX, float posY, float posZ) {
/* 790 */     double x = (prevposX + (posX - prevposX) * getTickDelta()) - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/* 791 */     double y = (prevposY + (posY - prevposY) * getTickDelta()) - (Module.mc.method_1561()).field_4686.method_19326().method_10214();
/* 792 */     double z = (prevposZ + (posZ - prevposZ) * getTickDelta()) - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/* 793 */     return new class_243(x, y, z);
/*     */   }
/*     */   
/*     */   public static void drawLineDebug(class_243 start, class_243 end, Color color) {
/* 797 */     DEBUG_LINE_QUEUE.add(new DebugLineAction(start, end, color));
/*     */   }
/*     */   
/*     */   public static float getTickDelta() {
/* 801 */     return Module.mc.method_60646().method_60637(true);
/*     */   }
/*     */   public static final class FillAction extends Record { private final class_238 box; private final Color color;
/* 804 */     public FillAction(class_238 box, Color color) { this.box = box; this.color = color; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$FillAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #804	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 804 */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FillAction; } public class_238 box() { return this.box; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$FillAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #804	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FillAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$FillAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #804	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$FillAction;
/* 804 */       //   0	8	1	o	Ljava/lang/Object; } public Color color() { return this.color; }
/*     */      }
/*     */   public static final class OutlineAction extends Record { private final class_238 box; private final Color color; private final float lineWidth;
/* 807 */     public OutlineAction(class_238 box, Color color, float lineWidth) { this.box = box; this.color = color; this.lineWidth = lineWidth; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$OutlineAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #807	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$OutlineAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #807	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$OutlineAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #807	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineAction;
/* 807 */       //   0	8	1	o	Ljava/lang/Object; } public class_238 box() { return this.box; } public Color color() { return this.color; } public float lineWidth() { return this.lineWidth; }
/*     */      }
/*     */   public static final class FadeAction extends Record { private final class_238 box; private final Color color; private final Color color2;
/* 810 */     public FadeAction(class_238 box, Color color, Color color2) { this.box = box; this.color = color; this.color2 = color2; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$FadeAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #810	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FadeAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$FadeAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #810	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FadeAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$FadeAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #810	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$FadeAction;
/* 810 */       //   0	8	1	o	Ljava/lang/Object; } public class_238 box() { return this.box; } public Color color() { return this.color; } public Color color2() { return this.color2; }
/*     */      }
/*     */   public static final class FillSideAction extends Record { private final class_238 box; private final Color color; private final class_2350 side;
/* 813 */     public FillSideAction(class_238 box, Color color, class_2350 side) { this.box = box; this.color = color; this.side = side; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$FillSideAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #813	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FillSideAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$FillSideAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #813	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$FillSideAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$FillSideAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #813	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$FillSideAction;
/* 813 */       //   0	8	1	o	Ljava/lang/Object; } public class_238 box() { return this.box; } public Color color() { return this.color; } public class_2350 side() { return this.side; }
/*     */      }
/*     */   public static final class OutlineSideAction extends Record { private final class_238 box; private final Color color; private final float lineWidth; private final class_2350 side;
/* 816 */     public OutlineSideAction(class_238 box, Color color, float lineWidth, class_2350 side) { this.box = box; this.color = color; this.lineWidth = lineWidth; this.side = side; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #816	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #816	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #816	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$OutlineSideAction;
/* 816 */       //   0	8	1	o	Ljava/lang/Object; } public class_238 box() { return this.box; } public Color color() { return this.color; } public float lineWidth() { return this.lineWidth; } public class_2350 side() { return this.side; }
/*     */      }
/*     */   public static final class DebugLineAction extends Record { private final class_243 start; private final class_243 end; private final Color color;
/* 819 */     public DebugLineAction(class_243 start, class_243 end, Color color) { this.start = start; this.end = end; this.color = color; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$DebugLineAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #819	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$DebugLineAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$DebugLineAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #819	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$DebugLineAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$DebugLineAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #819	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$DebugLineAction;
/* 819 */       //   0	8	1	o	Ljava/lang/Object; } public class_243 start() { return this.start; } public class_243 end() { return this.end; } public Color color() { return this.color; }
/*     */      }
/*     */   public static final class LineAction extends Record { private final class_243 start; private final class_243 end; private final Color color;
/* 822 */     public LineAction(class_243 start, class_243 end, Color color) { this.start = start; this.end = end; this.color = color; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render3DEngine$LineAction;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #822	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$LineAction; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render3DEngine$LineAction;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #822	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render3DEngine$LineAction; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render3DEngine$LineAction;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #822	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render3DEngine$LineAction;
/* 822 */       //   0	8	1	o	Ljava/lang/Object; } public class_243 start() { return this.start; } public class_243 end() { return this.end; } public Color color() { return this.color; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\Render3DEngine.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */