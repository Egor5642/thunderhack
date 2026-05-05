package thunder.hack.utility.render.shaders.satin.api.managed.uniform;

public interface UniformFinder {
  Uniform1i findUniform1i(String paramString);
  
  Uniform2i findUniform2i(String paramString);
  
  Uniform3i findUniform3i(String paramString);
  
  Uniform4i findUniform4i(String paramString);
  
  Uniform1f findUniform1f(String paramString);
  
  Uniform2f findUniform2f(String paramString);
  
  Uniform3f findUniform3f(String paramString);
  
  Uniform4f findUniform4f(String paramString);
  
  UniformMat4 findUniformMat4(String paramString);
  
  SamplerUniform findSampler(String paramString);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\api\manage\\uniform\UniformFinder.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */