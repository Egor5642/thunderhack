/*     */ package thunder.hack.features.modules.misc;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2868;
/*     */ import net.minecraft.class_2879;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_742;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventEntitySpawn;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.combat.Aura;
/*     */ import thunder.hack.features.modules.combat.AutoCrystal;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.player.MovementUtility;
/*     */ 
/*     */ public class PearlChaser extends Module {
/*     */   private final Setting<BooleanSettingGroup> stopMotion;
/*     */   private final Setting<Boolean> legitStop;
/*     */   private final Setting<Boolean> pauseAura;
/*     */   private final Setting<Boolean> onlyOnGround;
/*     */   private final Setting<Boolean> noMove;
/*     */   private final Setting<Boolean> onlyTarget;
/*     */   
/*     */   public PearlChaser() {
/*  42 */     super("PearlChaser", Module.Category.MISC);
/*     */ 
/*     */     
/*  45 */     this.stopMotion = new Setting("StopMotion", new BooleanSettingGroup(false));
/*  46 */     this.legitStop = (new Setting("LegitStop", Boolean.valueOf(false))).addToGroup(this.stopMotion);
/*  47 */     this.pauseAura = new Setting("PauseAura", Boolean.valueOf(false));
/*  48 */     this.onlyOnGround = new Setting("OnlyOnGround", Boolean.valueOf(false));
/*  49 */     this.noMove = new Setting("NoMove", Boolean.valueOf(false));
/*  50 */     this.onlyTarget = new Setting("OnlyTarget", Boolean.valueOf(false));
/*     */ 
/*     */     
/*  53 */     this.delayTimer = new Timer();
/*     */ 
/*     */ 
/*     */     
/*  57 */     this.targets = new HashMap<>();
/*     */   } private Runnable postSyncAction; private final Timer delayTimer; private class_2338 targetBlock; private int lastPearlId; private int lastOurPearlId; private HashMap<class_1657, Long> targets;
/*     */   @EventHandler
/*     */   public void onEntitySpawn(EventEntitySpawn e) {
/*  61 */     if (e.getEntity() instanceof net.minecraft.class_1684)
/*  62 */       mc.field_1687.method_18456().stream()
/*  63 */         .min(Comparator.comparingDouble(p -> p.method_5707(e.getEntity().method_19538())))
/*  64 */         .ifPresent(player -> {
/*     */             if (player.equals(mc.field_1724)) {
/*     */               this.lastOurPearlId = e.getEntity().method_5628();
/*     */             }
/*     */           }); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = -100)
/*     */   public void onSync(EventSync event) {
/*  73 */     if (((Boolean)this.onlyTarget.getValue()).booleanValue()) {
/*  74 */       if (Aura.target != null && ModuleManager.aura.isEnabled()) { class_1297 class_1297 = Aura.target; if (class_1297 instanceof class_1657) { class_1657 pl = (class_1657)class_1297; if (!this.targets.containsKey(pl))
/*  75 */             this.targets.put(pl, Long.valueOf(System.currentTimeMillis()));  }
/*     */          }
/*  77 */        if (AutoCrystal.target != null && ModuleManager.autoCrystal.isEnabled()) { class_1657 class_1657 = AutoCrystal.target; if (class_1657 instanceof class_1657) { class_1657 pl = class_1657; if (!this.targets.containsKey(pl))
/*  78 */             this.targets.put(pl, Long.valueOf(System.currentTimeMillis()));  }
/*     */          }
/*  80 */        (new HashMap<>(this.targets)).forEach((k, v) -> {
/*     */             if (System.currentTimeMillis() - v.longValue() > 10000L) {
/*     */               this.targets.remove(k);
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  87 */     if (mc.field_1724.method_6032() < 5.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  91 */     if (!this.delayTimer.passedMs(1000L)) {
/*     */       return;
/*     */     }
/*  94 */     for (Iterator<class_1297> iterator = mc.field_1687.method_18112().iterator(); iterator.hasNext(); ) { class_1297 ent = iterator.next();
/*  95 */       if (!(ent instanceof net.minecraft.class_1684) || 
/*  96 */         ent.method_5628() == this.lastPearlId || ent.method_5628() == this.lastOurPearlId)
/*  97 */         continue;  mc.field_1687.method_18456().stream()
/*  98 */         .filter(e -> (this.targets.containsKey(e) || !((Boolean)this.onlyTarget.getValue()).booleanValue()))
/*  99 */         .min(Comparator.comparingDouble(p -> p.method_5707(ent.method_19538())))
/* 100 */         .ifPresent(player -> {
/*     */             if (!player.equals(mc.field_1724)) {
/*     */               this.targetBlock = calcTrajectory(ent);
/*     */               
/*     */               this.lastPearlId = ent.method_5628();
/*     */             } 
/*     */           }); }
/*     */ 
/*     */     
/* 109 */     if (this.targetBlock == null) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     if (mc.field_1724.method_5707(this.targetBlock.method_46558()) < 49.0D) {
/*     */       return;
/*     */     }
/* 116 */     float rotationPitch = (float)-Math.toDegrees(calcTrajectory(this.targetBlock));
/* 117 */     float rotationYaw = (float)Math.toDegrees(Math.atan2((this.targetBlock.method_10260() + 0.5F) - mc.field_1724.method_23321(), (this.targetBlock.method_10263() + 0.5F) - mc.field_1724.method_23317())) - 90.0F;
/* 118 */     class_2338 tracedBP = checkTrajectory(rotationYaw, rotationPitch);
/*     */     
/* 120 */     if (tracedBP == null || this.targetBlock.method_19770((class_2374)tracedBP.method_46558()) > 36.0D) {
/*     */       return;
/*     */     }
/* 123 */     if (((Boolean)this.pauseAura.getValue()).booleanValue() && ModuleManager.aura.isEnabled()) {
/* 124 */       ModuleManager.aura.pause();
/*     */     }
/* 126 */     if (((Boolean)this.onlyOnGround.getValue()).booleanValue() && !mc.field_1724.method_24828()) {
/*     */       return;
/*     */     }
/* 129 */     if (((Boolean)this.noMove.getValue()).booleanValue() && MovementUtility.isMoving()) {
/*     */       return;
/*     */     }
/* 132 */     if (((BooleanSettingGroup)this.stopMotion.getValue()).isEnabled()) {
/* 133 */       if (!((Boolean)this.legitStop.getValue()).booleanValue())
/* 134 */         mc.field_1724.method_18800(0.0D, 0.0D, 0.0D); 
/* 135 */       mc.field_1690.field_1894.method_23481(false);
/* 136 */       mc.field_1690.field_1881.method_23481(false);
/* 137 */       mc.field_1690.field_1913.method_23481(false);
/* 138 */       mc.field_1690.field_1849.method_23481(false);
/* 139 */       mc.field_1724.field_3913.field_3905 = 0.0F;
/* 140 */       mc.field_1724.field_3913.field_3907 = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 144 */     sendMessage(ClientSettings.isRu() ? ("Догоняем перл! Позиция X:" + 
/* 145 */         tracedBP.method_10263() + " Y:" + tracedBP.method_10264() + " Z:" + tracedBP.method_10260() + " Углы Y:" + rotationYaw + " P:" + rotationPitch) : ("Chasing pearl on X:" + 
/* 146 */         tracedBP.method_10263() + " Y:" + tracedBP.method_10264() + " Z:" + tracedBP.method_10260() + " Angle Y:" + rotationYaw + " P:" + rotationPitch));
/*     */     
/* 148 */     mc.field_1724.method_36456(rotationYaw);
/* 149 */     mc.field_1724.method_36457(MathUtility.clamp(rotationPitch, -89.0F, 89.0F));
/*     */     
/* 151 */     float yaw = mc.field_1724.method_36454();
/* 152 */     float pitch = mc.field_1724.method_36455();
/*     */     
/* 154 */     this.postSyncAction = (() -> {
/*     */         int epSlot = findEPSlot();
/*     */         
/*     */         int originalSlot = (mc.field_1724.method_31548()).field_7545;
/*     */         if (epSlot != -1) {
/*     */           (mc.field_1724.method_31548()).field_7545 = epSlot;
/*     */           sendPacket((class_2596)new class_2868(epSlot));
/*     */           sendSequencedPacket(());
/*     */           sendPacket((class_2596)new class_2879(class_1268.field_5808));
/*     */           (mc.field_1724.method_31548()).field_7545 = originalSlot;
/*     */           sendPacket((class_2596)new class_2868(originalSlot));
/*     */         } 
/*     */       });
/* 167 */     this.targetBlock = null;
/* 168 */     this.delayTimer.reset();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPostSync(EventPostSync event) {
/* 173 */     if (this.postSyncAction != null) {
/* 174 */       this.postSyncAction.run();
/* 175 */       this.postSyncAction = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int findEPSlot() {
/* 180 */     int epSlot = -1;
/* 181 */     if (mc.field_1724.method_6047().method_7909() == class_1802.field_8634)
/* 182 */       epSlot = (mc.field_1724.method_31548()).field_7545; 
/* 183 */     if (epSlot == -1)
/* 184 */       for (int l = 0; l < 9; l++) {
/* 185 */         if (mc.field_1724.method_31548().method_5438(l).method_7909() == class_1802.field_8634) {
/* 186 */           epSlot = l; break;
/*     */         } 
/*     */       }  
/* 189 */     return epSlot;
/*     */   }
/*     */   
/*     */   private float calcTrajectory(@NotNull class_2338 bp) {
/* 193 */     double a = Math.hypot((bp.method_10263() + 0.5F) - mc.field_1724.method_23317(), (bp.method_10260() + 0.5F) - mc.field_1724.method_23321());
/* 194 */     double y = 6.125D * ((bp.method_10264() + 1.0F) - mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376()));
/* 195 */     y = 0.05000000074505806D * (0.05000000074505806D * a * a + y);
/* 196 */     y = Math.sqrt(9.37890625D - y);
/* 197 */     double d = 3.0625D - y;
/* 198 */     y = Math.atan2(d * d + y, 0.05000000074505806D * a);
/* 199 */     d = Math.atan2(d, 0.05000000074505806D * a);
/* 200 */     return (float)Math.min(y, d);
/*     */   }
/*     */   
/*     */   private class_2338 calcTrajectory(class_1297 e) {
/* 204 */     return traceTrajectory(e.method_23317(), e.method_23318(), e.method_23321(), (e.method_18798()).field_1352, (e.method_18798()).field_1351, (e.method_18798()).field_1350);
/*     */   }
/*     */   
/*     */   private class_2338 checkTrajectory(float yaw, float pitch) {
/* 208 */     if (Float.isNaN(pitch))
/* 209 */       return null; 
/* 210 */     float yawRad = yaw / 180.0F * 3.1415927F;
/* 211 */     float pitchRad = pitch / 180.0F * 3.1415927F;
/* 212 */     double x = mc.field_1724.method_23317() - (class_3532.method_15362(yawRad) * 0.16F);
/* 213 */     double y = mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376()) - 0.1000000014901161D;
/* 214 */     double z = mc.field_1724.method_23321() - (class_3532.method_15374(yawRad) * 0.16F);
/* 215 */     double motionX = (-class_3532.method_15374(yawRad) * class_3532.method_15362(pitchRad) * 0.4F);
/* 216 */     double motionY = (-class_3532.method_15374(pitchRad) * 0.4F);
/* 217 */     double motionZ = (class_3532.method_15362(yawRad) * class_3532.method_15362(pitchRad) * 0.4F);
/* 218 */     float distance = class_3532.method_15355((float)(motionX * motionX + motionY * motionY + motionZ * motionZ));
/* 219 */     motionX /= distance;
/* 220 */     motionY /= distance;
/* 221 */     motionZ /= distance;
/* 222 */     motionX *= 1.5D;
/* 223 */     motionY *= 1.5D;
/* 224 */     motionZ *= 1.5D;
/* 225 */     if (!mc.field_1724.method_24828()) motionY += mc.field_1724.method_18798().method_10214(); 
/* 226 */     return traceTrajectory(x, y, z, motionX, motionY, motionZ);
/*     */   }
/*     */ 
/*     */   
/*     */   private class_2338 traceTrajectory(double x, double y, double z, double mx, double my, double mz) {
/* 231 */     for (int i = 0; i < 300; i++) {
/* 232 */       class_243 lastPos = new class_243(x, y, z);
/* 233 */       x += mx;
/* 234 */       y += my;
/* 235 */       z += mz;
/* 236 */       mx *= 0.99D;
/* 237 */       my *= 0.99D;
/* 238 */       mz *= 0.99D;
/* 239 */       my -= 0.029999999329447746D;
/* 240 */       class_243 pos = new class_243(x, y, z);
/* 241 */       class_3965 bhr = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/* 242 */       if (bhr != null && bhr.method_17783() == class_239.class_240.field_1332) return bhr.method_17777();
/*     */       
/* 244 */       for (class_1297 ent : mc.field_1687.method_18112()) {
/* 245 */         if (!(ent instanceof net.minecraft.class_1667) && ent != mc.field_1724 && !(ent instanceof net.minecraft.class_1684) && 
/* 246 */           ent.method_5829().method_994(new class_238(x - 0.3D, y - 0.3D, z - 0.3D, x + 0.3D, y + 0.3D, z + 0.2D))) {
/* 247 */           return null;
/*     */         }
/*     */       } 
/* 250 */       if (y <= -65.0D)
/*     */         break; 
/* 252 */     }  return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\PearlChaser.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */