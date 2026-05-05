/*    */ package thunder.hack.features.modules.render;
/*    */ public class Shaders extends Module {
/*    */   private final Setting<SettingGroup> select;
/*    */   private final Setting<Boolean> hands;
/*    */   private final Setting<Boolean> players;
/*    */   private final Setting<Boolean> self;
/*    */   private final Setting<Boolean> friends;
/*    */   private final Setting<Boolean> crystals;
/*    */   private final Setting<Boolean> creatures;
/*    */   private final Setting<Boolean> monsters;
/*    */   private final Setting<Boolean> ambients;
/*    */   private final Setting<Boolean> others;
/*    */   public Setting<ShaderManager.Shader> mode;
/*    */   public Setting<ShaderManager.Shader> handsMode;
/*    */   public final Setting<Integer> maxRange;
/*    */   public final Setting<Float> factor;
/*    */   
/*    */   public Shaders() {
/* 19 */     super("Shaders", Module.Category.RENDER);
/*    */ 
/*    */     
/* 22 */     this.select = new Setting("Select", new SettingGroup(false, 0));
/* 23 */     this.hands = (new Setting("Hands", Boolean.valueOf(true))).addToGroup(this.select);
/* 24 */     this.players = (new Setting("Players", Boolean.valueOf(true))).addToGroup(this.select);
/* 25 */     this.self = (new Setting("Self", Boolean.valueOf(true), v -> ((Boolean)this.players.getValue()).booleanValue())).addToGroup(this.select);
/* 26 */     this.friends = (new Setting("Friends", Boolean.valueOf(true))).addToGroup(this.select);
/* 27 */     this.crystals = (new Setting("Crystals", Boolean.valueOf(true))).addToGroup(this.select);
/* 28 */     this.creatures = (new Setting("Creatures", Boolean.valueOf(false))).addToGroup(this.select);
/* 29 */     this.monsters = (new Setting("Monsters", Boolean.valueOf(false))).addToGroup(this.select);
/* 30 */     this.ambients = (new Setting("Ambients", Boolean.valueOf(false))).addToGroup(this.select);
/* 31 */     this.others = (new Setting("Others", Boolean.valueOf(false))).addToGroup(this.select);
/*    */     
/* 33 */     this.mode = new Setting("Mode", ShaderManager.Shader.Default);
/* 34 */     this.handsMode = new Setting("HandsMode", ShaderManager.Shader.Default);
/*    */     
/* 36 */     this.maxRange = new Setting("MaxRange", Integer.valueOf(64), Integer.valueOf(16), Integer.valueOf(256), v -> (((Boolean)this.players.getValue()).booleanValue() || ((Boolean)this.crystals.getValue()).booleanValue() || ((Boolean)this.friends.getValue()).booleanValue() || ((Boolean)this.creatures.getValue()).booleanValue() || ((Boolean)this.monsters.getValue()).booleanValue() || ((Boolean)this.ambients.getValue()).booleanValue() || ((Boolean)this.others.getValue()).booleanValue()));
/* 37 */     this.factor = new Setting("GradientFactor", Float.valueOf(2.0F), Float.valueOf(0.0F), Float.valueOf(20.0F), v -> (this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)));
/* 38 */     this.gradient = new Setting("Gradient", Float.valueOf(2.0F), Float.valueOf(0.0F), Float.valueOf(20.0F), v -> (this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)));
/* 39 */     this.alpha2 = new Setting("GradientAlpha", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255), v -> (this.mode.is(ShaderManager.Shader.Gradient) || this.handsMode.is(ShaderManager.Shader.Gradient)));
/* 40 */     this.lineWidth = new Setting("LineWidth", Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(500));
/* 41 */     this.quality = new Setting("Quality", Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(6));
/* 42 */     this.octaves = new Setting("SmokeOctaves", Integer.valueOf(10), Integer.valueOf(5), Integer.valueOf(30));
/* 43 */     this.fillAlpha = new Setting("FillAlpha", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255));
/* 44 */     this.glow = new Setting("SmokeGlow", Boolean.valueOf(true));
/*    */     
/* 46 */     this.colors = new Setting("Colors", new SettingGroup(false, 0));
/* 47 */     this.outlineColor = (new Setting("Outline", new ColorSetting(-2013200640))).addToGroup(this.colors);
/* 48 */     this.outlineColor1 = (new Setting("SmokeOutline", new ColorSetting(-2013200640), v -> (this.mode.is(ShaderManager.Shader.Smoke) || this.handsMode.is(ShaderManager.Shader.Smoke)))).addToGroup(this.colors);
/* 49 */     this.outlineColor2 = (new Setting("SmokeOutline2", new ColorSetting(-2013200640), v -> (this.mode.is(ShaderManager.Shader.Smoke) || this.handsMode.is(ShaderManager.Shader.Smoke)))).addToGroup(this.colors);
/* 50 */     this.fillColor1 = (new Setting("Fill", new ColorSetting(-2013200640))).addToGroup(this.colors);
/* 51 */     this.fillColor2 = (new Setting("SmokeFill", new ColorSetting(-2013200640))).addToGroup(this.colors);
/* 52 */     this.fillColor3 = (new Setting("SmokeFil2", new ColorSetting(-2013200640))).addToGroup(this.colors);
/*    */   } public final Setting<Float> gradient; public final Setting<Integer> alpha2; public final Setting<Integer> lineWidth; public final Setting<Integer> quality; public final Setting<Integer> octaves; public final Setting<Integer> fillAlpha; public final Setting<Boolean> glow; private final Setting<SettingGroup> colors; public final Setting<ColorSetting> outlineColor; public final Setting<ColorSetting> outlineColor1; public final Setting<ColorSetting> outlineColor2; public final Setting<ColorSetting> fillColor1; public final Setting<ColorSetting> fillColor2; public final Setting<ColorSetting> fillColor3;
/*    */   public boolean shouldRender(class_1297 entity) {
/* 55 */     if (entity == null) {
/* 56 */       return false;
/*    */     }
/* 58 */     if (mc.field_1724 == null) {
/* 59 */       return false;
/*    */     }
/* 61 */     if (mc.field_1724.method_5707(entity.method_19538()) > this.maxRange.getPow2Value()) {
/* 62 */       return false;
/*    */     }
/* 64 */     if (entity instanceof class_1657) {
/* 65 */       if (entity == mc.field_1724 && !((Boolean)this.self.getValue()).booleanValue())
/* 66 */         return false; 
/* 67 */       if (Managers.FRIEND.isFriend((class_1657)entity))
/* 68 */         return ((Boolean)this.friends.getValue()).booleanValue(); 
/* 69 */       return ((Boolean)this.players.getValue()).booleanValue();
/*    */     } 
/*    */     
/* 72 */     if (entity instanceof net.minecraft.class_1511) {
/* 73 */       return ((Boolean)this.crystals.getValue()).booleanValue();
/*    */     }
/* 75 */     switch (entity.method_5864().method_5891()) { case field_6294: case field_6300: case field_6302: case field_6303: case field_24460:  }  return (
/*    */ 
/*    */ 
/*    */       
/* 79 */       (Boolean)this.others.getValue()).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRender3D(class_4587 matrices) {
/* 84 */     if (((Boolean)this.hands.getValue()).booleanValue()) {
/* 85 */       Managers.SHADER.renderShader(() -> ((IGameRenderer)mc.field_1773).irenderHand(mc.field_1773.method_19418(), Render3DEngine.getTickDelta(), matrices.method_23760().method_23761()), (ShaderManager.Shader)this.handsMode.getValue());
/*    */     }
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 90 */     Managers.SHADER.reloadShaders();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Shaders.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */