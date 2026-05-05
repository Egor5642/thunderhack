package thunder.hack.injection.accesors;

import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_4668.class})
public interface IRenderPhaseMixin {
  @Accessor("name")
  String getName();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IRenderPhaseMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */