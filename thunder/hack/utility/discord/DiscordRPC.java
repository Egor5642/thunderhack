/*   */ package thunder.hack.utility.discord;
/*   */ 
/*   */ import com.sun.jna.Library;
/*   */ import com.sun.jna.Native;
/*   */ 
/*   */ public interface DiscordRPC extends Library {
/* 7 */   public static final DiscordRPC INSTANCE = (DiscordRPC)Native.load("discord-rpc", DiscordRPC.class);
/*   */   
/*   */   void Discord_UpdateHandlers(DiscordEventHandlers paramDiscordEventHandlers);
/*   */   
/*   */   void Discord_UpdatePresence(DiscordRichPresence paramDiscordRichPresence);
/*   */   
/*   */   void Discord_Respond(String paramString, int paramInt);
/*   */   
/*   */   void Discord_Register(String paramString1, String paramString2);
/*   */   
/*   */   void Discord_Shutdown();
/*   */   
/*   */   void Discord_UpdateConnection();
/*   */   
/*   */   void Discord_RegisterSteamGame(String paramString1, String paramString2);
/*   */   
/*   */   void Discord_RunCallbacks();
/*   */   
/*   */   void Discord_Initialize(String paramString1, DiscordEventHandlers paramDiscordEventHandlers, boolean paramBoolean, String paramString2);
/*   */   
/*   */   void Discord_ClearPresence();
/*   */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\discord\DiscordRPC.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */