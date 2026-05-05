/*     */ package thunder.hack.core.manager.world;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.class_1511;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_243;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ 
/*     */ public class CrystalManager
/*     */   implements IManager {
/*  14 */   private final Map<Integer, Long> deadCrystals = new ConcurrentHashMap<>();
/*  15 */   private final Map<Integer, Attempt> attackedCrystals = new ConcurrentHashMap<>();
/*  16 */   private final Map<class_2338, Attempt> awaitingPositions = new ConcurrentHashMap<>();
/*     */   
/*     */   public void onAttack(class_1511 crystal) {
/*  19 */     setDead(Integer.valueOf(crystal.method_5628()), System.currentTimeMillis());
/*  20 */     addAttack(crystal);
/*     */   }
/*     */   
/*     */   public void reset() {
/*  24 */     this.deadCrystals.clear();
/*  25 */     this.attackedCrystals.clear();
/*  26 */     this.awaitingPositions.clear();
/*     */   }
/*     */   
/*     */   public void update() {
/*  30 */     long time = System.currentTimeMillis();
/*  31 */     this.deadCrystals.entrySet().removeIf(entry -> (time - ((Long)entry.getValue()).longValue() > Managers.SERVER.getPing() * 2L));
/*  32 */     this.attackedCrystals.entrySet().removeIf(entry -> ((Attempt)entry.getValue()).shouldRemove());
/*  33 */     this.awaitingPositions.entrySet().removeIf(entry -> ((Attempt)entry.getValue()).shouldRemove());
/*     */   }
/*     */   
/*     */   public boolean isDead(Integer id) {
/*  37 */     return this.deadCrystals.containsKey(id);
/*     */   }
/*     */   
/*     */   public void setDead(Integer id, long deathTime) {
/*  41 */     this.deadCrystals.putIfAbsent(id, Long.valueOf(deathTime));
/*     */   }
/*     */   
/*     */   public boolean isBlocked(Integer id) {
/*  45 */     return (this.attackedCrystals.containsKey(id) && ((Attempt)this.attackedCrystals.get(id)).canSetPosBlocked());
/*     */   }
/*     */   
/*     */   public void addAttack(class_1511 entity) {
/*  49 */     this.attackedCrystals.compute(Integer.valueOf(entity.method_5628()), (pos, attempt) -> {
/*     */           if (attempt == null) {
/*     */             return new Attempt(this, System.currentTimeMillis(), 1, entity.method_19538());
/*     */           }
/*     */           if (((Boolean)ModuleManager.autoCrystal.breakFailsafe.getValue()).booleanValue()) {
/*     */             attempt.addAttempt();
/*     */           }
/*     */           return attempt;
/*     */         });
/*     */   }
/*     */   
/*     */   public Map<class_2338, Attempt> getAwaitingPositions() {
/*  61 */     return this.awaitingPositions;
/*     */   }
/*     */   
/*     */   public void confirmSpawn(class_2338 bp) {
/*  65 */     this.awaitingPositions.remove(bp);
/*     */   }
/*     */   
/*     */   public void addAwaitingPos(class_2338 blockPos) {
/*  69 */     boolean blocked = ModuleManager.autoCrystal.isPositionBlockedByCrystal(blockPos.method_10084());
/*     */     
/*  71 */     this.awaitingPositions.compute(blockPos, (pos, attempt) -> {
/*     */           if (attempt == null) {
/*     */             return new Attempt(this, System.currentTimeMillis(), 1, blockPos.method_46558());
/*     */           }
/*     */           if (!blocked && ((Boolean)ModuleManager.autoCrystal.placeFailsafe.getValue()).booleanValue()) {
/*     */             attempt.addAttempt();
/*     */           }
/*     */           return attempt;
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean isPositionBlocked(class_2338 bp) {
/*  83 */     return (this.awaitingPositions.containsKey(bp) && ((Attempt)this.awaitingPositions.get(bp)).canSetPosBlocked());
/*     */   }
/*     */   
/*     */   public class Attempt {
/*     */     long time;
/*     */     int attempts;
/*     */     float distance;
/*     */     public class_243 pos;
/*     */     
/*     */     Attempt(CrystalManager this$0, long time, int attempts, class_243 pos) {
/*  93 */       this.time = time;
/*  94 */       this.pos = pos;
/*  95 */       this.attempts = attempts;
/*  96 */       this.distance = (float)IManager.mc.field_1724.method_5707(pos);
/*     */     }
/*     */     
/*     */     public class_243 getPos() {
/* 100 */       return this.pos;
/*     */     }
/*     */     
/*     */     public long getTime() {
/* 104 */       return this.time;
/*     */     }
/*     */     
/*     */     public float getDistance() {
/* 108 */       return this.distance;
/*     */     }
/*     */     
/*     */     public boolean shouldRemove() {
/* 112 */       return (Math.abs(this.distance - IManager.mc.field_1724.method_5707(this.pos)) >= 1.0D);
/*     */     }
/*     */     
/*     */     public void addAttempt() {
/* 116 */       this.attempts++;
/*     */     }
/*     */     
/*     */     public boolean canSetPosBlocked() {
/* 120 */       return (this.attempts >= Math.max(((Integer)ModuleManager.autoCrystal.attempts.getValue()).intValue(), Managers.SERVER.getPing() / 25.0F));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\world\CrystalManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */