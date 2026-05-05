package thunder.hack.injection.accesors;

import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1927.class})
public interface IExplosion {
  @Mutable
  @Accessor("x")
  void setX(double paramDouble);
  
  @Mutable
  @Accessor("y")
  void setY(double paramDouble);
  
  @Mutable
  @Accessor("z")
  void setZ(double paramDouble);
  
  @Mutable
  @Accessor("entity")
  void setEntity(class_1297 paramclass_1297);
  
  @Mutable
  @Accessor("world")
  void setWorld(class_1937 paramclass_1937);
  
  @Mutable
  @Accessor("world")
  class_1937 getWorld();
  
  @Mutable
  @Accessor("damageSource")
  class_1282 getDamageSource();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IExplosion.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */