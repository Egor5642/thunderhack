/*    */ package thunder.hack.features.cmd;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1074;
/*    */ import net.minecraft.class_2170;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_7157;
/*    */ import net.minecraft.class_7887;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.manager.client.CommandManager;
/*    */ 
/*    */ public abstract class Command {
/* 20 */   protected static final class_7157 REGISTRY_ACCESS = class_2170.method_46732(class_7887.method_46817());
/* 21 */   protected static final class_310 mc = class_310.method_1551();
/*    */   
/*    */   protected final List<String> names;
/*    */   private final String description;
/*    */   
/*    */   public Command(String... names) {
/* 27 */     this.names = Arrays.asList(names);
/* 28 */     this.description = "descriptions.commands." + (String)this.names.get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendMessage(String message) {
/* 34 */     if (mc.field_1724 == null)
/* 35 */       return;  mc.field_1724.method_43496(class_2561.method_30163(CommandManager.getClientMessage() + " " + CommandManager.getClientMessage()));
/*    */   }
/*    */   @NotNull
/*    */   protected static <T> RequiredArgumentBuilder<class_2172, T> arg(String name, ArgumentType<T> type) {
/* 39 */     return RequiredArgumentBuilder.argument(name, type);
/*    */   }
/*    */   @NotNull
/*    */   protected static LiteralArgumentBuilder<class_2172> literal(String name) {
/* 43 */     return LiteralArgumentBuilder.literal(name);
/*    */   }
/*    */   
/*    */   public void register(CommandDispatcher<class_2172> dispatcher) {
/* 47 */     for (String name : this.names) {
/* 48 */       LiteralArgumentBuilder<class_2172> builder = LiteralArgumentBuilder.literal(name);
/* 49 */       executeBuild(builder);
/* 50 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getName() {
/* 55 */     return this.names.get(0);
/*    */   }
/*    */   
/*    */   public String getAliases() {
/* 59 */     return String.join(", ", this.names.stream().filter(n -> !n.equals(this.names.get(0))).toList());
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 63 */     return class_1074.method_4662(this.description, new Object[0]);
/*    */   }
/*    */   
/*    */   public abstract void executeBuild(LiteralArgumentBuilder<class_2172> paramLiteralArgumentBuilder);
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\Command.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */