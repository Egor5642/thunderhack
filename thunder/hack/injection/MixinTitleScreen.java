/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import java.net.URI;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_156;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_3675;
/*    */ import net.minecraft.class_410;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_442;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.gui.mainmenu.MainMenuScreen;
/*    */ import thunder.hack.gui.misc.DialogScreen;
/*    */ import thunder.hack.utility.render.TextureStorage;
/*    */ 
/*    */ 
/*    */ @Mixin({class_442.class})
/*    */ public class MixinTitleScreen
/*    */   extends class_437
/*    */ {
/*    */   protected MixinTitleScreen(class_2561 title) {
/* 30 */     super(title);
/*    */   }
/*    */   
/*    */   @Inject(method = {"init"}, at = {@At("RETURN")})
/*    */   public void postInitHook(CallbackInfo ci) {
/* 35 */     if (((Boolean)ClientSettings.customMainMenu.getValue()).booleanValue() && !(MainMenuScreen.getInstance()).confirm && ModuleManager.clickGui.getBind().getKey() != -1) {
/* 36 */       Module.mc.method_1507((class_437)MainMenuScreen.getInstance());
/*    */     }
/* 38 */     if (ModuleManager.clickGui.getBind().getKey() == -1) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       DialogScreen dialogScreen2 = new DialogScreen(TextureStorage.cutie, ClientSettings.isRu() ? "Спасибо что скачали ThunderHack!" : "Thank you for downloading ThunderHack!", ClientSettings.isRu() ? "Меню с функциями клиента открывается на клавишу - P" : "Menu with client modules is opened with the key - P", ClientSettings.isRu() ? "Зайти в майн" : "Join on minecraft", ClientSettings.isRu() ? "Закрыть майн" : "Close minecraft", () -> {
/*    */             ModuleManager.clickGui.setBind(class_3675.method_15981("key.keyboard.p").method_1444(), false, false);
/*    */             
/*    */             Module.mc.method_1507((class_437)MainMenuScreen.getInstance());
/*    */           }() -> {
/*    */             ModuleManager.clickGui.setBind(class_3675.method_15981("key.keyboard.p").method_1444(), false, false);
/*    */             
/*    */             Module.mc.method_1490();
/*    */           });
/*    */       
/* 54 */       DialogScreen dialogScreen1 = new DialogScreen(TextureStorage.questionPic, "Hello!", "What's your language?", "Русский", "English", () -> {
/*    */             ClientSettings.language.setValue(ClientSettings.Language.RU);
/*    */ 
/*    */ 
/*    */             
/*    */             Module.mc.method_1507((class_437)dialogScreen2);
/*    */           }() -> {
/*    */             ClientSettings.language.setValue(ClientSettings.Language.ENG);
/*    */ 
/*    */ 
/*    */             
/*    */             Module.mc.method_1507((class_437)dialogScreen2);
/*    */           });
/*    */ 
/*    */       
/* 69 */       Module.mc.method_1507((class_437)dialogScreen1);
/*    */     } 
/*    */     
/* 72 */     if (ThunderHack.isOutdated && !FabricLoader.getInstance().isDevelopmentEnvironment())
/* 73 */       Module.mc.method_1507((class_437)new class_410(confirm -> { if (confirm) {
/*    */                 class_156.method_668().method_673(URI.create("https://github.com/Pan4ur/ThunderHack-Recode/releases/download/latest/thunderhack-1.7.jar/"));
/*    */               } else {
/*    */                 Module.mc.method_1490();
/*    */               } 
/* 78 */             }class_2561.method_30163(String.valueOf(class_124.field_1061) + "You are using an outdated version of ThunderHack Recode"), class_2561.method_30163("Please update to the latest release"), class_2561.method_30163("Download"), class_2561.method_30163("Quit Game"))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinTitleScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */