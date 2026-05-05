package thunder.hack.injection.accesors;

import net.minecraft.class_2625;
import net.minecraft.class_7743;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_7743.class})
public interface ISignEditScreen {
  @Accessor("blockEntity")
  class_2625 getBlockEntity();
  
  @Accessor("front")
  boolean isFront();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\ISignEditScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */