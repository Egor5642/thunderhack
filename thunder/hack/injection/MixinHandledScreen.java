/*     */ package thunder.hack.injection;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_1263;
/*     */ import net.minecraft.class_1277;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1703;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_1733;
/*     */ import net.minecraft.class_1735;
/*     */ import net.minecraft.class_1747;
/*     */ import net.minecraft.class_1767;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_22;
/*     */ import net.minecraft.class_2480;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_308;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_465;
/*     */ import net.minecraft.class_9209;
/*     */ import net.minecraft.class_9288;
/*     */ import net.minecraft.class_9334;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import thunder.hack.core.Core;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.render.Tooltips;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ @Mixin({class_465.class})
/*     */ public abstract class MixinHandledScreen<T extends class_1703> extends class_437 implements class_3936<T> {
/*     */   @Unique
/*  52 */   private final Timer delayTimer = new Timer(); @Unique
/*     */   private Runnable postRender; @Shadow
/*     */   @Nullable
/*     */   protected class_1735 field_2787; @Shadow
/*     */   protected int field_2776; @Shadow
/*     */   protected int field_2800;
/*     */   
/*  59 */   protected MixinHandledScreen(class_2561 title) { super(title);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.clickableRects = new HashMap<>(); }
/*     */   @Inject(method = {"render"}, at = {@At("HEAD")}) private void drawScreenHook(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) { if (Module.fullNullCheck())
/*     */       return;  for (int i1 = 0; i1 < Module.mc.field_1724.field_7512.field_7761.size(); i1++) { class_1735 slot = (class_1735)Module.mc.field_1724.field_7512.field_7761.get(i1); if (method_2387(slot, mouseX, mouseY) && slot.method_7682() && ModuleManager.itemScroller.isEnabled() && shit() && attack() && this.delayTimer.passedMs(((Integer)ModuleManager.itemScroller.delay.getValue()).intValue())) { method_2383(slot, slot.field_7874, 0, class_1713.field_7794); this.delayTimer.reset(); }  }
/*     */      }
/* 104 */   private boolean shit() { return (class_3675.method_15987(class_310.method_1551().method_22683().method_4490(), 340) || class_3675.method_15987(class_310.method_1551().method_22683().method_4490(), 344)); } @Inject(method = {"render"}, at = {@At("TAIL")}) private void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) { if (Module.fullNullCheck())
/* 105 */       return;  if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && this.field_22787.field_1724.field_7498.method_34255().method_7960()) {
/* 106 */       if (Tooltips.hasItems(this.field_2787.method_7677()) && ((Boolean)Tooltips.storage.getValue()).booleanValue()) {
/* 107 */         renderShulkerToolTip(context, mouseX, mouseY, 0, 0, this.field_2787.method_7677());
/* 108 */       } else if (this.field_2787.method_7677().method_7909() == class_1802.field_8204 && ((Boolean)Tooltips.maps.getValue()).booleanValue()) {
/* 109 */         drawMapPreview(context, this.field_2787.method_7677(), mouseX, mouseY);
/*     */       } 
/*     */     }
/* 112 */     int xOffset = 0;
/* 113 */     int yOffset = 20;
/* 114 */     int stage = 0;
/*     */     
/* 116 */     if (ModuleManager.tooltips.isEnabled() && ((Boolean)ModuleManager.tooltips.shulkerRegear.getValue()).booleanValue()) {
/* 117 */       this.clickableRects.clear();
/* 118 */       for (int i1 = 0; i1 < Module.mc.field_1724.field_7512.field_7761.size(); i1++) {
/* 119 */         class_1735 slot = (class_1735)Module.mc.field_1724.field_7512.field_7761.get(i1);
/* 120 */         if (!slot.method_7677().method_7960()) {
/*     */           
/* 122 */           class_1792 class_1792 = slot.method_7677().method_7909(); if (class_1792 instanceof class_1747) { class_1747 bi = (class_1747)class_1792; if (bi.method_7711() instanceof class_2480 && 
/* 123 */               renderShulkerToolTip(context, xOffset, yOffset + 67, mouseX, mouseY, slot.method_7677())) {
/*     */               
/* 125 */               this.clickableRects.put(new Render2DEngine.Rectangle(xOffset, yOffset, (xOffset + 176), (yOffset + 67)), Integer.valueOf(slot.field_7874));
/* 126 */               yOffset += 67;
/* 127 */               if (stage == 0) {
/* 128 */                 if (yOffset + 67 >= Module.mc.method_22683().method_4502()) {
/* 129 */                   yOffset = 20;
/* 130 */                   xOffset = Module.mc.method_22683().method_4486() - 176;
/* 131 */                   stage = 1;
/*     */                 } 
/* 133 */               } else if (stage == 1) {
/* 134 */                 if (yOffset + 67 >= Module.mc.method_22683().method_4502()) {
/* 135 */                   yOffset = 20;
/* 136 */                   xOffset = 170;
/* 137 */                   stage = 2;
/*     */                 }
/*     */               
/* 140 */               } else if (yOffset + 67 >= Module.mc.method_22683().method_4502()) {
/* 141 */                 yOffset = 20;
/* 142 */                 xOffset = Module.mc.method_22683().method_4486() - 352;
/* 143 */                 stage = 0;
/*     */               } 
/*     */             }  }
/*     */         
/*     */         } 
/*     */       } 
/* 149 */       if (this.postRender != null) {
/* 150 */         this.postRender.run();
/* 151 */         this.postRender = null;
/*     */       } 
/*     */     }  }
/*     */    private boolean attack() {
/*     */     return Core.hold_mouse0;
/*     */   } private static final class_1799[] ITEMS = new class_1799[27]; private Map<Render2DEngine.Rectangle, Integer> clickableRects; @Inject(method = {"drawSlot(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/screen/slot/Slot;)V"}, at = {@At("TAIL")})
/*     */   protected void drawSlotHook(class_332 context, class_1735 slot, CallbackInfo ci) {
/* 158 */     if (ModuleManager.serverHelper.isEnabled() && ((Boolean)ModuleManager.serverHelper.aucHelper.getValue()).booleanValue())
/* 159 */       ModuleManager.serverHelper.onRenderChest(context, slot); 
/*     */   }
/*     */   
/*     */   public boolean renderShulkerToolTip(class_332 context, int offsetX, int offsetY, int mouseX, int mouseY, class_1799 stack) {
/*     */     try {
/* 164 */       class_9288 compoundTag = (class_9288)stack.method_57824(class_9334.field_49622);
/* 165 */       if (compoundTag == null) {
/* 166 */         return false;
/*     */       }
/* 168 */       float[] colors = { 1.0F, 1.0F, 1.0F };
/* 169 */       class_1792 focusedItem = stack.method_7909();
/* 170 */       if (focusedItem instanceof class_1747) { class_1747 bi = (class_1747)focusedItem; if (bi.method_7711() instanceof class_2480)
/*     */           try {
/* 172 */             Color c = new Color(((class_1767)Objects.<class_1767>requireNonNull(class_2480.method_10527(stack.method_7909()))).method_7787());
/* 173 */             colors = new float[] { c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getRed() / 255.0F, c.getAlpha() / 255.0F };
/* 174 */           } catch (NullPointerException npe) {
/* 175 */             colors = new float[] { 1.0F, 1.0F, 1.0F };
/*     */           }   }
/*     */       
/* 178 */       draw(context, compoundTag.method_57489().toList(), offsetX, offsetY, mouseX, mouseY, colors);
/* 179 */     } catch (Exception ignore) {
/* 180 */       return false;
/*     */     } 
/* 182 */     return true;
/*     */   }
/*     */   
/*     */   @Inject(method = {"drawMouseoverTooltip"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void onDrawMouseoverTooltip(class_332 context, int x, int y, CallbackInfo ci) {
/* 187 */     if (Module.fullNullCheck())
/* 188 */       return;  if (this.field_2787 != null && !this.field_2787.method_7677().method_7960() && this.field_22787.field_1724.field_7498.method_34255().method_7960() && 
/* 189 */       this.field_2787.method_7677().method_7909() == class_1802.field_8204 && ((Boolean)Tooltips.maps.getValue()).booleanValue()) ci.cancel();
/*     */   
/*     */   }
/*     */   
/*     */   @Unique
/*     */   private void draw(class_332 context, List<class_1799> itemStacks, int offsetX, int offsetY, int mouseX, int mouseY, float[] colors) {
/* 195 */     RenderSystem.disableDepthTest();
/* 196 */     GL11.glClear(256);
/*     */     
/* 198 */     offsetX += 8;
/* 199 */     offsetY -= 82;
/*     */     
/* 201 */     drawBackground(context, offsetX, offsetY, colors);
/*     */     
/* 203 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 204 */     class_308.method_24211();
/* 205 */     int row = 0;
/* 206 */     int i = 0;
/* 207 */     for (class_1799 itemStack : itemStacks) {
/* 208 */       context.method_51427(itemStack, offsetX + 8 + i * 18, offsetY + 7 + row * 18);
/* 209 */       context.method_51431(Module.mc.field_1772, itemStack, offsetX + 8 + i * 18, offsetY + 7 + row * 18);
/*     */       
/* 211 */       if (mouseX > offsetX + 8 + i * 18 && mouseX < offsetX + 28 + i * 18 && mouseY > offsetY + 7 + row * 18 && mouseY < offsetY + 27 + row * 18) {
/* 212 */         this.postRender = (() -> context.method_51437(this.field_22793, method_25408(Module.mc, itemStack), itemStack.method_32347(), mouseX, mouseY));
/*     */       }
/* 214 */       i++;
/* 215 */       if (i >= 9) {
/* 216 */         i = 0;
/* 217 */         row++;
/*     */       } 
/*     */     } 
/* 220 */     class_308.method_24210();
/* 221 */     RenderSystem.enableDepthTest();
/*     */   }
/*     */   
/*     */   private void drawBackground(class_332 context, int x, int y, float[] colors) {
/* 225 */     RenderSystem.disableBlend();
/* 226 */     RenderSystem.setShaderColor(colors[0], colors[1], colors[2], 1.0F);
/* 227 */     RenderSystem.texParameter(3553, 10240, 9729);
/* 228 */     RenderSystem.texParameter(3553, 10241, 9987);
/* 229 */     context.method_25290(TextureStorage.container, x, y, 0.0F, 0.0F, 176, 67, 176, 67);
/* 230 */     RenderSystem.enableBlend();
/*     */   }
/*     */   
/*     */   private void drawMapPreview(class_332 context, class_1799 stack, int x, int y) {
/* 234 */     RenderSystem.enableBlend();
/* 235 */     context.method_51448().method_22903();
/* 236 */     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 238 */     int y1 = y - 12;
/* 239 */     int x1 = x + 8;
/* 240 */     int z = 300;
/*     */     
/* 242 */     class_22 mapState = class_1806.method_8001(stack, (class_1937)this.field_22787.field_1687);
/*     */     
/* 244 */     if (mapState != null) {
/* 245 */       mapState.method_101((class_1657)this.field_22787.field_1724);
/*     */       
/* 247 */       x1 += 8;
/* 248 */       y1 += 8;
/* 249 */       z = 310;
/* 250 */       double scale = 0.65625D;
/* 251 */       context.method_51448().method_46416(x1, y1, z);
/* 252 */       context.method_51448().method_22905((float)scale, (float)scale, 0.0F);
/* 253 */       class_4597.class_4598 consumer = this.field_22787.method_22940().method_23000();
/* 254 */       this.field_22787.field_1773.method_3194().method_1773(context.method_51448(), (class_4597)consumer, (class_9209)stack.method_57824(class_9334.field_49646), mapState, false, 15728880);
/*     */     } 
/* 256 */     context.method_51448().method_22909();
/*     */   }
/*     */   
/*     */   @Inject(method = {"mouseClicked"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
/* 261 */     if (Module.fullNullCheck())
/* 262 */       return;  if (button == 2 && this.field_2787 != null && !this.field_2787.method_7677().method_7960() && this.field_22787.field_1724.field_7498.method_34255().method_7960()) {
/* 263 */       class_1799 itemStack = this.field_2787.method_7677();
/*     */       
/* 265 */       if (Tooltips.hasItems(itemStack) && ((Boolean)Tooltips.middleClickOpen.getValue()).booleanValue()) {
/*     */         
/* 267 */         Arrays.fill((Object[])ITEMS, class_1799.field_8037);
/* 268 */         class_9288 nbt = (class_9288)itemStack.method_57824(class_9334.field_49622);
/*     */         
/* 270 */         if (nbt != null) {
/* 271 */           List<class_1799> list = nbt.method_57489().toList();
/* 272 */           for (int i = 0; i < list.size(); i++) {
/* 273 */             ITEMS[i] = list.get(i);
/*     */           }
/*     */         } 
/* 276 */         this.field_22787.method_1507((class_437)new PeekScreen(new class_1733(0, this.field_22787.field_1724.method_31548(), (class_1263)new class_1277(ITEMS)), this.field_22787.field_1724.method_31548(), this.field_2787.method_7677().method_7964(), ((class_1747)this.field_2787.method_7677().method_7909()).method_7711()));
/* 277 */         cir.setReturnValue(Boolean.valueOf(true));
/*     */       } 
/*     */     } 
/* 280 */     for (Render2DEngine.Rectangle rect : this.clickableRects.keySet()) {
/* 281 */       if (rect.contains(mouseX, mouseY)) {
/* 282 */         if (((Boolean)ModuleManager.tooltips.shulkerRegearShiftMode.getValue()).booleanValue()) {
/* 283 */           Module.mc.field_1761.method_2906(Module.mc.field_1724.field_7512.field_7763, ((Integer)this.clickableRects.get(rect)).intValue(), 0, class_1713.field_7794, (class_1657)Module.mc.field_1724); continue;
/*     */         } 
/* 285 */         Module.mc.field_1761.method_2906(Module.mc.field_1724.field_7512.field_7763, ((Integer)this.clickableRects.get(rect)).intValue(), 0, class_1713.field_7790, (class_1657)Module.mc.field_1724);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Shadow
/*     */   protected abstract boolean method_2387(class_1735 paramclass_1735, double paramDouble1, double paramDouble2);
/*     */   
/*     */   @Shadow
/*     */   protected abstract void method_2383(class_1735 paramclass_1735, int paramInt1, int paramInt2, class_1713 paramclass_1713);
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinHandledScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */