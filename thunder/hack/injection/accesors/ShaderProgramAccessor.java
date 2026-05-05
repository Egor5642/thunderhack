package thunder.hack.injection.accesors;

import java.util.Map;
import net.minecraft.class_284;
import net.minecraft.class_5944;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_5944.class})
public interface ShaderProgramAccessor {
  @Accessor("loadedUniforms")
  Map<String, class_284> getUniformsHook();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\ShaderProgramAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */