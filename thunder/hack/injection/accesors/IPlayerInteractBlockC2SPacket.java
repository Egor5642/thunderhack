package thunder.hack.injection.accesors;

import net.minecraft.class_1268;
import net.minecraft.class_2885;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2885.class})
public interface IPlayerInteractBlockC2SPacket {
  @Mutable
  @Accessor("hand")
  void setHand(class_1268 paramclass_1268);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IPlayerInteractBlockC2SPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */