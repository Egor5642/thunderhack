/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2561;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class HelpCommand
/*    */   extends Command
/*    */ {
/*    */   public HelpCommand() {
/* 18 */     super(new String[] { "help" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 23 */     builder.executes(context -> {
/*    */           sendMessage("Commands: \n");
/*    */           AtomicBoolean flip = new AtomicBoolean(false);
/*    */           Managers.COMMAND.getCommands().forEach(());
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\HelpCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */