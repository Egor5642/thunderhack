/*     */ package thunder.hack.features.modules.movement;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2848;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class AutoPilot extends Module {
/*  16 */   private final Setting<String> coordsInput = new Setting("Coords", "90, 50, -1000");
/*  17 */   private final Setting<Integer> cloudHeight = new Setting("CloudHeight", Integer.valueOf(190), Integer.valueOf(64), Integer.valueOf(320));
/*  18 */   private final Setting<Integer> rocketDelay = new Setting("RocketDelay", Integer.valueOf(30), Integer.valueOf(10), Integer.valueOf(80));
/*     */   
/*  20 */   private int timer = 0; private double targetX; private double targetY;
/*     */   private double targetZ;
/*     */   private boolean validCoords = false;
/*     */   
/*     */   public AutoPilot() {
/*  25 */     super("AutoPilot", Module.Category.MOVEMENT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  30 */     this.timer = 0;
/*  31 */     parseCoordinates();
/*  32 */     if (!this.validCoords) {
/*  33 */       disable();
/*     */     }
/*     */   }
/*     */   
/*     */   private void parseCoordinates() {
/*     */     try {
/*  39 */       String[] split = ((String)this.coordsInput.getValue()).replace(" ", "").split(",");
/*  40 */       if (split.length >= 3) {
/*  41 */         this.targetX = Double.parseDouble(split[0]);
/*  42 */         this.targetY = Double.parseDouble(split[1]);
/*  43 */         this.targetZ = Double.parseDouble(split[2]);
/*  44 */         this.validCoords = true;
/*  45 */       } else if (split.length == 2) {
/*  46 */         this.targetX = Double.parseDouble(split[0]);
/*  47 */         this.targetY = 100.0D;
/*  48 */         this.targetZ = Double.parseDouble(split[1]);
/*  49 */         this.validCoords = true;
/*     */       } else {
/*  51 */         this.validCoords = false;
/*     */       } 
/*  53 */     } catch (Exception e) {
/*  54 */       this.validCoords = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  60 */     if (mc.field_1724 == null || mc.field_1687 == null || !this.validCoords)
/*     */       return; 
/*  62 */     parseCoordinates();
/*     */     
/*  64 */     double diffX = this.targetX - mc.field_1724.method_23317();
/*  65 */     double diffY = this.targetY - mc.field_1724.method_23318();
/*  66 */     double diffZ = this.targetZ - mc.field_1724.method_23321();
/*     */     
/*  68 */     double distXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
/*     */     
/*  70 */     if ((distXZ < 10.0D && Math.abs(diffY) < 10.0D) || (distXZ < 20.0D && mc.field_1724.method_24828())) {
/*  71 */       disable();
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     float yawToTarget = (float)Math.toDegrees(Math.atan2(-diffX, diffZ));
/*  76 */     mc.field_1724.method_36456(yawToTarget);
/*     */     
/*  78 */     equipElytra();
/*     */ 
/*     */     
/*  81 */     if (!mc.field_1724.method_6128()) {
/*  82 */       if (mc.field_1724.method_24828()) {
/*  83 */         mc.field_1724.method_6043();
/*     */ 
/*     */       
/*     */       }
/*  87 */       else if ((mc.field_1724.method_18798()).field_1351 < -0.05D) {
/*  88 */         mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12982));
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  96 */     double cruiseY = (((Integer)this.cloudHeight.getValue()).intValue() + 15);
/*     */     
/*  98 */     if (distXZ > 150.0D) {
/*     */       
/* 100 */       if (mc.field_1724.method_23318() < cruiseY - 3.0D) {
/* 101 */         mc.field_1724.method_36457(-40.0F);
/* 102 */       } else if (mc.field_1724.method_23318() > cruiseY + 5.0D) {
/* 103 */         mc.field_1724.method_36457(10.0F);
/*     */       } else {
/* 105 */         mc.field_1724.method_36457(0.0F);
/*     */       } 
/*     */     } else {
/*     */       
/* 109 */       float pitchToTarget = (float)-Math.toDegrees(Math.atan2(diffY, distXZ));
/* 110 */       if (pitchToTarget > 35.0F) pitchToTarget = 35.0F; 
/* 111 */       mc.field_1724.method_36457(pitchToTarget);
/*     */     } 
/*     */ 
/*     */     
/* 115 */     this.timer++;
/* 116 */     double speed3D = mc.field_1724.method_18798().method_1033();
/*     */     
/* 118 */     if (distXZ > 150.0D) {
/* 119 */       if (this.timer >= 20 && (this.timer >= ((Integer)this.rocketDelay.getValue()).intValue() || speed3D < 0.7D)) {
/* 120 */         useFirework();
/* 121 */         this.timer = 0;
/*     */       }
/*     */     
/*     */     }
/* 125 */     else if (this.timer >= 20 && speed3D < 0.4D && mc.field_1724.method_23318() > this.targetY + 10.0D) {
/* 126 */       useFirework();
/* 127 */       this.timer = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void equipElytra() {
/* 133 */     if (mc.field_1724.method_31548().method_7372(2).method_7909() == class_1802.field_8833)
/*     */       return; 
/* 135 */     for (int i = 0; i < 36; i++) {
/* 136 */       if (mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8833) {
/* 137 */         int slotId = (i < 9) ? (i + 36) : i;
/* 138 */         mc.field_1761.method_2906(0, slotId, 0, class_1713.field_7790, (class_1657)mc.field_1724);
/* 139 */         mc.field_1761.method_2906(0, 6, 0, class_1713.field_7790, (class_1657)mc.field_1724);
/* 140 */         mc.field_1761.method_2906(0, slotId, 0, class_1713.field_7790, (class_1657)mc.field_1724);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void useFirework() {
/* 147 */     int fwSlot = -1;
/* 148 */     for (int i = 0; i < 36; i++) {
/* 149 */       if (mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8639) {
/* 150 */         fwSlot = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 155 */     if (fwSlot != -1) {
/* 156 */       int currentSlot = (mc.field_1724.method_31548()).field_7545;
/* 157 */       if (fwSlot < 9) {
/* 158 */         (mc.field_1724.method_31548()).field_7545 = fwSlot;
/* 159 */         mc.field_1761.method_2919((class_1657)mc.field_1724, class_1268.field_5808);
/* 160 */         (mc.field_1724.method_31548()).field_7545 = currentSlot;
/*     */       } else {
/* 162 */         mc.field_1761.method_2906(0, fwSlot, currentSlot, class_1713.field_7791, (class_1657)mc.field_1724);
/* 163 */         mc.field_1761.method_2919((class_1657)mc.field_1724, class_1268.field_5808);
/* 164 */         mc.field_1761.method_2906(0, fwSlot, currentSlot, class_1713.field_7791, (class_1657)mc.field_1724);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRender3D(class_4587 stack) {
/* 171 */     if (mc.field_1724 == null || !this.validCoords)
/*     */       return; 
/* 173 */     class_243 startPos = mc.field_1724.method_33571();
/* 174 */     class_243 endPos = new class_243(this.targetX, this.targetY, this.targetZ);
/*     */     
/*     */     try {
/* 177 */       Render3DEngine.drawLine(startPos, endPos, Color.GREEN);
/* 178 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\movement\AutoPilot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */