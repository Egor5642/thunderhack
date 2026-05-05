/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.utility.world.ExplosionUtility;
/*    */ 
/*    */ public class BenchMarkCommand extends Command {
/*    */   public BenchMarkCommand() {
/* 16 */     super(new String[] { "benchmark" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 21 */     builder.executes(context -> {
/*    */           (new Thread(())).start();
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\BenchMarkCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */