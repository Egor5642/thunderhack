/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.nio.file.Path;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_2583;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_437;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.CommandManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.core.manager.client.ProxyManager;
/*     */ import thunder.hack.events.impl.ClientClickEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.gui.misc.DialogScreen;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_437.class})
/*     */ public abstract class MixinScreen
/*     */ {
/*     */   @Inject(method = {"handleTextClick"}, at = {@At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", ordinal = 1, remap = false)}, cancellable = true)
/*     */   private void onRunCommand(class_2583 style, CallbackInfoReturnable<Boolean> cir) {
/*  44 */     ClientClickEvent clientClickEvent = (ClientClickEvent)Objects.requireNonNull(style.method_10970()); if (clientClickEvent instanceof ClientClickEvent) { ClientClickEvent clientClickEvent1 = clientClickEvent; if (clientClickEvent1.method_10844().startsWith(Managers.COMMAND.getPrefix()))
/*     */         try {
/*  46 */           CommandManager manager = Managers.COMMAND;
/*  47 */           manager.getDispatcher().execute(style.method_10970().method_10844().substring(Managers.COMMAND.getPrefix().length()), manager.getSource());
/*  48 */           cir.setReturnValue(Boolean.valueOf(true));
/*  49 */         } catch (CommandSyntaxException commandSyntaxException) {}  }
/*     */   
/*     */   }
/*     */   
/*     */   @Inject(method = {"filesDragged"}, at = {@At("HEAD")})
/*     */   public void filesDragged(List<Path> paths, CallbackInfo ci) {
/*  55 */     String configPath = ((Path)paths.get(0)).toString();
/*  56 */     File cfgFile = new File(configPath);
/*  57 */     String fileName = cfgFile.getName();
/*     */     
/*  59 */     if (fileName.contains(".th")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       DialogScreen dialogScreen = new DialogScreen(TextureStorage.questionPic, ClientSettings.isRu() ? "Обнаружен конфиг!" : "Config detected!", ClientSettings.isRu() ? ("Ты действительно хочешь загрузить " + fileName + "?") : ("Are you sure you want to load " + fileName + "?"), ClientSettings.isRu() ? "Да" : "Yes", ClientSettings.isRu() ? "Нет" : "No", () -> {
/*     */             Managers.MODULE.onUnload("none");
/*     */             
/*     */             Managers.CONFIG.load(cfgFile);
/*     */             Managers.MODULE.onLoad("none");
/*     */             Module.mc.method_1507(null);
/*     */           }() -> Module.mc.method_1507(null));
/*  72 */       Module.mc.method_1507((class_437)dialogScreen);
/*     */     }
/*  74 */     else if (fileName.contains(".txt")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  80 */       DialogScreen dialogScreen2 = new DialogScreen(TextureStorage.questionPic, ClientSettings.isRu() ? "Обнаружен текстовый файл!" : "Text file detected!", ClientSettings.isRu() ? ("Импортировать файл " + fileName + " как") : ("Import file " + fileName + " as"), ClientSettings.isRu() ? "Прокси" : "Proxies", ClientSettings.isRu() ? "Забить" : "Cancel", () -> {
/*     */             try {
/*     */               BufferedReader reader = new BufferedReader(new FileReader(cfgFile));
/*     */ 
/*     */               
/*     */               try { while (reader.ready()) {
/*     */                   String[] line = reader.readLine().split(":");
/*     */                   
/*     */                   String ip = line[0];
/*     */                   
/*     */                   String port = line[1];
/*     */                   String login = line[2];
/*     */                   String password = line[3];
/*     */                   int p = 80;
/*     */                   
/*     */                   try { p = Integer.parseInt(port); }
/*  96 */                   catch (Exception e) { LogUtils.getLogger().warn(e.getMessage()); }
/*     */                    Managers.PROXY.addProxy(new ProxyManager.ThProxy("Proxy" + (int)MathUtility.random(0.0F, 10000.0F), ip, p, login, password));
/*     */                 }  reader.close(); }
/*     */               catch (Throwable throwable) { try { reader.close(); }
/*     */                 catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                  throw throwable; }
/*     */             
/* 103 */             } catch (Exception exception) {}
/*     */ 
/*     */             
/*     */             Module.mc.method_1507(null);
/*     */           }() -> Module.mc.method_1507(null));
/*     */ 
/*     */       
/* 110 */       Module.mc.method_1507((class_437)dialogScreen2);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderPanoramaBackground"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void renderPanoramaBackgroundHook(class_332 context, float delta, CallbackInfo ci) {
/* 116 */     if (((Boolean)ClientSettings.customPanorama.getValue()).booleanValue() && Module.mc.field_1687 == null) {
/* 117 */       ci.cancel();
/* 118 */       Render2DEngine.drawMainMenuShader(context.method_51448(), 0.0F, 0.0F, Module.mc.method_22683().method_4486(), Module.mc.method_22683().method_4502());
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderInGameBackground"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void renderInGameBackground(CallbackInfo info) {
/* 124 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.disableGuiBackGround.getValue()).booleanValue()) {
/* 125 */       info.cancel();
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"renderBackground(Lnet/minecraft/client/gui/DrawContext;IIF)V"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void onRenderBackground(class_332 context, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
/* 131 */     if (ModuleManager.noRender.isEnabled() && ((Boolean)ModuleManager.noRender.disableGuiBackGround.getValue()).booleanValue() && Module.mc.field_1687 != null)
/* 132 */       ci.cancel(); 
/*     */   }
/*     */   
/*     */   @Shadow
/*     */   public abstract void method_25423(class_310 paramclass_310, int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */