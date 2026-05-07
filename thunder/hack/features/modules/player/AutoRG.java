package thunder.hack.features.modules.player;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_3965;
import thunder.hack.events.impl.EventTick;
import thunder.hack.features.modules.Module;
import thunder.hack.utility.player.InventoryUtility;
import thunder.hack.utility.player.SearchInvResult;

public final class AutoRG extends Module {
  private final class_2338.class_2339 mut = new class_2338.class_2339();
  private final class_2338.class_2339 neighborMut = new class_2338.class_2339();
  
  private static final class_2350[] FAST_DIRS = new class_2350[] { class_2350.field_11036, class_2350.field_11043, class_2350.field_11035, class_2350.field_11034, class_2350.field_11039, class_2350.field_11033 };
  
  public AutoRG() {
    super("AutoRG", Module.Category.PLAYER);
  }

  @EventHandler
  public void onTick(EventTick event) {
    if (mc.field_1724 == null || mc.field_1687 == null || mc.field_1761 == null)
      return; 
    SearchInvResult res = InventoryUtility.findBlockInHotBar(new class_2248[] { class_2246.field_10201 });
    if (!res.found())
      return; 
    int pX = mc.field_1724.method_31477();
    int pY = mc.field_1724.method_31478();
    int pZ = mc.field_1724.method_31479();

    // Проверяем коробку 5x5 снаружи (3x3 внутри) из обсидиана
    if (!isObsidianBox(pX, pY, pZ))
      return;
    
    int minX = pX - 2, maxX = pX + 2;
    int minY = pY - 2, maxY = pY + 2;
    int minZ = pZ - 2, maxZ = pZ + 2;
    
    // Проверяем наличие угольного блока внутри 3x3
    for (int x = minX + 1; x < maxX; x++) {
      for (int y = minY + 1; y < maxY; y++) {
        for (int z = minZ + 1; z < maxZ; z++) {
          class_2248 block = mc.field_1687.method_8320((class_2338)this.mut.method_10103(x, y, z)).method_26204();
          if (block == class_2246.field_10201)
            return;
        }
      }
    }
    
    // Ищем первое свободное место и ставим максимально быстро
    for (int x = minX + 1; x < maxX; x++) {
      for (int y = minY + 1; y < maxY; y++) {
        for (int z = minZ + 1; z < maxZ; z++) {
          class_2248 block = mc.field_1687.method_8320((class_2338)this.mut.method_10103(x, y, z)).method_26204();
          if (!block.method_9564().method_45474())
            continue;
      
          // Ставим блок без проверок безопасности
          placeBlockFast(x, y, z, res.slot());
          return;
        }
      }
    }
  }
  
  private void placeBlockFast(int x, int y, int z, int slot) {
    InventoryUtility.saveAndSwitchTo(slot);
    
    for (class_2350 dir : FAST_DIRS) {
      this.neighborMut.method_10103(x + dir.method_10148(), y + dir.method_10164(), z + dir.method_10165());
      if (!mc.field_1687.method_8320((class_2338)this.neighborMut).method_45474()) {
        class_3965 hitResult = new class_3965(class_243.method_24953((class_2382)this.neighborMut), dir.method_10153(), (class_2338)this.neighborMut, false);
        mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, hitResult);
        mc.field_1724.method_6104(class_1268.field_5808);
        break;
      }
    }
    
    InventoryUtility.returnSlot();
  }
  
  private boolean isObsidianBox(int x, int y, int z) {
    // Проверяем стены 5x5 вокруг игрока (только обсидиан)
    for (int dy = -2; dy <= 2; dy++) {
      for (int dx = -2; dx <= 2; dx++) {
        if (!isObsidian(x + dx, y + dy, z - 2)) return false;
        if (!isObsidian(x + dx, y + dy, z + 2)) return false;
      }
      for (int dz = -2; dz <= 2; dz++) {
        if (!isObsidian(x - 2, y + dy, z + dz)) return false;
        if (!isObsidian(x + 2, y + dy, z + dz)) return false;
      }
    }
    return true;
  }
  
  private boolean isObsidian(int x, int y, int z) {
    class_2248 b = mc.field_1687.method_8320((class_2338)this.mut.method_10103(x, y, z)).method_26204();
    return (b == class_2246.field_10540);
  }
}
