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
/*    */ public class SocksAuthRequestDecoder
/*    */   extends ReplayingDecoder<SocksAuthRequestDecoder.State>
/*    */ {
/*    */   private String username;
/*    */   
/*    */   public SocksAuthRequestDecoder() {
/* 35 */     super(State.CHECK_PROTOCOL_VERSION);
/*    */   }
/*    */   protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
/*    */     int fieldLength;
/*    */     String password;
/* 40 */     switch ((State)state()) {
/*    */       case CHECK_PROTOCOL_VERSION:
/* 42 */         if (byteBuf.readByte() != SocksSubnegotiationVersion.AUTH_PASSWORD.byteValue()) {
/* 43 */           out.add(SocksCommonUtils.UNKNOWN_SOCKS_REQUEST);
/*    */           break;
/*    */         } 
/* 46 */         checkpoint(State.READ_USERNAME);
/*    */       
/*    */       case READ_USERNAME:
/* 49 */         fieldLength = byteBuf.readByte();
/* 50 */         this.username = SocksCommonUtils.readUsAscii(byteBuf, fieldLength);
/* 51 */         checkpoint(State.READ_PASSWORD);
/*    */       
/*    */       case READ_PASSWORD:
/* 54 */         fieldLength = byteBuf.readByte();
/* 55 */         password = SocksCommonUtils.readUsAscii(byteBuf, fieldLength);
/* 56 */         out.add(new SocksAuthRequest(this.username, password));
/*    */         break;
/*    */       
/*    */       default:
/* 60 */         throw new Error();
/*    */     } 
/*    */     
/* 63 */     ctx.pipeline().remove((ChannelHandler)this);
/*    */   }
/*    */   
/*    */   public enum State
/*    */   {
/* 68 */     CHECK_PROTOCOL_VERSION,
/* 69 */     READ_USERNAME,
/* 70 */     READ_PASSWORD;
/*    */   }
/*    */ }


/* Location:              C:\Users\Егор\Downloads\thunderhack-1.7.jar!\io\netty\handler\codec\socks\SocksAuthRequestDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */