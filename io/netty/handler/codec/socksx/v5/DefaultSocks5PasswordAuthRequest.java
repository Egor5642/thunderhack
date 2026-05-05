/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.StringUtil;
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
/*    */ public class DefaultSocks5PasswordAuthRequest
/*    */   extends AbstractSocks5Message
/*    */   implements Socks5PasswordAuthRequest
/*    */ {
/*    */   private final String username;
/*    */   private final String password;
/*    */   
/*    */   public DefaultSocks5PasswordAuthRequest(String username, String password) {
/* 31 */     ObjectUtil.checkNotNull(username, "username");
/* 32 */     ObjectUtil.checkNotNull(password, "password");
/*    */     
/* 34 */     if (username.length() > 255) {
/* 35 */       throw new IllegalArgumentException("username: **** (expected: less than 256 chars)");
/*    */     }
/* 37 */     if (password.length() > 255) {
/* 38 */       throw new IllegalArgumentException("password: **** (expected: less than 256 chars)");
/*    */     }
/*    */     
/* 41 */     this.username = username;
/* 42 */     this.password = password;
/*    */   }
/*    */ 
/*    */   
/*    */   public String username() {
/* 47 */     return this.username;
/*    */   }
/*    */ 
/*    */   
/*    */   public String password() {
/* 52 */     return this.password;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     StringBuilder buf = new StringBuilder(StringUtil.simpleClassName(this));
/*    */     
/* 59 */     DecoderResult decoderResult = decoderResult();
/* 60 */     if (!decoderResult.isSuccess()) {
/* 61 */       buf.append("(decoderResult: ");
/* 62 */       buf.append(decoderResult);
/* 63 */       buf.append(", username: ");
/*    */     } else {
/* 65 */       buf.append("(username: ");
/*    */     } 
/* 67 */     buf.append(username());
/* 68 */     buf.append(", password: ****)");
/*    */     
/* 70 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5PasswordAuthRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */