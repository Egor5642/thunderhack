package thunder.hack.utility.render.shaders.satin.api.managed;

import net.minecraft.class_5944;
import thunder.hack.utility.render.shaders.satin.api.managed.uniform.UniformFinder;

public interface ManagedCoreShader extends UniformFinder {
  class_5944 getProgram();
  
  void release();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\api\managed\ManagedCoreShader.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */