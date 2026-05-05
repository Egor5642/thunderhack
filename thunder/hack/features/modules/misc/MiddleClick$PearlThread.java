/*     */ package thunder.hack.features.modules.misc;
/*     */ 
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2828;
/*     */ import net.minecraft.class_2879;
/*     */ import net.minecraft.class_2886;
/*     */ import net.minecraft.class_746;
/*     */ import thunder.hack.core.manager.client.AsyncManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.combat.Aura;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PearlThread
/*     */   extends Thread
/*     */ {
/*     */   public class_746 player;
/*     */   int epSlot;
/*     */   int originalSlot;
/*     */   int delay;
/*     */   boolean inv;
/*     */   
/*     */   public PearlThread(class_746 entityPlayer, int epSlot, int originalSlot, int delay, boolean inventory) {
/* 142 */     this.player = entityPlayer;
/* 143 */     this.epSlot = epSlot;
/* 144 */     this.originalSlot = originalSlot;
/* 145 */     this.delay = delay;
/* 146 */     this.inv = inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 151 */     if (!this.inv) {
/* 152 */       InventoryUtility.switchTo(this.epSlot);
/* 153 */       AsyncManager.sleep(this.delay);
/* 154 */       InteractionUtility.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455()));
/* 155 */       Module.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(class_1268.field_5808));
/* 156 */       AsyncManager.sleep(this.delay);
/* 157 */       InventoryUtility.switchTo(this.originalSlot);
/*     */     } else {
/* 159 */       Module.mc.field_1761.method_2906(Module.mc.field_1724.field_7512.field_7763, this.epSlot, this.originalSlot, class_1713.field_7791, (class_1657)Module.mc.field_1724);
/* 160 */       AsyncManager.sleep(this.delay);
/* 161 */       if (ModuleManager.aura.isEnabled() && Aura.target != null)
/* 162 */         Module.mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2831(Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455(), Module.mc.field_1724.method_24828())); 
/* 163 */       InteractionUtility.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, Module.mc.field_1724.method_36454(), Module.mc.field_1724.method_36455()));
/* 164 */       Module.mc.field_1724.field_3944.method_52787((class_2596)new class_2879(class_1268.field_5808));
/* 165 */       AsyncManager.sleep(this.delay);
/* 166 */       Module.mc.field_1761.method_2906(Module.mc.field_1724.field_7512.field_7763, this.epSlot, this.originalSlot, class_1713.field_7791, (class_1657)Module.mc.field_1724);
/*     */     } 
/* 168 */     super.run();
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\MiddleClick$PearlThread.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */