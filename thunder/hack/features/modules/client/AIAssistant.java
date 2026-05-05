/*    */ package thunder.hack.features.modules.client;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.net.http.HttpClient;
/*    */ import java.net.http.HttpRequest;
/*    */ import java.net.http.HttpResponse;
/*    */ import meteordevelopment.orbit.EventHandler;
/*    */ import net.minecraft.class_2596;
/*    */ import net.minecraft.class_2797;
/*    */ import thunder.hack.core.Managers;
/*    */ import thunder.hack.events.impl.PacketEvent;
/*    */ import thunder.hack.features.modules.Module;
/*    */ import thunder.hack.setting.Setting;
/*    */ 
/*    */ public class AIAssistant
/*    */   extends Module {
/* 17 */   public final Setting<String> prefix = new Setting("Prefix", "!");
/*    */ 
/*    */   
/*    */   public AIAssistant() {
/* 21 */     super("AIAssistant", Module.Category.CLIENT);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPacketSend(PacketEvent.Send e) {
/* 26 */     if (fullNullCheck()) {
/*    */       return;
/*    */     }
/* 29 */     class_2596 class_2596 = e.getPacket(); if (class_2596 instanceof class_2797) { class_2797 pac = (class_2797)class_2596;
/* 30 */       String message = pac.comp_945();
/*    */ 
/*    */       
/* 33 */       if (message.startsWith((String)this.prefix.getValue())) {
/* 34 */         e.cancel();
/*    */         
/* 36 */         String question = message.substring(((String)this.prefix.getValue()).length()).trim();
/* 37 */         askAI(question);
/*    */       }  }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   private void askAI(String question) {
/* 44 */     sendMessage("§b[AI] §7Думаю...");
/*    */ 
/*    */     
/* 47 */     Managers.ASYNC.run(() -> {
/*    */           try {
/*    */             HttpClient client = HttpClient.newHttpClient();
/*    */ 
/*    */             
/*    */             String url = "https://api.pawan.krd/v1/chat/completions";
/*    */ 
/*    */             
/*    */             String safeQuestion = question.replace("\"", "\\\"");
/*    */ 
/*    */             
/*    */             String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + safeQuestion + "\"}]}";
/*    */ 
/*    */             
/*    */             HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();
/*    */ 
/*    */             
/*    */             HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
/*    */             
/*    */             sendMessage("§b[AI] §fЗапрос обработан. Статус: " + response.statusCode());
/* 67 */           } catch (Exception ex) {
/*    */             sendMessage("§c[AI] Ошибка: " + ex.getMessage());
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\features\modules\client\AIAssistant.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */