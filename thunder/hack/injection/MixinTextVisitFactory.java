/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_5223;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.core.manager.player.FriendManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.misc.NameProtect;
/*    */ 
/*    */ @Mixin({class_5223.class})
/*    */ public class MixinTextVisitFactory
/*    */ {
/*    */   @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z", ordinal = 0), method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"}, index = 0)
/*    */   private static String adjustText(String text) {
/* 17 */     return protect(text);
/*    */   }
/*    */   
/*    */   private static String protect(String string) {
/* 21 */     if (!ModuleManager.nameProtect.isEnabled() || Module.mc.field_1724 == null)
/* 22 */       return string; 
/* 23 */     String me = Module.mc.method_1548().method_1676();
/* 24 */     if (string.contains(me) || (FriendManager.friends.stream().anyMatch(i -> i.contains(string)) && ((Boolean)NameProtect.hideFriends.getValue()).booleanValue())) {
/* 25 */       return string.replace(me, NameProtect.getCustomName());
/*    */     }
/* 27 */     return string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinTextVisitFactory.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */