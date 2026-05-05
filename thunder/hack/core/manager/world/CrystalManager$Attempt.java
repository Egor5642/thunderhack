/*     */ package thunder.hack.core.manager.world;
/*     */ 
/*     */ import net.minecraft.class_243;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Attempt
/*     */ {
/*     */   long time;
/*     */   int attempts;
/*     */   float distance;
/*     */   public class_243 pos;
/*     */   
/*     */   Attempt(CrystalManager this$0, long time, int attempts, class_243 pos) {
/*  93 */     this.time = time;
/*  94 */     this.pos = pos;
/*  95 */     this.attempts = attempts;
/*  96 */     this.distance = (float)IManager.mc.field_1724.method_5707(pos);
/*     */   }
/*     */   
/*     */   public class_243 getPos() {
/* 100 */     return this.pos;
/*     */   }
/*     */   
/*     */   public long getTime() {
/* 104 */     return this.time;
/*     */   }
/*     */   
/*     */   public float getDistance() {
/* 108 */     return this.distance;
/*     */   }
/*     */   
/*     */   public boolean shouldRemove() {
/* 112 */     return (Math.abs(this.distance - IManager.mc.field_1724.method_5707(this.pos)) >= 1.0D);
/*     */   }
/*     */   
/*     */   public void addAttempt() {
/* 116 */     this.attempts++;
/*     */   }
/*     */   
/*     */   public boolean canSetPosBlocked() {
/* 120 */     return (this.attempts >= Math.max(((Integer)ModuleManager.autoCrystal.attempts.getValue()).intValue(), Managers.SERVER.getPing() / 25.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\world\CrystalManager$Attempt.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */