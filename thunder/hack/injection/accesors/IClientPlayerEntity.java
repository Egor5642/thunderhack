package thunder.hack.injection.accesors;

import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_746.class})
public interface IClientPlayerEntity {
  @Invoker("sendMovementPackets")
  void iSendMovementPackets();
  
  @Accessor("lastYaw")
  float getLastYaw();
  
  @Accessor("lastPitch")
  float getLastPitch();
  
  @Accessor("lastYaw")
  void setLastYaw(float paramFloat);
  
  @Accessor("lastPitch")
  void setLastPitch(float paramFloat);
  
  @Accessor("mountJumpStrength")
  void setMountJumpStrength(float paramFloat);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IClientPlayerEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */