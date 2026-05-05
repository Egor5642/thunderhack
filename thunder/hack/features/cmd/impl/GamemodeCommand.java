/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_1934;
/*    */ import net.minecraft.class_2172;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class GamemodeCommand extends Command {
/*    */   public GamemodeCommand() {
/* 13 */     super(new String[] { "gamemode", "gm" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 18 */     builder.then(arg("mode", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*    */             String mode = (String)context.getArgument("mode", String.class);
/*    */             switch (mode) {
/*    */               case "survival":
/*    */               case "0":
/*    */                 mc.field_1761.method_2907(class_1934.field_9215);
/*    */               case "creative":
/*    */               case "1":
/*    */                 mc.field_1761.method_2907(class_1934.field_9220);
/*    */               case "spectator":
/*    */               case "2":
/*    */                 mc.field_1761.method_2907(class_1934.field_9219);
/*    */               case "adventure":
/*    */               case "3":
/*    */                 mc.field_1761.method_2907(class_1934.field_9216);
/*    */                 break;
/*    */             } 
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\GamemodeCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */