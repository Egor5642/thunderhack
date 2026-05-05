package thunder.hack.injection.accesors;

import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1297.class})
public interface IEntity {
  @Mutable
  @Accessor("pos")
  void setPos(class_243 paramclass_243);
  
  @Mutable
  @Accessor("blockPos")
  void setBlockPos(class_2338 paramclass_2338);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */