/*     */ package thunder.hack.utility.render;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_1044;
/*     */ import net.minecraft.class_286;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_757;
/*     */ import net.minecraft.class_9801;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Vector4f;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.gui.font.Texture;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.shaders.ArcShader;
/*     */ import thunder.hack.utility.render.shaders.HudShader;
/*     */ import thunder.hack.utility.render.shaders.RectangleShader;
/*     */ 
/*     */ public class Render2DEngine {
/*     */   public static TextureColorProgram TEXTURE_COLOR_PROGRAM;
/*     */   public static HudShader HUD_SHADER;
/*     */   public static RectangleShader RECTANGLE_SHADER;
/*     */   public static MainMenuProgram MAIN_MENU_PROGRAM;
/*     */   public static ArcShader ARC_PROGRAM;
/*     */   public static BlurProgram BLUR_PROGRAM;
/*  39 */   public static HashMap<Integer, BlurredShadow> shadowCache = new HashMap<>();
/*  40 */   public static HashMap<Integer, BlurredShadow> shadowCache1 = new HashMap<>();
/*  41 */   static final Stack<Rectangle> clipStack = new Stack<>();
/*     */   
/*     */   public static void addWindow(class_4587 stack, Rectangle r1) {
/*  44 */     Matrix4f matrix = stack.method_23760().method_23761();
/*  45 */     Vector4f coord = new Vector4f(r1.x, r1.y, 0.0F, 1.0F);
/*  46 */     Vector4f end = new Vector4f(r1.x1, r1.y1, 0.0F, 1.0F);
/*  47 */     coord.mulTranspose((Matrix4fc)matrix);
/*  48 */     end.mulTranspose((Matrix4fc)matrix);
/*  49 */     float x = coord.x();
/*  50 */     float y = coord.y();
/*  51 */     float endX = end.x();
/*  52 */     float endY = end.y();
/*  53 */     Rectangle r = new Rectangle(x, y, endX, endY);
/*  54 */     if (clipStack.empty()) {
/*  55 */       clipStack.push(r);
/*  56 */       beginScissor(r.x, r.y, r.x1, r.y1);
/*     */     } else {
/*  58 */       Rectangle lastClip = clipStack.peek();
/*  59 */       float lsx = lastClip.x;
/*  60 */       float lsy = lastClip.y;
/*  61 */       float lstx = lastClip.x1;
/*  62 */       float lsty = lastClip.y1;
/*  63 */       float nsx = class_3532.method_15363(r.x, lsx, lstx);
/*  64 */       float nsy = class_3532.method_15363(r.y, lsy, lsty);
/*  65 */       float nstx = class_3532.method_15363(r.x1, nsx, lstx);
/*  66 */       float nsty = class_3532.method_15363(r.y1, nsy, lsty);
/*  67 */       clipStack.push(new Rectangle(nsx, nsy, nstx, nsty));
/*  68 */       beginScissor(nsx, nsy, nstx, nsty);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void popWindow() {
/*  73 */     clipStack.pop();
/*  74 */     if (clipStack.empty()) {
/*  75 */       endScissor();
/*     */     } else {
/*  77 */       Rectangle r = clipStack.peek();
/*  78 */       beginScissor(r.x, r.y, r.x1, r.y1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void beginScissor(double x, double y, double endX, double endY) {
/*  83 */     double width = endX - x;
/*  84 */     double height = endY - y;
/*  85 */     width = Math.max(0.0D, width);
/*  86 */     height = Math.max(0.0D, height);
/*  87 */     float d = (float)Render3DEngine.getScaleFactor();
/*  88 */     int ay = (int)((Module.mc.method_22683().method_4502() - y + height) * d);
/*  89 */     RenderSystem.enableScissor((int)(x * d), ay, (int)(width * d), (int)(height * d));
/*     */   }
/*     */   
/*     */   public static void endScissor() {
/*  93 */     RenderSystem.disableScissor();
/*     */   }
/*     */   
/*     */   public static void addWindow(class_4587 stack, float x, float y, float x1, float y1, double animation_factor) {
/*  97 */     float h = y + y1;
/*  98 */     float h2 = (float)(h * (1.0D - MathUtility.clamp(animation_factor, 0.0D, 1.002500057220459D)));
/*     */     
/* 100 */     float x3 = x;
/* 101 */     float y3 = y + h2;
/* 102 */     float x4 = x1;
/* 103 */     float y4 = y1 - h2;
/*     */     
/* 105 */     if (x4 < x3) x4 = x3; 
/* 106 */     if (y4 < y3) y4 = y3; 
/* 107 */     addWindow(stack, new Rectangle(x3, y3, x4, y4));
/*     */   }
/*     */   
/*     */   public static void horizontalGradient(class_4587 matrices, float x1, float y1, float x2, float y2, Color startColor, Color endColor) {
/* 111 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 112 */     setupRender();
/* 113 */     RenderSystem.setShader(class_757::method_34540);
/* 114 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 115 */     buffer.method_22918(matrix, x1, y1, 0.0F).method_39415(startColor.getRGB());
/* 116 */     buffer.method_22918(matrix, x1, y2, 0.0F).method_39415(startColor.getRGB());
/* 117 */     buffer.method_22918(matrix, x2, y2, 0.0F).method_39415(endColor.getRGB());
/* 118 */     buffer.method_22918(matrix, x2, y1, 0.0F).method_39415(endColor.getRGB());
/* 119 */     class_286.method_43433(buffer.method_60800());
/* 120 */     endRender();
/*     */   }
/*     */   
/*     */   public static void verticalGradient(class_4587 matrices, float left, float top, float right, float bottom, Color startColor, Color endColor) {
/* 124 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 125 */     setupRender();
/* 126 */     RenderSystem.setShader(class_757::method_34540);
/* 127 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 128 */     buffer.method_22918(matrix, left, top, 0.0F).method_39415(startColor.getRGB());
/* 129 */     buffer.method_22918(matrix, left, bottom, 0.0F).method_39415(endColor.getRGB());
/* 130 */     buffer.method_22918(matrix, right, bottom, 0.0F).method_39415(endColor.getRGB());
/* 131 */     buffer.method_22918(matrix, right, top, 0.0F).method_39415(startColor.getRGB());
/* 132 */     class_286.method_43433(buffer.method_60800());
/* 133 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRect(class_4587 matrices, float x, float y, float width, float height, Color c) {
/* 137 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 138 */     setupRender();
/* 139 */     RenderSystem.setShader(class_757::method_34540);
/* 140 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 141 */     buffer.method_22918(matrix, x, y + height, 0.0F).method_39415(c.getRGB());
/* 142 */     buffer.method_22918(matrix, x + width, y + height, 0.0F).method_39415(c.getRGB());
/* 143 */     buffer.method_22918(matrix, x + width, y, 0.0F).method_39415(c.getRGB());
/* 144 */     buffer.method_22918(matrix, x, y, 0.0F).method_39415(c.getRGB());
/* 145 */     class_286.method_43433(buffer.method_60800());
/* 146 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRectWithOutline(class_4587 matrices, float x, float y, float width, float height, Color c, Color c2) {
/* 150 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 151 */     setupRender();
/* 152 */     RenderSystem.setShader(class_757::method_34540);
/* 153 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 154 */     buffer.method_22918(matrix, x, y + height, 0.0F).method_39415(c.getRGB());
/* 155 */     buffer.method_22918(matrix, x + width, y + height, 0.0F).method_39415(c.getRGB());
/* 156 */     buffer.method_22918(matrix, x + width, y, 0.0F).method_39415(c.getRGB());
/* 157 */     buffer.method_22918(matrix, x, y, 0.0F).method_39415(c.getRGB());
/* 158 */     class_286.method_43433(buffer.method_60800());
/*     */     
/* 160 */     buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 161 */     buffer.method_22918(matrix, x, y + height, 0.0F).method_39415(c2.getRGB());
/* 162 */     buffer.method_22918(matrix, x + width, y + height, 0.0F).method_39415(c2.getRGB());
/* 163 */     buffer.method_22918(matrix, x + width, y, 0.0F).method_39415(c2.getRGB());
/* 164 */     buffer.method_22918(matrix, x, y, 0.0F).method_39415(c2.getRGB());
/* 165 */     buffer.method_22918(matrix, x, y + height, 0.0F).method_39415(c2.getRGB());
/* 166 */     class_286.method_43433(buffer.method_60800());
/* 167 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRectDumbWay(class_4587 matrices, float x, float y, float x1, float y1, Color c1) {
/* 171 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 172 */     setupRender();
/* 173 */     RenderSystem.setShader(class_757::method_34540);
/* 174 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 175 */     buffer.method_22918(matrix, x, y1, 0.0F).method_39415(c1.getRGB());
/* 176 */     buffer.method_22918(matrix, x1, y1, 0.0F).method_39415(c1.getRGB());
/* 177 */     buffer.method_22918(matrix, x1, y, 0.0F).method_39415(c1.getRGB());
/* 178 */     buffer.method_22918(matrix, x, y, 0.0F).method_39415(c1.getRGB());
/* 179 */     class_286.method_43433(buffer.method_60800());
/* 180 */     endRender();
/*     */   }
/*     */   
/*     */   public static void setRectPoints(class_287 bufferBuilder, Matrix4f matrix, float x, float y, float x1, float y1, Color c1, Color c2, Color c3, Color c4) {
/* 184 */     bufferBuilder.method_22918(matrix, x, y1, 0.0F).method_39415(c1.getRGB());
/* 185 */     bufferBuilder.method_22918(matrix, x1, y1, 0.0F).method_39415(c2.getRGB());
/* 186 */     bufferBuilder.method_22918(matrix, x1, y, 0.0F).method_39415(c3.getRGB());
/* 187 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(c4.getRGB());
/*     */   }
/*     */   
/*     */   public static boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
/* 191 */     return (mouseX >= x && mouseX - width <= x && mouseY >= y && mouseY - height <= y);
/*     */   }
/*     */   
/*     */   public static void drawBlurredShadow(class_4587 matrices, float x, float y, float width, float height, int blurRadius, Color color) {
/* 195 */     if (!((Boolean)HudEditor.glow.getValue()).booleanValue())
/* 196 */       return;  width += (blurRadius * 2);
/* 197 */     height += (blurRadius * 2);
/* 198 */     x -= blurRadius;
/* 199 */     y -= blurRadius;
/*     */     
/* 201 */     int identifier = (int)(width * height + width * blurRadius);
/* 202 */     if (shadowCache.containsKey(Integer.valueOf(identifier))) {
/* 203 */       ((BlurredShadow)shadowCache.get(Integer.valueOf(identifier))).bind();
/*     */     } else {
/* 205 */       BufferedImage original = new BufferedImage((int)width, (int)height, 2);
/* 206 */       Graphics g = original.getGraphics();
/* 207 */       g.setColor(new Color(-1));
/* 208 */       g.fillRect(blurRadius, blurRadius, (int)(width - (blurRadius * 2)), (int)(height - (blurRadius * 2)));
/* 209 */       g.dispose();
/* 210 */       GaussianFilter op = new GaussianFilter(blurRadius);
/* 211 */       BufferedImage blurred = op.filter(original, null);
/* 212 */       shadowCache.put(Integer.valueOf(identifier), new BlurredShadow(blurred));
/*     */       
/*     */       return;
/*     */     } 
/* 216 */     setupRender();
/* 217 */     RenderSystem.setShaderColor(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 218 */     renderTexture(matrices, x, y, width, height, 0.0F, 0.0F, width, height, width, height);
/* 219 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawGradientBlurredShadow(class_4587 matrices, float x, float y, float width, float height, int blurRadius, Color color1, Color color2, Color color3, Color color4) {
/* 223 */     if (!((Boolean)HudEditor.glow.getValue()).booleanValue())
/* 224 */       return;  width += (blurRadius * 2);
/* 225 */     height += (blurRadius * 2);
/* 226 */     x -= blurRadius;
/* 227 */     y -= blurRadius;
/*     */     
/* 229 */     int identifier = (int)(width * height + width * blurRadius);
/* 230 */     if (shadowCache.containsKey(Integer.valueOf(identifier))) {
/* 231 */       ((BlurredShadow)shadowCache.get(Integer.valueOf(identifier))).bind();
/*     */     } else {
/* 233 */       BufferedImage original = new BufferedImage((int)width, (int)height, 2);
/* 234 */       Graphics g = original.getGraphics();
/* 235 */       g.setColor(new Color(-1));
/* 236 */       g.fillRect(blurRadius, blurRadius, (int)(width - (blurRadius * 2)), (int)(height - (blurRadius * 2)));
/* 237 */       g.dispose();
/* 238 */       GaussianFilter op = new GaussianFilter(blurRadius);
/* 239 */       BufferedImage blurred = op.filter(original, null);
/* 240 */       shadowCache.put(Integer.valueOf(identifier), new BlurredShadow(blurred));
/*     */       
/*     */       return;
/*     */     } 
/* 244 */     setupRender();
/* 245 */     renderGradientTexture(matrices, x, y, width, height, 0.0F, 0.0F, width, height, width, height, color1, color2, color3, color4);
/* 246 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawGradientBlurredShadow1(class_4587 matrices, float x, float y, float width, float height, int blurRadius, Color color1, Color color2, Color color3, Color color4) {
/* 250 */     if (!((Boolean)HudEditor.glow.getValue()).booleanValue())
/* 251 */       return;  width += (blurRadius * 2);
/* 252 */     height += (blurRadius * 2);
/* 253 */     x -= blurRadius;
/* 254 */     y -= blurRadius;
/*     */     
/* 256 */     int identifier = (int)(width * height + width * blurRadius);
/* 257 */     if (shadowCache1.containsKey(Integer.valueOf(identifier))) {
/* 258 */       ((BlurredShadow)shadowCache1.get(Integer.valueOf(identifier))).bind();
/*     */     } else {
/* 260 */       BufferedImage original = new BufferedImage((int)width, (int)height, 2);
/* 261 */       Graphics g = original.getGraphics();
/* 262 */       g.setColor(new Color(-1));
/* 263 */       g.fillRect(blurRadius, blurRadius, (int)(width - (blurRadius * 2)), (int)(height - (blurRadius * 2)));
/* 264 */       g.dispose();
/* 265 */       BufferedImage blurred = (new GaussianFilter(blurRadius)).filter(original, null);
/*     */       
/* 267 */       BufferedImage black = new BufferedImage((int)width + blurRadius * 2, (int)height + blurRadius * 2, 2);
/* 268 */       Graphics g2 = black.getGraphics();
/* 269 */       g2.setColor(new Color(0));
/* 270 */       g2.fillRect(0, 0, (int)width + blurRadius * 2, (int)height + blurRadius * 2);
/* 271 */       g2.dispose();
/*     */       
/* 273 */       BufferedImage combined = new BufferedImage((int)width, (int)height, 2);
/* 274 */       Graphics g1 = combined.getGraphics();
/* 275 */       g1.drawImage(black, -blurRadius, -blurRadius, null);
/* 276 */       g1.drawImage(blurred, 0, 0, null);
/* 277 */       g1.dispose();
/*     */       
/* 279 */       shadowCache1.put(Integer.valueOf(identifier), new BlurredShadow(combined));
/*     */       
/*     */       return;
/*     */     } 
/* 283 */     setupRender();
/* 284 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 285 */     renderGradientTexture(matrices, x, y, width, height, 0.0F, 0.0F, width, height, width, height, color1, color2, color3, color4);
/* 286 */     endRender();
/*     */   }
/*     */   
/*     */   public static void registerBufferedImageTexture(Texture i, BufferedImage bi) {
/*     */     try {
/* 291 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 292 */       ImageIO.write(bi, "png", baos);
/* 293 */       byte[] bytes = baos.toByteArray();
/* 294 */       registerTexture(i, bytes);
/* 295 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static void registerTexture(Texture i, byte[] content) {
/*     */     try {
/* 301 */       ByteBuffer data = BufferUtils.createByteBuffer(content.length).put(content);
/* 302 */       data.flip();
/* 303 */       class_1043 tex = new class_1043(class_1011.method_4324(data));
/* 304 */       Module.mc.execute(() -> Module.mc.method_1531().method_4616(i.getId(), (class_1044)tex));
/* 305 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderTexture(class_4587 matrices, double x0, double y0, double width, double height, float u, float v, double regionWidth, double regionHeight, double textureWidth, double textureHeight) {
/* 310 */     double x1 = x0 + width;
/* 311 */     double y1 = y0 + height;
/* 312 */     double z = 0.0D;
/* 313 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 314 */     RenderSystem.setShader(class_757::method_34542);
/* 315 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1585);
/* 316 */     buffer.method_22918(matrix, (float)x0, (float)y1, (float)z).method_22913(u / (float)textureWidth, (v + (float)regionHeight) / (float)textureHeight);
/* 317 */     buffer.method_22918(matrix, (float)x1, (float)y1, (float)z).method_22913((u + (float)regionWidth) / (float)textureWidth, (v + (float)regionHeight) / (float)textureHeight);
/* 318 */     buffer.method_22918(matrix, (float)x1, (float)y0, (float)z).method_22913((u + (float)regionWidth) / (float)textureWidth, v / (float)textureHeight);
/* 319 */     buffer.method_22918(matrix, (float)x0, (float)y0, (float)z).method_22913(u / (float)textureWidth, (v + 0.0F) / (float)textureHeight);
/* 320 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */   
/*     */   public static void renderGradientTexture(class_4587 matrices, double x0, double y0, double width, double height, float u, float v, double regionWidth, double regionHeight, double textureWidth, double textureHeight, Color c1, Color c2, Color c3, Color c4) {
/* 324 */     RenderSystem.setShader(class_757::method_34543);
/* 325 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
/* 326 */     renderGradientTextureInternal(buffer, matrices, x0, y0, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight, c1, c2, c3, c4);
/* 327 */     class_286.method_43433(buffer.method_60800());
/*     */   }
/*     */   
/*     */   public static void renderGradientTextureInternal(class_287 buff, class_4587 matrices, double x0, double y0, double width, double height, float u, float v, double regionWidth, double regionHeight, double textureWidth, double textureHeight, Color c1, Color c2, Color c3, Color c4) {
/* 331 */     double x1 = x0 + width;
/* 332 */     double y1 = y0 + height;
/* 333 */     double z = 0.0D;
/* 334 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 335 */     buff.method_22918(matrix, (float)x0, (float)y1, (float)z).method_22913(u / (float)textureWidth, (v + (float)regionHeight) / (float)textureHeight).method_39415(c1.getRGB());
/* 336 */     buff.method_22918(matrix, (float)x1, (float)y1, (float)z).method_22913((u + (float)regionWidth) / (float)textureWidth, (v + (float)regionHeight) / (float)textureHeight).method_39415(c2.getRGB());
/* 337 */     buff.method_22918(matrix, (float)x1, (float)y0, (float)z).method_22913((u + (float)regionWidth) / (float)textureWidth, v / (float)textureHeight).method_39415(c3.getRGB());
/* 338 */     buff.method_22918(matrix, (float)x0, (float)y0, (float)z).method_22913(u / (float)textureWidth, (v + 0.0F) / (float)textureHeight).method_39415(c4.getRGB());
/*     */   }
/*     */   
/*     */   public static void renderRoundedGradientRect(class_4587 matrices, Color color1, Color color2, Color color3, Color color4, float x, float y, float width, float height, float Radius) {
/* 342 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 343 */     RenderSystem.colorMask(false, false, false, true);
/* 344 */     RenderSystem.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 345 */     RenderSystem.clear(16384, false);
/* 346 */     RenderSystem.colorMask(true, true, true, true);
/*     */     
/* 348 */     drawRound(matrices, x, y, width, height, Radius, color1);
/* 349 */     setupRender();
/* 350 */     RenderSystem.blendFunc(772, 773);
/* 351 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27381, class_290.field_1576);
/* 352 */     bufferBuilder.method_22918(matrix, x, y + height, 0.0F).method_39415(color1.getRGB());
/* 353 */     bufferBuilder.method_22918(matrix, x + width, y + height, 0.0F).method_39415(color2.getRGB());
/* 354 */     bufferBuilder.method_22918(matrix, x + width, y, 0.0F).method_39415(color3.getRGB());
/* 355 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(color4.getRGB());
/* 356 */     class_286.method_43433(bufferBuilder.method_60800());
/* 357 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRound(class_4587 matrices, float x, float y, float width, float height, float radius, Color color) {
/* 361 */     renderRoundedQuad(matrices, color, x, y, (width + x), (height + y), radius, 4.0D);
/*     */   }
/*     */   
/*     */   public static void renderRoundedQuad(class_4587 matrices, Color c, double fromX, double fromY, double toX, double toY, double radius, double samples) {
/* 365 */     setupRender();
/* 366 */     RenderSystem.setShader(class_757::method_34540);
/* 367 */     renderRoundedQuadInternal(matrices.method_23760().method_23761(), c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F, fromX, fromY, toX, toY, radius, samples);
/* 368 */     endRender();
/*     */   }
/*     */   
/*     */   public static void renderRoundedQuad2(class_4587 matrices, Color c, Color c2, Color c3, Color c4, double fromX, double fromY, double toX, double toY, double radius) {
/* 372 */     setupRender();
/* 373 */     RenderSystem.setShader(class_757::method_34540);
/* 374 */     renderRoundedQuadInternal2(matrices.method_23760().method_23761(), c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F, c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, c2.getAlpha() / 255.0F, c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, c3.getAlpha() / 255.0F, c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, c4.getAlpha() / 255.0F, fromX, fromY, toX, toY, radius);
/* 375 */     endRender();
/*     */   }
/*     */   
/*     */   public static void renderRoundedQuadInternal(Matrix4f matrix, float cr, float cg, float cb, float ca, double fromX, double fromY, double toX, double toY, double radius, double samples) {
/* 379 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27381, class_290.field_1576);
/* 380 */     double[][] map = { { toX - radius, toY - radius, radius }, { toX - radius, fromY + radius, radius }, { fromX + radius, fromY + radius, radius }, { fromX + radius, toY - radius, radius } };
/* 381 */     for (int i = 0; i < 4; i++) {
/* 382 */       double[] current = map[i];
/* 383 */       double rad = current[2]; double r;
/* 384 */       for (r = i * 90.0D; r < 90.0D + i * 90.0D; r += 90.0D / samples) {
/* 385 */         float f1 = (float)Math.toRadians(r);
/* 386 */         float f2 = (float)(Math.sin(f1) * rad);
/* 387 */         float f3 = (float)(Math.cos(f1) * rad);
/* 388 */         bufferBuilder.method_22918(matrix, (float)current[0] + f2, (float)current[1] + f3, 0.0F).method_22915(cr, cg, cb, ca);
/*     */       } 
/* 390 */       float rad1 = (float)Math.toRadians(90.0D + i * 90.0D);
/* 391 */       float sin = (float)(Math.sin(rad1) * rad);
/* 392 */       float cos = (float)(Math.cos(rad1) * rad);
/* 393 */       bufferBuilder.method_22918(matrix, (float)current[0] + sin, (float)current[1] + cos, 0.0F).method_22915(cr, cg, cb, ca);
/*     */     } 
/* 395 */     class_286.method_43433(bufferBuilder.method_60800());
/*     */   }
/*     */   
/*     */   public static void renderRoundedQuadInternal2(Matrix4f matrix, float cr, float cg, float cb, float ca, float cr1, float cg1, float cb1, float ca1, float cr2, float cg2, float cb2, float ca2, float cr3, float cg3, float cb3, float ca3, double fromX, double fromY, double toX, double toY, double radC1) {
/* 399 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27381, class_290.field_1576);
/*     */     
/* 401 */     double[][] map = { { toX - radC1, toY - radC1, radC1 }, { toX - radC1, fromY + radC1, radC1 }, { fromX + radC1, fromY + radC1, radC1 }, { fromX + radC1, toY - radC1, radC1 } };
/*     */     
/* 403 */     for (int i = 0; i < 4; i++) {
/* 404 */       double[] current = map[i];
/* 405 */       double rad = current[2]; double r;
/* 406 */       for (r = (i * 90); r < (90 + i * 90); r += 10.0D) {
/* 407 */         float rad1 = (float)Math.toRadians(r);
/* 408 */         float sin = (float)(Math.sin(rad1) * rad);
/* 409 */         float cos = (float)(Math.cos(rad1) * rad);
/* 410 */         switch (i) {
/*     */           case 0:
/* 412 */             bufferBuilder.method_22918(matrix, (float)current[0] + sin, (float)current[1] + cos, 0.0F).method_22915(cr1, cg1, cb1, ca1); break;
/*     */           case 1:
/* 414 */             bufferBuilder.method_22918(matrix, (float)current[0] + sin, (float)current[1] + cos, 0.0F).method_22915(cr, cg, cb, ca); break;
/*     */           case 2:
/* 416 */             bufferBuilder.method_22918(matrix, (float)current[0] + sin, (float)current[1] + cos, 0.0F).method_22915(cr2, cg2, cb2, ca2); break;
/*     */           default:
/* 418 */             bufferBuilder.method_22918(matrix, (float)current[0] + sin, (float)current[1] + cos, 0.0F).method_22915(cr3, cg3, cb3, ca3); break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 422 */     class_286.method_43433(bufferBuilder.method_60800());
/*     */   }
/*     */   
/*     */   public static void draw2DGradientRect(class_4587 matrices, float left, float top, float right, float bottom, Color leftBottomColor, Color leftTopColor, Color rightBottomColor, Color rightTopColor) {
/* 426 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 427 */     setupRender();
/* 428 */     RenderSystem.setShader(class_757::method_34540);
/* 429 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 430 */     bufferBuilder.method_22918(matrix, right, top, 0.0F).method_39415(rightTopColor.getRGB());
/* 431 */     bufferBuilder.method_22918(matrix, left, top, 0.0F).method_39415(leftTopColor.getRGB());
/* 432 */     bufferBuilder.method_22918(matrix, left, bottom, 0.0F).method_39415(leftBottomColor.getRGB());
/* 433 */     bufferBuilder.method_22918(matrix, right, bottom, 0.0F).method_39415(rightBottomColor.getRGB());
/* 434 */     class_286.method_43433(bufferBuilder.method_60800());
/* 435 */     endRender();
/*     */   }
/*     */   
/*     */   public static void setupRender() {
/* 439 */     RenderSystem.enableBlend();
/* 440 */     RenderSystem.defaultBlendFunc();
/* 441 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void drawTracerPointer(class_4587 matrices, float x, float y, float size, float tracerWidth, float downHeight, boolean down, boolean glow, int color) {
/* 445 */     switch ((HudEditor.ArrowsStyle)HudEditor.arrowsStyle.getValue()) { case Default:
/* 446 */         drawDefaultArrow(matrices, x, y, size, tracerWidth, downHeight, down, glow, color); break;
/* 447 */       case New: drawNewArrow(matrices, x, y, size + 8.0F, new Color(color));
/*     */         break; }
/*     */   
/*     */   }
/*     */   public static void drawNewArrow(class_4587 matrices, float x, float y, float size, Color color) {
/* 452 */     RenderSystem.setShaderTexture(0, TextureStorage.arrow);
/* 453 */     setupRender();
/* 454 */     RenderSystem.setShaderColor(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 455 */     RenderSystem.disableDepthTest();
/* 456 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 457 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 458 */     RenderSystem.setShader(class_757::method_34542);
/* 459 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1585);
/* 460 */     bufferBuilder.method_22918(matrix, x - size / 2.0F, y + size, 0.0F).method_22913(0.0F, 1.0F);
/* 461 */     bufferBuilder.method_22918(matrix, x + size / 2.0F, y + size, 0.0F).method_22913(1.0F, 1.0F);
/* 462 */     bufferBuilder.method_22918(matrix, x + size / 2.0F, y, 0.0F).method_22913(1.0F, 0.0F);
/* 463 */     bufferBuilder.method_22918(matrix, x - size / 2.0F, y, 0.0F).method_22913(0.0F, 0.0F);
/* 464 */     class_286.method_43433(bufferBuilder.method_60800());
/* 465 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 466 */     RenderSystem.defaultBlendFunc();
/* 467 */     RenderSystem.enableDepthTest();
/* 468 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawDefaultArrow(class_4587 matrices, float x, float y, float size, float tracerWidth, float downHeight, boolean down, boolean glow, int color) {
/* 472 */     if (glow) {
/* 473 */       drawBlurredShadow(matrices, x - size * tracerWidth, y, x + size * tracerWidth - x - size * tracerWidth, size, 10, injectAlpha(new Color(color), 140));
/*     */     }
/* 475 */     matrices.method_22903();
/* 476 */     setupRender();
/* 477 */     Matrix4f matrix = matrices.method_23760().method_23761();
/*     */     
/* 479 */     RenderSystem.setShader(class_757::method_34540);
/* 480 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
/* 481 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(color);
/* 482 */     bufferBuilder.method_22918(matrix, x - size * tracerWidth, y + size, 0.0F).method_39415(color);
/* 483 */     bufferBuilder.method_22918(matrix, x, y + size - downHeight, 0.0F).method_39415(color);
/* 484 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(color);
/* 485 */     color = darker(new Color(color), 0.8F).getRGB();
/* 486 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(color);
/* 487 */     bufferBuilder.method_22918(matrix, x, y + size - downHeight, 0.0F).method_39415(color);
/* 488 */     bufferBuilder.method_22918(matrix, x + size * tracerWidth, y + size, 0.0F).method_39415(color);
/* 489 */     bufferBuilder.method_22918(matrix, x, y, 0.0F).method_39415(color);
/*     */     
/* 491 */     if (down) {
/* 492 */       color = darker(new Color(color), 0.6F).getRGB();
/* 493 */       bufferBuilder.method_22918(matrix, x - size * tracerWidth, y + size, 0.0F).method_39415(color);
/* 494 */       bufferBuilder.method_22918(matrix, x + size * tracerWidth, y + size, 0.0F).method_39415(color);
/* 495 */       bufferBuilder.method_22918(matrix, x, y + size - downHeight, 0.0F).method_39415(color);
/* 496 */       bufferBuilder.method_22918(matrix, x - size * tracerWidth, y + size, 0.0F).method_39415(color);
/*     */     } 
/*     */     
/* 499 */     class_286.method_43433(bufferBuilder.method_60800());
/* 500 */     endRender();
/* 501 */     matrices.method_22909();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endRender() {
/* 506 */     RenderSystem.defaultBlendFunc();
/* 507 */     RenderSystem.disableBlend();
/* 508 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void drawGradientRound(class_4587 ms, float v, float v1, float i, float i1, float v2, Color darker, Color darker1, Color darker2, Color darker3) {
/* 512 */     renderRoundedQuad2(ms, darker, darker1, darker2, darker3, v, v1, (v + i), (v1 + i1), v2);
/*     */   }
/*     */   
/*     */   public static float scrollAnimate(float endPoint, float current, float speed) {
/* 516 */     boolean shouldContinueAnimation = (endPoint > current);
/* 517 */     if (speed < 0.0F) {
/* 518 */       speed = 0.0F;
/* 519 */     } else if (speed > 1.0F) {
/* 520 */       speed = 1.0F;
/*     */     } 
/*     */     
/* 523 */     float dif = Math.max(endPoint, current) - Math.min(endPoint, current);
/* 524 */     float factor = dif * speed;
/* 525 */     return current + (shouldContinueAnimation ? factor : -factor);
/*     */   }
/*     */   
/*     */   public static Color injectAlpha(Color color, int alpha) {
/* 529 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), class_3532.method_15340(alpha, 0, 255));
/*     */   }
/*     */   
/*     */   public static Color TwoColoreffect(Color cl1, Color cl2, double speed, double count) {
/* 533 */     int angle = (int)((System.currentTimeMillis() / speed + count) % 360.0D);
/* 534 */     angle = ((angle >= 180) ? (360 - angle) : angle) * 2;
/* 535 */     return interpolateColorC(cl1, cl2, angle / 360.0F);
/*     */   }
/*     */   
/*     */   public static Color astolfo(boolean clickgui, int yOffset) {
/* 539 */     float speed = clickgui ? 3500.0F : 3000.0F;
/* 540 */     float hue = (float)(System.currentTimeMillis() % (int)speed + yOffset);
/* 541 */     if (hue > speed) {
/* 542 */       hue -= speed;
/*     */     }
/* 544 */     hue /= speed;
/* 545 */     if (hue > 0.5F) {
/* 546 */       hue = 0.5F - hue - 0.5F;
/*     */     }
/* 548 */     hue += 0.5F;
/* 549 */     return Color.getHSBColor(hue, 0.4F, 1.0F);
/*     */   }
/*     */   
/*     */   public static Color rainbow(int delay, float saturation, float brightness) {
/* 553 */     double rainbow = Math.ceil(((float)(System.currentTimeMillis() + delay) / 16.0F));
/* 554 */     rainbow %= 360.0D;
/* 555 */     return Color.getHSBColor((float)(rainbow / 360.0D), saturation, brightness);
/*     */   }
/*     */   
/*     */   public static Color skyRainbow(int speed, int index) {
/* 559 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 560 */     return Color.getHSBColor(((float)((angle %= 360) / 360.0D) < 0.5D) ? -((float)(angle / 360.0D)) : (float)(angle / 360.0D), 0.5F, 1.0F);
/*     */   }
/*     */   
/*     */   public static Color fade(int speed, int index, Color color, float alpha) {
/* 564 */     float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
/* 565 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 566 */     angle = ((angle > 180) ? (360 - angle) : angle) + 180;
/*     */     
/* 568 */     Color colorHSB = new Color(Color.HSBtoRGB(hsb[0], hsb[1], angle / 360.0F));
/*     */     
/* 570 */     return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(0, Math.min(255, (int)(alpha * 255.0F))));
/*     */   }
/*     */   
/*     */   public static Color getAnalogousColor(Color color) {
/* 574 */     float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
/* 575 */     float degree = 0.84F;
/* 576 */     float newHueSubtracted = hsb[0] - degree;
/* 577 */     return new Color(Color.HSBtoRGB(newHueSubtracted, hsb[1], hsb[2]));
/*     */   }
/*     */   
/*     */   public static Color applyOpacity(Color color, float opacity) {
/* 581 */     opacity = Math.min(1.0F, Math.max(0.0F, opacity));
/* 582 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlpha() * opacity));
/*     */   }
/*     */   
/*     */   public static int applyOpacity(int color_int, float opacity) {
/* 586 */     opacity = Math.min(1.0F, Math.max(0.0F, opacity));
/* 587 */     Color color = new Color(color_int);
/* 588 */     return (new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlpha() * opacity))).getRGB();
/*     */   }
/*     */   
/*     */   public static Color darker(Color color, float factor) {
/* 592 */     return new Color(Math.max((int)(color.getRed() * factor), 0), Math.max((int)(color.getGreen() * factor), 0), Math.max((int)(color.getBlue() * factor), 0), color.getAlpha());
/*     */   }
/*     */   
/*     */   public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
/* 596 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 597 */     float hue = angle / 360.0F;
/* 598 */     Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
/* 599 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0F))));
/*     */   }
/*     */   
/*     */   public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
/* 603 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 604 */     angle = ((angle >= 180) ? (360 - angle) : angle) * 2;
/* 605 */     return trueColor ? interpolateColorHue(start, end, angle / 360.0F) : interpolateColorC(start, end, angle / 360.0F);
/*     */   }
/*     */   
/*     */   public static Color interpolateColorC(Color color1, Color color2, float amount) {
/* 609 */     amount = Math.min(1.0F, Math.max(0.0F, amount));
/* 610 */     return new Color(interpolateInt(color1.getRed(), color2.getRed(), amount), interpolateInt(color1.getGreen(), color2.getGreen(), amount), interpolateInt(color1.getBlue(), color2.getBlue(), amount), interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
/*     */   }
/*     */   
/*     */   public static Color interpolateColorHue(Color color1, Color color2, float amount) {
/* 614 */     amount = Math.min(1.0F, Math.max(0.0F, amount));
/*     */     
/* 616 */     float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
/* 617 */     float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
/*     */     
/* 619 */     Color resultColor = Color.getHSBColor(interpolateFloat(color1HSB[0], color2HSB[0], amount), interpolateFloat(color1HSB[1], color2HSB[1], amount), interpolateFloat(color1HSB[2], color2HSB[2], amount));
/*     */     
/* 621 */     return new Color(resultColor.getRed(), resultColor.getGreen(), resultColor.getBlue(), interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
/*     */   }
/*     */   
/*     */   public static double interpolate(double oldValue, double newValue, double interpolationValue) {
/* 625 */     return oldValue + (newValue - oldValue) * interpolationValue;
/*     */   }
/*     */   
/*     */   public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
/* 629 */     return (float)interpolate(oldValue, newValue, (float)interpolationValue);
/*     */   }
/*     */   
/*     */   public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
/* 633 */     return (int)interpolate(oldValue, newValue, (float)interpolationValue);
/*     */   }
/*     */   
/*     */   public static void drawArc(class_4587 matrices, float x, float y, float width, float height, float radius, float thickness, float start, float end, Color c1, Color c2) {
/* 637 */     class_287 bb = preShaderDraw(matrices, x - width / 2.0F, y - height / 2.0F, x + width / 2.0F, y + height / 2.0F);
/* 638 */     ARC_PROGRAM.setParameters(x, y, width, height, radius, thickness, start, end, c1, c2);
/* 639 */     ARC_PROGRAM.use();
/* 640 */     class_286.method_43433(bb.method_60800());
/* 641 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRect(class_4587 matrices, float x, float y, float width, float height, float radius, float alpha) {
/* 645 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 646 */     RECTANGLE_SHADER.setParameters(x, y, width, height, radius, alpha);
/* 647 */     RECTANGLE_SHADER.use();
/* 648 */     class_286.method_43433(bb.method_60800());
/* 649 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRect(class_4587 matrices, float x, float y, float width, float height, float radius, float alpha, Color c1, Color c2, Color c3, Color c4) {
/* 653 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 654 */     RECTANGLE_SHADER.setParameters(x, y, width, height, radius, alpha, c1, c2, c3, c4);
/* 655 */     RECTANGLE_SHADER.use();
/* 656 */     class_286.method_43433(bb.method_60800());
/* 657 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawHudBase(class_4587 matrices, float x, float y, float width, float height, float radius) {
/* 661 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 662 */       drawRoundedBlur(matrices, x, y, width, height, radius, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/*     */     } else {
/* 664 */       class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 665 */       HUD_SHADER.setParameters(x, y, width, height, radius, ((Float)HudEditor.alpha.getValue()).floatValue(), ((Float)HudEditor.alpha.getValue()).floatValue());
/* 666 */       HUD_SHADER.use();
/* 667 */       class_286.method_43433(bb.method_60800());
/* 668 */       endRender();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawHudBase2(class_4587 matrices, float x, float y, float width, float height, float radius, float blurStrenth, float blurOpacity, float animationFactor) {
/* 673 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 674 */       blurStrenth *= animationFactor;
/* 675 */       blurOpacity = (float)interpolate(1.0D, blurOpacity, animationFactor);
/* 676 */       Color c = interpolateColorC(Color.BLACK, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject(), animationFactor);
/* 677 */       drawRoundedBlur(matrices, x, y, width, height, radius, c, blurStrenth, blurOpacity);
/*     */     } else {
/* 679 */       class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 680 */       HUD_SHADER.setParameters(x, y, width, height, radius, ((Float)HudEditor.alpha.getValue()).floatValue(), ((Float)HudEditor.alpha.getValue()).floatValue());
/* 681 */       HUD_SHADER.use();
/* 682 */       class_286.method_43433(bb.method_60800());
/* 683 */       endRender();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawHudBase(class_4587 matrices, float x, float y, float width, float height, float radius, boolean hud) {
/* 688 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 689 */     HUD_SHADER.setParameters(x, y, width, height, radius, ((Float)HudEditor.alpha.getValue()).floatValue(), ((Float)HudEditor.alpha.getValue()).floatValue());
/* 690 */     HUD_SHADER.use();
/* 691 */     class_286.method_43433(bb.method_60800());
/* 692 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawRoundedBlur(class_4587 matrices, float x, float y, float width, float height, float radius, Color c1) {
/* 696 */     drawRoundedBlur(matrices, x, y, width, height, radius, c1, ((Float)HudEditor.blurStrength.getValue()).floatValue(), ((Float)HudEditor.blurOpacity.getValue()).floatValue());
/*     */   }
/*     */   
/*     */   public static void drawRoundedBlur(class_4587 matrices, float x, float y, float width, float height, float radius, Color c1, float blurStrenth, float blurOpacity) {
/* 700 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 701 */     BLUR_PROGRAM.setParameters(x, y, width, height, radius, c1, blurStrenth, blurOpacity);
/* 702 */     BLUR_PROGRAM.use();
/* 703 */     class_286.method_43433(bb.method_60800());
/* 704 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawHudBase(class_4587 matrices, float x, float y, float width, float height, float radius, float alpha) {
/* 708 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 709 */     HUD_SHADER.setParameters(x, y, width, height, radius, alpha, ((Float)HudEditor.alpha.getValue()).floatValue());
/* 710 */     HUD_SHADER.use();
/* 711 */     class_286.method_43433(bb.method_60800());
/* 712 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawGuiBase(class_4587 matrices, float x, float y, float width, float height, float radius, float opacity) {
/* 716 */     class_287 bb = preShaderDraw(matrices, x - 10.0F, y - 10.0F, width + 20.0F, height + 20.0F);
/* 717 */     HUD_SHADER.setParameters(x, y, width, height, radius, 1.0F, opacity);
/* 718 */     HUD_SHADER.use();
/* 719 */     class_286.method_43433(bb.method_60800());
/* 720 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawMainMenuShader(class_4587 matrices, float x, float y, float width, float height) {
/* 724 */     class_287 bb = preShaderDraw(matrices, x, y, width, height);
/* 725 */     MAIN_MENU_PROGRAM.setParameters(x, y, width, height);
/* 726 */     MAIN_MENU_PROGRAM.use();
/* 727 */     class_286.method_43433(bb.method_60800());
/* 728 */     endRender();
/*     */   }
/*     */   
/*     */   public static class_287 preShaderDraw(class_4587 matrices, float x, float y, float width, float height) {
/* 732 */     setupRender();
/* 733 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 734 */     class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
/* 735 */     setRectanglePoints(buffer, matrix, x, y, x + width, y + height);
/* 736 */     return buffer;
/*     */   }
/*     */   
/*     */   public static void setRectanglePoints(class_287 buffer, Matrix4f matrix, float x, float y, float x1, float y1) {
/* 740 */     buffer.method_22918(matrix, x, y, 0.0F);
/* 741 */     buffer.method_22918(matrix, x, y1, 0.0F);
/* 742 */     buffer.method_22918(matrix, x1, y1, 0.0F);
/* 743 */     buffer.method_22918(matrix, x1, y, 0.0F);
/*     */   }
/*     */   
/*     */   public static void drawOrbiz(class_4587 matrices, float z, double r, Color c) {
/* 747 */     Matrix4f matrix = matrices.method_23760().method_23761();
/* 748 */     setupRender();
/* 749 */     RenderSystem.setShader(class_757::method_34540);
/* 750 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_27381, class_290.field_1576);
/* 751 */     for (int i = 0; i <= 20; i++) {
/* 752 */       float x2 = (float)(Math.sin((i * 56.548656F / 180.0F)) * r);
/* 753 */       float y2 = (float)(Math.cos((i * 56.548656F / 180.0F)) * r);
/* 754 */       bufferBuilder.method_22918(matrix, x2, y2, z).method_22915(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.4F);
/*     */     } 
/* 756 */     class_286.method_43433(bufferBuilder.method_60800());
/* 757 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawStar(class_4587 matrices, Color c, float scale) {
/* 761 */     setupRender();
/* 762 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 763 */     RenderSystem.setShaderTexture(0, TextureStorage.star);
/* 764 */     RenderSystem.setShaderColor(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
/* 765 */     renderGradientTexture(matrices, 0.0D, 0.0D, scale, scale, 0.0F, 0.0F, 128.0D, 128.0D, 128.0D, 128.0D, c, c, c, c);
/* 766 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawHeart(class_4587 matrices, Color c, float scale) {
/* 770 */     setupRender();
/* 771 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 772 */     RenderSystem.setShaderTexture(0, TextureStorage.heart);
/* 773 */     RenderSystem.setShaderColor(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
/* 774 */     renderGradientTexture(matrices, 0.0D, 0.0D, scale, scale, 0.0F, 0.0F, 128.0D, 128.0D, 128.0D, 128.0D, c, c, c, c);
/* 775 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawBloom(class_4587 matrices, Color c, float scale) {
/* 779 */     setupRender();
/* 780 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 781 */     RenderSystem.setShaderTexture(0, TextureStorage.firefly);
/* 782 */     RenderSystem.setShaderColor(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
/* 783 */     renderGradientTexture(matrices, 0.0D, 0.0D, scale, scale, 0.0F, 0.0F, 128.0D, 128.0D, 128.0D, 128.0D, c, c, c, c);
/* 784 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawBubble(class_4587 matrices, float angle, float factor) {
/* 788 */     setupRender();
/* 789 */     RenderSystem.blendFunc(GlStateManager.class_4535.SRC_ALPHA, GlStateManager.class_4534.ONE);
/* 790 */     RenderSystem.setShaderTexture(0, TextureStorage.bubble);
/* 791 */     matrices.method_22907(class_7833.field_40718.rotationDegrees(angle));
/* 792 */     float scale = factor * 2.0F;
/* 793 */     renderGradientTexture(matrices, (-scale / 2.0F), (-scale / 2.0F), scale, scale, 0.0F, 0.0F, 128.0D, 128.0D, 128.0D, 128.0D, applyOpacity(HudEditor.getColor(270), 1.0F - factor), applyOpacity(HudEditor.getColor(0), 1.0F - factor), applyOpacity(HudEditor.getColor(180), 1.0F - factor), applyOpacity(HudEditor.getColor(90), 1.0F - factor));
/* 794 */     endRender();
/*     */   }
/*     */   
/*     */   public static void drawLine(float x, float y, float x1, float y1, int color) {
/* 798 */     RenderSystem.setShader(class_757::method_34540);
/* 799 */     class_287 bufferBuilder = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
/* 800 */     bufferBuilder.method_22912(x, y, 0.0F).method_39415(color);
/* 801 */     bufferBuilder.method_22912(x1, y1, 0.0F).method_39415(color);
/* 802 */     class_286.method_43433(bufferBuilder.method_60800());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDark(Color color) {
/* 807 */     return isDark(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F);
/*     */   }
/*     */   
/*     */   public static boolean isDark(float r, float g, float b) {
/* 811 */     return (colorDistance(r, g, b, 0.0F, 0.0F, 0.0F) < colorDistance(r, g, b, 1.0F, 1.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public static float colorDistance(float r1, float g1, float b1, float r2, float g2, float b2) {
/* 815 */     float a = r2 - r1;
/* 816 */     float b = g2 - g1;
/* 817 */     float c = b2 - b1;
/* 818 */     return (float)Math.sqrt((a * a + b * b + c * c));
/*     */   }
/*     */   
/*     */   public static void initShaders() {
/* 822 */     HUD_SHADER = new HudShader();
/* 823 */     MAIN_MENU_PROGRAM = new MainMenuProgram();
/* 824 */     TEXTURE_COLOR_PROGRAM = new TextureColorProgram();
/* 825 */     ARC_PROGRAM = new ArcShader();
/* 826 */     RECTANGLE_SHADER = new RectangleShader();
/* 827 */     BLUR_PROGRAM = new BlurProgram();
/*     */   }
/*     */   @NotNull
/*     */   public static Color getColor(@NotNull Color start, @NotNull Color end, float progress, boolean smooth) {
/* 831 */     if (!smooth) {
/* 832 */       return (progress >= 0.95D) ? end : start;
/*     */     }
/* 834 */     int rDiff = end.getRed() - start.getRed();
/* 835 */     int gDiff = end.getGreen() - start.getGreen();
/* 836 */     int bDiff = end.getBlue() - start.getBlue();
/* 837 */     int aDiff = end.getAlpha() - start.getAlpha();
/*     */     
/* 839 */     return new Color(
/* 840 */         fixColorValue(start.getRed() + (int)(rDiff * progress)), 
/* 841 */         fixColorValue(start.getGreen() + (int)(gDiff * progress)), 
/* 842 */         fixColorValue(start.getBlue() + (int)(bDiff * progress)), 
/* 843 */         fixColorValue(start.getAlpha() + (int)(aDiff * progress)));
/*     */   }
/*     */   
/*     */   private static int fixColorValue(int colorVal) {
/* 847 */     return (colorVal > 255) ? 255 : Math.max(colorVal, 0);
/*     */   }
/*     */   
/*     */   public static void endBuilding(class_287 bb) {
/* 851 */     class_9801 builtBuffer = bb.method_60794();
/* 852 */     if (builtBuffer != null)
/* 853 */       class_286.method_43433(builtBuffer); 
/*     */   }
/*     */   
/*     */   public static class BlurredShadow {
/*     */     Texture id;
/*     */     
/*     */     public BlurredShadow(BufferedImage bufferedImage) {
/* 860 */       this.id = new Texture("texture/remote/" + RandomStringUtils.randomAlphanumeric(16));
/* 861 */       Render2DEngine.registerBufferedImageTexture(this.id, bufferedImage);
/*     */     }
/*     */     
/*     */     public void bind() {
/* 865 */       RenderSystem.setShaderTexture(0, this.id.getId());
/*     */     } }
/*     */   public static final class Rectangle extends Record { private final float x; private final float y; private final float x1; private final float y1;
/*     */     
/* 869 */     public Rectangle(float x, float y, float x1, float y1) { this.x = x; this.y = y; this.x1 = x1; this.y1 = y1; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/Render2DEngine$Rectangle;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #869	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 869 */       //   0	7	0	this	Lthunder/hack/utility/render/Render2DEngine$Rectangle; } public float x() { return this.x; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/Render2DEngine$Rectangle;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #869	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/utility/render/Render2DEngine$Rectangle; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/Render2DEngine$Rectangle;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #869	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/utility/render/Render2DEngine$Rectangle;
/* 869 */       //   0	8	1	o	Ljava/lang/Object; } public float y() { return this.y; } public float x1() { return this.x1; } public float y1() { return this.y1; }
/*     */      public boolean contains(double x, double y) {
/* 871 */       return (x >= this.x && x <= this.x1 && y >= this.y && y <= this.y1);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\Render2DEngine.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */