/*     */ package thunder.hack.utility;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import net.fabricmc.loader.api.metadata.Person;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1043;
/*     */ import net.minecraft.class_124;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_634;
/*     */ import net.minecraft.class_640;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import thunder.hack.ThunderHack;
/*     */ import thunder.hack.core.manager.client.ConfigManager;
/*     */ import thunder.hack.features.modules.Module;
/*     */ import thunder.hack.utility.math.MathUtility;
/*     */ 
/*     */ public final class ThunderUtility {
/*  30 */   public static List<String> changeLog = new ArrayList<>();
/*  31 */   public static List<String> starGazer = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String getAuthors() {
/*  37 */     List<String> names = ThunderHack.MOD_META.getAuthors().stream().map(Person::getName).toList();
/*     */     
/*  39 */     return String.join(", ", (Iterable)names);
/*     */   }
/*     */   
/*     */   public static String solveName(String notSolved) {
/*  43 */     AtomicReference<String> mb = new AtomicReference<>("FATAL ERROR");
/*  44 */     ((class_634)Objects.<class_634>requireNonNull(Module.mc.method_1562())).method_45732().forEach(player -> {
/*     */           if (notSolved.contains(player.method_2966().getName())) {
/*     */             mb.set(player.method_2966().getName());
/*     */           }
/*     */         });
/*     */     
/*  50 */     return mb.get();
/*     */   }
/*     */   
/*     */   public static class_2960 getCustomImg(String name) throws IOException {
/*  54 */     return Module.mc.method_1531().method_4617("th-" + name + "-" + (int)MathUtility.random(0.0F, 1000.0F), new class_1043(class_1011.method_4309(new FileInputStream(String.valueOf(ConfigManager.IMAGES_FOLDER) + "/" + String.valueOf(ConfigManager.IMAGES_FOLDER) + ".png"))));
/*     */   }
/*     */   
/*     */   public static void syncVersion() {
/*     */     try {
/*  59 */       if (!(new BufferedReader(new InputStreamReader((new URL("https://raw.githubusercontent.com/Pan4ur/THRecodeUtil/main/syncVersion121.txt")).openStream()))).readLine().equals("1.7b2407"))
/*  60 */         ThunderHack.isOutdated = true; 
/*  61 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static void parseStarGazer() {
/*  66 */     List<String> starGazers = new ArrayList<>();
/*     */     
/*     */     try {
/*  69 */       for (int page = 1; page <= 3; page++) {
/*  70 */         URL url = new URL("https://api.github.com/repos/Pan4ur/ThunderHack-Recode/stargazers?per_page=100&page=" + page);
/*  71 */         BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/*  72 */         StringBuilder response = new StringBuilder();
/*     */         
/*     */         String inputLine;
/*  75 */         while ((inputLine = in.readLine()) != null) {
/*  76 */           response.append(inputLine);
/*     */         }
/*  78 */         in.close();
/*     */         
/*  80 */         JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
/*  81 */         for (int i = 0; i < jsonArray.size(); i++) {
/*  82 */           JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
/*  83 */           starGazers.add(jsonObject.getAsJsonPrimitive("login").getAsString());
/*     */         } 
/*     */         
/*  86 */         Thread.sleep(1500L);
/*     */       } 
/*  88 */     } catch (Exception e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void syncContributors() {
/*     */     try {
/*  95 */       URL list = new URL("https://raw.githubusercontent.com/Pan4ur/THRecodeUtil/main/thTeam.txt");
/*  96 */       BufferedReader in = new BufferedReader(new InputStreamReader(list.openStream(), StandardCharsets.UTF_8));
/*     */       
/*  98 */       int i = 0; String inputLine;
/*  99 */       while ((inputLine = in.readLine()) != null) {
/* 100 */         ThunderHack.contributors[i] = inputLine.trim();
/* 101 */         i++;
/*     */       } 
/* 103 */       in.close();
/* 104 */     } catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String readManifestField(String fieldName) {
/*     */     try {
/* 111 */       Enumeration<URL> en = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
/* 112 */       while (en.hasMoreElements()) {
/*     */         try {
/* 114 */           URL url = en.nextElement();
/* 115 */           InputStream is = url.openStream();
/* 116 */           if (is != null) {
/* 117 */             String s = (new Manifest(is)).getMainAttributes().getValue(fieldName);
/* 118 */             if (s != null)
/* 119 */               return s; 
/*     */           } 
/* 121 */         } catch (Exception exception) {}
/*     */       }
/*     */     
/* 124 */     } catch (Exception exception) {}
/*     */     
/* 126 */     return "0";
/*     */   }
/*     */ 
/*     */   
/*     */   public static void parseCommits() {
/*     */     try {
/* 132 */       URL url = new URL("https://api.github.com/repos/Pan4ur/ThunderHack-Recode/commits?per_page=50");
/* 133 */       BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
/*     */       
/* 135 */       changeLog.add("Changelog [Recode; Date: " + ThunderHack.BUILD_DATE + "; GitHash:" + ThunderHack.GITHUB_HASH + "]");
/* 136 */       changeLog.add("\n");
/*     */       
/*     */       String inputLine;
/* 139 */       while ((inputLine = in.readLine()) != null) {
/* 140 */         JsonArray jsonArray = JsonParser.parseString(inputLine).getAsJsonArray();
/*     */         
/* 142 */         for (int i = 0; i < jsonArray.size(); i++) {
/* 143 */           JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
/* 144 */           JsonObject commitObject = jsonObject.getAsJsonObject("commit");
/* 145 */           JsonObject authorObject = commitObject.getAsJsonObject("author");
/*     */           
/* 147 */           String name = authorObject.get("name").getAsString().replace("\n", "");
/* 148 */           String date = authorObject.get("date").getAsString().replace("\n", "");
/* 149 */           String info = commitObject.get("message").getAsString().replace("\n", "");
/*     */           
/* 151 */           if (!name.contains("ImgBot") && !info.startsWith("Merge") && !info.startsWith("Revert")) {
/*     */ 
/*     */ 
/*     */             
/* 155 */             String formattedDate = String.valueOf(class_124.field_1080) + String.valueOf(class_124.field_1080) + date.split("T")[0];
/* 156 */             String formattedName = "@" + String.valueOf(class_124.field_1061) + name + String.valueOf(class_124.field_1070);
/*     */             
/* 158 */             changeLog.add("- " + info + " [" + formattedDate + "]  (" + formattedName + ")");
/*     */           } 
/*     */         } 
/* 161 */       }  in.close();
/* 162 */     } catch (Exception e) {
/* 163 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\ThunderUtility.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */