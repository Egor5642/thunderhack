package thunder.hack.injection.accesors;

import net.minecraft.class_2708;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2708.class})
public interface IPlayerPositionLookS2CPacket {
  @Mutable
  @Accessor("yaw")
  void setYaw(float paramFloat);
  
  @Mutable
  @Accessor("pitch")
  void setPitch(float paramFloat);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IPlayerPositionLookS2CPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */