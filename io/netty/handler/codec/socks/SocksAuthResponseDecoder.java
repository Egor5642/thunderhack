/*    */ package io.netty.handler.codec.socks;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ReplayingDecoder;
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
/*    */ 
/*    */ public class SocksAuthResponseDecoder
/*    */   extends ReplayingDecoder<SocksAuthResponseDecoder.State>
/*    */ {
/*    */   public SocksAuthResponseDecoder() {
/* 33 */     super(State.CHECK_PROTOCOL_VERSION);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
/*    */     SocksAuthStatus authStatus;
/* 39 */     switch ((State)state()) {
/*    */       case CHECK_PROTOCOL_VERSION:
/* 41 */         if (byteBuf.readByte() != SocksSubnegotiationVersion.AUTH_PASSWORD.byteValue()) {
/* 42 */           out.add(SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE);
/*    */           break;
/*    */         } 
/* 45 */         checkpoint(State.READ_AUTH_RESPONSE);
/*    */       
/*    */       case READ_AUTH_RESPONSE:
/* 48 */         authStatus = SocksAuthStatus.valueOf(byteBuf.readByte());
/* 49 */         out.add(new SocksAuthResponse(authStatus));
/*    */         break;
/*    */       
/*    */       default:
/* 53 */         throw new Error();
/*    */     } 
/*    */     
/* 56 */     channelHandlerContext.pipeline().remove((ChannelHandler)this);
/*    */   }
/*    */   
/*    */   public enum State
/*    */   {
/* 61 */     CHECK_PROTOCOL_VERSION,
/* 62 */     READ_AUTH_RESPONSE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksAuthResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */