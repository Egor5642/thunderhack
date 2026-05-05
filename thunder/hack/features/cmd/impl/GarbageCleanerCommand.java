/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ 
/*    */ public class GarbageCleanerCommand
/*    */   extends Command
/*    */ {
/*    */   public GarbageCleanerCommand() {
/* 15 */     super(new String[] { "gc", "garbagecleaner", "clearram" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.executes(context -> {
/*    */           sendMessage("Cleaning RAM..");
/*    */           System.gc();
/*    */           sendMessage("Successfully cleaned RAM!");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\GarbageCleanerCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */