/*    */ package thunder.hack.gui.font;
/*    */ 
/*    */ import net.minecraft.class_2960;
/*    */ 
/*    */ public class Texture {
/*    */   final class_2960 id;
/*    */   
/*    */   public Texture(String path) {
/*  9 */     this.id = class_2960.method_60655("thunderhack", validatePath(path));
/*    */   }
/*    */   
/*    */   public Texture(class_2960 i) {
/* 13 */     this.id = class_2960.method_60655(i.method_12836(), i.method_12832());
/*    */   }
/*    */   
/*    */   String validatePath(String path) {
/* 17 */     if (class_2960.method_20208(path)) {
/* 18 */       return path;
/*    */     }
/* 20 */     StringBuilder ret = new StringBuilder();
/* 21 */     for (char c : path.toLowerCase().toCharArray()) {
/* 22 */       if (class_2960.method_29184(c)) {
/* 23 */         ret.append(c);
/*    */       }
/*    */     } 
/* 26 */     return ret.toString();
/*    */   }
/*    */   
/*    */   public class_2960 getId() {
/* 30 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hack\gui\font\Texture.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */