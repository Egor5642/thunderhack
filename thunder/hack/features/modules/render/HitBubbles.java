/*    */ package thunder.hack.features.modules.render;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.ArrayList;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_7833;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.events.impl.EventAttack;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.injection.accesors.IClientPlayerEntity;
/*    */ import thunder.hack.setting.Setting;
/*    */ import thunder.hack.utility.Timer;
/*    */ import thunder.hack.utility.render.Render2DEngine;
/*    */ 
/*    */ public class HitBubbles extends Module {
/*    */   public final Setting<Integer> lifeTime;
/*    */   private final ArrayList<HitBubble> bubbles;
/*    */   
/*    */   public HitBubbles() {
/* 23 */     super("HitBubbles", Module.Category.RENDER);
/*    */ 
/*    */     
/* 26 */     this.lifeTime = new Setting("LifeTime", Integer.valueOf(30), Integer.valueOf(1), Integer.valueOf(150));
/*    */     
/* 28 */     this.bubbles = new ArrayList<>();
/*    */   }
/*    */   @EventHandler
/*    */   public void onHit(EventAttack e) {
/* 32 */     class_243 point = Managers.PLAYER.getRtxPoint(((IClientPlayerEntity)mc.field_1724).getLastYaw(), ((IClientPlayerEntity)mc.field_1724).getLastPitch(), ((Float)ModuleManager.aura.attackRange.getValue()).floatValue());
/* 33 */     if (point != null && !e.isPre())
/* 34 */       this.bubbles.add(new HitBubble((float)point.field_1352, (float)point.field_1351, (float)point.field_1350, -((IClientPlayerEntity)mc.field_1724).getLastYaw(), ((IClientPlayerEntity)mc.field_1724).getLastPitch(), new Timer())); 
/*    */   }
/*    */   
/*    */   public void onRender3D(class_4587 matrixStack) {
/* 38 */     RenderSystem.disableDepthTest();
/* 39 */     ArrayList<HitBubble> bubblesCopy = Lists.newArrayList(this.bubbles);
/* 40 */     bubblesCopy.forEach(b -> {
/*    */           matrixStack.method_22903();
/*    */           matrixStack.method_22904(b.x - (mc.method_1561()).field_4686.method_19326().method_10216(), b.y - (mc.method_1561()).field_4686.method_19326().method_10214(), b.z - (mc.method_1561()).field_4686.method_19326().method_10215());
/*    */           matrixStack.method_22907(class_7833.field_40716.rotationDegrees(b.yaw));
/*    */           matrixStack.method_22907(class_7833.field_40714.rotationDegrees(b.pitch));
/*    */           Render2DEngine.drawBubble(matrixStack, (float)-b.life.getPassedTimeMs() / 4.0F, (float)b.life.getPassedTimeMs() / 1500.0F);
/*    */           matrixStack.method_22909();
/*    */         });
/* 48 */     RenderSystem.enableDepthTest();
/* 49 */     this.bubbles.removeIf(b -> b.life.passedMs((((Integer)this.lifeTime.getValue()).intValue() * 50)));
/*    */   }
/*    */   public static final class HitBubble extends Record { private final float x; private final float y; private final float z; private final float yaw; private final float pitch; private final Timer life;
/* 52 */     public HitBubble(float x, float y, float z, float yaw, float pitch, Timer life) { this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch; this.life = life; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #52	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 52 */       //   0	7	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble; } public float x() { return this.x; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #52	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #52	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble;
/* 52 */       //   0	8	1	o	Ljava/lang/Object; } public float y() { return this.y; } public float z() { return this.z; } public float yaw() { return this.yaw; } public float pitch() { return this.pitch; } public Timer life() { return this.life; }
/*    */      }
/*    */ 
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\HitBubbles.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */