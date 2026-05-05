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
/*    */ public class BlockESPCommand extends Command {
/*    */   public BlockESPCommand() {
/* 18 */     super(new String[] { "blockesp" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 23 */     builder.then(literal("reset").executes(context -> {
/*    */             ((ItemSelectSetting)ModuleManager.blockESP.selectedBlocks.getValue()).clear();
/*    */             
/*    */             sendMessage(ClientSettings.isRu() ? "BlockESP был очищен!" : "BlockESP got reset.");
/*    */             mc.field_1769.method_3279();
/*    */             return 1;
/*    */           }));
/* 30 */     builder.then(literal("add").then(arg("block", (ArgumentType)SearchArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("block", String.class);
/*    */               
/*    */               class_2248 result = getRegisteredBlock(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.blockESP.selectedBlocks.getValue()).add(result);
/*    */                 
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               
/*    */               mc.field_1769.method_3279();
/*    */               return 1;
/*    */             })));
/* 46 */     builder.then(literal("del").then(arg("block", (ArgumentType)SearchArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("block", String.class);
/*    */               
/*    */               class_2248 result = getRegisteredBlock(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.blockESP.selectedBlocks.getValue()).remove(result);
/*    */                 
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               
/*    */               mc.field_1769.method_3279();
/*    */               return 1;
/*    */             })));
/* 62 */     builder.executes(context -> {
/*    */           if (((ItemSelectSetting)ModuleManager.blockESP.selectedBlocks.getValue()).getItemsById().isEmpty()) {
/*    */             sendMessage("BlockESP list empty");
/*    */           } else {
/*    */             StringBuilder f = new StringBuilder("BlockESP list: ");
/*    */             
/*    */             for (String name : ((ItemSelectSetting)ModuleManager.blockESP.selectedBlocks.getValue()).getItemsById()) {
/*    */               try {
/*    */                 f.append(name).append(", ");
/* 71 */               } catch (Exception exception) {}
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
/* 82 */     for (class_2248 block : class_7923.field_41175) {
/* 83 */       if (block.method_9539().replace("block.minecraft.", "").equalsIgnoreCase(blockName.replace("block.minecraft.", ""))) {
/* 84 */         return block;
/*    */       }
/*    */     } 
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\BlockESPCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */