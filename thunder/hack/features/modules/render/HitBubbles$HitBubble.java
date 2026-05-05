/*    */ package thunder.hack.features.modules.render;
/*    */ 
/*    */ import thunder.hack.utility.Timer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class HitBubble
/*    */   extends Record
/*    */ {
/*    */   private final float x;
/*    */   private final float y;
/*    */   private final float z;
/*    */   private final float yaw;
/*    */   private final float pitch;
/*    */   private final Timer life;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #52	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #52	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lthunder/hack/features/modules/render/HitBubbles$HitBubble;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #52	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lthunder/hack/features/modules/render/HitBubbles$HitBubble;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public HitBubble(float x, float y, float z, float yaw, float pitch, Timer life) {
/* 52 */     this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch; this.life = life; } public float x() { return this.x; } public float y() { return this.y; } public float z() { return this.z; } public float yaw() { return this.yaw; } public float pitch() { return this.pitch; } public Timer life() { return this.life; }
/*    */ 
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\render\HitBubbles$HitBubble.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */