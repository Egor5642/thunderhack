package thunder.hack.injection.accesors;

import net.minecraft.class_2708;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2708.class})
public interface ISPacketPlayerPosLook {
  @Accessor("yaw")
  void setYaw(float paramFloat);
  
  @Accessor("pitch")
  void setPitch(float paramFloat);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\ISPacketPlayerPosLook.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */