package thunder.hack.utility.render.shaders.satin.impl;

import java.util.List;

public interface SamplerAccess {
  boolean hasSampler(String paramString);
  
  List<String> getSamplerNames();
  
  List<Integer> getSamplerShaderLocs();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\SamplerAccess.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */