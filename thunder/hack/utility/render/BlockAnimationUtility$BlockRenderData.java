/*     */ package thunder.hack.utility.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_4587;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class BlockRenderData
/*     */   extends Record
/*     */ {
/*     */   private final class_2338 pos;
/*     */   private final Color lineColor;
/*     */   private final int lineWidth;
/*     */   private final Color fillColor;
/*     */   private final BlockAnimationUtility.BlockAnimationMode animationMode;
/*     */   private final BlockAnimationUtility.BlockRenderMode renderMode;
/*     */   
/*     */   public final String toString() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> toString : (Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;)Ljava/lang/String;
/*     */     //   6: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #34	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: <illegal opcode> hashCode : (Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;)I
/*     */     //   6: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #34	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	7	0	this	Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object o) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: <illegal opcode> equals : (Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;Ljava/lang/Object;)Z
/*     */     //   7: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #34	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	8	0	this	Lthunder/hack/utility/render/BlockAnimationUtility$BlockRenderData;
/*     */     //   0	8	1	o	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private BlockRenderData(class_2338 pos, Color lineColor, int lineWidth, Color fillColor, BlockAnimationUtility.BlockAnimationMode animationMode, BlockAnimationUtility.BlockRenderMode renderMode) {
/*  34 */     this.pos = pos; this.lineColor = lineColor; this.lineWidth = lineWidth; this.fillColor = fillColor; this.animationMode = animationMode; this.renderMode = renderMode; } public class_2338 pos() { return this.pos; } public Color lineColor() { return this.lineColor; } public int lineWidth() { return this.lineWidth; } public Color fillColor() { return this.fillColor; } public BlockAnimationUtility.BlockAnimationMode animationMode() { return this.animationMode; } public BlockAnimationUtility.BlockRenderMode renderMode() { return this.renderMode; } void renderWithTime(Long time, class_4587 stack) { float f1; class_238 box;
/*     */     float scale;
/*     */     class_238 class_2381;
/*  37 */     switch (this.animationMode.ordinal()) {
/*     */       case 3:
/*  39 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/*  40 */           Render3DEngine.drawBoxOutline(new class_238(this.pos), this.lineColor, this.lineWidth);
/*     */         }
/*  42 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill) {
/*  43 */           Render3DEngine.drawFilledBox(stack, new class_238(this.pos), this.fillColor);
/*     */         }
/*     */         break;
/*     */       case 2:
/*  47 */         f1 = 1.0F - (float)time.longValue() / 300.0F;
/*  48 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*  49 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/*  50 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(f1, f1, f1).method_989(0.5D + f1 * 0.5D, 0.5D + f1 * 0.5D, 0.5D + f1 * 0.5D), this.lineColor, this.lineWidth);
/*     */         }
/*  52 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill)
/*  53 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(f1, f1, f1).method_989(0.5D + f1 * 0.5D, 0.5D + f1 * 0.5D, 0.5D + f1 * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * (1.0F - (float)time.longValue() / 300.0F)))); 
/*     */         break;
/*     */       case 0:
/*  56 */         box = new class_238(this.pos);
/*  57 */         renderBox(time, stack, box, this.renderMode, this.lineColor, this.lineWidth, this.fillColor);
/*     */         break;
/*     */       case 6:
/*  60 */         scale = (float)time.longValue() / 300.0F;
/*  61 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*  62 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/*  63 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), this.lineColor, this.lineWidth);
/*     */         }
/*  65 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill) {
/*  66 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * (float)time.longValue() / 300.0F)));
/*     */         }
/*     */         break;
/*     */       case 4:
/*  70 */         if (time.longValue() > 100L) {
/*  71 */           scale = 1.0F - (float)(time.longValue() - 100L) / 400.0F;
/*     */         } else {
/*  73 */           scale = (float)time.longValue() / 100.0F;
/*     */         } 
/*     */ 
/*     */         
/*  77 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*     */         
/*  79 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/*  80 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), this.lineColor, this.lineWidth);
/*     */         }
/*  82 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill)
/*  83 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * scale))); 
/*     */         break;
/*     */       case 5:
/*  86 */         scale = (float)time.longValue() / 300.0F;
/*  87 */         class_2381 = new class_238(this.pos.method_10263(), (this.pos.method_10264() + scale), this.pos.method_10260(), (this.pos.method_10263() + 1), this.pos.method_10264(), (this.pos.method_10260() + 1));
/*     */         
/*  89 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/*  90 */           Render3DEngine.drawBoxOutline(class_2381, this.lineColor, this.lineWidth);
/*     */         }
/*  92 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill) {
/*  93 */           Render3DEngine.drawFilledBox(stack, class_2381, Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * (float)time.longValue() / 300.0F)));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 7:
/*  98 */         if (time.longValue() < 200L) {
/*  99 */           scale = 1.0F;
/*     */         } else {
/* 101 */           scale = 1.0F + ((float)time.longValue() - 200.0F) / 400.0F;
/*     */         } 
/*     */ 
/*     */         
/* 105 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*     */         
/* 107 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/* 108 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(scale, scale, scale).method_989(0.5D + (scale * 0.5F), 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), this.lineColor, this.lineWidth);
/*     */         }
/* 110 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill) {
/* 111 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * scale)));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 8:
/* 116 */         if (time.longValue() < 200L) {
/* 117 */           scale = 1.5F - (float)time.longValue() / 200.0F * 0.5F;
/*     */         } else {
/* 119 */           scale = 1.0F;
/*     */         } 
/*     */         
/* 122 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*     */         
/* 124 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/* 125 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(scale, scale, scale).method_989(0.5D + (scale * 0.5F), 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), this.lineColor, this.lineWidth);
/*     */         }
/* 127 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill) {
/* 128 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * scale)));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 1:
/* 133 */         scale = 1.0F + (float)time.longValue() / 1500.0F;
/*     */         
/* 135 */         class_2381 = new class_238(this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260(), this.pos.method_10263(), this.pos.method_10264(), this.pos.method_10260());
/*     */         
/* 137 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/* 138 */           Render3DEngine.drawBoxOutline(class_2381.method_1002(scale, scale, scale).method_989(0.5D + (scale * 0.5F), 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.lineColor, (int)(this.lineColor.getAlpha() * (1.0F - (float)time.longValue() / 300.0F))), this.lineWidth);
/*     */         }
/* 140 */         if (this.renderMode == BlockAnimationUtility.BlockRenderMode.All || this.renderMode == BlockAnimationUtility.BlockRenderMode.Fill)
/* 141 */           Render3DEngine.drawFilledBox(stack, class_2381.method_1002(scale, scale, scale).method_989(0.5D + scale * 0.5D, 0.5D + scale * 0.5D, 0.5D + scale * 0.5D), Render2DEngine.injectAlpha(this.fillColor, (int)(this.fillColor.getAlpha() * (1.0F - (float)time.longValue() / 300.0F)))); 
/*     */         break;
/*     */     }  }
/*     */ 
/*     */   
/*     */   private static void renderBox(Long time, class_4587 stack, class_238 box, BlockAnimationUtility.BlockRenderMode renderMode, Color lineColor, int lineWidth, Color fillColor) {
/* 147 */     if (renderMode == BlockAnimationUtility.BlockRenderMode.All || renderMode == BlockAnimationUtility.BlockRenderMode.Line) {
/* 148 */       Render3DEngine.drawBoxOutline(box, Render2DEngine.injectAlpha(lineColor, (int)(fillColor.getAlpha() * (1.0F - (float)time.longValue() / 300.0F))), lineWidth);
/*     */     }
/* 150 */     if (renderMode == BlockAnimationUtility.BlockRenderMode.All || renderMode == BlockAnimationUtility.BlockRenderMode.Fill)
/* 151 */       Render3DEngine.drawFilledBox(stack, box, Render2DEngine.injectAlpha(fillColor, (int)(fillColor.getAlpha() * (1.0F - (float)time.longValue() / 300.0F)))); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\BlockAnimationUtility$BlockRenderData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */