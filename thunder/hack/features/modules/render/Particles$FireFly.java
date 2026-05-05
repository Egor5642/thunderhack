/*     */ package thunder.hack.features.modules.render;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_4184;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7833;
/*     */ import org.joml.Matrix4f;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.HudEditor;
/*     */ import thunder.hack.setting.impl.ColorSetting;
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
/*     */ public class FireFly
/*     */   extends Particles.ParticleBase
/*     */ {
/* 110 */   private final List<Trails.Trail> trails = new ArrayList<>();
/*     */   
/*     */   public FireFly(float posX, float posY, float posZ, float motionX, float motionY, float motionZ) {
/* 113 */     super(posX, posY, posZ, motionX, motionY, motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tick() {
/* 119 */     if (Module.mc.field_1724.method_5649(this.posX, this.posY, this.posZ) > 100.0D) { this.age -= 4; }
/* 120 */     else if (!Module.mc.field_1687.method_8320(new class_2338((int)this.posX, (int)this.posY, (int)this.posZ)).method_26215()) { this.age -= 8; }
/* 121 */     else { this.age--; }
/*     */     
/* 123 */     if (this.age < 0) {
/* 124 */       return true;
/*     */     }
/* 126 */     this.trails.removeIf(Trails.Trail::update);
/*     */     
/* 128 */     this.prevposX = this.posX;
/* 129 */     this.prevposY = this.posY;
/* 130 */     this.prevposZ = this.posZ;
/*     */     
/* 132 */     this.posX += this.motionX;
/* 133 */     this.posY += this.motionY;
/* 134 */     this.posZ += this.motionZ;
/*     */     
/* 136 */     this.trails.add(new Trails.Trail(new class_243(this.prevposX, this.prevposY, this.prevposZ), new class_243(this.posX, this.posY, this.posZ), (Particles.this.lmode.getValue() == Particles.ColorMode.Sync) ? HudEditor.getColor(this.age * 10) : ((ColorSetting)Particles.this.color.getValue()).getColorObject()));
/*     */     
/* 138 */     this.motionX *= 0.99F;
/* 139 */     this.motionY *= 0.99F;
/* 140 */     this.motionZ *= 0.99F;
/*     */     
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(class_287 bufferBuilder) {
/* 147 */     RenderSystem.setShaderTexture(0, TextureStorage.firefly);
/* 148 */     if (!this.trails.isEmpty()) {
/* 149 */       class_4184 camera = Module.mc.field_1773.method_19418();
/* 150 */       for (Trails.Trail ctx : this.trails) {
/* 151 */         class_243 pos = ctx.interpolate(1.0F);
/* 152 */         class_4587 matrices = new class_4587();
/* 153 */         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 154 */         matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
/* 155 */         matrices.method_22904(pos.field_1352, pos.field_1351, pos.field_1350);
/* 156 */         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
/* 157 */         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
/* 158 */         Matrix4f matrix = matrices.method_23760().method_23761();
/*     */         
/* 160 */         bufferBuilder.method_22918(matrix, 0.0F, -((Float)Particles.this.ffsize.getValue()).floatValue(), 0.0F).method_22913(0.0F, 1.0F).method_39415(Render2DEngine.injectAlpha(ctx.color(), (int)((255.0F * this.age / this.maxAge) * ctx.animation(Render3DEngine.getTickDelta()))).getRGB());
/* 161 */         bufferBuilder.method_22918(matrix, -((Float)Particles.this.ffsize.getValue()).floatValue(), -((Float)Particles.this.ffsize.getValue()).floatValue(), 0.0F).method_22913(1.0F, 1.0F).method_39415(Render2DEngine.injectAlpha(ctx.color(), (int)((255.0F * this.age / this.maxAge) * ctx.animation(Render3DEngine.getTickDelta()))).getRGB());
/* 162 */         bufferBuilder.method_22918(matrix, -((Float)Particles.this.ffsize.getValue()).floatValue(), 0.0F, 0.0F).method_22913(1.0F, 0.0F).method_39415(Render2DEngine.injectAlpha(ctx.color(), (int)((255.0F * this.age / this.maxAge) * ctx.animation(Render3DEngine.getTickDelta()))).getRGB());
/* 163 */         bufferBuilder.method_22918(matrix, 0.0F, 0.0F, 0.0F).method_22913(0.0F, 0.0F).method_39415(Render2DEngine.injectAlpha(ctx.color(), (int)((255.0F * this.age / this.maxAge) * ctx.animation(Render3DEngine.getTickDelta()))).getRGB());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\Particles$FireFly.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */