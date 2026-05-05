/*     */ package thunder.hack.core.manager.client;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_742;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.events.impl.EventPostTick;
/*     */ import thunder.hack.events.impl.EventSync;
/*     */ import thunder.hack.events.impl.EventTick;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public class AsyncManager
/*     */   implements IManager
/*     */ {
/*  23 */   private ClientService clientService = new ClientService();
/*  24 */   public static ExecutorService executor = Executors.newCachedThreadPool();
/*  25 */   private volatile Iterable<class_1297> threadSafeEntityList = Collections.emptyList();
/*  26 */   private volatile List<class_742> threadSafePlayersList = Collections.emptyList();
/*  27 */   public final AtomicBoolean ticking = new AtomicBoolean(false);
/*     */   
/*     */   public static void sleep(int delay) {
/*     */     try {
/*  31 */       Thread.sleep(delay);
/*  32 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = -200)
/*     */   public void onPostTick(EventPostTick e) {
/*  38 */     if (mc.field_1687 == null)
/*     */       return; 
/*  40 */     this.threadSafeEntityList = Lists.newArrayList(mc.field_1687.method_18112());
/*  41 */     this.threadSafePlayersList = Lists.newArrayList(mc.field_1687.method_18456());
/*  42 */     this.ticking.set(false);
/*     */   }
/*     */   
/*     */   public Iterable<class_1297> getAsyncEntities() {
/*  46 */     return this.threadSafeEntityList;
/*     */   }
/*     */   
/*     */   public List<class_742> getAsyncPlayers() {
/*  50 */     return this.threadSafePlayersList;
/*     */   }
/*     */   
/*     */   public AsyncManager() {
/*  54 */     this.clientService.setName("ThunderHack-AsyncProcessor");
/*  55 */     this.clientService.setDaemon(true);
/*  56 */     this.clientService.start();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onSync(EventSync e) {
/*  61 */     if (!this.clientService.isAlive()) {
/*  62 */       this.clientService = new ClientService();
/*  63 */       this.clientService.setName("ThunderHack-AsyncProcessor");
/*  64 */       this.clientService.setDaemon(true);
/*  65 */       this.clientService.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class ClientService
/*     */     extends Thread {
/*     */     public void run() {
/*  72 */       while (!Thread.currentThread().isInterrupted()) {
/*     */         try {
/*  74 */           Managers.TELEMETRY.onUpdate();
/*  75 */           if (!Module.fullNullCheck()) {
/*  76 */             Managers.MODULE.modules.forEach(m -> {
/*     */                   if (m.isEnabled())
/*     */                     m.onThread(); 
/*  79 */                 }); Thread.sleep(100L); continue;
/*  80 */           }  Thread.yield();
/*  81 */         } catch (Exception exception) {
/*  82 */           exception.printStackTrace();
/*  83 */           Command.sendMessage(exception.getMessage());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler(priority = 200)
/*     */   public void onTick(EventTick e) {
/*  91 */     this.ticking.set(true);
/*     */   }
/*     */   
/*     */   public void run(Runnable runnable, long delay) {
/*  95 */     executor.execute(() -> {
/*     */           try {
/*     */             Thread.sleep(delay);
/*  98 */           } catch (InterruptedException e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */           runnable.run();
/*     */         });
/*     */   }
/*     */   
/*     */   public void run(Runnable r) {
/* 106 */     executor.execute(r);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\AsyncManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */