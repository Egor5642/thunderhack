/*     */ package thunder.hack.features.modules.render;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.class_243;
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
/*     */ import thunder.hack.utility.render.Render3DEngine;
/*     */ import thunder.hack.utility.render.TextureStorage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleBase
/*     */ {
/*     */   protected float prevposX;
/*     */   protected float prevposY;
/*     */   protected float prevposZ;
/*     */   protected float posX;
/*     */   protected float posY;
/*     */   protected float posZ;
/*     */   protected float motionX;
/*     */   protected float motionY;
/*     */   protected float motionZ;
/*     */   protected int age;
/*     */   protected int maxAge;
/*     */   
/*     */   public ParticleBase(float posX, float posY, float posZ, float motionX, float motionY, float motionZ) {
/* 175 */     this.posX = posX;
/* 176 */     this.posY = posY;
/* 177 */     this.posZ = posZ;
/* 178 */     this.prevposX = posX;
/* 179 */     this.prevposY = posY;
/* 180 */     this.prevposZ = posZ;
/* 181 */     this.motionX = motionX;
/* 182 */     this.motionY = motionY;
/* 183 */     this.motionZ = motionZ;
/* 184 */     this.age = (int)MathUtility.random(100.0F, 300.0F);
/* 185 */     this.maxAge = this.age;
/*     */   }
/*     */   
/*     */   public boolean tick() {
/* 189 */     if (Module.mc.field_1724.method_5649(this.posX, this.posY, this.posZ) > 4096.0D) { this.age -= 8; }
/* 190 */     else { this.age--; }
/*     */     
/* 192 */     if (this.age < 0) {
/* 193 */       return true;
/*     */     }
/* 195 */     this.prevposX = this.posX;
/* 196 */     this.prevposY = this.posY;
/* 197 */     this.prevposZ = this.posZ;
/*     */     
/* 199 */     this.posX += this.motionX;
/* 200 */     this.posY += this.motionY;
/* 201 */     this.posZ += this.motionZ;
/*     */     
/* 203 */     this.motionX *= 0.9F;
/* 204 */     if (Particles.this.physics.getValue() == Particles.Physics.Fly)
/* 205 */       this.motionY *= 0.9F; 
/* 206 */     this.motionZ *= 0.9F;
/*     */     
/* 208 */     this.motionY -= 0.001F;
/*     */     
/* 210 */     return false;
/*     */   }
/*     */   
/*     */   public void render(class_287 bufferBuilder) {
/* 214 */     switch (((Particles.Mode)Particles.this.mode.getValue()).ordinal()) { case 5:
/* 215 */         RenderSystem.setShaderTexture(0, TextureStorage.firefly); break;
/* 216 */       case 1: RenderSystem.setShaderTexture(0, TextureStorage.snowflake); break;
/* 217 */       case 4: RenderSystem.setShaderTexture(0, TextureStorage.dollar); break;
/* 218 */       case 3: RenderSystem.setShaderTexture(0, TextureStorage.heart); break;
/* 219 */       case 2: RenderSystem.setShaderTexture(0, TextureStorage.star);
/*     */         break; }
/*     */     
/* 222 */     class_4184 camera = Module.mc.field_1773.method_19418();
/* 223 */     Color color1 = (Particles.this.lmode.getValue() == Particles.ColorMode.Sync) ? HudEditor.getColor(this.age * 2) : ((ColorSetting)Particles.this.color.getValue()).getColorObject();
/* 224 */     class_243 pos = Render3DEngine.interpolatePos(this.prevposX, this.prevposY, this.prevposZ, this.posX, this.posY, this.posZ);
/*     */     
/* 226 */     class_4587 matrices = new class_4587();
/* 227 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 228 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 229 */     matrices.method_22904(pos.field_1352, pos.field_1351, pos.field_1350);
/* 230 */     matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 231 */     matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/*     */     
/* 233 */     Matrix4f matrix1 = matrices.method_23760().method_23761();
/*     */     
/* 235 */     bufferBuilder.method_22918(matrix1, 0.0F, -((Float)Particles.this.size.getValue()).floatValue(), 0.0F).method_22913(0.0F, 1.0F).method_39415(Render2DEngine.injectAlpha(color1, (int)(255.0F * this.age / this.maxAge)).getRGB());
/* 236 */     bufferBuilder.method_22918(matrix1, -((Float)Particles.this.size.getValue()).floatValue(), -((Float)Particles.this.size.getValue()).floatValue(), 0.0F).method_22913(1.0F, 1.0F).method_39415(Render2DEngine.injectAlpha(color1, (int)(255.0F * this.age / this.maxAge)).getRGB());
/* 237 */     bufferBuilder.method_22918(matrix1, -((Float)Particles.this.size.getValue()).floatValue(), 0.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(Render2DEngine.injectAlpha(color1, (int)(255.0F * this.age / this.maxAge)).getRGB());
/* 238 */     bufferBuilder.method_22918(matrix1, 0.0F, 0.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(Render2DEngine.injectAlpha(color1, (int)(255.0F * this.age / this.maxAge)).getRGB());
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Particles$ParticleBase.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */