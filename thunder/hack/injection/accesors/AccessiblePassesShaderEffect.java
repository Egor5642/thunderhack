package thunder.hack.injection.accesors;

import java.util.List;
import net.minecraft.class_279;
import net.minecraft.class_283;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_279.class})
public interface AccessiblePassesShaderEffect {
  @Accessor
  List<class_283> getPasses();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\AccessiblePassesShaderEffect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */