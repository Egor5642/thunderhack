/*     */ package thunder.hack.features.modules.player;
/*     */ 
/*     */ import net.minecraft.class_1268;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2350;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2846;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.core.manager.player.PlayerManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.Timer;
/*     */ import thunder.hack.utility.player.InteractionUtility;
/*     */ import thunder.hack.utility.player.InventoryUtility;
/*     */ import thunder.hack.utility.player.PlayerUtility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MineAction
/*     */ {
/*     */   @NotNull
/*     */   private final class_2338 pos;
/*     */   private float progress;
/*     */   private float prevProgress;
/*     */   private int mineBreaks;
/* 363 */   private final Timer attackTimer = new Timer();
/*     */   
/*     */   public MineAction(class_2338 pos, class_2350 direction) {
/* 366 */     this.pos = pos;
/* 367 */     this.progress = 0.0F;
/* 368 */     this.mineBreaks = 0;
/* 369 */     start(direction);
/*     */   }
/*     */   
/*     */   public void start(class_2350 direction) {
/* 373 */     class_2350 startDirection = (direction == null) ? Module.mc.field_1724.method_5735() : direction;
/*     */     
/* 375 */     if (startDirection != null)
/* 376 */       if (((Boolean)SpeedMine.this.doubleMine.getValue()).booleanValue()) {
/* 377 */         SpeedMine.access$000(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, startDirection));
/* 378 */         SpeedMine.access$100(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12968, this.pos, startDirection));
/* 379 */         SpeedMine.access$200(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, startDirection));
/*     */       } else {
/* 381 */         SpeedMine.access$300(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12968, this.pos, startDirection));
/* 382 */         SpeedMine.access$400(SpeedMine.this, (class_2596)new class_2846((SpeedMine.this.startMode.getValue() == SpeedMine.StartMode.StartAbort) ? class_2846.class_2847.field_12971 : class_2846.class_2847.field_12973, this.pos, startDirection));
/*     */       }  
/*     */   }
/*     */   
/*     */   public boolean update() {
/* 387 */     class_2350 dir = InteractionUtility.getStrictDirections(this.pos).stream().findFirst().orElse(Module.mc.field_1724.method_5735());
/*     */     
/* 389 */     if (this.mineBreaks >= ((Integer)SpeedMine.this.breakAttempts.getValue()).intValue() && SpeedMine.this.mode.not(SpeedMine.Mode.GrimInstant)) {
/* 390 */       return true;
/*     */     }
/* 392 */     if (PlayerUtility.squaredDistanceFromEyes(this.pos.method_46558()) > SpeedMine.this.range.getPow2Value()) {
/* 393 */       cancel();
/* 394 */       return true;
/*     */     } 
/*     */     
/* 397 */     if (Module.mc.field_1687.method_22347(this.pos)) {
/* 398 */       this.progress = 0.0F;
/* 399 */       this.prevProgress = -1.0F;
/* 400 */       return false;
/*     */     } 
/*     */     
/* 403 */     if (this.progress == 0.0F && this.prevProgress == -1.0F && SpeedMine.this.mode.is(SpeedMine.Mode.Packet) && this.attackTimer.every(800L)) {
/* 404 */       start(dir);
/* 405 */       Module.mc.field_1724.method_6104(class_1268.field_5808);
/*     */     } 
/*     */     
/* 408 */     int pickSlot = SpeedMine.this.getTool(this.pos);
/* 409 */     int prevSlot = (Module.mc.field_1724.method_31548()).field_7545;
/*     */     
/* 411 */     if (pickSlot == -1) {
/* 412 */       return false;
/*     */     }
/* 414 */     boolean instant = (this.mineBreaks > 0 && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant));
/*     */     
/* 416 */     if (this.progress >= 1.0F || instant) {
/* 417 */       if (((Boolean)SpeedMine.this.placeCrystal.getValue()).booleanValue()) {
/* 418 */         SpeedMine.this.placeCrystal();
/*     */       }
/* 420 */       switchTo(pickSlot, -1);
/*     */       
/* 422 */       if (SpeedMine.this.mode.getValue() == SpeedMine.Mode.GrimInstant || ((Boolean)SpeedMine.this.doubleMine.getValue()).booleanValue()) {
/* 423 */         SpeedMine.access$500(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, dir));
/*     */       } else {
/* 425 */         if (((Boolean)SpeedMine.this.stop.getValue()).booleanValue())
/* 426 */           SpeedMine.access$600(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, dir)); 
/* 427 */         if (((Boolean)SpeedMine.this.abort.getValue()).booleanValue())
/* 428 */           SpeedMine.access$700(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12971, this.pos, dir)); 
/* 429 */         if (((Boolean)SpeedMine.this.start.getValue()).booleanValue())
/* 430 */           SpeedMine.access$800(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12968, this.pos, dir)); 
/* 431 */         if (((Boolean)SpeedMine.this.stop2.getValue()).booleanValue()) {
/* 432 */           SpeedMine.access$900(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12973, this.pos, dir));
/*     */         }
/*     */       } 
/* 435 */       if (((Boolean)SpeedMine.this.clientRemove.getValue()).booleanValue()) {
/* 436 */         Module.mc.field_1761.method_2899(this.pos);
/*     */       }
/* 438 */       int delay = ((Boolean)SpeedMine.this.doubleMine.getValue()).booleanValue() ? 100 : ((Integer)SpeedMine.this.swapDelay.getValue()).intValue();
/*     */       
/* 440 */       if (delay != 0) {
/* 441 */         Managers.ASYNC.run(() -> switchTo(prevSlot, pickSlot), delay);
/*     */       } else {
/* 443 */         switchTo(prevSlot, pickSlot);
/*     */       } 
/* 445 */       this.mineBreaks++;
/*     */       
/* 447 */       this.progress = this.prevProgress = 0.0F;
/*     */       
/* 449 */       if (((Boolean)SpeedMine.this.doubleMine.getValue()).booleanValue() && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant) && SpeedMine.this.actions.size() >= 2)
/* 450 */         return true; 
/*     */     } else {
/* 452 */       this.prevProgress = this.progress;
/* 453 */       this.progress += SpeedMine.this.getBlockStrength(Module.mc.field_1687.method_8320(this.pos), this.pos);
/*     */     } 
/*     */     
/* 456 */     fixMovement();
/*     */     
/* 458 */     return false;
/*     */   }
/*     */   
/*     */   private void switchTo(int slot, int from) {
/* 462 */     if (SpeedMine.this.switchMode.getValue() == SpeedMine.SwitchMode.Alternative || slot >= 9)
/* 463 */     { if (from == -1) {
/* 464 */         Module.clickSlot((slot < 9) ? (slot + 36) : slot, (Module.mc.field_1724.method_31548()).field_7545, class_1713.field_7791);
/*     */       } else {
/* 466 */         Module.clickSlot((from < 9) ? (from + 36) : from, (Module.mc.field_1724.method_31548()).field_7545, class_1713.field_7791);
/* 467 */       }  SpeedMine.this.closeScreen(); }
/* 468 */     else if (SpeedMine.this.switchMode.is(SpeedMine.SwitchMode.Silent)) { InventoryUtility.switchToSilent(slot); }
/* 469 */     else { InventoryUtility.switchTo(slot); }
/*     */   
/*     */   }
/*     */   public void fixMovement() {
/* 473 */     if (((Boolean)SpeedMine.this.rotate.getValue()).booleanValue() && this.progress > 0.95D)
/* 474 */       ModuleManager.rotations.fixRotation = PlayerManager.calcAngle(Module.mc.field_1724.method_33571(), this.pos.method_46558())[0]; 
/*     */   }
/*     */   
/*     */   public class_2338 getPos() {
/* 478 */     return this.pos;
/*     */   }
/*     */   
/*     */   public float getPrevProgress() {
/* 482 */     return this.prevProgress;
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 486 */     return this.progress;
/*     */   }
/*     */   
/*     */   public void onSync() {
/* 490 */     if (((Boolean)SpeedMine.this.rotate.getValue()).booleanValue() && this.progress > 0.95D) {
/* 491 */       float[] angle = PlayerManager.calcAngle(Module.mc.field_1724.method_33571(), this.pos.method_46558().method_1031(0.0D, -0.25D, 0.0D));
/* 492 */       Module.mc.field_1724.method_36456(angle[0]);
/* 493 */       Module.mc.field_1724.method_36457(angle[1]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/* 498 */     if (this.progress == 0.0F) {
/*     */       return;
/*     */     }
/* 501 */     this.prevProgress = this.progress = 0.0F;
/* 502 */     class_2350 dir = InteractionUtility.getStrictDirections(this.pos).stream().findFirst().orElse(Module.mc.field_1724.method_5735());
/* 503 */     SpeedMine.access$1000(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12971, this.pos, dir));
/* 504 */     start(dir);
/*     */   }
/*     */   
/*     */   public void cancel() {
/* 508 */     if (this.progress != 0.0F)
/* 509 */       SpeedMine.access$1100(SpeedMine.this, (class_2596)new class_2846(class_2846.class_2847.field_12971, this.pos, class_2350.field_11033)); 
/*     */   }
/*     */   
/*     */   public boolean instantBreaking() {
/* 513 */     return (this.mineBreaks > 0 && SpeedMine.this.mode.is(SpeedMine.Mode.GrimInstant));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\player\SpeedMine$MineAction.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */