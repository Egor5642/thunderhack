/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_1713;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2815;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ 
/*    */ public class DropAllCommand extends Command {
/*    */   public DropAllCommand() {
/* 16 */     super(new String[] { "dropall", "drop" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 21 */     builder.then(literal("legit").executes(context -> {
/*    */             Managers.ASYNC.run((), 1L);
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             sendMessage("ok");
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             return 1;
/*    */           }));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     builder.executes(context -> {
/*    */           for (int i = 5; i <= 45; i++)
/*    */             mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, i, 1, class_1713.field_7795, (class_1657)mc.field_1724); 
/*    */           mc.field_1724.field_3944.method_52787((class_2596)new class_2815(mc.field_1724.field_7512.field_7763));
/*    */           sendMessage("ok");
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\DropAllCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */