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
/*    */ public final class SocksInitResponse
/*    */   extends SocksResponse
/*    */ {
/*    */   private final SocksAuthScheme authScheme;
/*    */   
/*    */   public SocksInitResponse(SocksAuthScheme authScheme) {
/* 31 */     super(SocksResponseType.INIT);
/* 32 */     this.authScheme = (SocksAuthScheme)ObjectUtil.checkNotNull(authScheme, "authScheme");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocksAuthScheme authScheme() {
/* 41 */     return this.authScheme;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encodeAsByteBuf(ByteBuf byteBuf) {
/* 46 */     byteBuf.writeByte(protocolVersion().byteValue());
/* 47 */     byteBuf.writeByte(this.authScheme.byteValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksInitResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */