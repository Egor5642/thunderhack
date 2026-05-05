/*     */ package thunder.hack.features.modules.player;
/*     */ import baritone.api.BaritoneAPI;
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1792;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_1802;
/*     */ import net.minecraft.class_9334;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.Setting;
/*     */ 
/*     */ public class AutoEat extends Module {
/*     */   public final Setting<Integer> hunger;
/*     */   public final Setting<Boolean> gapple;
/*     */   
/*     */   public AutoEat() {
/*  17 */     super("AutoEat", Module.Category.PLAYER);
/*     */ 
/*     */     
/*  20 */     this.hunger = new Setting("Hunger", Integer.valueOf(8), Integer.valueOf(0), Integer.valueOf(20));
/*  21 */     this.gapple = new Setting("Gapple", Boolean.valueOf(false));
/*  22 */     this.chorus = new Setting("Chorus", Boolean.valueOf(false));
/*  23 */     this.rottenFlesh = new Setting("RottenFlesh", Boolean.valueOf(false));
/*  24 */     this.spiderEye = new Setting("SpiderEye", Boolean.valueOf(false));
/*  25 */     this.pufferfish = new Setting("Pufferfish", Boolean.valueOf(false));
/*  26 */     this.swapBack = new Setting("SwapBack", Boolean.valueOf(true));
/*  27 */     this.pauseBaritone = new Setting("PauseBaritone", Boolean.valueOf(true), v -> ThunderHack.baritone);
/*     */   }
/*     */   public final Setting<Boolean> chorus; public final Setting<Boolean> rottenFlesh; public final Setting<Boolean> spiderEye; public final Setting<Boolean> pufferfish; public final Setting<Boolean> swapBack; public final Setting<Boolean> pauseBaritone;
/*     */   private boolean eating;
/*     */   private int prevSlot;
/*     */   
/*     */   public void onUpdate() {
/*  34 */     if (mc.field_1724.method_7344().method_7586() <= ((Integer)this.hunger.getValue()).intValue()) {
/*     */       boolean found;
/*     */ 
/*     */       
/*  38 */       if (!isHandGood(class_1268.field_5808) && !isHandGood(class_1268.field_5810))
/*  39 */       { found = switchToFood(); }
/*  40 */       else { found = true; }
/*     */       
/*  42 */       if (!found) {
/*  43 */         if (this.eating) {
/*  44 */           stopEating();
/*     */         }
/*     */         return;
/*     */       } 
/*  48 */       startEating();
/*  49 */     } else if (this.eating) {
/*  50 */       stopEating();
/*     */     } 
/*     */   }
/*     */   public void startEating() {
/*  54 */     this.eating = true;
/*     */     
/*  56 */     if (mc.field_1755 != null && !mc.field_1724.method_6115()) {
/*  57 */       ((IMinecraftClient)mc).idoItemUse();
/*     */     } else {
/*  59 */       if (((Boolean)this.pauseBaritone.getValue()).booleanValue() && ThunderHack.baritone) {
/*  60 */         BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
/*     */       }
/*  62 */       mc.field_1690.field_1904.method_23481(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stopEating() {
/*  67 */     this.eating = false;
/*  68 */     mc.field_1690.field_1904.method_23481(false);
/*  69 */     if (((Boolean)this.swapBack.getValue()).booleanValue()) {
/*  70 */       (mc.field_1724.method_31548()).field_7545 = this.prevSlot;
/*     */     }
/*  72 */     if (((Boolean)this.pauseBaritone.getValue()).booleanValue() && ThunderHack.baritone)
/*  73 */       BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume"); 
/*     */   }
/*     */   
/*     */   public boolean switchToFood() {
/*  77 */     for (int i = 0; i < 9; i++) {
/*  78 */       class_1799 stack = mc.field_1724.method_31548().method_5438(i);
/*  79 */       if (stack.method_57353().method_57832(class_9334.field_50075) && ((
/*  80 */         (Boolean)this.gapple.getValue()).booleanValue() || (stack.method_7909() != class_1802.field_8463 && stack.method_7909() != class_1802.field_8367)))
/*     */       {
/*  82 */         if (((Boolean)this.chorus.getValue()).booleanValue() || stack.method_7909() != class_1802.field_8233)
/*     */         {
/*  84 */           if (((Boolean)this.rottenFlesh.getValue()).booleanValue() || stack.method_7909() != class_1802.field_8511)
/*     */           {
/*  86 */             if (((Boolean)this.spiderEye.getValue()).booleanValue() || stack.method_7909() != class_1802.field_8680)
/*     */             {
/*  88 */               if (((Boolean)this.pufferfish.getValue()).booleanValue() || stack.method_7909() != class_1802.field_8323) {
/*     */                 
/*  90 */                 this.prevSlot = (mc.field_1724.method_31548()).field_7545;
/*  91 */                 (mc.field_1724.method_31548()).field_7545 = i;
/*  92 */                 sendPacket((class_2596)new class_2868(i));
/*  93 */                 return true;
/*     */               }  }  }  }  } 
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isHandGood(class_1268 hand) {
/* 101 */     class_1799 stack = (hand == class_1268.field_5808) ? mc.field_1724.method_6047() : mc.field_1724.method_6079();
/*     */     
/* 103 */     class_1792 item = stack.method_7909();
/* 104 */     return (stack.method_57353().method_57832(class_9334.field_50075) && (((Boolean)this.gapple
/* 105 */       .getValue()).booleanValue() || (item != class_1802.field_8463 && item != class_1802.field_8367)) && (((Boolean)this.chorus
/* 106 */       .getValue()).booleanValue() || item != class_1802.field_8233) && (((Boolean)this.rottenFlesh
/* 107 */       .getValue()).booleanValue() || item != class_1802.field_8511) && (((Boolean)this.spiderEye
/* 108 */       .getValue()).booleanValue() || item != class_1802.field_8680) && (((Boolean)this.pufferfish
/* 109 */       .getValue()).booleanValue() || item != class_1802.field_8323));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\AutoEat.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */