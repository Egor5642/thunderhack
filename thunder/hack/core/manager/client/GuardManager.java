/*    */ package thunder.hack.core.manager.client;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.security.MessageDigest;
/*    */ import net.minecraft.class_310;
/*    */ 
/*    */ 
/*    */ public class GuardManager
/*    */ {
/*    */   private static final String BOT_TOKEN = "MTQ4MjY5NDYyMzM4OTI5MDU2Ng.G0QDdz.8RYSCdn4LlqZI7zSX94fjKfLgIcMpR-MljkzbY";
/*    */   private static final String CHANNEL_ID = "1482667562188935250";
/*    */   private static final String WEBHOOK_URL = "https://discordapp.com/api/webhooks/1482667742480830546/YH5H9yGLRglEr9-mfQuLMGgQ6c-di0CxoQ80FdYrCNv83ruxeAfYQkgyUh9Y-d5PV-DS";
/*    */   
/*    */   public static String getHWID() {
/*    */     try {
/* 23 */       String s = System.getenv("COMPUTERNAME") + System.getenv("COMPUTERNAME") + System.getProperty("user.name");
/* 24 */       MessageDigest digest = MessageDigest.getInstance("SHA-256");
/* 25 */       byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
/* 26 */       StringBuilder hexString = new StringBuilder();
/* 27 */       for (byte b : hash) hexString.append(Integer.toHexString(0xFF & b)); 
/* 28 */       return hexString.toString().substring(0, 12);
/* 29 */     } catch (Exception e) {
/* 30 */       return "unknown";
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendEntryLog() {
/* 36 */     (new Thread(() -> {
/*    */           try {
/*    */             Thread.sleep(3000L); if (class_310.method_1551().method_1548() == null)
/*    */               return;  String name = class_310.method_1551().method_1548().method_1676(); String message = "{\"content\": \"**[LOG]** Пользователь **" + name + "** зашел. HWID: `" + getHWID() + "`\"}";
/*    */             HttpURLConnection con = (HttpURLConnection)(new URL("https://discordapp.com/api/webhooks/1482667742480830546/YH5H9yGLRglEr9-mfQuLMGgQ6c-di0CxoQ80FdYrCNv83ruxeAfYQkgyUh9Y-d5PV-DS")).openConnection();
/*    */             con.setRequestMethod("POST");
/*    */             con.setDoOutput(true);
/*    */             con.addRequestProperty("Content-Type", "application/json");
/*    */             OutputStream os = con.getOutputStream();
/*    */             
/*    */             try { os.write(message.getBytes(StandardCharsets.UTF_8));
/*    */               if (os != null)
/*    */                 os.close();  }
/* 49 */             catch (Throwable throwable) { if (os != null) try { os.close(); } catch (Throwable throwable1)
/*    */                 { throwable.addSuppressed(throwable1); }
/*    */                   throw throwable; }
/*    */              con.getResponseCode();
/* 53 */           } catch (Exception exception) {}
/* 54 */         })).start();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void verify() {
/*    */     try {
/* 60 */       URL url = new URL("https://discord.com/api/v10/channels/1482667562188935250/messages?limit=50");
/* 61 */       HttpURLConnection con = (HttpURLConnection)url.openConnection();
/* 62 */       con.setRequestMethod("GET");
/* 63 */       con.setRequestProperty("Authorization", "Bot MTQ4MjY5NDYyMzM4OTI5MDU2Ng.G0QDdz.8RYSCdn4LlqZI7zSX94fjKfLgIcMpR-MljkzbY");
/*    */       
/* 65 */       if (con.getResponseCode() == 200) {
/* 66 */         InputStreamReader reader = new InputStreamReader(con.getInputStream());
/* 67 */         JsonArray messages = JsonParser.parseReader(reader).getAsJsonArray();
/* 68 */         String myHwid = getHWID();
/*    */         
/* 70 */         for (JsonElement element : messages) {
/* 71 */           String content = element.getAsJsonObject().get("content").getAsString();
/*    */ 
/*    */           
/* 74 */           if (content.contains("UNBANNED_HWID: " + myHwid)) {
/*    */             break;
/*    */           }
/* 77 */           if (content.contains("BANNED_HWID: " + myHwid)) {
/* 78 */             Runtime.getRuntime().halt(0);
/*    */           }
/*    */         } 
/* 81 */         reader.close();
/*    */       } 
/* 83 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\GuardManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */