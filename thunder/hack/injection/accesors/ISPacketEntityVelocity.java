package thunder.hack.injection.accesors;

import net.minecraft.class_2743;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2743.class})
public interface ISPacketEntityVelocity {
  @Mutable
  @Accessor("velocityX")
  void setMotionX(int paramInt);
  
  @Mutable
  @Accessor("velocityY")
  void setMotionY(int paramInt);
  
  @Mutable
  @Accessor("velocityZ")
  void setMotionZ(int paramInt);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\ISPacketEntityVelocity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */