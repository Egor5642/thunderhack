/*    */ package thunder.hack.utility.discord.helpers;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RPCButton
/*    */   implements Serializable {
/*    */   private final String url;
/*    */   private final String label;
/*    */   
/*    */   public String getLabel() {
/* 12 */     return this.label;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 16 */     return this.url;
/*    */   }
/*    */   @NotNull
/*    */   public static RPCButton create(String substring, String s) {
/* 20 */     substring = substring.substring(0, Math.min(substring.length(), 31));
/* 21 */     return new RPCButton(substring, s);
/*    */   }
/*    */   
/*    */   protected RPCButton(String label, String url) {
/* 25 */     this.label = label;
/* 26 */     this.url = url;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\thunder\hac\\utility\discord\helpers\RPCButton.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */