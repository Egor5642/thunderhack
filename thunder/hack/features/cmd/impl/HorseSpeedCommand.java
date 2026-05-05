/*    */ package thunder.hack.features.cmd.impl;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1498;
/*    */ import net.minecraft.class_2172;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class HorseSpeedCommand extends Command {
/*    */   public HorseSpeedCommand() {
/* 15 */     super(new String[] { "gethorsespeed", "horsespeed" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
/* 20 */     builder.executes(context -> {
/*    */           if (mc.field_1724.method_5854() != null) {
/*    */             class_1297 patt0$temp = mc.field_1724.method_5854();
/*    */             if (patt0$temp instanceof class_1498) {
/*    */               class_1498 horse = (class_1498)patt0$temp;
/*    */               if (!horse.method_6725()) {
/*    */                 if (ClientSettings.isRu()) {
/*    */                   sendMessage(String.valueOf(class_124.field_1061) + "У тебя нет седла!");
/*    */                 } else {
/*    */                   sendMessage(String.valueOf(class_124.field_1061) + "You don't have a saddle!");
/*    */                 } 
/*    */                 return 1;
/*    */               } 
/*    */               float speed = horse.field_6250 * 43.17F;
/*    */               float ratio = speed / 14.512F;
/*    */               String verbose = "";
/*    */               if (ratio < 0.3D)
/*    */                 verbose = ClientSettings.isRu() ? "У тебя кринжовая лошадь :(" : "Your horse is shitty :("; 
/*    */               if (ratio > 0.3D && ratio < 0.6D)
/*    */                 verbose = ClientSettings.isRu() ? "У тебя нормальная лошадь" : "Your horse is normal"; 
/*    */               if (ratio > 0.6D)
/*    */                 verbose = ClientSettings.isRu() ? "У тебя пиздатая лошадь :)" : "Your horse is good :)"; 
/*    */               if (ratio > 0.9D)
/*    */                 verbose = ClientSettings.isRu() ? "Цыганы уже в пути" : "Your horse is very good :)"; 
/*    */               if (ClientSettings.isRu()) {
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + "Скорость лошади: " + String.valueOf(class_124.field_1060) + " из 14.512. " + speed);
/*    */               } else {
/*    */                 sendMessage(String.valueOf(class_124.field_1060) + "Horse speed: " + String.valueOf(class_124.field_1060) + " out of 14.512. " + speed);
/*    */               } 
/*    */               return 1;
/*    */             } 
/*    */           } 
/*    */           if (ClientSettings.isRu()) {
/*    */             sendMessage(String.valueOf(class_124.field_1061) + "У тебя нет лошади!");
/*    */           } else {
/*    */             sendMessage(String.valueOf(class_124.field_1061) + "You don't have a horse!");
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\HorseSpeedCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */