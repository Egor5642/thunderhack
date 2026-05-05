/*     */ package thunder.hack.features.modules.combat;
/*     */ import java.util.Comparator;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_2382;
/*     */ import net.minecraft.class_2620;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.events.impl.EventAttackBlock;
/*     */ import thunder.hack.events.impl.EventBreakBlock;
/*     */ import thunder.hack.events.impl.EventPlaceBlock;
/*     */ import thunder.hack.events.impl.EventPostSync;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.base.PlaceModule;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.SettingGroup;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.PlayerUtility;
/*     */ import thunder.hack.utility.world.HoleUtility;
/*     */ 
/*     */ public final class Blocker extends PlaceModule {
/*  27 */   private final Setting<Integer> actionShift = new Setting("Place Per Tick", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(5));
/*  28 */   private final Setting<Integer> actionInterval = new Setting("Delay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(5));
/*     */   
/*  30 */   private final Setting<SettingGroup> logic = new Setting("Logic", new SettingGroup(false, 0));
/*  31 */   private final Setting<Boolean> antiCev = (new Setting("Anti Cev", Boolean.valueOf(true))).addToGroup(this.logic);
/*  32 */   private final Setting<Boolean> antiCiv = (new Setting("Anti Civ", Boolean.valueOf(true))).addToGroup(this.logic);
/*  33 */   private final Setting<Boolean> expand = (new Setting("Expand", Boolean.valueOf(true))).addToGroup(this.logic);
/*  34 */   private final Setting<Boolean> antiTntAura = (new Setting("Anti TNT", Boolean.valueOf(false))).addToGroup(this.logic);
/*  35 */   private final Setting<Boolean> antiAutoAnchor = (new Setting("Anti Anchor", Boolean.valueOf(false))).addToGroup(this.logic);
/*     */   
/*  37 */   private final Setting<SettingGroup> detect = (new Setting("Detect", new SettingGroup(false, 1))).addToGroup(this.logic);
/*  38 */   private final Setting<Boolean> onPacket = (new Setting("On Break Packet", Boolean.valueOf(true))).addToGroup(this.detect);
/*  39 */   private final Setting<Boolean> onAttackBlock = (new Setting("On Attack Block", Boolean.valueOf(false))).addToGroup(this.detect);
/*  40 */   private final Setting<Boolean> onBreak = (new Setting("On Break", Boolean.valueOf(true))).addToGroup(this.detect);
/*     */   
/*  42 */   private final List<class_2338> placePositions = new CopyOnWriteArrayList<>();
/*  43 */   private int tickCounter = 0;
/*     */   
/*     */   public Blocker() {
/*  46 */     super("Blocker", Module.Category.COMBAT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  51 */     this.tickCounter = 0;
/*  52 */     sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onPostSync(EventPostSync event) {
/*  60 */     if (this.tickCounter < ((Integer)this.actionInterval.getValue()).intValue()) {
/*  61 */       this.tickCounter++;
/*     */       return;
/*     */     } 
/*  64 */     if (this.tickCounter >= ((Integer)this.actionInterval.getValue()).intValue()) {
/*  65 */       this.tickCounter = 0;
/*     */     }
/*     */     
/*  68 */     if (!getBlockResult().found() || this.placePositions.isEmpty()) {
/*     */       return;
/*     */     }
/*  71 */     this.placePositions.removeIf(b -> (PlayerUtility.squaredDistanceFromEyes(b.method_46558()) > this.range.getPow2Value()));
/*     */     
/*  73 */     int blocksPlaced = 0;
/*     */     
/*  75 */     while (blocksPlaced < ((Integer)this.actionShift.getValue()).intValue()) {
/*     */ 
/*     */ 
/*     */       
/*  79 */       class_2338 pos = this.placePositions.stream().filter(p -> InteractionUtility.canPlaceBlock(p, (InteractionUtility.Interact)this.interact.getValue(), true)).min(Comparator.comparing(p -> Double.valueOf(mc.field_1724.method_19538().method_1022(p.method_46558())))).orElse(null);
/*     */       
/*  81 */       if (pos != null && mc.field_1724.method_24828() && placeBlock(pos)) {
/*  82 */         blocksPlaced++;
/*  83 */         this.tickCounter = 0;
/*  84 */         this.placePositions.remove(pos);
/*  85 */         this.inactivityTimer.reset();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onPacketReceive(PacketEvent.Receive event) {
/*  93 */     if (event.getPacket() instanceof class_2620 && ((Boolean)this.onPacket.getValue()).booleanValue()) {
/*  94 */       class_2620 packet = (class_2620)event.getPacket();
/*  95 */       doLogic(packet.method_11277());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onAttackBlock(EventAttackBlock event) {
/* 102 */     if (!((Boolean)this.onAttackBlock.getValue()).booleanValue())
/* 103 */       return;  doLogic(event.getBlockPos());
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onBreak(EventBreakBlock event) {
/* 109 */     if (!((Boolean)this.onBreak.getValue()).booleanValue())
/* 110 */       return;  doLogic(event.getPos());
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void onPlaceBlock(@NotNull EventPlaceBlock event) {
/* 116 */     if (event.getBlockPos().equals(mc.field_1724.method_24515().method_10086(2)) && event
/* 117 */       .getBlock().equals(class_2246.field_10375) && ((Boolean)this.antiTntAura
/* 118 */       .getValue()).booleanValue()) {
/* 119 */       this.placePositions.add(event.getBlockPos());
/*     */     }
/* 121 */     if (event.getBlockPos().equals(mc.field_1724.method_24515().method_10086(2)) && event
/* 122 */       .getBlock().equals(class_2246.field_23152) && ((Boolean)this.antiAutoAnchor
/* 123 */       .getValue()).booleanValue()) {
/* 124 */       this.placePositions.add(event.getBlockPos());
/*     */     }
/*     */   }
/*     */   
/*     */   private void doLogic(class_2338 pos) {
/* 129 */     if (mc.field_1687 == null || mc.field_1724 == null || !HoleUtility.isHole(mc.field_1724.method_24515())) {
/*     */       return;
/*     */     }
/* 132 */     if (((Boolean)this.antiCev.getValue()).booleanValue()) {
/* 133 */       for (class_2338 checkPos : HoleUtility.getHolePoses(mc.field_1724.method_19538())) {
/* 134 */         if (pos.equals(checkPos.method_10086(2))) {
/* 135 */           this.placePositions.add(checkPos.method_10086(3));
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/* 141 */     if (HoleUtility.getSurroundPoses(mc.field_1724.method_19538()).contains(pos)) {
/* 142 */       if (mc.field_1687.method_8320(pos).method_26204() == class_2246.field_9987 || mc.field_1687.method_8320(pos).method_45474()) {
/*     */         return;
/*     */       }
/* 145 */       this.placePositions.add(pos.method_10084());
/*     */       
/* 147 */       if (((Boolean)this.expand.getValue()).booleanValue()) {
/* 148 */         for (class_2382 vec : HoleUtility.VECTOR_PATTERN) {
/* 149 */           class_2338 checkPos = pos.method_10081(vec);
/* 150 */           if (canPlaceBlock(checkPos, true) && 
/* 151 */             mc.field_1687.method_18467(class_1657.class, new class_238(checkPos)).isEmpty()) {
/* 152 */             this.placePositions.add(checkPos);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 160 */     if (((Boolean)this.antiCiv.getValue()).booleanValue())
/* 161 */       for (class_2338 checkPos : HoleUtility.getSurroundPoses(mc.field_1724.method_19538())) {
/* 162 */         if (pos.equals(checkPos.method_10084())) {
/* 163 */           this.placePositions.add(checkPos.method_10086(2));
/*     */           return;
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\combat\Blocker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */