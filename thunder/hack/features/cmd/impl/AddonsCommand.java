/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_2172;
/*    */ import thunder.hack.api.IAddon;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class AddonsCommand
/*    */   extends Command {
/*    */   public AddonsCommand() {
/* 17 */     super(new String[] { "addons" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 22 */     builder.executes(context -> {
/*    */           List<IAddon> sortedAddons = Managers.ADDON.getAddons().stream().filter(Objects::nonNull).sorted(Comparator.comparing(IAddon::getName)).toList();
/*    */           if (sortedAddons.isEmpty()) {
/*    */             sendMessage("No addons installed.");
/*    */             return 1;
/*    */           } 
/*    */           sortedAddons.forEach(());
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\AddonsCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */