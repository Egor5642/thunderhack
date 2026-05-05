/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import com.mojang.logging.LogUtils;
/*    */ import net.minecraft.class_1041;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_279;
/*    */ import net.minecraft.class_310;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedFramebuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FramebufferWrapper
/*    */   implements ManagedFramebuffer
/*    */ {
/*    */   private final String name;
/*    */   private class_276 wrapped;
/*    */   
/*    */   FramebufferWrapper(String name) {
/* 32 */     this.name = name;
/*    */   }
/*    */   
/*    */   void findTarget(class_279 shaderEffect) {
/* 36 */     if (shaderEffect == null) {
/* 37 */       this.wrapped = null;
/*    */     } else {
/* 39 */       this.wrapped = shaderEffect.method_1264(this.name);
/* 40 */       if (this.wrapped == null) {
/* 41 */         LogUtils.getLogger().warn("No target framebuffer found with name {} in shader {}", this.name, shaderEffect.method_1260());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getName() {
/* 47 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public class_276 getFramebuffer() {
/* 52 */     return this.wrapped;
/*    */   }
/*    */ 
/*    */   
/*    */   public void beginWrite(boolean updateViewport) {
/* 57 */     if (this.wrapped != null) {
/* 58 */       this.wrapped.method_1235(updateViewport);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw() {
/* 64 */     class_1041 window = class_310.method_1551().method_22683();
/* 65 */     draw(window.method_4489(), window.method_4506(), true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw(int width, int height, boolean disableBlend) {
/* 70 */     if (this.wrapped != null) {
/* 71 */       this.wrapped.method_22594(width, height, disableBlend);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 77 */     clear(class_310.field_1703);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear(boolean swallowErrors) {
/* 82 */     if (this.wrapped != null)
/* 83 */       this.wrapped.method_1230(swallowErrors); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\FramebufferWrapper.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */