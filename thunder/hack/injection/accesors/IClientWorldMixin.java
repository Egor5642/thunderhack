package thunder.hack.injection.accesors;

import net.minecraft.class_638;
import net.minecraft.class_7202;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_638.class})
public interface IClientWorldMixin {
  @Accessor("pendingUpdateManager")
  class_7202 getPendingUpdateManager();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IClientWorldMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */