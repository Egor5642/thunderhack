/*     */ package thunder.hack.gui.font;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import it.unimi.dsi.fastutil.chars.Char2IntArrayMap;
/*     */ import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.io.Closeable;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_289;
/*     */ import net.minecraft.class_293;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_757;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.joml.Matrix4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ public class FontRenderer implements Closeable {
/*  37 */   private static final Char2IntArrayMap colorCodes = new Char2IntArrayMap()
/*     */     {
/*     */     
/*     */     };
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
/*  56 */   private static final ExecutorService ASYNC_WORKER = Executors.newCachedThreadPool();
/*  57 */   private final Object2ObjectMap<class_2960, ObjectList<DrawEntry>> GLYPH_PAGE_CACHE = (Object2ObjectMap<class_2960, ObjectList<DrawEntry>>)new Object2ObjectOpenHashMap();
/*     */   private final float originalSize;
/*  59 */   private final ObjectList<GlyphMap> maps = (ObjectList<GlyphMap>)new ObjectArrayList();
/*  60 */   private final Char2ObjectArrayMap<Glyph> allGlyphs = new Char2ObjectArrayMap();
/*     */   private final int charsPerPage;
/*     */   private final int padding;
/*     */   private final String prebakeGlyphs;
/*  64 */   private int scaleMul = 0;
/*     */   private Font font;
/*  66 */   private int previousGameScale = -1;
/*     */   private Future<Void> prebakeGlyphsFuture;
/*     */   private boolean initialized;
/*     */   
/*     */   public FontRenderer(Font font, float sizePx, int charactersPerPage, int paddingBetweenCharacters, @Nullable String prebakeCharacters) {
/*  71 */     this.originalSize = sizePx;
/*  72 */     this.charsPerPage = charactersPerPage;
/*  73 */     this.padding = paddingBetweenCharacters;
/*  74 */     this.prebakeGlyphs = prebakeCharacters;
/*  75 */     init(font, sizePx);
/*     */   }
/*     */   
/*     */   public FontRenderer(Font font, float sizePx) {
/*  79 */     this(font, sizePx, 256, 5, null);
/*     */   }
/*     */   
/*     */   private static int floorNearestMulN(int x, int n) {
/*  83 */     return n * (int)Math.floor(x / n);
/*     */   }
/*     */   
/*     */   public static String stripControlCodes(String text) {
/*  87 */     char[] chars = text.toCharArray();
/*  88 */     StringBuilder f = new StringBuilder();
/*  89 */     for (int i = 0; i < chars.length; i++) {
/*  90 */       char c = chars[i];
/*  91 */       if (c == '§') {
/*  92 */         i++;
/*     */       } else {
/*     */         
/*  95 */         f.append(c);
/*     */       } 
/*  97 */     }  return f.toString();
/*     */   }
/*     */   
/*     */   private void sizeCheck() {
/* 101 */     int gs = (int)IManager.mc.method_22683().method_4495();
/* 102 */     if (gs != this.previousGameScale) {
/* 103 */       close();
/* 104 */       init(this.font, this.originalSize);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void init(Font font, float sizePx) {
/* 109 */     if (this.initialized) throw new IllegalStateException("Double call to init()"); 
/* 110 */     this.initialized = true;
/* 111 */     this.previousGameScale = (int)IManager.mc.method_22683().method_4495();
/* 112 */     this.scaleMul = this.previousGameScale;
/* 113 */     this.font = font.deriveFont(sizePx * this.scaleMul);
/* 114 */     if (this.prebakeGlyphs != null && !this.prebakeGlyphs.isEmpty()) {
/* 115 */       this.prebakeGlyphsFuture = prebake();
/*     */     }
/*     */   }
/*     */   
/*     */   private Future<Void> prebake() {
/* 120 */     return ASYNC_WORKER.submit(() -> {
/*     */           for (char c : this.prebakeGlyphs.toCharArray()) {
/*     */             if (Thread.interrupted())
/*     */               break; 
/*     */             locateGlyph1(c);
/*     */           } 
/*     */           return null;
/*     */         });
/*     */   }
/*     */   private GlyphMap generateMap(char from, char to) {
/* 130 */     GlyphMap gm = new GlyphMap(from, to, this.font, randomIdentifier(), this.padding);
/* 131 */     this.maps.add(gm);
/* 132 */     return gm;
/*     */   }
/*     */   
/*     */   private Glyph locateGlyph0(char glyph) {
/* 136 */     for (ObjectListIterator<GlyphMap> objectListIterator = this.maps.iterator(); objectListIterator.hasNext(); ) { GlyphMap map = objectListIterator.next();
/* 137 */       if (map.contains(glyph)) {
/* 138 */         return map.getGlyph(glyph);
/*     */       } }
/*     */     
/* 141 */     int base = floorNearestMulN(glyph, this.charsPerPage);
/* 142 */     GlyphMap glyphMap = generateMap((char)base, (char)(base + this.charsPerPage));
/* 143 */     return glyphMap.getGlyph(glyph);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Glyph locateGlyph1(char glyph) {
/* 148 */     return (Glyph)this.allGlyphs.computeIfAbsent(glyph, this::locateGlyph0);
/*     */   }
/*     */   
/*     */   public void drawString(class_4587 stack, String s, double x, double y, int color) {
/* 152 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 153 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 154 */     float b = (color & 0xFF) / 255.0F;
/* 155 */     float a = (color >> 24 & 0xFF) / 255.0F;
/* 156 */     drawString(stack, s, (float)x, (float)y, r, g, b, a);
/*     */   }
/*     */   
/*     */   public void drawString(class_4587 stack, String s, double x, double y, Color color) {
/* 160 */     drawString(stack, s, (float)x, (float)y, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha());
/*     */   }
/*     */   
/*     */   public void drawString(class_4587 stack, String s, float x, float y, float r, float g, float b, float a) {
/* 164 */     drawString(stack, s, x, y, r, g, b, a, false, 0);
/*     */   }
/*     */   
/*     */   public void drawString(class_4587 stack, String s, float x, float y, float r, float g, float b, float a, boolean gradient, int offset) {
/* 168 */     if (this.prebakeGlyphsFuture != null && !this.prebakeGlyphsFuture.isDone()) {
/*     */       try {
/* 170 */         this.prebakeGlyphsFuture.get();
/* 171 */       } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {}
/*     */     }
/*     */ 
/*     */     
/* 175 */     sizeCheck();
/* 176 */     float r2 = r, g2 = g, b2 = b;
/* 177 */     stack.method_22903();
/* 178 */     y -= 3.0F;
/* 179 */     stack.method_22904(MathUtility.roundToDecimal(x, 1), MathUtility.roundToDecimal(y, 1), 0.0D);
/* 180 */     stack.method_22905(1.0F / this.scaleMul, 1.0F / this.scaleMul, 1.0F);
/*     */     
/* 182 */     RenderSystem.enableBlend();
/* 183 */     RenderSystem.defaultBlendFunc();
/* 184 */     RenderSystem.disableCull();
/* 185 */     GL11.glTexParameteri(3553, 10241, 9729);
/* 186 */     GL11.glTexParameteri(3553, 10240, 9729);
/*     */     
/* 188 */     RenderSystem.setShader(class_757::method_34543);
/*     */     
/* 190 */     Matrix4f mat = stack.method_23760().method_23761();
/* 191 */     char[] chars = s.toCharArray();
/* 192 */     float xOffset = 0.0F;
/* 193 */     float yOffset = 0.0F;
/* 194 */     boolean inSel = false;
/* 195 */     int lineStart = 0;
/* 196 */     synchronized (this.GLYPH_PAGE_CACHE) {
/* 197 */       for (int i = 0; i < chars.length; i++) {
/* 198 */         char c = chars[i];
/* 199 */         if (inSel) {
/* 200 */           inSel = false;
/* 201 */           char c1 = Character.toUpperCase(c);
/* 202 */           if (colorCodes.containsKey(c1)) {
/* 203 */             int ii = colorCodes.get(c1);
/* 204 */             int[] col = RGBIntToRGB(ii);
/* 205 */             r2 = col[0] / 255.0F;
/* 206 */             g2 = col[1] / 255.0F;
/* 207 */             b2 = col[2] / 255.0F;
/* 208 */           } else if (c1 == 'R') {
/* 209 */             r2 = r;
/* 210 */             g2 = g;
/* 211 */             b2 = b;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 216 */           if (gradient) {
/* 217 */             Color color = HudEditor.getColor(i * offset);
/* 218 */             r2 = color.getRed() / 255.0F;
/* 219 */             g2 = color.getGreen() / 255.0F;
/* 220 */             b2 = color.getBlue() / 255.0F;
/* 221 */             a = color.getAlpha() / 255.0F;
/*     */           } 
/*     */           
/* 224 */           if (c == '§') {
/* 225 */             inSel = true;
/*     */           }
/* 227 */           else if (c == '\n') {
/* 228 */             yOffset += getStringHeight(s.substring(lineStart, i)) * this.scaleMul;
/* 229 */             xOffset = 0.0F;
/* 230 */             lineStart = i + 1;
/*     */           } else {
/*     */             
/* 233 */             Glyph glyph = locateGlyph1(c);
/* 234 */             if (glyph != null)
/* 235 */             { if (glyph.value() != ' ') {
/* 236 */                 class_2960 i1 = (glyph.owner()).bindToTexture;
/* 237 */                 DrawEntry entry = new DrawEntry(xOffset, yOffset, r2, g2, b2, glyph);
/* 238 */                 ((ObjectList)this.GLYPH_PAGE_CACHE.computeIfAbsent(i1, integer -> new ObjectArrayList())).add(entry);
/*     */               } 
/* 240 */               xOffset += glyph.width(); } 
/*     */           } 
/*     */         } 
/* 243 */       }  for (ObjectIterator<class_2960> objectIterator = this.GLYPH_PAGE_CACHE.keySet().iterator(); objectIterator.hasNext(); ) { class_2960 identifier = objectIterator.next();
/* 244 */         RenderSystem.setShaderTexture(0, identifier);
/* 245 */         List<DrawEntry> objects = (List<DrawEntry>)this.GLYPH_PAGE_CACHE.get(identifier);
/*     */         
/* 247 */         class_287 bb = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
/*     */         
/* 249 */         for (DrawEntry object : objects) {
/* 250 */           float xo = object.atX;
/* 251 */           float yo = object.atY;
/* 252 */           float cr = object.r;
/* 253 */           float cg = object.g;
/* 254 */           float cb = object.b;
/* 255 */           Glyph glyph = object.toDraw;
/* 256 */           GlyphMap owner = glyph.owner();
/* 257 */           float w = glyph.width();
/* 258 */           float h = glyph.height();
/* 259 */           float u1 = glyph.u() / owner.width;
/* 260 */           float v1 = glyph.v() / owner.height;
/* 261 */           float u2 = (glyph.u() + glyph.width()) / owner.width;
/* 262 */           float v2 = (glyph.v() + glyph.height()) / owner.height;
/*     */           
/* 264 */           bb.method_22918(mat, xo + 0.0F, yo + h, 0.0F).method_22913(u1, v2).method_22915(cr, cg, cb, a);
/* 265 */           bb.method_22918(mat, xo + w, yo + h, 0.0F).method_22913(u2, v2).method_22915(cr, cg, cb, a);
/* 266 */           bb.method_22918(mat, xo + w, yo + 0.0F, 0.0F).method_22913(u2, v1).method_22915(cr, cg, cb, a);
/* 267 */           bb.method_22918(mat, xo + 0.0F, yo + 0.0F, 0.0F).method_22913(u1, v1).method_22915(cr, cg, cb, a);
/*     */         } 
/* 269 */         Render2DEngine.endBuilding(bb); }
/*     */ 
/*     */       
/* 272 */       this.GLYPH_PAGE_CACHE.clear();
/*     */     } 
/* 274 */     stack.method_22909();
/*     */   }
/*     */   
/*     */   public void drawCenteredString(class_4587 stack, String s, double x, double y, int color) {
/* 278 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 279 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 280 */     float b = (color & 0xFF) / 255.0F;
/* 281 */     float a = (color >> 24 & 0xFF) / 255.0F;
/* 282 */     drawString(stack, s, (float)(x - (getStringWidth(s) / 2.0F)), (float)y, r, g, b, a);
/*     */   }
/*     */   
/*     */   public void drawCenteredString(class_4587 stack, String s, double x, double y, Color color) {
/* 286 */     drawString(stack, s, (float)(x - (getStringWidth(s) / 2.0F)), (float)y, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*     */   }
/*     */   
/*     */   public void drawCenteredString(class_4587 stack, String s, float x, float y, float r, float g, float b, float a) {
/* 290 */     drawString(stack, s, x - getStringWidth(s) / 2.0F, y, r, g, b, a);
/*     */   }
/*     */   
/*     */   public float getStringWidth(String text) {
/* 294 */     char[] c = stripControlCodes(text).toCharArray();
/* 295 */     float currentLine = 0.0F;
/* 296 */     float maxPreviousLines = 0.0F;
/* 297 */     for (char c1 : c) {
/* 298 */       if (c1 == '\n') {
/* 299 */         maxPreviousLines = Math.max(currentLine, maxPreviousLines);
/* 300 */         currentLine = 0.0F;
/*     */       } else {
/*     */         
/* 303 */         Glyph glyph = locateGlyph1(c1);
/* 304 */         currentLine += (glyph == null) ? 0.0F : (glyph.width() / this.scaleMul);
/*     */       } 
/* 306 */     }  return Math.max(currentLine, maxPreviousLines);
/*     */   }
/*     */   
/*     */   public float getStringHeight(String text) {
/* 310 */     char[] c = stripControlCodes(text).toCharArray();
/* 311 */     if (c.length == 0) {
/* 312 */       c = new char[] { ' ' };
/*     */     }
/* 314 */     float currentLine = 0.0F;
/* 315 */     float previous = 0.0F;
/* 316 */     for (char c1 : c) {
/* 317 */       if (c1 == '\n') {
/* 318 */         if (currentLine == 0.0F) {
/* 319 */           currentLine = (locateGlyph1(' ') == null) ? 0.0F : (((Glyph)Objects.<Glyph>requireNonNull(locateGlyph1(' '))).height() / this.scaleMul);
/*     */         }
/* 321 */         previous += currentLine;
/* 322 */         currentLine = 0.0F;
/*     */       } else {
/*     */         
/* 325 */         Glyph glyph = locateGlyph1(c1);
/* 326 */         currentLine = Math.max(
/*     */             
/* 328 */             (glyph == null) ? 0.0F : (glyph.height() / this.scaleMul), currentLine);
/*     */       } 
/*     */     } 
/*     */     
/* 332 */     return currentLine + previous;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/* 339 */       if (this.prebakeGlyphsFuture != null && !this.prebakeGlyphsFuture.isDone() && !this.prebakeGlyphsFuture.isCancelled()) {
/* 340 */         this.prebakeGlyphsFuture.cancel(true);
/* 341 */         this.prebakeGlyphsFuture.get();
/* 342 */         this.prebakeGlyphsFuture = null;
/*     */       } 
/* 344 */       for (ObjectListIterator<GlyphMap> objectListIterator = this.maps.iterator(); objectListIterator.hasNext(); ) { GlyphMap map = objectListIterator.next();
/* 345 */         map.destroy(); }
/*     */       
/* 347 */       this.maps.clear();
/* 348 */       this.allGlyphs.clear();
/* 349 */       this.initialized = false;
/* 350 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @Contract(value = "-> new", pure = true)
/*     */   @NotNull
/*     */   public static class_2960 randomIdentifier() {
/* 356 */     return class_2960.method_60655("thunderhack", "temp/" + randomString());
/*     */   }
/*     */   
/*     */   private static String randomString() {
/* 360 */     return IntStream.range(0, 32).<CharSequence>mapToObj(operand -> String.valueOf((char)(new Random()).nextInt(97, 123))).collect(Collectors.joining());
/*     */   }
/*     */   
/*     */   @Contract(value = "_ -> new", pure = true)
/*     */   public static int[] RGBIntToRGB(int in) {
/* 365 */     int red = in >> 16 & 0xFF;
/* 366 */     int green = in >> 8 & 0xFF;
/* 367 */     int blue = in & 0xFF;
/* 368 */     return new int[] { red, green, blue };
/*     */   }
/*     */   
/*     */   public float getFontHeight(String str) {
/* 372 */     return getStringHeight(str);
/*     */   }
/*     */   
/*     */   public void drawGradientString(class_4587 stack, String s, float x, float y, int offset) {
/* 376 */     drawString(stack, s, x, y, 255.0F, 255.0F, 255.0F, 255.0F, true, offset);
/*     */   }
/*     */   
/*     */   public void drawGradientCenteredString(class_4587 matrices, String s, float x, float y, int i) {
/* 380 */     drawGradientString(matrices, s, x - getStringWidth(s) / 2.0F, y, i);
/*     */   }
/*     */   static final class DrawEntry extends Record { private final float atX; private final float atY; private final float r; private final float g; private final float b; private final Glyph toDraw;
/* 383 */     DrawEntry(float atX, float atY, float r, float g, float b, Glyph toDraw) { this.atX = atX; this.atY = atY; this.r = r; this.g = g; this.b = b; this.toDraw = toDraw; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/gui/font/FontRenderer$DrawEntry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #383	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 383 */       //   0	7	0	this	Lthunder/hack/gui/font/FontRenderer$DrawEntry; } public float atX() { return this.atX; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/gui/font/FontRenderer$DrawEntry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #383	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/gui/font/FontRenderer$DrawEntry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/gui/font/FontRenderer$DrawEntry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #383	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/gui/font/FontRenderer$DrawEntry;
/* 383 */       //   0	8	1	o	Ljava/lang/Object; } public float atY() { return this.atY; } public float r() { return this.r; } public float g() { return this.g; } public float b() { return this.b; } public Glyph toDraw() { return this.toDraw; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\font\FontRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */