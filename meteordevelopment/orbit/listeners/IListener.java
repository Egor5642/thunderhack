package meteordevelopment.orbit.listeners;

public interface IListener {
  void call(Object paramObject);
  
  Class<?> getTarget();
  
  int getPriority();
  
  boolean isStatic();
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\listeners\IListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */