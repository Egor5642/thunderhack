/*     */ package thunder.hack.features.cmd.impl;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1799;
/*     */ import net.minecraft.class_2172;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.manager.client.ModuleManager;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ 
/*     */ public class KitCommand extends Command {
/*     */   public KitCommand() {
/*  25 */     super(new String[] { "kit" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/*  30 */     builder.then(literal("list").executes(context -> {
/*     */             listMessage();
/*     */             
/*     */             return 1;
/*     */           }));
/*     */     
/*  36 */     builder.then(literal("create").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               save((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  41 */     builder.then(literal("set").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               set((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  46 */     builder.then(literal("del").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               delete((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  51 */     builder.executes(context -> {
/*     */           sendMessage("kit <create/set/del/list> <name>");
/*     */           return 1;
/*     */         });
/*     */   }
/*     */   private static final String PATH = "ThunderHackRecode/misc/AutoGear.json";
/*     */   public static String getSelectedKit() {
/*     */     try {
/*  59 */       JsonObject json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/*  60 */       if (!json.get("selected").getAsString().equals("none"))
/*  61 */         return json.get("selected").getAsString(); 
/*  62 */     } catch (Exception exception) {}
/*     */     
/*  64 */     sendMessage(ClientSettings.isRu() ? "Кит не найден" : "Kit not found");
/*  65 */     return "";
/*     */   }
/*     */   
/*     */   public static String getKitItems(String kit) {
/*     */     try {
/*  70 */       JsonObject json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/*  71 */       return json.get(kit).getAsString();
/*  72 */     } catch (Exception exception) {
/*     */       
/*  74 */       sendMessage(ClientSettings.isRu() ? "Кит не найден" : "Kit not found");
/*  75 */       return "";
/*     */     } 
/*     */   }
/*     */   private void listMessage() {
/*     */     try {
/*  80 */       JsonObject json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/*  81 */       sendMessage(ClientSettings.isRu() ? "Доступные киты:" : "Available kits:");
/*  82 */       for (int i = 0; i < json.entrySet().size(); i++) {
/*  83 */         String item = json.entrySet().toArray()[i].toString().split("=")[0];
/*  84 */         sendMessage(String.valueOf(class_124.field_1080) + "-> " + String.valueOf(class_124.field_1080) + item);
/*     */       } 
/*  86 */     } catch (Exception e) {
/*  87 */       sendMessage(ClientSettings.isRu() ? "Проблема с конфигурацией китов!" : "Error with kit cfg!");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void delete(String name) {
/*     */     try {
/*  93 */       JsonObject json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/*  94 */       if (json.get(name) != null && !name.equals("selected"))
/*  95 */       { json.remove(name);
/*  96 */         if (json.get("selected").getAsString().equals(name))
/*  97 */           json.addProperty("selected", "none"); 
/*  98 */         saveFile(json, name, ClientSettings.isRu() ? "удален" : "deleted"); }
/*  99 */       else { sendMessage("Kit not found"); }
/*     */     
/* 101 */     } catch (Exception e) {
/* 102 */       sendMessage(ClientSettings.isRu() ? "Кит не найден" : "Kit not found");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void set(String name) {
/*     */     try {
/* 108 */       JsonObject json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/* 109 */       if (json.get(name) != null && !name.equals("selected"))
/* 110 */       { json.addProperty("selected", name);
/* 111 */         saveFile(json, name, ClientSettings.isRu() ? "выбран" : "selected");
/* 112 */         ModuleManager.autoGear.setup(); }
/* 113 */       else { sendMessage(ClientSettings.isRu() ? "Кит не найден" : "Kit not found"); } 
/* 114 */     } catch (Exception e) {
/* 115 */       sendMessage(ClientSettings.isRu() ? "Кит не найден" : "Kit not found");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void save(String name) {
/* 120 */     JsonObject json = new JsonObject();
/*     */     try {
/* 122 */       json = (new JsonParser()).parse(new FileReader("ThunderHackRecode/misc/AutoGear.json")).getAsJsonObject();
/* 123 */       if (json.get(name) != null && !name.equals("selected")) {
/* 124 */         sendMessage(ClientSettings.isRu() ? "Этот кит уже существует" : "This kit arleady exist");
/*     */         return;
/*     */       } 
/* 127 */     } catch (IOException e) {
/* 128 */       json.addProperty("selected", "none");
/*     */     } 
/*     */     
/* 131 */     StringBuilder jsonInventory = new StringBuilder();
/*     */     
/* 133 */     for (class_1799 item : (mc.field_1724.method_31548()).field_7547) {
/* 134 */       jsonInventory.append((item.method_7909() instanceof net.minecraft.class_1812) ? (item.method_7909().method_7876() + item.method_7909().method_7876()) : item.method_7909().method_7876()).append(" ");
/*     */     }
/* 136 */     json.addProperty(name, jsonInventory.toString());
/* 137 */     saveFile(json, name, ClientSettings.isRu() ? "сохранен" : "saved");
/*     */   }
/*     */   
/*     */   private void saveFile(@NotNull JsonObject completeJson, String name, String operation) {
/*     */     try {
/* 142 */       File file = new File("ThunderHackRecode/misc/AutoGear.json");
/*     */       try {
/* 144 */         file.createNewFile();
/* 145 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 148 */       BufferedWriter bw = new BufferedWriter(new FileWriter("ThunderHackRecode/misc/AutoGear.json"));
/* 149 */       bw.write(completeJson.toString());
/* 150 */       bw.close();
/* 151 */       sendMessage((ClientSettings.isRu() ? "Кит " : "Kit ") + (ClientSettings.isRu() ? "Кит " : "Kit ") + String.valueOf(class_124.field_1075) + name + " " + String.valueOf(class_124.field_1070));
/* 152 */     } catch (IOException e) {
/* 153 */       sendMessage(ClientSettings.isRu() ? "Ошибка сохранения файла" : "Error saving the file");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\KitCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */