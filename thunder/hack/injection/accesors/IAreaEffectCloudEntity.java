package thunder.hack.injection.accesors;

import net.minecraft.class_1295;
import net.minecraft.class_1844;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1295.class})
public interface IAreaEffectCloudEntity {
  @Accessor("potionContentsComponent")
  class_1844 getPotionContentsComponent();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IAreaEffectCloudEntity.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */