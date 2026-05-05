/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
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
/*    */ public final class SocksAuthResponse
/*    */   extends SocksResponse
/*    */ {
/* 28 */   private static final SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;
/*    */   private final SocksAuthStatus authStatus;
/*    */   
/*    */   public SocksAuthResponse(SocksAuthStatus authStatus) {
/* 32 */     super(SocksResponseType.AUTH);
/* 33 */     this.authStatus = (SocksAuthStatus)ObjectUtil.checkNotNull(authStatus, "authStatus");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksAuthStatus authStatus() {
/* 42 */     return this.authStatus;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/* 47 */     byteBuf.writeByte(SUBNEGOTIATION_VERSION.byteValue());
/* 48 */     byteBuf.writeByte(this.authStatus.byteValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksAuthResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */