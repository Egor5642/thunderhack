/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_266;
/*    */ import thunder.hack.ThunderHack;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class RctCommand extends Command {
/*    */   public RctCommand() {
/* 15 */     super(new String[] { "rct" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.executes(context -> {
/*    */           String sName = (mc.field_1724.field_3944.method_45734() == null) ? "none" : (mc.field_1724.field_3944.method_45734()).field_3761;
/*    */           if (!sName.contains("funtime") && !sName.contains("spookytime")) {
/*    */             sendMessage(ClientSettings.isRu() ? "Rct работает только на фанике и спуки" : "Rct works only on funtime and spookytime");
/*    */             return 1;
/*    */           } 
/*    */           String an = "an" + ((class_266)mc.field_1724.method_7327().method_1151().toArray()[0]).method_1114().getString().substring(10);
/*    */           Managers.ASYNC.run(());
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\RctCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */