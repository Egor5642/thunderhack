/*     */ package thunder.hack.utility.render;
/*     */ 
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_276;
/*     */ import net.minecraft.class_310;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ 
/*     */ public class MSAAFramebuffer
/*     */   extends class_276
/*     */ {
/*  15 */   public static final int MAX_SAMPLES = GL30.glGetInteger(36183);
/*  16 */   private static final Map<Integer, MSAAFramebuffer> INSTANCES = new HashMap<>();
/*     */   
/*     */   private final int samples;
/*     */   private int rboColor;
/*     */   private int rboDepth;
/*     */   
/*     */   public MSAAFramebuffer(int samples) {
/*  23 */     super(true);
/*  24 */     this.samples = samples;
/*  25 */     method_1236(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public static MSAAFramebuffer getInstance(int samples) {
/*  29 */     return INSTANCES.computeIfAbsent(Integer.valueOf(samples), x -> new MSAAFramebuffer(samples));
/*     */   }
/*     */   
/*     */   public static void use(boolean fancy, Runnable drawAction) {
/*  33 */     use(Math.min(fancy ? 16 : 4, MAX_SAMPLES), class_310.method_1551().method_1522(), drawAction);
/*     */   }
/*     */   
/*     */   public static void use(int samples, @NotNull class_276 mainBuffer, @NotNull Runnable drawAction) {
/*  37 */     RenderSystem.assertOnRenderThreadOrInit();
/*  38 */     MSAAFramebuffer msaaBuffer = getInstance(samples);
/*  39 */     msaaBuffer.method_1234(mainBuffer.field_1482, mainBuffer.field_1481, false);
/*     */     
/*  41 */     GlStateManager._glBindFramebuffer(36008, mainBuffer.field_1476);
/*  42 */     GlStateManager._glBindFramebuffer(36009, msaaBuffer.field_1476);
/*  43 */     GlStateManager._glBlitFrameBuffer(0, 0, msaaBuffer.field_1482, msaaBuffer.field_1481, 0, 0, msaaBuffer.field_1482, msaaBuffer.field_1481, 16384, 9729);
/*  44 */     msaaBuffer.method_1235(true);
/*     */     
/*  46 */     drawAction.run();
/*  47 */     msaaBuffer.method_1240();
/*     */     
/*  49 */     GlStateManager._glBindFramebuffer(36008, msaaBuffer.field_1476);
/*  50 */     GlStateManager._glBindFramebuffer(36009, mainBuffer.field_1476);
/*  51 */     GlStateManager._glBlitFrameBuffer(0, 0, msaaBuffer.field_1482, msaaBuffer.field_1481, 0, 0, msaaBuffer.field_1482, msaaBuffer.field_1481, 16384, 9729);
/*  52 */     msaaBuffer.method_1230(false);
/*  53 */     mainBuffer.method_1235(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_1234(int width, int height, boolean getError) {
/*  58 */     if (this.field_1482 != width || this.field_1481 != height) {
/*  59 */       super.method_1234(width, height, getError);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_1231(int width, int height, boolean getError) {
/*  65 */     RenderSystem.assertOnRenderThreadOrInit();
/*  66 */     this.field_1480 = width;
/*  67 */     this.field_1477 = height;
/*  68 */     this.field_1482 = width;
/*  69 */     this.field_1481 = height;
/*     */     
/*  71 */     this.field_1476 = GlStateManager.glGenFramebuffers();
/*  72 */     GlStateManager._glBindFramebuffer(36160, this.field_1476);
/*     */     
/*  74 */     this.rboColor = GlStateManager.glGenRenderbuffers();
/*  75 */     GlStateManager._glBindRenderbuffer(36161, this.rboColor);
/*  76 */     GL30.glRenderbufferStorageMultisample(36161, this.samples, 32856, width, height);
/*  77 */     GlStateManager._glBindRenderbuffer(36161, 0);
/*     */     
/*  79 */     this.rboDepth = GlStateManager.glGenRenderbuffers();
/*  80 */     GlStateManager._glBindRenderbuffer(36161, this.rboDepth);
/*  81 */     GL30.glRenderbufferStorageMultisample(36161, this.samples, 6402, width, height);
/*  82 */     GlStateManager._glBindRenderbuffer(36161, 0);
/*     */     
/*  84 */     GL30.glFramebufferRenderbuffer(36160, 36064, 36161, this.rboColor);
/*  85 */     GL30.glFramebufferRenderbuffer(36160, 36096, 36161, this.rboDepth);
/*     */     
/*  87 */     this.field_1475 = class_310.method_1551().method_1522().method_30277();
/*  88 */     this.field_1474 = class_310.method_1551().method_1522().method_30278();
/*     */     
/*  90 */     method_1239();
/*  91 */     method_1230(getError);
/*  92 */     method_1242();
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_1238() {
/*  97 */     RenderSystem.assertOnRenderThreadOrInit();
/*  98 */     method_1242();
/*  99 */     method_1240();
/*     */     
/* 101 */     if (this.field_1476 > -1) {
/* 102 */       GlStateManager._glBindFramebuffer(36160, 0);
/* 103 */       GlStateManager._glDeleteFramebuffers(this.field_1476);
/* 104 */       this.field_1476 = -1;
/*     */     } 
/*     */     
/* 107 */     if (this.rboColor > -1) {
/* 108 */       GlStateManager._glDeleteRenderbuffers(this.rboColor);
/* 109 */       this.rboColor = -1;
/*     */     } 
/*     */     
/* 112 */     if (this.rboDepth > -1) {
/* 113 */       GlStateManager._glDeleteRenderbuffers(this.rboDepth);
/* 114 */       this.rboDepth = -1;
/*     */     } 
/*     */     
/* 117 */     this.field_1475 = -1;
/* 118 */     this.field_1474 = -1;
/* 119 */     this.field_1482 = -1;
/* 120 */     this.field_1481 = -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\MSAAFramebuffer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */