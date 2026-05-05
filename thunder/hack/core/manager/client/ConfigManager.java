/*     */ package thunder.hack.core.manager.client;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.mojang.logging.LogUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.Bind;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.ItemSelectSetting;
/*     */ import thunder.hack.setting.impl.PositionSetting;
/*     */ 
/*     */ public class ConfigManager implements IManager {
/*  26 */   public static final File MAIN_FOLDER = new File(mc.field_1697, "ThunderHackRecode"); public static final String CONFIG_FOLDER_NAME = "ThunderHackRecode";
/*  27 */   public static final File CONFIGS_FOLDER = new File(MAIN_FOLDER, "configs");
/*  28 */   public static final File TEMP_FOLDER = new File(MAIN_FOLDER, "temp");
/*  29 */   public static final File MISC_FOLDER = new File(MAIN_FOLDER, "misc");
/*  30 */   public static final File SOUNDS_FOLDER = new File(MISC_FOLDER, "sounds");
/*  31 */   public static final File IMAGES_FOLDER = new File(MISC_FOLDER, "images");
/*  32 */   public static final File TABPARSER_FOLDER = new File(MISC_FOLDER, "tabparser");
/*  33 */   public static final File STASHLOGGER_FOLDER = new File(MISC_FOLDER, "stashlogger");
/*     */   
/*  35 */   public File currentConfig = null;
/*     */   
/*     */   public static boolean firstLaunch = false;
/*     */   
/*     */   public ConfigManager() {
/*  40 */     firstLaunch = !MAIN_FOLDER.exists();
/*  41 */     createDirs(new File[] { MAIN_FOLDER, CONFIGS_FOLDER, TEMP_FOLDER, MISC_FOLDER, SOUNDS_FOLDER, IMAGES_FOLDER, TABPARSER_FOLDER, STASHLOGGER_FOLDER });
/*     */   }
/*     */   
/*     */   private void createDirs(File... dirs) {
/*  45 */     for (File dir : dirs) { if (!dir.exists()) dir.mkdirs();  }
/*     */   
/*     */   } @NotNull
/*     */   public static String getConfigDate(String name) {
/*  49 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/*  50 */     if (!file.exists()) {
/*  51 */       return "none";
/*     */     }
/*  53 */     return (new SimpleDateFormat("dd MMM yyyy HH:mm")).format(new Date(file.lastModified()));
/*     */   }
/*     */   
/*     */   public void load(String name, String category) {
/*  57 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/*  58 */     if (!file.exists()) {
/*  59 */       Command.sendMessage(ClientSettings.isRu() ? ("Конфига " + name + " не существует!") : ("Config " + name + " does not exist!"));
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     if (this.currentConfig != null) {
/*  64 */       save(this.currentConfig);
/*     */     }
/*  66 */     Managers.MODULE.onUnload(category);
/*  67 */     load(file, category);
/*  68 */     Managers.MODULE.onLoad(category);
/*     */   }
/*     */   
/*     */   public void loadBinds(String name) {
/*  72 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/*  73 */     if (!file.exists()) {
/*  74 */       Command.sendMessage(ClientSettings.isRu() ? ("Конфига " + name + " не существует!") : ("Config " + name + " does not exist!"));
/*     */       
/*     */       return;
/*     */     } 
/*  78 */     if (this.currentConfig != null) {
/*  79 */       save(this.currentConfig);
/*     */     }
/*  81 */     loadBinds(file);
/*     */   }
/*     */   
/*     */   private void loadBinds(@NotNull File config) {
/*  85 */     if (!config.exists())
/*  86 */       save(config); 
/*     */     
/*  88 */     try { FileReader reader = new FileReader(config, StandardCharsets.UTF_8); 
/*  89 */       try { JsonObject modulesObject = JsonParser.parseReader(reader).getAsJsonArray().get(0).getAsJsonObject();
/*  90 */         JsonArray modules = modulesObject.getAsJsonArray("Modules");
/*     */         
/*  92 */         if (modules != null)
/*  93 */           for (JsonElement element : modules) {
/*  94 */             parseBinds(element.getAsJsonObject());
/*     */           } 
/*  96 */         Command.sendMessage(ClientSettings.isRu() ? ("Загружены бинды с конфига: " + config.getName()) : ("Loaded bind from config: " + config.getName()));
/*  97 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/*  98 */     { LogUtils.getLogger().warn(e.getMessage()); }
/*     */     
/* 100 */     saveCurrentConfig();
/*     */   }
/*     */   
/*     */   public void load(String name) {
/* 104 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/* 105 */     if (!file.exists()) {
/* 106 */       Command.sendMessage(ClientSettings.isRu() ? ("Конфига " + name + " не существует!") : ("Config " + name + " does not exist!"));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 111 */     if (this.currentConfig != null) {
/* 112 */       save(this.currentConfig);
/*     */     }
/* 114 */     Managers.MODULE.onUnload("none");
/* 115 */     load(file);
/* 116 */     Managers.MODULE.onLoad("none");
/*     */   }
/*     */   
/*     */   public void loadCloud(String name) {
/* 120 */     Command.sendMessage(ClientSettings.isRu() ? "Загружаю.." : "Downloading.."); 
/* 121 */     try { BufferedInputStream in = new BufferedInputStream((new URL("https://raw.githubusercontent.com/Pan4ur/THRecodeUtil/main/configs/" + name + ".th")).openStream()); 
/* 122 */       try { FileOutputStream fileOutputStream = new FileOutputStream(new File(CONFIGS_FOLDER, name + ".th")); 
/* 123 */         try { byte[] dataBuffer = new byte[1024];
/*     */           int bytesRead;
/* 125 */           while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1)
/* 126 */             fileOutputStream.write(dataBuffer, 0, bytesRead); 
/* 127 */           Command.sendMessage(ClientSettings.isRu() ? "Загрузил!" : "Downloaded!");
/* 128 */           load(name);
/* 129 */           fileOutputStream.close(); } catch (Throwable throwable) { try { fileOutputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  in.close(); } catch (Throwable throwable) { try { in.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception e)
/* 130 */     { Command.sendMessage(ClientSettings.isRu() ? "Произошла ошибка при загрузке! Может название неправильное?" : "There was an error downloading! Maybe the name is wrong?"); }
/*     */   
/*     */   }
/*     */   
/*     */   public void loadModuleOnly(String name, Module module) {
/* 135 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/* 136 */     if (!file.exists()) {
/* 137 */       Command.sendMessage(ClientSettings.isRu() ? ("Конфига " + name + " не существует!") : ("Config " + name + " does not exist!"));
/*     */       
/*     */       return;
/*     */     } 
/* 141 */     if (module.isEnabled()) {
/* 142 */       ThunderHack.EVENT_BUS.unsubscribe(module);
/* 143 */       module.setEnabled(false);
/*     */     } 
/*     */     
/* 146 */     loadModuleOnly(file, module);
/*     */     
/* 148 */     if (module.isEnabled())
/* 149 */       ThunderHack.EVENT_BUS.subscribe(module); 
/*     */   }
/*     */   
/*     */   public void load(@NotNull File config) {
/* 153 */     load(config, "none");
/*     */   }
/*     */   
/*     */   private void load(@NotNull File config, String category) {
/* 157 */     if (!config.exists()) {
/* 158 */       save(config);
/*     */     }
/*     */     try {
/* 161 */       FileReader reader = new FileReader(config, StandardCharsets.UTF_8);
/* 162 */       JsonObject modulesObject = JsonParser.parseReader(reader).getAsJsonArray().get(0).getAsJsonObject();
/* 163 */       JsonArray modules = modulesObject.getAsJsonArray("Modules");
/*     */       
/* 165 */       if (modules != null)
/* 166 */         for (JsonElement element : modules) {
/* 167 */           parseModule(element.getAsJsonObject(), category);
/*     */         } 
/* 169 */       Command.sendMessage(ClientSettings.isRu() ? ("Загружен конфиг " + config.getName()) : ("Loaded " + config.getName()));
/* 170 */     } catch (Exception e) {
/* 171 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 174 */     if (Objects.equals(category, "none")) {
/* 175 */       this.currentConfig = config;
/*     */     }
/* 177 */     saveCurrentConfig();
/*     */   }
/*     */   public void loadModuleOnly(File config, Module module) {
/*     */     
/* 181 */     try { FileReader reader = new FileReader(config); 
/* 182 */       try { JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
/* 183 */         JsonObject modulesObject = array.get(0).getAsJsonObject();
/* 184 */         JsonArray modules = modulesObject.getAsJsonArray("Modules");
/*     */         
/* 186 */         if (modules != null)
/* 187 */           for (JsonElement element : modules) {
/* 188 */             JsonObject moduleObject = element.getAsJsonObject();
/* 189 */             Module loadedModule = Managers.MODULE.modules.stream().filter(m -> (moduleObject.getAsJsonObject(m.getName()) != null)).findFirst().orElse(null);
/* 190 */             if (loadedModule != null && Objects.equals(module.getName(), loadedModule.getName())) {
/* 191 */               parseModule(moduleObject, "none");
/*     */             }
/*     */           }  
/* 194 */         Command.sendMessage(ClientSettings.isRu() ? ("Загружен модуль " + module.getName() + " с конфига " + config.getName()) : ("Loaded " + 
/* 195 */             module.getName() + " from " + config.getName()));
/*     */         
/* 197 */         reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/* 198 */     { LogUtils.getLogger().warn(e.getMessage()); }
/*     */   
/*     */   }
/*     */   
/*     */   public void save(String name) {
/* 203 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/* 204 */     if (file.exists()) {
/* 205 */       Command.sendMessage(ClientSettings.isRu() ? ("Перезаписываем " + name + "...") : ("Overwriting " + name + "..."));
/* 206 */       file.delete();
/*     */     } else {
/* 208 */       Command.sendMessage(ClientSettings.isRu() ? ("Конфиг " + name + " успешно сохранен!") : ("Config " + name + " successfully saved!"));
/*     */     } 
/* 210 */     save(file);
/*     */   }
/*     */   
/*     */   public void save(@NotNull File config) {
/*     */     try {
/* 215 */       if (!config.exists())
/* 216 */         config.createNewFile(); 
/* 217 */       JsonArray array = new JsonArray();
/*     */       
/* 219 */       JsonObject modulesObj = new JsonObject();
/* 220 */       modulesObj.add("Modules", (JsonElement)getModuleArray());
/* 221 */       array.add((JsonElement)modulesObj);
/*     */       
/* 223 */       FileWriter writer = new FileWriter(config, StandardCharsets.UTF_8);
/* 224 */       (new GsonBuilder()).setPrettyPrinting().create().toJson((JsonElement)array, writer);
/* 225 */       writer.close();
/* 226 */     } catch (IOException e) {
/* 227 */       LogUtils.getLogger().warn(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseModule(JsonObject object, String category) throws NullPointerException {
/* 236 */     Module module = Managers.MODULE.modules.stream().filter(m -> (object.getAsJsonObject(m.getName()) != null)).findFirst().orElse(null);
/*     */     
/* 238 */     if (module == null) {
/*     */       return;
/*     */     }
/* 241 */     if (!Objects.equals(category, "none") && !module.getCategory().getName().equalsIgnoreCase(category)) {
/*     */       return;
/*     */     }
/* 244 */     JsonObject mobject = object.getAsJsonObject(module.getName());
/*     */     
/* 246 */     for (Setting setting : module.getSettings()) {
/*     */       try {
/* 248 */         if (setting.getValue() instanceof thunder.hack.setting.impl.SettingGroup)
/* 249 */           continue;  if (setting.getValue() instanceof Boolean) {
/* 250 */           setting.setValue(Boolean.valueOf(mobject.getAsJsonPrimitive(setting.getName()).getAsBoolean())); continue;
/* 251 */         }  if (setting.getValue() instanceof Float) {
/* 252 */           setting.setValue(Float.valueOf(mobject.getAsJsonPrimitive(setting.getName()).getAsFloat())); continue;
/* 253 */         }  if (setting.getValue() instanceof Integer) {
/* 254 */           setting.setValue(Integer.valueOf(mobject.getAsJsonPrimitive(setting.getName()).getAsInt())); continue;
/* 255 */         }  if (setting.getValue() instanceof String) {
/* 256 */           setting.setValue(mobject.getAsJsonPrimitive(setting.getName()).getAsString().replace("%%", " ").replace("++", "/")); continue;
/* 257 */         }  if (setting.getValue() instanceof Bind) {
/* 258 */           JsonArray array = mobject.getAsJsonArray(setting.getName());
/* 259 */           if (array.get(0).getAsString().contains("M")) {
/* 260 */             setting.setValue(new Bind(Integer.parseInt(array.get(0).getAsString().replace("M", "")), true, array.get(1).getAsBoolean())); continue;
/*     */           } 
/* 262 */           setting.setValue(new Bind(Integer.parseInt(array.get(0).getAsString()), false, array.get(1).getAsBoolean())); continue;
/*     */         } 
/* 264 */         Object object1 = setting.getValue(); if (object1 instanceof ColorSetting) { ColorSetting colorSetting = (ColorSetting)object1;
/* 265 */           JsonArray array = mobject.getAsJsonArray(setting.getName());
/* 266 */           colorSetting.setColor(array.get(0).getAsInt());
/* 267 */           colorSetting.setRainbow(array.get(1).getAsBoolean()); continue; }
/* 268 */          object1 = setting.getValue(); if (object1 instanceof PositionSetting) { PositionSetting posSetting = (PositionSetting)object1;
/* 269 */           JsonArray array = mobject.getAsJsonArray(setting.getName());
/* 270 */           posSetting.setX(array.get(0).getAsFloat());
/* 271 */           posSetting.setY(array.get(1).getAsFloat()); continue; }
/* 272 */          object1 = setting.getValue(); if (object1 instanceof BooleanSettingGroup) { BooleanSettingGroup bGroup = (BooleanSettingGroup)object1;
/* 273 */           bGroup.setEnabled(mobject.getAsJsonPrimitive(setting.getName()).getAsBoolean()); continue; }
/* 274 */          object1 = setting.getValue(); if (object1 instanceof ItemSelectSetting) { ItemSelectSetting iSetting = (ItemSelectSetting)object1;
/* 275 */           JsonArray array = mobject.getAsJsonArray(setting.getName());
/* 276 */           for (int i = 0; i < array.size(); i++)
/* 277 */           { if (!iSetting.getItemsById().contains(array.get(i).getAsString()))
/* 278 */               iSetting.getItemsById().add(array.get(i).getAsString());  }  continue; }
/* 279 */          if (setting.getValue().getClass().isEnum()) {
/* 280 */           Enum value = (new EnumConverter(((Enum)setting.getValue()).getClass())).doBackward((JsonElement)mobject.getAsJsonPrimitive(setting.getName()));
/* 281 */           setting.setValue((value == null) ? setting.getDefaultValue() : value);
/*     */         } 
/* 283 */       } catch (Exception e) {
/* 284 */         LogUtils.getLogger().warn("[Thunderhack] Module: " + module.getName() + " Setting: " + setting.getName() + " Error: ");
/* 285 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseBinds(JsonObject object) throws NullPointerException {
/* 295 */     Module module = Managers.MODULE.modules.stream().filter(m -> (object.getAsJsonObject(m.getName()) != null)).findFirst().orElse(null);
/*     */     
/* 297 */     if (module == null) {
/*     */       return;
/*     */     }
/* 300 */     JsonObject mobject = object.getAsJsonObject(module.getName());
/*     */     
/* 302 */     for (Setting setting : module.getSettings()) {
/*     */       try {
/* 304 */         if (setting.getValue() instanceof Bind) {
/* 305 */           JsonArray array = mobject.getAsJsonArray(setting.getName());
/* 306 */           if (array.get(0).getAsString().contains("M")) {
/* 307 */             setting.setValue(new Bind(Integer.parseInt(array.get(0).getAsString().replace("M", "")), true, array.get(1).getAsBoolean())); continue;
/*     */           } 
/* 309 */           setting.setValue(new Bind(Integer.parseInt(array.get(0).getAsString()), false, array.get(1).getAsBoolean()));
/*     */         }
/*     */       
/* 312 */       } catch (Exception e) {
/* 313 */         LogUtils.getLogger().warn("[Thunderhack] Module: " + module.getName() + " Setting: " + setting.getName() + " Error: ");
/* 314 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   private JsonArray getModuleArray() {
/* 320 */     JsonArray modulesArray = new JsonArray();
/* 321 */     for (Module m : Managers.MODULE.modules) {
/* 322 */       modulesArray.add((JsonElement)getModuleObject(m));
/*     */     }
/* 324 */     return modulesArray;
/*     */   }
/*     */   
/*     */   public JsonObject getModuleObject(@NotNull Module m) {
/* 328 */     JsonObject attribs = new JsonObject();
/* 329 */     JsonParser jp = new JsonParser();
/*     */     
/* 331 */     for (Setting setting : m.getSettings()) {
/* 332 */       Object object = setting.getValue(); if (object instanceof ColorSetting) { ColorSetting color = (ColorSetting)object;
/* 333 */         JsonArray array = new JsonArray();
/* 334 */         array.add((JsonElement)new JsonPrimitive(Integer.valueOf(color.getRawColor())));
/* 335 */         array.add((JsonElement)new JsonPrimitive(Boolean.valueOf(color.isRainbow())));
/* 336 */         attribs.add(setting.getName(), (JsonElement)array); continue; }
/* 337 */        object = setting.getValue(); if (object instanceof PositionSetting) { PositionSetting pos = (PositionSetting)object;
/* 338 */         JsonArray array = new JsonArray();
/* 339 */         array.add((JsonElement)new JsonPrimitive(Float.valueOf(pos.getX())));
/* 340 */         array.add((JsonElement)new JsonPrimitive(Float.valueOf(pos.getY())));
/* 341 */         attribs.add(setting.getName(), (JsonElement)array); continue; }
/* 342 */        object = setting.getValue(); if (object instanceof BooleanSettingGroup) { BooleanSettingGroup bGroup = (BooleanSettingGroup)object;
/* 343 */         attribs.add(setting.getName(), jp.parse(String.valueOf(bGroup.isEnabled()))); continue; }
/* 344 */        object = setting.getValue(); if (object instanceof Bind) { Bind b = (Bind)object;
/* 345 */         JsonArray array = new JsonArray();
/* 346 */         if (b.isMouse()) {
/* 347 */           array.add(jp.parse(b.getBind()));
/*     */         } else {
/* 349 */           array.add((JsonElement)new JsonPrimitive(Integer.valueOf(b.getKey())));
/* 350 */         }  array.add((JsonElement)new JsonPrimitive(Boolean.valueOf(b.isHold())));
/* 351 */         attribs.add(setting.getName(), (JsonElement)array); continue; }
/* 352 */        object = setting.getValue(); if (object instanceof String) { String str = (String)object;
/*     */         try {
/* 354 */           attribs.add(setting.getName(), jp.parse(str.replace(" ", "%%").replace("/", "++")));
/* 355 */         } catch (Exception exception) {} continue; }
/*     */       
/* 357 */       object = setting.getValue(); if (object instanceof ItemSelectSetting) { ItemSelectSetting iSelect = (ItemSelectSetting)object;
/* 358 */         JsonArray array = new JsonArray();
/* 359 */         for (String id : iSelect.getItemsById())
/* 360 */           array.add((JsonElement)new JsonPrimitive(id)); 
/* 361 */         attribs.add(setting.getName(), (JsonElement)array); continue; }
/* 362 */        if (setting.isEnumSetting()) {
/* 363 */         attribs.add(setting.getName(), (new EnumConverter(((Enum)setting.getValue()).getClass())).doForward((Enum)setting.getValue())); continue;
/*     */       } 
/*     */       try {
/* 366 */         attribs.add(setting.getName(), jp.parse(setting.getValue().toString()));
/* 367 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 372 */     JsonObject moduleObject = new JsonObject();
/* 373 */     moduleObject.add(m.getName(), (JsonElement)attribs);
/* 374 */     return moduleObject;
/*     */   }
/*     */   
/*     */   public void delete(@NotNull File file) {
/* 378 */     file.delete();
/*     */   }
/*     */   
/*     */   public void delete(String name) {
/* 382 */     File file = new File(CONFIGS_FOLDER, name + ".th");
/* 383 */     if (!file.exists()) {
/*     */       return;
/*     */     }
/* 386 */     delete(file);
/*     */   }
/*     */   
/*     */   public List<String> getConfigList() {
/* 390 */     if (!MAIN_FOLDER.exists() || MAIN_FOLDER.listFiles() == null) return null;
/*     */     
/* 392 */     List<String> list = new ArrayList<>();
/*     */     
/* 394 */     if (CONFIGS_FOLDER.listFiles() != null) {
/* 395 */       for (File file : Arrays.<File>stream(Objects.<File[]>requireNonNull(CONFIGS_FOLDER.listFiles())).filter(f -> f.getName().endsWith(".th")).toList()) {
/* 396 */         list.add(file.getName().replace(".th", ""));
/*     */       }
/*     */     }
/* 399 */     return list;
/*     */   }
/*     */   
/*     */   public List<String> getCloudConfigs() {
/* 403 */     List<String> list = new ArrayList<>();
/*     */     try {
/* 405 */       URL url = new URL("https://raw.githubusercontent.com/Pan4ur/THRecodeUtil/main/cloudConfigs.txt");
/* 406 */       BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */       String inputLine;
/* 408 */       while ((inputLine = in.readLine()) != null)
/* 409 */         list.add(inputLine.trim()); 
/* 410 */     } catch (Exception exception) {}
/*     */     
/* 412 */     return list;
/*     */   }
/*     */   
/*     */   public void saveCurrentConfig() {
/* 416 */     File file = new File("ThunderHackRecode/misc/currentcfg.txt");
/*     */     try {
/* 418 */       if (file.exists()) {
/* 419 */         FileWriter writer = new FileWriter(file);
/* 420 */         writer.write(this.currentConfig.getName().replace(".th", ""));
/* 421 */         writer.close();
/*     */       } else {
/* 423 */         file.createNewFile();
/* 424 */         FileWriter writer = new FileWriter(file);
/* 425 */         writer.write(this.currentConfig.getName().replace(".th", ""));
/* 426 */         writer.close();
/*     */       } 
/* 428 */     } catch (Exception e) {
/* 429 */       LogUtils.getLogger().warn(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public File getCurrentConfig() {
/* 434 */     File file = new File("ThunderHackRecode/misc/currentcfg.txt");
/* 435 */     String name = "config";
/*     */     try {
/* 437 */       if (file.exists()) {
/* 438 */         Scanner reader = new Scanner(file);
/* 439 */         while (reader.hasNextLine())
/* 440 */           name = reader.nextLine(); 
/* 441 */         reader.close();
/*     */       } 
/* 443 */     } catch (Exception e) {
/* 444 */       LogUtils.getLogger().warn(e.getMessage());
/*     */     } 
/* 446 */     this.currentConfig = new File(CONFIGS_FOLDER, name + ".th");
/* 447 */     return this.currentConfig;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\ConfigManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */