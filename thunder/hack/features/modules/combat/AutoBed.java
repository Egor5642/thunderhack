/*     */ package thunder.hack.features.modules.combat;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Objects;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1703;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_1714;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_239;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_2815;
/*     */ import net.minecraft.class_2828;
/*     */ import net.minecraft.class_2885;
/*     */ import net.minecraft.class_3959;
/*     */ import net.minecraft.class_3965;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_516;
/*     */ import net.minecraft.class_7225;
/*     */ import net.minecraft.class_8786;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventPostSync;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.PlayerUpdateEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.injection.accesors.IClientPlayerEntity;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.SettingGroup;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.PlayerUtility;
/*     */ import thunder.hack.utility.player.SearchInvResult;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.world.ExplosionUtility;
/*     */ 
/*     */ public final class AutoBed extends Module {
/*  54 */   private final Setting<InteractionUtility.Interact> interactMode = new Setting("InteractMode", InteractionUtility.Interact.Vanilla);
/*  55 */   public static final Setting<Float> range = new Setting("Range", Float.valueOf(4.0F), Float.valueOf(2.0F), Float.valueOf(6.0F));
/*  56 */   public static final Setting<Float> wallRange = new Setting("WallRange", Float.valueOf(4.0F), Float.valueOf(0.0F), Float.valueOf(6.0F));
/*  57 */   public static final Setting<Integer> placeDelay = new Setting("PlaceDelay", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(1000));
/*  58 */   public static final Setting<Integer> explodeDelay = new Setting("ExplodeDelay", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(1000));
/*  59 */   public static final Setting<Float> minDamage = new Setting("MinDamage", Float.valueOf(8.0F), Float.valueOf(0.0F), Float.valueOf(25.0F));
/*  60 */   public static final Setting<Float> maxSelfDamage = new Setting("MaxSelfDamage", Float.valueOf(4.0F), Float.valueOf(0.0F), Float.valueOf(25.0F));
/*  61 */   private final Setting<Boolean> dimCheck = new Setting("DimensionCheck", Boolean.valueOf(false));
/*  62 */   public final Setting<Boolean> switchToHotbar = new Setting("SwitchToHotbar", Boolean.valueOf(true));
/*  63 */   public final Setting<Boolean> oldPlace = new Setting("1.12 Place", Boolean.valueOf(false));
/*  64 */   public final Setting<Boolean> autoSwap = new Setting("AutoSwap", Boolean.valueOf(true));
/*  65 */   public final Setting<Boolean> autoCraft = new Setting("AutoCraft", Boolean.valueOf(true));
/*  66 */   public static final Setting<Integer> minBeds = new Setting("MinBeds", Integer.valueOf(4), Integer.valueOf(0), Integer.valueOf(10));
/*  67 */   public static final Setting<Integer> bedsPerCraft = new Setting("BedsPerCraft", Integer.valueOf(8), Integer.valueOf(1), Integer.valueOf(27));
/*  68 */   private final Setting<SettingGroup> renderCategory = new Setting("Render", new SettingGroup(false, 0));
/*  69 */   private final Setting<Boolean> render = (new Setting("Render", Boolean.valueOf(true))).addToGroup(this.renderCategory);
/*  70 */   private final Setting<Boolean> rselfDamage = (new Setting("SelfDamage", Boolean.valueOf(true))).addToGroup(this.renderCategory);
/*  71 */   private final Setting<Boolean> drawDamage = (new Setting("RenderDamage", Boolean.valueOf(true))).addToGroup(this.renderCategory);
/*  72 */   private final Setting<ColorSetting> fillColor = (new Setting("Fill", new ColorSetting(Render2DEngine.injectAlpha(HudEditor.getColor(0), 150)))).addToGroup(this.renderCategory);
/*  73 */   private final Setting<ColorSetting> lineColor = (new Setting("Line", new ColorSetting(HudEditor.getColor(0)))).addToGroup(this.renderCategory);
/*  74 */   private final Setting<ColorSetting> textColor = (new Setting("Text", new ColorSetting(Color.WHITE))).addToGroup(this.renderCategory);
/*     */   private class_1657 target;
/*     */   private BedData bestBed;
/*     */   private BedData bestPos;
/*     */   private float rotationYaw;
/*     */   private float rotationPitch;
/*  80 */   private final Timer placeTimer = new Timer();
/*  81 */   private final Timer explodeTimer = new Timer();
/*     */   
/*     */   public AutoBed() {
/*  84 */     super("AutoBed", Module.Category.COMBAT);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/*  89 */     if (this.bestBed != null || this.bestPos != null) {
/*  90 */       mc.field_1724.method_36456(this.rotationYaw);
/*  91 */       mc.field_1724.method_36457(this.rotationPitch);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerUpdate(PlayerUpdateEvent e) {
/*  97 */     this.target = findTarget();
/*     */     
/*  99 */     if (mc.field_1687.method_8597().comp_648() && ((Boolean)this.dimCheck.getValue()).booleanValue()) {
/* 100 */       disable(ClientSettings.isRu() ? "Кровати не взрываются в этом измерении!" : "Beds don't explode in this dimension!");
/*     */       
/*     */       return;
/*     */     } 
/* 104 */     if (this.target != null && (this.target.method_29504() || this.target.method_6032() < 0.0F)) {
/* 105 */       this.target = null;
/*     */       
/*     */       return;
/*     */     } 
/* 109 */     this.bestBed = findBedToExplode();
/* 110 */     this.bestPos = findBlockToPlace();
/*     */     
/* 112 */     if (this.bestBed != null || this.bestPos != null) {
/*     */ 
/*     */       
/* 115 */       float[] angle = InteractionUtility.calculateAngle(((BedData)Objects.requireNonNullElseGet(this.bestPos, () -> this.bestBed)).hitResult().method_17784());
/*     */       
/* 117 */       this.rotationYaw = angle[0];
/* 118 */       this.rotationPitch = angle[1];
/* 119 */       ModuleManager.rotations.fixRotation = this.rotationYaw;
/*     */     } 
/*     */     
/* 122 */     if (((Boolean)this.autoCraft.getValue()).booleanValue()) {
/* 123 */       if (InventoryUtility.getBedsCount() <= ((Integer)minBeds.getValue()).intValue()) {
/* 124 */         craftBed();
/*     */         return;
/*     */       } 
/* 127 */       if (mc.field_1724.field_7512 instanceof class_1714) {
/* 128 */         sendPacket((class_2596)new class_2815(mc.field_1724.field_7512.field_7763));
/* 129 */         mc.field_1724.method_3137();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPostSync(EventPostSync e) {
/* 136 */     if (!(mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1748) && ((Boolean)this.autoSwap.getValue()).booleanValue() && this.bestPos != null) {
/* 137 */       SearchInvResult hotBarResult = InventoryUtility.findBedInHotBar();
/* 138 */       if (hotBarResult.found()) {
/* 139 */         hotBarResult.switchTo();
/* 140 */       } else if (((Boolean)this.switchToHotbar.getValue()).booleanValue()) {
/* 141 */         SearchInvResult invResult = InventoryUtility.findBed();
/* 142 */         if (invResult.found() && !(mc.field_1755 instanceof net.minecraft.class_479)) {
/* 143 */           mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, invResult.slot(), (mc.field_1724.method_31548()).field_7545, class_1713.field_7791, (class_1657)mc.field_1724);
/* 144 */           sendPacket((class_2596)new class_2815(mc.field_1724.field_7512.field_7763));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     if (this.bestBed != null && this.explodeTimer.passedMs(((Integer)explodeDelay.getValue()).intValue())) {
/* 150 */       sendSequencedPacket(id -> new class_2885(class_1268.field_5808, this.bestBed.hitResult(), id));
/* 151 */       mc.field_1724.method_6104(class_1268.field_5808);
/* 152 */       this.explodeTimer.reset();
/*     */     } 
/*     */     
/* 155 */     if (!(mc.field_1724.method_6047().method_7909() instanceof net.minecraft.class_1748)) {
/*     */       return;
/*     */     }
/* 158 */     if (this.bestPos != null && this.placeTimer.passedMs(((Integer)placeDelay.getValue()).intValue()) && !(mc.field_1687.method_8320(this.bestPos.hitResult().method_17777().method_10084()).method_26204() instanceof net.minecraft.class_2244)) {
/* 159 */       float angle2 = InteractionUtility.calculateAngle(this.bestPos.hitResult.method_17777().method_46558(), this.bestPos.hitResult.method_17777().method_10093(this.bestPos.dir).method_46558())[0];
/* 160 */       sendPacket((class_2596)new class_2828.class_2831(angle2, 0.0F, mc.field_1724.method_24828()));
/* 161 */       float prevYaw = mc.field_1724.method_36454();
/* 162 */       mc.field_1724.method_36456(angle2);
/* 163 */       mc.field_1724.field_5982 = angle2;
/* 164 */       ((IClientPlayerEntity)mc.field_1724).setLastYaw(angle2);
/* 165 */       sendSequencedPacket(id -> new class_2885(class_1268.field_5808, this.bestPos.hitResult(), id));
/* 166 */       mc.field_1724.method_6104(class_1268.field_5808);
/* 167 */       this.placeTimer.reset();
/* 168 */       mc.field_1724.method_36456(prevYaw);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRender3D(class_4587 stack) {
/* 174 */     if (this.bestPos != null && ((Boolean)this.render.getValue()).booleanValue()) {
/* 175 */       class_238 box = new class_238(this.bestPos.hitResult.method_17777().method_10084());
/* 176 */       class_238 box2 = new class_238(this.bestPos.hitResult.method_17777().method_10084().method_10093(this.bestPos.dir));
/*     */       
/* 178 */       class_238 finalBox = box.method_991(box2).method_35578(box.field_1325 - 0.44999998807907104D);
/*     */       
/* 180 */       String dmg = "" + MathUtility.round2(this.bestPos.damage()) + MathUtility.round2(this.bestPos.damage());
/*     */       
/* 182 */       Render3DEngine.OUTLINE_QUEUE.add(new Render3DEngine.OutlineAction(finalBox, ((ColorSetting)this.lineColor.getValue()).getColorObject(), 2.0F));
/* 183 */       Render3DEngine.FILLED_QUEUE.add(new Render3DEngine.FillAction(finalBox, ((ColorSetting)this.fillColor.getValue()).getColorObject()));
/*     */       
/* 185 */       if (((Boolean)this.drawDamage.getValue()).booleanValue())
/* 186 */         Render3DEngine.drawTextIn3D(dmg, finalBox.method_1005(), 0.0D, 0.1D, 0.0D, ((ColorSetting)this.textColor.getValue()).getColorObject()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_1657 findTarget() {
/* 191 */     return Managers.COMBAT.getNearestTarget(12.0F);
/*     */   }
/*     */   
/*     */   private BedData findBedToExplode() {
/* 195 */     int intRange = (int)(Math.floor(((Float)range.getValue()).floatValue()) + 1.0D);
/* 196 */     Iterable<class_2338> blocks_ = class_2338.method_25996(new class_2338((class_2382)class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10084()), intRange, intRange, intRange);
/*     */     
/* 198 */     BedData bestData = null;
/*     */     
/* 200 */     for (class_2338 b : blocks_) {
/* 201 */       class_2680 state = mc.field_1687.method_8320(b);
/* 202 */       if (PlayerUtility.squaredDistanceFromEyes(b.method_46558()) <= range.getPow2Value() && 
/* 203 */         state.method_26204() instanceof net.minecraft.class_2244) {
/* 204 */         class_3965 bhr = getInteractResult(b);
/*     */         
/* 206 */         mc.field_1687.method_8650(b, false);
/* 207 */         float damage = ExplosionUtility.getExplosionDamage(b.method_46558().method_1031(0.0D, -0.5D, 0.0D), this.target, false);
/* 208 */         float selfDamage = ExplosionUtility.getExplosionDamage(b.method_46558().method_1031(0.0D, -0.5D, 0.0D), (class_1657)mc.field_1724, false);
/* 209 */         mc.field_1687.method_8501(b, state);
/*     */         
/* 211 */         if (damage < ((Float)minDamage.getValue()).floatValue()) {
/*     */           continue;
/*     */         }
/* 214 */         if (selfDamage > ((Float)maxSelfDamage.getValue()).floatValue()) {
/*     */           continue;
/*     */         }
/* 217 */         if (selfDamage > mc.field_1724.method_6032() + mc.field_1724.method_6067() + 2.0F) {
/*     */           continue;
/*     */         }
/* 220 */         if (bestData != null && bestData.damage > damage) {
/*     */           continue;
/*     */         }
/* 223 */         if (bhr != null) {
/* 224 */           bestData = new BedData(bhr, damage, selfDamage, bhr.method_17780());
/*     */         }
/*     */       } 
/*     */     } 
/* 228 */     return bestData;
/*     */   }
/*     */   
/*     */   private BedData findBlockToPlace() {
/* 232 */     int intRange = (int)(Math.floor(((Float)range.getValue()).floatValue()) + 1.0D);
/* 233 */     Iterable<class_2338> blocks_ = class_2338.method_25996(new class_2338((class_2382)class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10084()), intRange, intRange, intRange);
/*     */     
/* 235 */     BedData bestData = null;
/*     */     
/* 237 */     for (class_2338 b : blocks_) {
/* 238 */       class_2680 state = mc.field_1687.method_8320(b);
/* 239 */       class_2680 state2 = mc.field_1687.method_8320(b.method_10084());
/*     */       
/* 241 */       if (PlayerUtility.squaredDistanceFromEyes(b.method_46558()) <= range.getPow2Value()) {
/* 242 */         if (state2.method_26204() instanceof net.minecraft.class_2244 && !this.placeTimer.passedMs(1500L) && this.bestPos != null) {
/* 243 */           return this.bestPos;
/*     */         }
/* 245 */         if (!state.method_45474()) {
/* 246 */           class_3965 bhr = InteractionUtility.getPlaceResult(b.method_10084(), (InteractionUtility.Interact)this.interactMode.getValue(), false);
/* 247 */           if (bhr != null) {
/*     */             
/* 249 */             class_3965 wallCheck = mc.field_1687.method_17742(new class_3959(InteractionUtility.getEyesPos((class_1297)mc.field_1724), bhr.method_17784(), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/* 250 */             if (wallCheck != null && wallCheck.method_17783() == class_239.class_240.field_1332 && wallCheck.method_17777() != b) {
/*     */               continue;
/*     */             }
/* 253 */             float damage = ExplosionUtility.getExplosionDamage(b.method_10084().method_46558().method_1031(0.0D, -0.5D, 0.0D), this.target, false);
/* 254 */             float selfDamage = ExplosionUtility.getExplosionDamage(b.method_10084().method_46558().method_1031(0.0D, -0.5D, 0.0D), (class_1657)mc.field_1724, false);
/*     */             
/* 256 */             if (damage < ((Float)minDamage.getValue()).floatValue()) {
/*     */               continue;
/*     */             }
/* 259 */             if (selfDamage > ((Float)maxSelfDamage.getValue()).floatValue()) {
/*     */               continue;
/*     */             }
/* 262 */             if (selfDamage > mc.field_1724.method_6032() + mc.field_1724.method_6067() + 2.0F) {
/*     */               continue;
/*     */             }
/* 265 */             if (bestData != null && bestData.damage > damage) {
/*     */               continue;
/*     */             }
/*     */             
/* 269 */             float bestDirdmg = 0.0F;
/* 270 */             class_2350 bestDir = null;
/* 271 */             for (class_2350 dir : class_2350.values()) {
/* 272 */               if (dir != class_2350.field_11033 && dir != class_2350.field_11036) {
/*     */                 
/* 274 */                 class_2338 offset = b.method_10084().method_10093(dir);
/*     */                 
/* 276 */                 if (mc.field_1687.method_8320(offset).method_45474())
/*     */                 {
/*     */                   
/* 279 */                   if (!((Boolean)this.oldPlace.getValue()).booleanValue() || !mc.field_1687.method_8320(b.method_10093(dir)).method_45474()) {
/*     */ 
/*     */ 
/*     */                     
/* 283 */                     float dirdamage = ExplosionUtility.getExplosionDamage(offset.method_46558().method_1031(0.0D, -0.5D, 0.0D), this.target, false);
/* 284 */                     float dirSelfDamage = ExplosionUtility.getExplosionDamage(offset.method_46558().method_1031(0.0D, -0.5D, 0.0D), (class_1657)mc.field_1724, false);
/* 285 */                     if (dirdamage > bestDirdmg && dirSelfDamage <= ((Float)maxSelfDamage.getValue()).floatValue()) {
/* 286 */                       bestDir = dir;
/* 287 */                       bestDirdmg = dirdamage;
/*     */                     } 
/*     */                   }  } 
/*     */               } 
/* 291 */             }  bestData = (bestDir == null) ? null : new BedData(bhr, damage, selfDamage, bestDir);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 296 */     return bestData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void craftBed() {
/* 301 */     int intRange = (int)(Math.floor(((Float)range.getValue()).floatValue()) + 1.0D);
/* 302 */     Iterable<class_2338> blocks_ = class_2338.method_25996(new class_2338((class_2382)class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10084()), intRange, intRange, intRange);
/*     */     
/* 304 */     for (class_2338 b : blocks_) {
/* 305 */       class_2680 state = mc.field_1687.method_8320(b);
/* 306 */       if (state.method_26204() instanceof net.minecraft.class_2304) {
/* 307 */         class_3965 result = getInteractResult(b);
/* 308 */         if (result != null) {
/* 309 */           class_1703 class_1703 = mc.field_1724.field_7512; if (class_1703 instanceof class_1714) { class_1714 craft = (class_1714)class_1703;
/* 310 */             mc.field_1724.method_3130().method_14884(craft.method_30264(), true);
/* 311 */             for (class_516 results : mc.field_1724.method_3130().method_1393()) {
/* 312 */               for (class_8786<?> recipe : (Iterable<class_8786<?>>)results.method_2648(true)) {
/* 313 */                 if (recipe.comp_1933().method_8110((class_7225.class_7874)results.method_48479()).method_7909() instanceof net.minecraft.class_1748) {
/* 314 */                   for (int i = 0; i < ((Integer)bedsPerCraft.getValue()).intValue(); i++)
/* 315 */                     mc.field_1761.method_2912(mc.field_1724.field_7512.field_7763, recipe, false); 
/* 316 */                   mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 0, 0, class_1713.field_7794, (class_1657)mc.field_1724);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             continue; }
/*     */           
/* 322 */           float[] angle = InteractionUtility.calculateAngle(result.method_17784());
/* 323 */           mc.field_1724.method_36456(angle[0]);
/* 324 */           mc.field_1724.method_36457(angle[1]);
/* 325 */           sendSequencedPacket(id -> new class_2885(class_1268.field_5808, result, id));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public class_3965 getInteractResult(@NotNull class_2338 bp) {
/* 333 */     float bestDistance = 999.0F;
/* 334 */     class_3965 bestResult = null; float x;
/* 335 */     for (x = 0.0F; x < 1.0F; x += 0.25F) {
/* 336 */       float y; for (y = 0.0F; y < 0.5F; y += 0.125F) {
/* 337 */         float z; for (z = 0.0F; z < 1.0F; z += 0.25F) {
/* 338 */           class_243 point = new class_243((bp.method_10263() + x), (bp.method_10264() + y), (bp.method_10260() + z));
/* 339 */           float distance = PlayerUtility.squaredDistanceFromEyes(point);
/*     */           
/* 341 */           class_3965 wallCheck = mc.field_1687.method_17742(new class_3959(InteractionUtility.getEyesPos((class_1297)mc.field_1724), point, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)mc.field_1724));
/* 342 */           if (wallCheck == null || wallCheck.method_17783() != class_239.class_240.field_1332 || wallCheck.method_17777() == bp || 
/* 343 */             distance <= wallRange.getPow2Value()) {
/*     */ 
/*     */ 
/*     */             
/* 347 */             class_3965 result = ExplosionUtility.rayCastBlock(new class_3959(InteractionUtility.getEyesPos((class_1297)mc.field_1724), point, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)mc.field_1724), bp);
/* 348 */             if (distance <= range.getPow2Value())
/*     */             {
/*     */               
/* 351 */               if (distance < bestDistance && 
/* 352 */                 result != null && result.method_17783() == class_239.class_240.field_1332) {
/* 353 */                 bestResult = result;
/* 354 */                 bestDistance = distance;
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 361 */     float bestDistance2 = 999.0F;
/* 362 */     class_2350 bestDirection = null;
/*     */     
/* 364 */     if (mc.field_1724.method_33571().method_10214() > bp.method_10084().method_10264()) {
/* 365 */       bestDirection = class_2350.field_11036;
/* 366 */     } else if (mc.field_1724.method_33571().method_10214() < bp.method_10264()) {
/* 367 */       bestDirection = class_2350.field_11033;
/*     */     } else {
/* 369 */       for (class_2350 dir : class_2350.values()) {
/* 370 */         class_243 directionVec = new class_243(bp.method_10263() + 0.5D + dir.method_10163().method_10263() * 0.5D, bp.method_10264() + 0.5D + dir.method_10163().method_10264() * 0.5D, bp.method_10260() + 0.5D + dir.method_10163().method_10260() * 0.5D);
/* 371 */         float distance = PlayerUtility.squaredDistanceFromEyes(directionVec);
/* 372 */         if (bestDistance2 > distance) {
/* 373 */           bestDirection = dir;
/* 374 */           bestDistance2 = distance;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 379 */     if (bestResult == null) {
/* 380 */       return null;
/*     */     }
/* 382 */     return new class_3965(bestResult.method_17784(), bestDirection, bestResult.method_17777(), false);
/*     */   }
/*     */   private static final class BedData extends Record { private final class_3965 hitResult; private final float damage; private final float selfDamage; private final class_2350 dir;
/* 385 */     private BedData(class_3965 hitResult, float damage, float selfDamage, class_2350 dir) { this.hitResult = hitResult; this.damage = damage; this.selfDamage = selfDamage; this.dir = dir; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/combat/AutoBed$BedData;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #385	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 385 */       //   0	7	0	this	Lthunder/hack/features/modules/combat/AutoBed$BedData; } public class_3965 hitResult() { return this.hitResult; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/combat/AutoBed$BedData;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #385	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/features/modules/combat/AutoBed$BedData; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/combat/AutoBed$BedData;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #385	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/features/modules/combat/AutoBed$BedData;
/* 385 */       //   0	8	1	o	Ljava/lang/Object; } public float damage() { return this.damage; } public float selfDamage() { return this.selfDamage; } public class_2350 dir() { return this.dir; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\AutoBed.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */