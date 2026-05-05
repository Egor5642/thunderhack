/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2246;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2374;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class VClipCommand extends Command {
/*    */   public VClipCommand() {
/* 19 */     super(new String[] { "vclip" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 24 */     builder.then(literal("down").executes(context -> {
/*    */             float y = 0.0F;
/*    */             
/*    */             int i = 1;
/*    */             
/*    */             while (i < 255) {
/*    */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) == class_2246.field_10124.method_9564()) {
/*    */                 y = (-i - 1);
/*    */                 
/*    */                 break;
/*    */               } 
/*    */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) != class_2246.field_9987.method_9564()) {
/*    */                 i++;
/*    */                 continue;
/*    */               } 
/*    */               sendMessage(String.valueOf(class_124.field_1061) + String.valueOf(class_124.field_1061));
/*    */               return 1;
/*    */             } 
/*    */             clip(y);
/*    */             return 1;
/*    */           }));
/* 45 */     builder.then(literal("up").executes(context -> {
/*    */             float y = 0.0F;
/*    */             
/*    */             int i = 4;
/*    */             
/*    */             while (i < 255) {
/*    */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, i, 0)) != class_2246.field_10124.method_9564()) {
/*    */                 i++;
/*    */                 continue;
/*    */               } 
/*    */               y = (i + 1);
/*    */             } 
/*    */             clip(y);
/*    */             return 1;
/*    */           }));
/* 60 */     builder.then(arg("count", (ArgumentType)DoubleArgumentType.doubleArg()).executes(context -> {
/*    */             double count = ((Double)context.getArgument("count", Double.class)).doubleValue();
/*    */             
/*    */             try {
/*    */               sendMessage(String.valueOf(class_124.field_1060) + "Клипаемся на " + String.valueOf(class_124.field_1060) + " блоков");
/*    */               clip(count);
/* 66 */             } catch (Exception exception) {}
/*    */ 
/*    */             
/*    */             return 1;
/*    */           }));
/*    */     
/* 72 */     builder.executes(context -> {
/*    */           sendMessage("Попробуй .vclip <число>");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */   
/*    */   private void clip(double b) {
/* 79 */     if (ClientSettings.clipCommandMode.getValue() == ClientSettings.ClipCommandMode.Matrix) {
/* 80 */       int i; for (i = 0; i < 10; i++) {
/* 81 */         mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), false));
/*    */       }
/* 83 */       for (i = 0; i < 10; i++)
/* 84 */         mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + b, mc.field_1724.method_23321(), false)); 
/*    */     } else {
/* 86 */       mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + b, mc.field_1724.method_23321(), false));
/*    */     } 
/* 88 */     mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + b, mc.field_1724.method_23321());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\VClipCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */