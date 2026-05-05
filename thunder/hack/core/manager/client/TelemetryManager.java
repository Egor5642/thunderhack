/*    */ package thunder.hack.core.manager.client;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.net.URI;
/*    */ import java.net.http.HttpClient;
/*    */ import java.net.http.HttpRequest;
/*    */ import java.net.http.HttpResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.compress.utils.Lists;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ import thunder.hack.features.modules.client.ClientSettings;
/*    */ import thunder.hack.utility.Timer;
/*    */ 
/*    */ public class TelemetryManager implements IManager {
/* 18 */   private final Timer pingTimer = new Timer();
/* 19 */   private List<String> onlinePlayers = new ArrayList<>();
/* 20 */   private List<String> allPlayers = new ArrayList<>();
/*    */   
/*    */   public void onUpdate() {
/* 23 */     if (this.pingTimer.every(90000L))
/* 24 */       fetchData(); 
/*    */   }
/*    */   
/*    */   public void fetchData() {
/* 28 */     if (((Boolean)ClientSettings.telemetry.getValue()).booleanValue())
/* 29 */       pingServer(mc.method_1548().method_1676()); 
/* 30 */     this.onlinePlayers = getPlayers(true);
/* 31 */     this.allPlayers = getPlayers(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void pingServer(String name) {
/* 37 */     HttpRequest req = HttpRequest.newBuilder(URI.create("https://api.thunderhack.net/v1/users/online?name=" + name)).POST(HttpRequest.BodyPublishers.noBody()).build();
/*    */     
/* 39 */     try { HttpClient client = HttpClient.newHttpClient(); 
/* 40 */       try { client.send(req, HttpResponse.BodyHandlers.ofString());
/* 41 */         if (client != null) client.close();  } catch (Throwable throwable) { if (client != null) try { client.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Throwable throwable) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<String> getPlayers(boolean online) {
/* 48 */     HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.thunderhack.net/v1/users" + (online ? "/online" : ""))).GET().build();
/* 49 */     List<String> names = new ArrayList<>();
/*    */     
/* 51 */     try { HttpClient client = HttpClient.newHttpClient(); 
/* 52 */       try { HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
/* 53 */         JsonArray array = JsonParser.parseString((String)response.body()).getAsJsonArray();
/* 54 */         array.forEach(e -> names.add(e.getAsJsonObject().get("name").getAsString()));
/* 55 */         if (client != null) client.close();  } catch (Throwable throwable) { if (client != null) try { client.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Throwable throwable) {}
/*    */     
/* 57 */     return names;
/*    */   }
/*    */   
/*    */   public List<String> getOnlinePlayers() {
/* 61 */     return Lists.newArrayList(this.onlinePlayers.iterator());
/*    */   }
/*    */   
/*    */   public List<String> getAllPlayers() {
/* 65 */     return Lists.newArrayList(this.allPlayers.iterator());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\client\TelemetryManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */