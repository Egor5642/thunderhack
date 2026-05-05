/*    */ package thunder.hack.features.modules.movement;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_2537;
/*    */ import thunder.hack.events.impl.EventCollision;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class Avoid extends Module {
/*    */   private final Setting<Boolean> voidAir;
/*    */   private final Setting<Boolean> cactus;
/*    */   private final Setting<Boolean> fire;
/*    */   private final Setting<Boolean> berryBush;
/*    */   
/*    */   public Avoid() {
/* 16 */     super("Avoid", Module.Category.MOVEMENT);
/*    */ 
/*    */     
/* 19 */     this.voidAir = new Setting("Void", Boolean.valueOf(true));
/* 20 */     this.cactus = new Setting("Cactus", Boolean.valueOf(true));
/* 21 */     this.fire = new Setting("Fire", Boolean.valueOf(true));
/* 22 */     this.berryBush = new Setting("BerryBush", Boolean.valueOf(true));
/* 23 */     this.powderSnow = new Setting("PowderSnow", Boolean.valueOf(true));
/* 24 */     this.unloaded = new Setting("Unloaded", Boolean.valueOf(true));
/* 25 */     this.lava = new Setting("Lava", Boolean.valueOf(true));
/* 26 */     this.plate = new Setting("Plate", Boolean.valueOf(true));
/* 27 */     this.trapString = new Setting("Tripwire", Boolean.valueOf(true));
/*    */   } private final Setting<Boolean> powderSnow; private final Setting<Boolean> unloaded; private final Setting<Boolean> lava; private final Setting<Boolean> plate; private final Setting<Boolean> trapString;
/*    */   @EventHandler
/*    */   public void onCollide(EventCollision e) {
/* 31 */     if (fullNullCheck())
/* 32 */       return;  class_2248 b = e.getState().method_26204();
/*    */     
/* 34 */     boolean avoidUnloaded = (!mc.field_1687.method_8393(e.getPos().method_10263() >> 4, e.getPos().method_10260() >> 4) && ((Boolean)this.unloaded.getValue()).booleanValue());
/* 35 */     boolean avoidVoid = (e.getPos().method_10264() < mc.field_1687.method_31607() && ((Boolean)this.voidAir.getValue()).booleanValue());
/* 36 */     boolean avoidCactus = (b == class_2246.field_10029 && ((Boolean)this.cactus.getValue()).booleanValue());
/* 37 */     boolean avoidFire = ((b == class_2246.field_10036 || b == class_2246.field_22089) && ((Boolean)this.fire.getValue()).booleanValue());
/* 38 */     boolean avoidBerryBush = (b == class_2246.field_16999 && ((Boolean)this.berryBush.getValue()).booleanValue());
/* 39 */     boolean avoidSusSnow = (b == class_2246.field_27879 && ((Boolean)this.powderSnow.getValue()).booleanValue());
/* 40 */     boolean avoidLava = (b == class_2246.field_10164 && ((Boolean)this.lava.getValue()).booleanValue());
/* 41 */     boolean avoidPlate = ((b instanceof net.minecraft.class_2440 || b == class_2246.field_10224 || b == class_2246.field_10582) && ((Boolean)this.plate.getValue()).booleanValue());
/* 42 */     boolean avoidTrapString = (b instanceof net.minecraft.class_2538 && ((Boolean)e.getState().method_11654((class_2769)class_2537.field_11669)).booleanValue() && ((Boolean)this.trapString.getValue()).booleanValue());
/*    */     
/* 44 */     if (avoidUnloaded || avoidFire || avoidCactus || avoidLava || avoidBerryBush || avoidSusSnow || avoidPlate || avoidTrapString || avoidVoid)
/* 45 */       e.setState(class_2246.field_10566.method_9564()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\Avoid.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */