/*     */ package thunder.hack.gui.font;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_1044;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ 
/*     */ 
/*     */ 
/*     */ class GlyphMap
/*     */ {
/*     */   final char fromIncl;
/*     */   final char toExcl;
/*     */   final Font font;
/*     */   final class_2960 bindToTexture;
/*     */   final int pixelPadding;
/*  29 */   private final Char2ObjectArrayMap<Glyph> glyphs = new Char2ObjectArrayMap();
/*     */   int width;
/*     */   int height;
/*     */   boolean generated = false;
/*     */   
/*     */   public GlyphMap(char from, char to, Font font, class_2960 identifier, int padding) {
/*  35 */     this.fromIncl = from;
/*  36 */     this.toExcl = to;
/*  37 */     this.font = font;
/*  38 */     this.bindToTexture = identifier;
/*  39 */     this.pixelPadding = padding;
/*     */   }
/*     */   
/*     */   public Glyph getGlyph(char c) {
/*  43 */     if (!this.generated) {
/*  44 */       generate();
/*     */     }
/*  46 */     return (Glyph)this.glyphs.get(c);
/*     */   }
/*     */   
/*     */   public void destroy() {
/*  50 */     class_310.method_1551().method_1531().method_4615(this.bindToTexture);
/*  51 */     this.glyphs.clear();
/*  52 */     this.width = -1;
/*  53 */     this.height = -1;
/*  54 */     this.generated = false;
/*     */   }
/*     */   
/*     */   public boolean contains(char c) {
/*  58 */     return (c >= this.fromIncl && c < this.toExcl);
/*     */   }
/*     */   
/*     */   private Font getFontForGlyph(char c) {
/*  62 */     if (this.font.canDisplay(c)) {
/*  63 */       return this.font;
/*     */     }
/*  65 */     return this.font;
/*     */   }
/*     */   
/*     */   public void generate() {
/*  69 */     if (this.generated) {
/*     */       return;
/*     */     }
/*  72 */     int range = this.toExcl - this.fromIncl - 1;
/*  73 */     int charsVert = (int)(Math.ceil(Math.sqrt(range)) * 1.5D);
/*  74 */     this.glyphs.clear();
/*  75 */     int generatedChars = 0;
/*  76 */     int charNX = 0;
/*  77 */     int maxX = 0, maxY = 0;
/*  78 */     int currentX = 0, currentY = 0;
/*  79 */     int currentRowMaxY = 0;
/*  80 */     List<Glyph> glyphs1 = new ArrayList<>();
/*  81 */     AffineTransform af = new AffineTransform();
/*  82 */     FontRenderContext frc = new FontRenderContext(af, true, false);
/*  83 */     while (generatedChars <= range) {
/*  84 */       char currentChar = (char)(this.fromIncl + generatedChars);
/*  85 */       Font font = getFontForGlyph(currentChar);
/*  86 */       Rectangle2D stringBounds = font.getStringBounds(String.valueOf(currentChar), frc);
/*     */       
/*  88 */       int width = (int)Math.ceil(stringBounds.getWidth());
/*  89 */       int height = (int)Math.ceil(stringBounds.getHeight());
/*  90 */       generatedChars++;
/*  91 */       maxX = Math.max(maxX, currentX + width);
/*  92 */       maxY = Math.max(maxY, currentY + height);
/*  93 */       if (charNX >= charsVert) {
/*  94 */         currentX = 0;
/*  95 */         currentY += currentRowMaxY + this.pixelPadding;
/*  96 */         charNX = 0;
/*  97 */         currentRowMaxY = 0;
/*     */       } 
/*  99 */       currentRowMaxY = Math.max(currentRowMaxY, height);
/* 100 */       glyphs1.add(new Glyph(currentX, currentY, width, height, currentChar, this));
/* 101 */       currentX += width + this.pixelPadding;
/* 102 */       charNX++;
/*     */     } 
/* 104 */     BufferedImage bi = new BufferedImage(Math.max(maxX + this.pixelPadding, 1), Math.max(maxY + this.pixelPadding, 1), 2);
/*     */     
/* 106 */     this.width = bi.getWidth();
/* 107 */     this.height = bi.getHeight();
/* 108 */     Graphics2D g2d = bi.createGraphics();
/* 109 */     g2d.setColor(new Color(255, 255, 255, 0));
/* 110 */     g2d.fillRect(0, 0, this.width, this.height);
/* 111 */     g2d.setColor(Color.WHITE);
/*     */     
/* 113 */     g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
/* 114 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/* 115 */     g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */     
/* 117 */     for (Glyph glyph : glyphs1) {
/* 118 */       g2d.setFont(getFontForGlyph(glyph.value()));
/* 119 */       FontMetrics fontMetrics = g2d.getFontMetrics();
/* 120 */       g2d.drawString(String.valueOf(glyph.value()), glyph.u(), glyph.v() + fontMetrics.getAscent());
/* 121 */       this.glyphs.put(glyph.value(), glyph);
/*     */     } 
/* 123 */     registerBufferedImageTexture(this.bindToTexture, bi);
/* 124 */     this.generated = true;
/*     */   }
/*     */   
/*     */   public static void registerBufferedImageTexture(class_2960 i, BufferedImage bi) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual getWidth : ()I
/*     */     //   4: istore_2
/*     */     //   5: aload_1
/*     */     //   6: invokevirtual getHeight : ()I
/*     */     //   9: istore_3
/*     */     //   10: new net/minecraft/class_1011
/*     */     //   13: dup
/*     */     //   14: getstatic net/minecraft/class_1011$class_1012.field_4997 : Lnet/minecraft/class_1011$class_1012;
/*     */     //   17: iload_2
/*     */     //   18: iload_3
/*     */     //   19: iconst_0
/*     */     //   20: invokespecial <init> : (Lnet/minecraft/class_1011$class_1012;IIZ)V
/*     */     //   23: astore #4
/*     */     //   25: aload #4
/*     */     //   27: checkcast thunder/hack/injection/accesors/INativeImage
/*     */     //   30: invokeinterface getPointer : ()J
/*     */     //   35: lstore #5
/*     */     //   37: lload #5
/*     */     //   39: aload #4
/*     */     //   41: invokevirtual method_4307 : ()I
/*     */     //   44: aload #4
/*     */     //   46: invokevirtual method_4323 : ()I
/*     */     //   49: imul
/*     */     //   50: invokestatic memIntBuffer : (JI)Ljava/nio/IntBuffer;
/*     */     //   53: astore #7
/*     */     //   55: iconst_0
/*     */     //   56: istore #8
/*     */     //   58: aload_1
/*     */     //   59: invokevirtual getRaster : ()Ljava/awt/image/WritableRaster;
/*     */     //   62: astore #10
/*     */     //   64: aload_1
/*     */     //   65: invokevirtual getColorModel : ()Ljava/awt/image/ColorModel;
/*     */     //   68: astore #11
/*     */     //   70: aload #10
/*     */     //   72: invokevirtual getNumBands : ()I
/*     */     //   75: istore #12
/*     */     //   77: aload #10
/*     */     //   79: invokevirtual getDataBuffer : ()Ljava/awt/image/DataBuffer;
/*     */     //   82: invokevirtual getDataType : ()I
/*     */     //   85: istore #13
/*     */     //   87: iload #13
/*     */     //   89: tableswitch default -> 163, 0 -> 128, 1 -> 135, 2 -> 163, 3 -> 142, 4 -> 149, 5 -> 156
/*     */     //   128: iload #12
/*     */     //   130: newarray byte
/*     */     //   132: goto -> 178
/*     */     //   135: iload #12
/*     */     //   137: newarray short
/*     */     //   139: goto -> 178
/*     */     //   142: iload #12
/*     */     //   144: newarray int
/*     */     //   146: goto -> 178
/*     */     //   149: iload #12
/*     */     //   151: newarray float
/*     */     //   153: goto -> 178
/*     */     //   156: iload #12
/*     */     //   158: newarray double
/*     */     //   160: goto -> 178
/*     */     //   163: new java/lang/IllegalArgumentException
/*     */     //   166: dup
/*     */     //   167: iload #13
/*     */     //   169: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
/*     */     //   174: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   177: athrow
/*     */     //   178: astore #9
/*     */     //   180: iconst_0
/*     */     //   181: istore #14
/*     */     //   183: iload #14
/*     */     //   185: iload_3
/*     */     //   186: if_icmpge -> 288
/*     */     //   189: iconst_0
/*     */     //   190: istore #15
/*     */     //   192: iload #15
/*     */     //   194: iload_2
/*     */     //   195: if_icmpge -> 282
/*     */     //   198: aload #10
/*     */     //   200: iload #15
/*     */     //   202: iload #14
/*     */     //   204: aload #9
/*     */     //   206: invokevirtual getDataElements : (IILjava/lang/Object;)Ljava/lang/Object;
/*     */     //   209: pop
/*     */     //   210: aload #11
/*     */     //   212: aload #9
/*     */     //   214: invokevirtual getAlpha : (Ljava/lang/Object;)I
/*     */     //   217: istore #16
/*     */     //   219: aload #11
/*     */     //   221: aload #9
/*     */     //   223: invokevirtual getRed : (Ljava/lang/Object;)I
/*     */     //   226: istore #17
/*     */     //   228: aload #11
/*     */     //   230: aload #9
/*     */     //   232: invokevirtual getGreen : (Ljava/lang/Object;)I
/*     */     //   235: istore #18
/*     */     //   237: aload #11
/*     */     //   239: aload #9
/*     */     //   241: invokevirtual getBlue : (Ljava/lang/Object;)I
/*     */     //   244: istore #19
/*     */     //   246: iload #16
/*     */     //   248: bipush #24
/*     */     //   250: ishl
/*     */     //   251: iload #19
/*     */     //   253: bipush #16
/*     */     //   255: ishl
/*     */     //   256: ior
/*     */     //   257: iload #18
/*     */     //   259: bipush #8
/*     */     //   261: ishl
/*     */     //   262: ior
/*     */     //   263: iload #17
/*     */     //   265: ior
/*     */     //   266: istore #20
/*     */     //   268: aload #7
/*     */     //   270: iload #20
/*     */     //   272: invokevirtual put : (I)Ljava/nio/IntBuffer;
/*     */     //   275: pop
/*     */     //   276: iinc #15, 1
/*     */     //   279: goto -> 192
/*     */     //   282: iinc #14, 1
/*     */     //   285: goto -> 183
/*     */     //   288: new net/minecraft/class_1043
/*     */     //   291: dup
/*     */     //   292: aload #4
/*     */     //   294: invokespecial <init> : (Lnet/minecraft/class_1011;)V
/*     */     //   297: astore #14
/*     */     //   299: aload #14
/*     */     //   301: invokevirtual method_4524 : ()V
/*     */     //   304: invokestatic isOnRenderThread : ()Z
/*     */     //   307: ifeq -> 325
/*     */     //   310: invokestatic method_1551 : ()Lnet/minecraft/class_310;
/*     */     //   313: invokevirtual method_1531 : ()Lnet/minecraft/class_1060;
/*     */     //   316: aload_0
/*     */     //   317: aload #14
/*     */     //   319: invokevirtual method_4616 : (Lnet/minecraft/class_2960;Lnet/minecraft/class_1044;)V
/*     */     //   322: goto -> 336
/*     */     //   325: aload_0
/*     */     //   326: aload #14
/*     */     //   328: <illegal opcode> execute : (Lnet/minecraft/class_2960;Lnet/minecraft/class_1043;)Lnet/minecraft/class_4573;
/*     */     //   333: invokestatic recordRenderCall : (Lnet/minecraft/class_4573;)V
/*     */     //   336: goto -> 344
/*     */     //   339: astore_2
/*     */     //   340: aload_2
/*     */     //   341: invokevirtual printStackTrace : ()V
/*     */     //   344: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #132	-> 0
/*     */     //   #133	-> 5
/*     */     //   #134	-> 10
/*     */     //   #135	-> 25
/*     */     //   #136	-> 37
/*     */     //   #137	-> 55
/*     */     //   #139	-> 58
/*     */     //   #140	-> 64
/*     */     //   #141	-> 70
/*     */     //   #142	-> 77
/*     */     //   #143	-> 87
/*     */     //   #144	-> 128
/*     */     //   #145	-> 135
/*     */     //   #146	-> 142
/*     */     //   #147	-> 149
/*     */     //   #148	-> 156
/*     */     //   #149	-> 163
/*     */     //   #153	-> 180
/*     */     //   #154	-> 189
/*     */     //   #155	-> 198
/*     */     //   #156	-> 210
/*     */     //   #157	-> 219
/*     */     //   #158	-> 228
/*     */     //   #159	-> 237
/*     */     //   #160	-> 246
/*     */     //   #161	-> 268
/*     */     //   #154	-> 276
/*     */     //   #153	-> 282
/*     */     //   #164	-> 288
/*     */     //   #165	-> 299
/*     */     //   #166	-> 304
/*     */     //   #167	-> 310
/*     */     //   #169	-> 325
/*     */     //   #173	-> 336
/*     */     //   #171	-> 339
/*     */     //   #172	-> 340
/*     */     //   #174	-> 344
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   219	57	16	a	I
/*     */     //   228	48	17	r	I
/*     */     //   237	39	18	g	I
/*     */     //   246	30	19	b	I
/*     */     //   268	8	20	abgr	I
/*     */     //   192	90	15	x	I
/*     */     //   183	105	14	y	I
/*     */     //   5	331	2	ow	I
/*     */     //   10	326	3	oh	I
/*     */     //   25	311	4	image	Lnet/minecraft/class_1011;
/*     */     //   37	299	5	ptr	J
/*     */     //   55	281	7	backingBuffer	Ljava/nio/IntBuffer;
/*     */     //   58	278	8	off	I
/*     */     //   180	156	9	_d	Ljava/lang/Object;
/*     */     //   64	272	10	_ra	Ljava/awt/image/WritableRaster;
/*     */     //   70	266	11	_cm	Ljava/awt/image/ColorModel;
/*     */     //   77	259	12	nbands	I
/*     */     //   87	249	13	dataType	I
/*     */     //   299	37	14	tex	Lnet/minecraft/class_1043;
/*     */     //   340	4	2	e	Ljava/lang/Throwable;
/*     */     //   0	345	0	i	Lnet/minecraft/class_2960;
/*     */     //   0	345	1	bi	Ljava/awt/image/BufferedImage;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	336	339	java/lang/Throwable
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\font\GlyphMap.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */