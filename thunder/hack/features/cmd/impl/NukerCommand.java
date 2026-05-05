/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_7923;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.cmd.args.SearchArgumentType;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.setting.impl.ItemSelectSetting;
/*    */ 
/*    */ public class NukerCommand extends Command {
/*    */   public NukerCommand() {
/* 18 */     super(new String[] { "nuker" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 23 */     builder.then(literal("reset").executes(context -> {
/*    */             ((ItemSelectSetting)ModuleManager.nuker.selectedBlocks.getValue()).clear();
/*    */             
/*    */             sendMessage(ClientSettings.isRu() ? "Все блоки были удалены!" : "Nuker got reset!");
/*    */             return 1;
/*    */           }));
/* 29 */     builder.then(literal("add").then(arg("block", (ArgumentType)SearchArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("block", String.class);
/*    */               
/*    */               class_2248 result = getRegisteredBlock(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.nuker.selectedBlocks.getValue()).add(result);
/*    */                 
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               return 1;
/*    */             })));
/* 43 */     builder.then(literal("del").then(arg("block", (ArgumentType)SearchArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("block", String.class);
/*    */               
/*    */               class_2248 result = getRegisteredBlock(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.nuker.selectedBlocks.getValue()).remove(blockName);
/*    */                 
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               return 1;
/*    */             })));
/* 57 */     builder.executes(context -> {
/*    */           if (((ItemSelectSetting)ModuleManager.nuker.selectedBlocks.getValue()).getItemsById().isEmpty()) {
/*    */             sendMessage("Nuker list empty");
/*    */           } else {
/*    */             StringBuilder f = new StringBuilder("Nuker list: ");
/*    */             
/*    */             for (String name : ((ItemSelectSetting)ModuleManager.nuker.selectedBlocks.getValue()).getItemsById()) {
/*    */               try {
/*    */                 f.append(name).append(", ");
/* 66 */               } catch (Exception exception) {}
/*    */             } 
/*    */             sendMessage(f.toString());
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class_2248 getRegisteredBlock(String blockName) {
/* 77 */     for (class_2248 block : class_7923.field_41175) {
/* 78 */       if (block.method_9539().replace("block.minecraft.", "").equalsIgnoreCase(blockName.replace("block.minecraft.", ""))) {
/* 79 */         return block;
/*    */       }
/*    */     } 
/* 82 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\NukerCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */