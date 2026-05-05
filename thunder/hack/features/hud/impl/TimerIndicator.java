/*    */ package thunder.hack.features.hud.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_332;
/*    */ import thunder.hack.features.hud.HudElement;
/*    */ import thunder.hack.features.modules.client.HudEditor;
/*    */ import thunder.hack.features.modules.movement.Timer;
/*    */ import thunder.hack.gui.font.FontRenderers;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ import thunder.hack.utility.render.animation.EaseOutCirc;
/*    */ 
/*    */ public class TimerIndicator extends HudElement {
/* 14 */   private final EaseOutCirc timerAnimation = new EaseOutCirc();
/*    */   
/*    */   public TimerIndicator() {
/* 17 */     super("TimerIndicator", 60, 10);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRender2D(class_332 context) {
/* 22 */     super.onRender2D(context);
/*    */     
/* 24 */     if (HudEditor.hudStyle.is(HudEditor.HudStyle.Blurry)) {
/* 25 */       Render2DEngine.drawRoundedBlur(context.method_51448(), getPosX(), getPosY(), 65.0F, 15.0F, 3.0F, ((ColorSetting)HudEditor.blurColor.getValue()).getColorObject());
/* 26 */       Render2DEngine.drawRect(context.method_51448(), getPosX(), getPosY(), 65.0F * Timer.energy, 15.0F, 3.0F, 0.4F);
/* 27 */       FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), (Timer.energy >= 0.99F) ? "100%" : ("" + (int)Math.ceil((Timer.energy * 100.0F)) + "%"), (getPosX() + 32.0F), (getPosY() + 5.5F), (new Color(200, 200, 200, 255)).getRGB());
/* 28 */       setBounds(getPosX(), getPosY(), 65.0F, 15.0F);
/*    */     } else {
/* 30 */       Render2DEngine.drawGradientBlurredShadow(context.method_51448(), getPosX() - 1.0F, getPosY() - 1.0F, 62.0F, 12.0F, 6, HudEditor.getColor(90), HudEditor.getColor(180), HudEditor.getColor(0), HudEditor.getColor(270));
/* 31 */       Render2DEngine.drawRect(context.method_51448(), getPosX(), getPosY(), 60.0F, 10.0F, new Color(-1644167168, true));
/* 32 */       Render2DEngine.draw2DGradientRect(context.method_51448(), getPosX(), getPosY(), getPosX() + 60.0F * Timer.energy, getPosY() + 10.0F, HudEditor.getColor(90), HudEditor.getColor(180), HudEditor.getColor(0), HudEditor.getColor(270));
/* 33 */       Render2DEngine.drawBlurredShadow(context.method_51448(), getPosX() + 20.0F, getPosY(), 22.0F, 10.0F, 6, new Color(1191182336, true));
/* 34 */       FontRenderers.sf_bold_mini.drawCenteredString(context.method_51448(), (Timer.energy >= 0.99F) ? "100%" : ("" + (int)Math.ceil((Timer.energy * 100.0F)) + "%"), (getPosX() + 31.0F), (getPosY() + 3.5F), (new Color(200, 200, 200, 255)).getRGB());
/* 35 */       setBounds(getPosX(), getPosY(), 60.0F, 10.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 41 */     this.timerAnimation.update();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\hud\impl\TimerIndicator.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */