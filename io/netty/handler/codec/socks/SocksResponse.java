/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.util.internal.ObjectUtil;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SocksResponse
/*    */   extends SocksMessage
/*    */ {
/*    */   private final SocksResponseType responseType;
/*    */   
/*    */   protected SocksResponse(SocksResponseType responseType) {
/* 33 */     super(SocksMessageType.RESPONSE);
/* 34 */     this.responseType = (SocksResponseType)ObjectUtil.checkNotNull(responseType, "responseType");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksResponseType responseType() {
/* 43 */     return this.responseType;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */