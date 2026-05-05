/*     */ package thunder.hack.features.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2248;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.gui.font.FontRenderers;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ import thunder.hack.utility.render.Render2DEngine;
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.animation.AnimationUtility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   float x;
/*     */   float y;
/*     */   float z;
/*     */   float px;
/*     */   float py;
/*     */   float pz;
/*     */   float motionX;
/*     */   float motionY;
/*     */   float motionZ;
/*     */   float rotationAngle;
/*     */   float rotationSpeed;
/*     */   float health;
/*     */   long time;
/*     */   Color color;
/*     */   
/*     */   public Particle(float x, float y, float z, Color color, float rotationAngle, float rotationSpeed, float health) {
/* 114 */     this.x = x;
/* 115 */     this.y = y;
/* 116 */     this.z = z;
/* 117 */     this.px = x;
/* 118 */     this.py = y;
/* 119 */     this.pz = z;
/* 120 */     this.motionX = MathUtility.random(-(((Integer)HitParticles.this.speed.getValue()).intValue()) / 50.0F, ((Integer)HitParticles.this.speed.getValue()).intValue() / 50.0F);
/* 121 */     this.motionY = MathUtility.random(-(((Integer)HitParticles.this.speed.getValue()).intValue()) / 50.0F, ((Integer)HitParticles.this.speed.getValue()).intValue() / 50.0F);
/* 122 */     this.motionZ = MathUtility.random(-(((Integer)HitParticles.this.speed.getValue()).intValue()) / 50.0F, ((Integer)HitParticles.this.speed.getValue()).intValue() / 50.0F);
/* 123 */     this.time = System.currentTimeMillis();
/* 124 */     this.color = color;
/* 125 */     this.rotationAngle = rotationAngle;
/* 126 */     this.rotationSpeed = rotationSpeed;
/* 127 */     this.health = health;
/*     */   }
/*     */   
/*     */   public long getTime() {
/* 131 */     return this.time;
/*     */   }
/*     */   
/*     */   public boolean update() {
/* 135 */     double sp = Math.sqrt((this.motionX * this.motionX + this.motionZ * this.motionZ));
/* 136 */     this.px = this.x;
/* 137 */     this.py = this.y;
/* 138 */     this.pz = this.z;
/*     */     
/* 140 */     this.x += this.motionX;
/* 141 */     this.y += this.motionY;
/* 142 */     this.z += this.motionZ;
/*     */     
/* 144 */     if (posBlock(this.x, (this.y - ((Float)HitParticles.this.starsScale.getValue()).floatValue() / 10.0F), this.z)) {
/* 145 */       this.motionY = -this.motionY / 1.1F;
/* 146 */       this.motionX /= 1.1F;
/* 147 */       this.motionZ /= 1.1F;
/*     */     }
/* 149 */     else if (posBlock(this.x - sp, this.y, this.z - sp) || 
/* 150 */       posBlock(this.x + sp, this.y, this.z + sp) || 
/* 151 */       posBlock(this.x + sp, this.y, this.z - sp) || 
/* 152 */       posBlock(this.x - sp, this.y, this.z + sp) || 
/* 153 */       posBlock(this.x + sp, this.y, this.z) || 
/* 154 */       posBlock(this.x - sp, this.y, this.z) || 
/* 155 */       posBlock(this.x, this.y, this.z + sp) || 
/* 156 */       posBlock(this.x, this.y, this.z - sp)) {
/*     */       
/* 158 */       this.motionX = -this.motionX;
/* 159 */       this.motionZ = -this.motionZ;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     if (HitParticles.this.physics.getValue() == HitParticles.Physics.Fall) {
/* 164 */       this.motionY -= 0.035F;
/*     */     }
/* 166 */     this.motionX /= 1.005F;
/* 167 */     this.motionZ /= 1.005F;
/* 168 */     this.motionY /= 1.005F;
/*     */     
/* 170 */     return (System.currentTimeMillis() - getTime() > (((Integer)HitParticles.this.lifeTime.getValue()).intValue() * 1000));
/*     */   }
/*     */   
/*     */   public void render(class_4587 matrixStack) {
/* 174 */     float size = ((Float)HitParticles.this.starsScale.getValue()).floatValue();
/* 175 */     float scale = HitParticles.this.mode.is(HitParticles.Mode.Text) ? (0.025F * size) : 0.07F;
/*     */     
/* 177 */     double posX = Render2DEngine.interpolate(this.px, this.x, Render3DEngine.getTickDelta()) - (Module.mc.method_1561()).field_4686.method_19326().method_10216();
/* 178 */     double posY = Render2DEngine.interpolate(this.py, this.y, Render3DEngine.getTickDelta()) + 0.1D - (Module.mc.method_1561()).field_4686.method_19326().method_10214();
/* 179 */     double posZ = Render2DEngine.interpolate(this.pz, this.z, Render3DEngine.getTickDelta()) - (Module.mc.method_1561()).field_4686.method_19326().method_10215();
/*     */     
/* 181 */     matrixStack.method_22903();
/* 182 */     matrixStack.method_22904(posX, posY, posZ);
/*     */     
/* 184 */     matrixStack.method_22905(scale, scale, scale);
/*     */     
/* 186 */     matrixStack.method_46416(size / 2.0F, size / 2.0F, size / 2.0F);
/* 187 */     matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-Module.mc.field_1773.method_19418().method_19330()));
/* 188 */     matrixStack.method_22907(class_7833.field_40714.rotationDegrees(Module.mc.field_1773.method_19418().method_19329()));
/*     */     
/* 190 */     if (HitParticles.this.mode.is(HitParticles.Mode.Text)) {
/* 191 */       matrixStack.method_22907(class_7833.field_40718.rotationDegrees(180.0F));
/*     */     } else {
/* 193 */       matrixStack.method_22907(class_7833.field_40718.rotationDegrees(this.rotationAngle += AnimationUtility.deltaTime() * this.rotationSpeed));
/*     */     } 
/* 195 */     matrixStack.method_46416(-size / 2.0F, -size / 2.0F, -size / 2.0F);
/*     */     
/* 197 */     switch (((HitParticles.Mode)HitParticles.this.mode.getValue()).ordinal()) {
/*     */       case 0:
/* 199 */         Render2DEngine.drawOrbiz(matrixStack, 0.0F, 0.3D, this.color);
/* 200 */         Render2DEngine.drawOrbiz(matrixStack, -0.1F, 0.5D, this.color);
/* 201 */         Render2DEngine.drawOrbiz(matrixStack, -0.2F, 0.7D, this.color); break;
/*     */       case 1:
/* 203 */         Render2DEngine.drawStar(matrixStack, this.color, size); break;
/* 204 */       case 2: Render2DEngine.drawHeart(matrixStack, this.color, size); break;
/* 205 */       case 3: Render2DEngine.drawBloom(matrixStack, this.color, size); break;
/*     */       case 4:
/* 207 */         FontRenderers.sf_medium.drawCenteredString(matrixStack, "" + MathUtility.round2(this.health) + " ", 0.0D, 0.0D, ((this.health > 0.0F) ? (ColorSetting)HitParticles.this.colorH.getValue() : (ColorSetting)HitParticles.this.colorD.getValue()).getColorObject());
/*     */         break;
/*     */     } 
/* 210 */     matrixStack.method_22905(0.8F, 0.8F, 0.8F);
/* 211 */     matrixStack.method_22909();
/*     */   }
/*     */   
/*     */   private boolean posBlock(double x, double y, double z) {
/* 215 */     class_2248 b = Module.mc.field_1687.method_8320(class_2338.method_49637(x, y, z)).method_26204();
/* 216 */     return (!(b instanceof net.minecraft.class_2189) && b != class_2246.field_10382 && b != class_2246.field_10164);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\HitParticles$Particle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */