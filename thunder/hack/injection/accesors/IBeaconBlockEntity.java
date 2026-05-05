package thunder.hack.injection.accesors;

import net.minecraft.class_2580;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_2580.class})
public interface IBeaconBlockEntity {
  @Accessor("level")
  int getLevel();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IBeaconBlockEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */