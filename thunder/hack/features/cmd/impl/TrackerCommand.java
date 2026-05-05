/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class TrackerCommand extends Command {
/*    */   public TrackerCommand() {
/* 13 */     super(new String[] { "tracker" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 18 */     builder.executes(context -> {
/*    */           if (ModuleManager.tracker.isEnabled())
/*    */             ModuleManager.tracker.sendTrack(); 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\TrackerCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */