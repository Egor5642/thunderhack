package meteordevelopment.orbit;

import meteordevelopment.orbit.listeners.IListener;
import meteordevelopment.orbit.listeners.LambdaListener;

public interface IEventBus {
  void registerLambdaFactory(String paramString, LambdaListener.Factory paramFactory);
  
  <T> T post(T paramT);
  
  <T extends ICancellable> T post(T paramT);
  
  void subscribe(Object paramObject);
  
  void subscribe(Class<?> paramClass);
  
  void subscribe(IListener paramIListener);
  
  void unsubscribe(Object paramObject);
  
  void unsubscribe(Class<?> paramClass);
  
  void unsubscribe(IListener paramIListener);
}


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\IEventBus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */