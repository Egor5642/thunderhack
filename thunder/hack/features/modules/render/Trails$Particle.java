/*     */ package thunder.hack.features.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_4184;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import org.joml.Matrix4f;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Particle
/*     */ {
/*     */   double x;
/*     */   double y;
/*     */   double z;
/*     */   double motionX;
/*     */   double motionY;
/*     */   double motionZ;
/*     */   long time;
/*     */   Color color;
/*     */   
/*     */   public Particle(double x, double y, double z, Color color) {
/* 390 */     this.x = x;
/* 391 */     this.y = y;
/* 392 */     this.z = z;
/* 393 */     this.motionX = MathUtility.random(-(((Integer)Trails.this.speed.getValue()).intValue()) / 200.0F, ((Integer)Trails.this.speed.getValue()).intValue() / 200.0F);
/* 394 */     this.motionY = MathUtility.random(-(((Integer)Trails.this.speed.getValue()).intValue()) / 200.0F, ((Integer)Trails.this.speed.getValue()).intValue() / 200.0F);
/* 395 */     this.motionZ = MathUtility.random(-(((Integer)Trails.this.speed.getValue()).intValue()) / 200.0F, ((Integer)Trails.this.speed.getValue()).intValue() / 200.0F);
/* 396 */     this.time = System.currentTimeMillis();
/* 397 */     this.color = color;
/*     */   }
/*     */   
/*     */   public void update() {
/* 401 */     double sp = (((Integer)Trails.this.starsScale.getValue()).intValue() / 10.0F);
/* 402 */     this.x += this.motionX;
/* 403 */     this.y += this.motionY;
/* 404 */     this.z += this.motionZ;
/*     */     
/* 406 */     if (posBlock(this.x, this.y - (((Integer)Trails.this.starsScale.getValue()).intValue() / 10.0F), this.z)) {
/* 407 */       this.motionY = -this.motionY / 1.1D;
/*     */     }
/* 409 */     else if (posBlock(this.x, this.y, this.z) || 
/* 410 */       posBlock(this.x - sp, this.y, this.z - sp) || 
/* 411 */       posBlock(this.x + sp, this.y, this.z + sp) || 
/* 412 */       posBlock(this.x + sp, this.y, this.z - sp) || 
/* 413 */       posBlock(this.x - sp, this.y, this.z + sp) || 
/* 414 */       posBlock(this.x + sp, this.y, this.z) || 
/* 415 */       posBlock(this.x - sp, this.y, this.z) || 
/* 416 */       posBlock(this.x, this.y, this.z - sp) || 
/* 417 */       posBlock(this.x, this.y, this.z + sp)) {
/*     */       
/* 419 */       this.motionX = -this.motionX;
/* 420 */       this.motionZ = -this.motionZ;
/*     */     } 
/*     */ 
/*     */     
/* 424 */     if (Trails.this.physics.getValue() == HitParticles.Physics.Fall) this.motionY -= 5.000000237487257E-4D; 
/* 425 */     this.motionX /= 1.005D;
/* 426 */     this.motionZ /= 1.005D;
/* 427 */     this.motionY /= 1.005D;
/*     */   }
/*     */   
/*     */   public void render(class_4587 matrixStack, class_287 bufferBuilder) {
/* 431 */     update();
/* 432 */     float scale = ((Integer)Trails.this.starsScale.getValue()).intValue() / 10.0F;
/* 433 */     double posX = this.x - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/* 434 */     double posY = this.y - (Module.mc.method_1561()).field_4686.method_19326().method_10214();
/* 435 */     double posZ = this.z - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/*     */     
/* 437 */     class_4184 camera = Module.mc.field_1773.method_19418();
/*     */     
/* 439 */     class_4587 matrices = new class_4587();
/* 440 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 441 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 442 */     matrices.method_22904(posX, posY, posZ);
/* 443 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 444 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/*     */     
/* 446 */     Matrix4f matrix = matrices.method_23760().method_23761();
/*     */     
/* 448 */     float colorAnim = (float)(System.currentTimeMillis() - this.time) / 1000.0F * ((Integer)Trails.this.lifeTime.getValue()).intValue();
/*     */     
/* 450 */     Color c = (Trails.this.lmode.getValue() == Trails.Mode.Sync) ? HudEditor.getColor((int)(360.0F * colorAnim)) : ((ColorSetting)Trails.this.lcolor.getValue()).getColorObject();
/*     */     
/* 452 */     bufferBuilder.method_22918(matrix, -scale / 2.0F, scale, 0.0F).method_22913(0.0F, 1.0F).method_39415(Render2DEngine.applyOpacity(c, 1.0F - colorAnim).getRGB());
/* 453 */     bufferBuilder.method_22918(matrix, scale, scale, 0.0F).method_22913(1.0F, 1.0F).method_39415(Render2DEngine.applyOpacity(c, 1.0F - colorAnim).getRGB());
/* 454 */     bufferBuilder.method_22918(matrix, scale, -scale / 2.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(Render2DEngine.applyOpacity(c, 1.0F - colorAnim).getRGB());
/* 455 */     bufferBuilder.method_22918(matrix, -scale / 2.0F, -scale / 2.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(Render2DEngine.applyOpacity(c, 1.0F - colorAnim).getRGB());
/*     */   }
/*     */   
/*     */   private boolean posBlock(double x, double y, double z) {
/* 459 */     class_2248 b = Module.mc.field_1687.method_8320(class_2338.method_49637(x, y, z)).method_26204();
/* 460 */     return (b != class_2246.field_10124 && b != class_2246.field_10382 && b != class_2246.field_10164);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Trails$Particle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */