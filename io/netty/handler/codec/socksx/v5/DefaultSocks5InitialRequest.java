/*    */ package io.netty.handler.codec.socksx.v5;
/*    */ 
/*    */ import io.netty.handler.codec.DecoderResult;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import io.netty.util.internal.StringUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class DefaultSocks5InitialRequest
/*    */   extends AbstractSocks5Message
/*    */   implements Socks5InitialRequest
/*    */ {
/*    */   private final List<Socks5AuthMethod> authMethods;
/*    */   
/*    */   public DefaultSocks5InitialRequest(Socks5AuthMethod... authMethods) {
/* 36 */     ObjectUtil.checkNotNull(authMethods, "authMethods");
/*    */     
/* 38 */     List<Socks5AuthMethod> list = new ArrayList<Socks5AuthMethod>(authMethods.length);
/* 39 */     for (Socks5AuthMethod m : authMethods) {
/* 40 */       if (m == null) {
/*    */         break;
/*    */       }
/* 43 */       list.add(m);
/*    */     } 
/*    */     
/* 46 */     this.authMethods = Collections.unmodifiableList((List<? extends Socks5AuthMethod>)ObjectUtil.checkNonEmpty(list, "list"));
/*    */   }
/*    */   
/*    */   public DefaultSocks5InitialRequest(Iterable<Socks5AuthMethod> authMethods) {
/* 50 */     ObjectUtil.checkNotNull(authMethods, "authSchemes");
/*    */     
/* 52 */     List<Socks5AuthMethod> list = new ArrayList<Socks5AuthMethod>();
/* 53 */     for (Socks5AuthMethod m : authMethods) {
/* 54 */       if (m == null) {
/*    */         break;
/*    */       }
/* 57 */       list.add(m);
/*    */     } 
/*    */     
/* 60 */     this.authMethods = Collections.unmodifiableList((List<? extends Socks5AuthMethod>)ObjectUtil.checkNonEmpty(list, "list"));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Socks5AuthMethod> authMethods() {
/* 65 */     return this.authMethods;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     StringBuilder buf = new StringBuilder(StringUtil.simpleClassName(this));
/*    */     
/* 72 */     DecoderResult decoderResult = decoderResult();
/* 73 */     if (!decoderResult.isSuccess()) {
/* 74 */       buf.append("(decoderResult: ");
/* 75 */       buf.append(decoderResult);
/* 76 */       buf.append(", authMethods: ");
/*    */     } else {
/* 78 */       buf.append("(authMethods: ");
/*    */     } 
/* 80 */     buf.append(authMethods());
/* 81 */     buf.append(')');
/*    */     
/* 83 */     return buf.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socksx\v5\DefaultSocks5InitialRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */