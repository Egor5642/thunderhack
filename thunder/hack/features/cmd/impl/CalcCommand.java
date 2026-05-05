/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class CalcCommand extends Command {
/*    */   public CalcCommand() {
/* 13 */     super(new String[] { "calc" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 18 */     builder.then(arg("count", (ArgumentType)StringArgumentType.string()).executes(context -> {
/*    */             String expression = (String)context.getArgument("count", String.class);
/*    */             try {
/*    */               sendMessage(evaluateExpression(expression));
/*    */               return 1;
/* 23 */             } catch (Exception e) {
/*    */               sendMessage("Try use operators: + - m(*) d(/)");
/*    */               return -1;
/*    */             } 
/*    */           }));
/*    */   }
/*    */   
/*    */   public static String evaluateExpression(String expression) {
/* 31 */     char operator = Character.MIN_VALUE;
/* 32 */     int operand1 = 0;
/* 33 */     int operand2 = 0;
/* 34 */     for (int i = 0; i < expression.length(); i++) {
/* 35 */       char ch = expression.charAt(i);
/* 36 */       if (ch == '+' || ch == '-' || ch == 'm' || ch == 'd') {
/* 37 */         operator = ch;
/* 38 */         operand1 = Integer.parseInt(expression.substring(0, i));
/* 39 */         operand2 = Integer.parseInt(expression.substring(i + 1));
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 44 */     switch (operator) { case '+': 
/*    */       case '-': 
/*    */       case 'm':
/*    */       
/*    */       case 'd':
/* 49 */        }  throw new IllegalArgumentException("Wrong");
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\CalcCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */