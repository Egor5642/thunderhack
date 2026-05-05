/*     */ package thunder.hack.utility.render;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Kernel;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class GaussianFilter
/*     */ {
/*     */   protected float radius;
/*     */   protected Kernel kernel;
/*     */   
/*     */   public GaussianFilter(float radius) {
/*  14 */     setRadius(radius);
/*     */   }
/*     */   
/*     */   public static void convolveAndTranspose(@NotNull Kernel kernel, int[] inPixels, int[] outPixels, int width, int height, boolean alpha, boolean premultiply, boolean unpremultiply, int edgeAction) {
/*  18 */     float[] matrix = kernel.getKernelData(null);
/*  19 */     int cols = kernel.getWidth();
/*  20 */     int cols2 = cols / 2;
/*  21 */     for (int y = 0; y < height; y++) {
/*  22 */       int index = y;
/*  23 */       int ioffset = y * width;
/*  24 */       for (int x = 0; x < width; x++) {
/*  25 */         float r = 0.0F, g = 0.0F, b = 0.0F, a = 0.0F;
/*  26 */         int moffset = cols2;
/*  27 */         for (int col = -cols2; col <= cols2; col++) {
/*  28 */           float f = matrix[moffset + col];
/*  29 */           if (f != 0.0F) {
/*  30 */             int ix = x + col;
/*  31 */             if (ix < 0) {
/*  32 */               if (edgeAction == 1) {
/*  33 */                 ix = 0;
/*  34 */               } else if (edgeAction == 2) {
/*  35 */                 ix = (x + width) % width;
/*     */               }
/*     */             
/*  38 */             } else if (ix >= width) {
/*  39 */               if (edgeAction == 1) {
/*  40 */                 ix = width - 1;
/*  41 */               } else if (edgeAction == 2) {
/*  42 */                 ix = (x + width) % width;
/*     */               } 
/*     */             } 
/*     */             
/*  46 */             int rgb = inPixels[ioffset + ix];
/*  47 */             int pa = rgb >> 24 & 0xFF;
/*  48 */             int pr = rgb >> 16 & 0xFF;
/*  49 */             int pg = rgb >> 8 & 0xFF;
/*  50 */             int pb = rgb & 0xFF;
/*     */             
/*  52 */             if (premultiply) {
/*  53 */               float a255 = pa * 0.003921569F;
/*  54 */               pr = (int)(pr * a255);
/*  55 */               pg = (int)(pg * a255);
/*  56 */               pb = (int)(pb * a255);
/*     */             } 
/*     */             
/*  59 */             a += f * pa;
/*  60 */             r += f * pr;
/*  61 */             g += f * pg;
/*  62 */             b += f * pb;
/*     */           } 
/*     */         } 
/*     */         
/*  66 */         if (unpremultiply && a != 0.0F && a != 255.0F) {
/*  67 */           float f = 255.0F / a;
/*  68 */           r *= f;
/*  69 */           g *= f;
/*  70 */           b *= f;
/*     */         } 
/*     */         
/*  73 */         int ia = alpha ? clamp((int)(a + 0.5D)) : 255;
/*  74 */         int ir = clamp((int)(r + 0.5D));
/*  75 */         int ig = clamp((int)(g + 0.5D));
/*  76 */         int ib = clamp((int)(b + 0.5D));
/*  77 */         outPixels[index] = ia << 24 | ir << 16 | ig << 8 | ib;
/*  78 */         index += height;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int clamp(int c) {
/*  84 */     if (c < 0) return 0; 
/*  85 */     return Math.min(c, 255);
/*     */   }
/*     */   
/*     */   public static Kernel makeKernel(float radius) {
/*  89 */     int r = (int)Math.ceil(radius);
/*  90 */     int rows = r * 2 + 1;
/*  91 */     float[] matrix = new float[rows];
/*  92 */     float sigma = radius / 3.0F;
/*  93 */     float sigma22 = 2.0F * sigma * sigma;
/*  94 */     float sigmaPi2 = 6.2831855F * sigma;
/*  95 */     float sqrtSigmaPi2 = (float)Math.sqrt(sigmaPi2);
/*  96 */     float radius2 = radius * radius;
/*  97 */     float total = 0.0F;
/*  98 */     int index = 0;
/*  99 */     for (int row = -r; row <= r; row++) {
/* 100 */       float distance = (row * row);
/* 101 */       if (distance > radius2) {
/* 102 */         matrix[index] = 0.0F;
/*     */       } else {
/* 104 */         matrix[index] = (float)Math.exp((-distance / sigma22)) / sqrtSigmaPi2;
/*     */       } 
/* 106 */       total += matrix[index];
/* 107 */       index++;
/*     */     } 
/* 109 */     for (int i = 0; i < rows; i++) {
/* 110 */       matrix[i] = matrix[i] / total;
/*     */     }
/* 112 */     return new Kernel(rows, 1, matrix);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadius(float radius) {
/* 117 */     this.radius = radius;
/* 118 */     this.kernel = makeKernel(radius);
/*     */   }
/*     */   
/*     */   public BufferedImage filter(BufferedImage src, BufferedImage dst) {
/* 122 */     int width = src.getWidth();
/* 123 */     int height = src.getHeight();
/* 124 */     if (dst == null) {
/* 125 */       dst = createCompatibleDestImage(src, null);
/*     */     }
/* 127 */     int[] inPixels = new int[width * height];
/* 128 */     int[] outPixels = new int[width * height];
/* 129 */     src.getRGB(0, 0, width, height, inPixels, 0, width);
/* 130 */     if (this.radius > 0.0F) {
/* 131 */       convolveAndTranspose(this.kernel, inPixels, outPixels, width, height, true, true, false, 1);
/* 132 */       convolveAndTranspose(this.kernel, outPixels, inPixels, height, width, true, false, true, 1);
/*     */     } 
/* 134 */     dst.setRGB(0, 0, width, height, inPixels, 0, width);
/* 135 */     return dst;
/*     */   }
/*     */   
/*     */   public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel dstCM) {
/* 139 */     if (dstCM == null) {
/* 140 */       dstCM = src.getColorModel();
/*     */     }
/* 142 */     return new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()), dstCM.isAlphaPremultiplied(), null);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 146 */     return "Blur/Gaussian Blur...";
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\GaussianFilter.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */