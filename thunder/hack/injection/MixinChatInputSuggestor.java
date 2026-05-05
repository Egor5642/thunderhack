/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.mojang.brigadier.ParseResults;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_342;
/*    */ import net.minecraft.class_4717;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ @Mixin({class_4717.class})
/*    */ public abstract class MixinChatInputSuggestor
/*    */ {
/*    */   @Final
/*    */   @Shadow
/*    */   class_342 field_21599;
/*    */   @Shadow
/*    */   boolean field_21614;
/*    */   @Shadow
/*    */   private ParseResults<class_2172> field_21610;
/*    */   
/*    */   @Inject(method = {"refresh"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/brigadier/StringReader;canRead()Z", remap = false)}, cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
/*    */   public void refreshHook(CallbackInfo ci, String string, StringReader reader) {
/* 34 */     if (Module.fullNullCheck())
/* 35 */       return;  if (reader.canRead(Managers.COMMAND.getPrefix().length()) && reader.getString().startsWith(Managers.COMMAND.getPrefix(), reader.getCursor())) {
/* 36 */       reader.setCursor(reader.getCursor() + 1);
/*    */       
/* 38 */       if (this.field_21610 == null) {
/* 39 */         this.field_21610 = Managers.COMMAND.getDispatcher().parse(reader, Managers.COMMAND.getSource());
/*    */       }
/* 41 */       int cursor = this.field_21599.method_1881();
/*    */       
/* 43 */       if (cursor >= 1 && (this.field_21612 == null || !this.field_21614)) {
/* 44 */         this.field_21611 = Managers.COMMAND.getDispatcher().getCompletionSuggestions(this.field_21610, cursor);
/* 45 */         this.field_21611.thenRun(() -> {
/*    */               if (this.field_21611.isDone())
/*    */                 method_23937(); 
/*    */             });
/*    */       } 
/* 50 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   private CompletableFuture<Suggestions> field_21611;
/*    */   @Shadow
/*    */   private class_4717.class_464 field_21612;
/*    */   
/*    */   @Shadow
/*    */   protected abstract void method_23937();
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinChatInputSuggestor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */