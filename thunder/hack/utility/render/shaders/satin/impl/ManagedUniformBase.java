/*    */ package thunder.hack.utility.render.shaders.satin.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_283;
/*    */ import net.minecraft.class_5944;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ManagedUniformBase
/*    */ {
/*    */   protected final String name;
/*    */   
/*    */   public ManagedUniformBase(String name) {
/* 29 */     this.name = name;
/*    */   }
/*    */   
/*    */   public abstract boolean findUniformTargets(List<class_283> paramList);
/*    */   
/*    */   public abstract boolean findUniformTarget(class_5944 paramclass_5944);
/*    */   
/*    */   public String getName() {
/* 37 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\render\shaders\satin\impl\ManagedUniformBase.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */