/*    */ package thunder.hack.injection;
/*    */ 
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2583;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_5250;
/*    */ import net.minecraft.class_5481;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_332.class})
/*    */ public class MixinDrawContext
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_4587 field_44657;
/*    */   
/*    */   public void drawTextHook(class_327 textRenderer, class_5481 text, int x, int y, int color, boolean shadow, CallbackInfoReturnable<Integer> cir) {
/* 26 */     class_5250 text1 = class_2561.method_43473();
/* 27 */     text.accept((i, style, codePoint) -> {
/*    */           text1.method_10852((class_2561)class_2561.method_43470(new String(Character.toChars(codePoint))).method_10862(style));
/*    */           
/*    */           return true;
/*    */         });
/* 32 */     FontRenderers.sf_medium.drawString(this.field_44657, text1.getString(), x, y, color);
/* 33 */     cir.setReturnValue(Integer.valueOf((int)FontRenderers.sf_medium.getStringWidth(text.toString())));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\MixinDrawContext.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */