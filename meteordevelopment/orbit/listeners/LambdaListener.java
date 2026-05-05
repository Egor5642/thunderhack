/*     */ package meteordevelopment.orbit.listeners;
/*     */ 
/*     */ import java.lang.invoke.LambdaMetafactory;
/*     */ import java.lang.invoke.MethodHandle;
/*     */ import java.lang.invoke.MethodHandles;
/*     */ import java.lang.invoke.MethodType;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.function.Consumer;
/*     */ import meteordevelopment.orbit.EventHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LambdaListener
/*     */   implements IListener
/*     */ {
/*     */   private static boolean isJava1dot8;
/*     */   private static Constructor<MethodHandles.Lookup> lookupConstructor;
/*     */   private static Method privateLookupInMethod;
/*     */   private final Class<?> target;
/*     */   private final boolean isStatic;
/*     */   private final int priority;
/*     */   private Consumer<Object> executor;
/*     */   
/*     */   public LambdaListener(Factory factory, Class<?> klass, Object object, Method method) {
/*  40 */     this.target = method.getParameters()[0].getType();
/*  41 */     this.isStatic = Modifier.isStatic(method.getModifiers());
/*  42 */     this.priority = ((EventHandler)method.<EventHandler>getAnnotation(EventHandler.class)).priority(); try {
/*     */       MethodHandles.Lookup lookup; MethodHandle methodHandle;
/*     */       MethodType invokedType;
/*  45 */       String name = method.getName();
/*     */ 
/*     */       
/*  48 */       if (isJava1dot8) {
/*  49 */         boolean a = lookupConstructor.isAccessible();
/*  50 */         lookupConstructor.setAccessible(true);
/*  51 */         lookup = lookupConstructor.newInstance(new Object[] { klass });
/*  52 */         lookupConstructor.setAccessible(a);
/*     */       } else {
/*     */         
/*  55 */         lookup = factory.create(privateLookupInMethod, klass);
/*     */       } 
/*     */       
/*  58 */       MethodType methodType = MethodType.methodType(void.class, method.getParameters()[0].getType());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  63 */       if (this.isStatic) {
/*  64 */         methodHandle = lookup.findStatic(klass, name, methodType);
/*  65 */         invokedType = MethodType.methodType(Consumer.class);
/*     */       } else {
/*     */         
/*  68 */         methodHandle = lookup.findVirtual(klass, name, methodType);
/*  69 */         invokedType = MethodType.methodType(Consumer.class, klass);
/*     */       } 
/*     */       
/*  72 */       MethodHandle lambdaFactory = LambdaMetafactory.metafactory(lookup, "accept", invokedType, MethodType.methodType(void.class, Object.class), methodHandle, methodType).getTarget();
/*     */       
/*  74 */       if (this.isStatic) { this.executor = lambdaFactory.invoke(); }
/*  75 */       else { this.executor = lambdaFactory.invoke(object); } 
/*  76 */     } catch (Throwable throwable) {
/*  77 */       throwable.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void call(Object event) {
/*  83 */     this.executor.accept(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getTarget() {
/*  88 */     return this.target;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPriority() {
/*  93 */     return this.priority;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStatic() {
/*  98 */     return this.isStatic;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/* 103 */       isJava1dot8 = System.getProperty("java.version").startsWith("1.8");
/*     */       
/* 105 */       if (isJava1dot8) {
/* 106 */         lookupConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[] { Class.class });
/*     */       } else {
/*     */         
/* 109 */         privateLookupInMethod = MethodHandles.class.getDeclaredMethod("privateLookupIn", new Class[] { Class.class, MethodHandles.Lookup.class });
/*     */       } 
/* 111 */     } catch (NoSuchMethodException e) {
/* 112 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Factory {
/*     */     MethodHandles.Lookup create(Method param1Method, Class<?> param1Class) throws InvocationTargetException, IllegalAccessException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\meteordevelopment\orbit\listeners\LambdaListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */