/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.class_437;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.gui.thundergui.ThunderGui;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ 
/*    */ public final class ThunderHackGui extends Module {
/* 11 */   public static final Setting<ColorSetting> onColor1 = new Setting("OnColor1", new ColorSetting((new Color(71, 0, 117, 255)).getRGB()));
/* 12 */   public static final Setting<ColorSetting> onColor2 = new Setting("OnColor2", new ColorSetting((new Color(32, 1, 96, 255)).getRGB()));
/* 13 */   public static final Setting<Float> scrollSpeed = new Setting("ScrollSpeed", Float.valueOf(1.0F), Float.valueOf(0.1F), Float.valueOf(2.0F));
/*    */   
/*    */   public ThunderHackGui() {
/* 16 */     super("ThunderGui", Module.Category.CLIENT);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 21 */     mc.method_1507((class_437)ThunderGui.getThunderGui());
/* 22 */     disable();
/*    */   }
/*    */   
/*    */   public static Color getColorByTheme(int id) {
/* 26 */     switch (id) { case 0: case 1: case 2: case 3: case 8: case 4: case 5: case 6: case 7: case 9:  }  return 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 36 */       new Color(37, 27, 41, 250);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\ThunderHackGui.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */