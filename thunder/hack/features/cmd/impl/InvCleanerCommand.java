/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1792;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2248;
/*    */ import net.minecraft.class_7923;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.manager.client.ModuleManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.cmd.args.ChestStealerArgumentType;
/*    */ import thunder.hack.setting.impl.ItemSelectSetting;
/*    */ 
/*    */ public class InvCleanerCommand extends Command {
/*    */   public InvCleanerCommand() {
/* 19 */     super(new String[] { "invcleaner", "cleaner" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 24 */     builder.then(literal("reset").executes(context -> {
/*    */             ((ItemSelectSetting)ModuleManager.inventoryCleaner.items.getValue()).clear();
/*    */             
/*    */             sendMessage("InvCleaner got reset.");
/*    */             return 1;
/*    */           }));
/* 30 */     builder.then(literal("add").then(arg("item", (ArgumentType)ChestStealerArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("item", String.class);
/*    */               
/*    */               String result = getRegistered(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.inventoryCleaner.items.getValue()).add(result);
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               return 1;
/*    */             })));
/* 43 */     builder.then(literal("del").then(arg("item", (ArgumentType)ChestStealerArgumentType.create()).executes(context -> {
/*    */               String blockName = (String)context.getArgument("item", String.class);
/*    */               
/*    */               String result = getRegistered(blockName);
/*    */               
/*    */               if (result != null) {
/*    */                 ((ItemSelectSetting)ModuleManager.inventoryCleaner.items.getValue()).remove(result);
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + blockName);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               } 
/*    */               return 1;
/*    */             })));
/* 56 */     builder.executes(context -> {
/*    */           if (((ItemSelectSetting)ModuleManager.inventoryCleaner.items.getValue()).getItemsById().isEmpty()) {
/*    */             sendMessage("InvCleaner list empty");
/*    */           } else {
/*    */             StringBuilder f = new StringBuilder("InvCleaner list: ");
/*    */             
/*    */             for (String name : ((ItemSelectSetting)ModuleManager.inventoryCleaner.items.getValue()).getItemsById()) {
/*    */               try {
/*    */                 f.append(name).append(", ");
/* 65 */               } catch (Exception exception) {}
/*    */             } 
/*    */             sendMessage(f.toString());
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getRegistered(String Name) {
/* 75 */     for (class_2248 block : class_7923.field_41175) {
/* 76 */       if (block.method_9539().replace("block.minecraft.", "").equalsIgnoreCase(Name)) {
/* 77 */         return block.method_9539().replace("block.minecraft.", "");
/*    */       }
/*    */     } 
/* 80 */     for (class_1792 item : class_7923.field_41178) {
/* 81 */       if (item.method_7876().replace("item.minecraft.", "").equalsIgnoreCase(Name)) {
/* 82 */         return item.method_7876().replace("item.minecraft.", "");
/*    */       }
/*    */     } 
/* 85 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\InvCleanerCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */