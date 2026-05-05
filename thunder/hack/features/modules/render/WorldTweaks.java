/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2761;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*    */ import thunder.hack.setting.impl.ColorSetting;
/*    */ 
/*    */ public class WorldTweaks
/*    */   extends Module {
/*    */   public WorldTweaks() {
/* 15 */     super("WorldTweaks", Module.Category.RENDER);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.ctime = new Setting("ChangeTime", Boolean.valueOf(false));
/* 23 */     this.ctimeVal = new Setting("Time", Integer.valueOf(21), Integer.valueOf(0), Integer.valueOf(23));
/*    */   }
/*    */   public static final Setting<BooleanSettingGroup> fogModify = new Setting("FogModify", new BooleanSettingGroup(true)); public static final Setting<Integer> fogStart = (new Setting("FogStart", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(256))).addToGroup(fogModify);
/*    */   public static final Setting<Integer> fogEnd = (new Setting("FogEnd", Integer.valueOf(64), Integer.valueOf(10), Integer.valueOf(256))).addToGroup(fogModify);
/*    */   
/*    */   public void onEnable() {
/* 29 */     this.oldTime = mc.field_1687.method_8510();
/*    */   }
/*    */   public static final Setting<ColorSetting> fogColor = (new Setting("FogColor", new ColorSetting(new Color(11075839)))).addToGroup(fogModify); public final Setting<Boolean> ctime; public final Setting<Integer> ctimeVal; long oldTime;
/*    */   
/*    */   public void onDisable() {
/* 34 */     mc.field_1687.method_8435(this.oldTime);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void onPacketReceive(PacketEvent.Receive event) {
/* 39 */     if (event.getPacket() instanceof class_2761 && ((Boolean)this.ctime.getValue()).booleanValue()) {
/* 40 */       this.oldTime = ((class_2761)event.getPacket()).method_11871();
/* 41 */       event.cancel();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 47 */     if (((Boolean)this.ctime.getValue()).booleanValue()) mc.field_1687.method_8435((((Integer)this.ctimeVal.getValue()).intValue() * 1000)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\WorldTweaks.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */