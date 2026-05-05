/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_3675;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.ModuleArgumentType;
/*    */ import thunder.hack.features.hud.impl.KeyBinds;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.setting.impl.Bind;
/*    */ 
/*    */ public class BindCommand
/*    */   extends Command {
/*    */   public BindCommand() {
/* 24 */     super(new String[] { "bind" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 29 */     builder.then(arg("module", (ArgumentType)ModuleArgumentType.create())
/* 30 */         .then(arg("key", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*    */               int key;
/*    */               
/*    */               Module module = (Module)context.getArgument("module", Module.class);
/*    */               
/*    */               String stringKey = (String)context.getArgument("key", String.class);
/*    */               if (stringKey == null) {
/*    */                 sendMessage(module.getName() + " is bound to " + module.getName() + String.valueOf(class_124.field_1080));
/*    */                 return 1;
/*    */               } 
/*    */               if (stringKey.equalsIgnoreCase("none") || stringKey.equalsIgnoreCase("null")) {
/*    */                 key = -1;
/*    */               } else {
/*    */                 try {
/*    */                   key = class_3675.method_15981("key.keyboard." + stringKey.toLowerCase()).method_1444();
/* 45 */                 } catch (NumberFormatException e) {
/*    */                   sendMessage(ClientSettings.isRu() ? "Такой кнопки не существует!" : "There is no such button");
/*    */                   
/*    */                   return 1;
/*    */                 } 
/*    */               } 
/*    */               if (key == 0) {
/*    */                 sendMessage("Unknown key '" + stringKey + "'!");
/*    */                 return 1;
/*    */               } 
/* 55 */               module.setBind(key, (!stringKey.equals("M") && stringKey.contains("M")), false);
/*    */ 
/*    */               
/*    */               sendMessage("Bind for " + String.valueOf(class_124.field_1060) + module.getName() + String.valueOf(class_124.field_1068) + " set to " + String.valueOf(class_124.field_1080) + stringKey.toUpperCase());
/*    */               
/*    */               return 1;
/*    */             })));
/*    */     
/* 63 */     builder.then(literal("list").executes(context -> {
/*    */             StringBuilder binds = new StringBuilder("Binds: ");
/*    */             
/*    */             for (Module feature : Managers.MODULE.modules) {
/*    */               if (!Objects.equals(feature.getBind().getBind(), "None")) {
/*    */                 binds.append("\n- ").append(feature.getName()).append(" -> ").append(KeyBinds.getShortKeyName(feature)).append(feature.getBind().isHold() ? "[hold]" : "");
/*    */               }
/*    */             } 
/*    */             sendMessage(binds.toString());
/*    */             return 1;
/*    */           }));
/* 74 */     builder.then(literal("clear").executes(context -> {
/*    */             for (Module mod : Managers.MODULE.modules)
/*    */               mod.setBind(new Bind(-1, false, false)); 
/*    */             return 1;
/*    */           }));
/* 79 */     builder.then(literal("reset").executes(context -> {
/*    */             for (Module mod : Managers.MODULE.modules)
/*    */               mod.setBind(new Bind(-1, false, false)); 
/*    */             sendMessage("Done!");
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\BindCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */