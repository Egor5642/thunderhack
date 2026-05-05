/*     */ package thunder.hack.features.modules.misc;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1293;
/*     */ import net.minecraft.class_1294;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_1935;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2602;
/*     */ import net.minecraft.class_2663;
/*     */ import net.minecraft.class_2664;
/*     */ import net.minecraft.class_3417;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_745;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.events.impl.EventAttack;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.events.impl.TotemPopEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.world.ExplosionUtility;
/*     */ 
/*     */ public class FakePlayer extends Module {
/*  34 */   private final Setting<Boolean> copyInventory = new Setting("CopyInventory", Boolean.valueOf(false)); public static class_745 fakePlayer; private Setting<Boolean> record; private Setting<Boolean> play; private Setting<Boolean> autoTotem; private Setting<String> name; private final List<PlayerState> positions;
/*     */   int movementTick;
/*     */   int deathTime;
/*     */   
/*     */   public FakePlayer() {
/*  39 */     super("FakePlayer", Module.Category.MISC);
/*     */ 
/*     */     
/*  42 */     this.record = new Setting("Record", Boolean.valueOf(false));
/*  43 */     this.play = new Setting("Play", Boolean.valueOf(false));
/*  44 */     this.autoTotem = new Setting("AutoTotem", Boolean.valueOf(false));
/*  45 */     this.name = new Setting("Name", "Hell_Raider");
/*     */     
/*  47 */     this.positions = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  53 */     fakePlayer = new class_745(mc.field_1687, new GameProfile(UUID.fromString("66123666-6666-6666-6666-666666666600"), (String)this.name.getValue()));
/*  54 */     fakePlayer.method_5719((class_1297)mc.field_1724);
/*     */     
/*  56 */     if (((Boolean)this.copyInventory.getValue()).booleanValue()) {
/*  57 */       fakePlayer.method_6122(class_1268.field_5808, mc.field_1724.method_6047().method_7972());
/*  58 */       fakePlayer.method_6122(class_1268.field_5810, mc.field_1724.method_6079().method_7972());
/*     */       
/*  60 */       fakePlayer.method_31548().method_5447(36, mc.field_1724.method_31548().method_5438(36).method_7972());
/*  61 */       fakePlayer.method_31548().method_5447(37, mc.field_1724.method_31548().method_5438(37).method_7972());
/*  62 */       fakePlayer.method_31548().method_5447(38, mc.field_1724.method_31548().method_5438(38).method_7972());
/*  63 */       fakePlayer.method_31548().method_5447(39, mc.field_1724.method_31548().method_5438(39).method_7972());
/*     */     } 
/*     */     
/*  66 */     mc.field_1687.method_53875((class_1297)fakePlayer);
/*  67 */     fakePlayer.method_6092(new class_1293(class_1294.field_5924, 9999, 2));
/*  68 */     fakePlayer.method_6092(new class_1293(class_1294.field_5898, 9999, 4));
/*  69 */     fakePlayer.method_6092(new class_1293(class_1294.field_5907, 9999, 1));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive e) {
/*  74 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2664) { class_2664 explosion = (class_2664)class_2596; if (fakePlayer != null && fakePlayer.field_6235 == 0) {
/*  75 */         fakePlayer.method_48922(mc.field_1687.method_48963().method_48830());
/*  76 */         fakePlayer.method_6033(fakePlayer.method_6032() + fakePlayer.method_6067() - ExplosionUtility.getAutoCrystalDamage(new class_243(explosion.method_11475(), explosion.method_11477(), explosion.method_11478()), (class_1657)fakePlayer, 0, false));
/*  77 */         if (fakePlayer.method_29504() && 
/*  78 */           fakePlayer.method_6095(mc.field_1687.method_48963().method_48830())) {
/*  79 */           fakePlayer.method_6033(10.0F);
/*     */ 
/*     */           
/*  82 */           ThunderHack.EVENT_BUS.post(new TotemPopEvent((class_1657)fakePlayer, 1));
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/*  92 */     if (((Boolean)this.record.getValue()).booleanValue()) {
/*  93 */       this.positions.add(new PlayerState(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455()));
/*     */       return;
/*     */     } 
/*  96 */     if (fakePlayer != null) {
/*  97 */       if (((Boolean)this.play.getValue()).booleanValue() && !this.positions.isEmpty())
/*  98 */       { this.movementTick++;
/*     */         
/* 100 */         if (this.movementTick >= this.positions.size()) {
/* 101 */           this.movementTick = 0;
/*     */           return;
/*     */         } 
/* 104 */         PlayerState p = this.positions.get(this.movementTick);
/* 105 */         fakePlayer.method_36456(p.yaw);
/* 106 */         fakePlayer.method_36457(p.pitch);
/* 107 */         fakePlayer.method_5847(p.yaw);
/*     */         
/* 109 */         fakePlayer.method_43391(p.x, p.y, p.z);
/* 110 */         fakePlayer.method_5759(p.x, p.y, p.z, p.yaw, p.pitch, 3); }
/* 111 */       else { this.movementTick = 0; }
/*     */       
/* 113 */       if (((Boolean)this.autoTotem.getValue()).booleanValue() && fakePlayer.method_6079().method_7909() != class_1802.field_8288) {
/* 114 */         fakePlayer.method_6122(class_1268.field_5810, new class_1799((class_1935)class_1802.field_8288));
/*     */       }
/* 116 */       if (fakePlayer.method_29504()) {
/* 117 */         this.deathTime++;
/* 118 */         if (this.deathTime > 10) disable(); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onAttack(EventAttack e) {
/* 125 */     if (fakePlayer != null && e.getEntity() == fakePlayer && fakePlayer.field_6235 == 0 && !e.isPre()) {
/* 126 */       mc.field_1687.method_43128((class_1657)mc.field_1724, fakePlayer.method_23317(), fakePlayer.method_23318(), fakePlayer.method_23321(), class_3417.field_15115, class_3419.field_15248, 1.0F, 1.0F);
/*     */       
/* 128 */       if (mc.field_1724.field_6017 > 0.0F || ModuleManager.criticals.isEnabled())
/* 129 */         mc.field_1687.method_43128((class_1657)mc.field_1724, fakePlayer.method_23317(), fakePlayer.method_23318(), fakePlayer.method_23321(), class_3417.field_15016, class_3419.field_15248, 1.0F, 1.0F); 
/* 130 */       fakePlayer.method_48922(mc.field_1687.method_48963().method_48830());
/* 131 */       if (ModuleManager.aura.getAttackCooldown() >= 0.85D)
/* 132 */       { fakePlayer.method_6033(fakePlayer.method_6032() + fakePlayer.method_6067() - InventoryUtility.getHitDamage(mc.field_1724.method_6047(), (class_1657)fakePlayer)); }
/* 133 */       else { fakePlayer.method_6033(fakePlayer.method_6032() + fakePlayer.method_6067() - 1.0F); }
/* 134 */        if (fakePlayer.method_29504() && 
/* 135 */         fakePlayer.method_6095(mc.field_1687.method_48963().method_48830())) {
/* 136 */         fakePlayer.method_6033(10.0F);
/* 137 */         (new class_2663((class_1297)fakePlayer, (byte)35)).method_11471((class_2602)mc.field_1724.field_3944);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 145 */     if (fakePlayer == null)
/* 146 */       return;  fakePlayer.method_5768();
/* 147 */     fakePlayer.method_31745(class_1297.class_5529.field_26998);
/* 148 */     fakePlayer.method_36209();
/* 149 */     fakePlayer = null;
/* 150 */     this.positions.clear();
/* 151 */     this.deathTime = 0;
/*     */   }
/*     */   private static final class PlayerState extends Record { private final double x; private final double y; private final double z; private final float yaw; private final float pitch;
/* 154 */     private PlayerState(double x, double y, double z, float yaw, float pitch) { this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/misc/FakePlayer$PlayerState;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #154	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/* 154 */       //   0	7	0	this	Lthunder/hack/features/modules/misc/FakePlayer$PlayerState; } public double x() { return this.x; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/misc/FakePlayer$PlayerState;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #154	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lthunder/hack/features/modules/misc/FakePlayer$PlayerState; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/misc/FakePlayer$PlayerState;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #154	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lthunder/hack/features/modules/misc/FakePlayer$PlayerState;
/* 154 */       //   0	8	1	o	Ljava/lang/Object; } public double y() { return this.y; } public double z() { return this.z; } public float yaw() { return this.yaw; } public float pitch() { return this.pitch; }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\FakePlayer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */