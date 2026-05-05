package thunder.hack.injection.accesors;

import com.mojang.authlib.minecraft.UserApiService;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_5520;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_310.class})
public interface IMinecraftClient {
  @Accessor("itemUseCooldown")
  int getUseCooldown();
  
  @Accessor("itemUseCooldown")
  void setUseCooldown(int paramInt);
  
  @Invoker("doItemUse")
  void idoItemUse();
  
  @Invoker("doAttack")
  boolean idoAttack();
  
  @Mutable
  @Accessor("profileKeys")
  void setProfileKeys(class_7853 paramclass_7853);
  
  @Mutable
  @Accessor("session")
  void setSessionT(class_320 paramclass_320);
  
  @Mutable
  @Accessor
  void setUserApiService(UserApiService paramUserApiService);
  
  @Mutable
  @Accessor("socialInteractionsManager")
  void setSocialInteractionsManagerT(class_5520 paramclass_5520);
  
  @Mutable
  @Accessor("abuseReportContext")
  void setAbuseReportContextT(class_7574 paramclass_7574);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IMinecraftClient.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */