/*    */ package thunder.hack.utility.discord;
/*    */ 
/*    */ import com.sun.jna.Structure;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DiscordUser extends Structure {
/*    */   public String userId;
/*    */   public String username;
/*    */   @Deprecated
/*    */   public String discriminator;
/*    */   public String avatar;
/*    */   
/*    */   protected List<String> getFieldOrder() {
/* 15 */     return Arrays.asList(new String[] { "userId", "username", "discriminator", "avatar" });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\discord\DiscordUser.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */