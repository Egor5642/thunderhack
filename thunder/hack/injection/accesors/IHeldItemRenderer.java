package thunder.hack.injection.accesors;

import net.minecraft.class_1799;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_759.class})
public interface IHeldItemRenderer {
  @Accessor("equipProgressMainHand")
  void setEquippedProgressMainHand(float paramFloat);
  
  @Accessor("equipProgressOffHand")
  void setEquippedProgressOffHand(float paramFloat);
  
  @Accessor("equipProgressMainHand")
  float getEquippedProgressMainHand();
  
  @Accessor("equipProgressOffHand")
  float getEquippedProgressOffHand();
  
  @Accessor("mainHand")
  void setItemStackMainHand(class_1799 paramclass_1799);
  
  @Accessor("offHand")
  void setItemStackOffHand(class_1799 paramclass_1799);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IHeldItemRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */