/*     */ package thunder.hack.core.manager.client;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
/*     */ import thunder.hack.api.IAddon;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.IManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.hud.HudElement;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public class AddonManager implements IManager {
/*  17 */   private int totalAddons = 0;
/*  18 */   private final List<IAddon> addons = new ArrayList<>();
/*     */   
/*     */   public void incrementAddonCount() {
/*  21 */     this.totalAddons++;
/*     */   }
/*     */   
/*     */   public int getTotalAddons() {
/*  25 */     return this.totalAddons;
/*     */   }
/*     */   
/*     */   public void addAddon(IAddon addon) {
/*  29 */     this.addons.add(addon);
/*     */   }
/*     */   
/*     */   public List<IAddon> getAddons() {
/*  33 */     return this.addons;
/*     */   }
/*     */   
/*     */   public void initAddons() {
/*  37 */     LogUtils.getLogger().info("Starting addon initialization.");
/*     */     
/*  39 */     for (Iterator<EntrypointContainer<IAddon>> iterator = FabricLoader.getInstance().getEntrypointContainers("thunderhack", IAddon.class).iterator(); iterator.hasNext(); ) { EntrypointContainer<IAddon> entrypoint = iterator.next();
/*  40 */       IAddon addon = (IAddon)entrypoint.getEntrypoint();
/*     */       
/*     */       try {
/*  43 */         LogUtils.getLogger().info("Initializing addon: " + addon.getClass().getName());
/*  44 */         LogUtils.getLogger().debug("Addon class loader: " + String.valueOf(addon.getClass().getClassLoader()));
/*  45 */         addon.onInitialize();
/*  46 */         LogUtils.getLogger().info("Addon initialized successfully: " + addon.getClass().getName());
/*     */         
/*  48 */         incrementAddonCount();
/*  49 */         LogUtils.getLogger().debug("Addon count incremented.");
/*     */         
/*  51 */         addAddon(addon);
/*  52 */         LogUtils.getLogger().debug("Addon added to manager.");
/*  53 */         ThunderHack.EVENT_BUS.registerLambdaFactory(addon.getPackage(), (lookupInMethod, klass) -> (MethodHandles.Lookup)lookupInMethod.invoke(null, new Object[] { klass, MethodHandles.lookup() }));
/*     */ 
/*     */         
/*  56 */         if (addon.getModules() != null) {
/*  57 */           addon.getModules().stream().filter(Objects::nonNull).forEach(module -> {
/*     */                 try {
/*     */                   LogUtils.getLogger().info("Registering module: " + module.getClass().getName());
/*     */                   LogUtils.getLogger().debug("Module class loader: " + String.valueOf(module.getClass().getClassLoader()));
/*     */                   Managers.MODULE.registerModule(module);
/*     */                   LogUtils.getLogger().info("Module registered successfully: " + module.getClass().getName());
/*  63 */                 } catch (Exception e) {
/*     */                   LogUtils.getLogger().error("Error registering module: " + module.getClass().getName(), e);
/*     */                 } 
/*     */               });
/*     */         }
/*     */         
/*  69 */         if (addon.getCommands() != null) {
/*  70 */           addon.getCommands().stream().filter(Objects::nonNull).forEach(command -> {
/*     */                 try {
/*     */                   LogUtils.getLogger().info("Registering command: " + command.getClass().getName());
/*     */                   LogUtils.getLogger().debug("Command class loader: " + String.valueOf(command.getClass().getClassLoader()));
/*     */                   Managers.COMMAND.registerCommand(command);
/*     */                   LogUtils.getLogger().info("Command registered successfully: " + command.getClass().getName());
/*  76 */                 } catch (Exception e) {
/*     */                   LogUtils.getLogger().error("Error registering command: " + command.getClass().getName(), e);
/*     */                 } 
/*     */               });
/*     */         }
/*     */         
/*  82 */         if (addon.getHudElements() != null) {
/*  83 */           addon.getHudElements().stream().filter(Objects::nonNull).forEach(hudElement -> {
/*     */                 try {
/*     */                   LogUtils.getLogger().info("Registering HUD element: " + hudElement.getClass().getName());
/*     */                   LogUtils.getLogger().debug("HUD element class loader: " + String.valueOf(hudElement.getClass().getClassLoader()));
/*     */                   Managers.MODULE.registerHudElement(hudElement);
/*     */                   LogUtils.getLogger().info("HUD element registered successfully: " + hudElement.getClass().getName());
/*  89 */                 } catch (Exception e) {
/*     */                   LogUtils.getLogger().error("Error registering HUD element: " + hudElement.getClass().getName(), e);
/*     */                 } 
/*     */               });
/*     */         }
/*  94 */       } catch (Exception e) {
/*  95 */         LogUtils.getLogger().error("Error initializing addon: " + addon.getClass().getName(), e);
/*     */       }  }
/*     */ 
/*     */     
/*  99 */     LogUtils.getLogger().info("Addon initialization complete.");
/*     */   }
/*     */   
/*     */   public void shutDown() {
/* 103 */     for (IAddon addon : getAddons()) {
/*     */       try {
/* 105 */         addon.onShutdown();
/* 106 */       } catch (Exception e) {
/* 107 */         LogUtils.getLogger().error("Error running addon onShutdown method: " + addon.getClass().getName(), e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\AddonManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */