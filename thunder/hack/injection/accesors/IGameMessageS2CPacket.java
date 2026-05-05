package thunder.hack.injection.accesors;

import net.minecraft.class_2561;
import net.minecraft.class_7439;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_7439.class})
public interface IGameMessageS2CPacket {
  @Mutable
  @Accessor("content")
  void setContent(class_2561 paramclass_2561);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IGameMessageS2CPacket.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */