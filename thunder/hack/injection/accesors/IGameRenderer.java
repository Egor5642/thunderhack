package thunder.hack.injection.accesors;

import net.minecraft.class_4184;
import net.minecraft.class_757;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_757.class})
public interface IGameRenderer {
  @Invoker("renderHand")
  void irenderHand(class_4184 paramclass_4184, float paramFloat, Matrix4f paramMatrix4f);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IGameRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */