/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.ModuleArgumentType;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class DrawCommand extends Command {
/*    */   public DrawCommand() {
/* 16 */     super(new String[] { "draw" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 21 */     builder.then(arg("module", (ArgumentType)ModuleArgumentType.create()).executes(context -> {
/*    */             Module module = (Module)context.getArgument("module", Module.class);
/*    */             module.setDrawn(!module.isDrawn());
/*    */             if (ClientSettings.isRu()) {
/*    */               sendMessage("Модуль " + String.valueOf(class_124.field_1060) + module.getName() + String.valueOf(class_124.field_1068) + " теперь " + (module.isDrawn() ? "виден в ArrayList" : "не виден в ArrayList"));
/*    */             } else {
/*    */               sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + module.getName() + " is now " + String.valueOf(class_124.field_1068));
/*    */             } 
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\DrawCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */