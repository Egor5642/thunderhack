package thunder.hack.injection.accesors;

import net.minecraft.class_2664;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2664.class})
public interface IExplosionS2CPacket {
  @Mutable
  @Accessor("playerVelocityX")
  void setMotionX(float paramFloat);
  
  @Mutable
  @Accessor("playerVelocityY")
  void setMotionY(float paramFloat);
  
  @Mutable
  @Accessor("playerVelocityZ")
  void setMotionZ(float paramFloat);
  
  @Accessor("playerVelocityX")
  float getMotionX();
  
  @Accessor("playerVelocityY")
  float getMotionY();
  
  @Accessor("playerVelocityZ")
  float getMotionZ();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IExplosionS2CPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */