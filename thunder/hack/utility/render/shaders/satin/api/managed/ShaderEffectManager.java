/*    */ package thunder.hack.utility.render.shaders.satin.api.managed;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.class_293;
/*    */ import net.minecraft.class_2960;
/*    */ import thunder.hack.utility.render.shaders.satin.impl.ReloadableShaderEffectManager;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ShaderEffectManager
/*    */ {
/*    */   static ShaderEffectManager getInstance() {
/* 31 */     return (ShaderEffectManager)ReloadableShaderEffectManager.INSTANCE;
/*    */   }
/*    */   
/*    */   ManagedShaderEffect manage(class_2960 paramclass_2960);
/*    */   
/*    */   ManagedShaderEffect manage(class_2960 paramclass_2960, Consumer<ManagedShaderEffect> paramConsumer);
/*    */   
/*    */   ManagedCoreShader manageCoreShader(class_2960 paramclass_2960);
/*    */   
/*    */   ManagedCoreShader manageCoreShader(class_2960 paramclass_2960, class_293 paramclass_293);
/*    */   
/*    */   ManagedCoreShader manageCoreShader(class_2960 paramclass_2960, class_293 paramclass_293, Consumer<ManagedCoreShader> paramConsumer);
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\api\managed\ShaderEffectManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */