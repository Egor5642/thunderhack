/*    */ package thunder.hack.setting.impl;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import org.lwjgl.glfw.GLFW;
/*    */ 
/*    */ public class Bind
/*    */ {
/*    */   private final int key;
/*    */   private boolean hold;
/*    */   private final boolean mouse;
/*    */   
/*    */   public Bind(int key, boolean mouse, boolean hold) {
/* 13 */     this.key = key;
/* 14 */     this.mouse = mouse;
/* 15 */     this.hold = hold;
/*    */   }
/*    */   
/*    */   public int getKey() {
/* 19 */     return this.key;
/*    */   }
/*    */   
/*    */   public String getBind() {
/* 23 */     if (this.mouse) return "M" + this.key;
/*    */     
/* 25 */     String kn = (this.key > 0) ? GLFW.glfwGetKeyName(this.key, 0) : "None";
/*    */     
/* 27 */     if (kn == null) {
/*    */       try {
/* 29 */         for (Field declaredField : GLFW.class.getDeclaredFields()) {
/* 30 */           if (declaredField.getName().startsWith("GLFW_KEY_")) {
/* 31 */             int a = ((Integer)declaredField.get(null)).intValue();
/* 32 */             if (a == this.key) {
/* 33 */               String nb = declaredField.getName().substring("GLFW_KEY_".length());
/* 34 */               kn = nb.substring(0, 1).toUpperCase() + nb.substring(0, 1).toUpperCase();
/*    */             } 
/*    */           } 
/*    */         } 
/* 38 */       } catch (Exception ignore) {
/* 39 */         kn = "unknown." + this.key;
/*    */       } 
/*    */     }
/*    */     
/* 43 */     return (this.key == -1) ? "None" : kn.toUpperCase();
/*    */   }
/*    */   
/*    */   public boolean isHold() {
/* 47 */     return this.hold;
/*    */   }
/*    */   
/*    */   public boolean isMouse() {
/* 51 */     return this.mouse;
/*    */   }
/*    */   
/*    */   public void setHold(boolean hold) {
/* 55 */     this.hold = hold;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\setting\impl\Bind.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */