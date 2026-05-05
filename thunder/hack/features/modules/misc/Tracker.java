/*     */ package thunder.hack.features.modules.misc;
/*     */ 
/*     */ import io.netty.util.internal.ConcurrentSet;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2885;
/*     */ import net.minecraft.class_7439;
/*     */ import thunder.hack.events.impl.EventEntitySpawn;
/*     */ import thunder.hack.events.impl.PacketEvent;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ 
/*     */ public class Tracker
/*     */   extends Module {
/*     */   protected final Setting<Boolean> only1v1;
/*     */   protected final Set<class_2338> placed;
/*     */   protected final AtomicInteger awaitingExp;
/*     */   
/*     */   public Tracker() {
/*  28 */     super("Tracker", Module.Category.MISC);
/*     */ 
/*     */     
/*  31 */     this.only1v1 = new Setting("1v1-Only", Boolean.valueOf(true));
/*     */     
/*  33 */     this.placed = (Set<class_2338>)new ConcurrentSet();
/*  34 */     this.awaitingExp = new AtomicInteger();
/*  35 */   } protected static final AtomicInteger crystals = new AtomicInteger();
/*  36 */   protected static final AtomicInteger exp = new AtomicInteger();
/*     */   
/*     */   protected static class_1657 trackedPlayer;
/*     */   protected boolean awaiting;
/*     */   protected int crystalStacks;
/*     */   protected int expStacks;
/*     */   
/*     */   public void onEnable() {
/*  44 */     this.awaiting = false;
/*  45 */     trackedPlayer = null;
/*  46 */     this.awaitingExp.set(0);
/*  47 */     crystals.set(0);
/*  48 */     exp.set(0);
/*  49 */     this.crystalStacks = 0;
/*  50 */     this.expStacks = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayInfo() {
/*  55 */     return (trackedPlayer == null) ? null : trackedPlayer.method_5477().getString();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/*  60 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_7439) { class_7439 pac = (class_7439)class_2596;
/*     */       
/*  62 */       String s = pac.comp_763().getString();
/*  63 */       if (!s.contains("<") && (s.contains("has accepted your duel request") || s.contains("Accepted the duel request from"))) {
/*  64 */         sendMessage(ClientSettings.isRu() ? "Дуель принята! Обновляю цель..." : "Duel accepted! Resetting target...");
/*  65 */         trackedPlayer = null;
/*  66 */         this.awaitingExp.set(0);
/*  67 */         crystals.set(0);
/*  68 */         exp.set(0);
/*  69 */         this.crystalStacks = 0;
/*  70 */         this.expStacks = 0;
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntitySpawn(EventEntitySpawn e) {
/*  77 */     if (e.getEntity() instanceof net.minecraft.class_1511 && 
/*  78 */       !this.placed.remove(class_2338.method_49637(e.getEntity().method_23317(), e.getEntity().method_23318() - 1.0D, e.getEntity().method_23321()))) {
/*  79 */       crystals.incrementAndGet();
/*     */     }
/*     */     
/*  82 */     if (e.getEntity() instanceof net.minecraft.class_1683) {
/*  83 */       if (this.awaitingExp.get() > 0)
/*  84 */       { if (mc.field_1724.method_5858(e.getEntity()) < 16.0D) { this.awaitingExp.decrementAndGet(); }
/*  85 */         else { exp.incrementAndGet(); }  }
/*  86 */       else { exp.incrementAndGet(); }
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/*  92 */     boolean found = false;
/*  93 */     for (class_1657 player : mc.field_1687.method_18456()) {
/*  94 */       if (player == null || player.equals(mc.field_1724))
/*     */         continue; 
/*  96 */       if (found && ((Boolean)this.only1v1.getValue()).booleanValue()) {
/*  97 */         disable(ClientSettings.isRu() ? "Ты не в дуели! Отключаю.." : "Disabled, you are not in a 1v1! Disabling...");
/*     */         return;
/*     */       } 
/* 100 */       if (trackedPlayer == null)
/* 101 */         sendMessage(String.valueOf(class_124.field_1076) + String.valueOf(class_124.field_1076) + (ClientSettings.isRu() ? "Следим за " : "Now tracking ") + String.valueOf(class_124.field_1064) + player.method_5477().getString() + "!"); 
/* 102 */       trackedPlayer = player;
/* 103 */       found = true;
/*     */     } 
/*     */     
/* 106 */     if (trackedPlayer == null)
/*     */       return; 
/* 108 */     this; int exp = Tracker.exp.get() / 64;
/* 109 */     if (this.expStacks != exp) {
/* 110 */       this.expStacks = exp;
/* 111 */       if (ClientSettings.isRu()) { sendMessage(String.valueOf(class_124.field_1064) + String.valueOf(class_124.field_1064) + trackedPlayer.method_5477().getString() + " использовал " + String.valueOf(class_124.field_1076) + String.valueOf(class_124.field_1068) + exp + String.valueOf(class_124.field_1076) + " Пузырьков опыта!"); }
/* 112 */       else { sendMessage(String.valueOf(class_124.field_1064) + String.valueOf(class_124.field_1064) + trackedPlayer.method_5477().getString() + " used " + String.valueOf(class_124.field_1076) + String.valueOf(class_124.field_1068) + exp + String.valueOf(class_124.field_1076) + " of XP Bottles!"); }
/*     */     
/*     */     } 
/* 115 */     this; int crystals = Tracker.crystals.get() / 64;
/* 116 */     if (this.crystalStacks != crystals) {
/* 117 */       this.crystalStacks = crystals;
/* 118 */       if (!ClientSettings.isRu()) { sendMessage(String.valueOf(class_124.field_1064) + String.valueOf(class_124.field_1064) + trackedPlayer.method_5477().getString() + " used " + String.valueOf(class_124.field_1076) + String.valueOf(class_124.field_1068) + crystals + String.valueOf(class_124.field_1076) + " of Crystals!"); }
/* 119 */       else { sendMessage(String.valueOf(class_124.field_1064) + String.valueOf(class_124.field_1064) + trackedPlayer.method_5477().getString() + " использовал " + String.valueOf(class_124.field_1076) + String.valueOf(class_124.field_1068) + crystals + String.valueOf(class_124.field_1076) + " Кристаллов!"); }
/*     */     
/*     */     } 
/*     */   }
/*     */   public void sendTrack() {
/* 124 */     if (trackedPlayer != null) {
/* 125 */       StringBuilder builder; int c = crystals.get();
/* 126 */       int e = exp.get();
/*     */ 
/*     */       
/* 129 */       if (ClientSettings.isRu()) { builder = (new StringBuilder()).append(trackedPlayer.method_5477().getString()).append(class_124.field_1076).append(" использовал ").append(class_124.field_1068).append(c).append(class_124.field_1076).append(" (").append(class_124.field_1068); }
/* 130 */       else { builder = (new StringBuilder()).append(trackedPlayer.method_5477().getString()).append(class_124.field_1076).append(" has used ").append(class_124.field_1068).append(c).append(class_124.field_1076).append(" (").append(class_124.field_1068); }
/*     */       
/* 132 */       if (c % 64 == 0) { builder.append(c / 64); }
/* 133 */       else { builder.append(MathUtility.round(c / 64.0D, 1)); }
/*     */       
/* 135 */       if (ClientSettings.isRu()) { builder.append(class_124.field_1076).append(") кристаллов и ").append(class_124.field_1068).append(e).append(class_124.field_1076).append(" (").append(class_124.field_1068); }
/* 136 */       else { builder.append(class_124.field_1076).append(") crystals and ").append(class_124.field_1068).append(e).append(class_124.field_1076).append(" (").append(class_124.field_1068); }
/*     */       
/* 138 */       if (e % 64 == 0) { builder.append(e / 64); }
/* 139 */       else { builder.append(MathUtility.round(e / 64.0D, 1)); }
/*     */       
/* 141 */       if (ClientSettings.isRu()) { builder.append(class_124.field_1076).append(") пузырьков опыта."); }
/* 142 */       else { builder.append(class_124.field_1076).append(") bottles of experience."); }
/*     */       
/* 144 */       sendMessage(builder.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketSend(PacketEvent.Send event) {
/* 150 */     if (event.getPacket() instanceof net.minecraft.class_2886 && (
/* 151 */       mc.field_1724.method_6047().method_7909() == class_1802.field_8287 || mc.field_1724.method_6079().method_7909() == class_1802.field_8287)) {
/* 152 */       this.awaitingExp.incrementAndGet();
/*     */     }
/*     */     
/* 155 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2885) { class_2885 pac = (class_2885)class_2596;
/* 156 */       if (mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || mc.field_1724.method_6079().method_7909() == class_1802.field_8301)
/* 157 */         this.placed.add(pac.method_12543().method_17777());  }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\Tracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */