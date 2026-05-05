/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class StaffCommand extends Command {
/* 16 */   public static List<String> staffNames = new ArrayList<>();
/*    */   
/*    */   public StaffCommand() {
/* 19 */     super(new String[] { "staff" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 24 */     builder.then(literal("reset").executes(context -> {
/*    */             staffNames.clear();
/*    */             
/*    */             sendMessage("staff list got reset.");
/*    */             
/*    */             return 1;
/*    */           }));
/* 31 */     builder.then(literal("add").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*    */               String name = (String)context.getArgument("name", String.class);
/*    */               
/*    */               staffNames.add(name);
/*    */               
/*    */               sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + " added to staff list");
/*    */               
/*    */               return 1;
/*    */             })));
/* 40 */     builder.then(literal("del").then(arg("name", (ArgumentType)StringArgumentType.word()).executes(context -> {
/*    */               String name = (String)context.getArgument("name", String.class);
/*    */               
/*    */               staffNames.remove(name);
/*    */               
/*    */               sendMessage(String.valueOf(class_124.field_1060) + String.valueOf(class_124.field_1060) + " removed from staff list");
/*    */               
/*    */               return 1;
/*    */             })));
/*    */     
/* 50 */     builder.executes(context -> {
/*    */           if (staffNames.isEmpty()) {
/*    */             sendMessage("Staff list empty");
/*    */           } else {
/*    */             StringBuilder f = new StringBuilder("Staff: ");
/*    */             for (String staff : staffNames) {
/*    */               try {
/*    */                 f.append(staff).append(", ");
/* 58 */               } catch (Exception exception) {}
/*    */             } 
/*    */             sendMessage(f.toString());
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\StaffCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */