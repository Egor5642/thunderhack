/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1799;
/*    */ import net.minecraft.class_1836;
/*    */ import net.minecraft.class_2480;
/*    */ import net.minecraft.class_2561;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.render.Tooltips;
/*    */ 
/*    */ @Mixin({class_2480.class})
/*    */ public class MixinShulkerBoxBlock
/*    */ {
/*    */   @Inject(method = {"appendTooltip"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onAppendTooltip(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 options, CallbackInfo ci) {
/* 21 */     if (ModuleManager.tooltips == null)
/* 22 */       return;  if (((Boolean)Tooltips.storage.getValue()).booleanValue()) ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinShulkerBoxBlock.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */