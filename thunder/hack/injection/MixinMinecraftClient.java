/*     */ package thunder.hack.injection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1041;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3262;
/*     */ import net.minecraft.class_4071;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_500;
/*     */ import net.minecraft.class_542;
/*     */ import net.minecraft.class_6417;
/*     */ import net.minecraft.class_642;
/*     */ import net.minecraft.class_8518;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.lwjgl.glfw.GLFW;
/*     */ import org.lwjgl.glfw.GLFWImage;
/*     */ import org.lwjgl.system.MemoryStack;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.Redirect;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventAttack;
/*     */ import thunder.hack.events.impl.EventHandleBlockBreaking;
/*     */ import thunder.hack.events.impl.EventPostTick;
/*     */ import thunder.hack.events.impl.EventScreen;
/*     */ import thunder.hack.events.impl.EventTick;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.gui.clickui.ClickGUI;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.utility.render.WindowResizeCallback;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_310.class})
/*     */ public abstract class MixinMinecraftClient
/*     */ {
/*     */   @Shadow
/*     */   @Final
/*     */   private class_1041 field_1704;
/*     */   @Unique
/*  54 */   private String[] shittyServers = new String[] { "mineblaze", "musteryworld", "dexland", "masedworld", "vimeworld", "hypemc", "vimemc" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Shadow
/*     */   public abstract void method_1507(@Nullable class_437 paramclass_437);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"<init>"}, at = {@At("TAIL")})
/*     */   void postWindowInit(class_542 args, CallbackInfo ci) {
/*     */     try {
/*  68 */       FontRenderers.settings = FontRenderers.create(12.0F, "comfortaa");
/*  69 */       FontRenderers.modules = FontRenderers.create(15.0F, "comfortaa");
/*  70 */       FontRenderers.categories = FontRenderers.create(18.0F, "comfortaa");
/*  71 */       FontRenderers.thglitch = FontRenderers.create(36.0F, "glitched");
/*  72 */       FontRenderers.thglitchBig = FontRenderers.create(72.0F, "glitched");
/*  73 */       FontRenderers.monsterrat = FontRenderers.create(18.0F, "monsterrat");
/*  74 */       FontRenderers.sf_bold = FontRenderers.create(16.0F, "sf_bold");
/*  75 */       FontRenderers.sf_medium = FontRenderers.create(16.0F, "sf_medium");
/*  76 */       FontRenderers.sf_medium_mini = FontRenderers.create(12.0F, "sf_medium");
/*  77 */       FontRenderers.sf_medium_modules = FontRenderers.create(14.0F, "sf_medium");
/*  78 */       FontRenderers.sf_bold_mini = FontRenderers.create(14.0F, "sf_bold");
/*  79 */       FontRenderers.sf_bold_micro = FontRenderers.create(12.0F, "sf_bold");
/*  80 */       FontRenderers.profont = FontRenderers.create(16.0F, "profont");
/*  81 */       FontRenderers.icons = FontRenderers.create(20.0F, "icons");
/*  82 */       FontRenderers.mid_icons = FontRenderers.create(46.0F, "icons");
/*  83 */       FontRenderers.big_icons = FontRenderers.create(72.0F, "icons");
/*  84 */     } catch (Exception e) {
/*  85 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"tick"}, at = {@At("HEAD")})
/*     */   void preTickHook(CallbackInfo ci) {
/*  91 */     if (!Module.fullNullCheck()) ThunderHack.EVENT_BUS.post(new EventTick()); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"tick"}, at = {@At("RETURN")})
/*     */   void postTickHook(CallbackInfo ci) {
/*  96 */     if (!Module.fullNullCheck()) ThunderHack.EVENT_BUS.post(new EventPostTick()); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"onResolutionChanged"}, at = {@At("TAIL")})
/*     */   private void captureResize(CallbackInfo ci) {
/* 101 */     ((WindowResizeCallback)WindowResizeCallback.EVENT.invoker()).onResized((class_310)this, this.field_1704);
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"doItemPick"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void doItemPickHook(CallbackInfo ci) {
/* 107 */     if (ModuleManager.middleClick.isEnabled() && ((Boolean)ModuleManager.middleClick.antiPickUp.getValue()).booleanValue()) {
/* 108 */       ci.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"setOverlay"}, at = {@At("HEAD")})
/*     */   public void setOverlay(class_4071 overlay, CallbackInfo ci) {}
/*     */ 
/*     */   
/*     */   @Inject(method = {"setScreen"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void setScreenHookPre(class_437 screen, CallbackInfo ci) {
/* 119 */     if (Module.fullNullCheck())
/* 120 */       return;  EventScreen event = new EventScreen(screen);
/* 121 */     ThunderHack.EVENT_BUS.post(event);
/* 122 */     if (event.isCancelled() || (ClickGUI.close && screen == null)) ci.cancel(); 
/*     */   }
/*     */   
/*     */   @Inject(method = {"setScreen"}, at = {@At("RETURN")})
/*     */   public void setScreenHookPost(class_437 screen, CallbackInfo ci) {
/* 127 */     if (Module.fullNullCheck())
/* 128 */       return;  if (screen instanceof class_500) { class_500 mScreen = (class_500)screen; if (ModuleManager.antiServerAdd.isEnabled() && mScreen.method_2529() != null) {
/* 129 */         for (int i = 0; i < mScreen.method_2529().method_2984(); i++) {
/* 130 */           class_642 info = mScreen.method_2529().method_2982(i);
/* 131 */           for (String server : this.shittyServers) {
/* 132 */             if (info != null && info.field_3761 != null && info.field_3761.toLowerCase().contains(server.toLowerCase())) {
/* 133 */               mScreen.method_2529().method_2983(info);
/* 134 */               mScreen.method_2529().method_2987();
/* 135 */               method_1507(screen);
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @Redirect(method = {"<init>"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/client/util/Icons;)V"))
/*     */   private void onChangeIcon(class_1041 instance, class_3262 resourcePack, class_8518 icons) throws IOException {
/* 147 */     if (GLFW.glfwGetPlatform() == 393218) {
/* 148 */       class_6417.method_41718(icons.method_51420(resourcePack));
/*     */       
/*     */       return;
/*     */     } 
/* 152 */     setWindowIcon(ThunderHack.class.getResourceAsStream("/icon.png"), ThunderHack.class.getResourceAsStream("/icon.png"));
/*     */   }
/*     */   public void setWindowIcon(InputStream img16x16, InputStream img32x32) {
/*     */     
/* 156 */     try { MemoryStack memorystack = MemoryStack.stackPush(); 
/* 157 */       try { GLFWImage.Buffer buffer = GLFWImage.malloc(2, memorystack);
/* 158 */         List<InputStream> imgList = List.of(img16x16, img32x32);
/* 159 */         List<ByteBuffer> buffers = new ArrayList<>();
/*     */         
/* 161 */         for (int i = 0; i < imgList.size(); i++) {
/* 162 */           class_1011 nativeImage = class_1011.method_4309(imgList.get(i));
/* 163 */           ByteBuffer bytebuffer = MemoryUtil.memAlloc(nativeImage.method_4307() * nativeImage.method_4323() * 4);
/*     */           
/* 165 */           bytebuffer.asIntBuffer().put(nativeImage.method_48463());
/* 166 */           buffer.position(i);
/* 167 */           buffer.width(nativeImage.method_4307());
/* 168 */           buffer.height(nativeImage.method_4323());
/* 169 */           buffer.pixels(bytebuffer);
/*     */           
/* 171 */           buffers.add(bytebuffer);
/*     */         } 
/*     */         
/*     */         try {
/* 175 */           if (GLFW.glfwGetPlatform() != 393219) {
/* 176 */             GLFW.glfwSetWindowIcon(Module.mc.method_22683().method_4490(), buffer);
/*     */           }
/* 178 */         } catch (Exception exception) {}
/*     */         
/* 180 */         buffers.forEach(MemoryUtil::memFree);
/* 181 */         if (memorystack != null) memorystack.close();  } catch (Throwable throwable) { if (memorystack != null) try { memorystack.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"doAttack"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void doAttackHook(CallbackInfoReturnable<Boolean> cir) {
/* 187 */     EventAttack event = new EventAttack(null, true);
/* 188 */     ThunderHack.EVENT_BUS.post(event);
/* 189 */     if (event.isCancelled()) {
/* 190 */       cir.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */   
/*     */   @Inject(method = {"handleBlockBreaking"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void handleBlockBreakingHook(boolean breaking, CallbackInfo ci) {
/* 196 */     EventHandleBlockBreaking event = new EventHandleBlockBreaking();
/* 197 */     ThunderHack.EVENT_BUS.post(event);
/* 198 */     if (event.isCancelled())
/* 199 */       ci.cancel(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinMinecraftClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */