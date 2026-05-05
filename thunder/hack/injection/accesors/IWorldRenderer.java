package thunder.hack.injection.accesors;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.class_3191;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_761.class})
public interface IWorldRenderer {
  @Accessor("blockBreakingInfos")
  Int2ObjectMap<class_3191> getBlockBreakingInfos();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\injection\accesors\IWorldRenderer.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */