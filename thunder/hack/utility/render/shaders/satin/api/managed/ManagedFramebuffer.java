package thunder.hack.utility.render.shaders.satin.api.managed;

import net.minecraft.class_276;

public interface ManagedFramebuffer {
  class_276 getFramebuffer();
  
  void beginWrite(boolean paramBoolean);
  
  void draw();
  
  void draw(int paramInt1, int paramInt2, boolean paramBoolean);
  
  void clear();
  
  void clear(boolean paramBoolean);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\api\managed\ManagedFramebuffer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */