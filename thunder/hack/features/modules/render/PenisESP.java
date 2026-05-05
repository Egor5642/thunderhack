/*     */ package thunder.hack.features.modules.render;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_332;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ 
/*     */ public class PenisESP extends Module {
/*     */   private final Setting<Boolean> onlyOwn;
/*     */   private final Setting<Float> ballSize;
/*     */   private final Setting<Float> penisSize;
/*     */   private final Setting<Float> friendSize;
/*     */   
/*     */   public PenisESP() {
/*  21 */     super("PenisESP", Module.Category.RENDER);
/*     */ 
/*     */     
/*  24 */     this.onlyOwn = new Setting("OnlyOwn", Boolean.valueOf(false));
/*  25 */     this.ballSize = new Setting("BallSize", Float.valueOf(0.1F), Float.valueOf(0.1F), Float.valueOf(0.5F));
/*  26 */     this.penisSize = new Setting("PenisSize", Float.valueOf(1.5F), Float.valueOf(0.1F), Float.valueOf(3.0F));
/*  27 */     this.friendSize = new Setting("FriendSize", Float.valueOf(1.5F), Float.valueOf(0.1F), Float.valueOf(3.0F));
/*  28 */     this.enemySize = new Setting("EnemySize", Float.valueOf(0.5F), Float.valueOf(0.1F), Float.valueOf(3.0F));
/*  29 */     this.gradation = new Setting("Gradation", Integer.valueOf(30), Integer.valueOf(20), Integer.valueOf(100));
/*  30 */     this.penisColor = new Setting("PenisColor", new ColorSetting(new Color(231, 180, 122, 255)));
/*  31 */     this.headColor = new Setting("HeadColor", new ColorSetting(new Color(240, 50, 180, 255)));
/*     */   }
/*     */   private final Setting<Float> enemySize; private final Setting<Integer> gradation; private final Setting<ColorSetting> penisColor; private final Setting<ColorSetting> headColor;
/*     */   public void onRender2D(class_332 event) {
/*  35 */     for (class_1657 player : mc.field_1687.method_18456()) {
/*  36 */       if (((Boolean)this.onlyOwn.getValue()).booleanValue() && player != mc.field_1724)
/*  37 */         continue;  double size = (Managers.FRIEND.isFriend(player) ? (Float)this.friendSize.getValue() : ((player != mc.field_1724) ? (Float)this.enemySize.getValue() : (Float)this.penisSize.getValue())).floatValue();
/*     */       
/*  39 */       class_243 base = getBase((class_1297)player);
/*  40 */       class_243 forward = base.method_1031(0.0D, player.method_17682() / 2.4D, 0.0D).method_1019(class_243.method_1030(0.0F, player.method_36454()).method_1021(0.1D));
/*     */       
/*  42 */       class_243 left = forward.method_1019(class_243.method_1030(0.0F, player.method_36454() - 90.0F).method_1021(((Float)this.ballSize.getValue()).floatValue()));
/*  43 */       class_243 right = forward.method_1019(class_243.method_1030(0.0F, player.method_36454() + 90.0F).method_1021(((Float)this.ballSize.getValue()).floatValue()));
/*     */       
/*  45 */       drawBall(player, ((Float)this.ballSize.getValue()).floatValue(), ((Integer)this.gradation.getValue()).intValue(), left, ((ColorSetting)this.penisColor.getValue()).getColorObject(), 0);
/*  46 */       drawBall(player, ((Float)this.ballSize.getValue()).floatValue(), ((Integer)this.gradation.getValue()).intValue(), right, ((ColorSetting)this.penisColor.getValue()).getColorObject(), 0);
/*  47 */       drawPenis(player, event.method_51448(), size, forward);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class_243 getBase(class_1297 entity) {
/*  52 */     double x = entity.field_6014 + (entity.method_23317() - entity.field_6014) * Render3DEngine.getTickDelta();
/*  53 */     double y = entity.field_6036 + (entity.method_23318() - entity.field_6036) * Render3DEngine.getTickDelta();
/*  54 */     double z = entity.field_5969 + (entity.method_23321() - entity.field_5969) * Render3DEngine.getTickDelta();
/*     */     
/*  56 */     return new class_243(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawBall(class_1657 player, double radius, int gradation, class_243 pos, Color color, int stage) {
/*     */     float alpha;
/*  62 */     for (alpha = 0.0F; alpha < Math.PI; alpha = (float)(alpha + Math.PI / gradation)) {
/*  63 */       float beta; for (beta = 0.0F; beta < 6.283185307179586D; beta = (float)(beta + Math.PI / gradation)) {
/*  64 */         double size, x1 = (float)(pos.method_10216() + radius * Math.cos(beta) * Math.sin(alpha));
/*  65 */         double y1 = (float)(pos.method_10214() + radius * Math.sin(beta) * Math.sin(alpha));
/*  66 */         double z1 = (float)(pos.method_10215() + radius * Math.cos(alpha));
/*     */         
/*  68 */         double sin = Math.sin(alpha + Math.PI / gradation);
/*  69 */         double x2 = (float)(pos.method_10216() + radius * Math.cos(beta) * sin);
/*  70 */         double y2 = (float)(pos.method_10214() + radius * Math.sin(beta) * sin);
/*  71 */         double z2 = (float)(pos.method_10215() + radius * Math.cos(alpha + Math.PI / gradation));
/*     */         
/*  73 */         class_243 base = getBase((class_1297)player);
/*  74 */         class_243 forward = base.method_1031(0.0D, player.method_17682() / 2.4D, 0.0D).method_1019(class_243.method_1030(0.0F, player.method_36454()).method_1021(0.1D));
/*  75 */         class_243 vec3d = new class_243(x1, y1, z1);
/*     */         
/*  77 */         switch (stage) {
/*     */           case 1:
/*  79 */             if (!vec3d.method_24802((class_2374)forward, 0.145D))
/*     */               break; 
/*     */           case 2:
/*  82 */             size = (Managers.FRIEND.isFriend(player) ? (Float)this.friendSize.getValue() : ((player != mc.field_1724) ? (Float)this.enemySize.getValue() : (Float)this.penisSize.getValue())).floatValue();
/*  83 */             if (vec3d.method_24802((class_2374)forward, size + 0.095D)) {
/*     */               break;
/*     */             }
/*     */           default:
/*  87 */             Render3DEngine.drawLine(vec3d, new class_243(x2, y2, z2), color);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public void drawPenis(class_1657 player, class_4587 event, double size, class_243 start) {
/*  93 */     class_243 copy = start;
/*  94 */     start = start.method_1019(class_243.method_1030(0.0F, player.method_36454()).method_1021(0.1D));
/*  95 */     class_243 end = start.method_1019(class_243.method_1030(0.0F, player.method_36454()).method_1021(size));
/*     */     
/*  97 */     List<class_243> vecs = getVec3ds(start, 0.1D);
/*  98 */     vecs.forEach(vec3d -> {
/*     */           if (!vec3d.method_24802((class_2374)copy, 0.145D))
/*     */             return;  if (vec3d.method_24802((class_2374)copy, 0.135D))
/*     */             return; 
/*     */           class_243 pos = vec3d.method_1019(class_243.method_1030(0.0F, player.method_36454()).method_1021(size));
/*     */           Render3DEngine.drawLine(vec3d, pos, ((ColorSetting)this.penisColor.getValue()).getColorObject());
/*     */         });
/* 105 */     drawBall(player, 0.1D, ((Integer)this.gradation.getValue()).intValue(), start, ((ColorSetting)this.penisColor.getValue()).getColorObject(), 1);
/* 106 */     drawBall(player, 0.1D, ((Integer)this.gradation.getValue()).intValue(), end, ((ColorSetting)this.headColor.getValue()).getColorObject(), 2);
/*     */   }
/*     */   
/*     */   public List<class_243> getVec3ds(class_243 vec3d, double radius) {
/* 110 */     List<class_243> vec3ds = new ArrayList<>();
/*     */     
/*     */     float alpha;
/* 113 */     for (alpha = 0.0F; alpha < Math.PI; alpha = (float)(alpha + Math.PI / ((Integer)this.gradation.getValue()).intValue())) {
/* 114 */       float beta; for (beta = 0.0F; beta < 6.314601203754922D; beta = (float)(beta + Math.PI / ((Integer)this.gradation.getValue()).intValue())) {
/* 115 */         double x1 = (float)(vec3d.method_10216() + radius * Math.cos(beta) * Math.sin(alpha));
/* 116 */         double y1 = (float)(vec3d.method_10214() + radius * Math.sin(beta) * Math.sin(alpha));
/* 117 */         double z1 = (float)(vec3d.method_10215() + radius * Math.cos(alpha));
/*     */         
/* 119 */         class_243 vec = new class_243(x1, y1, z1);
/* 120 */         vec3ds.add(vec);
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return vec3ds;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\PenisESP.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */