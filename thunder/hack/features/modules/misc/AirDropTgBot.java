/*     */ package thunder.hack.features.modules.misc;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.class_2596;
/*     */ import net.minecraft.class_7439;
/*     */ import thunder.hack.features.modules.Module;
/*     */ 
/*     */ public class AirDropTgBot extends Module {
/*     */   private final String BOT_TOKEN = "8667757999:AAFJZNd70pLVVanLLMekD-Db6U6Pmk5b8iI";
/*     */   private final String CHAT_ID = "6823107448";
/*     */   private int messageId30s;
/*     */   private int messageIdCoords;
/*     */   
/*     */   public AirDropTgBot() {
/*  25 */     super("AirDropTg", Module.Category.MISC);
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.BOT_TOKEN = "8667757999:AAFJZNd70pLVVanLLMekD-Db6U6Pmk5b8iI";
/*  30 */     this.CHAT_ID = "6823107448";
/*     */     
/*  32 */     this.messageId30s = -1;
/*  33 */     this.messageIdCoords = -1;
/*     */     
/*  35 */     this.currentCoords = null;
/*  36 */     this.openTimeMillis = -1L;
/*     */ 
/*     */     
/*  39 */     this.isPolling = false;
/*  40 */     this.lastUpdateId = 0L;
/*     */   }
/*     */   private String currentCoords; private long openTimeMillis; private Thread pollingThread; private boolean isPolling; private long lastUpdateId;
/*     */   public void onEnable() {
/*  44 */     this.isPolling = true;
/*  45 */     startBotPolling();
/*  46 */     resetState();
/*  47 */     System.out.println("[AirDropTg] Модуль включен, пулинг ТГ запущен.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  52 */     this.isPolling = false;
/*  53 */     if (this.pollingThread != null) {
/*  54 */       this.pollingThread.interrupt();
/*     */     }
/*  56 */     System.out.println("[AirDropTg] Модуль выключен.");
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/*  61 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_7439) { class_7439 packet = (class_7439)class_2596;
/*  62 */       String msg = packet.comp_763().getString();
/*     */ 
/*     */       
/*  65 */       if (msg.contains("На карте через 30 секунд приземлится Аир-Дроп")) {
/*  66 */         sendTgMessage("📦 Через 30 сек будет аир дроп!").thenAccept(msgId -> this.messageId30s = msgId.intValue());
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*  71 */       else if (msg.contains("На карте приземлился Аир-Дроп на координатах")) {
/*  72 */         if (this.messageId30s != -1) {
/*  73 */           deleteTgMessage(this.messageId30s);
/*  74 */           this.messageId30s = -1;
/*     */         } 
/*     */         
/*  77 */         Pattern pattern = Pattern.compile("координатах ([-0-9., ]+)\\.");
/*  78 */         Matcher matcher = pattern.matcher(msg);
/*  79 */         if (matcher.find()) {
/*  80 */           this.currentCoords = matcher.group(1).trim();
/*     */         } else {
/*  82 */           this.currentCoords = "неизвестно";
/*     */         } 
/*     */         
/*  85 */         this.openTimeMillis = System.currentTimeMillis() + 600000L;
/*     */         
/*  87 */         sendTgMessage("🎯 Аир-Дроп появился!\n📍 Координаты: " + this.currentCoords + "\n⏳ До открытия 600 сек.").thenAccept(msgId -> this.messageIdCoords = msgId.intValue());
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*  92 */       else if (msg.contains("Аир-Дроп откроется через 10 секунд")) {
/*  93 */         sendTgMessage("⚠️ Аир-Дроп откроется через 10 сек!");
/*     */       
/*     */       }
/*  96 */       else if (msg.contains("Аир-Дроп был открыт!")) {
/*  97 */         sendTgMessage("🔓 Аир-Дроп открыт!");
/*     */       
/*     */       }
/* 100 */       else if (msg.contains("Аир-Дроп был залутан и удалён")) {
/* 101 */         if (this.messageIdCoords != -1) {
/* 102 */           deleteTgMessage(this.messageIdCoords);
/* 103 */           this.messageIdCoords = -1;
/*     */         } 
/* 105 */         sendTgMessage("💀 Аир-Дроп был залутан и удалён! Ждем новый.");
/* 106 */         resetState();
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   private void resetState() {
/* 112 */     this.currentCoords = null;
/* 113 */     this.openTimeMillis = -1L;
/* 114 */     this.messageId30s = -1;
/* 115 */     this.messageIdCoords = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void startBotPolling() {
/* 121 */     this.pollingThread = new Thread(() -> {
/*     */           while (this.isPolling) {
/*     */             try {
/*     */               String urlStr = "https://api.telegram.org/bot8667757999:AAFJZNd70pLVVanLLMekD-Db6U6Pmk5b8iI/getUpdates?offset=" + this.lastUpdateId + 1L + "&timeout=5";
/*     */               
/*     */               HttpURLConnection conn = (HttpURLConnection)(new URL(urlStr)).openConnection();
/*     */               
/*     */               conn.setRequestMethod("GET");
/*     */               if (conn.getResponseCode() == 200) {
/*     */                 JsonObject response = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
/*     */                 if (response.get("ok").getAsBoolean()) {
/*     */                   JsonArray results = response.getAsJsonArray("result");
/*     */                   for (JsonElement element : results) {
/*     */                     JsonObject update = element.getAsJsonObject();
/*     */                     this.lastUpdateId = update.get("update_id").getAsLong();
/*     */                     if (update.has("message")) {
/*     */                       JsonObject message = update.getAsJsonObject("message");
/*     */                       if (message.has("text")) {
/*     */                         String text = message.get("text").getAsString();
/*     */                         if (text.startsWith("/air")) {
/*     */                           handleAirCommand();
/*     */                         }
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               Thread.sleep(1500L);
/* 149 */             } catch (InterruptedException e) {
/*     */               Thread.currentThread().interrupt();
/*     */               break;
/* 152 */             } catch (Exception exception) {}
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 157 */     this.pollingThread.setDaemon(true);
/* 158 */     this.pollingThread.start();
/*     */   }
/*     */   
/*     */   private void handleAirCommand() {
/* 162 */     if (this.currentCoords == null) {
/* 163 */       sendTgMessage("ℹ️ Аира пока нету.");
/*     */     } else {
/* 165 */       long timeLeft = (this.openTimeMillis - System.currentTimeMillis()) / 1000L;
/* 166 */       if (timeLeft < 0L) timeLeft = 0L; 
/* 167 */       sendTgMessage("📦 Текущий Аир-Дроп:\n📍 Координаты: " + this.currentCoords + "\n⏳ Откроется через: " + timeLeft + " сек.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private CompletableFuture<Integer> sendTgMessage(String text) {
/* 172 */     return CompletableFuture.supplyAsync(() -> {
/*     */           try {
/*     */             String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
/*     */             
/*     */             String urlStr = "https://api.telegram.org/bot8667757999:AAFJZNd70pLVVanLLMekD-Db6U6Pmk5b8iI/sendMessage?chat_id=6823107448&text=" + encodedText;
/*     */             
/*     */             HttpURLConnection conn = (HttpURLConnection)(new URL(urlStr)).openConnection();
/*     */             conn.setRequestMethod("GET");
/*     */             int responseCode = conn.getResponseCode();
/*     */             if (responseCode == 200) {
/*     */               JsonObject response = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonObject();
/*     */               System.out.println("[AirDropTg] Сообщение успешно отправлено в ТГ!");
/*     */               return Integer.valueOf(response.getAsJsonObject("result").get("message_id").getAsInt());
/*     */             } 
/*     */             System.out.println("[AirDropTg] Ошибка отправки! Код ответа сервера Telegram: " + responseCode);
/* 187 */           } catch (Exception e) {
/*     */             System.out.println("[AirDropTg] Ошибка в коде при отправке запроса:");
/*     */             e.printStackTrace();
/*     */           } 
/*     */           return Integer.valueOf(-1);
/*     */         });
/*     */   }
/*     */   
/*     */   private void deleteTgMessage(int messageId) {
/* 196 */     CompletableFuture.runAsync(() -> {
/*     */           try {
/*     */             String urlStr = "https://api.telegram.org/bot8667757999:AAFJZNd70pLVVanLLMekD-Db6U6Pmk5b8iI/deleteMessage?chat_id=6823107448&message_id=" + messageId;
/*     */             HttpURLConnection conn = (HttpURLConnection)(new URL(urlStr)).openConnection();
/*     */             conn.setRequestMethod("GET");
/*     */             int code = conn.getResponseCode();
/*     */             if (code == 200) {
/*     */               System.out.println("[AirDropTg] Сообщение " + messageId + " успешно удалено.");
/*     */             } else {
/*     */               System.out.println("[AirDropTg] Не удалось удалить сообщение. Код ошибки ТГ: " + code);
/*     */             } 
/* 207 */           } catch (Exception e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\AirDropTgBot.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */