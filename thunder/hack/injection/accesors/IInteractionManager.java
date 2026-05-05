package thunder.hack.injection.accesors;

import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_636.class})
public interface IInteractionManager {
  @Accessor("currentBreakingProgress")
  float getCurBlockDamageMP();
  
  @Accessor("currentBreakingProgress")
  void setCurBlockDamageMP(float paramFloat);
  
  @Invoker("syncSelectedSlot")
  void syncSlot();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IInteractionManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */