/*     */ package thunder.hack.features.modules.render;
/*     */ import net.minecraft.class_1297;
/*     */ import thunder.hack.setting.Setting;
/*     */ 
/*     */ public class NoRender extends Module {
/*     */   public final Setting<Boolean> disableGuiBackGround;
/*     */   public final Setting<Boolean> noSwing;
/*     */   public final Setting<Boolean> auto;
/*     */   public final Setting<Boolean> hurtCam;
/*     */   public final Setting<Boolean> potions;
/*     */   public final Setting<Boolean> xp;
/*     */   public final Setting<Boolean> arrows;
/*     */   public final Setting<Boolean> eggs;
/*     */   public final Setting<Boolean> elderGuardian;
/*     */   public final Setting<Boolean> vignette;
/*     */   public final Setting<Boolean> portal;
/*     */   public final Setting<Boolean> explosions;
/*     */   public final Setting<Boolean> campFire;
/*     */   public final Setting<Boolean> fireworks;
/*     */   public final Setting<Boolean> armor;
/*     */   public final Setting<Boolean> bossbar;
/*     */   public final Setting<Boolean> fireOverlay;
/*     */   public final Setting<Boolean> waterOverlay;
/*     */   public final Setting<Boolean> blockOverlay;
/*     */   
/*     */   public NoRender() {
/*  27 */     super("NoRender", Module.Category.RENDER);
/*     */ 
/*     */     
/*  30 */     this.disableGuiBackGround = new Setting("noGuiBackGround", Boolean.valueOf(true));
/*  31 */     this.noSwing = new Setting("NoHandSwing", Boolean.valueOf(false));
/*  32 */     this.auto = new Setting("Auto", Boolean.valueOf(false));
/*  33 */     this.hurtCam = new Setting("HurtCam", Boolean.valueOf(true));
/*  34 */     this.potions = new Setting("Potions", Boolean.valueOf(false));
/*  35 */     this.xp = new Setting("XP", Boolean.valueOf(false));
/*  36 */     this.arrows = new Setting("Arrows", Boolean.valueOf(false));
/*  37 */     this.eggs = new Setting("Eggs", Boolean.valueOf(false));
/*  38 */     this.elderGuardian = new Setting("Guardian", Boolean.valueOf(false));
/*  39 */     this.vignette = new Setting("Vignette", Boolean.valueOf(true));
/*  40 */     this.portal = new Setting("Portal", Boolean.valueOf(true));
/*  41 */     this.explosions = new Setting("Explosions", Boolean.valueOf(false));
/*  42 */     this.campFire = new Setting("CampFire", Boolean.valueOf(false));
/*  43 */     this.fireworks = new Setting("Fireworks", Boolean.valueOf(false));
/*  44 */     this.armor = new Setting("Armor", Boolean.valueOf(false));
/*  45 */     this.bossbar = new Setting("Bossbar", Boolean.valueOf(false));
/*  46 */     this.fireOverlay = new Setting("FireOverlay", Boolean.valueOf(false));
/*  47 */     this.waterOverlay = new Setting("WaterOverlay", Boolean.valueOf(false));
/*  48 */     this.blockOverlay = new Setting("BlockOverlay", Boolean.valueOf(false));
/*  49 */     this.nausea = new Setting("Nausea", Boolean.valueOf(false));
/*  50 */     this.blindness = new Setting("Blindness", Boolean.valueOf(false));
/*  51 */     this.fog = new Setting("Fog", Boolean.valueOf(false));
/*  52 */     this.darkness = new Setting("Darkness", Boolean.valueOf(false));
/*  53 */     this.items = new Setting("Items", Boolean.valueOf(false));
/*  54 */     this.crystals = new Setting("Crystals", Boolean.valueOf(false));
/*  55 */     this.fireEntity = new Setting("FireEntity", Boolean.valueOf(true));
/*  56 */     this.breakParticles = new Setting("BreakParticles", Boolean.valueOf(true));
/*  57 */     this.antiTitle = new Setting("AntiTitle", Boolean.valueOf(false));
/*  58 */     this.antiPlayerCollision = new Setting("AntiPlayerCollision", Boolean.valueOf(true));
/*  59 */     this.noScoreBoard = new Setting("NoScoreBoard", Boolean.valueOf(true));
/*  60 */     this.signText = new Setting("SignText", Boolean.valueOf(false));
/*  61 */     this.noWeather = new Setting("NoWeather", Boolean.valueOf(false));
/*  62 */     this.noArmorStands = new Setting("NoArmorStands", Boolean.valueOf(false));
/*  63 */     this.spawnerEntity = new Setting("SpawnerEntity", Boolean.valueOf(false));
/*  64 */     this.hotbarItemName = new Setting("HotbarItemName", Boolean.valueOf(false));
/*     */   }
/*     */   public final Setting<Boolean> nausea; public final Setting<Boolean> blindness; public final Setting<Boolean> fog; public final Setting<Boolean> darkness; public final Setting<Boolean> items; public final Setting<Boolean> crystals; public final Setting<Boolean> fireEntity; public final Setting<Boolean> breakParticles; public final Setting<Boolean> antiTitle; public final Setting<Boolean> antiPlayerCollision; public final Setting<Boolean> noScoreBoard; public final Setting<Boolean> signText; public final Setting<Boolean> noWeather; public final Setting<Boolean> noArmorStands; public final Setting<Boolean> spawnerEntity; public final Setting<Boolean> hotbarItemName; private int potionCouter; private int xpCounter; private int arrowCounter; private int itemsCounter;
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive e) {
/*  70 */     if (e.getPacket() instanceof net.minecraft.class_5904 && ((Boolean)this.antiTitle.getValue()).booleanValue())
/*  71 */       e.cancel(); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/*  76 */     for (class_1297 ent : Managers.ASYNC.getAsyncEntities()) {
/*  77 */       if (ent instanceof net.minecraft.class_1686) {
/*  78 */         this.potionCouter++;
/*  79 */         if (((Boolean)this.potions.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998); 
/*     */       } 
/*  81 */       if (ent instanceof net.minecraft.class_1683) {
/*  82 */         this.xpCounter++;
/*  83 */         if (((Boolean)this.xp.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998); 
/*     */       } 
/*  85 */       if (ent instanceof net.minecraft.class_1511 && (
/*  86 */         (Boolean)this.crystals.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998);
/*     */       
/*  88 */       if (ent instanceof net.minecraft.class_1667) {
/*  89 */         this.arrowCounter++;
/*  90 */         if (((Boolean)this.arrows.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998); 
/*     */       } 
/*  92 */       if (ent instanceof net.minecraft.class_1681 && (
/*  93 */         (Boolean)this.eggs.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998);
/*     */       
/*  95 */       if (ent instanceof net.minecraft.class_1542) {
/*  96 */         this.itemsCounter++;
/*  97 */         if (((Boolean)this.items.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998); 
/*     */       } 
/*  99 */       if (ent instanceof net.minecraft.class_1531 && (
/* 100 */         (Boolean)this.noArmorStands.getValue()).booleanValue()) mc.field_1687.method_2945(ent.method_5628(), class_1297.class_5529.field_26998);
/*     */     
/*     */     } 
/*     */     
/* 104 */     if (((Boolean)this.auto.getValue()).booleanValue()) {
/* 105 */       if (this.arrowCounter > 64) {
/* 106 */         Managers.NOTIFICATION.publicity("NoRender", ClientSettings.isRu() ? "Превышен лимит стрел! Удаляю..." : "Arrows limit reached! Removing...", 3, Notification.Type.SUCCESS);
/*     */       }
/* 108 */       if (this.itemsCounter > 16) {
/* 109 */         Managers.NOTIFICATION.publicity("NoRender", ClientSettings.isRu() ? "Превышен лимит вещей! Удаляю..." : "Item limit reached! Removing...", 3, Notification.Type.SUCCESS);
/*     */       }
/* 111 */       if (this.xpCounter > 16) {
/* 112 */         Managers.NOTIFICATION.publicity("NoRender", ClientSettings.isRu() ? "Превышен лимит пузырьков опыта! Удаляю..." : "XP orbs limit reached! Removing...", 3, Notification.Type.SUCCESS);
/*     */       }
/* 114 */       if (this.potionCouter > 8) {
/* 115 */         Managers.NOTIFICATION.publicity("NoRender", ClientSettings.isRu() ? "Превышен лимит зелий! Удаляю..." : "Potions limit reached! Removing...", 3, Notification.Type.SUCCESS);
/*     */       }
/*     */       
/* 118 */       List<Integer> toRemove = new ArrayList<>();
/*     */       
/* 120 */       for (class_1297 ent : Managers.ASYNC.getAsyncEntities()) {
/* 121 */         if (ent instanceof net.minecraft.class_1667 && this.arrowCounter > 64) toRemove.add(Integer.valueOf(ent.method_5628())); 
/* 122 */         if (ent instanceof net.minecraft.class_1542 && this.itemsCounter > 16) toRemove.add(Integer.valueOf(ent.method_5628())); 
/* 123 */         if (ent instanceof net.minecraft.class_1683 && this.xpCounter > 16) toRemove.add(Integer.valueOf(ent.method_5628())); 
/* 124 */         if (ent instanceof net.minecraft.class_1686 && this.potionCouter > 8) toRemove.add(Integer.valueOf(ent.method_5628()));
/*     */       
/*     */       } 
/*     */       try {
/* 128 */         toRemove.forEach(id -> mc.field_1687.method_2945(id.intValue(), class_1297.class_5529.field_26998));
/* 129 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 133 */     this.arrowCounter = 0;
/* 134 */     this.itemsCounter = 0;
/* 135 */     this.potionCouter = 0;
/* 136 */     this.xpCounter = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\NoRender.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */