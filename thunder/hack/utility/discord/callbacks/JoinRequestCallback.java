package thunder.hack.utility.discord.callbacks;

import com.sun.jna.Callback;
import thunder.hack.utility.discord.DiscordUser;

public interface JoinRequestCallback extends Callback {
  void apply(DiscordUser paramDiscordUser);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\discord\callbacks\JoinRequestCallback.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */