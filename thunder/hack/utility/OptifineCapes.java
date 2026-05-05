/*    */ package thunder.hack.utility;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import net.minecraft.class_1011;
/*    */ import net.minecraft.class_1043;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class OptifineCapes
/*    */ {
/*    */   public static void loadPlayerCape(GameProfile player, ReturnCapeTexture response) {
/*    */     try {
/* 25 */       String uuid = player.getId().toString();
/* 26 */       class_1043 nIBT = getCapeFromURL(String.format("http://s.optifine.net/capes/%s.png", new Object[] { player.getName() }));
/* 27 */       class_2960 capeTexture = class_310.method_1551().method_1531().method_4617("th-cape-" + uuid, nIBT);
/* 28 */       response.response(capeTexture);
/* 29 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public static class_1043 getCapeFromURL(String capeStringURL) {
/*    */     try {
/* 35 */       URL capeURL = new URL(capeStringURL);
/* 36 */       return getCapeFromStream(capeURL.openStream());
/* 37 */     } catch (IOException e) {
/* 38 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class_1043 getCapeFromStream(InputStream image) {
/* 43 */     class_1011 cape = null;
/*    */     try {
/* 45 */       cape = class_1011.method_4309(image);
/* 46 */     } catch (IOException e) {
/* 47 */       e.printStackTrace();
/*    */     } 
/* 49 */     if (cape != null) {
/* 50 */       return new class_1043(parseCape(cape));
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */   
/*    */   public static class_1011 parseCape(class_1011 image) {
/* 56 */     int imageWidth = 64;
/* 57 */     int imageHeight = 32;
/* 58 */     int imageSrcWidth = image.method_4307();
/* 59 */     int srcHeight = image.method_4323();
/*    */     
/* 61 */     for (int imageSrcHeight = image.method_4323(); imageWidth < imageSrcWidth || imageHeight < imageSrcHeight; imageHeight *= 2) {
/* 62 */       imageWidth *= 2;
/*    */     }
/*    */     
/* 65 */     class_1011 imgNew = new class_1011(imageWidth, imageHeight, true);
/* 66 */     for (int x = 0; x < imageSrcWidth; x++) {
/* 67 */       for (int y = 0; y < srcHeight; y++) {
/* 68 */         imgNew.method_4305(x, y, image.method_4315(x, y));
/*    */       }
/*    */     } 
/* 71 */     image.close();
/* 72 */     return imgNew;
/*    */   }
/*    */   
/*    */   public static interface ReturnCapeTexture {
/*    */     void response(class_2960 param1class_2960);
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\OptifineCapes.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */