/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.io.File;
/*    */ import net.minecraft.class_156;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class OpenFolderCommand
/*    */   extends Command {
/*    */   public OpenFolderCommand() {
/* 15 */     super(new String[] { "openfolder", "folder" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.executes(context -> {
/*    */           class_156.method_668().method_672(new File("ThunderHackRecode/configs/"));
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\OpenFolderCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */