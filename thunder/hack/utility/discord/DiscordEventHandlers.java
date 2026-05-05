/*    */ package thunder.hack.utility.discord;
/*    */ 
/*    */ import com.sun.jna.Structure;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import thunder.hack.utility.discord.callbacks.DisconnectedCallback;
/*    */ import thunder.hack.utility.discord.callbacks.ErroredCallback;
/*    */ import thunder.hack.utility.discord.callbacks.JoinGameCallback;
/*    */ import thunder.hack.utility.discord.callbacks.JoinRequestCallback;
/*    */ import thunder.hack.utility.discord.callbacks.ReadyCallback;
/*    */ import thunder.hack.utility.discord.callbacks.SpectateGameCallback;
/*    */ 
/*    */ public class DiscordEventHandlers extends Structure {
/*    */   public DisconnectedCallback disconnected;
/*    */   public JoinRequestCallback joinRequest;
/*    */   public SpectateGameCallback spectateGame;
/*    */   public ReadyCallback ready;
/*    */   public ErroredCallback errored;
/*    */   public JoinGameCallback joinGame;
/*    */   
/*    */   protected List<String> getFieldOrder() {
/* 22 */     return Arrays.asList(new String[] { "ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest" });
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\discord\DiscordEventHandlers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */