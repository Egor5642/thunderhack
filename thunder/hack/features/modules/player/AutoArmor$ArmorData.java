/*     */ package thunder.hack.features.modules.player;
/*     */ 
/*     */ import net.minecraft.class_1304;
/*     */ import thunder.hack.features.modules.Module;
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
/*     */ public class ArmorData
/*     */ {
/*     */   private class_1304 equipmentSlot;
/*     */   private int armorSlot;
/*     */   private int prevProtection;
/*     */   private int newSlot;
/*     */   private int newProtection;
/*     */   
/*     */   public ArmorData(class_1304 equipmentSlot, int armorSlot, int prevProtection, int newSlot, int newProtection) {
/* 168 */     this.equipmentSlot = equipmentSlot;
/* 169 */     this.armorSlot = armorSlot;
/* 170 */     this.prevProtection = prevProtection;
/* 171 */     this.newSlot = newSlot;
/* 172 */     this.newProtection = newProtection;
/*     */   }
/*     */   
/*     */   public int getArmorSlot() {
/* 176 */     return this.armorSlot;
/*     */   }
/*     */   
/*     */   public int getPrevProt() {
/* 180 */     return this.prevProtection;
/*     */   }
/*     */   
/*     */   public void setPrevProt(int prevProtection) {
/* 184 */     this.prevProtection = prevProtection;
/*     */   }
/*     */   
/*     */   public int getNewSlot() {
/* 188 */     return this.newSlot;
/*     */   }
/*     */   
/*     */   public void setNewSlot(int newSlot) {
/* 192 */     this.newSlot = newSlot;
/*     */   }
/*     */   
/*     */   public int getNewProtection() {
/* 196 */     return this.newProtection;
/*     */   }
/*     */   
/*     */   public void setNewProtection(int newProtection) {
/* 200 */     this.newProtection = newProtection;
/*     */   }
/*     */   
/*     */   public class_1304 getEquipmentSlot() {
/* 204 */     return this.equipmentSlot;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 208 */     setPrevProt(AutoArmor.this.getProtection(Module.mc.field_1724.method_31548().method_5438(getArmorSlot())));
/* 209 */     setNewSlot(-1);
/* 210 */     setNewProtection(-1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AutoArmor$ArmorData.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */