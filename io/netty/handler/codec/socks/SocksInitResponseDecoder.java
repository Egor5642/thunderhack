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
/*    */ public class SocksInitResponseDecoder
/*    */   extends ReplayingDecoder<SocksInitResponseDecoder.State>
/*    */ {
/*    */   public SocksInitResponseDecoder() {
/* 33 */     super(State.CHECK_PROTOCOL_VERSION);
/*    */   }
/*    */   
/*    */   protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
/*    */     SocksAuthScheme authScheme;
/* 38 */     switch ((State)state()) {
/*    */       case CHECK_PROTOCOL_VERSION:
/* 40 */         if (byteBuf.readByte() != SocksProtocolVersion.SOCKS5.byteValue()) {
/* 41 */           out.add(SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE);
/*    */           break;
/*    */         } 
/* 44 */         checkpoint(State.READ_PREFERRED_AUTH_TYPE);
/*    */       
/*    */       case READ_PREFERRED_AUTH_TYPE:
/* 47 */         authScheme = SocksAuthScheme.valueOf(byteBuf.readByte());
/* 48 */         out.add(new SocksInitResponse(authScheme));
/*    */         break;
/*    */       
/*    */       default:
/* 52 */         throw new Error();
/*    */     } 
/*    */     
/* 55 */     ctx.pipeline().remove((ChannelHandler)this);
/*    */   }
/*    */   
/*    */   public enum State
/*    */   {
/* 60 */     CHECK_PROTOCOL_VERSION,
/* 61 */     READ_PREFERRED_AUTH_TYPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksInitResponseDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */