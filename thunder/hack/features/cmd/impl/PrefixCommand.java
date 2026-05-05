/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class PrefixCommand extends Command {
/*    */   public PrefixCommand() {
/* 17 */     super(new String[] { "prefix" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 22 */     builder.then(literal("set").then(arg("prefix", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*    */               String prefix = (String)context.getArgument("prefix", String.class);
/*    */               
/*    */               Managers.COMMAND.setPrefix(prefix);
/*    */               sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + (ClientSettings.isRu() ? "Префикс изменен на " : "Changed prefix to "));
/*    */               ClientSettings.prefix.setValue(prefix);
/*    */               return 1;
/*    */             })));
/* 30 */     builder.executes(context -> {
/*    */           sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + (ClientSettings.isRu() ? "Текущий префикс: " : "Current prefix: "));
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\PrefixCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */