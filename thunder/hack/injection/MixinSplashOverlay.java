/*     */ package thunder.hack.injection;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_4011;
/*     */ import net.minecraft.class_425;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ @Mixin({class_425.class})
/*     */ public abstract class MixinSplashOverlay {
/*     */   @Final
/*     */   @Shadow
/*     */   private boolean field_18219;
/*     */   @Shadow
/*     */   private float field_17770;
/*     */   @Shadow
/*  32 */   private long field_17771 = -1L; @Shadow
/*  33 */   private long field_18220 = -1L;
/*     */   @Final
/*     */   @Shadow
/*     */   private class_4011 field_17767;
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("HEAD")}, cancellable = true)
/*  39 */   public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) { if (ModuleManager.unHook.isEnabled() || !((Boolean)ClientSettings.customLoadingScreen.getValue()).booleanValue())
/*     */       return; 
/*  41 */     ci.cancel();
/*  42 */     renderCustom(context, mouseX, mouseY, delta); } @Final
/*     */   @Shadow
/*     */   private Consumer<Optional<Throwable>> field_18218; public void renderCustom(class_332 context, int mouseX, int mouseY, float delta) {
/*     */     float h;
/*  46 */     int i = Module.mc.method_22683().method_4486();
/*  47 */     int j = Module.mc.method_22683().method_4502();
/*  48 */     long l = class_156.method_658();
/*  49 */     if (this.field_18219 && this.field_18220 == -1L) {
/*  50 */       this.field_18220 = l;
/*     */     }
/*     */     
/*  53 */     float f = (this.field_17771 > -1L) ? ((float)(l - this.field_17771) / 1000.0F) : -1.0F;
/*  54 */     float g = (this.field_18220 > -1L) ? ((float)(l - this.field_18220) / 500.0F) : -1.0F;
/*     */ 
/*     */     
/*  57 */     if (f >= 1.0F) {
/*  58 */       if (Module.mc.field_1755 != null) {
/*  59 */         Module.mc.field_1755.method_25394(context, 0, 0, delta);
/*     */       }
/*  61 */       int m = class_3532.method_15386((1.0F - class_3532.method_15363(f - 1.0F, 0.0F, 1.0F)) * 255.0F);
/*  62 */       context.method_25294(0, 0, i, j, withAlpha((new Color(458773)).getRGB(), m));
/*  63 */       h = 1.0F - class_3532.method_15363(f - 1.0F, 0.0F, 1.0F);
/*  64 */     } else if (this.field_18219) {
/*  65 */       if (Module.mc.field_1755 != null && g < 1.0F) {
/*  66 */         Module.mc.field_1755.method_25394(context, mouseX, mouseY, delta);
/*     */       }
/*  68 */       int m = class_3532.method_15384(class_3532.method_15350(g, 0.15D, 1.0D) * 255.0D);
/*  69 */       context.method_25294(0, 0, i, j, withAlpha((new Color(458773)).getRGB(), m));
/*  70 */       h = class_3532.method_15363(g, 0.0F, 1.0F);
/*     */     } else {
/*  72 */       int i1 = (new Color(458773)).getRGB();
/*  73 */       float m = (i1 >> 16 & 0xFF) / 255.0F;
/*  74 */       float n = (i1 >> 8 & 0xFF) / 255.0F;
/*  75 */       float o = (i1 & 0xFF) / 255.0F;
/*  76 */       GlStateManager._clearColor(m, n, o, 1.0F);
/*  77 */       GlStateManager._clear(16384, class_310.field_1703);
/*  78 */       h = 1.0F;
/*     */     } 
/*     */     
/*  81 */     int k = (int)(context.method_51421() * 0.5D);
/*  82 */     int p = (int)(context.method_51443() * 0.5D);
/*     */     
/*  84 */     RenderSystem.enableBlend();
/*  85 */     RenderSystem.blendFunc(770, 1);
/*     */     
/*  87 */     RenderSystem.setShaderColor(0.1F, 0.1F, 0.1F, h);
/*  88 */     context.method_25290(TextureStorage.thLogo, k - 150, p - 35, 0.0F, 0.0F, 300, 70, 300, 70);
/*  89 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, h);
/*  90 */     Render2DEngine.addWindow(context.method_51448(), (k - 150), (p - 35), (k - 150) + 300.0F * this.field_17770, (p + 35), 1.0D);
/*  91 */     context.method_25290(TextureStorage.thLogo, k - 150, p - 35, 0.0F, 0.0F, 300, 70, 300, 70);
/*  92 */     Render2DEngine.popWindow();
/*     */     
/*  94 */     float t = this.field_17767.method_18229();
/*  95 */     this.field_17770 = class_3532.method_15363(this.field_17770 * 0.95F + t * 0.050000012F, 0.0F, 1.0F);
/*     */     
/*  97 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*  98 */     RenderSystem.defaultBlendFunc();
/*  99 */     RenderSystem.disableBlend();
/*     */     
/* 101 */     if (f >= 2.0F) {
/* 102 */       Module.mc.method_18502(null);
/*     */     }
/*     */     
/* 105 */     if (this.field_17771 == -1L && this.field_17767.method_18787() && (!this.field_18219 || g >= 2.0F)) {
/*     */       try {
/* 107 */         this.field_17767.method_18849();
/* 108 */         this.field_18218.accept(Optional.empty());
/* 109 */       } catch (Throwable var23) {
/* 110 */         this.field_18218.accept(Optional.of(var23));
/*     */       } 
/*     */       
/* 113 */       this.field_17771 = class_156.method_658();
/* 114 */       if (Module.mc.field_1755 != null) {
/* 115 */         Module.mc.field_1755.method_25423(Module.mc, Module.mc.method_22683().method_4486(), Module.mc.method_22683().method_4502());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int withAlpha(int color, int alpha) {
/* 121 */     return color & 0xFFFFFF | alpha << 24;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinSplashOverlay.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */