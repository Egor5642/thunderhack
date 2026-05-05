/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_279;
/*    */ import net.minecraft.class_283;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.injection.accesors.IPostProcessShader;
/*    */ import thunder.hack.utility.interfaces.IShaderEffect;
/*    */ 
/*    */ @Mixin({class_279.class})
/*    */ public class MixinShaderEffect implements IShaderEffect {
/*    */   @Unique
/* 22 */   private final List<String> fakedBufferNames = new ArrayList<>();
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private Map<String, class_276> field_1495;
/*    */   
/*    */   public void addFakeTargetHook(String name, class_276 buffer) {
/* 29 */     class_276 previousFramebuffer = this.field_1495.get(name);
/* 30 */     if (previousFramebuffer == buffer) {
/*    */       return;
/*    */     }
/* 33 */     if (previousFramebuffer != null) {
/* 34 */       for (class_283 pass : this.field_1497) {
/* 35 */         if (pass.field_1536 == previousFramebuffer) ((IPostProcessShader)pass).setInput(buffer); 
/* 36 */         if (pass.field_1538 == previousFramebuffer) ((IPostProcessShader)pass).setOutput(buffer); 
/*    */       } 
/* 38 */       this.field_1495.remove(name);
/* 39 */       this.fakedBufferNames.remove(name);
/*    */     } 
/*    */     
/* 42 */     this.field_1495.put(name, buffer);
/* 43 */     this.fakedBufferNames.add(name);
/*    */   } @Shadow
/*    */   @Final
/*    */   private List<class_283> field_1497; @Inject(method = {"close"}, at = {@At("HEAD")})
/*    */   void deleteFakeBuffersHook(CallbackInfo ci) {
/* 48 */     for (String fakedBufferName : this.fakedBufferNames)
/* 49 */       this.field_1495.remove(fakedBufferName); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinShaderEffect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */