/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.impl.Bind;
/*    */ 
/*    */ public class ResetBindsCommand extends Command {
/*    */   public ResetBindsCommand() {
/* 15 */     super(new String[] { "resetbinds", "unbind", "fuckbinds" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.executes(context -> {
/*    */           for (Module mod : Managers.MODULE.modules)
/*    */             mod.setBind(new Bind(-1, false, false)); 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\ResetBindsCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */