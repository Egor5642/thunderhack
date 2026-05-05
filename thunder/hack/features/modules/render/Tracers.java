/*    */ package thunder.hack.features.modules.render;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_4587;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ import thunder.hack.utility.render.Render3DEngine;
/*    */ 
/*    */ public class Tracers extends Module {
/*    */   private final Setting<Float> height;
/*    */   
/*    */   public Tracers() {
/* 16 */     super("Tracers", Module.Category.RENDER);
/*    */ 
/*    */     
/* 19 */     this.height = new Setting("Height", Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(2.0F));
/*    */     
/* 21 */     this.color = new Setting("Color", new ColorSetting(new Color(-1812004864, true)));
/* 22 */     this.friendColor = new Setting("Friends", new ColorSetting(new Color(-1827152291, true)));
/*    */   } private final Setting<ColorSetting> color; private final Setting<ColorSetting> friendColor;
/*    */   public void onRender3D(class_4587 stack) {
/* 25 */     for (class_1657 player : Managers.ASYNC.getAsyncPlayers()) {
/* 26 */       if (player == mc.field_1724) {
/*    */         continue;
/*    */       }
/* 29 */       Color color1 = ((ColorSetting)this.color.getValue()).getColorObject();
/*    */       
/* 31 */       if (Managers.FRIEND.isFriend(player)) {
/* 32 */         color1 = ((ColorSetting)this.friendColor.getValue()).getColorObject();
/*    */       }
/* 34 */       double x1 = mc.field_1724.field_6014 + (mc.field_1724.method_23317() - mc.field_1724.field_6014) * Render3DEngine.getTickDelta();
/* 35 */       double y1 = mc.field_1724.method_18381(mc.field_1724.method_18376()) + mc.field_1724.field_6036 + (mc.field_1724.method_23318() - mc.field_1724.field_6036) * Render3DEngine.getTickDelta();
/* 36 */       double z1 = mc.field_1724.field_5969 + (mc.field_1724.method_23321() - mc.field_1724.field_5969) * Render3DEngine.getTickDelta();
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       class_243 vec2 = (new class_243(0.0D, 0.0D, 75.0D)).method_1037(-((float)Math.toRadians(mc.field_1773.method_19418().method_19329()))).method_1024(-((float)Math.toRadians(mc.field_1773.method_19418().method_19330()))).method_1031(x1, y1, z1);
/*    */       
/* 43 */       double x = player.field_6014 + (player.method_23317() - player.field_6014) * Render3DEngine.getTickDelta();
/* 44 */       double y = player.field_6036 + (player.method_23318() - player.field_6036) * Render3DEngine.getTickDelta();
/* 45 */       double z = player.field_5969 + (player.method_23321() - player.field_5969) * Render3DEngine.getTickDelta();
/*    */       
/* 47 */       Render3DEngine.drawLineDebug(vec2, new class_243(x, y + ((Float)this.height.getValue()).floatValue(), z), color1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Tracers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */