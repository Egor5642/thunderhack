/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_634;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ @Mixin({class_634.class})
/*    */ public class MixinClientPlayNetworkHandler
/*    */ {
/*    */   @Inject(method = {"sendChatMessage"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void sendChatMessageHook(@NotNull String message, CallbackInfo ci) {
/* 19 */     if (message.equals(String.valueOf(ModuleManager.unHook.code)) && ModuleManager.unHook.isEnabled()) {
/* 20 */       ModuleManager.unHook.disable();
/*    */     }
/* 22 */     if (Module.fullNullCheck())
/* 23 */       return;  if (message.startsWith(Managers.COMMAND.getPrefix())) {
/*    */       try {
/* 25 */         Managers.COMMAND.getDispatcher().execute(message
/* 26 */             .substring(Managers.COMMAND.getPrefix().length()), Managers.COMMAND
/* 27 */             .getSource());
/*    */       }
/* 29 */       catch (CommandSyntaxException commandSyntaxException) {}
/*    */       
/* 31 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinClientPlayNetworkHandler.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */