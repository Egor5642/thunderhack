/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2338;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class GpsCommand extends Command {
/*    */   public GpsCommand() {
/* 15 */     super(new String[] { "gps" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.then(literal("off").executes(context -> {
/*    */             ThunderHack.gps_position = null;
/*    */             
/*    */             return 1;
/*    */           }));
/* 25 */     builder.then(arg("x", (ArgumentType)IntegerArgumentType.integer())
/* 26 */         .then(arg("z", (ArgumentType)IntegerArgumentType.integer()).executes(context -> {
/*    */               int x = ((Integer)context.getArgument("x", Integer.class)).intValue();
/*    */               
/*    */               int z = ((Integer)context.getArgument("z", Integer.class)).intValue();
/*    */               ThunderHack.gps_position = new class_2338(x, 0, z);
/*    */               sendMessage("GPS настроен на X: " + ThunderHack.gps_position.method_10263() + " Z: " + ThunderHack.gps_position.method_10260());
/*    */               return 1;
/*    */             })));
/* 34 */     builder.executes(context -> {
/*    */           sendMessage("Попробуй .gps off / .gps x z");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\GpsCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */