package thunder.hack.injection.accesors;

import net.minecraft.class_4604;
import org.joml.FrustumIntersection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_4604.class})
public interface IFrustum {
  @Accessor
  FrustumIntersection getFrustumIntersection();
  
  @Accessor("x")
  double getX();
  
  @Accessor("x")
  void setX(double paramDouble);
  
  @Accessor("y")
  double getY();
  
  @Accessor("y")
  void setY(double paramDouble);
  
  @Accessor("z")
  double getZ();
  
  @Accessor("z")
  void setZ(double paramDouble);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IFrustum.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */