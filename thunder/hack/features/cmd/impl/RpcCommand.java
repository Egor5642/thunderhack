/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.RPC;
/*    */ 
/*    */ public class RpcCommand extends Command {
/*    */   public RpcCommand() {
/* 14 */     super(new String[] { "rpc" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 19 */     builder.then(((RequiredArgumentBuilder)arg("bigImg", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*    */             String bigImg = (String)context.getArgument("bigImg", String.class);
/*    */             
/*    */             RPC.WriteFile(bigImg, "none");
/*    */             
/*    */             sendMessage("Большая картинка RPC изменена на " + bigImg);
/*    */             return 1;
/* 26 */           })).then(arg("littleImg", (ArgumentType)StringArgumentType.greedyString()).executes(context -> {
/*    */               String bigImg = (String)context.getArgument("bigImg", String.class);
/*    */               
/*    */               String littleImg = (String)context.getArgument("littleImg", String.class);
/*    */               
/*    */               RPC.WriteFile(bigImg, littleImg);
/*    */               
/*    */               sendMessage("Большая картинка RPC изменена на " + bigImg);
/*    */               sendMessage("Маленькая картинка RPC изменена на " + littleImg);
/*    */               return 1;
/*    */             })));
/* 37 */     builder.executes(context -> {
/*    */           sendMessage(".rpc url or .rpc url url");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\RpcCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */