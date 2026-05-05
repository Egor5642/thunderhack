/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2828;
/*    */ import net.minecraft.class_3532;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class HClipCommand extends Command {
/*    */   public HClipCommand() {
/* 16 */     super(new String[] { "hclip" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 21 */     builder.then(literal("s").executes(context -> {
/*    */             double x = -(class_3532.method_15374(mc.field_1724.method_36454() * 0.017453292F) * 0.8D);
/*    */             
/*    */             double z = class_3532.method_15362(mc.field_1724.method_36454() * 0.017453292F) * 0.8D;
/*    */             
/*    */             for (int i = 0; i < 10; i++) {
/*    */               mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317() + x, mc.field_1724.method_23318(), mc.field_1724.method_23321() + z, false));
/*    */             }
/*    */             
/*    */             mc.field_1724.method_5814(mc.field_1724.method_23317() + x, mc.field_1724.method_23318(), mc.field_1724.method_23321() + z);
/*    */             
/*    */             return 1;
/*    */           }));
/* 34 */     builder.then(arg("count", (ArgumentType)DoubleArgumentType.doubleArg()).executes(context -> {
/*    */             double speed = ((Double)context.getArgument("count", Double.class)).doubleValue();
/*    */             
/*    */             try {
/*    */               sendMessage(String.valueOf(class_124.field_1060) + "клипаемся на  " + String.valueOf(class_124.field_1060) + " блоков.");
/*    */               mc.field_1724.method_5814(mc.field_1724.method_23317() - class_3532.method_15374(mc.field_1724.method_36454() * 0.017453292F) * speed, mc.field_1724.method_23318(), mc.field_1724.method_23321() + class_3532.method_15362(mc.field_1724.method_36454() * 0.017453292F) * speed);
/* 40 */             } catch (Exception exception) {}
/*    */ 
/*    */             
/*    */             return 1;
/*    */           }));
/*    */     
/* 46 */     builder.executes(context -> {
/*    */           sendMessage("Попробуй .hclip <число>, .hclip s");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\HClipCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */