/*     */ package thunder.hack.core.manager.client;
/*     */ 
/*     */ import java.io.File;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.Clip;
/*     */ import javax.sound.sampled.FloatControl;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2378;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_3414;
/*     */ import net.minecraft.class_3419;
/*     */ import net.minecraft.class_7923;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.features.modules.client.SoundFX;
/*     */ import thunder.hack.features.modules.misc.ChatUtils;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ 
/*     */ public class SoundManager
/*     */   implements IManager
/*     */ {
/*  25 */   public final class_2960 KEYPRESS_SOUND = class_2960.method_60654("thunderhack:keypress");
/*  26 */   public class_3414 KEYPRESS_SOUNDEVENT = class_3414.method_47908(this.KEYPRESS_SOUND);
/*  27 */   public final class_2960 KEYRELEASE_SOUND = class_2960.method_60654("thunderhack:keyrelease");
/*  28 */   public class_3414 KEYRELEASE_SOUNDEVENT = class_3414.method_47908(this.KEYRELEASE_SOUND);
/*  29 */   public final class_2960 UWU_SOUND = class_2960.method_60654("thunderhack:uwu");
/*  30 */   public class_3414 UWU_SOUNDEVENT = class_3414.method_47908(this.UWU_SOUND);
/*  31 */   public final class_2960 ENABLE_SOUND = class_2960.method_60654("thunderhack:enable");
/*  32 */   public class_3414 ENABLE_SOUNDEVENT = class_3414.method_47908(this.ENABLE_SOUND);
/*  33 */   public final class_2960 DISABLE_SOUND = class_2960.method_60654("thunderhack:disable");
/*  34 */   public class_3414 DISABLE_SOUNDEVENT = class_3414.method_47908(this.DISABLE_SOUND);
/*  35 */   public final class_2960 MOAN1_SOUND = class_2960.method_60654("thunderhack:moan1");
/*  36 */   public class_3414 MOAN1_SOUNDEVENT = class_3414.method_47908(this.MOAN1_SOUND);
/*  37 */   public final class_2960 MOAN2_SOUND = class_2960.method_60654("thunderhack:moan2");
/*  38 */   public class_3414 MOAN2_SOUNDEVENT = class_3414.method_47908(this.MOAN2_SOUND);
/*  39 */   public final class_2960 MOAN3_SOUND = class_2960.method_60654("thunderhack:moan3");
/*  40 */   public class_3414 MOAN3_SOUNDEVENT = class_3414.method_47908(this.MOAN3_SOUND);
/*  41 */   public final class_2960 MOAN4_SOUND = class_2960.method_60654("thunderhack:moan4");
/*  42 */   public class_3414 MOAN4_SOUNDEVENT = class_3414.method_47908(this.MOAN4_SOUND);
/*  43 */   public final class_2960 SKEET_SOUND = class_2960.method_60654("thunderhack:skeet");
/*  44 */   public class_3414 SKEET_SOUNDEVENT = class_3414.method_47908(this.SKEET_SOUND);
/*  45 */   public final class_2960 ORTHODOX_SOUND = class_2960.method_60654("thunderhack:orthodox");
/*  46 */   public class_3414 ORTHODOX_SOUNDEVENT = class_3414.method_47908(this.ORTHODOX_SOUND);
/*  47 */   public final class_2960 BOOLEAN_SOUND = class_2960.method_60654("thunderhack:boolean");
/*  48 */   public class_3414 BOOLEAN_SOUNDEVENT = class_3414.method_47908(this.BOOLEAN_SOUND);
/*  49 */   public final class_2960 SCROLL_SOUND = class_2960.method_60654("thunderhack:scroll");
/*  50 */   public class_3414 SCROLL_SOUNDEVENT = class_3414.method_47908(this.SCROLL_SOUND);
/*  51 */   public final class_2960 SWIPEIN_SOUND = class_2960.method_60654("thunderhack:swipein");
/*  52 */   public class_3414 SWIPEIN_SOUNDEVENT = class_3414.method_47908(this.SWIPEIN_SOUND);
/*  53 */   public final class_2960 SWIPEOUT_SOUND = class_2960.method_60654("thunderhack:swipeout");
/*  54 */   public class_3414 SWIPEOUT_SOUNDEVENT = class_3414.method_47908(this.SWIPEOUT_SOUND);
/*  55 */   public final class_2960 ALERT_SOUND = class_2960.method_60654("thunderhack:alert");
/*  56 */   public class_3414 ALERT_SOUNDEVENT = class_3414.method_47908(this.ALERT_SOUND);
/*  57 */   public final class_2960 PM_SOUND = class_2960.method_60654("thunderhack:pmsound");
/*  58 */   public class_3414 PM_SOUNDEVENT = class_3414.method_47908(this.PM_SOUND);
/*  59 */   public final class_2960 RIFK_SOUND = class_2960.method_60654("thunderhack:rifk");
/*  60 */   public class_3414 RIFK_SOUNDEVENT = class_3414.method_47908(this.RIFK_SOUND);
/*  61 */   public final class_2960 CUTIE_SOUND = class_2960.method_60654("thunderhack:cutie");
/*  62 */   public class_3414 CUTIE_SOUNDEVENT = class_3414.method_47908(this.CUTIE_SOUND);
/*     */ 
/*     */   
/*  65 */   private final Timer scrollTimer = new Timer();
/*     */   
/*     */   public void registerSounds() {
/*  68 */     class_2378.method_10230(class_7923.field_41172, this.KEYPRESS_SOUND, this.KEYPRESS_SOUNDEVENT);
/*  69 */     class_2378.method_10230(class_7923.field_41172, this.KEYRELEASE_SOUND, this.KEYRELEASE_SOUNDEVENT);
/*  70 */     class_2378.method_10230(class_7923.field_41172, this.ENABLE_SOUND, this.ENABLE_SOUNDEVENT);
/*  71 */     class_2378.method_10230(class_7923.field_41172, this.DISABLE_SOUND, this.DISABLE_SOUNDEVENT);
/*  72 */     class_2378.method_10230(class_7923.field_41172, this.MOAN1_SOUND, this.MOAN1_SOUNDEVENT);
/*  73 */     class_2378.method_10230(class_7923.field_41172, this.MOAN2_SOUND, this.MOAN2_SOUNDEVENT);
/*  74 */     class_2378.method_10230(class_7923.field_41172, this.MOAN3_SOUND, this.MOAN3_SOUNDEVENT);
/*  75 */     class_2378.method_10230(class_7923.field_41172, this.MOAN4_SOUND, this.MOAN4_SOUNDEVENT);
/*  76 */     class_2378.method_10230(class_7923.field_41172, this.UWU_SOUND, this.UWU_SOUNDEVENT);
/*     */     
/*  78 */     class_2378.method_10230(class_7923.field_41172, this.SKEET_SOUND, this.SKEET_SOUNDEVENT);
/*  79 */     class_2378.method_10230(class_7923.field_41172, this.ORTHODOX_SOUND, this.ORTHODOX_SOUNDEVENT);
/*  80 */     class_2378.method_10230(class_7923.field_41172, this.SCROLL_SOUND, this.SCROLL_SOUNDEVENT);
/*  81 */     class_2378.method_10230(class_7923.field_41172, this.BOOLEAN_SOUND, this.BOOLEAN_SOUNDEVENT);
/*  82 */     class_2378.method_10230(class_7923.field_41172, this.SWIPEIN_SOUND, this.SWIPEIN_SOUNDEVENT);
/*  83 */     class_2378.method_10230(class_7923.field_41172, this.SWIPEOUT_SOUND, this.SWIPEOUT_SOUNDEVENT);
/*  84 */     class_2378.method_10230(class_7923.field_41172, this.ALERT_SOUND, this.ALERT_SOUNDEVENT);
/*  85 */     class_2378.method_10230(class_7923.field_41172, this.PM_SOUND, this.PM_SOUNDEVENT);
/*  86 */     class_2378.method_10230(class_7923.field_41172, this.RIFK_SOUND, this.RIFK_SOUNDEVENT);
/*  87 */     class_2378.method_10230(class_7923.field_41172, this.CUTIE_SOUND, this.CUTIE_SOUNDEVENT);
/*     */   }
/*     */   public void playHitSound(SoundFX.HitSound value) {
/*     */     class_3414 sound;
/*  91 */     switch (value) { case UWU:
/*  92 */         playSound(this.UWU_SOUNDEVENT); break;
/*  93 */       case SKEET: playSound(this.SKEET_SOUNDEVENT); break;
/*  94 */       case KEYBOARD: playSound(this.KEYPRESS_SOUNDEVENT); break;
/*  95 */       case CUTIE: playSound(this.CUTIE_SOUNDEVENT); break;
/*     */       case MOAN:
/*  97 */         switch ((int)MathUtility.random(0.0F, 3.0F)) { case 0: 
/*     */           case 1: 
/*     */           case 2: 
/*     */           default:
/* 101 */             break; }  sound = this.MOAN4_SOUNDEVENT;
/*     */         
/* 103 */         playSound(sound); break;
/*     */       case RIFK:
/* 105 */         playSound(this.RIFK_SOUNDEVENT); break;
/* 106 */       case CUSTOM: playSound("hit");
/*     */         break; }
/*     */   
/*     */   }
/*     */   public void playEnable() {
/* 111 */     if (ModuleManager.soundFX.enableMode.getValue() == SoundFX.OnOffSound.Inertia) {
/* 112 */       playSound(this.ENABLE_SOUNDEVENT);
/* 113 */     } else if (ModuleManager.soundFX.enableMode.getValue() == SoundFX.OnOffSound.Custom) {
/* 114 */       playSound("enable");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void playDisable() {
/* 119 */     if (ModuleManager.soundFX.disableMode.getValue() == SoundFX.OnOffSound.Inertia) {
/* 120 */       playSound(this.DISABLE_SOUNDEVENT);
/* 121 */     } else if (ModuleManager.soundFX.disableMode.getValue() == SoundFX.OnOffSound.Custom) {
/* 122 */       playSound("disable");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void playScroll() {
/* 127 */     if (this.scrollTimer.every(50L)) {
/* 128 */       if (ModuleManager.soundFX.scrollSound.getValue() == SoundFX.ScrollSound.KeyBoard) {
/* 129 */         playSound(this.KEYPRESS_SOUNDEVENT);
/* 130 */       } else if (ModuleManager.soundFX.scrollSound.getValue() == SoundFX.ScrollSound.Custom) {
/* 131 */         playSound("scroll");
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void playSound(class_3414 sound) {
/* 137 */     if (mc.field_1724 != null && mc.field_1687 != null)
/* 138 */       mc.field_1687.method_8396((class_1657)mc.field_1724, mc.field_1724.method_24515(), sound, class_3419.field_15245, ((Integer)ModuleManager.soundFX.volume.getValue()).intValue() / 100.0F, 1.0F); 
/*     */   }
/*     */   
/*     */   public void playSound(String name) {
/*     */     try {
/* 143 */       Clip clip = AudioSystem.getClip();
/* 144 */       clip.open(AudioSystem.getAudioInputStream((new File(ConfigManager.SOUNDS_FOLDER, name + ".wav")).getAbsoluteFile()));
/* 145 */       FloatControl floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
/* 146 */       floatControl.setValue(floatControl.getMaximum() - floatControl.getMinimum() * ((Integer)ModuleManager.soundFX.volume.getValue()).intValue() / 100.0F + floatControl.getMinimum());
/* 147 */       clip.start();
/* 148 */     } catch (Exception e) {
/* 149 */       Command.sendMessage((ClientSettings.isRu() ? "Ошибка воспроизведения звука! Проверь " : "Error with playing sound! Check ") + (ClientSettings.isRu() ? "Ошибка воспроизведения звука! Проверь " : "Error with playing sound! Check "));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void playSlider() {
/* 154 */     playSound(this.SCROLL_SOUNDEVENT);
/*     */   }
/*     */   
/*     */   public void playBoolean() {
/* 158 */     playSound(this.BOOLEAN_SOUNDEVENT);
/*     */   }
/*     */   
/*     */   public void playSwipeIn() {
/* 162 */     playSound(this.SWIPEIN_SOUNDEVENT);
/*     */   }
/*     */   
/*     */   public void playSwipeOut() {
/* 166 */     playSound(this.SWIPEOUT_SOUNDEVENT);
/*     */   }
/*     */   
/*     */   public void playPmSound(ChatUtils.PMSound sound) {
/* 170 */     if (sound == ChatUtils.PMSound.Default) { playSound(this.PM_SOUNDEVENT); }
/* 171 */     else { Managers.SOUND.playSound("pmsound"); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\SoundManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */