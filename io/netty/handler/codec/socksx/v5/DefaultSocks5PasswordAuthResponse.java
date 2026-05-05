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
/*    */ public class DefaultSocks5PasswordAuthResponse
/*    */   extends AbstractSocks5Message
/*    */   implements Socks5PasswordAuthResponse
/*    */ {
/*    */   private final Socks5PasswordAuthStatus status;
/*    */   
/*    */   public DefaultSocks5PasswordAuthResponse(Socks5PasswordAuthStatus status) {
/* 30 */     this.status = (Socks5PasswordAuthStatus)ObjectUtil.checkNotNull(status, "status");
/*    */   }
/*    */ 
/*    */   
/*    */   public Socks5PasswordAuthStatus status() {
/* 35 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     StringBuilder buf = new StringBuilder(StringUtil.simpleClassName(this));
/*    */     
/* 42 */     DecoderResult decoderResult = decoderResult();
/* 43 */     if (!decoderResult.isSuccess()) {
/* 44 */       buf.append("(decoderResult: ");
/* 45 */       buf.append(decoderResult);
/* 46 */       buf.append(", status: ");
/*    */     } else {
/* 48 */       buf.append("(status: ");
/*    */     } 
/* 50 */     buf.append(status());
/* 51 */     buf.append(')');
/*    */     
/* 53 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5PasswordAuthResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */