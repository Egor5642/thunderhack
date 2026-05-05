/*     */ package thunder.hack.features.cmd.impl;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1657;
/*     */ import net.minecraft.class_1713;
/*     */ import net.minecraft.class_2172;
/*     */ import net.minecraft.class_2246;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2374;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_2828;
/*     */ import net.minecraft.class_2848;
/*     */ 
/*     */ public class EClipCommand extends Command {
/*     */   public EClipCommand() {
/*  21 */     super(new String[] { "eclip" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/*  26 */     builder.then(((LiteralArgumentBuilder)literal("bedrock").executes(context -> {
/*     */             execute(-((float)mc.field_1724.method_23318()) - 3.0F);
/*     */             return 1;
/*  29 */           })).then(arg("number", (ArgumentType)FloatArgumentType.floatArg()).executes(context -> {
/*     */               float y = -((float)mc.field_1724.method_23318()) - 3.0F;
/*     */               
/*     */               if (y == 0.0F) {
/*     */                 y = ((Float)context.getArgument("number", Float.class)).floatValue();
/*     */               }
/*     */               execute(y);
/*     */               return 1;
/*     */             })));
/*  38 */     builder.then(((LiteralArgumentBuilder)literal("down").executes(context -> {
/*     */             float y = 0.0F;
/*     */             
/*     */             int i = 1;
/*     */             
/*     */             while (i < 255) {
/*     */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) == class_2246.field_10124.method_9564()) {
/*     */                 y = (-i - 1);
/*     */                 break;
/*     */               } 
/*     */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) != class_2246.field_9987.method_9564()) {
/*     */                 i++;
/*     */                 continue;
/*     */               } 
/*     */               sendMessage(String.valueOf(class_124.field_1061) + " можно телепортироваться только под бедрок");
/*     */               sendMessage(String.valueOf(class_124.field_1061) + " eclip bedrock");
/*     */               return 1;
/*     */             } 
/*     */             execute(y);
/*     */             return 1;
/*  58 */           })).then(arg("number", (ArgumentType)FloatArgumentType.floatArg()).executes(context -> {
/*     */               float y = 0.0F;
/*     */               
/*     */               int i = 1;
/*     */               
/*     */               while (i < 255) {
/*     */                 if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) == class_2246.field_10124.method_9564()) {
/*     */                   y = (-i - 1);
/*     */                   break;
/*     */                 } 
/*     */                 if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, -i, 0)) != class_2246.field_9987.method_9564()) {
/*     */                   i++;
/*     */                   continue;
/*     */                 } 
/*     */                 sendMessage(String.valueOf(class_124.field_1061) + " можно телепортироваться только под бедрок");
/*     */                 sendMessage(String.valueOf(class_124.field_1061) + " eclip bedrock");
/*     */                 return 1;
/*     */               } 
/*     */               if (y == 0.0F) {
/*     */                 y = ((Float)context.getArgument("number", Float.class)).floatValue();
/*     */               }
/*     */               execute(y);
/*     */               return 1;
/*     */             })));
/*  82 */     builder.then(((LiteralArgumentBuilder)literal("up").executes(context -> {
/*     */             float y = 0.0F;
/*     */             
/*     */             int i = 4;
/*     */             while (i < 255) {
/*     */               if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, i, 0)) != class_2246.field_10124.method_9564()) {
/*     */                 i++;
/*     */                 continue;
/*     */               } 
/*     */               y = (i + 1);
/*     */             } 
/*     */             execute(y);
/*     */             return 1;
/*  95 */           })).then(arg("number", (ArgumentType)FloatArgumentType.floatArg()).executes(context -> {
/*     */               float y = 0.0F;
/*     */               int i = 4;
/*     */               while (i < 255) {
/*     */                 if (mc.field_1687.method_8320(class_2338.method_49638((class_2374)mc.field_1724.method_19538()).method_10069(0, i, 0)) != class_2246.field_10124.method_9564()) {
/*     */                   i++;
/*     */                   continue;
/*     */                 } 
/*     */                 y = (i + 1);
/*     */               } 
/*     */               if (y == 0.0F) {
/*     */                 y = ((Float)context.getArgument("number", Float.class)).floatValue();
/*     */               }
/*     */               execute(y);
/*     */               return 1;
/*     */             })));
/*     */   }
/*     */ 
/*     */   
/*     */   private void execute(float y) {
/*     */     int elytra;
/* 116 */     if ((elytra = InventoryUtility.findItemInInventory(new class_1792[] { class_1802.field_8833 }).slot()) == -1) {
/* 117 */       sendMessage(String.valueOf(class_124.field_1061) + "вам нужны элитры в инвентаре");
/*     */       return;
/*     */     } 
/* 120 */     if (elytra != -2) {
/* 121 */       mc.field_1761.method_2906(0, elytra, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/* 122 */       mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/*     */     } 
/*     */     
/* 125 */     mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), false));
/* 126 */     mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), false));
/* 127 */     mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12982));
/* 128 */     mc.field_1724.field_3944.method_52787((class_2596)new class_2828.class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + y, mc.field_1724.method_23321(), false));
/* 129 */     mc.field_1724.field_3944.method_52787((class_2596)new class_2848((class_1297)mc.field_1724, class_2848.class_2849.field_12982));
/*     */     
/* 131 */     if (elytra != -2) {
/* 132 */       mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/* 133 */       mc.field_1761.method_2906(0, elytra, 1, class_1713.field_7790, (class_1657)mc.field_1724);
/*     */     } 
/*     */     
/* 136 */     mc.field_1724.method_5814(mc.field_1724.method_23317(), mc.field_1724.method_23318() + y, mc.field_1724.method_23321());
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\EClipCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */