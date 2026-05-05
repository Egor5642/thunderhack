/*     */ package meteordevelopment.orbit;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.function.Function;
/*     */ import meteordevelopment.orbit.listeners.IListener;
/*     */ import meteordevelopment.orbit.listeners.LambdaListener;
/*     */ 
/*     */ 
/*     */ public class EventBus
/*     */   implements IEventBus
/*     */ {
/*     */   private static class LambdaFactoryInfo
/*     */   {
/*     */     public final String packagePrefix;
/*     */     public final LambdaListener.Factory factory;
/*     */     
/*     */     public LambdaFactoryInfo(String packagePrefix, LambdaListener.Factory factory) {
/*  23 */       this.packagePrefix = packagePrefix;
/*  24 */       this.factory = factory;
/*     */     }
/*     */   }
/*     */   
/*  28 */   private final Map<Object, List<IListener>> listenerCache = new ConcurrentHashMap<>();
/*  29 */   private final Map<Class<?>, List<IListener>> staticListenerCache = new ConcurrentHashMap<>();
/*     */   
/*  31 */   private final Map<Class<?>, List<IListener>> listenerMap = new ConcurrentHashMap<>();
/*     */   
/*  33 */   private final List<LambdaFactoryInfo> lambdaFactoryInfos = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void registerLambdaFactory(String packagePrefix, LambdaListener.Factory factory) {
/*  37 */     synchronized (this.lambdaFactoryInfos) {
/*  38 */       this.lambdaFactoryInfos.add(new LambdaFactoryInfo(packagePrefix, factory));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T post(T event) {
/*  44 */     List<IListener> listeners = this.listenerMap.get(event.getClass());
/*     */     
/*  46 */     if (listeners != null) {
/*  47 */       for (IListener listener : listeners) listener.call(event);
/*     */     
/*     */     }
/*  50 */     return event;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ICancellable> T post(T event) {
/*  55 */     List<IListener> listeners = this.listenerMap.get(event.getClass());
/*     */     
/*  57 */     if (listeners != null) {
/*  58 */       event.setCancelled(false);
/*     */       
/*  60 */       for (IListener listener : listeners) {
/*  61 */         listener.call(event);
/*  62 */         if (event.isCancelled())
/*     */           break; 
/*     */       } 
/*     */     } 
/*  66 */     return event;
/*     */   }
/*     */ 
/*     */   
/*     */   public void subscribe(Object object) {
/*  71 */     subscribe(getListeners(object.getClass(), object), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void subscribe(Class<?> klass) {
/*  76 */     subscribe(getListeners(klass, null), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void subscribe(IListener listener) {
/*  81 */     subscribe(listener, false);
/*     */   }
/*     */   
/*     */   private void subscribe(List<IListener> listeners, boolean onlyStatic) {
/*  85 */     for (IListener listener : listeners) subscribe(listener, onlyStatic); 
/*     */   }
/*     */   
/*     */   private void subscribe(IListener listener, boolean onlyStatic) {
/*  89 */     if (onlyStatic) {
/*  90 */       if (listener.isStatic()) insert(this.listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList()), listener);
/*     */     
/*     */     } else {
/*  93 */       insert(this.listenerMap.computeIfAbsent(listener.getTarget(), aClass -> new CopyOnWriteArrayList()), listener);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void insert(List<IListener> listeners, IListener listener) {
/*  98 */     int i = 0;
/*  99 */     for (; i < listeners.size() && 
/* 100 */       listener.getPriority() <= ((IListener)listeners.get(i)).getPriority(); i++);
/*     */ 
/*     */     
/* 103 */     listeners.add(i, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsubscribe(Object object) {
/* 108 */     unsubscribe(getListeners(object.getClass(), object), false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsubscribe(Class<?> klass) {
/* 113 */     unsubscribe(getListeners(klass, null), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsubscribe(IListener listener) {
/* 118 */     unsubscribe(listener, false);
/*     */   }
/*     */   
/*     */   private void unsubscribe(List<IListener> listeners, boolean staticOnly) {
/* 122 */     for (IListener listener : listeners) unsubscribe(listener, staticOnly); 
/*     */   }
/*     */   
/*     */   private void unsubscribe(IListener listener, boolean staticOnly) {
/* 126 */     List<IListener> l = this.listenerMap.get(listener.getTarget());
/*     */     
/* 128 */     if (l != null)
/* 129 */       if (staticOnly) {
/* 130 */         if (listener.isStatic()) l.remove(listener); 
/*     */       } else {
/* 132 */         l.remove(listener);
/*     */       }  
/*     */   }
/*     */   
/*     */   private List<IListener> getListeners(Class<?> klass, Object object) {
/* 137 */     Function<Object, List<IListener>> func = o -> {
/*     */         List<IListener> listeners = new CopyOnWriteArrayList<>();
/*     */         
/*     */         getListeners(listeners, klass, object);
/*     */         
/*     */         return listeners;
/*     */       };
/*     */     
/* 145 */     if (object == null) return this.staticListenerCache.computeIfAbsent(klass, func);
/*     */ 
/*     */     
/* 148 */     for (Object key : this.listenerCache.keySet()) {
/* 149 */       if (key == object) return this.listenerCache.get(object);
/*     */     
/*     */     } 
/* 152 */     List<IListener> listeners = func.apply(object);
/* 153 */     this.listenerCache.put(object, listeners);
/* 154 */     return listeners;
/*     */   }
/*     */   
/*     */   private void getListeners(List<IListener> listeners, Class<?> klass, Object object) {
/* 158 */     for (Method method : klass.getDeclaredMethods()) {
/* 159 */       if (isValid(method)) {
/* 160 */         listeners.add(new LambdaListener(getLambdaFactory(klass), klass, object, method));
/*     */       }
/*     */     } 
/*     */     
/* 164 */     if (klass.getSuperclass() != null) getListeners(listeners, klass.getSuperclass(), object); 
/*     */   }
/*     */   
/*     */   private boolean isValid(Method method) {
/* 168 */     if (!method.isAnnotationPresent((Class)EventHandler.class)) return false; 
/* 169 */     if (method.getReturnType() != void.class) return false; 
/* 170 */     if (method.getParameterCount() != 1) return false;
/*     */     
/* 172 */     return !method.getParameters()[0].getType().isPrimitive();
/*     */   }
/*     */   
/*     */   private LambdaListener.Factory getLambdaFactory(Class<?> klass) {
/* 176 */     synchronized (this.lambdaFactoryInfos) {
/* 177 */       for (LambdaFactoryInfo info : this.lambdaFactoryInfos) {
/* 178 */         if (klass.getName().startsWith(info.packagePrefix)) return info.factory;
/*     */       
/*     */       } 
/*     */     } 
/* 182 */     throw new NoLambdaFactoryException(klass);
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\EventBus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */