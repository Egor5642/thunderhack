/*    */ package thunder.hack.features.modules.player;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_1802;
/*    */ import thunder.hack.injection.accesors.IMinecraftClient;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class FastUse extends Module {
/*    */   private final Setting<Integer> delay;
/*    */   public Setting<Boolean> blocks;
/*    */   
/*    */   public FastUse() {
/* 12 */     super("FastUse", Module.Category.PLAYER);
/*    */ 
/*    */     
/* 15 */     this.delay = new Setting("Delay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(5));
/* 16 */     this.blocks = new Setting("Blocks", Boolean.valueOf(false));
/* 17 */     this.crystals = new Setting("Crystals", Boolean.valueOf(false));
/* 18 */     this.xp = new Setting("XP", Boolean.valueOf(false));
/* 19 */     this.all = new Setting("All", Boolean.valueOf(true));
/*    */   }
/*    */   public Setting<Boolean> crystals; public Setting<Boolean> xp; public Setting<Boolean> all;
/*    */   public void onUpdate() {
/* 23 */     if (check(mc.field_1724.method_6047().method_7909()) && ((IMinecraftClient)mc).getUseCooldown() > ((Integer)this.delay.getValue()).intValue())
/* 24 */       ((IMinecraftClient)mc).setUseCooldown(((Integer)this.delay.getValue()).intValue()); 
/*    */   }
/*    */   
/*    */   public boolean check(class_1792 item) {
/* 28 */     return ((item instanceof net.minecraft.class_1747 && ((Boolean)this.blocks.getValue()).booleanValue()) || (item == class_1802.field_8301 && ((Boolean)this.crystals
/* 29 */       .getValue()).booleanValue()) || (item == class_1802.field_8287 && ((Boolean)this.xp
/* 30 */       .getValue()).booleanValue()) || ((Boolean)this.all
/* 31 */       .getValue()).booleanValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\FastUse.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */