package thunder.hack.injection.accesors;

import net.minecraft.class_2828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2828.class})
public interface IPlayerMoveC2SPacket {
  @Mutable
  @Accessor("onGround")
  void setOnGround(boolean paramBoolean);
  
  @Mutable
  @Accessor("y")
  void setY(double paramDouble);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IPlayerMoveC2SPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */