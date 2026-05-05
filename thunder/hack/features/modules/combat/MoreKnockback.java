/*    */ package thunder.hack.features.modules.combat;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_1294;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2824;
/*    */ import net.minecraft.class_2848;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.math.MathUtility;
/*    */ import thunder.hack.utility.player.MovementUtility;
/*    */ 
/*    */ public class MoreKnockback extends Module {
/*    */   public Setting<Boolean> inMove;
/*    */   
/*    */   public MoreKnockback() {
/* 23 */     super("MoreKnockback", Module.Category.COMBAT);
/*    */ 
/*    */     
/* 26 */     this.inMove = new Setting("InMove", Boolean.valueOf(true));
/* 27 */     this.hurtTime = new Setting("HurtTime", Integer.valueOf(10), Integer.valueOf(0), Integer.valueOf(10));
/* 28 */     this.chance = new Setting("Chance", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(100));
/*    */   } public Setting<Integer> hurtTime; public Setting<Integer> chance;
/*    */   @EventHandler
/*    */   public void onSendPacket(PacketEvent.Send event) {
/* 32 */     if ((!MovementUtility.isMoving() || ((Boolean)this.inMove.getValue()).booleanValue()) && event
/* 33 */       .getPacket() instanceof class_2824 && 
/* 34 */       Criticals.getInteractType((class_2824)event.getPacket()) == Criticals.InteractType.ATTACK && 
/* 35 */       !(Criticals.getEntity((class_2824)event.getPacket()) instanceof net.minecraft.class_1511)) {
/* 36 */       class_1297 class_1297 = Criticals.getEntity((class_2824)event.getPacket()); if (class_1297 instanceof class_1309) { class_1309 lent = (class_1309)class_1297; if (lent.field_6235 <= ((Integer)this.hurtTime
/* 37 */           .getValue()).intValue() && 
/* 38 */           MathUtility.random(0.0F, 100.0F) >= (100 - ((Integer)this.chance.getValue()).intValue()) && 
/* 39 */           !canCrit()) {
/*    */           
/* 41 */           if (mc.field_1724.method_5624()) sendPacket((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12985)); 
/* 42 */           sendPacket((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 43 */           sendPacket((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12985));
/* 44 */           sendPacket((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12981));
/* 45 */           debug("wtap");
/* 46 */           mc.field_1724.method_5728(true);
/* 47 */           mc.field_1724.field_3919 = true;
/*    */         }  }
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean canCrit() {
/* 59 */     boolean reasonForSkipCrit = ((mc.field_1724.method_31549()).field_7479 || mc.field_1724.method_6128() || ModuleManager.elytraPlus.isEnabled() || mc.field_1724.method_6059(class_1294.field_5919) || mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538())).method_26204() == class_2246.field_10343 || mc.field_1724.method_5771() || mc.field_1724.method_5869());
/*    */     
/* 61 */     if (mc.field_1724.method_7261(0.5F) < 0.9F) {
/* 62 */       return false;
/*    */     }
/* 64 */     if (ModuleManager.criticals.isEnabled() && !ModuleManager.criticals.mode.is(Criticals.Mode.Grim)) {
/* 65 */       return true;
/*    */     }
/* 67 */     if (ModuleManager.criticals.isEnabled() && ModuleManager.criticals.mode.is(Criticals.Mode.Grim) && !mc.field_1724.method_24828()) {
/* 68 */       return true;
/*    */     }
/* 70 */     if (!reasonForSkipCrit) {
/* 71 */       return (!mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.0F);
/*    */     }
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\MoreKnockback.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */