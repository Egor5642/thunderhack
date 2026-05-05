package thunder.hack.injection.accesors;

import net.minecraft.class_2540;
import net.minecraft.class_2824;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_2824.class})
public interface IPlayerInteractEntityC2SPacket {
  @Invoker("write")
  void write(class_2540 paramclass_2540);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IPlayerInteractEntityC2SPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */