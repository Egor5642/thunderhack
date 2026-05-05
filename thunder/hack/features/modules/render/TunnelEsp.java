/*     */ package thunder.hack.features.modules.render;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_238;
/*     */ import net.minecraft.class_4587;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class TunnelEsp extends Module {
/*     */   private final Setting<ColorSetting> color;
/*     */   public Setting<Boolean> box;
/*     */   public Setting<Boolean> outline;
/*     */   List<class_238> renderBoxes;
/*     */   private Timer delayTimer;
/*     */   
/*     */   public TunnelEsp() {
/*  20 */     super("TunnelEsp", Module.Category.RENDER);
/*     */ 
/*     */     
/*  23 */     this.color = new Setting("Color", new ColorSetting(new Color(-1366652170, true)));
/*  24 */     this.box = new Setting("Box", Boolean.valueOf(true));
/*  25 */     this.outline = new Setting("Outline", Boolean.valueOf(true));
/*  26 */     this.renderBoxes = new ArrayList<>();
/*  27 */     this.delayTimer = new Timer();
/*     */   }
/*     */   public void onRender3D(class_4587 stack) {
/*     */     try {
/*  31 */       for (class_238 box_ : this.renderBoxes) {
/*     */         
/*  33 */         if (box_.method_17941() < 5.0D && box_.method_17939() < 5.0D) {
/*     */           continue;
/*     */         }
/*  36 */         if (((Boolean)this.box.getValue()).booleanValue()) Render3DEngine.drawFilledBox(stack, box_, ((ColorSetting)this.color.getValue()).getColorObject()); 
/*  37 */         if (((Boolean)this.outline.getValue()).booleanValue())
/*  38 */           Render3DEngine.drawBoxOutline(box_, Render2DEngine.injectAlpha(((ColorSetting)this.color.getValue()).getColorObject(), 255), 2.0F); 
/*     */       } 
/*  40 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  46 */     this.renderBoxes.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onThread() {
/*  51 */     if (this.delayTimer.passedMs(2000L)) {
/*  52 */       for (int x = (int)(mc.field_1724.method_23317() - 128.0D); x < mc.field_1724.method_23317() + 128.0D; x++) {
/*  53 */         for (int z = (int)(mc.field_1724.method_23321() - 128.0D); z < mc.field_1724.method_23321() + 128.0D; z++) {
/*  54 */           for (int y = 0; y < 121; y++) {
/*  55 */             class_2338 bp = new class_2338(x, y, z);
/*     */             
/*  57 */             if (one_two(bp) && !alreadyIn(new class_238(bp.method_10263(), bp.method_10264(), bp.method_10260(), (bp.method_10263() + 1), (bp.method_10264() + 2), (bp.method_10260() + 1)))) {
/*  58 */               class_238 renderBox = new class_238(bp.method_10263(), bp.method_10264(), bp.method_10260(), (bp.method_10263() + 1), (bp.method_10264() + 2), (bp.method_10260() + 1));
/*  59 */               this.renderBoxes.add(getFullBox(renderBox, x, y, z, 1));
/*     */             } 
/*     */             
/*  62 */             if (one_one(bp) && !alreadyIn(new class_238(bp))) {
/*  63 */               class_238 renderBox = new class_238(bp);
/*  64 */               this.renderBoxes.add(getFullBox(renderBox, x, y, z, 0));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       this.delayTimer.reset();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class_238 getFullBox(class_238 raw, int x, int y, int z, int mode) {
/*  81 */     class_2338 checkBp1 = new class_2338(x, y, z + 1);
/*     */     
/*  83 */     Function<class_2338, Boolean> check = getCheckByMode(mode);
/*     */     
/*  85 */     while (((Boolean)check.apply(checkBp1)).booleanValue()) {
/*  86 */       raw = raw.method_35579(raw.field_1324 + 1.0D);
/*  87 */       checkBp1 = checkBp1.method_10072();
/*     */     } 
/*     */     
/*  90 */     class_2338 checkBp2 = new class_2338(x + 1, y, z);
/*  91 */     while (((Boolean)check.apply(checkBp2)).booleanValue()) {
/*  92 */       raw = raw.method_35577(raw.field_1320 + 1.0D);
/*  93 */       checkBp2 = checkBp2.method_10078();
/*     */     } 
/*     */     
/*  96 */     class_2338 checkBp3 = new class_2338(x, y, z - 1);
/*  97 */     while (((Boolean)check.apply(checkBp3)).booleanValue()) {
/*  98 */       raw = raw.method_35576(raw.field_1321 - 1.0D);
/*  99 */       checkBp3 = checkBp3.method_10095();
/*     */     } 
/*     */     
/* 102 */     class_2338 checkBp4 = new class_2338(x - 1, y, z);
/* 103 */     while (((Boolean)check.apply(checkBp4)).booleanValue()) {
/* 104 */       raw = raw.method_35574(raw.field_1323 - 1.0D);
/* 105 */       checkBp4 = checkBp4.method_10067();
/*     */     } 
/*     */     
/* 108 */     return raw;
/*     */   }
/*     */   
/*     */   private Function<class_2338, Boolean> getCheckByMode(int mode) {
/* 112 */     switch (mode) { case 1: case 2:  }  return 
/*     */ 
/*     */       
/* 115 */       TunnelEsp::one_one;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean alreadyIn(class_238 box) {
/* 120 */     for (class_238 box2 : this.renderBoxes) {
/* 121 */       if (box.method_994(box2))
/* 122 */         return true; 
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean one_three(class_2338 pos) {
/* 129 */     if (!isAir(pos) || !isAir(pos.method_10084()) || !isAir(pos.method_10084().method_10084())) return false; 
/* 130 */     if (isAir(pos.method_10074()) || isAir(pos.method_10084().method_10084().method_10084())) return false;
/*     */     
/* 132 */     if (isAir(pos.method_10084().method_10095()) && isAir(pos.method_10084().method_10072())) {
/* 133 */       return (!isAir(pos.method_10084().method_10078()) && !isAir(pos.method_10084().method_10067()));
/*     */     }
/* 135 */     if (isAir(pos.method_10084().method_10078()) && isAir(pos.method_10084().method_10067())) {
/* 136 */       return (!isAir(pos.method_10084().method_10095()) && !isAir(pos.method_10084().method_10072()));
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean one_two(class_2338 pos) {
/* 143 */     if (!isAir(pos) || !isAir(pos.method_10084())) return false; 
/* 144 */     if (isAir(pos.method_10074()) || isAir(pos.method_10084().method_10084())) return false;
/*     */     
/* 146 */     if (isAir(pos.method_10095()) && isAir(pos.method_10072()) && isAir(pos.method_10084().method_10095()) && isAir(pos.method_10084().method_10072())) {
/* 147 */       return (!isAir(pos.method_10078()) && !isAir(pos.method_10067()) && !isAir(pos.method_10084().method_10078()) && !isAir(pos.method_10084().method_10067()));
/*     */     }
/* 149 */     if (isAir(pos.method_10078()) && isAir(pos.method_10067()) && isAir(pos.method_10084().method_10078()) && isAir(pos.method_10084().method_10067())) {
/* 150 */       return (!isAir(pos.method_10095()) && !isAir(pos.method_10072()) && !isAir(pos.method_10084().method_10095()) && !isAir(pos.method_10084().method_10072()));
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean one_one(class_2338 pos) {
/* 157 */     if (!isAir(pos)) return false; 
/* 158 */     if (isAir(pos.method_10074()) || isAir(pos.method_10084())) return false;
/*     */     
/* 160 */     if (isAir(pos.method_10095()) && isAir(pos.method_10072())) {
/* 161 */       return (!isAir(pos.method_10078()) && !isAir(pos.method_10067()) && !isAir(pos.method_10084().method_10078()) && !isAir(pos.method_10084().method_10067()));
/*     */     }
/* 163 */     if (isAir(pos.method_10078()) && isAir(pos.method_10067())) {
/* 164 */       return (!isAir(pos.method_10095()) && !isAir(pos.method_10072()));
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isAir(class_2338 bp) {
/* 171 */     return mc.field_1687.method_22347(bp);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\TunnelEsp.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */