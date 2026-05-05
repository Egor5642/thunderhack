/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_3675;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.core.manager.client.MacroManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.MacroArgumentType;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class MacroCommand
/*    */   extends Command {
/*    */   public MacroCommand() {
/* 22 */     super(new String[] { "macro", "macros" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 27 */     builder.then(literal("list").executes(context -> {
/*    */             sendMessage(ClientSettings.isRu() ? "Макросы:" : "Macros:");
/*    */             
/*    */             sendMessage(" ");
/*    */             Managers.MACRO.getMacros().forEach(());
/*    */             return 1;
/*    */           }));
/* 34 */     builder.then(literal("remove").then(arg("macro", (ArgumentType)MacroArgumentType.create()).executes(context -> {
/*    */               MacroManager.Macro macro = (MacroManager.Macro)context.getArgument("macro", MacroManager.Macro.class);
/*    */               
/*    */               if (macro == null) {
/*    */                 sendMessage(ClientSettings.isRu() ? "Не существует такого макроса!" : "Wrong macro name!");
/*    */                 
/*    */                 return 1;
/*    */               } 
/*    */               
/*    */               Managers.MACRO.removeMacro(macro);
/*    */               sendMessage((ClientSettings.isRu() ? "Удален макрос " : "Removed macro ") + (ClientSettings.isRu() ? "Удален макрос " : "Removed macro "));
/*    */               return 1;
/*    */             })));
/* 47 */     builder.then(literal("add")
/* 48 */         .then(arg("name", (ArgumentType)StringArgumentType.word())
/* 49 */           .then(arg("bind", (ArgumentType)StringArgumentType.word())
/* 50 */             .then(arg("args", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*    */                   String name = (String)context.getArgument("name", String.class);
/*    */                   
/*    */                   String bind = ((String)context.getArgument("bind", String.class)).toUpperCase();
/*    */                   
/*    */                   String args = (String)context.getArgument("args", String.class);
/*    */                   
/*    */                   if (class_3675.method_15981("key.keyboard." + bind.toLowerCase()).method_1444() == -1) {
/*    */                     sendMessage(ClientSettings.isRu() ? "Неправильный бинд!" : "Wrong bind!");
/*    */                     
/*    */                     return 1;
/*    */                   } 
/*    */                   MacroManager.Macro macro = new MacroManager.Macro(name, args, class_3675.method_15981("key.keyboard." + bind.toLowerCase()).method_1444());
/*    */                   MacroManager.addMacro(macro);
/*    */                   sendMessage(ClientSettings.isRu() ? ("Добавлен макрос " + name + " на кнопку " + toString(macro.getBind())) : ("Added macro " + name + " to " + toString(macro.getBind())));
/*    */                   return 1;
/*    */                 })))));
/* 67 */     builder.executes(context -> {
/*    */           sendMessage(usage());
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString(int key) {
/* 75 */     String kn = (key > 0) ? GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key)) : "None";
/*    */     
/* 77 */     if (kn == null) {
/*    */       try {
/* 79 */         for (Field declaredField : GLFW.class.getDeclaredFields()) {
/* 80 */           if (declaredField.getName().startsWith("GLFW_KEY_")) {
/* 81 */             int a = ((Integer)declaredField.get(null)).intValue();
/* 82 */             if (a == key) {
/* 83 */               String nb = declaredField.getName().substring("GLFW_KEY_".length());
/* 84 */               kn = nb.substring(0, 1).toUpperCase() + nb.substring(0, 1).toUpperCase();
/*    */             } 
/*    */           } 
/*    */         } 
/* 88 */       } catch (Exception ignored) {
/* 89 */         kn = "unknown." + key;
/*    */       } 
/*    */     }
/*    */     
/* 93 */     return (key == -1) ? "None" : kn.toUpperCase();
/*    */   }
/*    */   
/*    */   String usage() {
/* 97 */     return "macro add/remove/list (macro add name key text), (macro remove name)";
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\MacroCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */