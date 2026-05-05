/*    */ package thunder.hack.core.manager.player;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_1657;
/*    */ import net.minecraft.class_742;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import thunder.hack.core.manager.IManager;
/*    */ 
/*    */ public class FriendManager implements IManager {
/* 13 */   public static List<String> friends = new ArrayList<>();
/*    */   
/*    */   public boolean isFriend(String name) {
/* 16 */     return friends.stream().anyMatch(friend -> friend.equalsIgnoreCase(name));
/*    */   }
/*    */   
/*    */   public boolean isFriend(@NotNull class_1657 player) {
/* 20 */     return isFriend(player.method_5477().getString());
/*    */   }
/*    */   
/*    */   public void removeFriend(String name) {
/* 24 */     friends.remove(name);
/*    */   }
/*    */   
/*    */   public void addFriend(String friend) {
/* 28 */     friends.add(friend);
/*    */   }
/*    */   
/*    */   public List<String> getFriends() {
/* 32 */     return friends;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 36 */     friends.clear();
/*    */   }
/*    */   
/*    */   public List<class_742> getNearFriends() {
/* 40 */     if (mc.field_1687 == null) return new ArrayList<>();
/*    */     
/* 42 */     return mc.field_1687.method_18456().stream()
/* 43 */       .filter(player -> friends.contains(player.method_5477().getString()))
/* 44 */       .toList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveFriends() {
/* 49 */     File file = new File("ThunderHackRecode/misc/friends.txt");
/*    */     try {
/* 51 */       file.createNewFile();
/* 52 */     } catch (Exception exception) {}
/*    */ 
/*    */     
/* 55 */     try { BufferedWriter writer = new BufferedWriter(new FileWriter(file)); 
/* 56 */       try { for (String friend : friends)
/* 57 */           writer.write(friend + "\n"); 
/* 58 */         writer.close(); } catch (Throwable throwable) { try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadFriends() {
/*    */     try {
/* 64 */       File file = new File("ThunderHackRecode/misc/friends.txt");
/*    */       
/* 66 */       if (file.exists()) {
/* 67 */         BufferedReader reader = new BufferedReader(new FileReader(file)); 
/* 68 */         try { while (reader.ready())
/* 69 */             friends.add(reader.readLine()); 
/* 70 */           reader.close(); } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
/*    */       } 
/* 72 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\core\manager\player\FriendManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */