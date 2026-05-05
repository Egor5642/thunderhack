package thunder.hack.injection.accesors;

import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1309.class})
public interface ILivingEntity {
  @Accessor("lastAttackedTicks")
  int getLastAttackedTicks();
  
  @Accessor("jumpingCooldown")
  int getLastJumpCooldown();
  
  @Accessor("jumpingCooldown")
  void setLastJumpCooldown(int paramInt);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\ILivingEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */