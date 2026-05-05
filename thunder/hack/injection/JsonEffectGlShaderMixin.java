/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_280;
/*    */ import net.minecraft.class_281;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_5912;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import thunder.hack.utility.render.shaders.satin.impl.SamplerAccess;
/*    */ 
/*    */ @Mixin({class_280.class})
/*    */ public abstract class JsonEffectGlShaderMixin
/*    */   implements SamplerAccess {
/*    */   @WrapOperation(at = {@At(value = "INVOKE", target = "net/minecraft/util/Identifier.ofVanilla (Ljava/lang/String;)Lnet/minecraft/util/Identifier;", ordinal = 0)}, method = {"<init>"})
/*    */   class_2960 constructProgramIdentifier(String arg, Operation<class_2960> original, class_5912 unused, String id) {
/* 18 */     if (!id.contains(":")) {
/* 19 */       return (class_2960)original.call(new Object[] { arg });
/*    */     }
/* 21 */     class_2960 split = class_2960.method_60654(id);
/* 22 */     return class_2960.method_60655(split.method_12836(), "shaders/program/" + split.method_12832() + ".json");
/*    */   }
/*    */   
/*    */   @WrapOperation(at = {@At(value = "INVOKE", target = "net/minecraft/util/Identifier.ofVanilla (Ljava/lang/String;)Lnet/minecraft/util/Identifier;", ordinal = 0)}, method = {"loadEffect"})
/*    */   private static class_2960 constructProgramIdentifier(String arg, Operation<class_2960> original, class_5912 unused, class_281.class_282 shaderType, String id) {
/* 27 */     if (!arg.contains(":")) {
/* 28 */       return (class_2960)original.call(new Object[] { arg });
/*    */     }
/* 30 */     class_2960 split = class_2960.method_60654(id);
/* 31 */     return class_2960.method_60655(split.method_12836(), "shaders/program/" + split.method_12832() + shaderType.method_1284());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\JsonEffectGlShaderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */