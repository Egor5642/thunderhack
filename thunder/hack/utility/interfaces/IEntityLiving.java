package thunder.hack.utility.interfaces;

import java.util.List;
import thunder.hack.features.modules.combat.Aura;

public interface IEntityLiving {
  double getPrevServerX();
  
  double getPrevServerY();
  
  double getPrevServerZ();
  
  List<Aura.Position> getPositionHistory();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\interfaces\IEntityLiving.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */