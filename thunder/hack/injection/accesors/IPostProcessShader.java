package thunder.hack.injection.accesors;

import net.minecraft.class_276;
import net.minecraft.class_283;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_283.class})
public interface IPostProcessShader {
  @Mutable
  @Accessor("input")
  void setInput(class_276 paramclass_276);
  
  @Mutable
  @Accessor("output")
  void setOutput(class_276 paramclass_276);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IPostProcessShader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */