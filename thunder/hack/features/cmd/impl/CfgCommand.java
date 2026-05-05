/*     */ package thunder.hack.features.cmd.impl;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_156;
/*     */ import net.minecraft.class_2172;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.core.Managers;
/*     */ import thunder.hack.features.cmd.args.CategoryArgumentType;
/*     */ import thunder.hack.features.cmd.args.CfgArgumentType;
/*     */ import thunder.hack.features.cmd.args.ModuleArgumentType;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public class CfgCommand extends Command {
/*     */   public CfgCommand() {
/*  23 */     super(new String[] { "cfg", "config" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/*  28 */     builder.executes(context -> {
/*     */           StringBuilder configs = new StringBuilder("Configs: ");
/*     */           
/*     */           for (String str : Objects.<List>requireNonNull(Managers.CONFIG.getConfigList())) {
/*     */             configs.append("\n- " + String.valueOf(str.equals(Managers.CONFIG.getCurrentConfig().getName().replace(".th", "")) ? class_124.field_1060 : "")).append(str).append(class_124.field_1070);
/*     */           }
/*     */           
/*     */           sendMessage(configs.toString());
/*     */           return 1;
/*     */         });
/*  38 */     builder.then(literal("list").executes(context -> {
/*     */             StringBuilder configs = new StringBuilder("Configs: ");
/*     */             
/*     */             for (String str : Objects.<List>requireNonNull(Managers.CONFIG.getConfigList())) {
/*     */               configs.append("\n- " + String.valueOf(str.equals(Managers.CONFIG.getCurrentConfig().getName().replace(".th", "")) ? class_124.field_1060 : "")).append(str).append(class_124.field_1070);
/*     */             }
/*     */             
/*     */             sendMessage(configs.toString());
/*     */             return 1;
/*     */           }));
/*  48 */     builder.then(literal("dir").executes(context -> {
/*     */             try {
/*     */               class_156.method_668().method_673((new File("ThunderHackRecode/configs/")).toURI());
/*  51 */             } catch (Exception e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             
/*     */             return 1;
/*     */           }));
/*  57 */     builder.then(literal("save").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               Managers.CONFIG.save((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  62 */     builder.then(literal("loadcloud").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               Managers.CONFIG.loadCloud((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  67 */     builder.then(literal("cloudlist").executes(context -> {
/*     */             StringBuilder configs = new StringBuilder("Cloud Configs: \n");
/*     */             
/*     */             for (String str : Objects.<List>requireNonNull(Managers.CONFIG.getCloudConfigs())) {
/*     */               String[] split = str.split(";");
/*     */               
/*     */               configs.append("\n- " + String.valueOf(class_124.field_1067) + split[0] + String.valueOf(class_124.field_1070) + String.valueOf(class_124.field_1080) + " author: " + String.valueOf(class_124.field_1070) + split[1] + String.valueOf(class_124.field_1080) + " last updated: " + String.valueOf(class_124.field_1070) + split[2]);
/*     */             } 
/*     */             sendMessage(configs.toString());
/*     */             return 1;
/*     */           }));
/*  78 */     builder.then(literal("cloud").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*     */               Managers.CONFIG.loadCloud((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  83 */     builder.then(literal("set")
/*  84 */         .then(((RequiredArgumentBuilder)arg("name", (ArgumentType)CfgArgumentType.create())
/*  85 */           .then(arg("module", (ArgumentType)ModuleArgumentType.create()).executes(context -> {
/*     */                 Managers.CONFIG.loadModuleOnly((String)context.getArgument("name", String.class), (Module)context.getArgument("module", Module.class));
/*     */                 
/*     */                 return 1;
/*  89 */               }))).executes(context -> {
/*     */               Managers.CONFIG.load((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*  94 */     builder.then(literal("load")
/*  95 */         .then(((RequiredArgumentBuilder)arg("name", (ArgumentType)CfgArgumentType.create())
/*  96 */           .then(arg("module", (ArgumentType)ModuleArgumentType.create()).executes(context -> {
/*     */                 Managers.CONFIG.loadModuleOnly((String)context.getArgument("name", String.class), (Module)context.getArgument("module", Module.class));
/*     */                 
/*     */                 return 1;
/* 100 */               }))).executes(context -> {
/*     */               Managers.CONFIG.load((String)context.getArgument("name", String.class));
/*     */               
/*     */               return 1;
/*     */             })));
/*     */     
/* 106 */     builder.then(literal("loadCategory")
/* 107 */         .then(arg("name", (ArgumentType)CfgArgumentType.create()).then(arg("category", (ArgumentType)CategoryArgumentType.create()).executes(context -> {
/*     */                 Managers.CONFIG.load((String)context.getArgument("name", String.class), (String)context.getArgument("category", String.class));
/*     */                 
/*     */                 return 1;
/*     */               }))));
/* 112 */     builder.then(literal("loadBinds")
/* 113 */         .then(arg("name", (ArgumentType)CfgArgumentType.create()).executes(context -> {
/*     */               Managers.CONFIG.loadBinds((String)context.getArgument("name", String.class));
/*     */               return 1;
/*     */             })));
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\CfgCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */