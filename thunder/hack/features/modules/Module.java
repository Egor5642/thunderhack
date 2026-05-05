/*     */ package thunder.hack.features.modules;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_332;
/*     */ import net.minecraft.class_3675;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_7202;
/*     */ import net.minecraft.class_7204;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.core.manager.client.CommandManager;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.gui.notification.Notification;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.Bind;
/*     */ 
/*     */ public abstract class Module {
/*  35 */   private final Setting<Bind> bind = new Setting("Keybind", new Bind(-1, false, false));
/*  36 */   private final Setting<Boolean> drawn = new Setting("Drawn", Boolean.valueOf(true));
/*  37 */   private final Setting<Boolean> enabled = new Setting("Enabled", Boolean.valueOf(false));
/*     */   
/*     */   private final String description;
/*     */   
/*     */   private final Category category;
/*     */   private final String displayName;
/*  43 */   private final List<String> ignoreSoundList = Arrays.asList(new String[] { "ClickGui", "ThunderGui", "HudEditor" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static final class_310 mc = class_310.method_1551();
/*     */   
/*     */   public Module(@NotNull String name, @NotNull Category category) {
/*  52 */     this.displayName = name;
/*  53 */     this.description = "descriptions." + category.getName().toLowerCase() + "." + name.toLowerCase();
/*  54 */     this.category = category;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */   
/*     */   public void onLogin() {}
/*     */ 
/*     */   
/*     */   public void onLogout() {}
/*     */ 
/*     */   
/*     */   public void onUpdate() {}
/*     */ 
/*     */   
/*     */   public void onRender2D(class_332 event) {}
/*     */ 
/*     */   
/*     */   public void onRender3D(class_4587 event) {}
/*     */ 
/*     */   
/*     */   public void onUnload() {}
/*     */ 
/*     */   
/*     */   public boolean isToggleable() {
/*  83 */     return true;
/*     */   }
/*     */   
/*     */   protected void sendPacket(class_2596<?> packet) {
/*  87 */     if (mc.method_1562() == null)
/*     */       return; 
/*  89 */     mc.method_1562().method_52787(packet);
/*     */   }
/*     */   
/*     */   protected void sendPacketSilent(class_2596<?> packet) {
/*  93 */     if (mc.method_1562() == null)
/*  94 */       return;  ThunderHack.core.silentPackets.add(packet);
/*  95 */     mc.method_1562().method_52787(packet);
/*     */   }
/*     */   
/*     */   protected void sendSequencedPacket(class_7204 packetCreator) {
/*  99 */     if (mc.method_1562() == null || mc.field_1687 == null)
/* 100 */       return;  class_7202 pendingUpdateManager = mc.field_1687.method_41925().method_41937(); 
/* 101 */     try { int i = pendingUpdateManager.method_41942();
/* 102 */       mc.method_1562().method_52787(packetCreator.predict(i));
/* 103 */       if (pendingUpdateManager != null) pendingUpdateManager.close();  } catch (Throwable throwable) { if (pendingUpdateManager != null)
/*     */         try { pendingUpdateManager.close(); }
/*     */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */           throw throwable; }
/* 107 */      } public String getDisplayInfo() { return null; }
/*     */ 
/*     */   
/*     */   public boolean isOn() {
/* 111 */     return ((Boolean)this.enabled.getValue()).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isOff() {
/* 115 */     return !((Boolean)this.enabled.getValue()).booleanValue();
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 119 */     this.enabled.setValue(Boolean.valueOf(enabled));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onThread() {}
/*     */   
/*     */   public void enable() {
/* 126 */     if (!(this instanceof thunder.hack.features.modules.client.UnHook)) {
/* 127 */       this.enabled.setValue(Boolean.valueOf(true));
/*     */     }
/* 129 */     if (!fullNullCheck() || this instanceof thunder.hack.features.modules.client.UnHook || this instanceof thunder.hack.features.modules.client.Windows) {
/* 130 */       onEnable();
/*     */     }
/* 132 */     if (isOn()) ThunderHack.EVENT_BUS.subscribe(this); 
/* 133 */     if (fullNullCheck())
/*     */       return; 
/* 135 */     LogUtils.getLogger().info("[ThunderHack] enabled " + getName());
/* 136 */     Managers.MODULE.sortModules();
/*     */     
/* 138 */     if (!this.ignoreSoundList.contains(getDisplayName())) {
/* 139 */       Managers.NOTIFICATION.publicity(getDisplayName(), ClientSettings.isRu() ? "Модуль включен!" : "Was Enabled!", 2, Notification.Type.ENABLED);
/* 140 */       Managers.SOUND.playEnable();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void disable(String reason) {
/* 145 */     sendMessage(reason);
/* 146 */     disable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disable() {
/*     */     try {
/* 154 */       ThunderHack.EVENT_BUS.unsubscribe(this);
/* 155 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 158 */     this.enabled.setValue(Boolean.valueOf(false));
/*     */     
/* 160 */     Managers.MODULE.sortModules();
/*     */     
/* 162 */     if (fullNullCheck())
/*     */       return; 
/* 164 */     onDisable();
/*     */     
/* 166 */     ThunderHack.LOGGER.info("[ThunderHack] disabled {}", getName());
/*     */     
/* 168 */     if (!this.ignoreSoundList.contains(getDisplayName())) {
/* 169 */       Managers.NOTIFICATION.publicity(getDisplayName(), ClientSettings.isRu() ? "Модуль выключен!" : "Was Disabled!", 2, Notification.Type.DISABLED);
/* 170 */       Managers.SOUND.playDisable();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void toggle() {
/* 175 */     if (((Boolean)this.enabled.getValue()).booleanValue()) { disable(); }
/* 176 */     else { enable(); }
/*     */   
/*     */   }
/*     */   public String getDisplayName() {
/* 180 */     return this.displayName;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 184 */     return this.description;
/*     */   }
/*     */   
/*     */   public boolean isDrawn() {
/* 188 */     return ((Boolean)this.drawn.getValue()).booleanValue();
/*     */   }
/*     */   
/*     */   public void setDrawn(boolean d) {
/* 192 */     this.drawn.setValue(Boolean.valueOf(d));
/*     */   }
/*     */   
/*     */   public Category getCategory() {
/* 196 */     return this.category;
/*     */   }
/*     */   
/*     */   public Bind getBind() {
/* 200 */     return (Bind)this.bind.getValue();
/*     */   }
/*     */   
/*     */   public void setBind(int key, boolean mouse, boolean hold) {
/* 204 */     setBind(new Bind(key, mouse, hold));
/*     */   }
/*     */   
/*     */   public void setBind(Bind b) {
/* 208 */     this.bind.setValue(b);
/*     */   }
/*     */   
/*     */   public boolean listening() {
/* 212 */     return isOn();
/*     */   }
/*     */   
/*     */   public String getFullArrayString() {
/* 216 */     return getDisplayName() + getDisplayName() + String.valueOf(class_124.field_1080);
/*     */   }
/*     */   
/*     */   public static boolean fullNullCheck() {
/* 220 */     return (mc.field_1724 == null || mc.field_1687 == null || ModuleManager.unHook.isEnabled());
/*     */   }
/*     */   
/*     */   public String getName() {
/* 224 */     return getDisplayName();
/*     */   }
/*     */   
/*     */   public List<Setting<?>> getSettings() {
/* 228 */     ArrayList<Setting<?>> settingList = new ArrayList<>();
/* 229 */     Class<?> currentSuperclass = getClass();
/*     */     
/* 231 */     while (currentSuperclass != null) {
/* 232 */       for (Field field : currentSuperclass.getDeclaredFields()) {
/* 233 */         if (Setting.class.isAssignableFrom(field.getType())) {
/*     */           
/*     */           try {
/*     */             
/* 237 */             field.setAccessible(true);
/* 238 */             settingList.add((Setting)field.get(this));
/* 239 */           } catch (IllegalAccessException error) {
/* 240 */             ThunderHack.LOGGER.warn(error.getMessage());
/*     */           } 
/*     */         }
/*     */       } 
/* 244 */       currentSuperclass = currentSuperclass.getSuperclass();
/*     */     } 
/*     */     
/* 247 */     settingList.forEach(s -> s.setModule(this));
/*     */     
/* 249 */     return settingList;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 253 */     return isOn();
/*     */   }
/*     */   
/*     */   public boolean isDisabled() {
/* 257 */     return !isEnabled();
/*     */   }
/*     */   
/*     */   public static void clickSlot(int id) {
/* 261 */     if (id == -1 || mc.field_1761 == null || mc.field_1724 == null)
/* 262 */       return;  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, class_1713.field_7790, (class_1657)mc.field_1724);
/*     */   }
/*     */   
/*     */   public static void clickSlot(int id, class_1713 type) {
/* 266 */     if (id == -1 || mc.field_1761 == null || mc.field_1724 == null)
/* 267 */       return;  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, type, (class_1657)mc.field_1724);
/*     */   }
/*     */   
/*     */   public static void clickSlot(int id, int button, class_1713 type) {
/* 271 */     if (id == -1 || mc.field_1761 == null || mc.field_1724 == null)
/* 272 */       return;  mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, button, type, (class_1657)mc.field_1724);
/*     */   }
/*     */   
/*     */   public void sendMessage(String message) {
/* 276 */     if (fullNullCheck() || !((Boolean)ClientSettings.clientMessages.getValue()).booleanValue() || ModuleManager.unHook.isEnabled())
/* 277 */       return;  if (mc.method_18854()) {
/* 278 */       mc.field_1724.method_43496(class_2561.method_30163(CommandManager.getClientMessage() + " " + CommandManager.getClientMessage() + "[" + String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1064) + getDisplayName() + "] " + String.valueOf(class_124.field_1080)));
/*     */     } else {
/* 280 */       mc.method_40000(() -> mc.field_1724.method_43496(class_2561.method_30163(CommandManager.getClientMessage() + " " + CommandManager.getClientMessage() + "[" + String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1064) + getDisplayName() + "] " + String.valueOf(class_124.field_1080))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendChatMessage(String message) {
/* 287 */     if (fullNullCheck())
/* 288 */       return;  mc.method_1562().method_45729(message);
/*     */   }
/*     */   
/*     */   public void sendChatCommand(String command) {
/* 292 */     if (fullNullCheck())
/*     */       return; 
/* 294 */     mc.method_1562().method_45730(command);
/*     */   }
/*     */   
/*     */   public void debug(String message) {
/* 298 */     if (fullNullCheck() || !((Boolean)ClientSettings.debug.getValue()).booleanValue())
/* 299 */       return;  if (mc.method_18854()) {
/* 300 */       mc.field_1724.method_43496(class_2561.method_30163(CommandManager.getClientMessage() + " " + CommandManager.getClientMessage() + "[" + String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1064) + getDisplayName() + "] [🔧] " + String.valueOf(class_124.field_1080)));
/*     */     } else {
/* 302 */       mc.method_40000(() -> mc.field_1724.method_43496(class_2561.method_30163(CommandManager.getClientMessage() + " " + CommandManager.getClientMessage() + "[" + String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1064) + getDisplayName() + "] [🔧] " + String.valueOf(class_124.field_1080))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyPressed(int button) {
/* 309 */     if (button == -1 || ModuleManager.unHook.isEnabled()) {
/* 310 */       return false;
/*     */     }
/* 312 */     if (Managers.MODULE.activeMouseKeys.contains(Integer.valueOf(button))) {
/* 313 */       Managers.MODULE.activeMouseKeys.clear();
/* 314 */       return true;
/*     */     } 
/*     */     
/* 317 */     if (button < 10) {
/* 318 */       return false;
/*     */     }
/* 320 */     return class_3675.method_15987(mc.method_22683().method_4490(), button);
/*     */   }
/*     */   
/*     */   public boolean isKeyPressed(Setting<Bind> bind) {
/* 324 */     if (((Bind)bind.getValue()).getKey() == -1 || ModuleManager.unHook.isEnabled())
/* 325 */       return false; 
/* 326 */     return isKeyPressed(((Bind)bind.getValue()).getKey());
/*     */   }
/*     */   @Nullable
/*     */   public Setting<?> getSettingByName(String name) {
/* 330 */     for (Setting<?> setting : getSettings()) {
/* 331 */       if (!setting.getName().equalsIgnoreCase(name))
/* 332 */         continue;  return setting;
/*     */     } 
/*     */     
/* 335 */     return null;
/*     */   }
/*     */   
/*     */   public static class Category {
/*     */     private final String name;
/* 340 */     private static final Map<String, Category> CATEGORIES = new LinkedHashMap<>();
/*     */ 
/*     */     
/* 343 */     public static final Category COMBAT = new Category("Combat");
/* 344 */     public static final Category MISC = new Category("Misc");
/* 345 */     public static final Category RENDER = new Category("Render");
/* 346 */     public static final Category MOVEMENT = new Category("Movement");
/* 347 */     public static final Category PLAYER = new Category("Player");
/* 348 */     public static final Category CLIENT = new Category("Client");
/* 349 */     public static final Category HUD = new Category("HUD");
/*     */ 
/*     */     
/*     */     static {
/* 353 */       CATEGORIES.put("Combat", COMBAT);
/* 354 */       CATEGORIES.put("Misc", MISC);
/* 355 */       CATEGORIES.put("Render", RENDER);
/* 356 */       CATEGORIES.put("Movement", MOVEMENT);
/* 357 */       CATEGORIES.put("Player", PLAYER);
/* 358 */       CATEGORIES.put("Client", CLIENT);
/* 359 */       CATEGORIES.put("HUD", HUD);
/*     */     }
/*     */ 
/*     */     
/*     */     private Category(String name) {
/* 364 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 368 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Category getCategory(String name) {
/* 373 */       return CATEGORIES.computeIfAbsent(name, Category::new);
/*     */     }
/*     */     
/*     */     public static Collection<Category> values() {
/* 377 */       return CATEGORIES.values();
/*     */     }
/*     */     
/*     */     public static boolean isCustomCategory(Category category) {
/* 381 */       Set<String> predefinedCategoryNames = Set.of("Combat", "Misc", "Render", "Movement", "Player", "Client", "HUD");
/*     */       
/* 383 */       return !predefinedCategoryNames.contains(category.getName());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 388 */       if (this == o) return true; 
/* 389 */       if (o == null || getClass() != o.getClass()) return false; 
/* 390 */       Category category = (Category)o;
/* 391 */       return Objects.equals(this.name, category.name);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 396 */       return Objects.hash(new Object[] { this.name });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\Module.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */