/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class GetNbtCommand extends Command {
/*    */   public GetNbtCommand() {
/* 13 */     super(new String[] { "nbt", "getnbt" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 18 */     builder.executes(context -> {
/*    */           sendMessage((mc.field_1724.method_6047().method_57353() != null) ? mc.field_1724.method_6047().method_57353().toString() : (ClientSettings.isRu() ? "У этого предмета нет nbt тегов!" : "This item don't contains nbt tags!"));
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\GetNbtCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */