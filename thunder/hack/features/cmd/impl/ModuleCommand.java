/*     */ package thunder.hack.features.cmd.impl;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2172;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.cmd.Command;
/*     */ import thunder.hack.features.cmd.args.ModuleArgumentType;
/*     */ import thunder.hack.features.cmd.args.SettingArgumentType;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.features.modules.client.ClientSettings;
/*     */ import thunder.hack.setting.Setting;
/*     */ import thunder.hack.setting.impl.BooleanSettingGroup;
/*     */ import thunder.hack.setting.impl.ColorSetting;
/*     */ import thunder.hack.setting.impl.EnumConverter;
/*     */ import thunder.hack.setting.impl.PositionSetting;
/*     */ 
/*     */ public class ModuleCommand extends Command {
/*     */   public ModuleCommand() {
/*  30 */     super(new String[] { "module", "modules" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/*  35 */     builder.then(arg("module", (ArgumentType)ModuleArgumentType.create()).executes(context -> {
/*     */             Module module = (Module)context.getArgument("module", Module.class);
/*     */             
/*     */             sendMessage(module.getDisplayName() + " : " + module.getDisplayName());
/*     */             
/*     */             for (Setting<?> setting2 : (Iterable<Setting<?>>)module.getSettings()) {
/*     */               sendMessage(setting2.getName() + " : " + setting2.getName());
/*     */             }
/*     */             
/*     */             return 1;
/*     */           }));
/*  46 */     builder.then(((RequiredArgumentBuilder)arg("module", (ArgumentType)ModuleArgumentType.create())
/*  47 */         .then(literal("reset").executes(context -> {
/*     */               Module module = (Module)context.getArgument("module", Module.class); for (Setting s : module.getSettings()) {
/*     */                 Object patt0$temp = s.getValue();
/*     */                 if (patt0$temp instanceof ColorSetting) {
/*     */                   ColorSetting cs = (ColorSetting)patt0$temp;
/*     */                   cs.setDefault();
/*     */                   continue;
/*     */                 } 
/*     */                 s.setValue(s.getDefaultValue());
/*     */               } 
/*     */               return 1;
/*  58 */             }))).then(arg("setting", (ArgumentType)SettingArgumentType.create())
/*  59 */           .then(arg("settingValue", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*     */                 Module module = (Module)context.getArgument("module", Module.class);
/*     */                 
/*     */                 String settingName = (String)context.getArgument("setting", String.class);
/*     */                 
/*     */                 String settingValue = (String)context.getArgument("settingValue", String.class);
/*     */                 
/*     */                 Setting setting = null;
/*     */                 for (Setting set : module.getSettings()) {
/*     */                   if (Objects.equals(set.getName(), settingName)) {
/*     */                     setting = set;
/*     */                   }
/*     */                 } 
/*     */                 if (setting == null) {
/*     */                   sendMessage("No such setting");
/*     */                   return 1;
/*     */                 } 
/*     */                 JsonParser jp = new JsonParser();
/*     */                 if (setting.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
/*     */                   setting.setValue(settingValue);
/*     */                   sendMessage(String.valueOf(class_124.field_1063) + String.valueOf(class_124.field_1063) + " " + module.getName() + setting.getName() + (ClientSettings.isRu() ? " был выставлен " : " has been set to "));
/*     */                   return 1;
/*     */                 } 
/*     */                 try {
/*     */                   if (setting.getName().equalsIgnoreCase("Enabled")) {
/*     */                     if (settingValue.equalsIgnoreCase("true")) {
/*     */                       module.enable();
/*     */                     }
/*     */                     if (settingValue.equalsIgnoreCase("false")) {
/*     */                       module.disable();
/*     */                     }
/*     */                   } 
/*     */                   setCommandValue(module, setting, jp.parse(settingValue));
/*  92 */                 } catch (Exception e) {
/*     */                   sendMessage((ClientSettings.isRu() ? "Неверное значение! Эта настройка требует тип: " : "Bad Value! This setting requires a: ") + (ClientSettings.isRu() ? "Неверное значение! Эта настройка требует тип: " : "Bad Value! This setting requires a: "));
/*     */                   
/*     */                   return 1;
/*     */                 } 
/*     */                 
/*     */                 if (settingValue.contains("toggle")) {
/*     */                   sendMessage(String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1080) + " " + module.getName() + setting.getName());
/*     */                 } else {
/*     */                   sendMessage(String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1080) + " " + module.getName() + setting.getName() + (ClientSettings.isRu() ? " был выставлен " : " has been set to "));
/*     */                 } 
/*     */                 return 1;
/*     */               }))));
/* 105 */     builder.executes(context -> {
/*     */           sendMessage("Modules: ");
/*     */           for (Module.Category category : Managers.MODULE.getCategories()) {
/*     */             StringBuilder modules = new StringBuilder(category.getName() + ": ");
/*     */             for (Module module1 : Managers.MODULE.getModulesByCategory(category)) {
/*     */               modules.append(module1.isEnabled() ? class_124.field_1060 : class_124.field_1061).append(module1.getName()).append(class_124.field_1068).append(", ");
/*     */             }
/*     */             sendMessage(modules.toString());
/*     */           } 
/*     */           return 1;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCommandValue(@NotNull Module feature, Setting setting, JsonElement element) {
/* 124 */     for (Setting checkSetting : feature.getSettings()) {
/* 125 */       if (Objects.equals(setting.getName(), checkSetting.getName())) {
/* 126 */         String str; JsonArray array; JsonArray array3; switch (checkSetting.getValue().getClass().getSimpleName()) {
/*     */           case "SettingGroup":
/*     */           case "Bind":
/*     */             return;
/*     */           case "Boolean":
/* 131 */             if (element.getAsString().equals("toggle")) {
/* 132 */               checkSetting.setValue(Boolean.valueOf(!((Boolean)checkSetting.getValue()).booleanValue()));
/*     */               return;
/*     */             } 
/* 135 */             checkSetting.setValue(Boolean.valueOf(element.getAsBoolean()));
/*     */             return;
/*     */           
/*     */           case "BooleanSettingGroup":
/* 139 */             ((BooleanSettingGroup)checkSetting.getValue()).setEnabled(element.getAsBoolean());
/*     */             continue;
/*     */           case "Double":
/* 142 */             checkSetting.setValue(Double.valueOf(element.getAsDouble()));
/*     */             return;
/*     */           
/*     */           case "Float":
/* 146 */             checkSetting.setValue(Float.valueOf(element.getAsFloat()));
/*     */             return;
/*     */           
/*     */           case "Integer":
/* 150 */             checkSetting.setValue(Integer.valueOf(element.getAsInt()));
/*     */             return;
/*     */           
/*     */           case "String":
/* 154 */             str = element.getAsString();
/* 155 */             checkSetting.setValue(str.replace("_", " "));
/*     */             return;
/*     */           
/*     */           case "ColorSetting":
/* 159 */             array = element.getAsJsonArray();
/* 160 */             ((ColorSetting)checkSetting.getValue()).setColor(array.get(0).getAsInt());
/* 161 */             ((ColorSetting)checkSetting.getValue()).setRainbow(array.get(1).getAsBoolean());
/*     */             return;
/*     */           
/*     */           case "PositionSetting":
/* 165 */             array3 = element.getAsJsonArray();
/* 166 */             ((PositionSetting)checkSetting.getValue()).setX(array3.get(0).getAsFloat());
/* 167 */             ((PositionSetting)checkSetting.getValue()).setY(array3.get(1).getAsFloat());
/*     */             return;
/*     */         } 
/*     */         
/*     */         try {
/* 172 */           EnumConverter converter = new EnumConverter(((Enum)checkSetting.getValue()).getClass());
/* 173 */           Enum value = converter.doBackward(element);
/* 174 */           checkSetting.setValue((value == null) ? checkSetting.getDefaultValue() : value);
/* 175 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\ModuleCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */