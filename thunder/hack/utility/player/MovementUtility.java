/*     */ package thunder.hack.utility.player;
/*     */ 
/*     */ import net.minecraft.class_1294;
/*     */ import net.minecraft.class_3532;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.events.impl.EventMove;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ 
/*     */ public final class MovementUtility
/*     */ {
/*     */   public static boolean isMoving() {
/*  13 */     return (Module.mc.field_1724 != null && Module.mc.field_1687 != null && Module.mc.field_1724.field_3913 != null && (Module.mc.field_1724.field_3913.field_3905 != 0.0D || Module.mc.field_1724.field_3913.field_3907 != 0.0D));
/*     */   }
/*     */   
/*     */   public static double getSpeed() {
/*  17 */     return Math.hypot((Module.mc.field_1724.method_18798()).field_1352, (Module.mc.field_1724.method_18798()).field_1350);
/*     */   }
/*     */   
/*     */   public static double[] forward(double d) {
/*  21 */     float f = Module.mc.field_1724.field_3913.field_3905;
/*  22 */     float f2 = Module.mc.field_1724.field_3913.field_3907;
/*  23 */     float f3 = Module.mc.field_1724.method_36454();
/*  24 */     if (f != 0.0F) {
/*  25 */       if (f2 > 0.0F) {
/*  26 */         f3 += ((f > 0.0F) ? -45 : 45);
/*  27 */       } else if (f2 < 0.0F) {
/*  28 */         f3 += ((f > 0.0F) ? 45 : -45);
/*     */       } 
/*  30 */       f2 = 0.0F;
/*  31 */       if (f > 0.0F) {
/*  32 */         f = 1.0F;
/*  33 */       } else if (f < 0.0F) {
/*  34 */         f = -1.0F;
/*     */       } 
/*     */     } 
/*  37 */     double d2 = Math.sin(Math.toRadians((f3 + 90.0F)));
/*  38 */     double d3 = Math.cos(Math.toRadians((f3 + 90.0F)));
/*  39 */     double d4 = f * d * d3 + f2 * d * d2;
/*  40 */     double d5 = f * d * d2 - f2 * d * d3;
/*  41 */     return new double[] { d4, d5 };
/*     */   }
/*     */   
/*     */   public static void setMotion(double speed) {
/*  45 */     double forward = Module.mc.field_1724.field_3913.field_3905;
/*  46 */     double strafe = Module.mc.field_1724.field_3913.field_3907;
/*  47 */     float yaw = Module.mc.field_1724.method_36454();
/*  48 */     if (forward == 0.0D && strafe == 0.0D) {
/*  49 */       Module.mc.field_1724.method_18800(0.0D, (Module.mc.field_1724.method_18798()).field_1351, 0.0D);
/*     */     } else {
/*  51 */       if (forward != 0.0D) {
/*  52 */         if (strafe > 0.0D) {
/*  53 */           yaw += ((forward > 0.0D) ? -45 : 45);
/*  54 */         } else if (strafe < 0.0D) {
/*  55 */           yaw += ((forward > 0.0D) ? 45 : -45);
/*     */         } 
/*  57 */         strafe = 0.0D;
/*  58 */         if (forward > 0.0D) {
/*  59 */           forward = 1.0D;
/*  60 */         } else if (forward < 0.0D) {
/*  61 */           forward = -1.0D;
/*     */         } 
/*     */       } 
/*  64 */       double sin = class_3532.method_15374((float)Math.toRadians((yaw + 90.0F)));
/*  65 */       double cos = class_3532.method_15362((float)Math.toRadians((yaw + 90.0F)));
/*  66 */       Module.mc.field_1724.method_18800(forward * speed * cos + strafe * speed * sin, (Module.mc.field_1724.method_18798()).field_1351, forward * speed * sin - strafe * speed * cos);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float getMoveDirection() {
/*  71 */     double forward = Module.mc.field_1724.field_3913.field_3905;
/*  72 */     double strafe = Module.mc.field_1724.field_3913.field_3907;
/*     */     
/*  74 */     if (strafe > 0.0D) {
/*  75 */       strafe = 1.0D;
/*  76 */     } else if (strafe < 0.0D) {
/*  77 */       strafe = -1.0D;
/*     */     } 
/*     */     
/*  80 */     float yaw = Module.mc.field_1724.method_36454();
/*  81 */     if (forward == 0.0D && strafe == 0.0D) {
/*  82 */       return yaw;
/*     */     }
/*  84 */     if (forward != 0.0D) {
/*  85 */       if (strafe > 0.0D) {
/*  86 */         yaw += (forward > 0.0D) ? -45.0F : -135.0F;
/*  87 */       } else if (strafe < 0.0D) {
/*  88 */         yaw += (forward > 0.0D) ? 45.0F : 135.0F;
/*  89 */       } else if (forward < 0.0D) {
/*  90 */         yaw += 180.0F;
/*     */       } 
/*     */     }
/*  93 */     if (forward == 0.0D) {
/*  94 */       if (strafe > 0.0D) {
/*  95 */         yaw -= 90.0F;
/*  96 */       } else if (strafe < 0.0D) {
/*  97 */         yaw += 90.0F;
/*     */       } 
/*     */     }
/*     */     
/* 101 */     return yaw;
/*     */   }
/*     */   
/*     */   public static double[] forwardWithoutStrafe(double d) {
/* 105 */     float f3 = Module.mc.field_1724.method_36454();
/* 106 */     double d4 = d * Math.cos(Math.toRadians((f3 + 90.0F)));
/* 107 */     double d5 = d * Math.sin(Math.toRadians((f3 + 90.0F)));
/* 108 */     return new double[] { d4, d5 };
/*     */   }
/*     */   
/*     */   public static double getJumpSpeed() {
/* 112 */     double jumpSpeed = 0.3999999463558197D;
/* 113 */     if (Module.mc.field_1724.method_6059(class_1294.field_5913)) {
/* 114 */       double amplifier = Module.mc.field_1724.method_6112(class_1294.field_5913).method_5578();
/* 115 */       jumpSpeed += (amplifier + 1.0D) * 0.1D;
/*     */     } 
/* 117 */     return jumpSpeed;
/*     */   }
/*     */   
/*     */   public static void modifyEventSpeed(EventMove event, double d) {
/* 121 */     double d2 = Module.mc.field_1724.field_3913.field_3905;
/* 122 */     double d3 = Module.mc.field_1724.field_3913.field_3907;
/* 123 */     float f = Module.mc.field_1724.method_36454();
/* 124 */     if (d2 == 0.0D && d3 == 0.0D) {
/* 125 */       event.setX(0.0D);
/* 126 */       event.setZ(0.0D);
/*     */     } else {
/* 128 */       if (d2 != 0.0D) {
/* 129 */         if (d3 > 0.0D) {
/* 130 */           f += ((d2 > 0.0D) ? -45 : 45);
/* 131 */         } else if (d3 < 0.0D) {
/* 132 */           f += ((d2 > 0.0D) ? 45 : -45);
/*     */         } 
/*     */         
/* 135 */         d3 = 0.0D;
/* 136 */         if (d2 > 0.0D) {
/* 137 */           d2 = 1.0D;
/* 138 */         } else if (d2 < 0.0D) {
/* 139 */           d2 = -1.0D;
/*     */         } 
/*     */       } 
/* 142 */       double sin = Math.sin(Math.toRadians((f + 90.0F)));
/* 143 */       double cos = Math.cos(Math.toRadians((f + 90.0F)));
/*     */       
/* 145 */       event.setX(d2 * d * cos + d3 * d * sin);
/* 146 */       event.setZ(d2 * d * sin - d3 * d * cos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBaseMoveSpeed() {
/* 152 */     double d = 0.2873D;
/*     */     
/* 154 */     if (Module.fullNullCheck()) return d;
/*     */     
/* 156 */     if (Module.mc.field_1724.method_6059(class_1294.field_5904)) {
/* 157 */       int n = Module.mc.field_1724.method_6112(class_1294.field_5904).method_5578();
/* 158 */       d *= 1.0D + 0.2D * (n + 1);
/*     */     } 
/* 160 */     if (Module.mc.field_1724.method_6059(class_1294.field_5913)) {
/* 161 */       int n = Module.mc.field_1724.method_6112(class_1294.field_5913).method_5578();
/* 162 */       d /= 1.0D + 0.2D * (n + 1);
/*     */     } 
/* 164 */     if (Module.mc.field_1724.method_6059(class_1294.field_5909)) {
/* 165 */       int n = Module.mc.field_1724.method_6112(class_1294.field_5909).method_5578();
/* 166 */       d /= 1.0D + 0.2D * (n + 1);
/*     */     } 
/* 168 */     return d;
/*     */   }
/*     */   
/*     */   public static boolean sprintIsLegit(float yaw) {
/* 172 */     return (Math.abs(Math.abs(class_3532.method_15393(yaw)) - Math.abs(class_3532.method_15393(Managers.PLAYER.yaw))) < 40.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\player\MovementUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */