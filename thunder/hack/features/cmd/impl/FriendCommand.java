/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_640;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.FriendArgumentType;
/*    */ import thunder.hack.features.cmd.args.PlayerArgumentType;
/*    */ 
/*    */ public class FriendCommand extends Command {
/*    */   public FriendCommand() {
/* 17 */     super(new String[] { "friend", "friends" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 22 */     builder.then(literal("reset").executes(context -> {
/*    */             Managers.FRIEND.clear();
/*    */             
/*    */             sendMessage("Friends got reset.");
/*    */             
/*    */             return 1;
/*    */           }));
/* 29 */     builder.then(literal("add").then(arg("player", (ArgumentType)PlayerArgumentType.create()).executes(context -> {
/*    */               class_640 player = (class_640)context.getArgument("player", class_640.class);
/*    */               
/*    */               Managers.FRIEND.addFriend(player.method_2966().getName());
/*    */               
/*    */               sendMessage(player.method_2966().getName() + " has been friended");
/*    */               return 1;
/*    */             })));
/* 37 */     builder.then(literal("remove").then(arg("player", (ArgumentType)FriendArgumentType.create()).executes(context -> {
/*    */               String nickname = (String)context.getArgument("player", String.class);
/*    */               
/*    */               Managers.FRIEND.removeFriend(nickname);
/*    */               
/*    */               sendMessage(nickname + " has been unfriended");
/*    */               return 1;
/*    */             })));
/* 45 */     builder.then(literal("is").then(arg("player", (ArgumentType)PlayerArgumentType.create()).executes(context -> {
/*    */               class_640 player = (class_640)context.getArgument("player", class_640.class);
/*    */               
/*    */               sendMessage(player.method_2966().getName() + player.method_2966().getName());
/*    */               
/*    */               return 1;
/*    */             })));
/* 52 */     builder.executes(context -> {
/*    */           if (Managers.FRIEND.getFriends().isEmpty()) {
/*    */             sendMessage("Friend list empty D:");
/*    */           } else {
/*    */             StringBuilder f = new StringBuilder("Friends: ");
/*    */             for (String friend : Managers.FRIEND.getFriends()) {
/*    */               try {
/*    */                 f.append(friend).append(", ");
/* 60 */               } catch (Exception exception) {}
/*    */             } 
/*    */             sendMessage(f.toString());
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\FriendCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */