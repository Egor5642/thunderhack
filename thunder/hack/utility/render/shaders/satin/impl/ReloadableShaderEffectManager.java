/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.class_1041;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_293;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_5912;
/*    */ import thunder.hack.utility.render.WindowResizeCallback;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedCoreShader;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ManagedShaderEffect;
/*    */ import thunder.hack.utility.render.shaders.satin.api.managed.ShaderEffectManager;
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
/*    */ 
/*    */ public final class ReloadableShaderEffectManager
/*    */   implements ShaderEffectManager
/*    */ {
/* 36 */   public static final ReloadableShaderEffectManager INSTANCE = new ReloadableShaderEffectManager();
/*    */ 
/*    */   
/*    */   private final Set<ResettableManagedShaderBase<?>> managedShaders;
/*    */ 
/*    */ 
/*    */   
/*    */   public ReloadableShaderEffectManager() {
/* 44 */     this.managedShaders = (Set<ResettableManagedShaderBase<?>>)new ReferenceOpenHashSet();
/*    */     WindowResizeCallback.EVENT.register((client, window) -> onResolutionChanged(window.method_4489(), window.method_4506()));
/*    */   }
/*    */   public ManagedShaderEffect manage(class_2960 location) {
/* 48 */     return manage(location, s -> {
/*    */         
/*    */         });
/*    */   }
/*    */   
/*    */   public ManagedShaderEffect manage(class_2960 location, Consumer<ManagedShaderEffect> initCallback) {
/* 54 */     ResettableManagedShaderEffect ret = new ResettableManagedShaderEffect(location, initCallback);
/* 55 */     this.managedShaders.add(ret);
/* 56 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public ManagedCoreShader manageCoreShader(class_2960 location) {
/* 61 */     return manageCoreShader(location, class_290.field_1580);
/*    */   }
/*    */ 
/*    */   
/*    */   public ManagedCoreShader manageCoreShader(class_2960 location, class_293 vertexFormat) {
/* 66 */     return manageCoreShader(location, vertexFormat, s -> {
/*    */         
/*    */         });
/*    */   }
/*    */   
/*    */   public ManagedCoreShader manageCoreShader(class_2960 location, class_293 vertexFormat, Consumer<ManagedCoreShader> initCallback) {
/* 72 */     ResettableManagedCoreShader ret = new ResettableManagedCoreShader(location, vertexFormat, initCallback);
/* 73 */     this.managedShaders.add(ret);
/* 74 */     return ret;
/*    */   }
/*    */   
/*    */   public void reload(class_5912 shaderResources) {
/* 78 */     for (ResettableManagedShaderBase<?> ss : this.managedShaders) {
/* 79 */       ss.initializeOrLog(shaderResources);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onResolutionChanged(int newWidth, int newHeight) {
/* 84 */     runShaderSetup(newWidth, newHeight);
/*    */   }
/*    */   
/*    */   private void runShaderSetup(int newWidth, int newHeight) {
/* 88 */     if (!this.managedShaders.isEmpty())
/* 89 */       for (ResettableManagedShaderBase<?> ss : this.managedShaders) {
/* 90 */         if (ss.isInitialized())
/* 91 */           ss.setup(newWidth, newHeight); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ReloadableShaderEffectManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */