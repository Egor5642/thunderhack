/*    */ package thunder.hack.features.cmd.impl;
/*    */ 
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.class_124;
/*    */ import net.minecraft.class_2172;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_268;
/*    */ import net.minecraft.class_270;
/*    */ import net.minecraft.class_640;
/*    */ import thunder.hack.core.manager.client.ConfigManager;
/*    */ import thunder.hack.features.cmd.Command;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ 
/*    */ public class TabParseCommand extends Command {
/*    */   public TabParseCommand() {
/* 28 */     super(new String[] { "tabparse" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void executeBuild(LiteralArgumentBuilder<class_2172> builder) {
/* 33 */     builder.executes(context -> {
/*    */           String serverIP = "unknown_server";
/*    */           
/*    */           if (mc.method_1562().method_45734() != null && (mc.method_1562().method_45734()).field_3761 != null) {
/*    */             serverIP = (mc.method_1562().method_45734()).field_3761.replace(':', '_');
/*    */           }
/*    */           
/*    */           String randomSuffix = generateRandomString(5);
/*    */           
/*    */           File dir = new File(ConfigManager.TABPARSER_FOLDER, serverIP);
/*    */           
/*    */           if (!dir.exists()) {
/*    */             dir.mkdirs();
/*    */           }
/*    */           
/*    */           String fileName = serverIP + "-" + serverIP + "-" + (new SimpleDateFormat("dd.MM.yyyy")).format(new Date()) + ".txt";
/*    */           
/*    */           File file = new File(dir, fileName);
/*    */           
/*    */           try {
/*    */             file.createNewFile();
/*    */             
/*    */             OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
/*    */             writer.write("========================\n\n");
/*    */             writer.write("Server: " + (mc.method_1562().method_45734()).field_3761 + "\n");
/*    */             writer.write("Date: " + (new SimpleDateFormat("dd.MM.yyyy")).format(new Date()) + "\n\n");
/*    */             writer.write("========================\n\n");
/*    */             List<class_640> sortedPlayers = new ArrayList<>(mc.method_1562().method_2880());
/*    */             sortedPlayers.sort(());
/*    */             for (class_640 entry : sortedPlayers) {
/*    */               writer.write(class_268.method_1142((class_270)entry.method_2955(), (class_2561)class_2561.method_43470(entry.method_2966().getName())).getString() + "\n");
/*    */             }
/*    */             writer.close();
/*    */             sendMessage(ClientSettings.isRu() ? (String.valueOf(class_124.field_1060) + "Таб успешно сохранен в " + String.valueOf(class_124.field_1060)) : (String.valueOf(class_124.field_1060) + "Tab was successfully saved in " + String.valueOf(class_124.field_1060)));
/* 67 */           } catch (IOException e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */           return 1;
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   private String generateRandomString(int length) {
/* 76 */     char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
/* 77 */     StringBuilder sb = new StringBuilder();
/* 78 */     Random random = new Random();
/* 79 */     for (int i = 0; i < length; i++) {
/* 80 */       char c = chars[random.nextInt(chars.length)];
/* 81 */       sb.append(c);
/*    */     } 
/* 83 */     return sb.toString();
/*    */   }
/*    */   
/*    */   private String getPlayerPrefix(class_640 playerInfo) {
/* 87 */     return (playerInfo.method_2971() != null) ? playerInfo.method_2971().getString() : "";
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\cmd\impl\TabParseCommand.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */