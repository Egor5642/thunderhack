package thunder.hack.injection.accesors;

import net.minecraft.class_1011;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1011.class})
public interface INativeImage {
  @Accessor("pointer")
  long getPointer();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\INativeImage.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */