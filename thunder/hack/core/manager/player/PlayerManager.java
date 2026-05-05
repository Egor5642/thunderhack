/*     */ package thunder.hack.core.manager.player;
/*     */ import java.util.ArrayDeque;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1675;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_241;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2735;
/*     */ import net.minecraft.class_2868;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_3966;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventFixVelocity;
/*     */ import thunder.hack.events.impl.EventKeyboardInput;
/*     */ import thunder.hack.events.impl.EventPlayerJump;
/*     */ import thunder.hack.events.impl.EventPlayerTravel;
/*     */ import thunder.hack.events.impl.EventPostSync;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.combat.Aura;
/*     */ import thunder.hack.injection.accesors.IClientPlayerEntity;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.world.ExplosionUtility;
/*     */ 
/*     */ public class PlayerManager implements IManager {
/*     */   public float yaw;
/*     */   public float pitch;
/*  39 */   public final Timer switchTimer = new Timer(); public float lastYaw; public float lastPitch; public float currentPlayerSpeed; public float averagePlayerSpeed; public int ticksElytraFlying;
/*     */   public int serverSideSlot;
/*  41 */   private final ArrayDeque<Float> speedResult = new ArrayDeque<>(20);
/*     */ 
/*     */   
/*     */   public float bodyYaw;
/*     */ 
/*     */   
/*     */   public float prevBodyYaw;
/*     */   
/*     */   public boolean inInventory;
/*     */ 
/*     */   
/*     */   @EventHandler(priority = 200)
/*     */   public void onSync(EventSync event) {
/*  54 */     if (Module.fullNullCheck())
/*     */       return; 
/*  56 */     this.yaw = mc.field_1724.method_36454();
/*  57 */     this.pitch = mc.field_1724.method_36455();
/*  58 */     this.lastYaw = ((IClientPlayerEntity)mc.field_1724).getLastYaw();
/*  59 */     this.lastPitch = ((IClientPlayerEntity)mc.field_1724).getLastPitch();
/*  60 */     if (mc.field_1755 == null) this.inInventory = false; 
/*  61 */     if (mc.field_1724.method_6128() && mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833)
/*  62 */     { this.ticksElytraFlying++; }
/*  63 */     else { this.ticksElytraFlying = 0; }
/*     */   
/*     */   }
/*     */   @EventHandler
/*     */   public void onTick(EventTick e) {
/*  68 */     this.currentPlayerSpeed = (float)Math.hypot(mc.field_1724.method_23317() - mc.field_1724.field_6014, mc.field_1724.method_23321() - mc.field_1724.field_5969);
/*     */     
/*  70 */     if (this.speedResult.size() > 20) {
/*  71 */       this.speedResult.poll();
/*     */     }
/*  73 */     this.speedResult.add(Float.valueOf(this.currentPlayerSpeed));
/*     */     
/*  75 */     float average = 0.0F;
/*     */     
/*  77 */     for (Float value : this.speedResult) average += MathUtility.clamp(value.floatValue(), 0.0F, 20.0F);
/*     */     
/*  79 */     this.averagePlayerSpeed = average / this.speedResult.size();
/*     */   }
/*     */   
/*     */   @EventHandler(priority = -200)
/*     */   public void postSync(EventPostSync event) {
/*  84 */     if (mc.field_1724 == null)
/*     */       return; 
/*  86 */     this.prevBodyYaw = this.bodyYaw;
/*  87 */     this.bodyYaw = getBodyYaw();
/*     */     
/*  89 */     if (!((Boolean)ModuleManager.rotations.clientLook.getValue()).booleanValue()) {
/*  90 */       mc.field_1724.method_36456(this.yaw);
/*  91 */       mc.field_1724.method_36457(this.pitch);
/*     */     } 
/*     */     
/*  94 */     ModuleManager.rotations.fixRotation = Float.NaN;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onJump(EventPlayerJump e) {
/*  99 */     ModuleManager.rotations.onJump(e);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMove(EventFixVelocity e) {
/* 104 */     ModuleManager.rotations.onPlayerMove(e);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void modifyVelocity(EventPlayerTravel e) {
/* 109 */     ModuleManager.rotations.modifyVelocity(e);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onKeyInput(EventKeyboardInput e) {
/* 114 */     ModuleManager.rotations.onKeyInput(e);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSyncWithServer(PacketEvent.Send event) {
/* 119 */     if (event.getPacket() instanceof net.minecraft.class_2813) {
/* 120 */       this.inInventory = true;
/*     */     }
/* 122 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2868) { class_2868 slot = (class_2868)class_2596;
/* 123 */       this.switchTimer.reset();
/* 124 */       this.serverSideSlot = slot.method_12442(); }
/*     */     
/* 126 */     if (event.getPacket() instanceof net.minecraft.class_2815) {
/* 127 */       this.inInventory = false;
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/* 133 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2735) { class_2735 slot = (class_2735)class_2596;
/* 134 */       this.switchTimer.reset();
/* 135 */       this.serverSideSlot = slot.method_11803(); }
/*     */   
/*     */   }
/*     */   
/*     */   private float getBodyYaw() {
/* 140 */     double x = mc.field_1724.method_23317() - mc.field_1724.field_6014;
/* 141 */     double z = mc.field_1724.method_23321() - mc.field_1724.field_5969;
/* 142 */     float offset = this.bodyYaw;
/* 143 */     if (x * x + z * z > 0.002500000176951289D) offset = (float)(class_3532.method_15349(z, x) * 57.2957763671875D - 90.0D); 
/* 144 */     if (mc.field_1724.field_6251 > 0.0F)
/* 145 */       offset = ((IClientPlayerEntity)(class_310.method_1551()).field_1724).getLastYaw(); 
/* 146 */     float deltaBodyYaw = class_3532.method_15363(class_3532.method_15393(((IClientPlayerEntity)(class_310.method_1551()).field_1724).getLastYaw() - this.bodyYaw + class_3532.method_15393(offset - this.bodyYaw) * 0.3F), -45.0F, 75.0F);
/* 147 */     return ((deltaBodyYaw > 50.0F) ? (deltaBodyYaw * 0.2F) : 0.0F) + ((IClientPlayerEntity)(class_310.method_1551()).field_1724).getLastYaw() - deltaBodyYaw;
/*     */   }
/*     */   public boolean checkRtx(float yaw, float pitch, float distance, float wallDistance, Aura.RayTrace rt) {
/*     */     class_3966 ehr;
/* 151 */     if (rt == Aura.RayTrace.OFF) {
/* 152 */       return true;
/*     */     }
/* 154 */     class_239 result = rayTrace(distance, yaw, pitch);
/* 155 */     class_243 startPoint = mc.field_1724.method_19538().method_1031(0.0D, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0D);
/* 156 */     double distancePow2 = Math.pow(distance, 2.0D);
/*     */     
/* 158 */     if (result != null) {
/* 159 */       distancePow2 = startPoint.method_1025(result.method_17784());
/*     */     }
/* 161 */     class_243 rotationVector = getRotationVector(pitch, yaw).method_1021(distance);
/* 162 */     class_243 endPoint = startPoint.method_1019(rotationVector);
/*     */     
/* 164 */     class_238 entityArea = mc.field_1724.method_5829().method_18804(rotationVector).method_1009(1.0D, 1.0D, 1.0D);
/*     */ 
/*     */ 
/*     */     
/* 168 */     double maxDistance = Math.max(distancePow2, Math.pow(wallDistance, 2.0D));
/*     */     
/* 170 */     if (rt == Aura.RayTrace.OnlyTarget && Aura.target != null) {
/* 171 */       ehr = class_1675.method_18075((class_1297)mc.field_1724, startPoint, endPoint, entityArea, e -> (!e.method_7325() && e.method_5863() && e == Aura.target), maxDistance);
/*     */     } else {
/* 173 */       ehr = class_1675.method_18075((class_1297)mc.field_1724, startPoint, endPoint, entityArea, e -> (!e.method_7325() && e.method_5863()), maxDistance);
/*     */     } 
/* 175 */     if (ehr != null) {
/* 176 */       boolean allowedWallDistance = (startPoint.method_1025(ehr.method_17784()) <= Math.pow(wallDistance, 2.0D));
/* 177 */       boolean wallMissing = (result == null);
/* 178 */       boolean wallBehindEntity = (startPoint.method_1025(ehr.method_17784()) < distancePow2);
/* 179 */       boolean allowWallHit = (wallMissing || allowedWallDistance || wallBehindEntity);
/*     */       
/* 181 */       if (allowWallHit && startPoint.method_1025(ehr.method_17784()) <= Math.pow(distance, 2.0D)) {
/* 182 */         return (ehr.method_17782() == Aura.target || Aura.target == null || rt == Aura.RayTrace.OnlyTarget);
/*     */       }
/*     */     } 
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkRtx(float yaw, float pitch, float distance, float wallDistance, class_1297 entity) {
/* 189 */     class_239 result = rayTrace(distance, yaw, pitch);
/* 190 */     class_243 startPoint = mc.field_1724.method_19538().method_1031(0.0D, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0D);
/* 191 */     double distancePow2 = Math.pow(distance, 2.0D);
/*     */     
/* 193 */     if (result != null) {
/* 194 */       distancePow2 = startPoint.method_1025(result.method_17784());
/*     */     }
/* 196 */     class_243 rotationVector = getRotationVector(pitch, yaw).method_1021(distance);
/* 197 */     class_243 endPoint = startPoint.method_1019(rotationVector);
/*     */     
/* 199 */     class_238 entityArea = mc.field_1724.method_5829().method_18804(rotationVector).method_1009(1.0D, 1.0D, 1.0D);
/*     */ 
/*     */ 
/*     */     
/* 203 */     double maxDistance = Math.max(distancePow2, Math.pow(wallDistance, 2.0D));
/*     */     
/* 205 */     class_3966 ehr = class_1675.method_18075((class_1297)mc.field_1724, startPoint, endPoint, entityArea, e -> (!e.method_7325() && e.method_5863() && e == entity), maxDistance);
/*     */     
/* 207 */     if (ehr != null) {
/* 208 */       boolean allowedWallDistance = (startPoint.method_1025(ehr.method_17784()) <= Math.pow(wallDistance, 2.0D));
/* 209 */       boolean wallMissing = (result == null);
/* 210 */       boolean wallBehindEntity = (startPoint.method_1025(ehr.method_17784()) < distancePow2);
/* 211 */       boolean allowWallHit = (wallMissing || allowedWallDistance || wallBehindEntity);
/*     */       
/* 213 */       if (allowWallHit && startPoint.method_1025(ehr.method_17784()) <= Math.pow(distance, 2.0D)) {
/* 214 */         return (ehr.method_17782() == entity);
/*     */       }
/*     */     } 
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public class_1297 getRtxTarget(float yaw, float pitch, float distance, boolean ignoreWalls) {
/* 222 */     class_1297 targetedEntity = null;
/* 223 */     class_239 result = ignoreWalls ? null : rayTrace(distance, yaw, pitch);
/* 224 */     class_243 vec3d = mc.field_1724.method_19538().method_1031(0.0D, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0D);
/* 225 */     double distancePow2 = Math.pow(distance, 2.0D);
/* 226 */     if (result != null) distancePow2 = result.method_17784().method_1025(vec3d); 
/* 227 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 228 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * distance, vec3d2.field_1351 * distance, vec3d2.field_1350 * distance);
/* 229 */     class_238 box = mc.field_1724.method_5829().method_18804(vec3d2.method_1021(distance)).method_1009(1.0D, 1.0D, 1.0D);
/* 230 */     class_3966 entityHitResult = class_1675.method_18075((class_1297)mc.field_1724, vec3d, vec3d3, box, entity -> (!entity.method_7325() && entity.method_5863()), distancePow2);
/* 231 */     if (entityHitResult != null) {
/* 232 */       class_1297 entity2 = entityHitResult.method_17782();
/* 233 */       class_243 vec3d4 = entityHitResult.method_17784();
/* 234 */       double g = vec3d.method_1025(vec3d4);
/* 235 */       if ((g < distancePow2 || result == null) && 
/* 236 */         entity2 instanceof net.minecraft.class_1309) {
/* 237 */         targetedEntity = entity2;
/* 238 */         return targetedEntity;
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     return targetedEntity;
/*     */   }
/*     */   
/*     */   public class_243 getRtxPoint(float yaw, float pitch, float distance) {
/* 246 */     class_243 vec3d = mc.field_1724.method_19538().method_1031(0.0D, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0D);
/* 247 */     double distancePow2 = Math.pow(distance, 2.0D);
/* 248 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 249 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * distance, vec3d2.field_1351 * distance, vec3d2.field_1350 * distance);
/* 250 */     class_238 box = mc.field_1724.method_5829().method_18804(vec3d2.method_1021(distance)).method_1009(1.0D, 1.0D, 1.0D);
/* 251 */     class_3966 entityHitResult = class_1675.method_18075((class_1297)mc.field_1724, vec3d, vec3d3, box, entity -> (!entity.method_7325() && entity.method_5863()), distancePow2);
/* 252 */     if (entityHitResult != null) {
/* 253 */       class_1297 entity2 = entityHitResult.method_17782();
/* 254 */       class_243 vec3d4 = entityHitResult.method_17784();
/* 255 */       if (entity2 instanceof net.minecraft.class_1309) {
/* 256 */         return vec3d4;
/*     */       }
/*     */     } 
/* 259 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLookingAtBox(float yaw, float pitch, class_2338 blockPos) {
/* 263 */     class_243 vec3d = mc.field_1724.method_5836(1.0F);
/* 264 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 265 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * 7.0D, vec3d2.field_1351 * 7.0D, vec3d2.field_1350 * 7.0D);
/* 266 */     class_3965 result = ExplosionUtility.rayCastBlock(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)mc.field_1724), blockPos);
/* 267 */     return (result != null && result.method_17783() == class_239.class_240.field_1332 && result.method_17777().equals(blockPos));
/*     */   }
/*     */   
/*     */   public class_239 rayTrace(double dst, float yaw, float pitch) {
/* 271 */     class_243 vec3d = mc.field_1724.method_5836(1.0F);
/* 272 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 273 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
/* 274 */     return (class_239)mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/*     */   }
/*     */   
/*     */   public class_239 getRtxTarget(float yaw, float pitch, double x, double y, double z) {
/* 278 */     class_239 result = rayTrace(5.0D, yaw, pitch, x, y, z);
/* 279 */     class_243 vec3d = (new class_243(x, y, z)).method_1031(0.0D, mc.field_1724.method_18381(mc.field_1724.method_18376()), 0.0D);
/* 280 */     double distancePow2 = 25.0D;
/* 281 */     if (result != null)
/* 282 */       distancePow2 = result.method_17784().method_1025(vec3d); 
/* 283 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 284 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * 5.0D, vec3d2.field_1351 * 5.0D, vec3d2.field_1350 * 5.0D);
/* 285 */     class_238 box = (new class_238(x - 0.3D, y, z - 0.3D, x + 0.3D, y + 1.8D, z + 0.3D)).method_18804(vec3d2.method_1021(5.0D)).method_1009(1.0D, 1.0D, 1.0D);
/* 286 */     class_3966 entityHitResult = class_1675.method_18075((class_1297)mc.field_1724, vec3d, vec3d3, box, entity -> (!entity.method_7325() && entity.method_5863()), distancePow2);
/* 287 */     if (entityHitResult != null) {
/* 288 */       class_1297 entity2 = entityHitResult.method_17782();
/* 289 */       class_243 vec3d4 = entityHitResult.method_17784();
/* 290 */       double g = vec3d.method_1025(vec3d4);
/* 291 */       if ((g < distancePow2 || result == null) && 
/* 292 */         entity2 instanceof net.minecraft.class_1309) {
/* 293 */         return (class_239)entityHitResult;
/*     */       }
/*     */     } 
/*     */     
/* 297 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isInWeb() {
/* 301 */     class_238 pBox = mc.field_1724.method_5829();
/* 302 */     class_2338 pBlockPos = class_2338.method_49638((class_2374)mc.field_1724.method_19538());
/*     */     
/* 304 */     for (int x = pBlockPos.method_10263() - 2; x <= pBlockPos.method_10263() + 2; x++) {
/* 305 */       for (int y = pBlockPos.method_10264() - 1; y <= pBlockPos.method_10264() + 4; y++) {
/* 306 */         for (int z = pBlockPos.method_10260() - 2; z <= pBlockPos.method_10260() + 2; z++) {
/* 307 */           class_2338 bp = new class_2338(x, y, z);
/* 308 */           if (pBox.method_994(new class_238(bp)) && mc.field_1687.method_8320(bp).method_26204() == class_2246.field_10343) {
/* 309 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 314 */     return false;
/*     */   }
/*     */   
/*     */   public class_239 rayTrace(double dst, float yaw, float pitch, double x, double y, double z) {
/* 318 */     class_243 vec3d = new class_243(x, y, z);
/* 319 */     class_243 vec3d2 = getRotationVector(pitch, yaw);
/* 320 */     class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
/* 321 */     return (class_239)mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/*     */   }
/*     */   
/*     */   public static float[] calcAngle(class_243 to) {
/* 325 */     if (to == null) return null; 
/* 326 */     double difX = to.field_1352 - (mc.field_1724.method_33571()).field_1352;
/* 327 */     double difY = (to.field_1351 - (mc.field_1724.method_33571()).field_1351) * -1.0D;
/* 328 */     double difZ = to.field_1350 - (mc.field_1724.method_33571()).field_1350;
/* 329 */     double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
/* 330 */     return new float[] { (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D), (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist))) };
/*     */   }
/*     */   
/*     */   public static class_241 calcAngleVec(class_243 to) {
/* 334 */     if (to == null) return null; 
/* 335 */     double difX = to.field_1352 - (mc.field_1724.method_33571()).field_1352;
/* 336 */     double difY = (to.field_1351 - (mc.field_1724.method_33571()).field_1351) * -1.0D;
/* 337 */     double difZ = to.field_1350 - (mc.field_1724.method_33571()).field_1350;
/* 338 */     double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
/* 339 */     return new class_241((float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D), (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist))));
/*     */   }
/*     */   @NotNull
/*     */   public class_243 getRotationVector(float yaw, float pitch) {
/* 343 */     return new class_243((class_3532.method_15374(-pitch * 0.017453292F) * class_3532.method_15362(yaw * 0.017453292F)), -class_3532.method_15374(yaw * 0.017453292F), (class_3532.method_15362(-pitch * 0.017453292F) * class_3532.method_15362(yaw * 0.017453292F)));
/*     */   }
/*     */   
/*     */   public static float[] calcAngle(class_243 from, class_243 to) {
/* 347 */     if (to == null) return null; 
/* 348 */     double difX = to.field_1352 - from.field_1352;
/* 349 */     double difY = (to.field_1351 - from.field_1351) * -1.0D;
/* 350 */     double difZ = to.field_1350 - from.field_1350;
/* 351 */     double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
/* 352 */     return new float[] { (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D), (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist))) };
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\player\PlayerManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */