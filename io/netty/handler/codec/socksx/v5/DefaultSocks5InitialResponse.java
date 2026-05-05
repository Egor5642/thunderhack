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
/*    */ public class DefaultSocks5InitialResponse
/*    */   extends AbstractSocks5Message
/*    */   implements Socks5InitialResponse
/*    */ {
/*    */   private final Socks5AuthMethod authMethod;
/*    */   
/*    */   public DefaultSocks5InitialResponse(Socks5AuthMethod authMethod) {
/* 30 */     this.authMethod = (Socks5AuthMethod)ObjectUtil.checkNotNull(authMethod, "authMethod");
/*    */   }
/*    */ 
/*    */   
/*    */   public Socks5AuthMethod authMethod() {
/* 35 */     return this.authMethod;
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
/* 46 */       buf.append(", authMethod: ");
/*    */     } else {
/* 48 */       buf.append("(authMethod: ");
/*    */     } 
/* 50 */     buf.append(authMethod());
/* 51 */     buf.append(')');
/*    */     
/* 53 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5InitialResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */