/*    */ package thunder.hack.features.modules.player;
/*    */ import net.minecraft.class_124;
/*    */ import thunder.hack.core.manager.world.WayPointManager;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AutoRespawn extends Module {
/*    */   private final Setting<Boolean> deathcoords;
/*    */   private final Setting<Boolean> autokit;
/*    */   private final Setting<String> kit;
/*    */   private final Setting<Boolean> autohome;
/*    */   
/*    */   public AutoRespawn() {
/* 13 */     super("AutoRespawn", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 16 */     this.deathcoords = new Setting("deathcoords", Boolean.valueOf(true));
/* 17 */     this.autokit = new Setting("Auto Kit", Boolean.valueOf(false));
/* 18 */     this.kit = new Setting("kit name", "kitname", v -> ((Boolean)this.autokit.getValue()).booleanValue());
/* 19 */     this.autohome = new Setting("Auto Home", Boolean.valueOf(false));
/* 20 */     this.autowaypoint = new Setting("Auto Waypoint", Boolean.valueOf(false));
/*    */ 
/*    */     
/* 23 */     this.waypointCount = 0;
/* 24 */     this.timer = new Timer();
/*    */   }
/*    */   private final Setting<Boolean> autowaypoint; private boolean flag; private int waypointCount; private final Timer timer;
/*    */   public void onUpdate() {
/* 28 */     if (fullNullCheck())
/*    */       return; 
/* 30 */     if (this.timer.passedMs(2100L)) {
/* 31 */       this.timer.reset();
/*    */     }
/*    */     
/* 34 */     if (mc.field_1755 instanceof net.minecraft.class_418) {
/* 35 */       if (this.flag) {
/* 36 */         this.waypointCount++;
/* 37 */         if (((Boolean)this.deathcoords.getValue()).booleanValue())
/* 38 */           sendMessage(String.valueOf(class_124.field_1065) + "[PlayerDeath] " + String.valueOf(class_124.field_1065) + String.valueOf(class_124.field_1054) + " " + (int)mc.field_1724.method_23317() + " " + (int)mc.field_1724.method_23318()); 
/* 39 */         if (((Boolean)this.autowaypoint.getValue()).booleanValue()) {
/* 40 */           WayPointManager.WayPoint wp = new WayPointManager.WayPoint((int)mc.field_1724.method_23317(), (int)mc.field_1724.method_23318(), (int)mc.field_1724.method_23321(), "Death №" + this.waypointCount, mc.method_1542() ? "SinglePlayer" : (mc.method_1562().method_45734()).field_3761, mc.field_1687.method_27983().method_29177().method_12832());
/* 41 */           Managers.WAYPOINT.addWayPoint(wp);
/*    */         } 
/* 43 */         mc.field_1724.method_7331();
/* 44 */         mc.method_1507(null);
/*    */         
/* 46 */         Managers.ASYNC.run(() -> { if (((Boolean)this.autokit.getValue()).booleanValue() && mc.field_1724 != null) mc.field_1724.field_3944.method_45730("kit " + (String)this.kit.getValue());  if (((Boolean)this.autohome.getValue()).booleanValue() && mc.field_1724 != null) mc.field_1724.field_3944.method_45730("home");  }1000L);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 54 */         this.flag = false;
/*    */       } 
/*    */     } else {
/* 57 */       this.flag = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AutoRespawn.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */